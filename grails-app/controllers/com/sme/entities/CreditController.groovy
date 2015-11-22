package com.sme.entities

//import com.sme.entities.*
import java.text.DecimalFormat
import grails.util.Environment
import grails.transaction.Transactional

@Transactional (readOnly = true)
class CreditController  {
    
    def businessTransactionService
    def messageSource
    
    def index() { 
        def transactions = []
        
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
        
        transactions = BusinessTransaction.createCriteria().list(params) {
            eq('company', company) 
            
            operationType {
                ge('code', 2000)
                lt('code', 3000)
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
            
            and {
                order('transactionDate', 'desc')
                order('id', 'desc')
            }
        }
        
        def counter = transactions.totalCount
        
        if(Environment.current == Environment.DEVELOPMENT) {
            println ''
            println "-- ${new Date().format("dd/MM/yyyy HH:mm:ss")} ${session.user.login}: Method credit/index"
            println "Counter : ${counter}"
            println "filterDateFrom: ${filterDateFrom}"
            println "${params}"
            println "Operation Filter: ${filterOperationType} / ${params.filterOperationType?.id}"
        }
        
        [
            transactions:   transactions,
            counter:        counter,
            params:         params,
            errMessage:     params.errMessage,
            successMsg:     params.successMsg,
            msgDetails:     params.msgDetails,
            filterDateFrom: filterDateFrom,
            filterDateTill: filterDateTill,
            
            filterOperation: filterOperationType
        ]
    }
    
    def create() {
        def opList = GenericOperation.createCriteria().list(){
            ge('code', 2000)
            lt('code', 3000)
        }
        
        respond new BusinessTransaction(params), model:[opList: opList]
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
            }
            
            respond businessTransactionInstance.errors, view:'create'
            return
        }

        businessTransactionInstance.transactionDate.clearTime()
        businessTransactionInstance.save flush:true
        
        businessTransactionService.createPeer(businessTransactionInstance)
        
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
            return
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
    
    /***************************************************************************
     *  Edit selected BusinessTransaction instance: verification that
     *  Transaction is not included into Cash Flow Summary
     **************************************************************************/
    def edit() {
        def transactionID
        def transactionInstance
        def company = session?.company
        def errMessage
        
        if(!params.id_edit) {
            redirect action: 'index', model: ['offset': params.offset]
            return
        }
        
        transactionID = new Integer(params.id_edit)
        transactionInstance = BusinessTransaction.get(transactionID)
        
        //  Verifying that Transaction is not included into any Cash Flow
        //  Summary
        
        def opList = GenericOperation.createCriteria().list(){
            ge('code', 2000)
            lt('code', 3000)
        }
        
        def yearTrans = transactionInstance.transactionDate.year - 100 + 2000
        def monthTrans = transactionInstance.transactionDate.month + 1
        
        println "Transaction Year  : ${yearTrans}"
        println "Transaction Month : ${monthTrans}"
        
        def includedList = CashFlowStatement.createCriteria().list() {
            eq('company', company)
            eq('year', yearTrans)
            eq('month', monthTrans)
        }
        
        println "${includedList} Size: ${includedList.size()}"
        
        if(includedList.size() > 0) {
            redirect(action: 'index', params: [
                    offset: params.offset, 
                    errMessage: message(code: 'businesstransaction.error.alreadyincf'),
                    'filterDateFrom': params?.filterDateFrom, 'filterDateTill': params?.filterDateTill, 'filterOperation.id': params?.filterOperation?.id
                ]
            )
        }
        
        [
            businessTransactionInstance: transactionInstance,
            params: params,
            radioLabels: [message(code: 'businesstransaction.cashmode.label'), message(code: 'businesstransaction.bankmode.label')],
            opList: opList
        ]
    }
    
