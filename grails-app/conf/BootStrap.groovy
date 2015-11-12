import com.runninglama.Trajet
import com.runninglama.TypeVehicule
import com.runninglama.Utilisateur
import com.runninglama.Vehicule
import grails.util.Environment

class BootStrap {

    def init = { servletContext ->

        // On insère les utilisateurs uniquement dans l'environnement de développement
        if (Environment.currentEnvironment == Environment.DEVELOPMENT) {


            Utilisateur utilisateur1 = new Utilisateur(email: 'email@test.fr', nom: "Custoja", prenom: "Julien", pseudo: "JulienCsj",
                    passwordHash: '49440ae7cec983ae6155b1d4d80ee77bd071b115', passwordSalt: 'abc', dateInscription: new Date(),
                    dateDerniereConnexion: new Date(), dateNaissance: new Date(), telephone: "0987675434", vehicules: []).save(flush: true)

            Utilisateur utilisateur2 = new Utilisateur(email: 'email2@test.fr', nom: "unNom", prenom: "unPrenom", pseudo: "unPseudo",
                    passwordHash: '49440ae7cec983ae6155b1d4d80ee77bd071b115', passwordSalt: 'abc', dateInscription: new Date(),
                    dateDerniereConnexion: new Date(), dateNaissance: new Date(), telephone: "0987675434", vehicules: []).save(flush: true)

            // Mot de passe : abcde
            Utilisateur utilisateur3 = new Utilisateur(email: 'email3@test.fr', nom: "Benoit", prenom: "Sauvere", pseudo: "NitriKx",
                    passwordHash: 'fe1a276b6e561c7fd842b442e164bce1d564b3ac', passwordSalt: 'abc', motDePasse: "abcde", motDePasseConfirmation: "abcde",
                    dateInscription: new Date(), dateDerniereConnexion: new Date(), dateNaissance: new Date(), telephone: "0987675434", vehicules: []).save(flush: true)

            Vehicule vehicule = new Vehicule(possesseur: utilisateur1, marque: "Telsa", modele: "Model X", annee: new Date(year: 2015), nb_place: 5,
                    kilometrage: 10000, type: TypeVehicule.VOITURE).save(flush: true);

            Vehicule vehicule2 = new Vehicule(possesseur: utilisateur2, marque: "Citroen", modele: "C4", annee: new Date(year: 2015), nb_place: 5,
                    kilometrage: 10000, type: TypeVehicule.VOITURE).save(flush: true);

            Trajet trajet = new Trajet(conducteur: utilisateur1, vehicule: vehicule, commentaire: "", prix: 29.99, nombrePlace: 3, dateAller: new Date(), dateRetour: new Date(),
                    depart: "Toulouse", departLat: "43.604652", departLng: "1.444209",
                    arrivee: "Pakistan", arriveeLat: "30.375321", arriveeLng: "69.345115").save(flush: true)
        }

    }
    def destroy = {
    }
}
