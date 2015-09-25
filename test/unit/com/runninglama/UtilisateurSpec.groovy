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
                email: aEmail, nom: aNom, prenon: aPrenom, passwordHash: aPasswordHash, passwordSalt: aPasswordSalt)

        when: "on essaye de valider un utilisateur"
        def validation = util.validate()

        then: "le déplacement est correctement effectué"
        util.hasErrors() == false


        where:
        aPseudo            | aPrenom  | aNom      | aEmail               | aTelephone   | aDateDerniereConnexion | aDateInscription | aDateNaissance | aPasswordHash        | aPasswordSalt
        "ARunningLama"     | "Julien" | "Custoja" | "julien.c@gmail.com" | "0567543456" | new Date()             | new Date()       | new Date()     | "gfhthqsdfjgjqfjgfg" | "ssdfdsfdsfsd"
        "ARunningTortoise" | "Loic"   | "Leger"   | "loic.l@gmail.com"   | "0590674531" | null                   | new Date()       | new Date()     | "gfhthqsdfjgjqfjgfg" | "ssdfdsfdsfsd"
    }

    @Unroll
    def "Un utilisateur avec des données incorrectes"() {

        given: "Un utilisateur avec des données incorrectes "
        def util = new Utilisateur(dateDerniereConnexion: aDateDerniereConnexion, telephone: aTelephone,
                pseudo: aPseudo, dateInscription: aDateInscription, dateNaissance: aDateNaissance,
                email: aEmail, nom: aNom, prenon: aPrenom, passwordHash: aPasswordHash, passwordSalt: aPasswordSalt)

        when: "on essaye de valider un utilisateur"
        def validation = util.validate()

        then: "le déplacement est correctement effectué"
        util.hasErrors() == true


        where:
        aPseudo    | aPrenom  | aNom      | aEmail               | aTelephone   | aDateDerniereConnexion | aDateInscription | aDateNaissance | aPasswordHash        | aPasswordSalt
        ""         | "Julien" | "Custoja" | "julien.c@gmail.com" | "0567543456" | new Date() | new Date() | new Date() | "gfhthqsdfjgjqfjgfg" | "ssdfdsfdsfsd"
        null       | "Loic"   | "Leger"   | "loic.l@gmail.com"   | "0590674531" | null       | new Date() | new Date() | "gfhthqsdfjgjqfjgfg" | "ssdfdsfdsfsd"
        "Pseudo1"  | ""       | "Custoja" | "julien.c@gmail.com" | "0567543456" | new Date() | new Date() | new Date() | "gfhthqsdfjgjqfjgfg" | "ssdfdsfdsfsd"
        "Pseudo2"  | null     | "Leger"   | "loic.l@gmail.com"   | "0590674531" | null       | new Date() | new Date() | "gfhthqsdfjgjqfjgfg" | "ssdfdsfdsfsd"
        "Pseudo3"  | "Julien" | ""        | "julien.c@gmail.com" | "0567543456" | new Date() | new Date() | new Date() | "gfhthqsdfjgjqfjgfg" | "ssdfdsfdsfsd"
        "Pseudo4"  | "Loic"   | null      | "loic.l@gmail.com"   | "0590674531" | null       | new Date() | new Date() | "gfhthqsdfjgjqfjgfg" | "ssdfdsfdsfsd"
        "Pseudo5"  | "Julien" | "Custoja" | ""                   | "0567543456" | new Date() | new Date() | new Date() | "gfhthqsdfjgjqfjgfg" | "ssdfdsfdsfsd"
        "Pseudo6"  | "Loic"   | "Leger"   | null                 | "0590674531" | null       | new Date() | new Date() | "gfhthqsdfjgjqfjgfg" | "ssdfdsfdsfsd"
        "Pseudo7"  | "Julien" | "Custoja" | "julien.c"           | "0567543456" | new Date() | new Date() | new Date() | "gfhthqsdfjgjqfjgfg" | "ssdfdsfdsfsd"
        "Pseudo8"  | "Loic"   | "Leger"   | "loic.l@gmail.com"   | "059067453"  | null       | new Date() | new Date() | "gfhthqsdfjgjqfjgfg" | "ssdfdsfdsfsd"
        "Pseudo9"  | "Julien" | "Custoja" | "julien.c@gmail.com" | null         | new Date() | new Date() | new Date() | "gfhthqsdfjgjqfjgfg" | "ssdfdsfdsfsd"
        "Pseudo10" | "Loic"   | "Leger"   | "loic.l@gmail.com"   | ""           | null       | new Date() | new Date() | "gfhthqsdfjgjqfjgfg" | "ssdfdsfdsfsd"
        "Pseudo11" | ""       | "Custoja" | "julien.c@gmail.com" | "0567543456" | new Date() | new Date() | new Date() | "gfhthqsdfjgjqfjgfg" | "ssdfdsfdsfsd"
        "Pseudo12" | "null"   | "Leger"   | "loic.l@gmail.com"   | "0590674531" | null       | null       | new Date() | "gfhthqsdfjgjqfjgfg" | "ssdfdsfdsfsd"
        "Pseudo13" | "Julien" | ""        | "julien.c@gmail.com" | "0567543456" | new Date() | new Date() | null       | "gfhthqsdfjgjqfjgfg" | "ssdfdsfdsfsd"
        "Pseudo14" | "Loic"   | "null"    | "loic.l@gmail.com"   | "0590674531" | null       | new Date() | new Date() | ""                   | "ssdfdsfdsfsd"
        "Pseudo15" | "Julien" | "Custoja" | ""                   | "0567543456" | new Date() | new Date() | new Date() | null                 | "ssdfdsfdsfsd"
        "Pseudo16" | "Loic"   | "Leger"   | "null"               | "0590674531" | null       | new Date() | new Date() | "gfhthqsdfjgjqfjgfg" | ""
        "Pseudo17" | "Julien" | "Custoja" | "julien.c"           | "0567543456" | new Date() | new Date() | new Date() | "gfhthqsdfjgjqfjgfg" | "null"

    }
}
