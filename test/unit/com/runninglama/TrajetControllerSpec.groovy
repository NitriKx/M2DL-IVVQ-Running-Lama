package com.runninglama

import grails.test.GrailsMock
import grails.test.mixin.*
import spock.lang.*

@TestFor(TrajetController)
@Mock(Trajet)
class TrajetControllerSpec extends Specification {

    def populateValidParams(params) {
        assert params != null
        params["depart"] = 'Toulouse'
        params["departLat"] = '123.345'
        params["departLng"] = '1.4583'

        params["arrivee"] = 'Muret'
        params["arriveeLat"] = '123.563'
        params["arriveeLng"] = '2.456'

        params["dateAller"] = new Date()
        params["dateRetour"] = new Date()

        params["commentaire"] = 'Un commentaire'
        params["prix"] = 12.5
        params["nombrePlace"] = 4

        params["conducteur"] = Mock(Utilisateur)
        params["vehicule"] = Mock(Vehicule)

    }

    def setup() {
        controller.trajetService = Mock(TrajetService)
    }


    void "Test l'affichage du formulaire d'ajout de trajet"() {
        when: "une demande d'accès au formulaire d'ajout par un utilisateur connecté"

        def utilisateur = TestsHelper.creeUtilisateurValide()
        request.session['utilisateur'] = utilisateur

        controller.ajouterTrajet()
        then: "l'utilisateur est redirigé sur la page d'inscription"
        view == '/trajet/ajouter'
        response.status == 200
    }

    void "Test the index action returns the correct model"() {

        when: "The index action is executed"
        controller.index()

        then: "The model is correct"
        !model.trajetInstanceList
        model.trajetInstanceCount == 0
    }

    void "Test the create action returns the correct model"() {
        when: "The create action is executed"
        controller.create()

        then: "The model is correctly created"
        model.trajetInstance != null
    }

    void "Test the save action correctly persists an instance"() {

        when: "The save action is executed with an invalid instance"
        request.contentType = FORM_CONTENT_TYPE
        def trajet = new Trajet()
        trajet.validate()
        controller.save(trajet)

        then: "The create view is rendered again with the correct model"
        model.trajetInstance != null
        view == 'create'

        when: "The save action is executed with a valid instance"
        response.reset()
        populateValidParams(params)
        trajet = new Trajet(params)

        controller.save(trajet)

        then: "A redirect is issued to the show action"
        response.redirectedUrl == '/trajet/show/1'
        controller.flash.message != null
        Trajet.count() == 1
    }

    void "Test that the show action returns the correct model"() {
        when: "The show action is executed with a null domain"
        controller.show(null)

        then: "A 404 error is returned"
        response.status == 404

        when: "A domain instance is passed to the show action"
        populateValidParams(params)
        def trajet = new Trajet(params)
        controller.show(trajet)

        then: "A model is populated containing the domain instance"
        model.trajetInstance == trajet
    }

    void "Test that the edit action returns the correct model"() {
        when: "The edit action is executed with a null domain"
        controller.edit(null)

        then: "A 404 error is returned"
        response.status == 404

        when: "A domain instance is passed to the edit action"
        populateValidParams(params)
        def trajet = new Trajet(params)
        controller.edit(trajet)

        then: "A model is populated containing the domain instance"
        model.trajetInstance == trajet
    }

    void "Test the update action performs an update on a valid domain instance"() {
        when: "Update is called for a domain instance that doesn't exist"
        request.contentType = FORM_CONTENT_TYPE
        controller.update(null)

        then: "A 404 error is returned"
        response.redirectedUrl == '/trajet/index'
        flash.message != null


        when: "An invalid domain instance is passed to the update action"
        response.reset()
        def trajet = new Trajet()
        trajet.validate()
        controller.update(trajet)

        then: "The edit view is rendered again with the invalid instance"
        view == 'edit'
        model.trajetInstance == trajet

        when: "A valid domain instance is passed to the update action"
        response.reset()
        populateValidParams(params)
        trajet = new Trajet(params).save(flush: true)
        controller.update(trajet)

        then: "A redirect is issues to the show action"
        response.redirectedUrl == "/trajet/show/$trajet.id"
        flash.message != null
    }

    void "Test that the delete action deletes an instance if it exists"() {
        when: "The delete action is called for a null instance"
        request.contentType = FORM_CONTENT_TYPE
        controller.delete(null)

        then: "A 404 is returned"
        response.redirectedUrl == '/trajet/index'
        flash.message != null

        when: "A domain instance is created"
        response.reset()
        populateValidParams(params)
        def trajet = new Trajet(params).save(flush: true)

        then: "It exists"
        Trajet.count() == 1

        when: "The domain instance is passed to the delete action"
        controller.delete(trajet)

        then: "The instance is deleted"
        Trajet.count() == 0
        response.redirectedUrl == '/trajet/index'
        flash.message != null
    }

    void "Test d'un ajout de trajet avec des champs corrects"() {
        given: "un membre qui veut ajouter un trajet"
        Trajet trajet = new Trajet(params)
        def utilisateur = TestsHelper.creeUtilisateurValide()
        request.session['utilisateur'] = utilisateur
        GroovyMock(Vehicule, global: true)

        when: "il valide le trajet"
        Vehicule.findById(_) >> Mock(Vehicule)
        controller.ajouterTrajetPost(trajet)

        then:"the trajet is created"
        response.redirectedUrl == '/accueil'

    }
}
