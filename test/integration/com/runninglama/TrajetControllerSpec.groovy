package com.runninglama

import grails.test.spock.Integration
import spock.lang.*

/**
 *
 */
@Integration
class TrajetControllerIntegration extends Specification {

    def trajetController

    def setup() {
    }

    def cleanup() {
    }

    void "test l'ajout d'un participant a un trajet"() {
        given:"Un trajet avec un véhicule et un conducteur et un utilisateur qui souhaite s'inscrire sur ce trajet"
        Utilisateur conducteur = TestsHelper.creeUtilisateurValide().save(flush: true)
        Utilisateur participant = TestsHelper.creeUtilisateurValide().save(flush: true)
        Vehicule vehicule = TestsHelper.creeVehiculeValide(conducteur).save(flush: true)
        Trajet trajet = TestsHelper.creeTrajetValide(conducteur, vehicule).save(flush: true)

        when:"l'utilisateur s'inscrit au trajet"
        trajet.participants.add(participant)
        trajet.save(flush: true)

        then:"l'utilisateur est bien inscrit au trajet"
        trajet.participants.size() > 0
        participant.participe(true) == true
    }
}
