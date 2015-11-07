<!DOCTYPE html>
<html>
<head>
    <meta name="layout" content="main"/>
</head>
<body>

<!-- Page Content -->
<div class="container" style="margin-top: 35px;">
    <div class="row">
        <div class="col-lg-12">
            <h1 class="page-header">Liste des trajets
                <small>Ces trajets sont proposés par des membres de la communauté Running Lama</small>
            </h1>
        </div>
    </div>
    <!-- /.row -->

    <!-- Blog Post Row -->
    <g:each var="trajet" in="${lesTrajets}">
        <div class="row">
            <div class="col-md-1 text-center">
                <p><i class="fa fa-camera fa-4x"></i>
                </p>
                <p>${trajet.dateAller}</p>
            </div>
            <div class="col-md-5">
                <a href="blog-post.html">
                    <img class="img-responsive img-hover" src="http://placehold.it/600x300" alt="">
                </a>
            </div>
            <div class="col-md-6">
                <h3>
                    <a href="blog-post.html">${trajet.depart} &rarr; ${trajet.arrivee}</a>
                </h3>
                <p>Proposé par <a href="#">${trajet.conducteur.prenom} ${trajet.conducteur.nom}</a>
                </p>
                <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur.</p>
                <g:link class="btn btn-primary" controller="trajet" action="voirTrajet" params="[id:trajet.id]">Voir les details <i class="fa fa-angle-right"></i></g:link>
            </div>
        </div>
        <!-- /.row -->
    </g:each>
</div>
<!-- /.container -->
<script src="js/jquery.js"></script>
</body>
</html>
