package com.runninglama

import grails.test.mixin.Mock
import grails.test.mixin.TestFor
import spock.lang.Specification
import spock.lang.Unroll
import org.codehaus.groovy.grails.plugins.codecs.SHA1Codec


/**
 * Created by loicleger on 01/10/15.
 */
@TestFor(UtilisateurService)
@Mock(Utilisateur)
class UtilisateurServiceSpec extends Specification {


    def setup() {
        service.utilisateurDAOService = Mock(UtilisateurDAOService)
        mockCodec(SHA1Codec)
    }

    def cleanup() {
    }

    @Unroll
    def "test qu'un utilisateur se connecte avec des identifiants correct" () {
        given: "un visiteur qui veut se connecter avec son pseudo et son mot de passe et qui est membre "
        String pseudo = "toto"
        String motDePasse = "toto"
        Utilisateur utilisateur = new Utilisateur(pseudo: pseudo)
        utilisateur.motDePasse = motDePasse

        when: "le visiteur veut se connecter"
        service.utilisateurDAOService.findByPseudo(pseudo) >> new Utilisateur(pseudo: "toto", passwordHash: "630547c1fada14c61e876be55ac877e13f5c03d7", passwordSalt: "toto")
        Utilisateur membre = service.verifierIdentifiants(utilisateur)

        then: "le visiteur est connecte"
        membre
        membre.hasErrors() == false

    }

    @Unroll
    def "test qu'un utilisateur veut se connecter avec un pseudo incorrect" () {
        given: "un visiteur qui veut se connecter avec son pseudo et son mot de passe et qui est membre "
        String pseudo = "toti"
        String motDePasse = "toto"
        Utilisateur utilisateur = new Utilisateur(pseudo: pseudo,motDePasse: motDePasse)

        when: "le visiteur veut se connecter"
        service.utilisateurDAOService.findByPseudo(pseudo) >> null
        Utilisateur membre = service.verifierIdentifiants(utilisateur)

        then: "le visiteur n'est pas connecté"
        !membre

    }


    @Unroll
    def "test qu'un utilisateur veut se connecter avec un mot de passe incorrect" () {
        given: "un visiteur qui veut se connecter avec son pseudo et son mot de passe et qui est membre "
        String pseudo = "toto"
        String motDePasse = "toti"
        Utilisateur utilisateur = new Utilisateur(pseudo: pseudo,motDePasse: motDePasse)

        when: "le visiteur veut se connecter"
        service.utilisateurDAOService.findByPseudo(pseudo) >> new Utilisateur(pseudo: "toto",passwordHash: "630547c1fada14c61e876be55ac877e13f5c03d7",passwordSalt: "toto")
        Utilisateur membre = service.verifierIdentifiants(utilisateur)

        then: "le visiteur est connecte"
        !membre

    }

    @Unroll
    def "test qu'un visiteur veut s'inscrire avec des champs corrects" () {
        given: "un visiteur qui veut s'inscrire et qui remplit les champs "
        String pseudo = "toto"
        String motDePasse = "toti"
        String motDePasseConfirmation = "toti"
        String nom = "nom"
        String prenom = "prenom"
        String email ="totoo@toto.toto"
        String telephone = "0505050505"
        String date = new Date()
        Utilisateur utilisateur = new Utilisateur(pseudo:pseudo,motDePasse: motDePasse,motDePasseConfirmation: motDePasseConfirmation,
                                                    nom:nom,prenom: prenom,email: email,telephone: telephone,dateNaissance: date)

        when: "le visiteur veut s'inscrire et clique sur m'inscrire"
        service.inscrireUtilisateur(utilisateur)

        then: "le visiteur est inscrit"
        1 * service.utilisateurDAOService.save(_)

    }

    @Unroll
    def "test qu'un visiteur veut s'inscrire avec des mots de passe différent" () {
        given: "un visiteur qui veut s'inscrire et qui remplit les champs "
        String pseudo = "toto"
        String motDePasse = "toti"
        String motDePasseConfirmation = "toto"
        String nom = "nom"
        String prenom = "prenom"
        String email ="totoo@toto.toto"
        String telephone = "0505050505"
        String date = new Date()
        Utilisateur utilisateur = new Utilisateur(pseudo:pseudo,motDePasse: motDePasse,motDePasseConfirmation: motDePasseConfirmation,
                nom:nom,prenom: prenom,email: email,telephone: telephone,dateNaissance: date)

        when: "le visiteur veut s'inscrire et clique sur m'inscrire"
        def membre = service.inscrireUtilisateur(utilisateur)

        then: "le visiteur est inscrit"
        membre.hasErrors() == true
        membre.errors.getFieldError('motDePasse')

    }

