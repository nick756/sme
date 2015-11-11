package com.sme.actions

import com.sme.entities.*
import java.text.*
import grails.util.Environment
import grails.transaction.Transactional

class AdminHomeController {

    def businessTransactionService
    def cashFlowService
    def exportEntitiesService
    def messageSource
    
    def index(Integer max) {
        
        def resultList
        def filterName = ''
        def filterAccount = ''
        def filterCity = ''
        
        if(!session?.user) {
            redirect (controller: 'login')
            return
        }
        
        params.offset = params.offset ?: 0
        params.max = params.max ?: 10
        
        if(params.filterName) {
            filterName = params.filterName
        }
        
        if(params.filterAccount) {
            filterAccount = params.filterAccount
        }
        
        if(params.filterCity) {
            filterCity = params.filterCity
        }
        
        resultList = Business.createCriteria().list(params) {
            if(filterName != '') {
                ilike('name', "%${filterName}%")
            }
            
            if(filterAccount != '') {
                ilike('accountNo', "${filterAccount}%")
            }
            
            if(filterCity != '') {
                ilike('city', "%${filterCity}%")
            }
        }
        
        //  TODO:   check passed 'offset' when returning from Transactions List,
        //          can be beyond the range
        
        [
            businesses: resultList, 
            businessInstanceCount: resultList.totalCount,
            params: params
        ]
    }
    
    def show() {
        if(!session?.user) {
            redirect (controller: 'login')
            return
        }        

        params.offset = params.offset ?: 0
        params.max = params.max ?: 10
        
        [businessInstance: Business.get(new Integer(params?.id)), params: [max: params?.max, offset: params?.offset]]
    }
    
    def edit() {
        if(!session?.user) {
            redirect (controller: 'login')
        }
        
        [businessInstance: Business.get(params?.id)]
    }
    
    def createinstance() {
        respond new Business(params)
    }
    
    def save() {
        def businessInstance = new Business(params)
        
        if(!businessInstance.validate()) {
            respond businessInstance.errors, view: 'createinstance'
            println "New Business: errors"
            return
        }
        else {
            businessInstance.save(flush: true)
        }
        
        redirect action: 'index'
    }
    
    /**
     *  Fetching list of performed Transactions for a given Company, filtering
     *  options can be applied
     */
    def listtransactions() {
        if(!session?.user) {
            redirect (controller: 'login')
            return
        }
        
        def transList = []
        def transCount
        def filtOption
        def opFilter = 0
        
        def dateFrom
        def dateTill
        def dateFromStr
        def dateTillStr
        Business businessInstance
        
        if(Environment.current == Environment.DEVELOPMENT) {
            println ''
            println "--- ${new Date()}: ${session.user.login} in ${params.controller}/${params?.action} ---"
            println "Current Locale: ${session.locale}"
            
            if(session.locale == null || session.locale.toString() == 'en_us') {
                println "English Version must be used"
            }
            print "Forwarded params: "
            println params
        }
        
        params.offset = params.offset ?: 0
        params.max = params.max ?: 10
        
        //  Overring default Grails Form behavior (cannot pass existing id)
        params.id = params.id ?: params.instId
        businessInstance = Business.get(new Integer(params?.id))
        filtOption = 1
        
        if(params?.filterOption) {
            filtOption = params?.filterOption.toLong()
        }
        
        switch(filtOption) {
        case 1:
            transList = BusinessTransaction.createCriteria().list(params) {
                eq('company', businessInstance)

                and {
                    order('transactionDate', 'asc')
                    order('id', 'asc')
                }                
            }
            break

        case 2:
            dateTill = new Date().clearTime()
            dateFrom = dateTill.minus(31)

            transList = BusinessTransaction.createCriteria().list(params) {
                eq('company', businessInstance)

                between('transactionDate', dateFrom, dateTill)

                and {
                    order('transactionDate', 'asc')
                    order('id', 'asc')
                }
            }
            break

        case 3:
            dateFrom = null
            dateTill = null

            if(params?.dateFrom && params?.dateTill) {
                try {
                    dateFrom = new Date().parse("d/M/yyyy", params?.dateFrom).clearTime()
                    dateTill = new Date().parse("d/M/yyyy", params?.dateTill).clearTime() 
                }
                catch(Exception e) {
                    filtOption = 1
                }
            }

            transList = BusinessTransaction.createCriteria().list(params) {
                eq('company', businessInstance)

                if(dateFrom && dateTill) {
                    between('transactionDate', dateFrom, dateTill + 1)
                }

                and {
                    order('transactionDate', 'asc')
                    order('id', 'asc')
                }
            }
            break
        }
        
        if(Environment.current == Environment.DEVELOPMENT) {
            println "Total Records filtered: ${transList.totalCount}"
            println "Total Records after pagination: ${transList.size()}"
            println "operationFilter: ${filtOption}"
            println "dateFrom: ${dateFrom}"
            println "dateTill: ${dateTill}"
        }        
        
        if(dateFrom && dateTill) {
            dateFromStr = new SimpleDateFormat('dd/MM/yyyy').format(dateFrom)
            dateTillStr = new SimpleDateFormat('dd/MM/yyyy').format(dateTill)
        }
        else {
            dateFromStr = null
            dateTillStr = null
        }
        
        [
            businessInstance:   businessInstance,
            transactionsList:   transList,
            transactionCount:   transList.totalCount,
            operationFilter:    filtOption,
            dateFrom:           dateFromStr,
            dateTill:           dateTillStr,
            params:             [max: params?.max, offset: params?.offset]
        ]
    }
    
