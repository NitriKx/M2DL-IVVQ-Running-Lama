package com.runninglama

import grails.transaction.Transactional
import java.security.MessageDigest

@Transactional
class ServiceUtilisateurService {

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
}
