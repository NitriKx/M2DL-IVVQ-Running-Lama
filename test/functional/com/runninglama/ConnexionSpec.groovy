package com.runninglama

import geb.spock.GebSpec
import spock.lang.*

@Stepwise
class ConnexionSpec extends GebSpec {

    def "test la connection avec des identifiants corrects"() {
        when:
        to ConnexionPage
        pseudo = "JulienCsj"
        motDePasse = "test"
        btn.click()

        then:
        at AccueilPage
    }

}