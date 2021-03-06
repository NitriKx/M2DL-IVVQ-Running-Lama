package com.runninglama

import spock.lang.Specification

/**
 * See the API for {@link grails.test.mixin.services.ServiceUnitTestMixin} for usage instructions
 */
class UtilisateurDAOServiceSpec extends Specification {

    def utilisateurDAOService

    def setup() {
    }

    def cleanup() {
    }

    void "test que l'utilisateur est effectivement ajouté de la base de données"() {

        given: "un utilisateur valide pas en base de données"

        def utilisateur = new Utilisateur(dateDerniereConnexion: aDateDerniereConnexion, telephone: aTelephone,
                                            pseudo: aPseudo, dateInscription: aDateInscription, dateNaissance: aDateNaissance,
                                            email: aEmail, nom: aNom, prenom: aPrenom, passwordHash: aPasswordHash, passwordSalt: aPasswordSalt, motDePasse: aMotDePasse, motDePasseConfirmation: aMotDePasseConfirmation)
        utilisateur.validate() != false

        when: "on demande l'ajout de cet utilisateur"
        def resultatAjout = utilisateurDAOService.save(utilisateur)


        then: "l'utilisateur existe dans la base de données"
        resultatAjout != null
        Utilisateur.findAllById(resultatAjout.id).size() == 1

        where:
        aPseudo            | aPrenom  | aNom      | aEmail               | aTelephone   | aDateDerniereConnexion | aDateInscription | aDateNaissance | aPasswordHash        | aPasswordSalt  | aMotDePasse | aMotDePasseConfirmation
        "ARunningLama"     | "Julien" | "Custoja" | "julien.c@gmail.com" | "0567543456" | new Date()             | new Date()       | new Date()     | "gfhthqsdfjgjqfjgfg" | "ssdfdsfdsfsd" | "azerty"    | "azerty"
    }

    void "test la récupération d'un utilisateur par pseudo"() {
        given: "un utilisateur valide pas en base de données"


        def utilisateur = new Utilisateur(dateDerniereConnexion: aDateDerniereConnexion, telephone: aTelephone,
                pseudo: aPseudo, dateInscription: aDateInscription, dateNaissance: aDateNaissance,
                email: aEmail, nom: aNom, prenom: aPrenom, passwordHash: aPasswordHash, passwordSalt: aPasswordSalt, motDePasse: aMotDePasse, motDePasseConfirmation: aMotDePasseConfirmation)
        utilisateur.validate() != false

        when: "on demande l'ajout de cet utilisateur"
        def resultatAjout = utilisateurDAOService.save(utilisateur)

        then: "l'utilisateur peut etre retrouvé par son pseudo"
        resultatAjout != null
        def utilisateurRetourne = utilisateurDAOService.findByPseudo(aPseudo)
        utilisateurRetourne != null

        where:
        aPseudo            | aPrenom  | aNom      | aEmail               | aTelephone   | aDateDerniereConnexion | aDateInscription | aDateNaissance | aPasswordHash        | aPasswordSalt  | aMotDePasse | aMotDePasseConfirmation
        "ARunningLama"     | "Julien" | "Custoja" | "julien.c@gmail.com" | "0567543456" | new Date()             | new Date()       | new Date()     | "gfhthqsdfjgjqfjgfg" | "ssdfdsfdsfsd" | "azerty"    | "azerty"
    }

    void "teste qu'après ajout d'un utilisateur, la liste des utilisateurs est bien mis à jour"() {
        given: "une base de données avec un certain nombre d'utilisateurs"
        def nombreUtilisateurActuel = Utilisateur.count()

        when: "on ajoute un utilisateur"
        def utilisateur = TestsHelper.creeUtilisateurValide().save(flush: true)

        then: "la liste d'utilisateur contient maintenant un utilisateur"
        utilisateurDAOService.liste().size() == nombreUtilisateurActuel + 1
    }
}
