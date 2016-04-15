package com.sme.services

import grails.transaction.Transactional
import com.sme.entities.*

/**
 *  Managing all aspects of Billing, for all defined User Groups, except Bank
 *  Operators (as on 15/04/2016)
 */
@Transactional
class BillingService {

    def initiateInvoicing(Business company) {
        
    }
    
    def createNewInvoice(Business company, Date effectiveDate) {
        if(!company?.startBillingDate || company?.freeServices) {
            return false
        }
    }
    
    def serviceMethod() {

    }
}
