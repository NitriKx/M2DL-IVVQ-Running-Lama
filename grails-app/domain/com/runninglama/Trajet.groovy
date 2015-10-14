package com.runninglama

class Trajet {

    String depart
    String departLat
    String departLng

    String arrivee
    String arriveeLat
    String arriveeLng

    Date dateAller
    Date dateRetour

    String commentaire
    Float prix
    Integer nombrePlace

    Utilisateur conducteur
    Vehicule vehicule


    static hasMany = [participants:Utilisateur]

    static constraints = {
        depart nullable: false, blank: false
        arrivee nullable:false, blank: false
        conducteur nullable: false, blank: false
        vehicule nullable: false, blank: false
        dateAller nullable: false, blank: false
    }
}
