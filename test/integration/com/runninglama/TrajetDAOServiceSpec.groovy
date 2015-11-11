package com.runninglama

import spock.lang.*

/**
 *
 */
class TrajetDAOServiceSpec extends Specification {

    def trajetDAOService
    def trajetService

    def setup() {
    }

    def cleanup() {
    }

    void "teste que la méthode save enregistre bien une instance en base de données"() {
        given: "un trajet valide pas en base de données"
        def utilisateur = TestsHelper.creeUtilisateurValide();
        utilisateur = utilisateur.save(flush: true)
        def vehicule = TestsHelper.creeVehiculeValide(utilisateur);
        vehicule = vehicule.save(flush: true)
        def trajet = TestsHelper.creeTrajetValide(utilisateur, vehicule);
        trajet.save(flush: true)

        when: "on demande l'ajout d'un trajet"
        def resultatAjout = trajetDAOService.save(trajet)

        then: "le trajet existe dans la base de données"
        resultatAjout != null
        Trajet.findAllById(resultatAjout.id).size() == 1
    }

//    void "teste que la méthode search retourne bien une liste de trajet de la base de données"() {
//        given: "plusieurs trajets valides en base de données"
//        def utilisateur = TestsHelper.creeUtilisateurValide();
//        utilisateur = utilisateur.save(flush: true)
//        def vehicule = TestsHelper.creeVehiculeValide(utilisateur);
//        vehicule = vehicule.save(flush: true)
//        def trajet = TestsHelper.creeTrajetValide(utilisateur, vehicule);
//        trajet.save(flush: true)
//        def trajet2 = TestsHelper.creeTrajetValide2(utilisateur, vehicule);
//        trajet2.save(flush: true)
//        def trajet3 = TestsHelper.creeTrajetValide3(utilisateur, vehicule);
//        trajet3.save(flush: true)
//
//        trajetDAOService.save(trajet)
//        trajetDAOService.save(trajet2)
//        trajetDAOService.save(trajet3)
//
//        when: "on demande la recherche d'un trajet"
//        def params
//        params['depart_google']     = 'okinawa'
//        params['arrivee_google']    = 'osaka'
//        params['prixMax']           = 100
//        params['dateAller']         = new Date()
//        params['dateRetour']        = new Date()
//        def resultatRecherche = trajetDAOService.search(params)
//
//        then: "le trajet existe dans la base de données"
//        resultatRecherche != null
//        resultatRecherche.size() == 2
//    }


    void "test la notation d'un trajet"() {
        given: "un trajet a sauvegarder"
        Utilisateur conducteur = TestsHelper.creeUtilisateurValide()
        conducteur.save(flush: true)

        Utilisateur utilisateur1 = TestsHelper.creeUtilisateurValide()
        utilisateur1.save(flush: true)
        Utilisateur utilisateur2 = TestsHelper.creeUtilisateurValide()
        utilisateur2.save(flush: true)

        Vehicule vehicule = TestsHelper.creeVehiculeValide(conducteur)
        vehicule.save(flush: true)

        Trajet trajet = TestsHelper.creeTrajetValide3(conducteur, vehicule)
        trajet.addToParticipants(utilisateur1)
        trajet.addToParticipants(utilisateur2)
        trajet.save(flush: true)

        def params1 = ['note' : '3', 'commentaireNote' : 'Super trajet !']
        def params2 = ['note' : '1', 'commentaireNote' : 'Super trajet !']

        when:
        trajetService.noterTrajet(trajet,params1,utilisateur1)
        trajetService.noterTrajet(trajet,params2,utilisateur2)

        then:
        trajet.notations.size() == 2
        trajet.conducteur.noteMoyenne == 5.5
    }
}
