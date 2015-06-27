package com.sme.actions

import com.sme.entities.User
import org.springframework.web.servlet.support.RequestContextUtils as RCU

class LoginController {

    def index = { 
        if(params.lang) {
            def newLocale = new Locale(params.lang)
            RCU.getLocaleResolver(request).setLocale(request, response, newLocale)
            //println "Locale set: " + newLocale
        }
        else {
            //println "Locale remains unchanged"
        }
    }
    
    def process = {LoginCommand cmd ->
        
        if(session?.user) {
            render(view: 'index')
        }
        else {
            if(request.method == 'POST') {
                if(!cmd.hasErrors()) {
                    session.user = cmd.getUser()
                    //session.company = session?.user.company

                    if(session?.user) {
                        switch (session?.user.role.code) {
                            case 1: redirect(controller: 'adminHome')
                                    break
                            case 2: //redirect(controller: 'smeHome')
                                    break
                        }
                        //session.setMaxInactiveInterval(600)
                        //redirect(uri: '/')
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
