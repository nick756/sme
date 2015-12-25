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
     *  triming. Default GSP page 'creditreport' is to be used for displaying (in
     *  new window).
     *  
     *  Passed Parameters (as on 14/12/2015):
     *  -   businessID
     **/
    def creditreport() {

        /*def businessID      = ''
        def filterAccount   = ''
        def filterCity      = ''
        def result = []*/
        
        def transactions1 = []
        def transactions2 = []
        
        if(!session.user) {
            redirect (controller: 'login', action: 'index')
            return
        }
        
        def user = session?.user
        def companyID = session?.company?.id
        def company
        
        def filterDateFrom = params?.filterDateFrom ?: null
        def filterDateTill = params?.filterDateTill ?: null
        
        def filterOperation = params?.filterOperationType
        
        def filterOperationType = null
        
        if(params.filterOperation != null && params.filterOperation?.id != "") {
            //println "...passed: ${params.filterOperationType?.id}"
            filterOperationType = GenericOperation.get(new Integer(params?.filterOperation?.id))
        }
        
        if(!user.isAttached()) {
            user.attach()
        }
        
        company = Business.get(companyID)
        
        params.offset = params.offset ?: 0
        params.max = params.max ?: 10       
        
        transactions1 = BusinessTransaction.createCriteria().list(params) {
            eq('company', company) 
            
            operationType {
                or {
                    and {
                        eq('code', 2000)
                    }
                    
                    eq('code', 515)
                }
            }
            
            if(filterDateFrom != null) {
                ge('transactionDate', filterDateFrom)
            }
            
            if(filterDateTill) {
                le('transactionDate', filterDateTill)
            }
            
            if(filterOperationType) {
                eq('operationType', filterOperationType)
            }
            
            /*and {
                order('transactionDate', 'desc')
                order('id', 'desc')
            }*/
            order('operationType', 'desc')
            order('transactionDate')
        }
        
        transactions2 = BusinessTransaction.createCriteria().list(params) {
            eq('company', company) 
            
            operationType {
                or {
                    and {
                        eq('code', 2005)
                    }
                    
                    eq('code', 510)
                }
            }
            
            if(filterDateFrom != null) {
                ge('transactionDate', filterDateFrom)
            }
            
            if(filterDateTill) {
                le('transactionDate', filterDateTill)
            }
            
            if(filterOperationType) {
                eq('operationType', filterOperationType)
            }
            
            /*and {
                order('transactionDate', 'desc')
                order('id', 'desc')
            }*/
            order('operationType', 'desc')
            order('transactionDate')
        }
        
        [
            transactions1:   transactions1,
            transactions2:   transactions2,
            companyName:    company.name
            /*counter:        counter,
            params:         params,
            errMessage:     params.errMessage,
            successMsg:     params.successMsg,
            msgDetails:     params.msgDetails,
            filterDateFrom: filterDateFrom,
            filterDateTill: filterDateTill,
            
            filterOperation: filterOperationType*/
        ]
        
    }
    
    /**
     *  Generation of PNL Statement by instance of PNLStatement
     */
    def pnlstatement() {
        def statement
        def lines
        def sections
        
        if(params?.id) {
            statement = PNLStatement.get(new Integer(params?.id))
        }
        
        sections = statement.sections.asList().sort{it.group.code}
        
        sections.each {sect ->
            lines       = sect.lines.asList().sort{it.type.code}
            sect.lines  = lines
        }
        
        statement.sections = sections
        
        lines = null
        sections = null
        
        [
            statement: statement
        ]
    }
}
