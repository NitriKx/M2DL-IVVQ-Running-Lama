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
            <h1 class="page-header">${trajet.depart} &rarr; ${trajet.arrivee}
                <small>proposé par ${trajet.conducteur.prenom} ${trajet.conducteur.nom}</small>
            </h1>
        </div>
    </div>
<!-- /.row -->
    <div class="row">
        <!-- Map Column -->
        <div class="col-md-8">
            <!-- Embedded Google Map -->
            <iframe width="100%" height="400px" frameborder="0" scrolling="no" marginheight="0" marginwidth="0" src="http://maps.google.com/maps?hl=en&amp;ie=UTF8&amp;ll=37.0625,-95.677068&amp;spn=56.506174,79.013672&amp;t=m&amp;z=4&amp;output=embed"></iframe>
        </div>
        <!-- Contact Details Column -->
        <div class="col-md-4">
            <h3>Détails</h3>
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
    <!-- /.row -->

</div>
<!-- /.container -->
<script src="js/jquery.js"></script>
</body>
</html>