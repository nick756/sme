package com.sme.entities

import org.springframework.web.context.request.RequestContextHolder

class PaymentMode {
    
    Integer code
    String value_EN
    String value_MS

    static constraints = {
        code    unique: true
    }
    
    public String toString() {
        def session = RequestContextHolder.currentRequestAttributes().getSession()
        def out = ''
        def locl = 'en'
        
        if(session) {
            locl = session.locale.toString()
        } 
        
        if(locl == 'en') {
            out = value_EN
        }
        else {
            out = value_MS
        }
        
        return out
    }
}
