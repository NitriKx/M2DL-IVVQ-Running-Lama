package com.runninglama

import grails.util.GrailsWebUtil
import grails.test.mixin.*
import org.codehaus.groovy.grails.web.servlet.mvc.GrailsParameterMap
import org.grails.datastore.mapping.core.Session
import spock.lang.*

import javax.servlet.http.HttpSession

@TestFor(UtilisateurController)
@Mock(Utilisateur)
class UtilisateurControllerSpec extends Specification {

    def populateValidParams(params) {
        assert params != null
        params["email"] = 'email@test.fr'
        params["nom"] = 'Custoja'
        params["prenom"] = 'Julien'
        params["pseudo"] = 'TheRunningLama'
        params["passwordHash"] = 'jhdfqsfgqsdufhqsduhfqsuidfqsdf'
        params["passwordSalt"] = 'qsdqfqsdfsdfqgfgtryurdx'
        params["dateInscription"] = new Date()
        params["dateDerniereConnexion"] = new Date()
        params["dateNaissance"] = new Date()
        params["email"] = 'julien.c@test.fr'
        params["telephone"] = '0987675434'
        params["motDePasse"] = 'azerty'
    }

    def setup() {
        controller.utilisateurService = Mock(UtilisateurService)
    }

    void "test l'affichage du formulaire d'inscription"(){
        when: "une demande d'accès au formulaire d'inscription"
        controller.inscription()
        then: "l'utilisateur est redirigé sur la page d'inscription"
        view == '/utilisateur/inscription'
        response.status == 200
    }

    void "test la deconnexion d'un utilisateur"() {
        when: "un utilisateur se deconnecte"
        controller.deconnexion();

        then: "l'utilisateur est redirigé sur l'accueil"
        response.redirectedUrl == '/accueil/index'
    }

    void "Test l'inscription d'un utilisateur valide"() {
        given: "Un utilisateur valide"
        Utilisateur utilisateur = Mock(Utilisateur);
        utilisateur.hasErrors () >> false
        controller.utilisateurService.inscrireUtilisateur(utilisateur) >> utilisateur

        when: "on inscrit l'utilisateur"
        controller.inscriptionPost(params)

        then: "L'inscription est validée"
        view == '/utilisateur/inscription'
        response.status == 200
    }

    void "test l'affichage du formulaire de connexion"() {
        when: "une demande d'accès au formulaire de connexion"
        controller.connexion()
        then: "l'utilisateur est redirigé sur la page d'inscription"
        view == '/utilisateur/connexion'
        response.status == 200
    }

    void "test la connexion d'un utilisateur valide"() {
        given: "les informations d'un utilisateur qui souhaite se connecter"
        def paramsConnexion = [:]
        paramsConnexion['pseudo'] = "TheRunningLama"
        paramsConnexion['motDePasse'] = "azerty"
        Utilisateur utilisateur = new Utilisateur(params)
        request.session['utilisateur'] = utilisateur

        controller.utilisateurService.verifierIdentifiants(_ as Utilisateur) >> { Utilisateur u -> u }

        when: "l'utilisateur se connecte"
        controller.connexionPost(paramsConnexion)

        then: "L'utilisateur est ajouté dans la session"
        request.session['utilisateur'] != null

        and: "l'utilisateur est redirigé sur la page d'accueil"
        response.redirectedUrl == '/accueil/index'
    }

    void "test la connexion d'un utilisateur invalide"() {
        given: "les informations d'un utilisateur qui souhaite se connecter"
        def request = GrailsWebUtil.bindMockWebRequest();
        controller.utilisateurService.verifierIdentifiants(_ as Utilisateur) >> { null }
        controller.session.getAttribute('utilisateur') >> null

        when: "l'utilisateur se connecte"
        controller.connexionPost(params)

        then: "L'utilisateur n'est pas ajouté en session"
        request.session['utilisateur'] == null

        and: "l'utilisateur est redirigé sur la page de connexion"
        view == '/utilisateur/connexion'
    }

    void "test l'affichage du formulaire de modif"() {
        when: "une demande d'accès au formulaire de connexion"
        controller.modifierProfil()
        then: "l'utilisateur est redirigé sur la page de modif de profil"
        view == '/utilisateur/modifierProfil'
        response.status == 200
    }

    void "test la modification du profil"() {
        given: "les informations modifiés d'un utilisateur qui est connecté"
        Utilisateur membre = new Utilisateur(params)
        request.session['utilisateur'] = membre
        Utilisateur nouveauMembre = TestsHelper.creeUtilisateurValide()
        nouveauMembre.setPseudo("NewPseudo")

        when: "l'utilisateur se connecte"
        nouveauMembre.validate()
        controller.utilisateurService.modifierUtilisateur(_ as Utilisateur,_ as Utilisateur,_) >> { Utilisateur u,Utilisateur u2,String p -> nouveauMembre }
        controller.modifierProfilPost()

        then: "L'utilisateur est modifié"
        nouveauMembre.getPseudo() == "NewPseudo"
        nouveauMembre.hasErrors() == false

        and: "l'utilisateur est redirigé sur la page de connexion"
        response.redirectUrl == '/accueil/index'
    }

