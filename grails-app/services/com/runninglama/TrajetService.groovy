package com.runninglama

import grails.transaction.Transactional

@Transactional
class TrajetService {

    TrajetDAOService trajetDAOService

    def ajouterOuModifierTrajet(Trajet trajet) {
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

    def listeTrajets(params) {
        trajetDAOService.liste(params)
    }

    def nombreTrajets() {
        trajetDAOService.count()
    }
}
