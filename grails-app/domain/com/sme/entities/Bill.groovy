package com.sme.entities

class Bill {
    
    Date dateCreated
    Date dueDate
    Date periodFrom
    Date periodTill
    Date gracePeriodTill
    Date paymentDate
    
    BigDecimal amount
    BigDecimal amountPaid
    
    static belongsTo = [
        company: Business
    ]
    
    static constraints = {
    }
}
