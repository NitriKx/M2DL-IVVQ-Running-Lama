package com.runninglama

/**
 * Created by julien on 11/11/15.
 */
class InscriptionPage extends geb.Page {

    static at = {
        $("#page-header").text() == "Inscription Totalement gratuit, pour toujours."
    }

    static url = "utilisateur/inscription/inscription"

    static content = {
        btnInscription { $("#btnInscription") }
        inputNom { $("#nom") }
        inputPrenom { $("#prenom") }
        inputPseudo { $("#pseudo") }
        inputEmail { $("#email") }
        inputMotDePasse { $("#motDePasse") }
        inputMotDePasseConfirmation { $("#motDePasseConfirmation") }
        inputTelephone { $("#telephone") }
    }
}