    /**
     *  Managing CF Statements for a given Business instance
     *  
     *  Passed from GSP Page:
     *  
     *  params.period_year
     *  params.month
     *  params.generate (1/null)
     *  
     *  Scenarios to handle:
     *  
     *  1   Statement does not exist, Transactions for the period are found
     *  2   Statement does not exist, Transactions are NOT found
     *  3   Statement already exists
     *  
     *  Must be banned:
     *  
     *  1   Preparing Statement out of sequence (no gaps are allowed)
     *  2   Future dated Statements
     *  
     */
    def statements() {
       
        String errMessage
        
        def statements = []
        def transList  = []
        def prevStatement = null
        
        def businessID
        def business
        def month = null
        def year  = null
        
        Integer yearPassed  = 0
        Integer monthPassed = 0     //  Must be 1..12
        Integer monthPrev
        Integer yearPrev
        
        boolean create = false
        
        if(!session?.user) {
            redirect (controller: 'login')
            return
        }        
        
        params.id = params.id ?: params.instID
        
        if(params.id) {
            businessID = new Integer(params?.id)
        }
       
        if(businessID) {
            business = Business.get(businessID)
        }
        
        if(params.period_year) {
            year = new Date().copyWith (
                year:       new Integer(params.period_year),
                month:      Calendar.JANUARY,
                dayOfMonth: 1
            )
            
            yearPassed = new Integer(params.period_year)
        }
        
        if(params.month && params.month?.id != 'null') {
            month       = Month.get(new Integer(params?.month?.id))
            monthPassed = month.number
        }
        else {
            monthPassed = -1
        }
        
        if(year && month) {
            create = true
        }
        else if(year && !month) {
            errMessage  = "${message(code: 'pnlstatement.error.missing_month')}"
            month       = null
        }
        
        statements = CashFlowStatement.findAllByCompany(Business.get(businessID))
        
        if(Environment.current == Environment.DEVELOPMENT) {
            println ''
            println "--- ${session.user.login}: In Method \'statements\' ---"
            println "Passed ID            : ${businessID} / ${params?.instID}"
            println "Identified Company   : ${business?.name}"
            println "Selected Year        : ${params?.period_year}"
            println "Selected Month       : ${params?.month?.id} -> ${month}/${month?.number}"
            println "Existing Statements  : ${statements.size()}"
            println "Create new Statement : ${create}"
            println "Error Message: ${errMessage}"
        }
        
        //  Check that Statement for the  period does not yet exist, and if it
        //  is not the first statement, then previous period is covered
        
        if(statements.size() > 0) {
            prevStatement = CashFlowStatement.createCriteria().get {
                eq('company', business)
                eq('year', yearPassed)
                eq('month', monthPassed)
            }
            
            if(prevStatement) {
                create = false
                errMessage = message(code: 'application.error.duplicated_cf_period')
                println 'Duplicated Statement Period'
                println errMessage
            }
            else {
                prevStatement = null
                
                monthPrev = monthPassed - 1
                yearPrev = yearPassed
                
                if(monthPrev == 0) {
                    monthPrev = 12
                    yearPrev = yearPassed - 1
                }
                
                prevStatement = CashFlowStatement.createCriteria().get {
                    eq('company', business)
                    eq('year', yearPrev)
                    eq('month', monthPrev)
                }
                
                if(!prevStatement && month) {
                    create = false  //  Missing previous period
                    errMessage = message(code: 'application.error.missing_cf_period')
                }
            }
        }
        
        if(create) {
            transList = businessTransactionService.checkAvailableTransactions(business, new Integer(params.period_year), month.number - 1)
        
            if(Environment.current == Environment.DEVELOPMENT) {
                println ''
                println '--- Return from service'
                println "Transactions Found :"
                println transList
            }
            
            if(transList) {
                if(Environment.current == Environment.DEVELOPMENT) {
                    println "Proceed to creation of new Statement Record"
                }
                
                def newStatement = cashFlowService.createNewCFStatement(business, yearPassed, monthPassed)
                cashFlowService.populateCFStatement(newStatement, transList)
                cashFlowService.calculateCFStatement(newStatement)
            }
        }
        
        //  Statements List must be refreshed after new instance
        statements = CashFlowStatement.findAllByCompany(Business.get(businessID))
        
        [
            statementList:      statements,
            statementCount:     statements.size(),
            businessInstance:   business,
            monthInst:          month,
            yearInst:           year,
            params:             params,
            errMessage:         errMessage,
            params:             params
        ]
    }
    
