package com.sme.entities

import java.text.DecimalFormat

class PNLStatement {
    
    Date dateCreated
    Date lastUpdated
    
    //  Period the PNL Statement is calculated for
    Integer year
    Integer month
    Date    dateFrom
    Date    dateTill
    boolean fullYear    
    
    BigDecimal grossProfit
    BigDecimal otherIncome
    BigDecimal expenses
    BigDecimal taxation
    BigDecimal netProfitBT  //  Before Tax
    BigDecimal netProfitAT  //  After Tax
    
    static belongsTo    = [company: Business]
    static hasMany      = [sections: PNLSection]

    static constraints = {
        dateCreated nullable: true
        lastUpdated nullable: true
    }
    
    static mapping = {
        sort 'dateFrom'
    }
    
    public String toString() {
        "${dateFrom.format('dd/MM/yyyy')}-${dateTill.format('dd/MM/yyyy')}: ${new DecimalFormat('#,##0.00').format(netProfitAT)}"
    }
}
