<%--
  Created by IntelliJ IDEA.
  User: julien
  Date: 30/09/15
  Time: 08:53
--%>

<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <meta name="layout" content="main" />
</head>

<div class="row">
    <div class="col-xs-12">
            <div class="panel panel-default">
                <div class="panel-heading">${trajet.depart} -> ${trajet.arrivee}</div>
                <div class="panel-body">
                    <div class="pull-right">

                        <g:if test="${trajet.conducteur.id == utilisateur.id}">
                            <g:link controller="trajet" action="supprimer" params="[id: trajet.id]" class="btn btn-danger">Supprimer</g:link>
                            <g:link controller="trajet" action="modifier" params="[id: trajet.id]" class="btn btn-primary">Modifier</g:link>
                        </g:if>

                        <g:if test="${!session.utilisateur.participe(trajet)}">
                            <g:link controller="trajet" action="ajouterParticipant" params="[idTrajet: trajet.id]" class="btn btn-success">Participer</g:link>
                        </g:if>
                    </div>
                    <ul>
                        <li>Date aller : ${trajet.dateAller}</li>
                        <li>Date retour : ${trajet.dateRetour}</li>
                        <li>Prix : ${trajet.prix}</li>
                        <li>Commentaire : ${trajet.commentaire}</li>
                        <li>Nombre place : ${trajet.nombrePlace}</li>
                        <li>Nombre place : ${trajet.nombrePlace}</li>

                        <li>Conducteur : ${trajet.conducteur.prenom} ${trajet.conducteur.nom}</li>
                        <li>Véhicule : ${trajet.vehicule.marque} ${trajet.vehicule.modele}</li>
                        <li>Les participants :
                            <ul>
                               <g:each in="${trajet.participants}" var="participant">
                                   <li>${participant.nom} ${participant.prenom}</li>
                               </g:each>
                            </ul>
                        </li>
                    </ul>
                </div>
            </div>
    </div>
</div>

</html>