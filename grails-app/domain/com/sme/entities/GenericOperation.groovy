package com.sme.entities

import org.springframework.web.context.request.RequestContextHolder

/**
Generic Operations (Operation Types) assignable 
to a Generic Profile
 */
class GenericOperation {
    Integer code
    String name
    String name_EN
    
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
        name_EN     nullable: true
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
        def session = RequestContextHolder.currentRequestAttributes().getSession()
        def out = ''
        
        if(this.code < 1000) {
            if(session.locale.toString() == 'ms') {
                if(this.inbound) {
                    out = "MASUK: ${name}"
                }
                else {
                    out = "KELUAR: ${name}"
                }
            }
            else {
                if(this.inbound) {
                    out = "IN: ${name_EN}"
                }
                else {
                    out = "OUT: ${name_EN}"
                }                
            }
        }
        else {
            if(session.locale.toString() == 'ms') {
                out = name
            }
            else {
                out = name_EN
            }
        }
        
        return out       
    }
}
