package com.runninglama


import grails.test.mixin.*
import grails.util.GrailsWebUtil
import spock.lang.*

import javax.servlet.http.HttpSession

@TestFor(VehiculeController)
@Mock([Vehicule, Utilisateur])
class VehiculeControllerSpec extends Specification {

    def utilisateur

    def setup() {
        controller.vehiculeService = Mock(VehiculeService)
        utilisateur = TestsHelper.creeUtilisateurValide()
        request.session['utilisateur'] = utilisateur
    }

    void "Test the index action returns the correct model"() {

        when: "on appelle l'action index"
        controller.index()

        then: "le modèle retourné est correct"
        1 * controller.vehiculeService.recupererListVehicule(_ as Utilisateur, [max: 10]) >> []
        !model.vehiculeInstanceList
        1 * controller.vehiculeService.getNombreVehicules(_ as Utilisateur, []) >> 0
        model.vehiculeInstanceCount == 0
    }

    void "teste que la création d'une instance du modèle retourne un Vehicule valide"() {

        when: "The create action is executed"
        controller.create()

        then: "The model is correctly created"
        1 * controller.vehiculeService.creeVehicule(_) >> {
            params -> TestsHelper.creeVehiculeValide(params[0]['possesseur'])
        }
        model.vehiculeInstance != null
    }

    void "teste que l'action save enregistre bien l'instance qu'on lui passe"() {

        given: "un utilisateur authentifé"
        def vehicule = new Vehicule(utilisateur: utilisateur)
        assertFalse(vehicule.validate())

        when: "l'action de sauvegarde est exécutée avec une instance de vehicule invalide"
        request.contentType = FORM_CONTENT_TYPE
        controller.save(vehicule)

        then: "The create view is rendered again with the correct model"
        0 * controller.vehiculeService.creeOuModifierVehicule(_ as Vehicule) >> { Vehicule v -> v }
        model.vehiculeInstance != null
        view == 'create'

        when: "The save action is executed with a valid instance"
        response.reset()
        vehicule = TestsHelper.creeVehiculeValide(controller.session.getAttribute("utilisateur"))

        controller.save(vehicule)

        then: "A redirect is issued to the index"
        1 * controller.vehiculeService.creeOuModifierVehicule(_ as Vehicule) >> { Vehicule v ->
            v.save(flush: true)
        }
        response.redirectedUrl == '/vehicule/index'
        controller.flash.message == null
        Vehicule.count() == 1
    }

    void "teste que l'action show retourne bien le bon modèle"() {
        when: "l'action show est exécutée une un véhicule null"
        controller.show(null)

        then: "une erreur 404 est levée"
        response.status == 404

        when: "une instance du domaine est passée à l'action show"
        def vehicule = TestsHelper.creeVehiculeValide(utilisateur)
        controller.show(vehicule)

        then: "A model is populated containing the domain instance"
        model.vehiculeInstance == vehicule
    }

    void "teste que l'action edit retourne bien le bon modèle"() {
        when: "l'action edit est appelée avec un objet null"
        controller.edit(null)

        then: "une erreur 404 est retournée"
        response.status == 404

        when: "une instance du domain est passée à la méthode edit"
        def vehicule = TestsHelper.creeVehiculeValide(utilisateur)
        controller.edit(vehicule)

        then: "l'objet est bien ajouté au modèle"
        model.vehiculeInstance == vehicule
    }

    void "teste que l'action update enregistre bien les modification faites au modèle"() {
        when: "update est appelée avec un object null (qui n'existe pas)"
        request.contentType = FORM_CONTENT_TYPE
        controller.update(null)

        then: "une erreur 404 est retournée"
        response.redirectedUrl == '/vehicule/index'
        flash.message != null


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
        flash.message != null
    }

    void "teste que l'action delete efface bien un véhicule qui existe"() {
        when: "l'action delete est appelée avec un objet null"
        request.contentType = FORM_CONTENT_TYPE
        controller.delete(null)

        then: "une erreur 404 est retournée"
        response.redirectedUrl == '/vehicule/index'
        flash.message != null

        when: "une instance de domaine valide"
        response.reset()
        def vehicule = TestsHelper.creeVehiculeValide(utilisateur).save(flush: true)

        then: "qui est sauvegardée"
        Vehicule.count() == 1

        when: "l'instance est passée à delete"
        controller.delete(vehicule)

        then: "l'instance est supprimée"
        1 * controller.vehiculeService.supprimerVehicule(_ as Vehicule) >> { Vehicule v ->
            v.delete(flush: true)
        }

        Vehicule.count() == 0
        response.redirectedUrl == '/vehicule/index'
        flash.message != null
    }
}
