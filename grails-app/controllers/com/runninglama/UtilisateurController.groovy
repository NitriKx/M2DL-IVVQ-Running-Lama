package com.runninglama



import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional
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
        Utilisateur membre = utilisateurService.verifierIdentifiants(utilisateur);

        if(!membre) {
            render(view: 'connexion', model: [message:"Identifiants Incorrects"])
        } else {
            this.getSession().setAttribute('utilisateur',membre)
            flash.success = "Vous êtes bien connecté !"
            redirect(controller: 'accueil', action: 'index')
        }
    }



    def index() {
        Utilisateur utilisateur = this.getSession().getAttribute('utilisateur')
        if(utilisateur) {
            redirect(controller: 'utilisateur', action: 'modifierProfil')
        } else {
            redirect(controller: 'utilisateur', action: 'connexion')
        }
    }

    def deconnexion() {
        this.getSession().invalidate()
        Map<String, String> notification = new HashMap<>()
        flash.success = "Vous êtes bien déconnecté. A bientôt sur Running Lama !"
        redirect(controller: 'accueil', action: 'index')
    }

    def modifierProfil() {
        render(view: 'modifierProfil', model: [utilisateur: this.getSession().getAttribute('utilisateur')])
    }

    def modifierProfilPost() {
        Utilisateur utilisateurModifie = new Utilisateur(params)
        Utilisateur utilisateur = utilisateurService.modifierUtilisateur(this.getSession().getAttribute('utilisateur'),utilisateurModifie,params.ancienMotDePasse)
        if(utilisateur.hasErrors()) {
            render(view: 'modifierProfil',model: [utilisateur:  utilisateur])
        } else {
            this.getSession().setAttribute('utilisateur',utilisateur)
            redirect(controller: 'accueil', action: 'index')
        }
    }
}
