package com.sme.entities

class Industry {
    Date dateCreated
    Date lastUpdated
    Integer code
    String name
    
    static constraints = {
        dateCreated nullable: true
        lastUpdated nullable: true
        code nullable: false
        name nullable: false
    }
    
    public String toString() {
        String.format('%1$2s', code).replace(' ', '0') + ": " + name
    }
}
