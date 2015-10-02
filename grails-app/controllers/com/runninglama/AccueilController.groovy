package com.runninglama

class AccueilController {

    def index() {
        def lesDerniersUtilisateurs = Utilisateur.findAll()
        render view: 'index', model:[lesDerniersUtilisateurs]
    }
}
