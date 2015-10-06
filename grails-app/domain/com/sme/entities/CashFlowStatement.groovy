package com.sme.entities

import java.text.DecimalFormat

/**
 *  Represents monthly Cash Flow record for a Business instance
 */
class CashFlowStatement {
    
    Date dateCreated
    Date lastUpdated
    
    Integer year
    Integer month
    
    BigDecimal inflow
    BigDecimal outflow
    BigDecimal nettAmount
    BigDecimal cumulativeAmount     //  BF + CF
    BigDecimal openingBalance
    
    BigDecimal cashHand
    BigDecimal cashBank

    static belongsTo = [company: Business]
    static hasMany = [transactions: BusinessTransaction]
    
    static constraints = {
        dateCreated     nullable: true
        lastUpdated     nullable: true
        transactions    nullable: true
        cashHand        nullable: true
        cashBank        nullable: true
    }
    
    static mapping = {
        sort    'year'
        sort    'month'
    } 
    
    public String toString() {
        String.format('%1$2s', month).replace(' ', '0') + "/" +
        String.format('%1$4s', year) + " CF: " +
        (new DecimalFormat('#,##0.00')).format(nettAmount)
    }
        
}
