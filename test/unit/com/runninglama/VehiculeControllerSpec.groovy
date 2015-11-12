package com.runninglama


import grails.test.mixin.*
import grails.util.GrailsWebUtil
import org.springframework.http.HttpStatus
import spock.lang.*

import javax.servlet.http.HttpSession

@TestFor(VehiculeController)
@Mock([Vehicule, Utilisateur])
class VehiculeControllerSpec extends Specification {

    def utilisateur

    def setup() {
        controller.vehiculeService = Mock(VehiculeService)
        controller.trajetService = Mock(TrajetService)

        // On force l'utilisateur dans la session car le filtre de sécurité fait le controle en intégration
        utilisateur = TestsHelper.creeUtilisateurValide()
        request.session['utilisateur'] = utilisateur
    }

    def cleanup() {
        request.session['utilisateur'] = null
    }




    //
    //  INDEX
    //


    void "Test the index action returns the correct model"() {

        when: "on appelle l'action index"
        controller.index()

        then: "le modèle retourné est correct"
        1 * controller.vehiculeService.recupererListVehicule(_ as Utilisateur, [max: 10]) >> []
        !model.vehiculeInstanceList
        1 * controller.vehiculeService.getNombreVehicules(_ as Utilisateur, []) >> 0
        model.vehiculeInstanceCount == 0

        when: "on appelle l'action index avec une limite de résultats"
        controller.index(20)

        then: "le modèle retourné est correct"
        1 * controller.vehiculeService.recupererListVehicule(_ as Utilisateur, [max: 20]) >> []
        !model.vehiculeInstanceList
        1 * controller.vehiculeService.getNombreVehicules(_ as Utilisateur, []) >> 0
        model.vehiculeInstanceCount == 0
    }




    //
    //  CREATE
    //


    void "teste que la création d'une instance du modèle retourne un Vehicule valide"() {

        when: "The create action is executed"
        controller.create()

        then: "The model is correctly created"
        1 * controller.vehiculeService.creeVehicule(_) >> {
            params -> TestsHelper.creeVehiculeValide(params[0]['possesseur'])
        }
        model.vehiculeInstance != null
    }



    //
    //  SAVE
    //


    void "teste que l'action save retourne bien une erreur si l'instance est null (mode formulaire)"() {
        when: "l'action show est appelée avec un objet null"
        request.contentType = FORM_CONTENT_TYPE
        controller.save(null)

        then: "une erreur 404 est retournée"
        response.redirectedUrl == '/vehicule/index'
        flash.message != null
    }

    void "teste que l'action save retourne bien une erreur si l'instance est null (mode non formulaire)"() {
        when: "l'action show est appelée avec un objet null"
        controller.save(null)

        then: "une erreur 404 est retournée"
        response.status == HttpStatus.NOT_FOUND.value()
        flash.message == null
    }

    void "teste que l'action save enregistre bien l'instance qu'on lui passe"() {
        when: "The save action is executed with a valid instance"
        def vehicule = TestsHelper.creeVehiculeValide(controller.session.getAttribute("utilisateur"))

        controller.save(vehicule)

        then: "A redirect is issued to the index"
        1 * controller.vehiculeService.creeOuModifierVehicule(_ as Vehicule) >> { Vehicule v ->
            Mock(Vehicule)
        }
        response.redirectedUrl == '/vehicule/index'
        flash.message == null
    }

    void "teste que l'action save redirige bien vers la page de creation en cas d'instance invalide"() {
        when: "The save action is executed with an invalid instance"
        def vehicule = new Vehicule(possesseur: TestsHelper.creeUtilisateurValide(), marque: "Telsa", modele: "Model X", annee: new Date(year: 2015),
                kilometrage: 10000, type: TypeVehicule.VOITURE);

        controller.save(vehicule)

        then: "A redirect is issued to the index"
        view == 'create'
    }



    //
    //  SHOW
    //


    void "teste que l'action show retourne bien une erreur si l'instance est null (mode formulaire)"() {
        when: "l'action show est appelée avec un objet null"
        request.contentType = FORM_CONTENT_TYPE
        controller.show(null)

        then: "une erreur 404 est retournée"
        response.redirectedUrl == '/vehicule/index'
        flash.message != null
    }

