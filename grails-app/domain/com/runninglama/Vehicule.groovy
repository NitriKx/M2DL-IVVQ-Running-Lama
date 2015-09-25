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

    static constraints = {
        nb_place blank: false
        nb_place nullable: false
        marque blank: false
        marque nullable: false
        modele blank: false
        modele nullable: false
        annee blank: false
        annee nullable: false
        kilometrage blank: false
        kilometrage nullable: false
        type blak: false
        type nullable: false
        nb_place min: 1
    }
}
