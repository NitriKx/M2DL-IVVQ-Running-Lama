package com.runninglama

import grails.test.GrailsMock

import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)
class TrajetController {

    TrajetService trajetService

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def liste() {
        def lesTrajets = Trajet.list();
        print lesTrajets
        render view:'liste', model: [lesTrajets:lesTrajets]
    }

    def ajouterTrajet() {
        Utilisateur utilisateur = session.getAttribute('utilisateur')
        println(Trajet.list())
        render view:'ajouter', model: [listeVehicules:utilisateur.getVehicules()]
    }

    def ajouterTrajetPost(Trajet trajet) {
        trajet.setVehicule(Vehicule.findById(params.vehicule))
        trajet.setConducteur(session.getAttribute('utilisateur'))
        trajetService.ajouterTrajet(trajet)
        redirect(view: 'index' ,controller: 'accueil')
    }


    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond Trajet.list(params), model: [trajetInstanceCount: Trajet.count()]
    }

    def show(Trajet trajetInstance) {
        respond trajetInstance
    }

    def create() {
        respond new Trajet(params)
    }

    @Transactional
    def save(Trajet trajetInstance) {
        if (trajetInstance == null) {
            notFound()
            return
        }

        if (trajetInstance.hasErrors()) {
            respond trajetInstance.errors, view: 'create'
            return
        }

        trajetInstance.save flush: true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'trajet.label', default: 'Trajet'), trajetInstance.id])
                redirect trajetInstance
            }
            '*' { respond trajetInstance, [status: CREATED] }
        }
    }

    def edit(Trajet trajetInstance) {
        respond trajetInstance
    }

    @Transactional
    def update(Trajet trajetInstance) {
        if (trajetInstance == null) {
            notFound()
            return
        }

        if (trajetInstance.hasErrors()) {
            respond trajetInstance.errors, view: 'edit'
            return
        }

        trajetInstance.save flush: true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'Trajet.label', default: 'Trajet'), trajetInstance.id])
                redirect trajetInstance
            }
            '*' { respond trajetInstance, [status: OK] }
        }
    }

    @Transactional
    def delete(Trajet trajetInstance) {

        if (trajetInstance == null) {
            notFound()
            return
        }

        trajetInstance.delete flush: true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'Trajet.label', default: 'Trajet'), trajetInstance.id])
                redirect action: "index", method: "GET"
            }
            '*' { render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'trajet.label', default: 'Trajet'), params.id])
                redirect action: "index", method: "GET"
            }
            '*' { render status: NOT_FOUND }
        }
    }
}
