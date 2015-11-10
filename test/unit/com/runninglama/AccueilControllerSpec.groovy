package com.runninglama

import grails.test.mixin.TestFor
import grails.test.mixin.TestMixin
import grails.test.mixin.support.GrailsUnitTestMixin
import spock.lang.Specification

/**
 * See the API for {@link grails.test.mixin.support.GrailsUnitTestMixin} for usage instructions
 */
@TestFor(AccueilController)
class AccueilControllerSpec extends Specification {

    def accueilController

    def setup() {
        accueilController = new AccueilController()
    }

    def cleanup() {
    }
    
}
