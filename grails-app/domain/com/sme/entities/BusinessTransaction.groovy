package com.sme.entities

import java.text.DecimalFormat

class BusinessTransaction {
    
    Date dateCreated
    Date lastUpdated
    
    GenericOperation    operationType
    Date                transactionDate
    double              transactionAmount
    String              transactionRemarks
    String              operator            //  Do not use reference to User
    
    static belongsTo = [
        company:    Business, 
        statement:  CashFlowStatement
        
    ]

    static constraints = {
        transactionRemarks      nullable: false, blank: false
        operator                blank: false
        dateCreated             nullable: true
        lastUpdated             nullable: true
        statement               nullable: true
    }
    
    public String toString() {
        if(transactionDate) {
            return "${transactionDate.format('dd/MM/yyyy')} ${transactionRemarks}"
        }
        else {
            return "${transactionRemarks}"
        }
    }
}
