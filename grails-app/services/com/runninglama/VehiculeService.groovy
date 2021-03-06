package com.runninglama

import grails.transaction.Transactional
import org.codehaus.groovy.grails.web.servlet.mvc.GrailsParameterMap

class VehiculeService {

    VehiculeDAOService vehiculeDAOService

    def creeVehicule(params) {
        new Vehicule(params)
    }

    def vehiculeAppartientUtilisateur(Utilisateur possesseur, Vehicule vehicule) {
//        def res = possesseur.vehicules.contains(vehicule)
        vehicule.possesseur.id == possesseur.id
    }

    def getNombreVehicules(Utilisateur possesseur, params) {
        vehiculeDAOService.count(possesseur, params);
    }

    def recupererListVehicule(Utilisateur possesseur, params) {
        vehiculeDAOService.list(possesseur, params);
    }

    def creeOuModifierVehicule(Vehicule vehiculeInstance) {
        vehiculeInstance.possesseur.addToVehicules(vehiculeInstance)
        vehiculeDAOService.save(vehiculeInstance);
    }

    def supprimerVehicule(Vehicule vehiculeInstance) {
        vehiculeDAOService.delete(vehiculeInstance);
    }
}
