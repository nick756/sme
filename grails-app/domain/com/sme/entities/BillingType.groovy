package com.sme.entities

import org.springframework.web.context.request.RequestContextHolder

class BillingType {
    
    Integer code
    String value_EN
    String value_MS
    
    Integer billingPeriod   //  In Months, either 1 or 12
    BigDecimal defaultAmount;
    Integer gracePeriod
    Integer trialPeriod

    static constraints = {
        code    unique: true
    }
    
    static mapping = {
        sort 'code'
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
