package com.runninglama

/**
 * Created by julien on 11/11/15.
 */
class AccueilPageVisiteur extends geb.Page{

    static at = {
        $("#page-header").text() == "Bienvenue sur Running Lama"
    }

    static url = "/"

    static content = {
        lienConnexion { $("a", href: "/M2DL-IVVQ-Projet/utilisateur/connexion/connexion") }
        lienInscription { $("a", href: "/M2DL-IVVQ-Projet/utilisateur/inscription/inscription") }
    }
}
