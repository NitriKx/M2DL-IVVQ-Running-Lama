package com.runninglama

/**
 * Created by julien on 11/11/15.
 */
class ProfilPage extends geb.Page {

    static at = {
        $("#page-header").text() == "Profil utilisateur Modification"
    }

    static url = "/"

    static content = {
        btnModifier { $("#btnModifier") }
        inputNom { $("#nom") }
        inputPrenom { $("#prenom") }
        inputPseudo { $("#pseudo") }
        inputEmail { $("#email") }
    }
}
