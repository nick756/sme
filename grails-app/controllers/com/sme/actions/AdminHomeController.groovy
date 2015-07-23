package com.sme.actions

import com.sme.entities.*
import java.text.DecimalFormat

class AdminHomeController {

    def index(Integer max) {
        
        if(!session?.user) {
            redirect (controller: 'login')
        }
        
        params.offset = params.offset ?: 0
        params.max = Math.min(max ?: 10, 100)
        
        //  TODO:   check passed 'offset' when returning from Transactions List,
        //          can be beyond the range
        
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
    
    /**
     *  Fetching list of performed Transactions for a given Company, filtering
     *  options can be applied
     */
    def listtransactions() {
        if(!session?.user) {
            redirect (controller: 'login')
        }
        
        def transList = []
        def transCount
        def filtOption
        def opFilter = 0
        
        def Date dateFrom
        def Date dateTill
        
        println "LISTTRANSACTIONS: forwarded params"
        println params
        
        params.offset = params.offset ?: 0
        params.max = params.max ?: 10
        
        //  Overring default Grails Form behavior (cannot pass existing id)
        params.id = params.id ?: params.instId
        
        if(params?.filterOption) {
            filtOption = params?.filterOption.toLong()
            
            if(filtOption > 1) {
                if(filtOption == 2) {   //  One Month Period (inclusive)
                    dateTill = new Date().clearTime()
                    dateFrom = dateTill.minus(31)
                    
                    opFilter = 2
                    
                    //  Overriding 'offset' as filtering may be done not
                    //  from first page
                    params.offset = 0
                    
                    transList = BusinessTransaction.createCriteria().list() {
                        eq('company', Business.get(params.id))
                        
                        between('transactionDate', dateFrom, dateTill)
                       
                        and {
                            order('transactionDate', 'asc')
                            order('id', 'asc')
                        }
                    }
                    
                }
                else {                  //  Range of Dates
                    if(params?.dateFrom && params?.dateTill) {
                        dateFrom = new Date().parse("d/M/yyyy", params?.dateFrom)
                        dateTill = new Date().parse("d/M/yyyy", params?.dateTill)
                        
                        transList = BusinessTransaction.createCriteria().list() {
                            eq('company', Business.get(params.id))
                        
                            between('transactionDate', dateFrom, dateTill)
                       
                            and {
                                order('transactionDate', 'asc')
                                order('id', 'asc')
                            }
                        }
                        
                        opFilter = 3
                    }
                    else {              //  Fall back to default
                        
                    }
                }
            }
            else {
                transList = BusinessTransaction.findAllByCompany(
                    Business.get(params?.id), 
                    [sort: "transactionDate", order: "asc"],
                    [sort: 'id', order: 'asc']
                )                
            }
        }
        else {
            transList = BusinessTransaction.findAllByCompany(
                Business.get(params?.id), 
                [sort: "transactionDate", order: "asc"],
                [sort: 'id', order: 'asc']                
            )
        }
        
        println "Total Records (before pagination): ${transList.size()}"
        
        transCount = transList.size()
        def indexTo     = new Integer(params?.offset) + new Integer(params?.max) - 1
        def indexFrom   = new Integer(params?.offset)
        
        if(indexTo >= transCount) indexTo = transCount - 1
        
        if(transCount > 0) {
            transList = transList[indexFrom..indexTo]
        }
        
        println "Total Records passed after pagination: ${transList.size()}"
        
        [
            businessInstance:   Business.get(new Integer(params?.id)),
            transactionsList:   transList,
            transactionCount:   transCount,
            operationFilter:    opFilter,
            dateFrom:           dateFrom,
            dateTill:           dateTill,
            params:             [max: params?.max, offset: params?.offset]
        ]
    }
}
