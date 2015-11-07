package com.runninglama

class SecurityFilters {

    def filters = {
        allExceptIndex(controller:'utilisateur|accueil|trajet', action:'inscription|inscriptionPost|connexionPost|index|liste|voirTrajet', invert: true) {
            before = {
                if (!session.getAttribute('utilisateur') && !actionName.equals('connexion')) {
                    redirect(controller: 'utilisateur', action: 'connexion')
                    return false
                }
            }
        }
    }
}
