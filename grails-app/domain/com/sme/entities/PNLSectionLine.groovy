package com.sme.entities

/**
 *  Represents total of Transactions of specified 'type', calculated over
 *  period of time given by PNLStatement instance; may be related to particular
 *  PNLSubGroup only, if specified in parent PNLSection
 **/
class PNLSectionLine {

    GenericOperation    type
    BigDecimal          amount
    
    static belongsTo = [section: PNLSection]
    
    static constraints = {
    }
}
