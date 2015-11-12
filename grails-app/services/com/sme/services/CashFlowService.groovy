package com.sme.services

import grails.transaction.Transactional
import com.sme.entities.*
import grails.util.Environment

/**
 *  Managing Cash Flow Statements
 */
@Transactional(readOnly = true)
class CashFlowService {

    /**
     *  Creation of new CashFlow instance (blank) for a Company
     */
    def createNewCFStatement(Business company, Integer year, Integer month) {
        //def user = session?.user
        
        def statement = new CashFlowStatement(
            year:               year,
            month:              month,
            inflow:             0,
            outflow:            0,
            nettAmount:         0,
            cumulativeAmount:   0,
            openingBalance:     0,
            company:            company
        ).save(flush: true)
        
        company.addToStatements(statement)
        
        return statement
    }
    
    def populateCFStatement(CashFlowStatement statement, List transactions) {
        transactions.each { item ->
            statement.addToTransactions(item)
        }
        
        if(Environment.current == Environment.DEVELOPMENT) {
            println ''
            println "--- ${new Date().format('dd/MM/yyyy HH:mm:ss')} - CashFlowService.populateCFStatement ---"
            println "Records added: ${statement?.transactions.size()}"
        }
        
        statement.save(flush: true)
    }
    
    def calculateCFStatement(CashFlowStatement statement) {
        def transactions = statement?.transactions
        def prevStatement
        
        BigDecimal inflow   = new BigDecimal(0)
        BigDecimal outflow  = new BigDecimal(0)
        BigDecimal result   = new BigDecimal(0)
        
        if(Environment.current == Environment.DEVELOPMENT) {
            println ''
            println "--- ${new Date().format('dd/MM/yyyy HH:mm:ss')} - CashFlowService.calculateCFStatement ---"
            println "Records count     : ${transactions.size()}"
        } 
        
        if(transactions.size() > 0) {
            transactions.each {item ->
                if(item?.operationType.actual == 1) {
                    if(item?.operationType.inbound) {
                        inflow += item?.transactionAmount
                    }
                    else {
                        outflow += item?.transactionAmount
                    }
                }
            }
            
            result = inflow - outflow
            
            statement.inflow        = inflow
            statement.outflow       = outflow
            statement.nettAmount    = result
            
            //  Looking up previous Cash Flow Statement (may not exist)
            
            def prevYear  = statement.year
            def prevMonth = statement.month - 1
            
            if(prevMonth == 0) {
                prevMonth = 12
                prevYear  = prevYear - 1
            }
            
            prevStatement = CashFlowStatement.createCriteria().get {
                eq('company', statement.company)
                eq('year', prevYear)
                eq('month', prevMonth)
            } 
            
            if(prevStatement) {
                statement.openingBalance = prevStatement.cumulativeAmount
                statement.cumulativeAmount = result + statement.openingBalance
            }
            else {
                statement.cumulativeAmount = result
            }
            
            /*******************************************************************
             *  Managing Cash entries (in Hand and at Bank)
             ******************************************************************/
            BigDecimal cashHand = 0
            BigDecimal cashBank = 0
            
            transactions.each {
                switch(it?.operationType?.group?.code) {
                    case 5:
                        cashHand += it.transactionAmount
                        break
                    case 6:
                        cashBank += it.transactionAmount
                        break
                }
            }
            
            statement.cashHand = cashHand
            statement.cashBank = cashBank
            statement.save(flush: true)
            
            if(Environment.current == Environment.DEVELOPMENT) {
                println "Calculations done : ${result}"
            }
        }
    }
}
