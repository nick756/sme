package com.sme.actions

import com.sme.entities.*
import java.text.DecimalFormat
import grails.util.Environment
import grails.transaction.Transactional

/**
 *  Managing all aspects of Cash Flow Reporting for a Company, SME Operators
 *  Users Group
 */
class SmestatementController {

    def businessTransactionService
    def cashFlowService
    
    def index() { 
        if(!session.user) {
            redirect (controller: 'login', action: 'index')
            return
        }
        
        params.offset = params.offset ?: 0
        params.max = params.max ?: 12
        
        def user = session?.user
        def companyID = session?.company?.id
        def company
        
        if(!user.isAttached()) {
            user.attach()
        }
        
        company = Business.get(companyID)
        
        def statements = CashFlowStatement.createCriteria().list(params) {
            eq('company', company)
        }
        
        [
            statements: statements,
            totalCount: statements.totalCount,
            params: params,
            businessInstance: company
        ]
    }
    
    //  Show Form for creation of new Cash Flow Summary for a Month
    
    def create() {
        if(!session?.user) {
            redirect (controller: 'login')
            return
        } 
        
        [params: params, errMsg: params?.errMsg]
    }
    
    //  Actual generation of new new Cash Flow Summary
    
    def generate() {
        if(!session?.user) {
            redirect (controller: 'login')
            return
        } 
        
        params.offset = params.offset ?: 0
        params.max = params.max ?: 12        
        
        def user = session?.user
        def companyID = session?.company?.id
        Business company
        def errMsg
        def year, yearPassed, yearPrev
        def month, monthPassed, monthPrev
        def transList
        
        if(!user.isAttached()) {
            user.attach()
        }

        company = Business.get(companyID)
        
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
        
        def statements = CashFlowStatement.findAllByCompany(company)
        def prevStatement        
        
        if(Environment.current == Environment.DEVELOPMENT) {
            println ''
            println "--- ${session.user.login}: ${params.controller}/${params.action}"
            println "--- DEV. MODE"
            println "Identified Company   : ${company?.name}"
            println "Selected Year        : ${params?.period_year}"
            println "Selected Month       : ${params?.month?.id} -> ${month}/${month?.number}"
            println "Existing Statements  : ${statements.size()}"
        }
        
        //  Verification of Operation: 
        //  1   Month and Year are indicated
        //  2   Statement for given Month does not yet exist
        //  3   No missing Month(s) (gaps) in Statements
        
        if(year && !month) {
            errMsg  = "${message(code: 'pnlstatement.error.missing_month')}"
            month       = null
            
            render view: 'create', model: [errMsg: errMsg, max: params.max, offset: params.offset]
            return
        }
        
        if(statements.size() > 0) {
            prevStatement = CashFlowStatement.createCriteria().get {
                eq('company', company)
                eq('year', yearPassed)
                eq('month', monthPassed)
            }
            
            if(prevStatement) {
                errMsg = message(code: 'application.error.duplicated_cf_period')
                render view: 'create', model: [errMsg: errMsg, max: params.max, offset: params.offset]
                return
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
                    eq('company', company)
                    eq('year', yearPrev)
                    eq('month', monthPrev)
                }
                
                if(!prevStatement && month) {
                    errMsg = message(code: 'application.error.missing_cf_period')
                    render view: 'create', model: [errMsg: errMsg, max: params.max, offset: params.offset]
                    return                    
                }
            }
        }
        
        //  Creation of new Cash Flow Summary instance
        
        transList = businessTransactionService.checkAvailableTransactions(company, new Integer(params.period_year), month.number - 1)
        
        if(transList) {
            if(Environment.current == Environment.DEVELOPMENT) {
                println "Proceed to creation of new Statement Record"
            }
                
            def newStatement = cashFlowService.createNewCFStatement(company, yearPassed, monthPassed)
            cashFlowService.populateCFStatement(newStatement, transList)
            cashFlowService.calculateCFStatement(newStatement)
        }
        
        redirect (
            action: 'index',
            params: [max: params.max, offset: params.offset]
        )
    }
    
    //  Deleting ALL Cash Flow Summaries for Company
    
    def delete() {
        if(!session?.user) {
            redirect (controller: 'login')
            return
        } 
        
        def user = session?.user
        def companyID = session?.company?.id
        def company
        
        if(!user.isAttached()) {
            user.attach()
        }
 
        
        if(companyID) {
            company = Business.get(companyID)
        }
        
        def statements = company.statements as List
        def transactions
        
        statements.each {statement ->
            transactions = BusinessTransaction.findAllByStatement(statement)
            
            transactions.each {transaction ->
                println "Removing ${transaction}"
                statement.removeFromTransactions(transaction)
                
            }
            
            statement.save(flush: true)
            company.removeFromStatements(statement)
            //company.save(flush: true)
            
            println "Deleted Statement ${statement.id}: ${statement}"
            println "Transactions in Statement: ${statement?.transactions}"
            statement.delete(flush: true)
        }

        redirect action: 'index', params: [params: params]        
    }
}
