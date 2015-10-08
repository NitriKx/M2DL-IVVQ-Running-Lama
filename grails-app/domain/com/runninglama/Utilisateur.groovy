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

    List vehicules

    static hasMany = [
            vehicules: Vehicule
    ]

    @Transient
    String motDePasse

    @Transient
    String motDePasseConfirmation
    

    static constraints = {
        email email:true
        dateDerniereConnexion nullable: true
        telephone size: 10..10
        passwordHash nullable: false, blank: false
        passwordSalt nullable: false, blank: false
        motDePasse nullable:true
        motDePasseConfirmation nullable:true
    }


    @Override
    public String toString() {
        return "Utilisateur{" +
                "id=" + id +
                ", nom='" + nom + '\'' +
                ", prenom='" + prenom + '\'' +
                ", pseudo='" + pseudo + '\'' +
                ", passwordHash='" + passwordHash + '\'' +
                ", passwordSalt='" + passwordSalt + '\'' +
                ", dateInscription=" + dateInscription +
                ", dateDerniereConnexion=" + dateDerniereConnexion +
                ", dateNaissance=" + dateNaissance +
                ", email='" + email + '\'' +
                ", telephone='" + telephone + '\'' +
                ", version=" + version +
                '}';
    }
}
