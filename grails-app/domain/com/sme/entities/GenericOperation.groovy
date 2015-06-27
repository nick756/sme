package com.sme.entities

/**
    Generic Operations (Operation Types) assignable 
    to a Generic Profile
*/
class GenericOperation {
    Date dateCreated
    Date lastUpdated
    Integer code
    String name
    boolean inbound
    boolean outbound
    
    AccountType accountType
    
    static hasMany = [profiles: ProfileLink]
    
    static constraints = {
        dateCreated nullable: true
        lastUpdated nullable: true
        
        code        nullable: false, unique: true
        name        nullable: false, blank: false
        accountType nullable: false
        profiles    nullable: true

        //  Values of inbound and outbound cannot coincide
        inbound     nullable: false
        outbound    nullable: false, validator: {value, obj ->
                        return !(value == obj.inbound)
                    }
    }
    
    static mapping = {
        sort 'code'
    }
    
    public String toString() {
        def out
        
        if(this.inbound) {
            out = "IN: "
        }
        else {
            out = "OUT: "
        }
        
        return out + name
    }
}
