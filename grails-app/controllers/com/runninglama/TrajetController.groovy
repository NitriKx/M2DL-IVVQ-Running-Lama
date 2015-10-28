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
        Trajet trajet = trajetService.trouverTrajet(id);
        Utilisateur utilisateur = session.getAttribute('utilisateur')
        if(trajet != null) {
            render view: 'voir', model: [trajet: trajet]
        } else {
            render view: 'ajouter', model: [listeVehicules: utilisateur.getVehicules()]
        }
    }


    def rechercherTrajet() {
        def result = trajetService.rechercherTrajet(params)
        render view: 'liste', model: [lesTrajets: result, params:params]
    }

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond Trajet.list(params), model: [trajetInstanceCount: Trajet.count()]
    }

    def supprimer(Long id) {
        Trajet trajet = trajetService.trouverTrajet(id)
        if(trajet != null) {
            trajetService.supprimer(trajet)
            flash.success = "Le trajet a été supprimé"
        } else {
            flash.danger = "Impossible de supprimer le message"
        }
        liste()
    }


    def ajouterTrajet() {
        Utilisateur utilisateur = session.getAttribute('utilisateur')
        render view:'ajouter', model: [listeVehicules:utilisateur.getVehicules()]
    }

    def editer(Trajet trajetAEditer) {
        Utilisateur utilisateur = session.getAttribute('utilisateur')
        render view:'editer', model: [listeVehicules:utilisateur.getVehicules(), trajet: trajetAEditer]
    }

    def updateTrajet(Trajet trajet) {

        if (trajet == null) {
            notFound()
            return
        }

        if (trajet.hasErrors()) {
            respond trajet.errors, view: 'editer'
            return
        }

        trajetService.ajouterOuModifierTrajet(trajet)

        redirect(view: 'index', controller: 'accueil')
    }

    def ajouterTrajetPost(Trajet trajet) {
        trajet.setConducteur(session.getAttribute('utilisateur'))
        trajetService.ajouterOuModifierTrajet(trajet)
        flash.success = "Le trajet a bien été crée."
        redirect(view: 'index' ,controller: 'accueil')
    }

    def ajouterParticipant(Long idTrajet) {
        Trajet trajet = trajetService.trouverTrajet(idTrajet)
        Utilisateur utilisateur = session.utilisateur
        trajet.addToParticipants(utilisateur)
        trajetService.ajouterOuModifierTrajet(trajet)
        flash.success = "Vous êtes bien inscrit au trajet"
        voirTrajet(idTrajet)
    }
}
