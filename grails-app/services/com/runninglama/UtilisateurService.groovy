package com.runninglama

import grails.transaction.Transactional

import java.security.MessageDigest

@Transactional
class UtilisateurService {

    UtilisateurDAOService utilisateurDAOService

    def serviceMethod() {

    }

    def verifierIdentifiants(String pseudo,String mdp) {
        Utilisateur utilisateur = utilisateurDAOService.findByPseudo(pseudo)
        if(utilisateur) {
            mdp = utilisateur.passwordSalt + mdp
            mdp = mdp.encodeAsSHA1()
            println(mdp)
            if(!utilisateur.passwordHash.equals(mdp))
                return null
        }
        utilisateur
    }

    def generator = { String alphabet, int n ->
        new Random().with {
            (1..n).collect { alphabet[ nextInt( alphabet.length() ) ] }.join()
        }
    }

    def inscrireUtilisateur(Utilisateur utilisateur) {
        if(utilisateur.motDePasse == utilisateur.motDePasseConfirmation) {
                utilisateur.dateInscription = new Date()
                String salt = generator((('A'..'Z')+('0'..'9')).join(), 9)
                utilisateur.passwordSalt = salt
                utilisateur.passwordHash = (salt+utilisateur.motDePasse).encodeAsSHA1()
                utilisateur.dateInscription = new Date()
                utilisateurDAOService.save(utilisateur)
        } else {
            utilisateur.errors.rejectValue('motDePasse', 'nullable')
        }
        utilisateur
    }
}
