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

        def idParticipants = trajet?.participants?.id

        // Seul un particicpant du trajet peut noter le trajet
        boolean autorisation = idParticipants?.contains(utilisateur.id)

        // Les participants ne peuvent voter qu'une fois
        def closure = trajet?.notations?.findAll { it.participant?.id == utilisateur.id }
        autorisation = autorisation && (closure == null || closure.isEmpty())

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
//            trajetInstance.participants.add(utilisateur)
        def idParticipants = trajetInstance?.participants?.id
        def autorisation = idParticipants?.contains(utilisateur.id)

        // Les participants ne peuvent voter qu'une fois
        def closure = trajetInstance?.notations?.findAll { it.participant.id == utilisateur.id }
        autorisation = autorisation && (closure == null || closure.isEmpty())
        if(autorisation)
        {
            trajetService.noterTrajet(trajetInstance, params, utilisateur)
        }
        voirTrajet(trajetInstance?.id)
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
        if(!trajet.participants.id.contains(utilisateur.id))
        {
            trajet.addToParticipants(utilisateur)
            trajetService.creerOuModifier(trajet)
            flash.success = "Vous êtes bien inscrit au trajet"
        }
        voirTrajet(idTrajet)
    }
}
