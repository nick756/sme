package com.sme.actions

import com.sme.entities.*

/**
 *  Controller for managing Billing aspects on SME level, implies automatic
 *  creation of Invoice (Bills) upon login nto the System.
 */
class SmebillingController {
    
    def billingService
    
    Date filterDateFrom = null
    Date filterDateTill = null

    def index() { 
        if(!session?.user) {
            redirect (controller: 'login')
            return
        } 
        
        params.offset = params.offset ?: 0
        params.max = params.max ?: 12
        
        def companyID = session?.company?.id
        Business company = Business.get(companyID)
        
        if(params?.filterDateFrom) {
            filterDateFrom = params?.filterDateFrom?.clearTime()
        }
        
        if(params?.filterDateTill) {
            filterDateTill = params?.filterDateTill?.clearTime()
        }
        
        def instancesList = Bill.createCriteria().list(params) {
            eq('company', company)
            
            if(filterDateFrom) {
                ge('dueDate', filterDateFrom)
            }
            
            if(filterDateTill) {
                le('dueDate', filterDateTill)
            }
            
            order 'dueDate', 'desc'
        }
        
        [
            instancesList:  instancesList,
            counter:        instancesList?.totalCount,
            params:             params,
            filterDateFrom:     filterDateFrom,
            filterDateTill:     filterDateTill
        ]
    }
    
    /**
     *  Updating selected Bill: only below Fields can be changed by Operator:
     *  <ol>
     *  <li>Payment Date</li>
     *  <li>Amount Paid</li>
     *  <li>Payment Mode</li>
     *  <li>Document Reference No</li>
     *  <li>Remarks</li>
     *  </ol>
     *  
     *  Action displays instance to be edited.
     */
    def edit(Bill bill) {
        //  Pagination Parameters must be passed here
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
     *  Actual update of Bill instance by SME Operator, with preliminary 
     *  verification: not associated with any View.
     */
    def update(Bill bill) {
        
        def errorMessage = null
        
        if(bill.amountPaid == null || !bill?.paymentMode || !bill?.paymentReference || !bill?.paymentDate) {
            errorMessage = "Please fill up all Fields in Invoice Payment Section"
        }
        else if(bill?.paymentDate < bill?.dueDate) {
            errorMessage = "Wrong Payment Date detected, please reconsider"
        }
        
        if(errorMessage) {
            render view: 'edit', model: [
                objectInstance: bill,
                errorMessage:   errorMessage,
                params:         params
            ]
            
            return            
        }
        
        bill.updateDate = new Date()
        bill.updatedBy = session?.user?.name
        bill.paid = true
        bill.outstanding = false
        
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
        
        redirect action: 'index', params: [params: params]
    }
}
