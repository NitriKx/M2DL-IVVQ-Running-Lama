package com.runninglama

import grails.transaction.Transactional

import javax.servlet.http.HttpSession

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

    def noterTrajet(Trajet trajetInstance, params, HttpSession session, Utilisateur utilisateur) {
//        trajetDAOService.noterTrajet(trajetInstance, params)

        Notation notation = new Notation()
        notation.participant = utilisateur
        notation.commentaire = params['commentaireNote']

        params['note'] = (Integer.parseInt(params['note']) < 0) ? "0" : params['note']
        params['note'] = (Integer.parseInt(params['note']) > 5) ? "5" : params['note']
        notation.note = Integer.parseInt(params['note'])

        trajetDAOService.saveNotation(notation)
        trajetInstance.addToNotations(notation)

        trajetInstance.getConducteur().noteMoyenne = (trajetInstance.getConducteur().noteMoyenne) ? (trajetInstance.getConducteur().noteMoyenne + Float.parseFloat(params['note']))/trajetInstance.notations.size() : Float.parseFloat(params['note'])

//        Utilisateur cur = trajetInstance.getConducteur().merge();
//        session['utilisateur'] = cur
//        cur.save(flush: true)
        trajetDAOService.save(trajetInstance)
    }

//    def getAutorisationNotation(Trajet trajet, Utilisateur utilisateur) {
//        trajetDAOService.getAutorisationNotation(trajet, utilisateur)
//    }
}
