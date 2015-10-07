package com.runninglama

import grails.test.mixin.Mock
import grails.test.mixin.TestFor
import spock.lang.Specification

/**
 * See the API for {@link grails.test.mixin.services.ServiceUnitTestMixin} for usage instructions
 */
@TestFor(VehiculeService)
@Mock(Vehicule)
class VehiculeServiceSpec extends Specification {

    def setup() {
        service.vehiculeDAOService = Mock(VehiculeDAOService)
    }

    def cleanup() {
    }

    void "teste l'ajout d'un véhicule"() {

        given: "un nouveau véhicule à insérer"
        Vehicule vehicule = Mock(Vehicule)

        when: "on insère un nouveau véhicule"
        vehicule = service.creeOuModifierVehicule(vehicule)

        then: "le véhicule est bien ajouté"
        1 * service.vehiculeDAOService.save(_ as Vehicule) >> { Vehicule v -> v }
        assertNotNull(vehicule)

    }

    void "teste que lorsqu'on appelle getNombreVehicule, la bonne couche de DAO est appelée"() {

        when: "on appelle le service getNombreVehicule"
        def nombreVehicule = service.getNombreVehicules()

        then: "la bonne couche de DAO est appelée"
        assertNotNull(nombreVehicule)
        1 * service.vehiculeDAOService.count(_) >> { 0 }

    }

    void "teste la création d'un véhicule"() {

        when: "on créer un nouveau véhicule avec un paramètre"
        def vehiculeCree = service.creeVehicule(["modele": "model x"])

        then: "le vechiule crée a bien ce paramètre"
        vehiculeCree.modele == "model x"
    }

    void "teste la suppression d'un véhicule"() {

        given: "un nouveau véhicule à supprimer"
        Vehicule vehicule = Mock(Vehicule)

        when: "on supprimer le véhicule"
        vehicule = service.supprimerVehicule(vehicule)

        then: "le véhicule est bien supprimé"
        assertNotNull(vehicule)
        1 * service.vehiculeDAOService.delete(_ as Vehicule) >> { Vehicule v -> v }

    }
}
