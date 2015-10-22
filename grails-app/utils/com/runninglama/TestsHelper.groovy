package com.runninglama

import com.runninglama.TypeVehicule
import com.runninglama.Utilisateur
import com.runninglama.Vehicule

/**
 * Created by benoit on 08/10/15.
 */
class TestsHelper {

    static creeUtilisateurValide() {
        def params = [:]
        params["email"] = 'email@test.fr'
        params["nom"] = 'Custoja'
        params["prenom"] = 'Julien'
        params["pseudo"] = 'TheRunningLama'
        params["passwordHash"] = 'jhdfqsfgqsdufhqsduhfqsuidfqsdf'
        params["passwordSalt"] = 'qsdqfqsdfsdfqgfgtryurdx'
        params["dateInscription"] = new Date()
        params["dateDerniereConnexion"] = new Date()
        params["dateNaissance"] = new Date()
        params["email"] = 'julien.c@test.fr'
        params["telephone"] = '0987675434'
        def utilisateurValide = new Utilisateur(email: 'email@test.fr', nom: "Custoja", prenom: "Julien", pseudo: "TheRunningLama",
                passwordHash: 'jhdfqsfgqsdufhqsduhfqsuidfqsdf', passwordSalt: 'qsdqfqsdfsdfqgfgtryurdx', dateInscription: new Date(),
                dateDerniereConnexion: new Date(), dateNaissance: new Date(), telephone: "0987675434", vehicules: [])
        return utilisateurValide
    }

    static creeVehiculeValide(Utilisateur possesseur) {
        new Vehicule(possesseur: possesseur, marque: "Telsa", modele: "Model X", annee: new Date(year: 2015), nb_place: 5,
                kilometrage: 10000, type: TypeVehicule.VOITURE);
    }

    static creeTrajetValide(Utilisateur utilisateur, Vehicule vehicule) {

        def trajet = new Trajet(depart: "Toulouse", departLat: "1234", departLng: "1234",
                arrivee: "Muret", arriveeLat: "12341234",
                dateAller: new Date(), dateRetour: new Date(),
                commentaire: "Un commentaire", prix:34, nombrePlace: 5,
                conducteur: utilisateur, vehicule: vehicule, participants: null)
        return trajet
    }


}
