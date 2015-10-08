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

    def list(Utilisateur possesseur, params) {
        Vehicule.findAllByPossesseur(possesseur, params)
    }

    def count(Utilisateur possesseur, params) {
        Vehicule.countByPossesseur(possesseur, params)
    }
}
