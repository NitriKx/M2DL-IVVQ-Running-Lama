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
    }


    @Override
    public String toString() {
        return "Trajet{" +
                "id=" + id +
                ", depart='" + depart + '\'' +
                ", departLat='" + departLat + '\'' +
                ", departLng='" + departLng + '\'' +
                ", arrivee='" + arrivee + '\'' +
                ", arriveeLat='" + arriveeLat + '\'' +
                ", arriveeLng='" + arriveeLng + '\'' +
                ", dateAller=" + dateAller +
                ", dateRetour=" + dateRetour +
                ", commentaire='" + commentaire + '\'' +
                ", prix=" + prix +
                ", nombrePlace=" + nombrePlace +
                ", conducteur=" + conducteur +
                ", vehicule=" + vehicule +
                ", version=" + version +
                ", participants=" + participants +
                '}';
    }
}
