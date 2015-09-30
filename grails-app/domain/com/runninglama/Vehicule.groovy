package com.runninglama

enum TypeVehicule
{
    MOTO,
    BUS,
    VOITURE
}

class Vehicule {

    int nb_place
    String marque
    String modele
    Date annee
    int kilometrage
    TypeVehicule type

    // Variables gérées par GORM
    Date dateCreated
    Date lastUpdated

    static constraints = {
        nb_place blank: false, nullable: false, min: 1
        marque blank: false, nullable: false
        modele blank: false, nullable: false
        annee blank: false, nullable: false
        kilometrage blank: false, nullable: false
        type blank: false, nullable: false
    }
}
