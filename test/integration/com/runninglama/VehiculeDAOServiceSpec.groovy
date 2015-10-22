package com.runninglama

import grails.test.mixin.Mock
import grails.test.mixin.TestFor
import spock.lang.Specification

/**
 * See the API for {@link grails.test.mixin.services.ServiceUnitTestMixin} for usage instructions
 */
class VehiculeDAOServiceSpec extends Specification {

    VehiculeDAOService service

    def setup() {
    }

    def cleanup() {
    }

    void "teste que le vehicule est effectivement supprimé de la base de données"() {

        given: "un véhicule en base de données"
        def utilisateur = TestsHelper.creeUtilisateurValide();
        utilisateur = utilisateur.save(flush: true)
        def vehicule = TestsHelper.creeVehiculeValide(utilisateur);
        vehicule = vehicule.save(flush: true)

        when: "on demande la suppression de ce véhicule"
        service.delete(vehicule)

        then: "le véhicule n'existe plus dans la base de données"
        Vehicule.findAllById(vehicule.id).isEmpty()
        Vehicule.count() == 0
    }


    void "teste que le vehicule est effectivement ajouté de la base de données"() {

        given: "un véhicule valide pas en base de données"
        def utilisateur = TestsHelper.creeUtilisateurValide();
        utilisateur = utilisateur.save(flush: true)
        def vehicule = TestsHelper.creeVehiculeValide(utilisateur);
        vehicule = vehicule.save(flush: true)

        when: "on demande l'ajout de ce véhicule"
        def resultatAjout = service.save(vehicule)

        then: "le véhicule existe dans la base de données"
        resultatAjout != null
        Vehicule.findAllById(resultatAjout.id).size() == 1
    }

    void "test que si on ajoute des véhicule dans la base de données, l'opération de listage les retourne"() {

        given: "un véhicule valide dans la base de données"
        def utilisateur = TestsHelper.creeUtilisateurValide();
        utilisateur = utilisateur.save(flush: true)
        def vehicule = TestsHelper.creeVehiculeValide(utilisateur);
        vehicule = vehicule.save(flush: true)

        when: "on liste les vehicules"
        def listeVehicule = service.list(vehicule.possesseur, [:])

        then: "le vehicule est dans la base de données"
        listeVehicule.contains(vehicule)
    }

    void "teste que  lorsqu'on ajoute un véhicule dans la base de données, le compteur de véhicule est incrémenté"() {

        given: "une base de données sans véhicule et un véhicule valide"
        def utilisateur = TestsHelper.creeUtilisateurValide();
        utilisateur = utilisateur.save(flush: true)
        def vehicule = TestsHelper.creeVehiculeValide(utilisateur);
        Vehicule.count() == 0
        vehicule = vehicule.save(flush: true)

        when: "on demande l'ajout de ce véhicule"
        def resultatAjout = service.save(vehicule)

        then: "la base de données contient maintenant un véhicule"
        resultatAjout != null
        service.count(resultatAjout.possesseur, [:]) == 1
    }
}
