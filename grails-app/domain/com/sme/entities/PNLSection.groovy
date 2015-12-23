package com.sme.entities

import java.text.DecimalFormat

class PNLSection {
    
    PNLGroup    group
    PNLSubGroup subgroup
    BigDecimal  amountTotal
    
    static belongsTo = [statement: PNLStatement]
    
    //  If Section has not null subgroup, all Transactions are related to
    //  respective PNLSubGroup
    
    static hasMany = [
        lines: PNLSectionLine
    ]
    
    static constraints = {
        group       nullable: false
        subgroup    nullable: true
        lines       nullable: true
    }
    
    static mapping = {
        sort 'group.code'
    }
    
    public String toString() {
        if(!subgroup) {
            "${group.name}: ${new DecimalFormat('#,##0.00').format(amountTotal)}"
        }
        else {
            "${group.name}/${subgroup?.name}: ${new DecimalFormat('#,##0.00').format(amountTotal)}"
        }
    }
}
