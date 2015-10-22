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

    def creeUtilisateurValide() {
        return TestsHelper.creeUtilisateurValide()
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
        vehicule.possesseur = possesseur

        when: "Les attributs sont invalides"
        def estValide = vehicule.validate()

        then: "Le vehicule est invalide"
        estValide == false

        where:
        possesseur                | annee                 | nbPlace | kilometrage | marque   | modele  | type
        null                      | null                  | 0       | -10         | null     | null    | null
        null                      | new Date(2015, 9, 10) | 0       | 1000        | "Audi"   | "ETron" | TypeVehicule.VOITURE
        creeUtilisateurValide()   | new Date(2015, 9, -1) | -1      | -1          | ""       | ""      | TypeVehicule.MOTO
        creeUtilisateurValide()   | new Date(2015, 9, 10) | 0       | 1000        | "Audi"   | "ETron" | TypeVehicule.VOITURE
        creeUtilisateurValide()   | new Date(2015, 9, 10) | 10      | 1000        | "Nissan" | ""      | TypeVehicule.VOITURE
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
        vehicule.possesseur = possesseur

        when: "Les contraintes sont respectées"
        def estValide = vehicule.validate()

        then: "Le vehicule est invalide"
        estValide == true

        where:
        possesseur                | annee                  | nbPlace | kilometrage | marque    | modele | type
        creeUtilisateurValide()   | new Date(2015, 10, 10) | 1       | 1000        | "Nissan"  | "Leaf" | TypeVehicule.VOITURE
        creeUtilisateurValide()   | new Date(2015, 9, 10)  | 10      | 1000        | "Renault" | "Zoe"  | TypeVehicule.VOITURE
    }

    void "test le toString haha"() {
        given: "Un véhicule"
        Vehicule vehicule = new Vehicule(possesseur: creeUtilisateurValide(), annee: 2005, nb_place: 1, kilometrage: 1000, marque: "Peugeot", modele: "106", type: TypeVehicule.VOITURE)
        when: "toString est appelé"
        String toString = vehicule.toString();
        then:
        toString == vehicule.marque+" - "+vehicule.modele+ " - "+ vehicule.kilometrage
    }

}