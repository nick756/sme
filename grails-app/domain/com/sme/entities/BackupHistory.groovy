package com.sme.entities

class BackupHistory {
    
    Date    backupDate
    String  operator
    Long    size
    
    static constraints = {
    }
    
    static mapping = {
        sort backupDate: 'desc'
    }
}
