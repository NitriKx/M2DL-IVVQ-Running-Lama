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
        departLat nullable: false, blank: false
        departLng nullable: false, blank: false

        arrivee nullable:false, blank: false
        arriveeLat nullable:false, blank: false
        arriveeLng nullable:false, blank: false

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
