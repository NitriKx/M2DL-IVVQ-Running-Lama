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
        def idParticipants = trajet.participants.id
        idParticipants.add(utilisateur.id)
        def autorisation = idParticipants.contains(utilisateur.id)
        if(trajet != null) {
            render view: 'voir', model: [trajet: trajet, utilisateur:utilisateur, notationAutorisee: autorisation]
        } else {
            render view: 'ajouter', model: [listeVehicules: utilisateur.getVehicules()]
        }
    }


    def rechercherTrajet() {
        def result = trajetService.rechercherTrajet(params)
        render view: 'liste', model: [lesTrajets: result, params:params]
    }

    def noter(Trajet trajetInstance) {
        def tel = params
        Utilisateur utilisateur = session.getAttribute("utilisateur")
            trajetInstance.participants.add(utilisateur)
        if(trajetInstance.participants.id.contains(utilisateur.id))
        {
            trajetService.noterTrajet(trajetInstance, params, session)
            this.session.setAttribute('utilisateur', trajetInstance.conducteur)
            render(template: 'notation', model: [trajet: trajetInstance])
        }
        else
        {
            redirect(view: "/accueil/index")
        }
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

    def ajouterTrajetPost(Trajet trajet) {
        trajet.setVehicule(Vehicule.findById(trajet.vehicule.id))
        trajet.setConducteur(session.getAttribute('utilisateur'))
        trajetService.creerOuModifier(trajet)
        flash.success = "Le trajet a bien été crée."
        redirect(view: 'index' ,controller: 'accueil')
    }

    def ajouterParticipant(Long idTrajet) {
        Trajet trajet = trajetService.trouverTrajet(idTrajet)
        Utilisateur utilisateur = session.utilisateur
        trajet.addToParticipants(utilisateur)
        trajetService.creerOuModifier(trajet)
        flash.success = "Vous êtes bien inscrit au trajet"
        voirTrajet(idTrajet)
    }
}
