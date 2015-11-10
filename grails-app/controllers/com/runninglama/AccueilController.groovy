package com.runninglama

import grails.transaction.Transactional

@Transactional
class AccueilController {

    def index() {
        render view: 'index', model:[lesDerniersUtilisateurs:Utilisateur.findAll(), lesTrajets:Trajet.list(), isAccueil: true]
    }
}
