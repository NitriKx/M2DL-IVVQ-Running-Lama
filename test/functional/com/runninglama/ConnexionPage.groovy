package com.runninglama

class ConnexionPage extends geb.Page {

    static at = {
        $(".page-header").text() == "Connexion"
    }

    static url = "utilisateur/connexion"

    static content = {
        pseudo            { $("input", name: "pseudo")               }
        motDePasse             { $("input", name: "motDePasse")           }
        btn         { $("#btnConnexion")               }
    }

}