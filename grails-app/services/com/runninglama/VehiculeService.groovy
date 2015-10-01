package com.runninglama

import grails.transaction.Transactional

@Transactional
class VehiculeService {

    VehiculeDAOService vehiculeDAOService

    def creeOuModifierVehicule(Vehicule vehiculeInstance) {
        vehiculeDAOService.save(vehiculeInstance);
    }

    def supprimerVehicule(Vehicule vehiculeInstance) {
        vehiculeDAOService.delete(vehiculeInstance);
    }

}