    /***************************************************************************
     *  Actual update of existing Transaction handles existing peer, if any
     *  had been created for it. Existing peer must be discarded and then
     *  new one to be created
     **************************************************************************/
    def updatetransaction(BusinessTransaction businessTransactionInstance) {
        def peerID = businessTransactionInstance.peer
        def peer = null
        def companyID = session?.company.id
        def company
        def successMessage
        def msgDetails
        
        if(companyID) {
            company = Business.get(companyID)
        }
        else {
            println "Cannot find Company ID"
        }
        
        if(peerID) {
            peer = BusinessTransaction.get(peerID)
        }
        
        if(Environment.current == Environment.DEVELOPMENT) {
            println "--- ${new Date().format("dd/MM/yyyy HH:mm:ss")} ${session.user.login}: method ${params.controller}/${params.action}"
            println "Received Instance : ${businessTransactionInstance}"
            println "Identified Peer   : ${peer}"
        }
        
        if(businessTransactionInstance.hasErrors()) {
            println "Error Detected"
        }
        
        businessTransactionInstance.save flush: true
        
        //  Discarding existing peer, if any, and re-creating it for updated
        //  Transaction
        
        if(peer) {
            company.removeFromBusinessTransactions(peer)
            peer.delete flush: true
            businessTransactionService.createPeer(businessTransactionInstance)
        }
        
        successMessage = message(code: 'businesstransaction.update.success')
        successMessage += ":"
        
        msgDetails = "${businessTransactionInstance.transactionDate.format('dd/MM/yyyy')} "
        msgDetails += "${businessTransactionInstance.transactionRemarks} "
        msgDetails += "${new DecimalFormat('#,##0.00').format(businessTransactionInstance.transactionAmount)}"
        
        redirect action: 'index', params: [offset: params.offset, successMsg: successMessage, msgDetails: msgDetails, 'filterDateFrom': params?.filterDateFrom, 'filterDateTill': params?.filterDateTill, 'filterOperation.id': params?.filterOperation?.id]
    }
    
    /**
     *  Delete selected Transaction, with verification and chceking inclusion
     *  into a Cash Flow Statement
     */
    def delete(BusinessTransaction businessTransactionInstance) {
        def peerID = businessTransactionInstance.peer
        def peer = null
        def companyID = session?.company.id
        def company
        def successMessage
        def msgDetails
        
        if(companyID) {
            company = Business.get(companyID)
        }
        else {
            println "Cannot find Company ID"
        }
        
        //  Verifying that Transaction is not included into any Cash Flow
        //  Summary
        
        def yearTrans = businessTransactionInstance.transactionDate.year - 100 + 2000
        def monthTrans = businessTransactionInstance.transactionDate.month + 1
        
        println "Transaction Year  : ${yearTrans}"
        println "Transaction Month : ${monthTrans}"
        
        def includedList = CashFlowStatement.createCriteria().list() {
            eq('company', company)
            eq('year', yearTrans)
            eq('month', monthTrans)
        }
        
        if(peerID > 0) {
            peer = BusinessTransaction.get(peerID)
        }
        
        if(Environment.current == Environment.DEVELOPMENT) {
            println "--- ${new Date().format("dd/MM/yyyy HH:mm:ss")} ${session.user.login}: method ${params.controller}/${params.action}"
            println "Received Instance : ${businessTransactionInstance}"
            println "Identified Peer   : ${peer}"
            println "${includedList} Size: ${includedList.size()}"
        }        

        if(includedList.size() > 0) {
            redirect(action: 'index', params: [
                    offset: params.offset, 
                    errMessage: message(code: 'businesstransaction.error.alreadyincf')
                ]
            )
            
            return
        }
        
        //  Discarding existing peer, if any
        
        if(peer) {
            company.removeFromBusinessTransactions(peer)
            peer.delete flush: true
            
            println "Peer ${peer} was successfully deleted"
        }
        
        successMessage = message(code: 'businesstransaction.delete.success')
        successMessage += ":"

        msgDetails = "${businessTransactionInstance.transactionDate.format('dd/MM/yyyy')} "
        msgDetails += "${businessTransactionInstance.transactionRemarks} "
        msgDetails += "${new DecimalFormat('#,##0.00').format(businessTransactionInstance.transactionAmount)}"
        
        company.removeFromBusinessTransactions(businessTransactionInstance)
        businessTransactionInstance.delete flush: true
        
        redirect action: 'index', params: [offset: 0, successMsg: successMessage, msgDetails: msgDetails]
    }
}
