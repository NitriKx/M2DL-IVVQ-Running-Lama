package com.runninglama

import javax.persistence.Transient

class Utilisateur {

    String nom
    String prenom
    String pseudo
    String passwordHash
    String passwordSalt
    Date dateInscription
    Date dateDerniereConnexion
    Date dateNaissance
    String email
    String telephone

    @Transient
    String motDePasse

    @Transient
    String motDePasseConfirmation
    

    static constraints = {
        email email:true
        dateDerniereConnexion nullable: true
        telephone size: 10..10
    }
}
