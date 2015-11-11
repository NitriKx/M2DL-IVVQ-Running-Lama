package com.runninglama

import grails.transaction.Transactional

@Transactional
class TrajetDAOService {

    def liste(params) {
        Trajet.list(params)
    }

    def save(Trajet trajet) {
        trajet.save(flush: true)
    }

    def delete(Trajet trajet) {
        trajet.delete(flush: true)
    }

    def search(params) {
        def c = Trajet.createCriteria()
        def result = c.list {
            and {
                ilike('depart', '%' + params.depart_google + '%')
                ilike('arrivee', '%' + params.arrivee_google + '%')
                if(params.prix) le('prix', Float.parseFloat(params.prixMax))
                if(params.dateAller) eq('dateAller', params.dateAller)
                //if(params.dateRetour) eq('dateRetour', params.dateRetour)
            }
        }
        return result
    }

    def trouver(Long id) {
        return Trajet.findById(id)
    }

    def count() {
        return Trajet.count()
    }

    def saveNotation(Notation notation) {
        notation.save(flush: true)
    }
}
