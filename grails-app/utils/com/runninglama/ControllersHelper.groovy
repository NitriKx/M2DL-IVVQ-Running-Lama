package com.runninglama

/**
 * Created by benoit on 22/10/15.
 */
class ControllersHelper {

    static recupererUtilisateurAuthentifie(session) {
        def utilisateurAuthentifie = session.getAttribute("utilisateur")
        if (!utilisateurAuthentifie) {
            throw new RuntimeException("Erreur de filtrage des requêtes: l'utilisateur est arrivé dans un controlleur sans être authentifié");
        }
        utilisateurAuthentifie
    }

}