    @Unroll
    def "test qu'un membre veut modifier son profil avec des champs corrects" () {
        given: "un membre qui veut modifier son profil et qui remplit les champs "

        Utilisateur utilisateur = new Utilisateur(pseudo:"toto",nom:"toto",prenom:"toto",email:"toto@toto.fr",telephone:"0505050505",passwordSalt: "abcd",passwordHash: "dee9b46e23c4b18ecb604673e72fcfc8f51fee70")
        String pseudo = "lolo"
        String ancienMotDePasse = "toto"
        String motDePasse = "lalu"
        String motDePasseConfirmation = "lalu"
        String telephone = "0505050505"
        Utilisateur utilisateurModifie = new Utilisateur(pseudo:pseudo,motDePasse: motDePasse,motDePasseConfirmation: motDePasseConfirmation,telephone: telephone)

        when: "le membre valide"
        service.modifierUtilisateur(utilisateur,utilisateurModifie,ancienMotDePasse)

        then: "le membre est bien modfié"
        1 * service.utilisateurDAOService.save(_)
        utilisateur.pseudo=="lolo"
        utilisateur.passwordHash=="92008f37677931d6c8fbba8f7317210ba95c3e7f"
        utilisateur.telephone=="0505050505"
    }

    @Unroll
    def "test qu'un membre veut modifier son profil avec des mots de passe différent" () {
        given: "un membre qui veut modifier son profil et qui remplit les champs "
        Utilisateur utilisateur = new Utilisateur(pseudo:"toto",nom:"toto",prenom:"toto",email:"toto@toto.fr",telephone:"0505050505",passwordSalt: "abcd",passwordHash: "dee9b46e23c4b18ecb604673e72fcfc8f51fee70")
        String pseudo = "lolo"
        String ancienMotDePasse = "toto"
        String motDePasse = "lalu"
        String motDePasseConfirmation = "lalo"
        String telephone = "0505050505"
        Utilisateur utilisateurModifie = new Utilisateur(pseudo:pseudo,motDePasse: motDePasse,motDePasseConfirmation: motDePasseConfirmation,telephone: telephone)

        when: "le membre valide"
        Utilisateur membre = service.modifierUtilisateur(utilisateur,utilisateurModifie,ancienMotDePasse)

        then: "le membre n'est pas modfié et indormé des erreurs"
        membre.hasErrors() == true
        membre.errors.getFieldError('motDePasse')
    }

    @Unroll
    def "test qu'un membre veut modifier son profil avec un ancien mot de passe incorrect" () {
        given: "un membre qui veut modifier son profil et qui remplit les champs "
        Utilisateur utilisateur = new Utilisateur(pseudo:"toto",nom:"toto",prenom:"toto",email:"toto@toto.fr",telephone:"0505050505",passwordSalt: "abcd",passwordHash: "dee9b46e23c4b18ecb604673e72fcfc8f51fee70")
        String pseudo = "lolo"
        String ancienMotDePasse = "toti"
        String motDePasse = "lalu"
        String motDePasseConfirmation = "lalu"
        String telephone = "0505050505"
        Utilisateur utilisateurModifie = new Utilisateur(pseudo:pseudo,motDePasse: motDePasse,motDePasseConfirmation: motDePasseConfirmation,telephone: telephone)

        when: "le membre valide"
        Utilisateur membre = service.modifierUtilisateur(utilisateur,utilisateurModifie,ancienMotDePasse)

        then: "le membre n'est pas modfié et indormé des erreurs"
        membre.hasErrors() == true
        membre.errors.getFieldError('passwordHash')
    }

    void "test la methode liste utilisateur"(){
        when: "on appelle le service pour obtenir la liste des utilisateur"
        def liste = service.listeUtilisateurs(_)

        then: "la bonne couche de DAO est appelée"
        1 * service.utilisateurDAOService.liste(_)
    }
}
