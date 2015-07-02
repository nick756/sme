package com.sme.actions

import com.sme.entities.*
import com.sme.services.*
import groovy.xml.MarkupBuilder

class MobileController {
    def mobileSessionService
  
    
    def index() { }
    
    //  Somehow, does not work correctly when converted
    //  to method (internal attributes are not available
    //  for XML rendering
    
    def login = {
        def opStatus = 0;
        def user
        def userID  
        def login = params?.name
        def passw = params?.passw

        boolean failure = false
        def descr
        
        def writer = new StringWriter()
        def builder = new MarkupBuilder(writer)
        
        if(login) {
            user = User.findByLogin(login)
            
            if(user && user?.passw == passw&&user?.role?.code == 2) {
                userID = mobileSessionService.addUser(user)
                opStatus = 0
            }
            else {
                if(user && user?.role?.code != 2) {
                    userID = 0
                    opStatus = 1
                }
                else {
                    userID = 0
                    opStatus = 2
                }
            }
        }
       
        render(contentType: 'text/xml') {
            result(code: opStatus, id: userID) {
                originator(request.getRemoteAddr())
                
                if(opStatus == 0) {
                    description("Successful Login")
                }
                else if(opStatus == 1) {
                    description("User Role is not supported for Mobile Interface")
                }
                else if(opStatus == 2) {
                    description("Authentification failed")
                }
                else if(opStatus == 3) {
                    description("Session expired")
                }
                
                if(opStatus == 0) {
                    operator {
                        name(user?.name)
                        role(user?.role?.name)
                        company(user?.company?.name)
                        companyID(user?.company?.id)
                    }
                }
                else {
                    operator{}
                }
            }
        }
    }
}
