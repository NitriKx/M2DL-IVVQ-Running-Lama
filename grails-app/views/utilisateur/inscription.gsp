<%--
  Created by IntelliJ IDEA.
  User: julien
  Date: 30/09/15
  Time: 08:53
--%>

<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Inscription</title>
    <meta name="layout" content="main" />
</head>
<div class="container" style="margin-top: 45px;">
    <div class="row">
        <div class="col-xs-12 col-sm-8 col-md-6 col-sm-offset-2 col-md-offset-3">
            <g:form name="formInscription" url="[controller:'utilisateur',action:'inscriptionPost']">
                <h2>Inscription <small>Totalement gratuit, pour toujours.</small></h2>
                <hr class="colorgraph">
                <div class="row">
                    <g:hasErrors bean="${utilisateur}">
                        <div class="alert alert-danger">
                        <ul>
                            <g:eachError var="err" bean="${utilisateur}">
                                <li>${err}</li>
                            </g:eachError>
                        </ul>
                        </div>
                    </g:hasErrors>
                    <g:if test="${utilisateur?.hasErrors() == false}">
                        <div class="alert alert-success">
                            <p>Votre inscription a bien été enregistrée.</p>
                        </div>
                    </g:if>
                </div>
                <div class="row">
                    <div class="col-md-12">
                        <p>Si vous possedez déjà un compte sur Running Lama, vous pouvez vous connecter en cliquant <g:link controller="utilisateur" action="connexion">ici</g:link>.</p>
                    </div>
                    <div class="col-xs-12 col-sm-6 col-md-6">
                        <div class="form-group">
                            <input type="text" name="nom" id="nom" class="form-control" value="${fieldValue(bean:utilisateur,field:'nom')}" placeholder="Nom" tabindex="1">
                        </div>
                    </div>
                    <div class="col-xs-12 col-sm-6 col-md-6">
                        <div class="form-group">
                            <input type="text" name="prenom" id="prenom" class="form-control" value="${fieldValue(bean:utilisateur,field:'prenom')}" placeholder="Prénom" tabindex="2">
                        </div>
                    </div>
                </div>
                <div class="form-group">
                    <input type="text" name="pseudo" id="pseudo" class="form-control" placeholder="Pseudo" value="${fieldValue(bean:utilisateur,field:'pseudo')}" tabindex="3">
                </div>
                <div class="form-group">
                    <input type="email" name="email" id="email" class="form-control" placeholder="Adresse Email" value="${fieldValue(bean:utilisateur,field:'email')}" tabindex="4">
                </div>
                <div class="row">
                    <div class="col-xs-12 col-sm-6 col-md-6">
                        <div class="form-group">
                            <input type="password" name="motDePasse" id="motDePasse" value="${fieldValue(bean:utilisateur,field:'motDePasse')}" class="form-control" placeholder="Mot de passe"  tabindex="5">
                        </div>
                    </div>
                    <div class="col-xs-12 col-sm-6 col-md-6">
                        <div class="form-group">
                            <input type="password" name="motDePasseConfirmation" id="motDePasseConfirmation" class="form-control " value="${fieldValue(bean:utilisateur,field:'motDePasseConfirmation')}" placeholder="Mot de passe (confirmation)" tabindex="6">
                        </div>
                    </div>
                </div>
                <div class="row">
                    <div class="col-xs-12 col-sm-6 col-md-6">
                        <div class="form-group">
                            <g:datePicker name="dateNaissance" value="${new Date()}" precision="day"/>

                        </div>
                    </div>
                    <div class="col-xs-12 col-sm-6 col-md-6">
                        <div class="form-group">
                            <input type="text" name="telephone" id="telephone" class="form-control " value="${fieldValue(bean:utilisateur,field:'telephone')}" placeholder="Téléphone" tabindex="6">
                        </div>
                    </div>
                </div>

                <hr class="colorgraph">
                <div class="row">
                    <div class="col-xs-12 col-md-6 col-md-offset-6"><input type="submit" value="M'inscrire" class="btn btn-primary btn-block btn-lg" tabindex="7"></div>
                </div>
            </g:form>
        </div>
    </div>
</div>
</html>