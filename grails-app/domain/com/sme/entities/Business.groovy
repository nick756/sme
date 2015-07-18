package com.sme.entities

class Business {
    Date dateCreated
    Date lastUpdated
    
    String name
    String regNumber
    Date incorpDate
    String address
    String city
    
    State           state
    Industry        industry
    GenericProfile  profile
    
    static hasMany = [
        users: User,
        businessTransactions: BusinessTransaction
    ]
    
    static constraints = {
        name        (blank: false)
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
    }
    
    static mapping = {
        autoTimestamp false
    }
    
    public String toString() {
        regNumber + " " + name
    }
}
