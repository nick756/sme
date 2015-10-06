package com.sme.entities

import java.text.DecimalFormat

/**
 *  Assumptions as on 28/09/2015:
 *  
 *  1   An SME has only a single Bank Account, otherwise those must be 
 *      aggregated
 */
class BusinessTransaction {
    
    GenericOperation    operationType
    Date                transactionDate
    double              transactionAmount
    String              transactionRemarks
    String              operator            //  Do not use reference to User
    Integer             cash                //  Flag of cash or bank account
    
    //  Reference (ID) to auto-created peer entry, cash or bank
    Integer             peer
    
    static belongsTo = [
        company:    Business, 
        statement:  CashFlowStatement
        
    ]
    
    static constraints = {
        transactionRemarks      nullable: false, blank: false
        operator                blank: false
        statement               nullable: true
        cash                    nullable: true, blank: true  //  For compatibility
        peer                    nullable: true
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
