package com.sme.util

/**
 *
 * @author Nikolay Krasnikov
 * 
 * In-memory version of Income Statement, used for statistics and charts. PNL 
 * Groups are hard-wired in this Class, synchronized with conventional list of
 * PNLGroup.
 * 
 * The Class is used for consolidated report for multiple companies, as well as
 * multi-period report for a single company (multiple instances).
 */
class IncomeSummary {
    String company
    Integer companyID
    
    BigDecimal amountSales
    BigDecimal amountCost
    BigDecimal amountProfitGross
    BigDecimal amountIncome
    BigDecimal amountExpense
    BigDecimal amountTax
    BigDecimal amountProfitBT
    BigDecimal amountProfitAT
    
    Integer year
    Integer quarter
    Integer month
    Date dateFrom
    Date dateTill
    String prefix
    String caption
    
    //  Captions are only used for individual Statements (single Company)
    
    def createCaption() {
        if(this.quarter > 0) {
            this.caption = "\"" + this.prefix  + String.format('%1$1s', this.quarter) + "\'" + String.format('%1$2s', this.year - 2000) + "\""
        }
        else {
            this.caption = "\"" + String.format('%1$2s', this.year - 2000) + "/" + String.format('%1$2s', this.month) + "\""
        }
    }
    
    def validate() {
        if(year <= 0) {
            return false
        }
        else if(this.quarter > 0 && this.month > 0) {
            return false
        }
        
        //  If quarter = 0 && month = 0, then entire given year is considered
        
        return true
    }
    
    def assignPeriod() {
        def monthStart
        def monthStop
        Date lastMonth
        
        if(this.quarter > 0) {
            switch(this.quarter) {
                case 1:
                    monthStart = 1
                    monthStop = 3
                    break
                    
                case 2:
                    monthStart = 4
                    monthStop = 6
                    break
                    
                case 3:
                    monthStart = 7
                    monthStop = 9
                    break
                    
                case 4:
                    monthStart = 10
                    monthStop = 12
                    break
            }
        }
        else if(month > 0) {
            monthStart = this.month
            monthStop = this.month
        }
        else {  //  Period of entire given year
            monthStart = 1
            monthStop = 12
        }
        
        this.dateFrom = new Date().copyWith(year: this.year, month: monthStart - 1, dayOfMonth: 1)
        lastMonth = new Date().copyWith(year: this.year, month: monthStop - 1, dayOfMonth: 1)
        this.dateTill = new Date().copyWith(year: this.year, month: monthStop - 1, dayOfMonth: lastMonth.toCalendar().getActualMaximum(Calendar.DAY_OF_MONTH))
        
        this.dateFrom.clearTime()
        this.dateTill.clearTime()
    }
}

