package com.runninglama

import grails.transaction.Transactional

@Transactional
class VehiculeService {

    def creeOuModifierVehicule(Vehicule vehiculeInstance) {
        vehiculeInstance.save();
    }

    def supprimerVehicule(Vehicule vehiculeInstance) {
        vehiculeInstance.delete(flush: true);
    }


}
