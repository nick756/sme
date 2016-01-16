package com.sme.actions

import com.sme.entities.*
import java.text.DecimalFormat
import grails.util.Environment
import grails.transaction.Transactional

import com.sme.util.*
import java.text.*
import grails.converters.*

class BankHomeController {

    def index() {
        
        def resultList
        def filterName = ''
        def filterAccount = ''
        def filterCity = ''
        
        def bankID = session?.user.bank.id
        def bank
        
        bank = Business.get(bankID)
        
        if(!session?.user) {
            redirect (controller: 'login')
            return
        }
        
        params.offset = params.offset ?: 0
        params.max = params.max ?: 10
        
        if(params.filterName) {
            filterName = params.filterName
        }
        
        if(params.filterAccount) {
            filterAccount = params.filterAccount
        }
        
        if(params.filterCity) {
            filterCity = params.filterCity
        }
        
        resultList = Business.createCriteria().list(params) {
            eq('bank', bank) 
            
            if(filterName != '') {
                ilike('name', "%${filterName}%")
            }
            
            if(filterAccount != '') {
                ilike('accountNo', "${filterAccount}%")
            }
            
            if(filterCity != '') {
                ilike('city', "%${filterCity}%")
            }
        }
        
        //  TODO:   check passed 'offset' when returning from Transactions List,
        //          can be beyond the range
        
        [
            businesses: resultList, 
            businessInstanceCount: resultList.totalCount,
            params: params
        ]
        
    }
    
    def show() {
        if(!session?.user) {
            redirect (controller: 'login')
            return
        }        

        params.offset = params.offset ?: 0
        params.max = params.max ?: 10
        
        def businessInstance = Business.get(new Integer(params?.id))
        
        //  Usage Statistics for current and previous Year (number of entered
        //  Transactions)
        
        def now = new Date()
        def yearCurr = now.year + 1900
        def yearPrev = yearCurr - 1
        def statistics = []
        def dateFrom
        def dateTill
        
        12.times {
            def entry = new UsageData(year: yearPrev, month: it + 1, value: 0)
            entry.createCaption()
            
            dateFrom = new Date().copyWith(year: yearPrev, month: it, dayOfMonth: 1)
            dateTill = new Date().copyWith(year: yearPrev, month: it, dayOfMonth: dateFrom.toCalendar().getActualMaximum(Calendar.DAY_OF_MONTH))
            
            dateFrom.clearTime()
            dateTill.clearTime()
            
            entry.dateFrom = dateFrom
            entry.dateTill = dateTill
           
            statistics << entry
        }
        
        12.times {
            def entry = new UsageData(year: yearCurr, month: it + 1, value: 0)
            entry.createCaption()
            
            dateFrom = new Date().copyWith(year: yearCurr, month: it, dayOfMonth: 1)
            dateTill = new Date().copyWith(year: yearCurr, month: it, dayOfMonth: dateFrom.toCalendar().getActualMaximum(Calendar.DAY_OF_MONTH))
            
            dateFrom.clearTime()
            dateTill.clearTime()
            
            entry.dateFrom = dateFrom
            entry.dateTill = dateTill

            statistics << entry
        }
        
        //  Collecting actual Statistics
        
        statistics.each {period ->
            
            dateFrom = period.dateFrom
            dateTill = period.dateTill
            
            def found = BusinessTransaction.createCriteria().list() {
                eq('company', businessInstance)
                ge('transactionDate', dateFrom)
                le('transactionDate', dateTill)
            }
            
            period.value= found.size()
        }
        
        //  Generating JSON object for script
        
        def jsOut = "[\n"
        def counter = 0
        
        statistics.each {record ->
            if(counter > 0) {
                jsOut += ",\n"
            }
            
            jsOut += "{"
            jsOut += "y: ${record.value}, label: ${record.caption}"
            jsOut += " }"
            
            ++counter
        }
        
        jsOut += "\n]"
        
//        println "Generated blank Statistics: "
//        println '---------------------------'
//        statistics.each {record ->
//            println record.toString()
//        }
        
        [
            businessInstance: Business.get(new Integer(params?.id)), 
            params: [max: params?.max, offset: params?.offset],
            jsOut: jsOut
        ]
    }
    
    def reportsettings() {
        if(!session?.user) {
            redirect controller: 'login', action: 'index'
            return
        }        
    }
}
