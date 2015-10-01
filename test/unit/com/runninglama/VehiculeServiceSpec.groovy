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

    }

    def cleanup() {
    }

    void "teste l'ajout d'un véhicule"() {

        given: "un nouveau véhicule à insérer"
            Vehicule vehicule = Mock(Vehicule)
            VehiculeDAOService vehiculeDAOService = Mock(VehiculeDAOService)
            service.vehiculeDAOService = vehiculeDAOService

        when: "on insère un nouveau véhicule"
            vehicule = service.creeOuModifierVehicule(vehicule)

        then: "le véhicule est bien ajouté"
            1 * vehiculeDAOService.save(vehicule)

    }

    void "teste la suppression d'un véhicule"() {

        given: "un nouveau véhicule à supprimer"
        Vehicule vehicule = Mock(Vehicule)
        VehiculeDAOService vehiculeDAOService = Mock(VehiculeDAOService)
        service.vehiculeDAOService = vehiculeDAOService

        when: "on supprimer le véhicule"
        vehicule = service.supprimerVehicule(vehicule)

        then: "le véhicule est bien supprimé"
        1 * vehiculeDAOService.delete(vehicule)

    }
}
