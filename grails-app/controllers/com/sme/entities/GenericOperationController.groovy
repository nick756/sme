package com.sme.entities



import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)
class GenericOperationController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond GenericOperation.list(params), model:[genericOperationInstanceCount: GenericOperation.count()]
    }

    def show(GenericOperation genericOperationInstance) {
        respond genericOperationInstance
    }

    def create() {
        respond new GenericOperation(params)
    }

    @Transactional
    def save(GenericOperation genericOperationInstance) {
        if (genericOperationInstance == null) {
            notFound()
            return
        }

        if (genericOperationInstance.hasErrors()) {
            respond genericOperationInstance.errors, view:'create'
            return
        }

        genericOperationInstance.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'genericOperation.label', default: 'GenericOperation'), genericOperationInstance.id])
                redirect genericOperationInstance
            }
            '*' { respond genericOperationInstance, [status: CREATED] }
        }
    }

    def edit(GenericOperation genericOperationInstance) {
        respond genericOperationInstance
    }

    @Transactional
    def update(GenericOperation genericOperationInstance) {
        if (genericOperationInstance == null) {
            notFound()
            return
        }

        if (genericOperationInstance.hasErrors()) {
            respond genericOperationInstance.errors, view:'edit'
            return
        }

        genericOperationInstance.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'GenericOperation.label', default: 'GenericOperation'), genericOperationInstance.id])
                redirect genericOperationInstance
            }
            '*'{ respond genericOperationInstance, [status: OK] }
        }
    }

    @Transactional
    def delete(GenericOperation genericOperationInstance) {

        if (genericOperationInstance == null) {
            notFound()
            return
        }

        genericOperationInstance.delete flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'GenericOperation.label', default: 'GenericOperation'), genericOperationInstance.id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'genericOperation.label', default: 'GenericOperation'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
