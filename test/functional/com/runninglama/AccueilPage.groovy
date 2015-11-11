package com.runninglama

/**
 * Created by julien on 11/11/15.
 */
class AccueilPage extends geb.Page{

    static at = {
        $(".page-header").text() == "Bienvenue sur Running Lama"
    }

    static url = "/"

    static content = {

    }
}
