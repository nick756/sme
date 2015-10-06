package com.sme.actions

import com.sme.entities.*
import com.sme.services.*
import grails.util.Environment

/**
 *  Support for full range of Mobile Operations. The following Methods are
 *  supported:
 *  
 *  -   login
 *  -   logout
 *  -   getoperations
 *  -   addtransaction
 *  -   listtransactions
 *  
 */
class MobileController {
    
    def mobileSessionService
    def businessTransactionService

    def index() { }

    def login() {
        def opStatus = 0;
        def user
        def userID  
        def login = params?.name
        def passw = params?.passw

        boolean failure = false
        def descr
        
        if(Environment.current == Environment.DEVELOPMENT) {
            println ''
            println "Operation LOGIN from ${request.getRemoteAddr()}"
        }
        
        if(login) {
            user = User.findByLogin(login)
            
            if(user && user?.passw == passw && user?.role?.code == 2) {
                userID = mobileSessionService.addUser(user)
                opStatus = 0
                
                if(Environment.current == Environment.DEVELOPMENT) {
                    println "User: ${user?.name} success"
                }
            }
            else {
                if(user && user?.role?.code != 2) {
                    userID = 0
                    opStatus = 1
                }
                else {
                    userID = 0
                    opStatus = 2
                }
            }
        }
        else {
            userID = 0
            opStatus = 2
        }
       
        render(contentType: 'text/xml') {
            result(code: opStatus, id: userID) {
                originator(request.getRemoteAddr())
                
                if(opStatus == 0) {
                    resDescription("Successful Login")
                }
                else if(opStatus == 1) {
                    resDescription("User Role is not supported for Mobile Interface")
                }
                else if(opStatus == 2) {
                    resDescription("Authentication failed")
                }
                else if(opStatus == 3) {
                    resDescription("Session expired")
                }
                
                if(opStatus == 0) {
                    operator {
                        name(user?.name)
                        role(user?.role?.name)
                        company(user?.company?.name)
                        companyID(user?.company?.id)
                    }
                }
                else {
                    operator{}
                }
            }
        }
    }
    
    /**
     *  Logout Operation, just cleaning Cash
     */
    def logout() {

        if(Environment.current == Environment.DEVELOPMENT) {
            println ''
            println "Operation LOGOUT from ${request.getRemoteAddr()}"
        }
        
        if(params?.id) {
            mobileSessionService.remove(new Integer(params?.id))
        }
        
        render(contentType: 'text/xml') {
            result(code: "0", id: "0") {
                originator(request.getRemoteAddr())
                resDescription("User Session terminated")
            }
        }
    }
    
    //  Forwarding list of supported Operations to Mobile Application
    
    def getoperations() {

        if(Environment.current == Environment.DEVELOPMENT) {
            println ''
            println "${new Date()} Operation GETOPERATIONS from ${request.getRemoteAddr()}"
        }
        
        def userID = new Integer(params?.id)
        def user
        def companyID = new Integer(params?.companyID)
        def company
        def oplist = []                 //  List of ProfileLink instances
        def profileName = "N/A"
        
        if(!mobileSessionService.validateTimeout(userID)) {
            
            if(Environment.current == Environment.DEVELOPMENT) {
                println "Timeout event: IP ${request.getRemoteAddr()}"
            }
            
            try {
                render(contentType: 'text/xml') {
                    result(code: "3", id: userID) {
                        originator(request.getRemoteAddr())
                        resDescription("Session expired")
                        profile(name: profileName){}
                    }
                }
            }
            catch (Exception e) {
                def xmlMessage = "<result code='3' id='${userID}'>"
                xmlMessage += "<originator>${request.getRemoteAddr()}</originator>"
                xmlMessage += "<resDescription>Session expired</resDescription>"
                xmlMessage += "<profile name='N/A'/>"
                
                render(xmlMessage)
            }
        }
        else {
            if(userID) {
                user = User.get(userID)
          
                if(companyID == user?.company?.id) {
                    company = Business.get(companyID)
                
                    if(company?.profile?.operations) {
                        oplist = company?.profile?.operations.asList()
                        profileName = company?.profile?.name
                    }
                
                    render(contentType: 'text/xml') {
                        result(code: "0", id: userID) {
                        
                            originator(request.getRemoteAddr())
                            resDescription("Successful Request")
                        
                            profile(name: profileName) {
                                oplist.each {
                                    def opinstance = it?.operation
                               
                                    operation {
                                        code(opinstance?.code)
                                        name(opinstance?.name)
                                        inbound(opinstance?.inbound)
                                        outbound(opinstance?.outbound)
                                        type(opinstance?.accountType?.name)
                                    }
                                }
                            }
                        }
                    }
                }
                else {
                    render(contentType: 'text/xml') {
                        result(code: "1", id: userID) {
                            originator(request.getRemoteAddr())
                            resDescription("Mismatching Company ID")
                            profile(name: profileName){}
                        }
                    }
                }
            }
            else {
                render(contentType: 'text/xml') {
                    result(code: "2", id: "0") {
                        originator(request.getRemoteAddr())
                        resDescription("Authentication failed")
                        profile(name: profileName){}
                    }
                }
            }
        }
    }
    
