package com.runninglama

import grails.transaction.Transactional
import org.codehaus.groovy.grails.web.servlet.mvc.GrailsParameterMap

class VehiculeService {

    VehiculeDAOService vehiculeDAOService

    def creeVehicule(params) {
        new Vehicule(params)
    }

    def getNombreVehicules() {
        vehiculeDAOService.count();
    }

    def recupererListVehicule(params) {
        vehiculeDAOService.list(params);
    }

    def creeOuModifierVehicule(Vehicule vehiculeInstance) {
        vehiculeDAOService.save(vehiculeInstance);
    }

    def supprimerVehicule(Vehicule vehiculeInstance) {
        vehiculeDAOService.delete(vehiculeInstance);
    }
}
