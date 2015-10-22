package com.runninglama

import grails.test.mixin.TestFor
import spock.lang.Specification
import grails.test.mixin.*

/**
 * See the API for {@link grails.test.mixin.services.ServiceUnitTestMixin} for usage instructions
 */
@TestFor(UtilisateurDAOService)
@Mock(Utilisateur)
class UtilisateurDAOServiceSpec extends Specification {

    //def UtilisateurDAOService service = new UtilisateurDAOService()


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
        def resultatAjout = service.save(utilisateur)


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
        def resultatAjout = service.save(utilisateur)

        then: "l'utilisateur peut etre retrouvé par son pseudo"
        resultatAjout != null
        def utilisateurRetourne = service.findByPseudo(aPseudo)
        utilisateurRetourne != null

        where:
        aPseudo            | aPrenom  | aNom      | aEmail               | aTelephone   | aDateDerniereConnexion | aDateInscription | aDateNaissance | aPasswordHash        | aPasswordSalt  | aMotDePasse | aMotDePasseConfirmation
        "ARunningLama"     | "Julien" | "Custoja" | "julien.c@gmail.com" | "0567543456" | new Date()             | new Date()       | new Date()     | "gfhthqsdfjgjqfjgfg" | "ssdfdsfdsfsd" | "azerty"    | "azerty"
    }
}
