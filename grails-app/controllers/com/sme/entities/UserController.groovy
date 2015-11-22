package com.sme.entities



import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)
class UserController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond User.list(params), model:[userInstanceCount: User.count()]
    }
    
    def list(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        
        def userInstanceList
        def searchByName = ''
        def searchByLogin = ''
        def searchByCompany = ''
        def searchByAccount = ''
        
        if(params.search_name) {
            searchByName = params.search_name
        }
        
        if(params.search_login) {
            searchByLogin = params.search_login
        }
        
        if(params.search_company) {
            searchByCompany = params.search_company
        }
        
        if(params.search_account) {
            searchByAccount = params.search_account
        }        
        
        userInstanceList = User.createCriteria().list(params) {
            if(searchByName != '') {
                ilike('name', "%${searchByName}%")
            }
            
            if(searchByLogin != '') {
                ilike('login', "%${searchByLogin}%")
            }
            
            if(searchByCompany != '') {
                company {
                    ilike('name', "%${searchByCompany}%")
                }
            }
            
            if(searchByAccount != '') {
                company {
                    ilike('accountNo', "${searchByAccount}%")
                }
            }            
        }
        
        [
            userInstanceList: userInstanceList,
            userInstanceCount: userInstanceList.totalCount,
            searchByName: searchByName,
            searchByLogin: searchByLogin,
            searchByCompany: searchByCompany,
            searchByAccount: searchByAccount
        ]
    }

    def show(User userInstance) {
        respond userInstance
    }

    def create() {
        respond new User(params)
    }

    @Transactional
    def save(User userInstance) {
        if (userInstance == null) {
            notFound()
            return
        }

        if (userInstance.hasErrors()) {
            respond userInstance.errors, view:'create'
            return
        }

        userInstance.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'user.label', default: 'User'), userInstance.id])
                redirect userInstance
            }
            '*' { respond userInstance, [status: CREATED] }
        }
    }

    def edit(User userInstance) {
        respond userInstance
    }

    @Transactional
    def update(User userInstance) {
        if (userInstance == null) {
            notFound()
            return
        }

        if (userInstance.hasErrors()) {
            respond userInstance.errors, view:'edit'
            return
        }

        userInstance.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'User.label', default: 'User'), userInstance.id])
                redirect userInstance
            }
            '*'{ respond userInstance, [status: OK] }
        }
    }

    @Transactional
    def delete(User userInstance) {

        if (userInstance == null) {
            notFound()
            return
        }

        userInstance.delete flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'User.label', default: 'User'), userInstance.id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'user.label', default: 'User'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
    
    /**
     *  Printing list of users
     */
    
    def report() {
        if(!session?.user) {
            redirect controller: 'login', action: 'index'
            return
        }
        
        def userInstanceList
        def searchByName = ''
        def searchByLogin = ''
        def searchByCompany = ''
        def searchByAccount = ''
        
        if(params.search_name) {
            searchByName = params.search_name
        }
        
        if(params.search_login) {
            searchByLogin = params.search_login
        }
        
        if(params.search_company) {
            searchByCompany = params.search_company
        }
        
        if(params.search_account) {
            searchByAccount = params.search_account
        }        
        
        userInstanceList = User.createCriteria().list(params) {
            if(searchByName != '') {
                ilike('name', "%${searchByName}%")
            }
            
            if(searchByLogin != '') {
                ilike('login', "%${searchByLogin}%")
            }
            
            if(searchByCompany != '') {
                company {
                    ilike('name', "%${searchByCompany}%")
                }
            }
            
            if(searchByAccount != '') {
                company {
                    ilike('accountNo', "${searchByAccount}%")
                }
            }             
        }        
        
        [usersList: userInstanceList]
    }
}
