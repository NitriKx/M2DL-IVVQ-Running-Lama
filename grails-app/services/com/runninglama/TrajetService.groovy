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
        notation.setCommentaire(params['commentaireNote'])

        if(Integer.parseInt(params['note']) < 0){
            params['note'] = "0"
        } else if(Integer.parseInt(params['note']) > 5){
            params['note'] = "5"
        }

        notation.note = Integer.parseInt(params['note'])

        trajetDAOService.saveNotation(notation)
        trajetInstance.addToNotations(notation)

        def temp = Trajet.findAllByConducteur(trajetInstance.conducteur)
        def tot = 0
        for(trajet in temp)
        {
            tot += trajet.notations.size()
        }

        trajetInstance.getConducteur().noteMoyenne = (trajetInstance.getConducteur().noteMoyenne) ? (trajetInstance.getConducteur().noteMoyenne * (tot - 1) + Float.parseFloat(params['note']))/tot : Float.parseFloat(params['note'])

        trajetDAOService.save(trajetInstance)
    }

    def countbyVehicule(Vehicule vehicule) {
        trajetDAOService.countByVehicule(vehicule)
    }
}
