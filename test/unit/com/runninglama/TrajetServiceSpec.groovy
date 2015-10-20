package com.runninglama

import grails.test.mixin.Mock
import grails.test.mixin.TestFor
import spock.lang.Specification

/**
 * See the API for {@link grails.test.mixin.services.ServiceUnitTestMixin} for usage instructions
 */
@TestFor(TrajetService)
class TrajetServiceSpec extends Specification {

    def populateValidParams(params) {
        assert params != null
        params["depart_google"] = 'okinawa'
        params["arrivee_google"] = 'osaka'
        params["dateAller"] = new Date(2015, 10, 20)
        params["prixMax"] = '20'
    }

    def setup() {
        service.trajetDAOService = Mock(TrajetDAOService)
    }

    def cleanup() {
    }

    void "teste que lorsqu'on appelle recupererListVehicule, la bonne couche de DAO est appelée"() {

        when: "on appelle le service recupererListVehicule"
        def listeTrajets = service.rechercherTrajet(null)

        then: "la bonne couche de DAO est appelée"
        assertNotNull(listeTrajets)
        1 * service.trajetDAOService.search(null) >> { [] }
    }

}
