package com.sme.actions

import com.sme.entities.*

class ProfileController {

    def index(Integer max) { 
        if(!session.user) {
            redirect controller: 'login'
        }
        
        params.max = Math.min(max ?: 5, 100)
        params.offset = 0
        
        [profiles: GenericProfile.list(params), profileCount: GenericProfile.count()]        
    }
    
    //  Combined with update of Operations Statuses (toggling status)
    def show(Integer max) {
        if(!session.user) {
            redirect controller: 'login'
        }
        
        params.offset = params.offset ?: 0
        params.max = Math.min(max ?: 5, 100)
        
        def profile = GenericProfile.get(new Integer(params?.id))
        
        def from = new Integer(params?.offset)
        def to = new Integer(params?.offset) + new Integer(params?.max) - 1
        def opCount = profile?.operations.size()
        
        if(to > opCount) to = opCount - 1

        println ''
        println "Profile.show: opCount = ${opCount} From: ${from} To: ${to}"
        println "Sorting: ${params.sort} ${params.order}"
        
        def operations = []
        
        if(opCount > 0) {
            if(params.sort == 'accountType') {
                operations = profile?.operations.asList().sort{
                    it.operation."${params.sort}".name
                }                
            }
            else if(params.sort && params.order == "asc") {
                operations = profile?.operations.asList().sort{
                    it.operation."${params.sort}"
                }
            }
            else if(params.sort && params.order == "desc") {
                operations = profile?.operations.asList().sort{
                    it.operation."${params.sort}"
                }.reverse()
            }            
            
            operations = operations[from..to]
        }
        
       
        if(params?.update && opCount > 0) {
            def checkboxes = params.list('checkbox').get(0)
        
            checkboxes.each {
                if(it.key.isLong()) {
                    def proflink = ProfileLink.get(it.key.toLong())
                    proflink.active = proflink.active ? false:true
                    proflink.save(flush: true)
                }
            }
        }
        
        [
            profileInstance:    profile, 
            operationList:      operations, 
            operationsCount:    opCount,
            params:             [max: params?.max, offset: params?.offset]
        ]
    }
}
