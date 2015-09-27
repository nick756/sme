package com.sme.actions

import com.sme.entities.*
import java.text.DecimalFormat
import grails.util.Environment
import grails.transaction.Transactional

@Transactional (readOnly = true)
class SmehomeController {
    def businessTransactionService
    def messageSource
    
    def index() { 
        def transactions = []
        
        if(!session.user) {
            redirect (controller: 'login', action: 'index')
        }
        
        def user = session?.user
        def company = session?.company
        
        if(!user.isAttached()) {
            user.attach()
        }
        
        if(!company.isAttached()) {
            company.attach()
        }
        
        params.offset = params.offset ?: 0
        params.max = params.max ?: 10        

        transactions = BusinessTransaction.createCriteria().list(params) {
            eq('company', company) 
            
            and {
                order('transactionDate', 'desc')
                order('id', 'desc')
            }
        }
        
        def counter = BusinessTransaction.findAllByCompany(company).size()
        
        if(Environment.current == Environment.DEVELOPMENT) {
            println ''
            println "-- ${session.user.login}: Method smehome/index"
            println "Counter : ${counter}"
        }
        
        [
            transactions:   transactions,
            counter:        counter,
            params:         params
        ]
    }
    
    def create() {
        respond new BusinessTransaction(params)
    }
    
    //  Show Page for updating Company Profile by SME Operator
    
    def editprofile(Business businessInstance) {
        if(!session?.user) {
            redirect controller: 'login', action: 'index'
        }
       
        respond businessInstance
    }
    
    //  Actual update of Business instance
    
    def updateprofile(Business businessInstance) {
        if(businessInstance == null) {
            println "*** Empty input in updateprifle"
            println params
            //redirect action: 'editprofile', params: [id: params?.id] 
            return
        }
        
        if(businessInstance.hasErrors()) {
            
            if(Environment.current == Environment.DEVELOPMENT) {
                println "Error in updateprofile"
            
                businessInstance.errors.allErrors.each {
                    println "${g.message([error: it])}"
                }
            }
            
            //render view: 'editprofile', model: [businessInstance: businessInstance]
            respond businessInstance.errors, view: 'editprofile'
            return
        }
        
        businessInstance.save flush: true
        
        if(Environment.current == Environment.DEVELOPMENT) {
            println "Instance updated"
        }        
        
        redirect action: 'profile'
    }
    
    @Transactional
    def save(BusinessTransaction businessTransactionInstance) {

        if (businessTransactionInstance.hasErrors()) {
            if(Environment.current == Environment.DEVELOPMENT) {
                println 'Error during Error verification:'
                println businessTransactionInstance.errors
            }
            
            respond businessTransactionInstance.errors, view:'create'
            return
        }

        businessTransactionInstance.save flush:true
        
        if(Environment.current == Environment.DEVELOPMENT) {
            println "Transaction added: ${businessTransactionInstance}"
        }        
        
        redirect action: 'index'
    }
    
    /**
     *  Displaying basic Company Profile
     */
    def profile() {
        if(!session.user) {
            redirect (controller: 'login', action: 'index')
        }
        
        params.offset = params.offset ?: 0
        params.max = params.max ?: 10        
        
        def operations = []
        def companyID = session?.user.company.id
        def business = Business.get(companyID)
        def counter = 0
        
        operations = business?.profile?.operations.asList()
        counter = business?.profile?.operations?.size()
        
        def indexTo     = new Integer(params?.offset) + new Integer(params?.max) - 1
        def indexFrom   = new Integer(params?.offset)
        
        if(indexTo >= counter) indexTo = counter - 1
        
        if(counter > 0) {
            operations = operations[indexFrom..indexTo]
        }        
        
        [
            businessInstance:   business,
            operations:         operations,
            params:             params,
            counter:            counter
        ]
    }
}
