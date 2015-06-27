package com.sme.entities

class GenericProfile {
    Date dateCreated
    Date lastUpdated
    Integer code
    String name
    String remarks
    
    def getOperations = {
        def result = []
        
        if(operations.size() > 0) {
            return operations
        }
        else {
            return result
        }
    }
    
    static hasMany = [
        operations: ProfileLink
    ]
    
    static constraints = {
        dateCreated     nullable: true
        lastUpdated     nullable: true
        code            unique: true
        remarks         nullable: true, maxSize: 1024
        operations      nullable: true
    }
    
    static mapping = {
        operations sort: 'operation'
    }
    
    public String toString() {
        String.format('%1$2s', code).replace(' ', '0') + " " + name
    }
}
