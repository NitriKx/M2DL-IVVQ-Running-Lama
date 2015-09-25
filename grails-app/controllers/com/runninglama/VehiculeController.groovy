package com.runninglama


import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)
class VehiculeController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond Vehicule.list(params), model: [vehiculeInstanceCount: Vehicule.count()]
    }

    def show(Vehicule vehiculeInstance) {
        respond vehiculeInstance
    }

    def create() {
        respond new Vehicule(params)
    }

    @Transactional
    def save(Vehicule vehiculeInstance) {
        if (vehiculeInstance == null) {
            notFound()
            return
        }

        if (vehiculeInstance.hasErrors()) {
            respond vehiculeInstance.errors, view: 'create'
            return
        }

        vehiculeInstance.save flush: true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'vehicule.label', default: 'Vehicule'), vehiculeInstance.id])
                redirect vehiculeInstance
            }
            '*' { respond vehiculeInstance, [status: CREATED] }
        }
    }

    def edit(Vehicule vehiculeInstance) {
        respond vehiculeInstance
    }

    @Transactional
    def update(Vehicule vehiculeInstance) {
        if (vehiculeInstance == null) {
            notFound()
            return
        }

        if (vehiculeInstance.hasErrors()) {
            respond vehiculeInstance.errors, view: 'edit'
            return
        }

        vehiculeInstance.save flush: true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'Vehicule.label', default: 'Vehicule'), vehiculeInstance.id])
                redirect vehiculeInstance
            }
            '*' { respond vehiculeInstance, [status: OK] }
        }
    }

    @Transactional
    def delete(Vehicule vehiculeInstance) {

        if (vehiculeInstance == null) {
            notFound()
            return
        }

        vehiculeInstance.delete flush: true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'Vehicule.label', default: 'Vehicule'), vehiculeInstance.id])
                redirect action: "index", method: "GET"
            }
            '*' { render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'vehicule.label', default: 'Vehicule'), params.id])
                redirect action: "index", method: "GET"
            }
            '*' { render status: NOT_FOUND }
        }
    }
}
