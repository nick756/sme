package com.sme.entities

class BackupHistory {
    
    Date    backupDate
    String  operator
    Long    size
    Integer numberUsers
    Integer numberCompanies
    Integer numberTransactions
    
    static constraints = {
    }
    
    static mapping = {
        sort backupDate: 'desc'
    }
}
