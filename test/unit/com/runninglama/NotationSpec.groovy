package com.runninglama

import grails.test.mixin.TestFor
import spock.lang.Specification
import spock.lang.Unroll

/**
 * See the API for {@link grails.test.mixin.domain.DomainClassUnitTestMixin} for usage instructions
 */
@TestFor(Notation)
class NotationSpec extends Specification {

    def setup() {
    }

    def cleanup() {
    }

    @Unroll
    def "Une Notation avec des données correctes"() {

        given: "Un trajet avec des données correctes "


        def notation = new Notation(
                note: aNote,
                commentaire: aCommentaire,
                participant: aParticipant
        )

        when: "on essaye de valider une notation"
        notation.validate()

        then: "le trajet est valide"
        notation.hasErrors() == false

        where:
        aNote | aCommentaire  | aParticipant
        0     | "commentaire" | Mock(Utilisateur)
        5     | ""            | Mock(Utilisateur)
        3     | null          | Mock(Utilisateur)
        3     | ""            | null
    }


    @Unroll
    def "Une Notation avec des données incorrectes"() {

        given: "Un trajet avec des données incorrectes "


        def notation = new Notation(
                note: aNote,
                commentaire: aCommentaire,
                participant: aParticipant
        )

        when: "on essaye de valider une notation"
        notation.validate()

        then: "le trajet est valide"
        notation.hasErrors() == true

        where:
        aNote | aCommentaire | aParticipant
        null  | 3            | Mock(Utilisateur)
        3     | null         | 4
        -1    | null         | 4
        6     | null         | 4

    }
}
