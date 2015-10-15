<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
    <meta name="description" content="">
    <meta name="author" content="">
    <link rel="icon" href="../../favicon.ico">

    <title>Running Lama</title>

    <!-- Latest compiled and minified CSS -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">


    <!-- Custom styles for this template -->
    <link href="<g:resource dir="css" file="justified-nav.css" />" rel="stylesheet">


    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!--[if lt IE 9]>
      <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
      <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->
    <script src="//code.jquery.com/jquery-1.11.3.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>


</head>

<body>

<div class="container">

    <!-- The justified navigation menu is meant for single line per list item.
           Multiple lines will require custom code not provided by Bootstrap. -->
    <div class="masthead">
        <h3 class="text-muted">Running Lama</h3>
        <nav style="margin-bottom: 20px;">
            <ul class="nav nav-justified">
                <li><g:link controller="accueil" action="index">Accueil</g:link></li>
                <li><g:link controller="trajet" action="liste">Voir les trajets</g:link></li>
                <li><g:link controller="trajet" action="ajouterTrajet">Ajouter un trajet</g:link></li>
                <li><g:link controller="utilisateur" action="index">Espace membre</g:link></li>
                <g:if test="${session.utilisateur}">
                    <li><g:link controller="utilisateur" action="deconnexion">Se déconnecter</g:link></li>
                </g:if>


            </ul>
        </nav>
    </div>

    <g:layoutBody/>

    <!-- Site footer -->
    <footer class="footer">
        <p>&copy; Running Lama 2015</p>
    </footer>

</div> <!-- /container -->
</body>
</html>


