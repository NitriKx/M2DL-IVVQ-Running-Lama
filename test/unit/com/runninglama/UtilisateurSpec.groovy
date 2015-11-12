package com.runninglama

import grails.test.mixin.TestFor
import spock.lang.Specification
import spock.lang.Unroll

/**
 * See the API for {@link grails.test.mixin.domain.DomainClassUnitTestMixin} for usage instructions
 */
@TestFor(Utilisateur)
class UtilisateurSpec extends Specification {

    def setup() {

    }

    def cleanup() {

    }

    @Unroll
    def "Un utilisateur avec des données correctes"() {

        given: "Un utilisateur avec des données correctes "
        def util = new Utilisateur(dateDerniereConnexion: aDateDerniereConnexion, telephone: aTelephone,
                pseudo: aPseudo, dateInscription: aDateInscription, dateNaissance: aDateNaissance,
                email: aEmail, nom: aNom, prenom: aPrenom, passwordHash: aPasswordHash,
                passwordSalt: aPasswordSalt, motDePasse: aMotDePasse, motDePasseConfirmation: aMotDePasseConfirmation, noteMoyenne: noteMoyenne)

        when: "on essaye de valider un utilisateur"
        util.validate()
        println(util.getErrors())

        then: "le déplacement est correctement effectué"
        util.hasErrors() == false


        where:
        aPseudo            | aPrenom  | aNom      | aEmail               | aTelephone   | aDateDerniereConnexion | aDateInscription | aDateNaissance | aPasswordHash        | aPasswordSalt  | aMotDePasse | aMotDePasseConfirmation | noteMoyenne
        "ARunningLama"     | "Julien" | "Custoja" | "julien.c@gmail.com" | "0567543456" | new Date()             | new Date()       | new Date()     | "gfhthqsdfjgjqfjgfg" | "ssdfdsfdsfsd" | "azerty"    | "azerty"                | 3.5
        "ARunningTortoise" | "Loic"   | "Leger"   | "loic.l@gmail.com"   | "0590674531" | null                   | new Date()       | new Date()     | "gfhthqsdfjgjqfjgfg" | "ssdfdsfdsfsd" | "azerty"    | "azerty"                | 4.8
    }

