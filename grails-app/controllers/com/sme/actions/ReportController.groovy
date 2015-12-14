package com.sme.actions

import com.sme.entities.*

class ReportController {

    def index() { }
    
    /**
     *  List of Companies in printable view: set filtering options are 
     *  considered
     */
    def companieslist() {
        def filterName      = ''
        def filterAccount   = ''
        def filterCity      = ''
        def result = []
        
        if(params?.filterName) {
            filterName = params.filterName
        }
        
        if(params?.filterAccount) {
            filterAccount = params.filterAccount
        }
        
        if(params?.filterCity) {
            filterCity = params.filterCity
        }
        
        result = Business.createCriteria().list() {
            if(filterName != '') {
                ilike('name', "%${filterName}%")
            }
            
            if(filterAccount != '') {
                ilike('accountNo', "${filterAccount}%")
            }
            
            if(filterCity != '') {
                ilike('city', "%${filterCity}%")
            }
            
            order('name')
        }
        
        [
            businesslist: result
        ]
    }
    
    /**
     *  Structured Report on Credit Operations for a given Business. Called only
     *  from SME Group. Grouping is done by prefixes entered into 'description'
     *  field of BusinessTransactions: identified by token '::' with following
     *  triming. Defualt GSP page 'creditreport' is to be used for displaying (in
     *  new window).
     *  
     *  Passed Parameters (as on 14/12/2015):
     *  -   businessID
     **/
    def creditreport() {
        
    }
}
