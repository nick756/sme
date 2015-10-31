package com.sme.entities

class Business implements Serializable {
    Date dateCreated
    Date lastUpdated
    
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
    
    static hasMany = [
        users: User,
        businessTransactions: BusinessTransaction,
        statements: CashFlowStatement
        
    ]
    
    static constraints = {
        name        (blank: false)
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
        registrationDate        nullable: true, blank: true
    }
    
    static mapping = {
        autoTimestamp true
    }
    
    public String toString() {
        regNumber + " " + name
    }
}
