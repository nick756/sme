package com.sme.entities

/**
 *  PNL Subgroup is only applied to Cost of Sales main Group, and only
 *  for Manufacturing Companies (Profile Code = 2)
 **/
class PNLSubGroup {
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
