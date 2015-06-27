package com.sme.entities

/**
    Only a single Profile is assignable to Business instance
*/
class BusinessProfile {
    Date dateCreated
    Date lastUpdated
    Integer code
    String name
    
    static constraints = {
        dateCreated nullable: true
        lastUpdated nullable: true
        code nullable: false; unique: true
        name nullable: false, blank: false
    }
    
    public String toString() {
        String.format('%1$2s', code).replace(' ', '0') + ": " + name
    }
}
