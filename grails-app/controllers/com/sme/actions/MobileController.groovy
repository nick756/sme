package com.sme.actions

import com.sme.entities.*
import com.sme.services.*

/**
 *  Support for full range of Mobile Operations
 */
class MobileController {
    
    def mobileSessionService

    def index() { }

    def login() {
        def opStatus = 0;
        def user
        def userID  
        def login = params?.name
        def passw = params?.passw

        boolean failure = false
        def descr
        
        if(login) {
            user = User.findByLogin(login)
            
            if(user && user?.passw == passw && user?.role?.code == 2) {
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
        else {
            userID = 0
            opStatus = 2
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
                    description("Authentication failed")
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
    
    //  Forwarding list of supported Operations to Mobile Application
    
    def getoperations() {
        
        def userID = new Integer(params?.id)
        def user
        def companyID = new Integer(params?.companyID)
        def company
        def oplist = []                 //  List of ProfileLink instances
        
        if(!mobileSessionService.validateTimeout(userID)) {
            render(contentType: 'text/xml') {
                result(code: "3", id: userID) {
                    originator(request.getRemoteAddr())
                    description("Session expired")
                    operationsList{}
                }
            }
        }
        else {
            if(userID) {
                user = User.get(userID)
          
                if(companyID == user?.company?.id) {
                    company = Business.get(companyID)
                
                    if(company?.profile?.operations) {
                        oplist = company?.profile?.operations.asList()
                    }
                
                    render(contentType: 'text/xml') {
                        result(code: "0", id: userID) {
                        
                            originator(request.getRemoteAddr())
                            description("Successful Request")
                        
                            supportedOperations {
                                oplist.each {
                                    def opinstance = it?.operation
                               
                                    operation {
                                        code(opinstance?.code)
                                        name(opinstance?.name)
                                        inbound(opinstance?.inbound)
                                        outbound(opinstance?.outbound)
                                        type(opinstance?.accountType?.name)
                                    }
                                }
                            }
                        }
                    }
                }
                else {
                    render(contentType: 'text/xml') {
                        result(code: "1", id: userID) {
                            originator(request.getRemoteAddr())
                            description("Mismatching Company ID")
                            operationsList{}
                        }
                    }
                }
            }
            else {
                render(contentType: 'text/xml') {
                    result(code: "2", id: "0") {
                        originator(request.getRemoteAddr())
                        description("Authentication failed")
                        operationsList{}
                    }
                }
            }
        }
    }
}