    void "test la modification du profil avec erreurs"() {
        given: "les informations modifiés d'un utilisateur qui est connecté"
        Utilisateur membre = new Utilisateur(params)
        request.session['utilisateur'] = membre
        Utilisateur nouveauMembre = new Utilisateur(params)
        nouveauMembre.setPseudo(controller.params.pseudo)

        when: "l'utilisateur se connecte"
        nouveauMembre.validate()
        controller.utilisateurService.modifierUtilisateur(_ as Utilisateur,_ as Utilisateur,_) >> { Utilisateur u,Utilisateur u2,String p -> nouveauMembre }
        controller.modifierProfilPost()

        then: "L'utilisateur n'est pas ajouté en session"
        request.session['utilisateur'] == membre
        nouveauMembre.hasErrors() == true

        and: "l'utilisateur est redirigé sur la page de connexion"
        view == '/utilisateur/modifierProfil'
    }

    void "test la methode index avec un utilisateur connecté"() {
        given: "Un utilisateur qui est connecté"
        def utilisateur = TestsHelper.creeUtilisateurValide()
        request.session['utilisateur'] = utilisateur

        when:"L'utilisateur souhaite acceder a son profil"
        controller.index()

        then:"L'utilisateut est bien redirigé vers son profil"
        response.redirectUrl == '/utilisateur/modifierProfil'
    }

    void "test la methode index avec un utilisateur non connecté"() {
        given: "Un utilisateur qui est connecté"
        request.session['utilisateur'] = null

        when:"L'utilisateur souhaite acceder a son profil"
        controller.index()

        then:"L'utilisateut est bien redirigé vers le formulaire de connexion"
        response.redirectUrl == '/utilisateur/connexion'
    }


    /*
    void "Test the index action returns the correct model"() {

        when: "The index action is executed"
        controller.index()

        then: "The model is correct"
        !model.utilisateurInstanceList
        model.utilisateurInstanceCount == 0
    }

    void "Test the create action returns the correct model"() {
        when: "The create action is executed"
        controller.create()

        then: "The model is correctly created"
        model.utilisateurInstance != null
    }

    void "Test the save action correctly persists an instance"() {

        when: "The save action is executed with an invalid instance"
        request.contentType = FORM_CONTENT_TYPE
        def utilisateur = new Utilisateur()
        utilisateur.validate()
        controller.save(utilisateur)

        then: "The create view is rendered again with the correct model"
        model.utilisateurInstance != null
        view == 'create'

        when: "The save action is executed with a valid instance"
        response.reset()
        populateValidParams(params)
        utilisateur = new Utilisateur(params)

        controller.save(utilisateur)

        then: "A redirect is issued to the show action"
        response.redirectedUrl == '/utilisateur/show/1'
        controller.flash.message != null
        Utilisateur.count() == 1
    }

    void "Test that the show action returns the correct model"() {
        when: "The show action is executed with a null domain"
        controller.show(null)

        then: "A 404 error is returned"
        response.status == 404

        when: "A domain instance is passed to the show action"
        populateValidParams(params)
        def utilisateur = new Utilisateur(params)
        controller.show(utilisateur)

        then: "A model is populated containing the domain instance"
        model.utilisateurInstance == utilisateur
    }

    void "Test that the edit action returns the correct model"() {
        when: "The edit action is executed with a null domain"
        controller.edit(null)

        then: "A 404 error is returned"
        response.status == 404

        when: "A domain instance is passed to the edit action"
        populateValidParams(params)
        def utilisateur = new Utilisateur(params)
        controller.edit(utilisateur)

        then: "A model is populated containing the domain instance"
        model.utilisateurInstance == utilisateur
    }

    void "Test the update action performs an update on a valid domain instance"() {
        when: "Update is called for a domain instance that doesn't exist"
        request.contentType = FORM_CONTENT_TYPE
        controller.update(null)

        then: "A 404 error is returned"
        response.redirectedUrl == '/utilisateur/index'
        flash.message != null


        when: "An invalid domain instance is passed to the update action"
        response.reset()
        def utilisateur = new Utilisateur()
        utilisateur.validate()
        controller.update(utilisateur)

        then: "The edit view is rendered again with the invalid instance"
        view == 'edit'
        model.utilisateurInstance == utilisateur

        when: "A valid domain instance is passed to the update action"
        response.reset()
        populateValidParams(params)
        utilisateur = new Utilisateur(params).save(flush: true)
        controller.update(utilisateur)

        then: "A redirect is issues to the show action"
        response.redirectedUrl == "/utilisateur/show/$utilisateur.id"
        flash.message != null
    }

    void "Test that the delete action deletes an instance if it exists"() {
        when: "The delete action is called for a null instance"
        request.contentType = FORM_CONTENT_TYPE
        controller.delete(null)

        then: "A 404 is returned"
        response.redirectedUrl == '/utilisateur/index'
        flash.message != null

        when: "A domain instance is created"
        response.reset()
        populateValidParams(params)
        def utilisateur = new Utilisateur(params).save(flush: true)

        then: "It exists"
        Utilisateur.count() == 1

        when: "The domain instance is passed to the delete action"
        controller.delete(utilisateur)

        then: "The instance is deleted"
        Utilisateur.count() == 0
        response.redirectedUrl == '/utilisateur/index'
        flash.message != null
    }

   */
}
