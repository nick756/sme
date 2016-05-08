package com.sme.entities

class BackupHistory {
    
    Date    backupDate
    String  operator
    Long    size
    Integer numberUsers
    Integer numberCompanies
    Integer numberTransactions
    Integer numberBills
    
    static constraints = {
    }
    
    static mapping = {
        sort backupDate: 'desc'
    }
}
