package com.runninglama

import grails.transaction.Transactional

@Transactional
class UtilisateurDAOService {

    Utilisateur findByPseudo(String pseudo) {
        Utilisateur.findByPseudo(pseudo)
    }

    def save(Utilisateur utilisateurInstance) {
        utilisateurInstance.save(flush: true)
    }
}
