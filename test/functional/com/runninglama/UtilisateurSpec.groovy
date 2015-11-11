package com.runninglama

import geb.spock.GebSpec
import spock.lang.*

@Stepwise
class UtilisateurSpec extends GebSpec {


    def seConnecter() {
        to ConnexionPage
        pseudo = "JulienCsj"
        motDePasse = "test"
        btn.click()

        at AccueilPageMembre

    }

    def "Etant donné un visiteur sur la page de connexion quand je rentre des identifiants valides alors je suis redirigé sur la page d'accueil ou sur la page que je consultais juste avant (ex: reservation de trajet)"() {
        when:
        to ConnexionPage
        pseudo = "JulienCsj"
        motDePasse = "test"
        btn.click()

        then:" L'utilisateur est redirigé vers l'accueil de l'application"
        at AccueilPageMembre
        and: "Une alerte lui indique qu'il est bien connecté"
        page.contains("Vous êtes bien connecté !")
    }

    def "Etant donné un visiteur sur la page de connexion quand je rentre des identifiants non valides alors je reçois un message avec les champs erronés"() {
        when:
        to ConnexionPage
        pseudo = "JulienCsj"
        motDePasse = "testtttt"
        btn.click()

        then: "On reste sur la page de connexion"
        at ConnexionPage
        and: "Une alerte est affichée sur la page"
        page.contains("Identifiants Incorrects")
    }

    def "Etant donné un visiteur sur la page d'accueil quand je clique sur le bouton mon profil alors je suis redirigé vers la page de mon profil"() {

        seConnecter()

        when: "L'utilisateur accède a son profil"
        lienProfil.click()

        then: "Il est redirigé sur son profil"
        at ProfilPage

        when: "Il modifie une information et sauvegarde"
        inputNom.value("Test")
        btnModifier.click()

        then:"Il est rediriger sur la page d'accueil"
        at AccueilPageMembre
        and:"Une alerte lui confirmela sauvegarde"
        page.contains("Votre profil a été sauvegardé.")

    }

    def "Etant donné un membre sur la page de modification de son profil quand je modifie mes champs avec des valeurs non valides alors je suis informé des erreurs que j'ai commises"() {
        seConnecter()

        when: "L'utilisateur accède a son profil"
        lienProfil.click()

        then: "Il est redirigé sur son profil"
        at ProfilPage

        when: "Il modifie une information et sauvegarde"
        inputNom.value("")
        btnModifier.click()

        then:"Il est rediriger sur la page de modification du profil"
        at ProfilPage
        and:"Une alerte lui confirme la présence d'erreur"
        page.contains("ne peut pas être nulle")
    }

    def "Etant donné un visiteur sur la page d'inscription, une demande d'inscription avec les bonnnes informations incroit bien l'utilisateur"() {
        when: "L'utilisateur accède au formulaire d'inscription"
        to InscriptionPage
        inputNom.value("Dupont")
        inputPrenom.value("Jean")
        inputPseudo.value("TheRunningLama")
        inputEmail.value("test@test.fr")
        inputMotDePasse("azerty")
        inputMotDePasseConfirmation("azerty")
        inputTelephone("0567843672")
        btnInscription.click()

        then:"L'utilisateur est redirigé sur la page d'inscription"
        at InscriptionPage

        and: "Une alerte confirme que l'inscription s'est bien passée"
        page.contains("Votre inscription a bien été enregistrée.")
    }

    def "Etant donné un visiteur sur la page d'inscription, une demande d'inscription avec les mauvaises informations n'inscrit pas l'utilisateur"() {
        when: "L'utilisateur accède au formulaire d'inscription"
        to InscriptionPage
        inputNom.value("Dupont")
        inputPrenom.value("")
        inputPseudo.value("TheRunningLama")
        inputEmail.value("test@test.fr")
        inputMotDePasse("azerty")
        inputMotDePasseConfirmation("azerty")
        inputTelephone("0567843672")
        btnInscription.click()

        then:"L'utilisateur est redirigé sur la page d'inscription"
        at InscriptionPage

        and: "Une alerte confirme que l'inscription s'est bien passée"
        page.contains("ne peut pas être nulle")
    }

}