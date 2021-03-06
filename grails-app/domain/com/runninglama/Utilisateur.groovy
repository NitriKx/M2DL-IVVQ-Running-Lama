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
    Float noteMoyenne

    //List vehicules

    //static fetchMode = [vehicules:"eager"]

    static hasMany = [
            vehicules: Vehicule
    ]

    static  mapping = {
        vehicules lazy: false
    }


    static transients = ['motDePasse','motDePasseConfirmation']

    String motDePasse
    String motDePasseConfirmation
    

    static constraints = {
        email email:true
        dateDerniereConnexion nullable: true
        telephone size: 10..10
        passwordHash nullable: false, blank: false
        passwordSalt nullable: false, blank: false
        motDePasse bindable: true
        motDePasseConfirmation bindable: true
        noteMoyenne nullable: true, blank: true
    }

    boolean participe(Trajet trajet) {
        return trajet?.participants?.id?.contains(this.id) && trajet.conducteur.id != this.id
    }


    String toString() {
        return this.pseudo
    }
}
