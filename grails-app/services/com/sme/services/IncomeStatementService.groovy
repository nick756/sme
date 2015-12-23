package com.sme.services

import grails.transaction.Transactional
import com.sme.entities.*

@Transactional(readOnly = true)
class IncomeStatementService {

    /**
     *  Fetching ALL Transactions that must be reflected in PNL Statements
     *  for a given Period; month can be zero (if full year is under consideration)
     */
    def getAllPNLTransactions(Business company, Integer year, Integer month) {
        
        def dateFrom
        def dateTill
        
        if(month > 0) {
            dateFrom = new Date().copyWith(year: year, month: month -1, dayOfMonth: 1)
            dateTill = new Date().copyWith(year: year, month: month - 1, dayOfMonth: dateFrom.toCalendar().getActualMaximum(Calendar.DAY_OF_MONTH))        
        }
        else {
            dateFrom = new Date().copyWith(year: year, month: 0, dayOfMonth: 1)
            dateTill = new Date().copyWith(year: year, month: 11, dayOfMonth: 31)
        }
        
        def transactions = BusinessTransaction.createCriteria().list() {
            eq('company', company)
            ge('transactionDate', dateFrom)
            le('transactionDate', dateTill)
            
            operationType {
                isNotNull('pnlGroup')
            }
            
            order('transactionDate')
        }
        
        println 'IncomeStatementService.getAllPNLTransactions'
        println "Year: ${year} Month: ${month} Count: ${transactions.size()}"
        
        return transactions        
    }
    
    /**
     *  Fetching all Transactions for a given Period that are to be reflected
     *  in PNL Statement: PNLgroup is used as selection criterion. PNL Statements
     *  can be either monthly, or annual (as on 17/12/2015).
     *  
     *  To be mostly used for Trading Businesses.
     **/
    def getTransactionsByGroup(Business company, Integer year, Integer month, PNLGroup group) {
        
        def dateFrom
        def dateTill
        
        if(month > 0) {
            dateFrom = new Date().copyWith(year: year, month: month - 1, dayOfMonth: 1)
            dateTill = new Date().copyWith(year: year, month: month - 1, dayOfMonth: dateFrom.toCalendar().getActualMaximum(Calendar.DAY_OF_MONTH))        
        }
        else {
            dateFrom = new Date().copyWith(year: year, month: 0, dayOfMonth: 1)
            dateTill = new Date().copyWith(year: year, month: 11, dayOfMonth: 31)
        }
        
        def transactions = BusinessTransaction.createCriteria().list() {
            eq('company', company)
            ge('transactionDate', dateFrom)
            le('transactionDate', dateTill)
            
            operationType {
                eq('pnlGroup', group)
            }
            
            order('transactionDate')
        }
        
        return transactions
    }
    
    /**
     *  Creation of new blank instance of PNLStatement, with prior verification
     *  that periods are not overlapping. If month == 0, then Statement is for full
     *  year
     **/
    def create(Business company, Integer year, Integer month) {
        boolean fullYear = (month > 0) ? false : true
        def dateFrom
        def dateTill
        def statement = null
        
        println ''
        println 'IncomeStatementService.create'
        println "Year: ${year} Month: ${month}"
        
        def existing = PNLStatement.createCriteria().list() {
            eq('company', company)
            eq('year', year)
            eq('month', month)
        }
        
        if(existing.size() > 0) {
            existing = null
            return null
        }
        
        if(fullYear) {
            dateFrom = new Date().copyWith(year: year, month: 0, dayOfMonth: 1)
            dateTill = new Date().copyWith(year: year, month: 11, dayOfMonth: 31)            
        }
        else {
            dateFrom = new Date().copyWith(year: year, month: month - 1, dayOfMonth: 1)
            dateTill = new Date().copyWith(year: year, month: month -1, dayOfMonth: dateFrom.toCalendar().getActualMaximum(Calendar.DAY_OF_MONTH))            
        }
        
        dateFrom.clearTime()
        dateTill.clearTime()
        
        statement = new PNLStatement(
            year:           year,
            month:          month,
            fullYear:       fullYear,
            dateFrom:       dateFrom,
            dateTill:       dateTill,
            grossProfit:    0,
            otherIncome:    0,
            expenses:       0,
            taxation:       0,
            netProfitBT:    0,
            netProfitAT:    0,
            company:        company
        ).save(flush: true)
        
        company.addToPnlStatements(statement)
        
        return statement
    }
    
    /*
     *  Creation of PNLSEction instances for a given PNLStatement
     */
    def createSections(Business company, PNLStatement statement) {
        def groups = PNLGroup.list(sort: 'code')
        
        groups.each{item ->
            def section = new PNLSection(
                group:          item,
                amountTotal:    0,
                statement:      statement
            )
            
            section.save(flush: true)
            statement.addToSections(section)
        }
        
        statement.sections.each{item ->
            def group = item.group
            
            def operations = GenericOperation.createCriteria().list() {
                eq('pnlGroup', group)
            }
            
            operations.each{op ->
                def line = new PNLSectionLine(
                    type:       op,
                    section:    item,
                    amount:     0
                ).save(flush: true)
                
                item.addToLines(line)
            }
        }
        
        calculate(company, statement)
        
        return true
    }
    
    /**
     *  Performing actual calculation for a given blank populated PNL Statement
     */
    def calculate(Business company, PNLStatement statement) {
        def dateFrom = statement.dateFrom
        def dateTill = statement.dateTill
        def operationType
        def group
        def transactions
        
        BigDecimal amountSal = 0
        BigDecimal amountCos = 0
        BigDecimal amountOth = 0
        BigDecimal amountAdm = 0
        BigDecimal amountTax = 0
        
        BigDecimal amountLine
        BigDecimal amountSection
        
        statement.sections.each {section ->
            amountSection = 0.0
            
            section.lines.each {line ->
                def opType = line.type
                amountLine = 0.0
                
                transactions = BusinessTransaction.createCriteria().list() {
                    eq('company', company)
                    ge('transactionDate', dateFrom)
                    le('transactionDate', dateTill)
                    eq('operationType', opType)
                }
                
                transactions.each {trans ->
                    amountLine += trans.transactionAmount
                }
                
                amountLine  = amountLine.setScale(2, BigDecimal.ROUND_HALF_UP)
                line.amount = amountLine
                line.save(flush: true)
                
                amountSection += amountLine
            }
            
            amountSection       = amountSection.setScale(2, BigDecimal.ROUND_HALF_UP)
            section.amountTotal = amountSection
            
            section.save(flush: true)
            
            switch(section?.group?.code) {
            case 1:     //  Sales
                amountSal += amountSection
                break
                    
            case 5:     //  Cost of Sales
                amountCos += amountSection
                break
                    
            case 10:    //  Other Income
                amountOth += amountSection
                break
                    
            case 15:    //  Admin Expenses
                amountAdm += amountSection
                break
                    
            case 20:    //  Taxation
                amountTax += amountSection
                break
            }             
        }
        
        //  Summurizing Results
        
        statement.grossProfit   = amountSal - amountCos
        statement.expenses      = amountCos + amountAdm
        statement.otherIncome   = amountOth
        statement.netProfitBT   = amountSal + amountOth - amountCos - amountAdm
        statement.taxation      = amountTax
        statement.netProfitAT   = amountSal + amountOth - amountCos - amountAdm - amountTax

        statement.save(flush: true)
        
        return true
    }
}
