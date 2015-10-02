package com.runninglama

import grails.transaction.Transactional

@Transactional
class VehiculeDAOService {

    def save(Vehicule vehiculeInstance) {
        vehiculeInstance.save(flush: true);
    }

    def delete(Vehicule vehiculeInstance) {
        vehiculeInstance.delete(flush: true);
    }

    def list(params) {
        Vehicule.list(params)
    }

    def count(params) {
        Vehicule.count()
    }
}
