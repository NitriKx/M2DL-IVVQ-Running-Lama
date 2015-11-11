<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>Running Lama - Réseau social de covoiturage</title>

    <!-- Bootstrap Core CSS -->
    <link rel="stylesheet" type="text/css" href="//netdna.bootstrapcdn.com/bootstrap/3.1.1/css/bootstrap.min.css">

    <!-- Custom CSS -->
    <link href="<g:resource dir="css" file="modern-business.css" />" rel="stylesheet">

    <!-- Custom Fonts -->
    <link href="<g:resource dir="font-awesome/css/" file="font-awesome.min.css" />" rel="stylesheet" type="text/css">

    <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
        <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
        <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
    <!-- Bootstrap Core JavaScript -->
    <script src="<g:resource dir="js" file="jquery.js" />"></script>
    <script src="<g:resource dir="js" file="bootstrap.min.js" />"></script>

</head>

<body>

<!-- Navigation -->
<nav class="navbar navbar-inverse navbar-fixed-top" role="navigation">
    <div class="container">
        <!-- Brand and toggle get grouped for better mobile display -->
        <div class="navbar-header">
            <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1">
                <span class="sr-only">Toggle navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <g:link class="navbar-brand" controller="accueil" action="index">Running Lama</g:link>
        </div>
        <!-- Collect the nav links, forms, and other content for toggling -->
        <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
        <ul class="nav navbar-nav navbar-right">
            <li>
                <g:link controller="accueil" action="index">Accueil</g:link>
            </li>
            <li>
                <g:if test="${session.utilisateur}"><g:link controller="trajet" action="ajouterTrajet">Ajouter trajet</g:link></g:if>
                <g:else><a href="#" data-toggle="modal" data-target="#modalConnexion">Ajouter trajet</a></g:else>
            </li>
            <li>
                <g:link controller="trajet" action="liste">Liste des trajets</g:link>

            </li>
            <li>
                <a href="contact.html">Rechercher un trajet</a>
            </li>
            <li class="dropdown">
                <a href="#" class="dropdown-toggle" data-toggle="dropdown"><g:if test="${session.utilisateur}">${session.utilisateur.prenom} ${session.utilisateur.nom}</g:if><g:else>Espace membre</g:else> <b class="caret"></b></a>
                <ul class="dropdown-menu">
                    <g:if test="${!session.utilisateur}">
                        <li><g:link controller="utilisateur" action="connexion"  id="connexion"><i class="fa fa-sign-in"></i> Connexion</g:link></li>
                        <li><g:link controller="utilisateur" action="inscription"  id="inscription"><i class="fa fa-hand-o-right"></i> Inscription</g:link></li>
                    </g:if>
                    <g:if test="${session.utilisateur}">
                        <li><g:link controller="utilisateur" action="modifierProfil" id="profil"><i class="fa fa-user"></i> Mon profil</g:link></li>
                        <li><g:link controller="vehicule" action="index" id="vehicule"><i class="fa fa-car"></i> Gérer mes véhicules</g:link></li>
                        <li><g:link controller="utilisateur" action="deconnexion" id="deconnexion"><i class="fa fa-sign-out"></i> Déconnexion</g:link></li>
                    </g:if>
                </ul>
            </li>
        </ul>
        </div>
        <!-- /.navbar-collapse -->
    </div>
    <!-- /.container -->
</nav>

<g:if test="${isAccueil}">
    <!-- Header Carousel -->
    <header id="myCarousel" class="carousel slide">
        <!-- Indicators -->
        <ol class="carousel-indicators">
            <li data-target="#myCarousel" data-slide-to="0" class="active"></li>
            <li data-target="#myCarousel" data-slide-to="1"></li>
            <li data-target="#myCarousel" data-slide-to="2"></li>
        </ol>

        <!-- Wrapper for slides -->
        <div class="carousel-inner">
            <div class="item active">
                <div class="fill" style="background-position: 0% 75%; background-image:url('https://pixabay.com/get/63ee2325a1b5f537043c/1446929401/california-210913_1920.jpg?direct');"></div>
                <div class="carousel-caption">
                    <h2>Running Lama - Réseau social de covoiturage</h2>
                </div>
            </div>
            <div class="item">
                <div class="fill" style="background-position: 0% 65%; background-image:url('https://pixabay.com/get/b5f68c705041b7be016b/1446929801/hippy-van-926290_1280.jpg?direct');"></div>
                <div class="carousel-caption">
                    <h2>Running Lama - Réseau social de covoiturage</h2>
                </div>
            </div>
        </div>

        <!-- Controls -->
        <a class="left carousel-control" href="#myCarousel" data-slide="prev">
            <span class="icon-prev"></span>
        </a>
        <a class="right carousel-control" href="#myCarousel" data-slide="next">
            <span class="icon-next"></span>
        </a>
    </header>
</g:if>
<g:if test="${flash?.size() > 0}">
    <br>
    <div class="container">
        <div class="row">
            <div class="col-md-12">
                <g:if test="${flash.success}">
                    <div class="alert alert-success">
                        ${flash.success}
                    </div>
                </g:if>
                <g:if test="${flash.warning}">
                    <div class="alert alert-warning">
                        ${flash.warning}
                    </div>
                </g:if>
                <g:if test="${flash.danger}">
                    <div class="alert alert-danger">
                        ${flash.danger}
                    </div>
                </g:if>
                <g:if test="${flash.info}">
                    <div class="alert alert-info">
                        ${flash.info}
                    </div>
                </g:if>
            </div>
        </div>
    </div>
</g:if>

<g:layoutBody />

<!-- Modal -->
<div class="modal fade" id="modalConnexion" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" id="myModalLabel">Oups ! Cette partie du site est réservée aux utilisateurs connectés</h4>
            </div>
            <div class="modal-body">
                <div class="row">
                    <div class="col-md-12">
                        <p>Pour acceder à certaines fonctionnalités (ajout de trajet, inscription à un trajet, ...), vous devez être connecté.
                            Si vous n'avez pas de compte sur Running-Lama, vous pouvez en créer un en cliquant <g:link controller="utilisateur" action="inscription">ici</g:link>.<br> </p>
                    </div>
                    <div class="col-md-6 col-md-offset-3">

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
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">Fermer</button>
            </div>
        </div>
    </div>
</div>
</body>

</html>