    @Unroll
    def "Un utilisateur avec des données incorrectes"() {

        given: "Un utilisateur avec des données incorrectes "
        def util = new Utilisateur(dateDerniereConnexion: aDateDerniereConnexion, telephone: aTelephone,
                pseudo: aPseudo, dateInscription: aDateInscription, dateNaissance: aDateNaissance,
                email: aEmail, nom: aNom, prenom: aPrenom, passwordHash: aPasswordHash, passwordSalt: aPasswordSalt, motDePasse: aMotDePasse, motDePasseConfirmation: aMotDePasseConfirmation)

        when: "on essaye de valider un utilisateur"
        def validation = util.validate()

        then: "le déplacement est correctement effectué"
        util.hasErrors() == true


        where:
        aPseudo    | aPrenom  | aNom      | aEmail               | aTelephone   | aDateDerniereConnexion | aDateInscription | aDateNaissance | aPasswordHash        | aPasswordSalt  | aMotDePasse | aMotDePasseConfirmation
        ""         | "Julien" | "Custoja" | "julien.c@gmail.com" | "0567543456" | new Date()             | new Date()       | new Date()     | "gfhthqsdfjgjqfjgfg" | "ssdfdsfdsfsd" | "azerty"    | "azerty"
        null       | "Loic"   | "Leger"   | "loic.l@gmail.com"   | "0590674531" | null                   | new Date()       | new Date()     | "gfhthqsdfjgjqfjgfg" | "ssdfdsfdsfsd" | "azerty"    | "azerty"
        "Pseudo1"  | ""       | "Custoja" | "julien.c@gmail.com" | "0567543456" | new Date()             | new Date()       | new Date()     | "gfhthqsdfjgjqfjgfg" | "ssdfdsfdsfsd" | "azerty"    | "azerty"
        "Pseudo2"  | null     | "Leger"   | "loic.l@gmail.com"   | "0590674531" | null                   | new Date()       | new Date()     | "gfhthqsdfjgjqfjgfg" | "ssdfdsfdsfsd" | "azerty"    | "azerty"
        "Pseudo3"  | "Julien" | ""        | "julien.c@gmail.com" | "0567543456" | new Date()             | new Date()       | new Date()     | "gfhthqsdfjgjqfjgfg" | "ssdfdsfdsfsd" | "azerty"    | "azerty"
        "Pseudo4"  | "Loic"   | null      | "loic.l@gmail.com"   | "0590674531" | null                   | new Date()       | new Date()     | "gfhthqsdfjgjqfjgfg" | "ssdfdsfdsfsd" | "azerty"    | "azerty"
        "Pseudo5"  | "Julien" | "Custoja" | ""                   | "0567543456" | new Date()             | new Date()       | new Date()     | "gfhthqsdfjgjqfjgfg" | "ssdfdsfdsfsd" | "azerty"    | "azerty"
        "Pseudo6"  | "Loic"   | "Leger"   | null                 | "0590674531" | null                   | new Date()       | new Date()     | "gfhthqsdfjgjqfjgfg" | "ssdfdsfdsfsd" | "azerty"    | "azerty"
        "Pseudo7"  | "Julien" | "Custoja" | "julien.c"           | "0567543456" | new Date()             | new Date()       | new Date()     | "gfhthqsdfjgjqfjgfg" | "ssdfdsfdsfsd" | "azerty"    | "azerty"
        "Pseudo8"  | "Loic"   | "Leger"   | "loic.l@gmail.com"   | "059067453"  | null                   | new Date()       | new Date()     | "gfhthqsdfjgjqfjgfg" | "ssdfdsfdsfsd" | "azerty"    | "azerty"
        "Pseudo9"  | "Julien" | "Custoja" | "julien.c@gmail.com" | null         | new Date()             | new Date()       | new Date()     | "gfhthqsdfjgjqfjgfg" | "ssdfdsfdsfsd" | "azerty"    | "azerty"
        "Pseudo10" | "Loic"   | "Leger"   | "loic.l@gmail.com"   | ""           | null                   | new Date()       | new Date()     | "gfhthqsdfjgjqfjgfg" | "ssdfdsfdsfsd" | "azerty"    | "azerty"
        "Pseudo11" | ""       | "Custoja" | "julien.c@gmail.com" | "0567543456" | new Date()             | new Date()       | new Date()     | "gfhthqsdfjgjqfjgfg" | "ssdfdsfdsfsd" | "azerty"    | "azerty"
        "Pseudo12" | "null"   | "Leger"   | "loic.l@gmail.com"   | "0590674531" | null                   | null             | new Date()     | "gfhthqsdfjgjqfjgfg" | "ssdfdsfdsfsd" | "azerty"    | "azerty"
        "Pseudo13" | "Julien" | ""        | "julien.c@gmail.com" | "0567543456" | new Date()             | new Date()       | null           | "gfhthqsdfjgjqfjgfg" | "ssdfdsfdsfsd" | "azerty"    | "azerty"
        "Pseudo14" | "Loic"   | "null"    | "loic.l@gmail.com"   | "0590674531" | null                   | new Date()       | new Date()     | ""                   | "ssdfdsfdsfsd" | "azerty"    | "azerty"
        "Pseudo15" | "Julien" | "Custoja" | ""                   | "0567543456" | new Date()             | new Date()       | new Date()     | null                 | "ssdfdsfdsfsd" | "azerty"    | "azerty"
        "Pseudo16" | "Loic"   | "Leger"   | "null"               | "0590674531" | null                   | new Date()       | new Date()     | "gfhthqsdfjgjqfjgfg" | ""             | "azerty"    | "azerty"
        "Pseudo17" | "Julien" | "Custoja" | "julien.c"           | "0567543456" | new Date()             | new Date()       | new Date()     | "gfhthqsdfjgjqfjgfg" | null           | "azerty"    | "azerty"
        "Pseudo17" | "Julien" | "Custoja" | "julien.c"           | "0567543456" | new Date()             | new Date()       | new Date()     | "gfhthqsdfjgjqfjgfg" | "ssdfdsfdsfsd" | null        | "azerty"
        "Pseudo17" | "Julien" | "Custoja" | "julien.c"           | "0567543456" | new Date()             | new Date()       | new Date()     | "gfhthqsdfjgjqfjgfg" | "ssdfdsfdsfsd" | "azerty"    | null

    }

    def "Un utilisateur enregistré"() {

        given: "Un utilisateur avec des données correctes "
        def util = new Utilisateur(dateDerniereConnexion: aDateDerniereConnexion, telephone: aTelephone,
                pseudo: aPseudo, dateInscription: aDateInscription, dateNaissance: aDateNaissance,
                email: aEmail, nom: aNom, prenom: aPrenom, passwordHash: aPasswordHash, passwordSalt: aPasswordSalt, motDePasse: aMotDePasse, motDePasseConfirmation: aMotDePasseConfirmation)

        when: "on veut afficher l'utilisateur"
        def validation = util.toString()

        then: "le déplacement est correctement effectué"
        validation.equals(aPseudo)

        where:
        aPseudo        | aPrenom  | aNom      | aEmail               | aTelephone   | aDateDerniereConnexion | aDateInscription | aDateNaissance | aPasswordHash        | aPasswordSalt  | aMotDePasse | aMotDePasseConfirmation | noteMoyenne
        "ARunningLama" | "Julien" | "Custoja" | "julien.c@gmail.com" | "0567543456" | new Date()             | new Date()       | new Date()     | "gfhthqsdfjgjqfjgfg" | "ssdfdsfdsfsd" | "azerty"    | "azerty"                | 3.5
    }
}