package com.sme.entities

class Business implements Serializable {
    Date dateCreated
    Date lastUpdated
    Integer internalID
    
    String name
    String accountNo
    String regNumber
    Date incorpDate
    Date registrationDate
    String address
    String city
    
    State           state
    Industry        industry
    GenericProfile  profile
    LendingAgency   bank
    
    static hasMany = [
        users:                  User,
        businessTransactions:   BusinessTransaction,
        statements:             CashFlowStatement,
        pnlStatements:          PNLStatement
    ]
    
    static constraints = {
        name        (blank: false)
        internalID  nullable: true, blank: true
        accountNo   nullable: true, blank: true
        regNumber   (blank: false)
        industry    nullable: true
        incorpDate  (nullable: true)
        address     (blank: true, nullable: true, maxSize: 1024)
        city        (nullable: true)
        state       (nullable: true)
        dateCreated (nullable: true)
        lastUpdated (nullable: true)
        profile     nullable: true
        users       nullable: true
        
        businessTransactions    nullable: true
        statements              nullable: true
        pnlStatements           nullable: true    
        registrationDate        nullable: true, blank: true
        bank                    nullable: true
    }
    
    static mapping = {
        autoTimestamp true
        sort 'name'
    }
    
    public String toString() {
        "${name}"
    }
}
