package com.sme.actions

import com.sme.entities.*
import java.text.DecimalFormat
import grails.util.Environment

class AdminHomeController {

    def businessTransactionService
    //def messageSource
    
    def index(Integer max) {
        
        if(!session?.user) {
            redirect (controller: 'login')
        }
        
        params.offset = params.offset ?: 0
        params.max = Math.min(max ?: 10, 100)
        
        //  TODO:   check passed 'offset' when returning from Transactions List,
        //          can be beyond the range
        
        [businesses: Business.list(params), businessInstanceCount: Business.count()]
    }
    
    def show() {
        params.offset = params.offset ?: 0
        params.max = params.max ?: 0
        
        [businessInstance: Business.get(new Integer(params?.id)), params: [max: params?.max, offset: params?.offset]]
    }
    
    def edit() {
        if(!session?.user) {
            redirect (controller: 'login')
        }
        
        [businessInstance: Business.get(params?.id)]
    }
    
    /**
     *  Fetching list of performed Transactions for a given Company, filtering
     *  options can be applied
     */
    def listtransactions() {
        if(!session?.user) {
            redirect (controller: 'login')
        }
        
        def transList = []
        def transCount
        def filtOption
        def opFilter = 0
        
        def Date dateFrom
        def Date dateTill
        
        if(Environment.current == Environment.DEVELOPMENT) {
            println ''
            println "--- ${session.user.login}: in Method \'listtransactions\' ---"
            println "Forwarded params"
            println params
        }
        
        params.offset = params.offset ?: 0
        params.max = params.max ?: 10
        
        //  Overring default Grails Form behavior (cannot pass existing id)
        params.id = params.id ?: params.instId
        
        if(params?.filterOption) {
            filtOption = params?.filterOption.toLong()
            
            if(filtOption > 1) {
                if(filtOption == 2) {   //  One Month Period (inclusive)
                    dateTill = new Date().clearTime()
                    dateFrom = dateTill.minus(31)
                    
                    opFilter = 2
                    
                    //  Overriding 'offset' as filtering may be done not
                    //  from first page
                    params.offset = 0
                    
                    transList = BusinessTransaction.createCriteria().list() {
                        eq('company', Business.get(params.id))
                        
                        between('transactionDate', dateFrom, dateTill)
                       
                        and {
                            order('transactionDate', 'asc')
                            order('id', 'asc')
                        }
                    }
                    
                }
                else {                  //  Range of Dates
                    if(params?.dateFrom && params?.dateTill) {
                        dateFrom = new Date().parse("d/M/yyyy", params?.dateFrom)
                        dateTill = new Date().parse("d/M/yyyy", params?.dateTill)
                        
                        transList = BusinessTransaction.createCriteria().list() {
                            eq('company', Business.get(params.id))
                        
                            between('transactionDate', dateFrom, dateTill)
                       
                            and {
                                order('transactionDate', 'asc')
                                order('id', 'asc')
                            }
                        }
                        
                        opFilter = 3
                    }
                    else {              //  Fall back to default
                        
                    }
                }
            }
            else {
                transList = BusinessTransaction.findAllByCompany(
                    Business.get(params?.id), 
                    [sort: "transactionDate", order: "asc"],
                    [sort: 'id', order: 'asc']
                )                
            }
        }
        else {
            transList = BusinessTransaction.findAllByCompany(
                Business.get(params?.id), 
                [sort: "transactionDate", order: "asc"],
                [sort: 'id', order: 'asc']                
            )
        }
        
        //  Overriding default pagination options: manual slicing list of
        //  fetched Transaction
        
        if(Environment.current == Environment.DEVELOPMENT) {
            println "Total Records found before pagination: ${transList.size()}"
        }
        
        transCount = transList.size()
        def indexTo     = new Integer(params?.offset) + new Integer(params?.max) - 1
        def indexFrom   = new Integer(params?.offset)
        
        if(indexTo >= transCount) indexTo = transCount - 1
        
        if(transCount > 0) {
            transList = transList[indexFrom..indexTo]
        }
        
        if(Environment.current == Environment.DEVELOPMENT) {
            println "Total Records passed after pagination: ${transList.size()}"
        }
        
        [
            businessInstance:   Business.get(new Integer(params?.id)),
            transactionsList:   transList,
            transactionCount:   transCount,
            operationFilter:    opFilter,
            dateFrom:           dateFrom,
            dateTill:           dateTill,
            params:             [max: params?.max, offset: params?.offset]
        ]
    }
    
    /**
     *  Managing PNL Statements for a given Business instance
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
        if(!session?.user) {
            redirect (controller: 'login')
        }
        
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
        
        boolean create = false
        
        params.id = params.id ?: params.instID
        
        if(params.id) {
            businessID = new Integer(params?.id)
        }
       
        if(businessID) {
            business = Business.get(businessID)
        }
        
        if(params.period_year) {
            year = new Date().copyWith (
                year: new Integer(params.period_year),
                month: Calendar.JANUARY,
                dayOfMonth: 1
            )
            
            yearPassed = new Integer(params.period_year)
        }
        
        if(params.month && params.month?.id != 'null') {
            month = Month.get(new Integer(params?.month?.id))
            monthPassed = month.number
        }
        
        if(year && month) {
            create = true
        }
        else if(year && !month) {
            errMessage = "${message(code: 'pnlstatement.error.missing_month')}"
        }
        
        statements = PNLStatement.findAllByCompany(Business.get(businessID))
        
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
            prevStatement = PNLStatement.createCriteria().get {
                eq('company', business)
                eq('year', yearPassed)
                eq('month', monthPassed)
            }
            
            if(prevStatement) {
                create = false
                errMessage = messageSource.getMessage('application.error.duplicated_pnl_period')
            }
            else {
                prevStatement = null
                
                monthPrev = monthPassed - 1
                yearPrev = yearPassed
                
                if(monthPrev == 0) {
                    monthPrev = 12
                    yearPrev = yearPassed - 1
                }
                
                prevStatement = PNLStatement.createCriteria().get {
                    eq('company', business)
                    eq('year', yearPrev)
                    eq('month', monthPrev)
                }
                
                if(!prevStatement) {
                    create = false  //  Missing previous period
                    errMessage = messageSource.getMessage('application.error.missing_pnl_period')
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
            }
        }
        
        [
            statementList:      statements,
            statementCount:     statements.size(),
            businessInstance:   business,
            monthInst:          month,
            yearInst:           year,
            params:             params
        ]
    }
}
