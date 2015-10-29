package com.runninglama


import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional
class TrajetController {

    TrajetService trajetService

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def liste() {
        def lesTrajets = Trajet.list();
        render view:'liste', model: [lesTrajets:lesTrajets]
    }

    def create() {
        respond new Trajet(params)
    }

    def voirTrajet(Long id) {
        Trajet trajet = Trajet.findById(id);
        Utilisateur utilisateur = session.getAttribute('utilisateur')
        if(trajet != null) {
            render view: 'voir', model: [trajet: trajet, utilisateur:utilisateur]
        } else {
            render view: 'ajouter', model: [listeVehicules: utilisateur.getVehicules()]
        }
    }

    def ajouterTrajet() {
        Utilisateur utilisateur = session.getAttribute('utilisateur')
        render view:'ajouter', model: [listeVehicules:utilisateur.getVehicules()]
    }

    def ajouterTrajetPost(Trajet trajet) {
        trajet.setVehicule(Vehicule.findById(trajet.vehicule.id))
        trajet.setConducteur(session.getAttribute('utilisateur'))
        trajetService.ajouterTrajet(trajet)
        redirect(view: 'index' ,controller: 'accueil')
    }

    def rechercherTrajet() {
        def result = trajetService.rechercherTrajet(params)
        render view: 'rechercherTrajet', model: [trajetInstanceList: result]
    }

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond Trajet.list(params), model: [trajetInstanceCount: Trajet.count()]
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