    void "teste que l'action show retourne bien une erreur si l'instance est null (mode non formulaire)"() {
        when: "l'action show est appelée avec un objet null"
        controller.show(null)

        then: "une erreur 404 est retournée"
        response.status == HttpStatus.NOT_FOUND.value()
        flash.message == null
    }

    void "teste que l'action show retourne bien le bon modèle"() {
        when: "une instance du domaine est passée à l'action show"
        response.reset()
        def vehicule = TestsHelper.creeVehiculeValide(utilisateur)
        controller.show(vehicule)

        then: "A model is populated containing the domain instance"
        model.vehiculeInstance == vehicule
    }

    void "teste que si un utilisateur n'a pas le droit de show un véhicule il reçoit une erreur (mode formulaire)"() {
        given: "un véhicule"
        def vehicule = Mock(Vehicule)

        when: "l'utilisateur sans vehicule veut afficher le vehicule de l'autre utilisateur"
        controller.show(vehicule)

        then: "l'utilisateur est redirigé sur un page d'erreur"
        1 * controller.vehiculeService.vehiculeAppartientUtilisateur(_ as Utilisateur, _ as Vehicule) >> { Utilisateur u, Vehicule v -> false }
        response.status == HttpStatus.FORBIDDEN.value()
    }

    void "teste que si un utilisateur n'a pas le droit de show un véhicule il reçoit une erreur (mode non formulaire)"() {
        given: "un véhicule"
        def vehicule = Mock(Vehicule)

        when: "l'utilisateur sans vehicule veut afficher le vehicule de l'autre utilisateur"
        request.contentType = FORM_CONTENT_TYPE
        controller.show(vehicule)

        then: "l'utilisateur est redirigé sur un page d'erreur"
        1 * controller.vehiculeService.vehiculeAppartientUtilisateur(_ as Utilisateur, _ as Vehicule) >> { Utilisateur u, Vehicule v -> false }
        response.redirectedUrl == '/vehicule/index'
        flash.message != null
    }



    //
    //   EDIT
    //

    void "teste que l'action edit retourne bien une erreur si l'instance est null (mode formulaire)"() {
        when: "l'action edit est appelée avec un objet null"
        request.contentType = FORM_CONTENT_TYPE
        controller.edit(null)

        then: "une erreur 404 est retournée"
        response.redirectedUrl == '/vehicule/index'
        flash.message != null
    }

    void "teste que l'action edit retourne bien une erreur si l'instance est null (mode non formulaire)"() {
        when: "l'action edit est appelée avec un objet null"
        controller.edit(null)

        then: "une erreur 404 est retournée"
        response.status == HttpStatus.NOT_FOUND.value()
        flash.message == null
    }

    void "teste que l'action edit retourne bien le bon modèle"() {

        when: "une instance du domain est passée à la méthode edit"
        response.reset()
        def vehicule = TestsHelper.creeVehiculeValide(utilisateur)
        controller.edit(vehicule)

        then: "l'objet est bien ajouté au modèle"
        model.vehiculeInstance == vehicule
    }

    void "teste que si un utilisateur n'a pas le droit de edit un véhicule il reçoit une erreur (mode formulaire)"() {
        given: "un véhicule"
        def vehicule = Mock(Vehicule)

        when: "l'utilisateur sans vehicule veut edit le vehicule de l'autre utilisateur"
        controller.edit(vehicule)

        then: "l'utilisateur est redirigé sur un page d'erreur"
        1 * controller.vehiculeService.vehiculeAppartientUtilisateur(_ as Utilisateur, _ as Vehicule) >> { Utilisateur u, Vehicule v -> false }
        response.status == HttpStatus.FORBIDDEN.value()
    }

