package com.sme.services

import grails.transaction.Transactional
import java.text.DecimalFormat
import com.sme.entities.*

@Transactional
class BusinessTransactionService {
    
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
}
