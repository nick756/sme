package com.sme.actions

import com.sme.entities.*

/**
 *  Controller for managing Billing Activities for Admin User Group.
 */
class BillingController {

    def billingService
    
    /**
     *  Listing down all Pending Invoices from all concerned Customers, with
     *  respective filtering options. Paging Parameters are being maintained in 
     *  session (session.pendingBillsOffset & params.resetPendingOffset).
     *  
     *  View provides with adequate Tabbed Navigation.
     */
    def index() { 
        if(!session?.user) {
            redirect (controller: 'login')
            return
        }
        
        params.offset = params.offset ?: 0
        params.max = params.max ?: 10
        
        if(params.resetPendingOffset) {
            session.pendingBillsOffset = null
        }
        
        if(session.pendingBillsOffset) {
            params.offset = session.pendingBillsOffset
        }
        else {
            session.pendingBillsOffset = params.offset
        }
        
        //  Search fields:
        //  -   searchDueDateFrom       (Bill)
        //  -   searchDueDateTill       (Bill)
        //  -   searchRegistrationFrom  (Business)
        
        def searchPendingBillsData = [:]
        
        if(session?.searchPendingBillsData) {
            searchPendingBillsData = session.searchPendingBillsData
        }
       
        if(params.searchDueDateFrom) {
            def searchDueDateFrom = params?.searchDueDateFrom?.clearTime()
            searchPendingBillsData['searchDueDateFrom'] = searchDueDateFrom
        }
        
        if(params.searchDueDateTill) {
            def searchDueDateTill = params?.searchDueDateTill?.clearTime()
            searchPendingBillsData['searchDueDateTill'] = searchDueDateTill            
        }
        
        if(params.searchRegistrationFrom) {
            def searchRegistrationFrom = params?.searchRegistrationFrom?.clearTime()
            searchPendingBillsData['searchRegistrationFrom'] = searchRegistrationFrom            
        }
        
        if(params.searchPendingCompany) {
            searchPendingBillsData['searchPendingCompany'] = params.searchPendingCompany
        }
        
        //  Preserving Search Criiteria in session
        session.searchPendingBillsData = searchPendingBillsData
        
        def instancesList = Bill.createCriteria().list(params) {
            
            eq('paid', false)
            eq('outstanding', true)
            eq('writtenOff', false)
            
            if(searchPendingBillsData['searchDueDateFrom']) {
                ge('dueDate', searchPendingBillsData['searchDueDateFrom'])
            }
            if(searchPendingBillsData['searchDueDateTill']) {
                le('dueDate', searchPendingBillsData['searchDueDateTill'])
            }
            if(searchPendingBillsData['searchRegistrationFrom']) {
                company {
                    ge('registrationDate', searchPendingBillsData['searchRegistrationFrom'])
                }
            }
            
            if(searchPendingBillsData['searchPendingCompany']) {
                company {
                    ilike('name', "%${searchPendingBillsData['searchPendingCompany']}%")
                }
            }
            
            order 'dueDate', 'desc'
        }
        
        [
            instancesList:  instancesList,
            counter:        instancesList.totalCount,
            searchData:     searchPendingBillsData,
            params:         params,
            message:        params?.message
        ]
    }
    
