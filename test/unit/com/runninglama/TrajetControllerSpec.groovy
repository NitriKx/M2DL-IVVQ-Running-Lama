package com.runninglama

import grails.test.mixin.*
import org.springframework.http.HttpStatus
import spock.lang.*

@TestFor(TrajetController)
@Mock([Trajet, Utilisateur, Vehicule])
class TrajetControllerSpec extends Specification {

    def setup() {
        def utilisateur = TestsHelper.creeUtilisateurValide()
        request.session['utilisateur'] = utilisateur
        controller.trajetService = Mock(TrajetService)
    }


    //
    //
    //   INDEX
    //
    //

    void "teste que l'affichage de l'acceuil des trajet reçoit les bonnes données"() {

        when: "The index action is executed"
        controller.index()

        then: "The model is correct"
        1 * controller.trajetService.listeTrajets(_) >> []
        1 * controller.trajetService.nombreTrajets() >> 0
        !model.trajetInstanceList
        model.trajetInstanceCount == 0
    }


    //
    //
    //   CREATE
    //
    //

    void "teste the create action returns the correct model"() {
        when: "The create action is executed"
        controller.create()

        then: "The model is correctly created"
        model.trajetInstance != null
    }


    //
    //
    //  LISTE
    //
    //

    void "teste l'affichage de la liste des trajet"() {
        when: "une demande d'accès a la liste des trajet"
        controller.liste()
        then: "l'utilisateur est redirigé sur la page"
        view == '/trajet/liste'
        response.status == 200
    }


    //
    //
    //  VOIR TRAJET (devrait être un test d'intégration)
    //
    //

    void "teste l'affichage du recapitulatif d'un trajet"() {
        given: "un trajet sauvegarder en base de données"
        Utilisateur utilisateur = request.session['utilisateur']
        utilisateur.save(flush:true)
        Vehicule vehicule = TestsHelper.creeVehiculeValide(utilisateur);
        vehicule.save();
        Trajet trajet = TestsHelper.creeTrajetValide(utilisateur, vehicule)
        trajet.save(flush:true)
        controller.trajetService.trouverTrajet(_) >> trajet
        when: "une demande d'accès au récapitulatif d'un trajet"
        controller.voirTrajet(trajet.id)
        then: "l'utilisateur est redirigé sur la page"
        view == '/trajet/voir'
        response.status == 200
    }

    void "teste l'affichage du recapitulatif d'un trajet qui n'existe pas"() {
        given: "un trajet sauvegarder en base de données"
        Trajet trajet = Mock(Trajet)
        trajet.id >> null

        when: "une demande d'accès au récapitulatif d'un trajet"
        controller.voirTrajet(trajet.id)
        then: "on ramène l'utilisateur sur la page d'ajout"
        view == '/trajet/ajouter'
        response.status == 200
    }





    //
    //
    //   RECHERCHE
    //
    //

    void "teste la recherche de trajet"() {

        when: "on appelle l'action rechercherTrajet"
        controller.rechercherTrajet()

        then: "le modèle retourné est correct"
        response.status == 200
        view == '/trajet/liste'

    }





    //
    //
    //   SUPPRIMER
    //
    //

    void "teste la suppression d'un trajet existant"() {
        given: "un trajet sauvegarder en base de données"
        Utilisateur utilisateur = request.session['utilisateur']
        utilisateur.save(flush:true)
        Vehicule vehicule = TestsHelper.creeVehiculeValide(utilisateur);
        vehicule.save();
        Trajet trajet = TestsHelper.creeTrajetValide(utilisateur, vehicule)
        trajet.save(flush:true)
        controller.trajetService.trouverTrajet(_) >> trajet

        when: "une demande de suppression"
        controller.supprimer(trajet.id)
        then: "Le service est appelé"
        1 * controller.trajetService.supprimer(_)
    }

