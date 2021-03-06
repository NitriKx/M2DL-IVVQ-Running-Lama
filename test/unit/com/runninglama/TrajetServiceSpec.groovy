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

    void "test la suppression d'un trajet"() {
        given: "un trajet sauvegarder en base de données"
        Trajet trajet = Mock(Trajet)

        when:"le controlleur demande la suppression du trajet"
        service.supprimer(trajet)

        then: "la couche DAO est appelé"
        1*service.trajetDAOService.delete(_)
    }

    void "test la creation d'un trajet"() {
        given: "un trajet a sauvegarder"
        Trajet trajet = Mock(Trajet)

        when: "le controlleur demande la suppression du trajet"
        service.ajouterOuModifierTrajet(trajet)

        then: "la couche DAO est appelé"
        1 * service.trajetDAOService.save(_)
    }

    void "teste que lorsqu'on appelle recupererListVehicule, la bonne couche de DAO est appel�e"() {

        when: "on appelle le service recupererListVehicule"
        def listeTrajets = service.rechercherTrajet(null)

        then: "la bonne couche de DAO est appel�e"
        assertNotNull(listeTrajets)
        1 * service.trajetDAOService.search(null) >> { [] }
    }

    void "test la methode trouver trajet"(){
        when: "on appelle le service pour trouver un trajet"
        def trajet = service.trouverTrajet(8L)

        then: "la bonne couche de DAO est appelée"
        1 * service.trajetDAOService.trouver(_)
    }

    void "test la methode liste trajet"(){
        when: "on appelle le service pour trouver un trajet"
        def trajet = service.listeTrajets(_)

        then: "la bonne couche de DAO est appelée"
        1 * service.trajetDAOService.liste(_)
    }

    void "test la methode nombre trajet"(){
        when: "on appelle le service pour trouver un trajet"
        def trajet = service.nombreTrajets()

        then: "la bonne couche de DAO est appelée"
        1 * service.trajetDAOService.count()
    }

    void "test la methode countByVehicule"() {
        when: "On appel le service"
        Vehicule vehicule = TestsHelper.creeVehiculeValide(Mock(Utilisateur))
        service.countbyVehicule(vehicule)

        then:"La bonne couche DAO est appelée"
        1 * service.trajetDAOService.countByVehicule(_)
    }

}
