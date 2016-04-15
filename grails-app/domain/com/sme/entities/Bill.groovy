package com.sme.entities

class Bill {
    
    Integer internalID
    
    Date dateCreated
    Date dueDate
    Date periodFrom
    Date periodTill
    Date gracePeriodTill
    Date paymentDate
    
    String invoiceNumber
    BillingType billingType
    
    BigDecimal amount
    BigDecimal amountPaid
    PaymentMode paymentMode
    String paymentReference
    String remarks
    
    boolean paid
    boolean outstanding
    boolean writtenOff
    
    static belongsTo = [
        company: Business
    ]
    
    static constraints = {
        paymentDate         nullable: true
        invoiceNumber       nullable: true
        paymentMode         nullable: true
        paymentReference    nullable: true
        remarks             nullable: true, maxSize: 1024
        paid                nullable: true
        outstanding         nullable: true
        writtenOff          nullable: true
    }
    
    static mapping = {
        sort dueDate: 'desc'
    }
}
