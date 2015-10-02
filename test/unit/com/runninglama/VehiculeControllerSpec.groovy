package com.runninglama


import grails.test.mixin.*
import spock.lang.*

@TestFor(VehiculeController)
@Mock(Vehicule)
class VehiculeControllerSpec extends Specification {

    def populateValidParams(params) {
        params["nb_place"] = 3
        params["annee"] = new Date(10, 9, 2015)
        params["kilometrage"] = 5000
        params["marque"] = 'Mitsubishi'
        params["modele"] = 'IMiev'
        params["type"] = TypeVehicule.VOITURE
        assert params != null
    }

    def setup() {
        controller.vehiculeService = Mock(VehiculeService)
    }



    void "Test the index action returns the correct model"() {

        when: "The index action is executed"
        controller.index()

        then: "The model is correct"
        1 * controller.vehiculeService.recupererListVehicule(_) >> []
        !model.vehiculeInstanceList
        1 * controller.vehiculeService.getNombreVehicules() >> 0
        model.vehiculeInstanceCount == 0
    }

    void "Test the create action returns the correct model"() {
        when: "The create action is executed"
        controller.create()

        then: "The model is correctly created"
        1 * controller.vehiculeService.creeVehicule() >> new Vehicule()
        model.vehiculeInstance != null
    }

    void "Test the save action correctly persists an instance"() {

        when: "The save action is executed with an invalid instance"
        request.contentType = FORM_CONTENT_TYPE
        def vehicule = new Vehicule()
        vehicule.validate()
        controller.save(vehicule)

        then: "The create view is rendered again with the correct model"
        0 * controller.vehiculeService.creeOuModifierVehicule(_ as Vehicule) >> { Vehicule v -> v }
        model.vehiculeInstance != null
        view == 'create'

        when: "The save action is executed with a valid instance"
        response.reset()
        populateValidParams(params)
        vehicule = new Vehicule(params)

        controller.save(vehicule)

        then: "A redirect is issued to the show action"
        1 * controller.vehiculeService.creeOuModifierVehicule(_ as Vehicule) >> { Vehicule v ->
            v.save(flush: true)
        }
        response.redirectedUrl == '/vehicule/show/1'
        controller.flash.message != null
        Vehicule.count() == 1
    }

    void "Test that the show action returns the correct model"() {
        when: "The show action is executed with a null domain"
        controller.show(null)

        then: "A 404 error is returned"
        response.status == 404

        when: "A domain instance is passed to the show action"
        populateValidParams(params)
        def vehicule = new Vehicule(params)
        controller.show(vehicule)

        then: "A model is populated containing the domain instance"
        model.vehiculeInstance == vehicule
    }

    void "Test that the edit action returns the correct model"() {
        when: "The edit action is executed with a null domain"
        controller.edit(null)

        then: "A 404 error is returned"
        response.status == 404

        when: "A domain instance is passed to the edit action"
        populateValidParams(params)
        def vehicule = new Vehicule(params)
        controller.edit(vehicule)

        then: "A model is populated containing the domain instance"
        model.vehiculeInstance == vehicule
    }

    void "Test the update action performs an update on a valid domain instance"() {
        when: "Update is called for a domain instance that doesn't exist"
        request.contentType = FORM_CONTENT_TYPE
        controller.update(null)

        then: "A 404 error is returned"
        response.redirectedUrl == '/vehicule/index'
        flash.message != null


        when: "An invalid domain instance is passed to the update action"
        response.reset()
        def vehicule = new Vehicule()
        vehicule.validate()
        controller.update(vehicule)

        then: "The edit view is rendered again with the invalid instance"
        0 * controller.vehiculeService.creeOuModifierVehicule(_ as Vehicule) >> { Vehicule v -> v }
        view == 'edit'
        model.vehiculeInstance == vehicule

        when: "A valid domain instance is passed to the update action"
        response.reset()
        populateValidParams(params)
        vehicule = new Vehicule(params).save(flush: true)
        controller.update(vehicule)

        then: "A redirect is issues to the show action"
        1 * controller.vehiculeService.creeOuModifierVehicule(_ as Vehicule) >> { Vehicule v -> v }
        response.redirectedUrl == "/vehicule/show/$vehicule.id"
        flash.message != null
    }

    void "Test that the delete action deletes an instance if it exists"() {
        when: "The delete action is called for a null instance"
        request.contentType = FORM_CONTENT_TYPE
        controller.vehiculeService = Mock(VehiculeService)
        controller.delete(null)

        then: "A 404 is returned"
        response.redirectedUrl == '/vehicule/index'
        flash.message != null

        when: "A domain instance is created"
        response.reset()
        populateValidParams(params)
        def vehicule = new Vehicule(params).save(flush: true)

        then: "It exists"
        Vehicule.count() == 1

        when: "The domain instance is passed to the delete action"
        controller.delete(vehicule)

        then: "The instance is deleted"
        1 * controller.vehiculeService.supprimerVehicule(_ as Vehicule) >> { Vehicule v ->
            v.delete(flush: true)
        }

        Vehicule.count() == 0
        response.redirectedUrl == '/vehicule/index'
        flash.message != null
    }
}
