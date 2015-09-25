package com.runninglama

import grails.test.mixin.TestFor
import spock.lang.Specification

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

    /*
    void "vehicule valide"() {
        given: "un vehicule de base"

        when:

        then:
    }*/
}
