package com.runninglama

import grails.test.mixin.TestFor
import spock.lang.*

/**
 *
 */
class TrajetControllerSpec extends Specification {

    def trajetController = new TrajetController()

    def setup() {
    }

    def cleanup() {
    }

    void "test l'inscription a un trajet"() {
        given:"Un trajet valide en base de donnée"
        Utilisateur conducteur = TestsHelper.creeUtilisateurValide()
        Vehicule vehicule = TestsHelper.creeVehiculeValide(conducteur)
        Trajet trajet = TestsHelper.creeTrajetValide(conducteur, vehicule)
        conducteur.save(flush: true)
        vehicule.save(flush: true)
        trajet.save(flush: true)

        when:"Un utilisateur veut s'inscrire au trajet"
        Utilisateur utilisateur = TestsHelper.creeUtilisateurValide()
        utilisateur.save(flush: true)
        trajetController.session.utilisateur = utilisateur
        trajetController.ajouterParticipant(trajet.id)

        then:"Le participant est ajoute au trajet"
        utilisateur.participe(trajet)
        trajet.getParticipants().contains(utilisateur)
    }
}
