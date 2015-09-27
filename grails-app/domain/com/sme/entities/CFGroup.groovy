package com.sme.entities

class CFGroup {
    Integer code
    String name
    
    static constraints = {
        code    nullable: false
        name    nullable: false, blank: false
    }
    
    static mapping = {
        sort 'code'
    }
    
    public String toString() {
        "${code} ${name}"
    }
}
