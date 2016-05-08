package com.sme.entities

class Bill {
    
    Integer internalID
    
    Date dateCreated
    Date dueDate
    Date periodFrom
    Date periodTill
    Date gracePeriodTill
    Date paymentDate
    Date confirmationDate
    Date updateDate    
    
    String invoiceNumber
    BillingType billingType
    
    BigDecimal amount
    BigDecimal amountPaid
    PaymentMode paymentMode
    String paymentReference
    String remarks
    String confirmationRemarks
    
    //  Closed = paid && confirmed
    boolean paid
    boolean outstanding
    boolean writtenOff
    boolean confirmed       //  Confirmed by Admins
    
    String createdBy
    String confirmedBy
    String updatedBy
    
    static belongsTo = [
        company: Business
    ]
    
    static constraints = {
        internalID          nullable: true
        dateCreated         nullable: true
        paymentDate         nullable: true
        invoiceNumber       nullable: true
        paymentMode         nullable: true
        paymentReference    nullable: true
        remarks             nullable: true, maxSize: 1024
        paid                nullable: true
        outstanding         nullable: true
        writtenOff          nullable: true
        amountPaid          nullable: true
        gracePeriodTill     nullable: true
        confirmed           nullable: true
        createdBy           nullable: true
        confirmedBy         nullable: true
        updatedBy           nullable: true
        confirmationDate    nullable: true
        updateDate          nullable: true
        confirmationRemarks nullable: true
    }
    
    static mapping = {
        sort dueDate: 'desc'
    }
}
