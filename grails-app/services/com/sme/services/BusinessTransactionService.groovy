package com.sme.services

import grails.transaction.Transactional
import java.text.DecimalFormat
import com.sme.entities.*
import groovy.util.logging.Log4j

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
     *  is controlled for Mobile Interface
     */
    def getTransactions(Business company, Date from, Date till) {
        BusinessTransaction.createCriteria().list() {
            eq('company', company)
            between('transactionDate', from, till)
            order('transactionDate', 'asc')
        }
    }
}
