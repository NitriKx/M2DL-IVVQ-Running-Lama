package com.runninglama


import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)
class VehiculeController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    VehiculeService vehiculeService

    def index(Integer max) {
        def utilisateur = session.getAttribute('utilisateur')
        params.max = Math.min(max ?: 10, 100)
        respond vehiculeService.recupererListVehicule(utilisateur, params), model: [vehiculeInstanceCount: vehiculeService.getNombreVehicules(utilisateur, [])]
    }

    def show(Vehicule vehiculeInstance) {
        if (vehiculeInstance == null) {
            notFound()
            return
        }

        respond vehiculeInstance
    }

    def create() {
        def utilisateur = session.getAttribute('utilisateur')
        params['possesseur'] = utilisateur
        respond vehiculeService.creeVehicule(params)
    }

    def save(Vehicule vehiculeInstance) {
        if (vehiculeInstance == null) {
            notFound()
            return
        }

        if (vehiculeInstance.hasErrors()) {
            respond vehiculeInstance.errors, view: 'create'
            return
        }

        vehiculeInstance = vehiculeService.creeOuModifierVehicule(vehiculeInstance)

        redirect(action: 'index')
    }

    def edit(Vehicule vehiculeInstance) {
        if (vehiculeInstance == null) {
            notFound()
            return
        }

        respond vehiculeInstance
    }

    def update(Vehicule vehiculeInstance) {
        if (vehiculeInstance == null) {
            notFound()
            return
        }

        if (vehiculeInstance.hasErrors()) {
            respond vehiculeInstance.errors, view: 'edit'
            return
        }

        vehiculeService.creeOuModifierVehicule(vehiculeInstance)

        redirect(action: 'index')
    }

    def delete(Vehicule vehiculeInstance) {

        if (vehiculeInstance == null) {
            notFound()
            return
        }

        vehiculeService.supprimerVehicule(vehiculeInstance)

        redirect(action: 'index')
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
