package com.sme.actions

import com.sme.entities.*
import com.sme.services.*
import org.springframework.web.servlet.support.RequestContextUtils as RCU

class LoginController {

    def billingService
    
    def index = { 
        if(params.lang) {
            def newLocale = new Locale(params.lang)
            RCU.getLocaleResolver(request).setLocale(request, response, newLocale)
            session.locale = newLocale
            println "Locale set: " + newLocale
        }
        else {
            //println "Locale remains unchanged"
        }
        
        println "Current Locale: ${session.locale}"
    }
    
    def process = {LoginCommand cmd ->
        Locale locale
        def business
        
        if(session?.user) {
            render(view: 'index')
        }
        else {
            if(request.method == 'POST') {
                if(!cmd.hasErrors()) {
                    session.user = cmd.getUser()
                    //session.company = session?.user.company

                    if(session?.user) {
                        session.setMaxInactiveInterval(-1)
                        
                        def currentLocale = RCU.getLocale(request)
                        
                        println "Login process: Locale = ${currentLocale.toString()}"
                        
                        if(currentLocale.toString() == 'ms') {
                            locale = new Locale('ms')
                        }
                        else {
                            locale = new Locale('en')
                        }
                        
                        switch (session?.user.role.code) {
                        case 1: session.locale = locale
                            redirect(controller: 'adminHome')
                            break
                        
                            //  SME Operators login
                        case 2: session.company = session?.user.company
                            session.locale = locale
                            business = Business.get(session.company?.id)
                            
                            println ''
                            println "LOGIN: ${new Date().format("dd/MM/yyyy HH:mm")} User: ${session?.user?.name}/${session?.user.role.name}"
                            println "Company: ${business?.name}"
                               
                            if(business?.activated) {
                                if(billingService.checkForOutstandingInvoices(business)) {
                                
                                    println 'Outstanding Invoices detected'
                                
                                    params.offset = 0
                                    params.max = 10                                
                                
                                    def errorMessage = "You have Outstanding Invoices"
                                    def instancesList = Bill.createCriteria().list(params) {
                                        eq('company', business)
                                        order 'dueDate', 'desc'
                                    }
                                
                                    render view: '/smebilling/index', model:  [
                                        instancesList:  instancesList,
                                        counter:        instancesList?.totalCount,
                                        params:         params,
                                        errMessage:     errorMessage
                                    ]
                                }
                                else {
                                    redirect(controller: 'smehome')
                                }                                
                            }
                            else {
                                billingService.activateBusiness(business)
                                
                                println "Account activated: Billing starts from ${business?.startBillingDate?.format('dd/MM/yyyy')}"
                                
                                redirect controller: 'smehome'
                            }

                            break
                        case 3: session.bank = session?.user.bank
                            session.locale = locale
                            redirect(controller: 'bankHome')
                            break
                        }
                    }
                }
                else {
                    render(view: '/login/index', model: [loginCmd: cmd])
                }
            }
            else {
                render(view: '/login/index')
            }
        }        
    }
    
    def logout = {
        def curLocale = RCU.getLocale(request)
        session.invalidate()
        RCU.getLocaleResolver(request).setLocale(request, response, curLocale)
        redirect(action: 'index')        
    }
    
}

class LoginCommand {

    String login
    String passw
    private u

    User getUser() {
        if(!u && login) {
            u = User.findByLogin(login)
        }

        return u
    }

    static constraints = {
        login(blank: false, validator: {val, cmd ->
                if(!cmd.user) {
                    return "user.not.found"
                }
            })
        passw(blank: false, validator: {val, cmd ->
                if(cmd.user && cmd.user.passw != val) {
                    return "user.password.invalid"
                }
            })
    }
}
