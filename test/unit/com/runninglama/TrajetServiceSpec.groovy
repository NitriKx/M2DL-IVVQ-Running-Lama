package com.runninglama

import grails.test.mixin.Mock
import grails.test.mixin.TestFor
import spock.lang.Specification

/**
 * See the API for {@link grails.test.mixin.services.ServiceUnitTestMixin} for usage instructions
 */
@TestFor(TrajetService)
@Mock(Trajet)
class TrajetServiceSpec extends Specification {

    def setup() {
        service.trajetDAOService = Mock(TrajetDAOService)
    }

    def cleanup() {
    }

    void "test la suppression d'un trajet"() {
        given: "un trajet sauvegarder en base de données"
        Trajet trajet = Mock(Trajet)

        when:"le controlleur demande la suppression du trajet"
        service.delete(trajet)

        then: "la couche DAO est appelé"
        1*service.trajetDAOService.delete(_)
    }

    void "test la creation d'un trajet"() {
        given: "un trajet a sauvegarder"
        Trajet trajet = Mock(Trajet)

        when:"le controlleur demande la suppression du trajet"
        service.ajouterTrajet(trajet)

        then: "la couche DAO est appelé"
        1*service.trajetDAOService.save(_)
    }
}
