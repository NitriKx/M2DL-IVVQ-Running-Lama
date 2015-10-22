package com.runninglama

import grails.transaction.Transactional

@Transactional
class AccueilController {

    def index() {
        def lesDerniersUtilisateurs = Utilisateur.findAll()
        def lesTrajets = Trajet.list();

        render view: 'index', model:[lesDerniersUtilisateurs:lesDerniersUtilisateurs, lesTrajets:lesTrajets]
    }
}
