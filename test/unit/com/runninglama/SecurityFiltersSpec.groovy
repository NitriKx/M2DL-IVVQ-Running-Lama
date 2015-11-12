package com.runninglama

import grails.test.mixin.Mock
import grails.test.mixin.TestFor
import grails.test.mixin.TestMixin
import grails.test.mixin.web.FiltersUnitTestMixin
import spock.lang.Specification

@TestMixin(FiltersUnitTestMixin)
@Mock(SecurityFilters)
@TestFor(VehiculeController)
class SecurityFiltersSpec extends Specification {

    def utilisateurController

    def setup() {
        utilisateurController = new UtilisateurController();
    }

    def cleanup() {
    }

    void "test user is filtered and redirected to connexion index if not logged and try access vehicule creation"() {

        when:
        withFilters(action:"create") {
            controller.index()
        }

        then:
        response.redirectedUrl == '/utilisateur/connexion'

    }

    void "test user is filtered and redirected to connexion index if not logged and try access vehicule edition"() {

        when:
        withFilters(action:"edit") {
            controller.edit()
        }

        then:
        response.redirectedUrl == '/utilisateur/connexion'

    }

    void "test user is filtered and redirected to connexion index if not logged and try access vehicule suppression"() {

        when:
        withFilters(action:"delete") {
            controller.delete()
        }

        then:
        response.redirectedUrl == '/utilisateur/connexion'

    }

    void "test user is filtered and redirected to connexion index if not logged and try access connection"() {
        when:
        withFilters(action:"delete") {
            utilisateurController.connexion()
        }
        then:
        response.redirectedUrl == '/utilisateur/connexion'
    }
}
