package com.runninglama

import grails.transaction.Transactional

@Transactional
class TrajetService {

    TrajetDAOService trajetDAOService

    def creerOuModifier(Trajet trajet) {
        trajet.validate()
        trajetDAOService.save(trajet)
    }

    def supprimer(Trajet trajet) {
        trajetDAOService.delete(trajet)
    }

    def rechercherTrajet(params) {
        trajetDAOService.search(params)
    }

    def trouverTrajet(Long id) {
        trajetDAOService.trouver(id)
    }
}
