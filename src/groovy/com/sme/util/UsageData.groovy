package com.sme.util

/**
 *
 * @author Nikolay Krasnikov
 */
class UsageData {
    Integer year
    Integer month
    Date dateFrom
    Date dateTill
    Integer value
    String caption
    
    void createCaption() {
        this.caption = "\"" + String.format('%1$2s', this.year - 2000) + "/" + String.format('%1$2s', this.month).replace(' ', '0') + "\""
    }
    
    String toString() {
        "${year} ${month} ${caption}: ${value}"
    }
}

