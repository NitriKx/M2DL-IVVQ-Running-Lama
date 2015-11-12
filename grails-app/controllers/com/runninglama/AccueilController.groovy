package com.runninglama

import grails.transaction.Transactional

@Transactional
class AccueilController {

    UtilisateurService utilisateurService
    TrajetService trajetService

    def index() {
        render view: 'index', model:
                [lesDerniersUtilisateurs:utilisateurService.listeUtilisateurs(max: 5),
                 lesTrajets:trajetService.listeTrajets(max: 5),
                 isAccueil: true]
    }
}