    void "teste que si un utilisateur n'a pas le droit de edit un véhicule il reçoit une erreur (mode non formulaire)"() {
        given: "un véhicule"
        def vehicule = Mock(Vehicule)

        when: "l'utilisateur sans vehicule veut edit le vehicule de l'autre utilisateur"
        request.contentType = FORM_CONTENT_TYPE
        controller.edit(vehicule)

        then: "l'utilisateur est redirigé sur un page d'erreur"
        1 * controller.vehiculeService.vehiculeAppartientUtilisateur(_ as Utilisateur, _ as Vehicule) >> { Utilisateur u, Vehicule v -> false }
        response.redirectedUrl == '/vehicule/index'
        flash.message != null
    }



    //
    //  UPDATE
    //

    void "teste que l'action update retourne bien une erreur si l'instance est null (mode formulaire)"() {
        when: "l'action update est appelée avec un objet null"
        request.contentType = FORM_CONTENT_TYPE
        controller.update(null)

        then: "une erreur 404 est retournée"
        response.redirectedUrl == '/vehicule/index'
        flash.message != null
    }

    void "teste que l'action update retourne bien une erreur si l'instance est null (mode non formulaire)"() {
        when: "l'action update est appelée avec un objet null"
        controller.update(null)

        then: "une erreur 404 est retournée"
        response.status == HttpStatus.NOT_FOUND.value()
        flash.message == null
    }


    void "teste que l'action update enregistre bien les modification faites au modèle"() {
        when: "une instance invalide est passée à la méthode update"
        response.reset()
        def vehicule = new Vehicule()
        assertFalse(vehicule.validate())
        controller.update(vehicule)

        then: "la vue d'édition est à nouveau affichée avec l'instance invalide"
        0 * controller.vehiculeService.creeOuModifierVehicule(_ as Vehicule) >> { Vehicule v -> v }
        view == 'edit'
        model.vehiculeInstance == vehicule

        when: "une instance valide du domaine est passée à l'action update"
        response.reset()
        vehicule = TestsHelper.creeVehiculeValide(utilisateur).save(flush: true)
        controller.update(vehicule)

        then: "une redirection vers l'index est retournée"
        1 * controller.vehiculeService.creeOuModifierVehicule(_ as Vehicule) >> { Vehicule v -> v }
        response.redirectedUrl == "/vehicule/index"
    }

    void "teste que si un utilisateur n'a pas le droit de update un véhicule il reçoit une erreur (mode formulaire)"() {
        given: "un véhicule"
        def vehicule = Mock(Vehicule)

        when: "l'utilisateur sans vehicule veut modifier le vehicule de l'autre utilisateur"
        controller.update(vehicule)

        then: "l'utilisateur est redirigé sur un page d'erreur"
        1 * controller.vehiculeService.vehiculeAppartientUtilisateur(_ as Utilisateur, _ as Vehicule) >> { Utilisateur u, Vehicule v -> false }
        response.status == HttpStatus.FORBIDDEN.value()
    }

    void "teste que si un utilisateur n'a pas le droit de update un véhicule il reçoit une erreur (mode non formulaire)"() {
        given: "un véhicule"
        def vehicule = Mock(Vehicule)

        when: "l'utilisateur sans vehicule veut modifier le vehicule de l'autre utilisateur"
        request.contentType = FORM_CONTENT_TYPE
        controller.update(vehicule)

        then: "l'utilisateur est redirigé sur un page d'erreur"
        1 * controller.vehiculeService.vehiculeAppartientUtilisateur(_ as Utilisateur, _ as Vehicule) >> { Utilisateur u, Vehicule v -> false }
        response.redirectedUrl == '/vehicule/index'
        flash.message != null
    }



    //
    //  DELETE
    //

    void "teste que l'action delete retourne bien une erreur si l'instance est null (mode formulaire)"() {
        when: "l'action supprimer est appelée avec un objet null"
        request.contentType = FORM_CONTENT_TYPE
        controller.delete(null)

        then: "une erreur 404 est retournée"
        response.redirectedUrl == '/vehicule/index'
        flash.message != null
    }

    void "teste que l'action delete retourne bien une erreur si l'instance est null (mode non formulaire)"() {
        when: "l'action supprimer est appelée avec un objet null"
        controller.delete(null)

        then: "une erreur 404 est retournée"
        response.status == HttpStatus.NOT_FOUND.value()
        flash.message == null
    }

