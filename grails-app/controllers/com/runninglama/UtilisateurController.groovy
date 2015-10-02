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
        println utilisateur
        utilisateur = utilisateurService.inscrireUtilisateur(utilisateur)
        render view:'inscription', model: [utilisateur: utilisateur]
    }

    def connexion() {
        render view: 'connexion'
    }

    def connexionPost(params) {
        Utilisateur utilisateur = new Utilisateur(params)
        if(!utilisateurService.verifierIdentifiants(utilisateur)) {
            render(view: 'connexion', model: [message:"Identifiants Incorrects"])
        } else {
            this.getSession().setAttribute('utilisateur',utilisateur)
            render view: 'index'
        }
    }
}
