package com.runninglama

import grails.transaction.Transactional

import java.security.MessageDigest

@Transactional
class UtilisateurService {

    def serviceMethod() {

    }

    def verifierIdentifiants(params) {
        Utilisateur utilisateur = Utilisateur.findByPseudo(params.pseudo)
        if(utilisateur) {
            String mdp = utilisateur.passwordSalt + params.mdp
            MessageDigest md = MessageDigest.getInstance("SHA-1", "BC");
            byte[] mdpToByte = mdp.bytes
            String mdpBase = Hex.encodeToString(mdpToByte);
            utilisateur.passwordHash.equals(mdpBase)
        }
        utilisateur
    }

    def generator = { String alphabet, int n ->
        new Random().with {
            (1..n).collect { alphabet[ nextInt( alphabet.length() ) ] }.join()
        }
    }
}
