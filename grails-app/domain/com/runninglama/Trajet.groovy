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


    static hasMany = [participants:Utilisateur, notations:Notation]


    static constraints = {
        depart nullable: false, blank: false
        departLat nullable: true, blank: true
        departLng nullable: true, blank: true

        arrivee nullable:false, blank: false
        arriveeLat nullable:true, blank: true
        arriveeLng nullable:true, blank: true

        conducteur nullable: false, blank: false
        vehicule nullable: false, blank: false
        dateAller nullable: false, blank: false
        dateRetour nullable: false, blank: false
        prix nullable: false, blank: false
        nombrePlace nullable: false, blank: false, min:1
        commentaire nullable: true, blank: true
    }
}
