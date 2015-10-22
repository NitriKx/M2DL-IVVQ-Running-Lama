package com.runninglama

import grails.test.mixin.Mock
import grails.test.mixin.TestFor
import spock.lang.Specification

/**
 * See the API for {@link grails.test.mixin.services.ServiceUnitTestMixin} for usage instructions
 */
@TestFor(VehiculeService)
@Mock([Vehicule, Utilisateur])
class VehiculeServiceSpec extends Specification {

    def setup() {
        service.vehiculeDAOService = Mock(VehiculeDAOService)
    }

    def cleanup() {
    }

    void "teste l'ajout d'un véhicule"() {

        given: "un nouveau véhicule à insérer par un utilisateur connecté"
        Vehicule vehicule = Mock(Vehicule)
        def utilisateur = TestsHelper.creeUtilisateurValide()
        vehicule.possesseur >> utilisateur
        when: "on insère un nouveau véhicule"
        vehicule = service.creeOuModifierVehicule(vehicule)

        then: "le véhicule est bien ajouté"
        1 * service.vehiculeDAOService.save(_ as Vehicule) >> { Vehicule v -> v }
        assertNotNull(vehicule)
    }

    void "teste que lorsqu'on appelle recupererListVehicule, la bonne couche de DAO est appelée"() {
        given: "un utilisateur"
        def utilisateur = Mock(Utilisateur)

        when: "on appelle le service recupererListVehicule"
        def listeVehicules = service.recupererListVehicule(utilisateur, [])

        then: "la bonne couche de DAO est appelée"
        assertNotNull(listeVehicules)
        1 * service.vehiculeDAOService.list(_ as Utilisateur, []) >> { [] }
    }

    void "teste que lorsqu'on appelle getNombreVehicule, la bonne couche de DAO est appelée"() {
        given: "un utilisateur"
        def utilisateur = Mock(Utilisateur)

        when: "on appelle le service getNombreVehicule"
        def nombreVehicule = service.getNombreVehicules(utilisateur, [])

        then: "la bonne couche de DAO est appelée"
        assertNotNull(nombreVehicule)
        1 * service.vehiculeDAOService.count(_ as Utilisateur, []) >> { 0 }

    }

    void "teste la création d'un véhicule"() {

        when: "on créer un nouveau véhicule avec un paramètre"
        def vehiculeCree = service.creeVehicule(["modele": "model x"])

        then: "le vechiule crée a bien ce paramètre"
        vehiculeCree.modele == "model x"
    }

    void "teste la suppression d'un véhicule"() {

        given: "un nouveau véhicule à supprimer"
        Vehicule vehicule = Mock(Vehicule)

        when: "on supprimer le véhicule"
        vehicule = service.supprimerVehicule(vehicule)

        then: "le véhicule est bien supprimé"
        assertNotNull(vehicule)
        1 * service.vehiculeDAOService.delete(_ as Vehicule) >> { Vehicule v -> v }

    }

    void "teste que si l'on crée une voiture pour un utilisateur, la voiture lui appartient bien"() {
        given: "un utilisateur qui existe"
        def utilisateur = TestsHelper.creeUtilisateurValide().save(flush: true);

        when: "on lui cree une voiture"
        def voiture = TestsHelper.creeVehiculeValide(utilisateur).save(flush: true);

        then: "l'utilisateur possède bien la voiture"
        service.vehiculeAppartientUtilisateur(utilisateur, voiture);
    }


}
