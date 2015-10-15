package com.runninglama

import grails.transaction.Transactional

@Transactional
class TrajetService {

    TrajetDAOService trajetDAOService

    def ajouterTrajet(Trajet trajet) {
        trajet.validate()
        println(trajet)
        if(trajet.hasErrors()) {
            println("******ERR"+trajet.getErrors())
        }

        trajetDAOService.save(trajet)
    }
}
