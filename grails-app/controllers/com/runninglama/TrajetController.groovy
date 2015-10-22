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
        render view:'liste', model: [lesTrajets:lesTrajets]
    }

    def voirTrajet(Long id) {
        Trajet trajet = Trajet.findById(id);
        Utilisateur utilisateur = session.getAttribute('utilisateur')
        if(trajet != null) {
            render view: 'voir', model: [trajet: trajet, utilisateur:utilisateur]
        } else {
            notFound()
        }
    }

    def supprimer(Long id) {
        Trajet trajet = Trajet.findById(id)
        if(trajet != null) {
            trajetService.delete(trajet)
            flash.message = "Le trajet a été supprimé"
        } else {
            flash.message = "Impossible de supprimer le message"
        }
        liste()
    }

    def ajouterTrajet() {
        Utilisateur utilisateur = session.getAttribute('utilisateur')
        render view:'ajouter', model: [listeVehicules:utilisateur.getVehicules()]
    }

    def ajouterTrajetPost(Trajet trajet) {
        trajet.setVehicule(Vehicule.findById(params.vehicule))
        trajet.setConducteur(session.getAttribute('utilisateur'))
        trajetService.ajouterTrajet(trajet)
        redirect(view: 'index' ,controller: 'accueil')
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'trajet.label', default: 'Trajet'), params.id])
                redirect controller: "accueil", action: "index", method: "GET"
            }
            '*' { render status: NOT_FOUND }
        }
    }
}