    void "teste la suppression d'un trajet inexistant"() {
        when: "une demande de suppression sur un trajet qui n'existe pas"
        controller.supprimer(65)
        then: "Le service n'est pas appelé"
        0 * controller.trajetService.supprimer(_)
    }





    //
    //
    //   AJOUT TRAJET
    //
    //

    void "teste l'affichage du formulaire d'ajout de trajet"() {
        given: "Un trajet qui appartient a un utilisateur"
        Vehicule vehicule = Mock(Vehicule)
        vehicule.possesseur >> request.session['utilisateur']

        when: "une demande d'accès au formulaire d'ajout par un utilisateur connecté"
        controller.ajouter()

        then: "l'utilisateur est redirigé sur la page"
        view == '/trajet/ajouter'
        response.status == 200
    }

    void "teste l'ajout de trajet avec des champs corrects"() {
        given: "un membre qui veut ajouter un trajet"
        Trajet trajet = Mock(Trajet)
        trajet.vehicule >> Mock(Vehicule)
        trajet.vehicule.id >> null
        def utilisateur = TestsHelper.creeUtilisateurValide()
        request.session['utilisateur'] = utilisateur

        when: "il valide le trajet"
        controller.ajouterTrajetPost(trajet)

        then:"the trajet is created"
        1 * controller.trajetService.ajouterOuModifierTrajet(_)
        response.redirectedUrl == '/accueil'
    }

    void "teste l'ajout de trajet avec une instance nulle (mode formmulaire)"() {
        when: "il ajoute un trajet null"
        request.contentType = FORM_CONTENT_TYPE
        controller.ajouterTrajetPost(null)

        then: "une erreur 404 est retournée"
        response.redirectedUrl == '/trajet/index'
        flash.message != null
    }

    void "teste l'ajout de trajet avec une instance nulle (mode non formmulaire)"() {
        when: "il ajoute un trajet null"
        controller.ajouterTrajetPost(null)

        then: "une erreur 404 est retournée"
        response.status == HttpStatus.NOT_FOUND.value()
        flash.message == null
    }






    //
    //
    //   EDITER TRAJET
    //
    //

    void "teste l'affichage du formulaire d'edition de trajet (avec un trajet valide)"() {
        given: "Un trajet qui appartient a un utilisateur"
        Utilisateur utilisateur = Mock(Utilisateur)
        request.session['utilisateur'] = utilisateur
        Trajet trajet = Mock(Trajet)

        when: "une demande d'accès au formulaire d'edition par un utilisateur connecté"
        controller.editer(trajet)

        then: "l'utilisateur est redirigé sur la page d'edition"
        1 * utilisateur.vehicules >> []
        model.listeVehicules != null
        model.trajet == trajet
        view == '/trajet/editer'
        response.status == 200
    }

    void "teste l'edition de trajet avec des champs corrects"() {
        given: "un membre qui veut editer un trajet"
        Trajet trajet = Mock(Trajet)
        trajet.vehicule >> Mock(Vehicule)
        trajet.vehicule.id >> null
        def utilisateur = TestsHelper.creeUtilisateurValide()
        request.session['utilisateur'] = utilisateur

        when: "il valide le trajet"
        controller.updateTrajet(trajet)

        then:"the trajet is created"
        1 * controller.trajetService.ajouterOuModifierTrajet(_)
        response.redirectedUrl == '/accueil'
    }

    void "teste l'edition de trajet avec une instance nulle (mode formmulaire)"() {
        when: "il ajoute un trajet null"
        request.contentType = FORM_CONTENT_TYPE
        controller.updateTrajet(null)

        then: "une erreur 404 est retournée"
        response.redirectedUrl == '/trajet/index'
        flash.message != null
    }

    void "teste l'edition de trajet avec une instance nulle (mode non formmulaire)"() {
        when: "il ajoute un trajet null"
        controller.updateTrajet(null)

        then: "une erreur 404 est retournée"
        response.status == HttpStatus.NOT_FOUND.value()
        flash.message == null
    }






}
