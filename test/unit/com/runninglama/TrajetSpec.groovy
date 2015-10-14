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
        util.hasErrors() == false

        Utilisateur utilisateur = Mock(Utilisateur)
        Vehicule vehicule = Mock(Vehicule)

        where:
        aDepart    | aArrivee | aDepartLat   | aDepartLng  | aArriveelat  | aArriveeLng | aDateAller | aDateRetour | aCommentaire  | aPrix | aNombrePlace | aConducteur | aVehicule
        "Toulouse" | "Muret"  | "43.6008029" | "1.3628011" | "43.4408242" | "1.2286806" | new Date() | new Date()  | "Commentaire" | 13.4  | 4            | utilisateur | vehicule
    }

}
