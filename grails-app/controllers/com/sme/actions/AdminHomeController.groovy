package com.sme.actions

import com.sme.entities.*
import java.text.DecimalFormat

class AdminHomeController {

    def index(Integer max) {
        
        if(!session?.user) {
            redirect (controller: 'login')
        }
        
        params.offset = params.offset ?: 0
        params.max = Math.min(max ?: 5, 100)
        
        [businesses: Business.list(params), businessInstanceCount: Business.count()]
    }
    
    def show = {
        params.offset = params.offset ?: 0
        params.max = params.max ?: 0
        
        [businessInstance: Business.get(new Integer(params?.id)), params: [max: params?.max, offset: params?.offset]]
    }
    
    def edit = {
        if(!session?.user) {
            redirect (controller: 'login')
        }
        
        [businessInstance: Business.get(params?.id)]
    }
    
    def listtransactions() {
        if(!session?.user) {
            redirect (controller: 'login')
        }
        
        params.offset = params.offset ?: 0
        params.max = params.max ?: 0
        
        [
            businessInstance: Business.get(new Integer(params?.id)),
            
            transactionsList: BusinessTransaction.findAllByCompany(
                Business.get(params?.id), 
                [max: 10, sort: "transactionDate", order: "asc", offset: params?.offset]
            ),
            
            params: [max: params?.max, offset: params?.offset]
        ]
    }
}