    /**
     *  Listing all Paid Invoices. Navigation aspects are the same as for index
     *  action:
     *  <ul>
     *  <li>session.paidBillsOffset</li>
     *  <li>params.resetPaidOffset</li>
     *  </ul> 
     *  Respective View is also Tabbed, similar to 'index'.
     *  <br/>
     *  Search Parameters are maintained in searchPaidBillsData Map stored in
     *  session, fields being the same as for 'index' action.
     */
    def paidbills() {
        if(!session?.user) {
            redirect (controller: 'login')
            return
        } 
        
        //  Handing paging matters
        
        params.offset = params.offset ?: 0
        params.max = params.max ?: 10
        
        if(params.resetPaidOffset) {
            session.paidBillsOffset = null
        }
        
        if(session.paidBillsOffset) {
            params.offset = session.paidBillsOffset
        }
        else {
            session.paidBillsOffset = params.offset
        }    
        
        //  Handling Serach Criteria
        
        def searchPaidBillsData = [:]
        
        if(session?.searchPaidBillsData) {
            searchPaidBillsData = session.searchPaidBillsData
        }
       
        if(params.searchDueDateFrom) {
            def searchDueDateFrom = params?.searchDueDateFrom?.clearTime()
            searchPaidBillsData['searchDueDateFrom'] = searchDueDateFrom
        }
        
        if(params.searchDueDateTill) {
            def searchDueDateTill = params?.searchDueDateTill?.clearTime()
            searchPaidBillsData['searchDueDateTill'] = searchDueDateTill            
        }
        
        if(params.searchRegistrationFrom) {
            def searchRegistrationFrom = params?.searchRegistrationFrom?.clearTime()
            searchPaidBillsData['searchRegistrationFrom'] = searchRegistrationFrom            
        }
        
        if(params.searchPaidCompany) {
            searchPaidBillsData['searchPaidCompany'] = params.searchPaidCompany
        }
        
        //  Preserving Search Criiteria in session
        session.searchPaidBillsData = searchPaidBillsData 
        
        def instancesList = Bill.createCriteria().list(params) {
            
            eq('paid', true)
            eq('outstanding', false)
            eq('writtenOff', false)
            
            if(searchPaidBillsData['searchDueDateFrom']) {
                ge('dueDate', searchPaidBillsData['searchDueDateFrom'])
            }
            if(searchPaidBillsData['searchDueDateTill']) {
                le('dueDate', searchPaidBillsData['searchDueDateTill'])
            }
            if(searchPaidBillsData['searchRegistrationFrom']) {
                company {
                    ge('registrationDate', searchPaidBillsData['searchRegistrationFrom'])
                }
            }
            
            if(searchPaidBillsData['searchPaidCompany']) {
                company {
                    ilike('name', "%${searchPaidBillsData['searchPaidCompany']}%")
                }
            }
            
            order 'dueDate', 'desc'
        }        

        [
            instancesList:  instancesList,
            counter:        instancesList.totalCount,
            searchData:     searchPaidBillsData,
            params:         params
        ]
    }
    
    def resetsearch() {
        def target = params?.target
        params.offset = 0
        
        switch(target) {
            case 'index':
                session.searchPendingBillsData = null
                session.resetPendingOffset = true
                session.pendingBillsOffset = null
                break
            case 'paidbills':
                session.searchPaidBillsData = null
                session.resetPaidOffset = true
                session.paidBillsOffset = null
                break
        }
        
        redirect action: "${target}", params: params
    }
    
    /**
     *  Conditional creation of new Bills for all registered Companies. 
     *  Evaluation is performed against Current Date, all paging and Search
     *  Parameters are reset - method can only be called from 'index'.
     */
    def evaluate() {
        if(!session?.user) {
            redirect (controller: 'login')
            return
        } 
        
        params.offset = 0
        params.max = 10
        
        def currentDate = new Date().clearTime()
        
        session.searchPendingBillsData  = null
        session.pendingBillsOffset      = null
        
        def message = billingService.evaluateCurrentBilling(currentDate, session?.user?.name)
        
        redirect view: 'index', params: ['resetPendingOffset': true, 'message': message]
    }
    
    /**
     *  Displaying Page with selected Bill for Admins confirmation.
     */
    def confirm(Bill bill) {
        if(!session?.user) {
            redirect (controller: 'login')
            return
        } 
        
        def errorMessage = params?.errorMessage ?: null
        
        [
            objectInstance: bill,
            params:         params,
            errorMessage:   errorMessage
        ]        
    }
    
    /**
     *  Updating instance of Bill after Admin's confirmation of payment receipt,
     *  subject to necessary verification
     */
    def update(Bill bill) {
        def errorMessage = null
        
        if(!bill.confirmationDate) {
            errorMessage = "Please fill up the Date when Payment receipt was confirmed"
        }
        else if(bill?.confirmationDate < bill?.dueDate || bill?.confirmationDate < bill?.paymentDate) {
            errorMessage = "Wrong Confirmation Date detected, please reconsider"
        }
        
        if(errorMessage) {
            render view: 'confirm', model: [
                objectInstance: bill,
                errorMessage:   errorMessage,
                params:         params
            ]
            
            return            
        }
        
        bill.confirmedBy = session?.user?.name
        bill.paid = true
        bill.outstanding = false
        bill.confirmed = true
        
        if(!bill.save(flush: true)) {
            println ''
            println "Exception while updating Invoice:"
            bill.errors.allErrors.each{
                println it
            }            
        }
        else {
            println "+++ Invoice ${bill.invoiceNumber} has been update: PAID = ${bill.paid}"
        }
        
        redirect action: 'paidbills', params: [params: params]        
    }
}
