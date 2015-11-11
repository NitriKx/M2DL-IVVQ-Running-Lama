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

    def noterTrajet(Trajet trajetInstance, params, Utilisateur utilisateur) {

        Notation notation = new Notation()
        notation.participant = utilisateur
        notation.commentaire = params['commentaireNote']

        params['note'] = (Integer.parseInt(params['note']) < 0) ? "0" : params['note']
        params['note'] = (Integer.parseInt(params['note']) > 5) ? "5" : params['note']
        notation.note = Integer.parseInt(params['note'])

        trajetDAOService.saveNotation(notation)
        trajetInstance.addToNotations(notation)

        trajetInstance.getConducteur().noteMoyenne = (trajetInstance.getConducteur().noteMoyenne) ? (trajetInstance.getConducteur().noteMoyenne * trajetInstance.notations.size() - 1 + Float.parseFloat(params['note']))/trajetInstance.notations.size() : Float.parseFloat(params['note'])

        trajetDAOService.save(trajetInstance)
    }
}
