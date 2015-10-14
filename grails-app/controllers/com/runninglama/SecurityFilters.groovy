package com.runninglama

class SecurityFilters {

    def filters = {
        allExceptIndex(controller:'utilisateur', action:'inscription|inscriptionPost|connexionPost', invert: true) {
            before = {
                if (!session.getAttribute('utilisateur') && !actionName.equals('connexion')) {
                    redirect(controller: 'utilisateur', action: 'connexion')
                    return false
                }
            }
        }
    }
}
