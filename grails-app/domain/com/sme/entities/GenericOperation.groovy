package com.sme.entities

/**
Generic Operations (Operation Types) assignable 
to a Generic Profile
 */
class GenericOperation {
    Integer code
    String name
    
    boolean inbound
    boolean outbound
    Integer actual
    
    //  Operation Types for double entry creation
    GenericOperation mirrorCash
    GenericOperation mirrorBank
    
    AccountType accountType
    CFGroup     group
    
    static hasMany = [profiles: ProfileLink]
    
    static constraints = {
        code        nullable: false, unique: true
        name        nullable: false, blank: false
        accountType nullable: false
        profiles    nullable: true
        group       nullable: true  //  For compatibility
        mirrorCash  nullable: true
        mirrorBank  nullable: true
        actual      nullable: true, blank: true

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
        def out = ''
        
        if(this.code < 1000) {
            if(this.inbound) {
                out = "MASUK: "
            }
            else {
                out = "KELUAR: "
            }
        }
        
        return out + name
    }
}
