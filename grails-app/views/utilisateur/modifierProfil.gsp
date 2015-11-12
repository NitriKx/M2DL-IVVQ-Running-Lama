<!DOCTYPE html>
<html>
<head>
    <meta name="layout" content="main"/>
</head>
<body>

<!-- Page Content -->
<div class="container" style="margin-top: 45px;">
    <div class="row">
        <div class="col-xs-12 col-sm-8 col-md-6 col-sm-offset-2 col-md-offset-3">
            <g:form name="formInscription" url="[controller:'utilisateur',action:'modifierProfilPost']">
                <h2>Profil utilisateur <small>Modification</small></h2>
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
                </div>
                <div class="row">
                    <div class="col-xs-12 col-sm-6 col-md-6">
                        <div class="form-group">
                            <input type="text" title="Nom" name="nom" id="nom" class="form-control" value="${fieldValue(bean:utilisateur,field:'nom')}" placeholder="Nom" tabindex="1">
                        </div>
                    </div>
                    <div class="col-xs-12 col-sm-6 col-md-6">
                        <div class="form-group">
                            <input type="text" title="Prenom" name="prenom" id="prenom" class="form-control" value="${fieldValue(bean:utilisateur,field:'prenom')}" placeholder="Prénom" tabindex="2">
                        </div>
                    </div>
                </div>
                <div class="form-group">
                    <input type="text" title="Pseudo" name="pseudo" id="pseudo" class="form-control" placeholder="Pseudo" value="${fieldValue(bean:utilisateur,field:'pseudo')}" tabindex="3">
                </div>
                <div class="form-group">
                    <input type="email" title="Email" name="email" id="email" class="form-control" placeholder="Adresse Email" value="${fieldValue(bean:utilisateur,field:'email')}" tabindex="4">
                </div>
                <div class="form-group">
                    <input type="password" title="Ancien mot de passe" name="ancienMotDePasse" id="ancienMotDePasse" class="form-control" placeholder="Ancien mot de passe" tabindex="4">
                </div>
                <div class="row">
                    <div class="col-xs-12 col-sm-6 col-md-6">
                        <div class="form-group">
                            <input type="password" title="Nouveau mot de passe" name="motDePasse" id="motDePasse" value="${fieldValue(bean:utilisateur,field:'motDePasse')}" class="form-control" placeholder="Nouveau mot de passe"  tabindex="5">
                        </div>
                    </div>
                    <div class="col-xs-12 col-sm-6 col-md-6">
                        <div class="form-group">
                            <input type="password" title="Confirmation" name="motDePasseConfirmation" id="motDePasseConfirmation" class="form-control" value="${fieldValue(bean:utilisateur,field:'motDePasseConfirmation')}" placeholder="Confirmation" tabindex="6">
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
                            <input type="text" title="Telephone" name="telephone" id="telephone" class="form-control" value="${fieldValue(bean:utilisateur,field:'telephone')}" placeholder="Téléphone" tabindex="6">
                        </div>
                    </div>
                </div>

                <hr class="colorgraph">
                <div>
                    <div class="col-xs-12 col-md-6 col-md-offset-6"><input type="submit" value="Modifier Profil" class="btn btn-primary btn-block btn-lg" tabindex="7"></div>
                </div>
            </g:form>
        </div>
    </div>
</div>
<!-- /.container -->
</body>
</html>
