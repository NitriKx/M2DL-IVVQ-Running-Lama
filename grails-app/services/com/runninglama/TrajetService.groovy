package com.runninglama

import grails.transaction.Transactional

import javax.servlet.http.HttpSession

@Transactional
class TrajetService {

    TrajetDAOService trajetDAOService

    def creerOuModifier(Trajet trajet) {
        trajet.validate()
        trajetDAOService.save(trajet)
    }

    def supprimer(Trajet trajet) {
        trajetDAOService.delete(trajet)
    }

    def rechercherTrajet(params) {
        trajetDAOService.search(params)
    }

    def trouverTrajet(Long id) {
        trajetDAOService.trouver(id)
    }

    def noterTrajet(Trajet trajetInstance, params, HttpSession session, Long idUtilisateur) {
//        trajetDAOService.noterTrajet(trajetInstance, params)
        if(trajetInstance.listeCommentaireNote == null || trajetInstance.listeCommentaireNote?.isEmpty())
        {
            trajetInstance.listeCommentaireNote = new ArrayList<LinkedHashMap<Long, String>>()
        }
        if(trajetInstance.listeNote == null || trajetInstance.listeNote?.isEmpty())
        {
            trajetInstance.listeNote = new ArrayList<LinkedHashMap<Long, String>>()
        }
        if(trajetInstance.listeNoteur == null || trajetInstance.listeNoteur?.isEmpty())
        {
            trajetInstance.listeNoteur = new ArrayList<Long>()
        }


        def comNoteMap = new LinkedHashMap<Long, String>()
        comNoteMap.put(idUtilisateur, params['commentaireNote'])
        trajetInstance.listeCommentaireNote.add(comNoteMap)

        def noteMap = new LinkedHashMap<Long, Integer>()
        noteMap.put(idUtilisateur, Integer.parseInt(params['note']))
        trajetInstance.listeNote.add(noteMap)

        trajetInstance.listeNoteur.add(idUtilisateur)

        params['note'] = (Integer.parseInt(params['note']) < 0) ? "0" : params['note']
        params['note'] = (Integer.parseInt(params['note']) > 5) ? "5" : params['note']
        trajetInstance.getConducteur().noteMoyenne = (trajetInstance.getConducteur().noteMoyenne) ? (trajetInstance.getConducteur().noteMoyenne + Float.parseFloat(params['note']))/Trajet.countByConducteur(trajetInstance.getConducteur()) : Float.parseFloat(params['note'])

//        Utilisateur cur = trajetInstance.getConducteur().merge();
//        session['utilisateur'] = cur
//        cur.save(flush: true)
        trajetDAOService.save(trajetInstance)
    }

//    def getAutorisationNotation(Trajet trajet, Utilisateur utilisateur) {
//        trajetDAOService.getAutorisationNotation(trajet, utilisateur)
//    }
}
