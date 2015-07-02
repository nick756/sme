package com.sme.actions

import com.sme.entities.*
import com.sme.services.*
import groovy.xml.MarkupBuilder

class MobileController {
    def mobileSessionService
    def opStatus = 0;
    
    def index() { }
    
    def login() {

        def login = params?.name
        def passw = params?.passw
        def user
        def userID
        boolean failure = false
        def descr
        
        def writer = new StringWriter()
        def builder = new MarkupBuilder(writer)
        
        if(login) {
            user = User.findByLogin(login)
            
            if(user && user?.passw == passw) {
                userID = mobileSessionService.addUser(user)
                descr = 'Successful Login'
                
                println ''
                println "Detected User ID: ${userID}"
                println "Result: ${descr}"
                
            }
            else {
                failure = true
            }
        }
        
        println 'Before XML Builder:'
        println "Status: ${opStatus}"
        println "User ID: ${userID}"
        println "Last Operation: " + mobileSessionService.getLastOperationTime(userID)
        println ''
        
        builder.authenticationResult {
            status {
                code (this.opStatus)
                description ("${descr}")
                id ("${userID}")
            }
            userDetails {
                name ("${user?.name}")
                role ("${user?.role?.name}")
                company ("${user?.company?.name}")
            }
        }
        
        render contentType: 'text/xml', encoding: 'UTF-8', writer.toString()
    }
}
