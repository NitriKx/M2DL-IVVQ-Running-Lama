package com.runninglama

/**
 * Created by benoit on 22/10/15.
 */
class ControllersHelper {

    static recupererUtilisateurAuthentifie(session) {
        def utilisateurAuthentifie = session.getAttribute("utilisateur")
        utilisateurAuthentifie
    }

}
