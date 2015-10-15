package com.runninglama

import grails.transaction.Transactional

@Transactional
class TrajetService {

    TrajetDAOService trajetDAOService

    def ajouterTrajet(Trajet trajet) {
        trajet.setConducteur(Utilisateur.findById(1))
        trajetDAOService.save(trajet)
    }
}
