package com.sme.entities

/**
 *  Applied directly to Trading Businesses, while for Manufacturing Companies
 *  must be combined (for Cost of Sales) with PNLSubGroup instances.
 *  
 *  The Class is used for attributing GenericOperation instances.
 **/
class PNLGroup {
    Integer code
    String name
    
    static constraints = {
    }
    
    static mapping = {
        sort 'code'
    }
    
    public String toString() {
        "${String.format('%1$2s', code).replace(' ', '0')}: ${name}"
    }
}
