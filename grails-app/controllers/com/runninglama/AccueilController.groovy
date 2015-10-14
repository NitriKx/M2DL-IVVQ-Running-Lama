package com.runninglama

import grails.transaction.Transactional

@Transactional
class AccueilController {

    def index() {
        def lesDerniersUtilisateurs = Utilisateur.findAll()
        render view: 'index', model:[lesDerniersUtilisateurs]
    }
}