    /**
     *  Adding new Transaction from Mobile Application. Expected Parameters:
     *  -   id
     *  -   companyID
     *  -   operationCode
     *  -   amount
     *  -   date
     *  -   description
     */
    def addtransaction() {

        if(Environment.current == Environment.DEVELOPMENT) {
            println ''
            println "${new Date()} Operation ADDTRANSACTION from ${request.getRemoteAddr()}"
        }

        params.id               = params?.id ?: 0
        params.companyID        = params.companyID ?: '0'
        params.operationCode    = params?.operationCode ?: '0'
        params.amount           = params?.amount ?: '0'
        params.date             = params.date ?: new Date().format("d/M/yyyy")
        
        def userID          = new Integer(params?.id)
        def companyID       = new Integer(params?.companyID)
        def operationCode   = new Integer(params?.operationCode)
        def amount          = new Double(params?.operationAmount)
        def description     = params?.operationDescription
        def operationDate   = new Date().parse("d/M/yyyy", params?.date)
        Integer cashFlag
        
        if(params.cash == 'true') {
            cashFlag = 1
        }
        else {
            cashFlag = 0
        }
        
        def resultCode = 0
        def resultDesc = "New Transaction added"
        def transactID = 0
        
        if(Environment.current == Environment.DEVELOPMENT) {
            println "Passed Description: ${description}"
            println "All params:"
            println params
        }
        
        if(!mobileSessionService.validateTimeout(userID)) {
            
            if(Environment.current == Environment.DEVELOPMENT) {
                println "Timeout event: IP ${request.getRemoteAddr()}"
            }
            
            render(contentType: 'text/xml') {
                result(code: "3", id: userID) {
                    originator(request.getRemoteAddr())
                    resDescription("Session expired")
                }
            }
        }
        else {  //  Proceed to operation
            
            def user = User.get(userID)
            
            if(Environment.current == Environment.DEVELOPMENT) {
                println "In processing: ${user?.name}"
            }
            
            transactID = businessTransactionService.addTransaction(
                user,
                operationDate,
                operationCode,
                amount,
                description,
                cashFlag
            )
            
            if(transactID <= 0) {
                resultCode = 1
                resultDesc = "Operation failed"
            }
            
            render(contentType: 'text/xml') {
                result(code: resultCode, id: userID) {
                    originator(request.getRemoteAddr())
                    resDescription(resultDesc)
                    transactionID(transactID)
                }
            }
        }
    }   //  End of 'def addtransaction()'
    
    /**
     *  Request for list of Transactions performed within specified range of 
     *  dates.
     *  
     *  Expected Parameters:
     *  -   id
     *  -   datefrom
     *  -   datetill
     *  
     *  All Dates are inclusive. Range for Mobile application should not be
     *  more than 90 days
     */
    def listtransactions() {
        
        def userID      = new Integer(params?.id)
        def dateFrom    = new Date().parse("d/M/yyyy", params?.dateFrom)
        def dateTill    = new Date().parse("d/M/yyyy", params?.dateTill)
        Date dateCurr   = new Date()
        def user        = User.get(userID)
        def company     = user?.company
        def recCount    = 0
        def recordList  = []
        def errorCode   = 0
        
        if(Environment.current == Environment.DEVELOPMENT) {
            println ''
            println "${new Date().format('dd/MM/yyyy HH:mm:ss')} ${user?.login}: operation LISTTRANSACTIONS from ${request.getRemoteAddr()}"
        } 
        
        dateFrom.clearTime()
        dateTill.clearTime()
        dateCurr.clearTime()
        
        if(dateTill > dateCurr) {
            dateTill = dateCurr
        }
        
        if(dateTill < dateFrom) {
            dateFrom = dateTill
        }
        else if(dateTill - dateFrom > 90) {
            dateFrom = dateTill.minus(90)
        }
        
        if(!mobileSessionService.validateTimeout(userID)) {
            
            if(Environment.current == Environment.DEVELOPMENT) {
                println "Timeout event: IP ${request.getRemoteAddr()}"
            }
            
            render(contentType: 'text/xml') {
                result(code: "3", id: userID) {
                    originator(request.getRemoteAddr())
                    resDescription("Session expired")
                }
            }
        }        
        else {
            recordList  = businessTransactionService.getTransactions(company, dateFrom, dateTill)
            recCount    = recordList.size()
        
            if(Environment.current == Environment.DEVELOPMENT) {
                println "Total Transactions found : ${recCount}"
                println "Final Range of Dates     : ${dateFrom.format('dd/MM/yyyy')} - ${dateTill.format('dd/MM/yyyy')}"
            }
        
            render(contentType: 'text/xml', encoding: 'UTF-8') {
                result(code: errorCode, id: userID) {
                    originator(request.getRemoteAddr())
                
                    if(errorCode == 0) {
                        resDescription("Successful Operation")
                    }
                    else if(errorCode == 1) {
                        resDescription("Wrong Dates Range: defaulted to DateTill")
                    }
                    
                    dateStart   (dateFrom.format('dd/MM/yyyy'))
                    dateStop    (dateTill.format('dd/MM/yyyy'))
                    recordCount (recCount)
                
                    records {
                        recordList.each {
                            def recInstance = it
                            
                            //  Cannot use implicit variable 'it' in Builder !!!
                            
                            record {
                                tranCode    (recInstance?.id)
                                date        (recInstance?.transactionDate.format('dd/MM/yyyy'))
                                type        (recInstance?.operationType)
                                amount      (recInstance?.transactionAmount)
                                descr       (recInstance?.transactionRemarks)
                                operator    (recInstance?.operator)
                            }
                        }
                    }
                }
            }
        }
    }
}
