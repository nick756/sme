package com.sme.services

import grails.transaction.Transactional
import java.text.DecimalFormat
import com.sme.entities.*
import groovy.util.logging.Log4j
import grails.util.Environment

@Log4j
@Transactional
class BusinessTransactionService {
    
    /*
     *  Adding New Transaction: using Mobile interface
     */
    def addTransaction(User operator, Date date, Integer code, double amount, String description) {
        def company = operator?.company
        
        if(company) {
            def newTransaction = new BusinessTransaction(
                operationType:      GenericOperation.findByCode(code),
                transactionDate:    date,
                transactionAmount:  amount,
                transactionRemarks: description,
                operator:           "${operator?.name}",
                company:            company
            )
        
            if(!newTransaction.validate()) {
                println "User: ${operator?.name} - validation failed for ${description}"
                return 0
            }
        
            else {
                newTransaction.save(flush: true)
                company.addToBusinessTransactions(newTransaction)
                return newTransaction.id
            }
        }
        else {
            return -1
        }
    }
    
    /**
     *  Adding Transaction through Web Interface
     */ 
    def addTransactionWeb(Business company, User user, Date date, Integer code, double amount, String description) {
        if(company) {
            def newTransaction = new BusinessTransaction(
                operationType:      GenericOperation.findByCode(code),
                transactionDate:    date,
                transactionAmount:  amount,
                transactionRemarks: description,
                operator:           "${operator?.name}",
                company:            company
            )
        
            if(!newTransaction.validate()) {
                return 0
            }
        
            else {
                newTransaction.save(flush: true)
                company.addToBusinessTransactions(newTransaction)
                return newTransaction.id
            }
        }
        else {
            return -1
        }        
    }
    
    /*
     *  Fetching list of Transactions for a given range of dates. Range of Dates
     *  is controlled for Mobile Interface in respective Controller
     */
    @Transactional (readOnly = true)
    def getTransactions(Business company, Date from, Date till) {
        BusinessTransaction.createCriteria().list() {
            eq('company', company)
            between('transactionDate', from, till)
            
            and {
                order('transactionDate', 'asc')
                order('id', 'asc')
            }
        }
    }
    
    /**
     *  Checking available Transactions for PNL Statement: PNLStatement instance
     *  must be checked prior to calling this method, to avoid duplicates
     */
    def checkAvailableTransactions(Business company, int year, int month) {
        List result = []
        Date dateFrom
        Date dateTill
        
        dateFrom = new Date().copyWith(year: year, month: month, dayOfMonth: 1)
        dateTill = new Date().copyWith(year: year, month: month, dayOfMonth: dateFrom.toCalendar().getActualMaximum(Calendar.DAY_OF_MONTH))
        
        dateFrom.clearTime()
        dateTill.clearTime()
        
        result = BusinessTransaction.createCriteria().list() {
            eq('company', company)
            between('transactionDate', dateFrom, dateTill)
            isNull('statement')
        }
        
        if(Environment.current == Environment.DEVELOPMENT) {
            println ''
            println "--- ${new Date().format('dd/MM/yyyy HH:mm:ss')} - BusinessTransactionService.checkAvailableTransactions ---"
            println "Passed Date from   : ${dateFrom.format('dd/MM/yyyy HH:mm:ss')}"
            println "Passed Date till   : ${dateTill.format('dd/MM/yyyy HH:mm:ss')}"
            println "Detected Interval  : ${dateTill - dateFrom + 1}"
            println "Transactions count : ${result.size()}"
        }
        
        return result
    }
}
