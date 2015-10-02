package com.runninglama

import grails.test.mixin.Mock
import grails.test.mixin.TestFor
import spock.lang.Specification

/**
 * See the API for {@link grails.test.mixin.services.ServiceUnitTestMixin} for usage instructions
 */
@TestFor(VehiculeDAOService)
@Mock(Vehicule)
class VehiculeDAOServiceSpec extends Specification {

    def setup() {
    }

    def cleanup() {
    }

    def creerVehiculeValide() {
        new Vehicule(marque: "Telsa", modele: "Model X", annee: new Date(year: 2015), nb_place: 5,
                kilometrage: 10000, type: TypeVehicule.VOITURE);
    }

    void "teste que le vehicule est effectivement supprimé de la base de données"() {

        given: "un véhicule en base de données"
        def vehicule = creerVehiculeValide();
        vehicule.save(flush: true)

        when: "on demande la suppression de ce véhicule"
        service.delete(vehicule)

        then: "le véhicule n'existe plus dans la base de données"
        Vehicule.findAllById(vehicule.id).isEmpty()
    }


    void "teste que le vehicule est effectivement ajouté de la base de données"() {

        given: "un véhicule valide pas en base de données"
        def vehicule = creerVehiculeValide();
        assertTrue(vehicule.validate())

        when: "on demande l'ajout de ce véhicule"
        def resultatAjout = service.save(vehicule)

        then: "le véhicule existe dans la base de données"
        assertNotNull(resultatAjout)
        Vehicule.findAllById(resultatAjout.id).size() == 1
    }

    void "test que si on ajoute des véhicule dans la base de données, l'opération de listage les retourne"() {

        given: "un véhicule valide dans la base de données"
        def vehicule = creerVehiculeValide();
        service.save(vehicule)

        when: "on liste les vehicules"
        def listeVehicule = service.list()

        then: "le vehicule est dans la base de données"
        listeVehicule.contains(vehicule)
    }
}
