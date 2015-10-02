package com.runninglama

import grails.transaction.Transactional

import java.security.MessageDigest

@Transactional
class UtilisateurService {

    def serviceMethod() {

    }

    def verifierIdentifiants(String pseudo,String mdp) {
        Utilisateur utilisateur = Utilisateur.findByPseudo(pseudo)
        if(utilisateur) {
            mdp = utilisateur.passwordSalt + mdp
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

    def inscrireUtilisateur(Utilisateur utilisateur) {
        if(utilisateur.motDePasse == utilisateur.motDePasseConfirmation) {
                utilisateur.dateInscription = new Date()
                String salt = generator((('A'..'Z')+('0'..'9')).join(), 9)
                utilisateur.passwordSalt = salt
                utilisateur.passwordHash = (salt+utilisateur.motDePasse).encodeAsSHA1()
                utilisateur.save()
        } else {
            utilisateur.errors.rejectValue('motDePasse', 'nullable')
        }
        utilisateur
    }
}
