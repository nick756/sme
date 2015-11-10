package com.sme.services

import grails.transaction.Transactional
import java.text.DecimalFormat
import com.sme.entities.*
import groovy.util.logging.Log4j
import grails.util.Environment

@Log4j
@Transactional
class BusinessTransactionService {
    
    /*
     *  Adding New Transaction: using Mobile interface
     */
    def addTransaction(User operator, Date date, Integer code, double amount, String description, Integer cash) {
        def company = operator?.company
        
        if(company) {
            def newTransaction = new BusinessTransaction(
                operationType:      GenericOperation.findByCode(code),
                transactionDate:    date.clearTime(),
                transactionAmount:  amount,
                transactionRemarks: description,
                operator:           "${operator?.name}",
                company:            company,
                cash:               cash
            )
        
            if(!newTransaction.validate()) {
                println "User: ${operator?.name} - validation failed for ${description}"
                return 0
            }
        
            else {
                newTransaction.save(flush: true)
                
                company.addToBusinessTransactions(newTransaction)
                createPeer(newTransaction)
                
                return newTransaction.id
            }
        }
        else {
            return -1
        }
    }
    
    /**
     *  Adding Transaction through Web Interface
     *  TODO (17/08/2017):  Option for saving an image (document) that may be
     *                      related to Transaction
     */ 
    def addTransactionWeb(Business company, User user, Date date, Integer code, double amount, String description, Integer cash) {
        if(company) {
            def newTransaction = new BusinessTransaction(
                operationType:      GenericOperation.findByCode(code),
                transactionDate:    date.clearTime(),
                transactionAmount:  amount,
                transactionRemarks: description,
                operator:           "${operator?.name}",
                company:            company,
                cash:               cash
            )
        
            if(!newTransaction.validate()) {
                return 0
            }
            else {
                newTransaction.save(flush: true)
                company.addToBusinessTransactions(newTransaction)
                return newTransaction.id
            }
        }
        else {
            return -1
        }        
    }
    
    /*
     *  Fetching list of Transactions for a given range of dates. Range of Dates
     *  is controlled for Mobile Interface in respective Controller
     */
    @Transactional (readOnly = true)
    def getTransactions(Business company, Date from, Date till) {
        from.clearTime()
        till.clearTime()
        
        BusinessTransaction.createCriteria().list() {
            eq('company', company)
            ge('transactionDate', from)
            le('transactionDate', till)
            
            and {
                order('transactionDate', 'asc')
                order('id', 'asc')
            }
        }
    }
    
    /**
     *  Checking available Transactions for PNL Statement: PNLStatement instance
     *  must be checked prior to calling this method, to avoid duplicates
     */
    @Transactional (readOnly = true)
    def checkAvailableTransactions(Business company, int year, int month) {
        List result = []
        Date dateFrom
        Date dateTill
        
        dateFrom = new Date().copyWith(year: year, month: month, dayOfMonth: 1)
        dateTill = new Date().copyWith(year: year, month: month, dayOfMonth: dateFrom.toCalendar().getActualMaximum(Calendar.DAY_OF_MONTH))
        
        dateFrom.clearTime()
        dateTill.clearTime()
        
        result = BusinessTransaction.createCriteria().list() {
            eq('company', company)
            ge('transactionDate', dateFrom)
            le('transactionDate', dateTill)
            isNull('statement')
        }
        
        if(Environment.current == Environment.DEVELOPMENT) {
            println ''
            println "--- ${new Date().format('dd/MM/yyyy HH:mm:ss')} - BusinessTransactionService.checkAvailableTransactions ---"
            println "Passed Date from   : ${dateFrom.format('dd/MM/yyyy HH:mm:ss')}"
            println "Passed Date till   : ${dateTill.format('dd/MM/yyyy HH:mm:ss')}"
            println "Detected Interval  : ${dateTill - dateFrom + 1}"
            println "Transactions count : ${result.size()}"
        }
        
        return result
    }
    
    /***************************************************************************
     *  Creation of peer for the given Transaction
     **************************************************************************/
    def createPeer(BusinessTransaction source) {
        def mode = source?.cash
        BusinessTransaction peer
        BigDecimal amountPeer
        def peerType
        
        if(Environment.current == Environment.DEVELOPMENT) {
            println ''
            println "--- ${new Date().format('dd/MM/yyyy HH:mm:ss')} - BusinessTransactionService.createPeer ---"
            println "Mode of Payment    : ${mode}"
            println "Actual Transaction : ${source.operationType.actual}"
            println "Cash case          : ${source?.operationType?.mirrorCash}"
            println "Bank case          : ${source?.operationType?.mirrorBank}"
        }
        
        if(source?.operationType.inbound && source?.operationType.code < 1000) {
            amountPeer = source?.transactionAmount
            
            if(mode == 1) {     //  Cash Operation
                peerType = source?.operationType?.mirrorCash
            }
            else {
                peerType = source?.operationType?.mirrorBank
            }
        }
        else if (source?.operationType.outbound && source?.operationType.code < 1000) {
            amountPeer = -source?.transactionAmount
            
            if(mode == 1) {     //  Cash Operation
                peerType = source?.operationType?.mirrorCash
            }
            else {
                peerType = source?.operationType?.mirrorBank
            }            
        }
        else {
            //  Exclude Cash Balance entries from peering
            
            if(source?.operationType.code == 1000 || source?.operationType.code == 1010 ) {
                return
            }
            else {
                amountPeer  = -source?.transactionAmount
                peerType    = source?.operationType.mirrorCash
            }
        }
        
        if(Environment.current == Environment.DEVELOPMENT) {        
            println 'After Processing:'
            println "Amount Peer   : ${amountPeer}"
            println "Operation     : ${peerType}"
        }
        
        peer = new BusinessTransaction(
            operationType:      peerType,
            transactionDate:    source?.transactionDate,
            transactionAmount:  amountPeer,
            transactionRemarks: "(${source?.transactionRemarks})",
            operator:           source?.operator,
            company:            source?.company,
            cash:               mode            
        ).save(flush: true)
        
        source.peer = peer?.id
        source.save(flush: true)
    }
}
