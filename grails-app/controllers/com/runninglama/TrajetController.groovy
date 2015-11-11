package com.runninglama


import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional
class TrajetController {

    TrajetService trajetService

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]


    //
    //  AFFICHEURS
    //


    def liste() {
        def lesTrajets = trajetService.listeTrajets(max: 5);
        render view:'liste', model: [lesTrajets:lesTrajets]
    }

    def create() {
        respond new Trajet(params)
    }

    def rechercherTrajet() {
        def result = trajetService.rechercherTrajet(params)
        render view: 'liste', model: [lesTrajets: result, params:params]
    }

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond trajetService.listeTrajets(params), model: [trajetInstanceCount: trajetService.nombreTrajets()]
    }

    def ajouter() {
        Utilisateur utilisateur = session.getAttribute('utilisateur')
        render view:'ajouter', model: [listeVehicules:utilisateur.getVehicules()]
    }

    def editer(Trajet trajetAEditer) {
        Utilisateur utilisateur = session.getAttribute('utilisateur')
        render view:'editer', model: [listeVehicules:utilisateur.getVehicules(), trajet: trajetAEditer]
    }



    //
    //  MODIFICATEURS
    //


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

    def ajouterTrajetPost(Trajet trajet) {

        if (trajet == null) {
            notFound()
            return
        }

        // Ajoute le conducteur dans le trajet en utilisant l'utilisateur dans la sessions
        Utilisateur utilisateur = session.getAttribute('utilisateur')
        trajet.conducteur = utilisateur
        trajet.validate()

        if (trajet.hasErrors()) {
            render model: [listeVehicules:utilisateur.getVehicules(), trajet: trajet], view: 'ajouter'
            return
        }

        trajetService.ajouterOuModifierTrajet(trajet)

        flash.success = "Le trajet a bien été crée."
        redirect(view: 'index', controller: 'accueil')
    }

    def updateTrajet(Trajet trajet) {

        Utilisateur utilisateur = session.getAttribute('utilisateur')

        if (trajet == null) {
            notFound()
            return
        }

        if (trajet.hasErrors()) {
            render model: [listeVehicules:utilisateur.getVehicules(), trajet: trajet], view: 'editer'
            return
        }

        trajetService.ajouterOuModifierTrajet(trajet)

        flash.success = "Le trajet a bien été modifié."
        redirect(view: 'index', controller: 'accueil')
    }

    def ajouterParticipant(Long idTrajet) {
        Trajet trajet = trajetService.trouverTrajet(idTrajet)
        Utilisateur utilisateur = session.utilisateur
        trajet.addToParticipants(utilisateur)
        trajetService.ajouterOuModifierTrajet(trajet)
        flash.success = "Vous êtes bien inscrit au trajet"
        voirTrajet(idTrajet)
    }




    //
    //  RESPONSE HELPERS
    //

    protected void voirTrajet(Long id) {
        Trajet trajet = trajetService.trouverTrajet(id);
        Utilisateur utilisateur = session.getAttribute('utilisateur')
        if(trajet != null) {
            render view: 'voir', model: [trajet: trajet]
        } else {
            render view: 'ajouter', model: [listeVehicules: utilisateur.getVehicules()]
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
