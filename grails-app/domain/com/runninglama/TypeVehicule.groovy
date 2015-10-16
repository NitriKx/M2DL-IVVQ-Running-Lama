package com.runninglama

/**
 * Created by Benoît Sauvère on 15/10/15.
 */
enum TypeVehicule {

    MOTO,
    BUS,
    VOITURE;

    TypeVehicule() {
        nope()
    }

    void nope() {
        // Cette fonction permet de contourner un bug dans l'analyse de la couverture du code
        // Cf. ArrayOutOfBoundException sur les entrée avec: line number="0"
    }
}