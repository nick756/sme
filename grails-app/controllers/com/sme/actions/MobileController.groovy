package com.sme.actions

import com.sme.entities.*
import com.sme.services.*

/**
 *  Support for full range of Mobile Operations
 */
class MobileController {
    
    def mobileSessionService

    def index() { }

    def login() {
        def opStatus = 0;
        def user
        def userID  
        def login = params?.name
        def passw = params?.passw

        boolean failure = false
        def descr
        
        println ''
        println "Operation LOGIN from ${request.getRemoteAddr()}"
        
        if(login) {
            user = User.findByLogin(login)
            
            if(user && user?.passw == passw && user?.role?.code == 2) {
                userID = mobileSessionService.addUser(user)
                opStatus = 0
                
                println "User: ${user?.name} success"
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

        println ''
        println "Operation LOGOUT from ${request.getRemoteAddr()}"
        
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

        println ''
        println "${new Date()} Operation GETOPERATIONS from ${request.getRemoteAddr()}"
        
        def userID = new Integer(params?.id)
        def user
        def companyID = new Integer(params?.companyID)
        def company
        def oplist = []                 //  List of ProfileLink instances
        def profileName = "N/A"
        
        if(!mobileSessionService.validateTimeout(userID)) {
            
            println "Timeout event: IP ${request.getRemoteAddr()}"
            
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
     *  Adding new Transaction
     */
    
    def addtransaction() {

        println ''
        println "${new Date()} Operation ADDTRANSACTION from ${request.getRemoteAddr()}"

        params.id               = params?.id ?: 0
        params.companyID        = params.companyID ?: '0'
        params.operationCode    = params?.operationCode ?: '0'
        params.amount           = params?.amount ?: '0'
        params.date             = params.date ?: new Date().format("d/M/yyyy")
        
        def userID          = new Integer(params?.id)
        def companyID       = new Integer(params?.companyID)
        def operationCode   = new Integer(params?.operationCode)
        def amount          = new Double(params?.amount)
        def description     = params?.description
        def operationDate   = new Date().parse("d/M/yyyy", params?.date)
        
        if(!mobileSessionService.validateTimeout(userID)) {
            
            println "Timeout event: IP ${request.getRemoteAddr()}"
            
            render(contentType: 'text/xml') {
                result(code: "3", id: userID) {
                    originator(request.getRemoteAddr())
                    resDescription("Session expired")
                }
            }
        }
        else {  //  Proceed to operation
            
            //  Actual Transactions are noy implemented yet
            //  As on 15/07/2015
            
            render(contentType: 'text/xml') {
                result(code: "0", id: userID) {
                    originator(request.getRemoteAddr())
                    resDescription("New Transaction added on ${operationDate.format('d/M/yyyy')}")
                    transactionID(1567)
                }
            }
        }
    }
}
