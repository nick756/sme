package com.sme.entities

import java.text.DecimalFormat

class PNLStatement {
    
    Date dateCreated
    Date lastUpdated
    
    Integer month
    Integer year
    
    BigDecimal openingBalance       //  Cumulative amount (result from past period)
    BigDecimal inboundAmount        //  Inflow amount
    BigDecimal outboundAmount       //  Outflow amount
    BigDecimal resultAmount         //  Nett amount (difference in - out)
    
    static belongsTo = [company: Business]
    static hasMany = [busTransactions: BusinessTransaction]

    static constraints = {
        dateCreated nullable: true
        lastUpdated nullable: true
    }
    
    static mapping = {
        sort    'year'
        sort    'month'
    }
    
    public String toString() {
        String.format('%1$2s', month).replace(' ', '0') + " " +
        String.format('%1$4s', year) + " PNL: " +
        (new DecimalFormat('#,##0.00')).format(resultAmount)
    }
}
