package com.runninglama

import grails.test.mixin.*
import spock.lang.*

@TestFor(TrajetController)
@Mock([Trajet, Utilisateur, Vehicule])
class TrajetControllerSpec extends Specification {

    def setup() {
        def utilisateur = TestsHelper.creeUtilisateurValide()
        request.session['utilisateur'] = utilisateur
        controller.trajetService = Mock(TrajetService)
    }


    void "teste l'affichage du formulaire d'ajout de trajet"() {
        given: "Un véhicule qui appartient a un utilisateur"
        Vehicule vehicule = Mock(Vehicule)
        vehicule.possesseur >> request.session['utilisateur']
        when: "une demande d'accès au formulaire d'ajout par un utilisateur connecté"
        controller.ajouterTrajet()
        then: "l'utilisateur est redirigé sur la page"
        view == '/trajet/ajouter'
        response.status == 200
    }

    void "teste l'affichage de la liste des trajet"() {
        when: "une demande d'accès a la liste des trajet"
        controller.liste()
        then: "l'utilisateur est redirigé sur la page"
        view == '/trajet/liste'
        response.status == 200
    }

    void "teste la recherche de trajet"() {

        when: "on appelle l'action rechercherTrajet"
        controller.rechercherTrajet()

        then: "le modèle retourné est correct"
        response.status == 200
        view == '/trajet/liste'

    }

    void "teste the index action returns the correct model"() {

        when: "The index action is executed"
        controller.index()

        then: "The model is correct"
        !model.trajetInstanceList
        model.trajetInstanceCount == 0
    }

    void "teste the create action returns the correct model"() {
        when: "The create action is executed"
        controller.create()

        then: "The model is correctly created"
        model.trajetInstance != null
    }

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
        1*controller.trajetService.supprimer(_)
    }

    void "teste la suppression d'un trajet inexistant"() {
        when: "une demande de suppression sur un trajet qui n'existe pas"
        controller.supprimer(65)
        then: "Le service n'est pas appelé"
        0*controller.trajetService.supprimer(_)
    }

    void "teste d'un ajout de trajet avec des champs corrects"() {
        given: "un membre qui veut ajouter un trajet"
        Trajet trajet = Mock(Trajet)
        trajet.vehicule >> Mock(Vehicule)
        trajet.vehicule.id >> null
        def utilisateur = TestsHelper.creeUtilisateurValide()
        request.session['utilisateur'] = utilisateur

        when: "il valide le trajet"
        controller.ajouterTrajetPost(trajet)

        then:"the trajet is created"
        1 * controller.trajetService.creerOuModifier(_)
        response.redirectedUrl == '/accueil'

    }


//    void "teste l'inscription a un trajet"() {
//        given: "Un trajet et un utilisateur qui n'est ni inscrit, ni conducteur sur le trajet"
//        Trajet trajet = Mock(Trajet)
//
//        Utilisateur utilisateur = Mock(Utilisateur)
//        request.session['utilisateur'] = utilisateur
//        controller.trajetService.trouverTrajet(_ as Long) >> { it -> trajet }
//        trajet.addToParticipants(_ as Utilisateur) >> true
//
//        when: "L'utilisateur veut s'inscrire au trajet"
//        controller.ajouterParticipant(trajet.id)
//
//        then:"le controlleur appel le service"
//        1 * controller.trajetService.trouverTrajet(_)
//        1 * controller.trajetService.creerOuModifier(_)
//    }
}
