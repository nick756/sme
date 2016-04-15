package com.sme.actions

import com.sme.entities.*

/**
 *  Controller for managing Billing aspects on SME level, implies automatic
 *  creation of Invoice (Bills) upon login nto the System.
 */
class SmebillingController {
    
    def billingService

    def index() { 
        if(!session?.user) {
            redirect (controller: 'login')
            return
        } 
        
        params.offset = params.offset ?: 0
        params.max = params.max ?: 12
        
        def companyID = session?.company?.id
        Business company = Business.get(companyID)
        
        def instancesList = Bill.createCriteria().list(params) {
            
        }
    }
}
