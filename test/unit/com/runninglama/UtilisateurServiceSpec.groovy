package com.runninglama

import grails.test.mixin.Mock
import grails.test.mixin.TestFor
import org.springframework.validation.FieldError
import spock.lang.Specification
import spock.lang.Unroll
import org.codehaus.groovy.grails.plugins.codecs.SHA1Codec

import java.lang.reflect.Field

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
        Utilisateur utilisateur = new Utilisateur(pseudo: pseudo,motDePasse: motDePasse)

        when: "le visiteur veut se connecter"
        service.utilisateurDAOService.findByPseudo(pseudo) >> new Utilisateur(pseudo: "toto",passwordHash: "630547c1fada14c61e876be55ac877e13f5c03d7",passwordSalt: "toto")
        Utilisateur membre = service.verifierIdentifiants(utilisateur)

        then: "le visiteur est connecte"
        membre
        !membre.hasErrors()

    }

    @Unroll
    def "test qu'un utilisateur veut se connecter avec un pseudo incorrect" () {
        given: "un visiteur qui veut se connecter avec son pseudo et son mot de passe et qui est membre "
        String pseudo = "toti"
        String mdp = "toto"

        when: "le visiteur veut se connecter"
        service.utilisateurDAOService.findByPseudo(pseudo) >> null
        Utilisateur membre = service.verifierIdentifiants(pseudo,mdp)

        then: "le visiteur est connecte"
        !membre

    }


    @Unroll
    def "test qu'un utilisateur veut se connecter avec un mot de passe incorrect" () {
        given: "un visiteur qui veut se connecter avec son pseudo et son mot de passe et qui est membre "
        String pseudo = "toto"
        String mdp = "toti"

        when: "le visiteur veut se connecter"
        service.utilisateurDAOService.findByPseudo(pseudo) >> new Utilisateur(pseudo: "toto",passwordHash: "630547c1fada14c61e876be55ac877e13f5c03d7",passwordSalt: "toto")
        Utilisateur membre = service.verifierIdentifiants(pseudo,mdp)

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
        service.inscrireUtilisateur(utilisateur)

        then: "le visiteur est inscrit"
        utilisateur.hasErrors()
        utilisateur.errors.getFieldError('motDePasse') == 'nullable'

    }

}
