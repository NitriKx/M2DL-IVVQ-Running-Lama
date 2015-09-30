package com.runninglama

import grails.test.mixin.TestFor
import spock.lang.Specification
import spock.lang.Unroll

/**
 * See the API for {@link grails.test.mixin.domain.DomainClassUnitTestMixin} for usage instructions
 */
@TestFor(Vehicule)
class VehiculeSpec extends Specification {
    Vehicule vehicule

    def setup() {
        vehicule = new Vehicule()
    }

    def cleanup() {
    }

    @Unroll
    void "test vehicule invalide sans contraintes"() {
        given: "Vehicule avec  attributs sans contraintes"
        vehicule.annee = annee
        vehicule.nb_place = nbPlace
        vehicule.kilometrage = kilometrage
        vehicule.marque = marque
        vehicule.modele = modele
        vehicule.type = type

        when: "Les attributs sont invalides"
        def estValide = vehicule.validate()

        then: "Le vehicule est invalide"
        estValide == false

        where:
        annee                 | nbPlace | kilometrage | marque   | modele  | type
        null                  | 0       | -10         | null     | null    | null
        new Date(2015, 9, -1) | -1      | -1          | ""       | ""      | TypeVehicule.MOTO
        new Date(2015, 9, 10) | 0       | 1000        | "Audi"   | "ETron" | TypeVehicule.VOITURE
        new Date(2015, 9, 10) | 10      | 1000        | "Nissan" | ""      | TypeVehicule.VOITURE
    }

    @Unroll
    void "test vehicule valide"() {
        given: "Vehicule avec  attributs sans contraintes"
        vehicule.annee = annee
        vehicule.nb_place = nbPlace
        vehicule.kilometrage = kilometrage
        vehicule.marque = marque
        vehicule.modele = modele
        vehicule.type = type

        when: "Les contraintes sont respectées"
        def estValide = vehicule.validate()

        then: "Le vehicule est invalide"
        estValide == true

        where:
        annee                  | nbPlace | kilometrage | marque    | modele | type
        new Date(2015, 10, 10) | 1       | 1000        | "Nissan"  | "Leaf" | TypeVehicule.VOITURE
        new Date(2015, 9, 10)  | 10      | 1000        | "Renault" | "Zoe"  | TypeVehicule.VOITURE
    }

}