package com.runninglama

/**
 * Created by julien on 11/11/15.
 */
class AccueilPageMembre extends geb.Page{

    static at = {
        $(".page-header").text() == "Bienvenue sur Running Lama"
    }

    static url = "/"

    static content = {
        lienProfil { $("a", href: "/M2DL-IVVQ-Projet/utilisateur/modifierProfil/profil") }
        lienVehicule { $("#vehicule") }
        lienDeconnexion { $("#deconnexion") }

    }
}
