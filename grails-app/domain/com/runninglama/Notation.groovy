package com.runninglama

class Notation {

    int note
    String commentaire
    Utilisateur participant

    static belongsTo = Trajet

    static constraints = {
        note            nullable: true, blank: true, min: 0, max: 5
        commentaire     nullable: true, blank: true
        participant     nullable: true, blank: true
    }
}