    /**
     *  Cash Flow Report for selected Month
     */
    
    def statementdetails(CashFlowStatement summary) {
        if(!session?.user) {
            redirect controller: 'login', action: 'index'
            return
        }
        
        def groupsMap = [:]
        def transactionsGroup
        
        CFGroup.list().each {group ->
            
            transactionsGroup = BusinessTransaction.createCriteria().list() {
                eq('statement', summary)
                
                operationType {
                    eq('group', group)
                }
                
                order('transactionDate')
            }
            
            groupsMap.put(group, transactionsGroup)
        }
        
        if(Environment.current == Environment.DEVELOPMENT) {
            println ''
            println "--- ${session.user.login} ${new Date().format('dd/MM/yyyy HH:mm')}: method ${params?.controller}/${params.action} ---"
            println "Received Summary: ${summary}"
            //            println "Transactions    : ${summary?.transactions?.toList().size()}"
            //            println summary?.transactions?.toList()
            //            println "Group Map       : ${groupsMap}"
            
        }   
        
        [
            groups: groupsMap,
            summary: summary,
            company: summary?.company
        ]
    }
    
    def deleteAllStatements(Business businessInstance) {
        
        def statements = businessInstance.statements as List
        def transactions
        
        statements.each {statement ->
            transactions = BusinessTransaction.findAllByStatement(statement)
            
            transactions.each {transaction ->
                statement.removeFromTransactions(transaction)
            }
            
            statement.save(flush: true)
            businessInstance.removeFromStatements(statement)
            statement.delete(flush: true)
        }

        redirect action: 'listtransactions', params: ['id': businessInstance.id]
    }
    
    //  Exporting entities in CSV format

    def export() {
        def busiCount = exportEntitiesService.exportBusinesses()
        def userCount = exportEntitiesService.exportUsers()
        def tranCount = exportEntitiesService.exportTransactions()
        def profCount = exportEntitiesService.exportProfiles()
        def operCount = exportEntitiesService.exportOperations()
        
        [
            busiCount: busiCount,
            userCount: userCount,
            tranCount: tranCount,
            profCount: profCount,
            operCount: operCount
        ]
    }
}
