package com.runninglama

import grails.test.mixin.TestFor
import spock.lang.Specification
import spock.lang.Unroll

/**
 * See the API for {@link grails.test.mixin.domain.DomainClassUnitTestMixin} for usage instructions
 */
@TestFor(Trajet)
class TrajetSpec extends Specification {

    def setup() {
    }

    def cleanup() {
    }

    @Unroll
    def "Un trajet avec des données correctes"() {

        given: "Un trajet avec des données correctes "


        def trajet = new Trajet(depart: aDepart, departLat: aDepartLat, departLng: aDepartLng,
                                arrivee: aArrivee, arriveeLat: aArriveelat, arriveeLng: aArriveeLng,
                                dateAller: aDateAller, dateRetour: aDateRetour,
                                commentaire: aCommentaire, prix:aPrix, nombrePlace: aNombrePlace,
                                conducteur: aConducteur, vehicule: aVehicule)

        when: "on essaye de valider un trajet"
        trajet.validate()

        then: "le trajet est valide"
        trajet.hasErrors() == false

        where:
        aDepart    | aArrivee | aDepartLat   | aDepartLng  | aArriveelat  | aArriveeLng | aDateAller | aDateRetour | aCommentaire  | aPrix | aNombrePlace | aConducteur | aVehicule
        "Toulouse" | "Muret"  | "43.6008029" | "1.3628011" | "43.4408242" | "1.2286806" | new Date() | new Date()  | "Commentaire" | 13.4  | 4            | Mock(Utilisateur) | Mock(Vehicule)
        "Toulouse" | "Muret"  | null         | "1.3628011" | "43.4408242" | "1.2286806" | new Date() | new Date()  | "Commentaire" | 13.4  | 4            | Mock(Utilisateur) | Mock(Vehicule)
        "Toulouse" | "Muret"  | "43.6008029" | null        | "43.4408242" | "1.2286806" | new Date() | new Date()  | "Commentaire" | 13.4  | 4            | Mock(Utilisateur) | Mock(Vehicule)
        "Toulouse" | "Muret"  | "43.6008029" | "1.3628011" | null         | "1.2286806" | new Date() | new Date()  | "Commentaire" | 13.4  | 4            | Mock(Utilisateur) | Mock(Vehicule)
        "Toulouse" | "Muret"  | "43.6008029" | "1.3628011" | "43.4408242" | null        | new Date() | new Date()  | "Commentaire" | 13.4  | 4            | Mock(Utilisateur) | Mock(Vehicule)
    }


    @Unroll
    def "Un trajet avec des données incorrectes"() {

        given: "Un trajet avec des données incorrectes"


        def trajet = new Trajet(depart: aDepart, departLat: aDepartLat, departLng: aDepartLng,
                arrivee: aArrivee, arriveeLat: aArriveelat, arriveeLng: aArriveeLng,
                dateAller: aDateAller, dateRetour: aDateRetour,
                commentaire: aCommentaire, prix:aPrix, nombrePlace: aNombrePlace,
                conducteur: aConducteur, vehicule: aVehicule)

        when: "on essaye de valider un trajet"
        trajet.validate()

        then: "le trajet est invalide"
        trajet.hasErrors() == true

        where:
        aDepart    | aArrivee | aDepartLat   | aDepartLng  | aArriveelat  | aArriveeLng | aDateAller | aDateRetour | aCommentaire  | aPrix | aNombrePlace | aConducteur       | aVehicule
        null       | "Muret"  | "43.6008029" | "1.3628011" | "43.4408242" | "1.2286806" | new Date() | new Date()  | "Commentaire" | 13.4  | 4            | Mock(Utilisateur) | Mock(Vehicule)
        "Toulouse" | null     | "43.6008029" | "1.3628011" | "43.4408242" | "1.2286806" | new Date() | new Date()  | "Commentaire" | 13.4  | 4            | Mock(Utilisateur) | Mock(Vehicule)
        "Toulouse" | "Muret"  | "43.6008029" | "1.3628011" | "43.4408242" | "1.2286806" | null       | new Date()  | "Commentaire" | 13.4  | 4            | Mock(Utilisateur) | Mock(Vehicule)
        "Toulouse" | "Muret"  | "43.6008029" | "1.3628011" | "43.4408242" | "1.2286806" | new Date() | null        | "Commentaire" | 13.4  | 4            | Mock(Utilisateur) | Mock(Vehicule)
        "Toulouse" | "Muret"  | "43.6008029" | "1.3628011" | "43.4408242" | "1.2286806" | new Date() | new Date()  | "Commentaire" | null  | 4            | Mock(Utilisateur) | Mock(Vehicule)
        "Toulouse" | "Muret"  | "43.6008029" | "1.3628011" | "43.4408242" | "1.2286806" | new Date() | new Date()  | "Commentaire" | 13.4  | null         | Mock(Utilisateur) | Mock(Vehicule)
        "Toulouse" | "Muret"  | "43.6008029" | "1.3628011" | "43.4408242" | "1.2286806" | new Date() | new Date()  | "Commentaire" | 13.4  | 0            | Mock(Utilisateur) | Mock(Vehicule)
        "Toulouse" | "Muret"  | "43.6008029" | "1.3628011" | "43.4408242" | "1.2286806" | new Date() | new Date()  | "Commentaire" | 13.4  | 4            | null              | Mock(Vehicule)
        "Toulouse" | "Muret"  | "43.6008029" | "1.3628011" | "43.4408242" | "1.2286806" | new Date() | new Date()  | "Commentaire" | 13.4  | 4            | Mock(Utilisateur) | null


    }

}
