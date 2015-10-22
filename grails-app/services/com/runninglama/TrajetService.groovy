package com.runninglama

import grails.transaction.Transactional

@Transactional
class TrajetService {

    TrajetDAOService trajetDAOService

    def ajouterTrajet(Trajet trajet) {
        trajet.validate()
        trajetDAOService.save(trajet)
    }

    def delete(Trajet trajet) {
        trajetDAOService.delete(trajet)
    }
}