    void "teste que l'action delete efface bien un véhicule qui existe"() {
        given: "une instance de domaine valide"
        response.reset()
        def vehicule = TestsHelper.creeVehiculeValide(utilisateur).save(flush: true)

        when: "l'instance est passée à supprimer"
        controller.trajetService.countbyVehicule(_) >> 0
        controller.delete(vehicule)

        then: "l'instance est supprimée"
        1 * controller.vehiculeService.supprimerVehicule(_ as Vehicule) >> { Vehicule v ->
            v.delete(flush: true)
        }

        response.redirectedUrl == '/vehicule/index'
    }


    void "teste que si un utilisateur n'a pas le droit de delete un véhicule il reçoit une erreur (mode formulaire)"() {
        given: "un véhicule"
        def vehicule = Mock(Vehicule)

        when: "l'utilisateur sans vehicule veut supprimer le vehicule de l'autre utilisateur"
        controller.trajetService.countbyVehicule(_) >> 0
        controller.delete(vehicule)

        then: "l'utilisateur est redirigé sur un page d'erreur"
        1 * controller.vehiculeService.vehiculeAppartientUtilisateur(_ as Utilisateur, _ as Vehicule) >> { Utilisateur u, Vehicule v -> false }
        response.status == HttpStatus.FORBIDDEN.value()
    }

    void "teste que si un utilisateur n'a pas le droit de delete un véhicule il reçoit une erreur (mode non formulaire)"() {
        given: "un véhicule"
        def vehicule = Mock(Vehicule)

        when: "l'utilisateur sans vehicule veut supprimer le vehicule de l'autre utilisateur"
        request.contentType = FORM_CONTENT_TYPE
        controller.trajetService.countbyVehicule(_) >> 0
        controller.delete(vehicule)

        then: "l'utilisateur est redirigé sur un page d'erreur"
        1 * controller.vehiculeService.vehiculeAppartientUtilisateur(_ as Utilisateur, _ as Vehicule) >> { Utilisateur u, Vehicule v -> false }
        response.redirectedUrl == '/vehicule/index'
        flash.message != null
    }

    void "teste la supression d'un véhicule impliqué dans un trajet"() {
        given: "un véhicule"
        def vehicule = Mock(Vehicule)

        when: "l'utilisateur sans vehicule veut supprimer le vehicule"
        request.contentType = FORM_CONTENT_TYPE
        controller.trajetService.countbyVehicule(_) >> 1
        controller.delete(vehicule)

        then: "l'utilisateur est redirigé sur un page d'erreur"
        response.redirectedUrl == '/vehicule/index'
    }

    void"test la suppression d'un vehicule utilisée dans un trajet"() {
        given: "un véhicule"
        def vehicule = Mock(Vehicule)

        when: "l'utilisateur sans vehicule veut supprimer le vehicule"
        request.contentType = FORM_CONTENT_TYPE
        controller.trajetService.countbyVehicule(_) >> null
        controller.delete(vehicule)

        then: "l'utilisateur est redirigé sur un page d'erreur"
        response.redirectedUrl == '/vehicule/index'
    }



    //
    //  ERROR TESTING (quand le logiciel part en c******)
    //

    void "teste ce qu'il se passe lorsqu'on appelle des méthodes avec des services null"() {
        given: "un contexte ou le vehiculeService n'est pas injecté dans le code"
        controller.vehiculeService = null

        when: "on appelle show"
        controller.show(Mock(Vehicule))
        then: "une NPE est levée"
        thrown NullPointerException

        when: "on appelle edit"
        controller.edit(Mock(Vehicule))
        then: "une NPE est levée"
        thrown NullPointerException

        when: "on appelle update"
        controller.update(Mock(Vehicule))
        then: "une NPE est levée"
        thrown NullPointerException

        when: "on appelle delete"
        controller.delete(Mock(Vehicule))
        then: "une NPE est levée"
        thrown NullPointerException
    }


}
