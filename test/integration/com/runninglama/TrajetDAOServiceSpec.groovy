package com.runninglama

import spock.lang.*

/**
 *
 */
class TrajetDAOServiceSpec extends Specification {

    def trajetDAOService

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

        then: "le trajet existe dans la base de données et le conducteur participe au trajet"
        resultatAjout != null
        utilisateur.participe(trajet) == true
        Trajet.findAllById(resultatAjout.id).size() == 1
    }

    void "test la methode trouver"() {
        given: "un trajet valide pas en base de données"
        def utilisateur = TestsHelper.creeUtilisateurValide();
        utilisateur = utilisateur.save(flush: true)
        def vehicule = TestsHelper.creeVehiculeValide(utilisateur);
        vehicule = vehicule.save(flush: true)
        def trajet = TestsHelper.creeTrajetValide(utilisateur, vehicule);
        trajet.save(flush: true)

        when: "on demande a récupérer le trajet"
        def resultatAjout = trajetDAOService.save(trajet)

        then: "le trajet existe dans la base de données"
        trajetDAOService.trouver(trajet.id) != null

    }

    void "teste que la méthode search retourne bien une liste de trajet de la base de données"() {
        given: "plusieurs trajets valides en base de données"
        def utilisateur = TestsHelper.creeUtilisateurValide();
        utilisateur = utilisateur.save(flush: true)
        def vehicule = TestsHelper.creeVehiculeValide(utilisateur);
        vehicule = vehicule.save(flush: true)

        def trajet = TestsHelper.creeTrajetValide(utilisateur, vehicule);
        def trajet2 = TestsHelper.creeTrajetValide2(utilisateur, vehicule);
        trajet2.save(flush: true)
        def trajet3 = TestsHelper.creeTrajetValide3(utilisateur, vehicule);
        trajet3.save(flush: true)

        trajetDAOService.save(trajet)
        trajetDAOService.save(trajet2)
        trajetDAOService.save(trajet3)

        when: "on demande la recherche d'un trajet"
        def params = new HashMap<String, Object>()
        params['depart_google']     = 'okinawa'
        params['arrivee_google']    = 'osaka'


        def resultatRecherche = trajetDAOService.search(params)
        then: "le trajet existe dans la base de données"
        resultatRecherche != null
        resultatRecherche.size() == 2
    }

    void "test la suppression d'un trajet"() {
        given: "un trajet valide pas en base de données"
        def utilisateur = TestsHelper.creeUtilisateurValide();
        utilisateur = utilisateur.save(flush: true)
        def vehicule = TestsHelper.creeVehiculeValide(utilisateur);
        vehicule = vehicule.save(flush: true)
        def trajet = TestsHelper.creeTrajetValide(utilisateur, vehicule);
        trajet.save(flush: true)

        when:"On supprime le trajet"
        trajetDAOService.delete(trajet)

        then:
        Trajet.countById(trajet.id) == 0
    }


    void "teste que la méthode count retourne bien un trajet de plus lorsqu'on en ajoute un"() {
        given: "un trajet valide pas en base de données"
        def utilisateur = TestsHelper.creeUtilisateurValide();
        utilisateur = utilisateur.save(flush: true)
        def vehicule = TestsHelper.creeVehiculeValide(utilisateur);
        vehicule = vehicule.save(flush: true)
        def trajet = TestsHelper.creeTrajetValide(utilisateur, vehicule);
        def compteurAvantAjout = trajetDAOService.count();

        when:"On ajoute le trajet"
        trajetDAOService.save(trajet)

        then:
        trajetDAOService.count() == compteurAvantAjout + 1
    }


    void "teste que la méthode liste contient bien un trajet que l'on vient d'ajouter"() {
        given: "un trajet valide pas en base de données"
        def utilisateur = TestsHelper.creeUtilisateurValide();
        utilisateur = utilisateur.save(flush: true)
        def vehicule = TestsHelper.creeVehiculeValide(utilisateur);
        vehicule = vehicule.save(flush: true)
        def trajet = TestsHelper.creeTrajetValide(utilisateur, vehicule);

        when:"On ajoute le trajet"
        trajetDAOService.save(trajet)

        then:
        trajetDAOService.liste().contains(trajet)
    }

}
