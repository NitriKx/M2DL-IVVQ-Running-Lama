package com.runninglama

import grails.transaction.Transactional

@Transactional
class TrajetDAOService {

    def save(Trajet trajet) {
        trajet.save(flush: true)
    }

    def delete(Trajet trajet) {
        trajet.delete(flush: true)
    }

    def search(params) {
        def c = Trajet.createCriteria()
        def result = c.list{
            and
                    {
                        ilike ('depart', '%' + params.depart_google + '%')
                        ilike ('arrivee', '%' + params.arrivee_google + '%')
                        le('prix', Float.parseFloat(params.prixMax))
                        eq('dateAller', params.dateAller)
                        if(params.boolDateRetour) { eq('dateRetour', params.dateRetour)}
                    }
        }
        return result
    }
}
