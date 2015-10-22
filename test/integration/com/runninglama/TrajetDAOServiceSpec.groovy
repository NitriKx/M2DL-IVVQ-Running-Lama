package com.runninglama

import grails.test.mixin.TestFor
import grails.test.spock.IntegrationSpec
import spock.lang.*

/**
 *
 */
class TrajetDAOServiceSpec extends Specification {

    def trajetDAOService

    def setup() {
    }

    def cleanup() {
    }

    void "teste que la méthode save enregistre bien une instance en base de données"() {
        given: "un trajet valide pas en base de données"
        def utilisateur = TestsHelper.creeUtilisateurValide();
        utilisateur = utilisateur.save(flush: true)
        def vehicule = TestsHelper.creeVehiculeValide(utilisateur);
        vehicule = vehicule.save(flush: true)
        def trajet = TestsHelper.creeTrajetValide(utilisateur, vehicule);
        trajet.save(flush: true)

        when: "on demande l'ajout d'un trajet"
        def resultatAjout = trajetDAOService.save(trajet)

        then: "le trajet existe dans la base de données"
        resultatAjout != null
        Trajet.findAllById(resultatAjout.id).size() == 1
    }


}
