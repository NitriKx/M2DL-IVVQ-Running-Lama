package com.runninglama

import grails.transaction.Transactional

@Transactional
class TrajetDAOService {

    def save(Trajet trajet) {
        trajet.save(flush: true)
    }

    def delete(Trajet trajet) {
        trajet.delete(flush:true)
    }
}
