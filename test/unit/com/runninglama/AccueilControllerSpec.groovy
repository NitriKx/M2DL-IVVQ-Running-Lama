package com.runninglama

import grails.test.mixin.Mock
import grails.test.mixin.TestFor
import grails.test.mixin.TestMixin
import grails.test.mixin.support.GrailsUnitTestMixin
import spock.lang.Specification

/**
 * See the API for {@link grails.test.mixin.support.GrailsUnitTestMixin} for usage instructions
 */
@TestFor(AccueilController)
@Mock([Trajet, Utilisateur])
class AccueilControllerSpec extends Specification {

    def setup() {
        controller.utilisateurService = Mock(UtilisateurService)
        controller.trajetService = Mock(TrajetService)
    }

    def cleanup() {
    }

    def "teste que la page d'acceuil s'affiche bien"() {
        when: "la page d'acceuil est chargée"
        controller.index()

        then: "la réponse contient les derniers utilisateurs"
        1 * controller.utilisateurService.listeUtilisateurs(_) >> { it -> [] }
        model.lesDerniersUtilisateurs != null

        then: "la réponse contient les derniers trajets"
        1 * controller.trajetService.listeTrajets(_) >> { it -> [] }
        model.lesTrajets != null

        then: "la réponse content un booléen qui indique que l'on est à l'acceuil"
        model.isAccueil
    }


}
