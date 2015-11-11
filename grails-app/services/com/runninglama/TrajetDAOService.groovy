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

//                      eq('dateRetour', new Date(params.dateRetour))
            }
//            maxResults params.int( 'max' ) ?: 10
//            firstResult params.int( 'offset' ) ?: 0
//            if( params.sort && params.order ) order params.sort, params.order
        }
        return result
    }

    def trouver(Long id) {
        return Trajet.findById(id)
    }

    def count() {
        return Trajet.count()
    }
}
