<!DOCTYPE html>
<html>
<head>
    <meta name="layout" content="main"/>
</head>
<body>

<!-- Page Content -->
<div class="container" style="margin-top: 25px;">

    <div class="row">
        <div class="col-md-4 col-md-offset-4">
            <h2 class="page-header">Connexion</h2>
            <p>Pour acceder à certaines fonctionnalités (ajout de trajet, inscription à un trajet, ...), vous devez être connecté.<br>
            Si vous n'avez pas de compte sur Running-Lama, vous pouvez en créer un en cliquant <g:link controller="utilisateur" action="inscription">ici</g:link>.<br> </p>
            <g:if test="${message}">
                <div class="alert alert-danger">
                    <p>${message}</p>
                </div>
            </g:if>
            <g:form class="form-signin" url="[controller:'utilisateur',action:'connexionPost']">
                <input name="pseudo" type="text" class="form-control" value="${fieldValue(bean:utilisateur,field:'pseudo')}" placeholder="Pseudo" required autofocus>
                <br>
                <input name="motDePasse" type="password" class="form-control" value="${fieldValue(bean:utilisateur,field:'motDePasse')}" placeholder="Mot de Passe" required>
                <br>
                <button class="btn btn-lg btn-success btn-block" type="submit">
                    Se Connecter</button>
            </g:form>
        </div>
    </div>

</div>
<!-- /.container -->
</body>
</html>
