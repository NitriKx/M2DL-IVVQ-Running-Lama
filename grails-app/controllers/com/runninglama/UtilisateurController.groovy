package com.runninglama



import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)
class UtilisateurController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]
    def utilisateurService

    def inscription() {
        render view:'inscription'
    }

    def inscriptionPost(params) {
        Utilisateur utilisateur = new Utilisateur(params)
        utilisateur = utilisateurService.inscrireUtilisateur(utilisateur)
        render view:'inscription', model: [utilisateur: utilisateur]
    }

    def connexion() {
        render view: 'connexion'
    }

    def connexionPost(params) {
        Utilisateur utilisateur = new Utilisateur(params)
        Utilisateur utilisateurVerif = utilisateurService.verifierIdentifiants(utilisateur);
        if(utilisateurVerif == null) {
            render(view: 'connexion', model: [message:"Identifiants Incorrects"])
        } else {
            this.getSession().setAttribute('utilisateur',utilisateurVerif)
            redirect(controller: 'accueil', action: 'index')
        }
    }

    def index() {
        Utilisateur utilisateur = this.getSession().getAttribute('utilisateur')
        if(utilisateur) {
            // Redirection vers la gestion des voitures
        } else {
            connexion()
        }
    }

    def deconnexion() {
        this.getSession().invalidate()
        redirect(controller: 'accueil', action: 'index')
    }
}
