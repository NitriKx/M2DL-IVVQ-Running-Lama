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
            <div id="map" style="width: auto; height: 400px;"></div>
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
<script type="text/javascript" src="http://maps.google.com/maps/api/js?sensor=false&language=fr"></script>
<script>
    function initialiser() {
        var latlng = new google.maps.LatLng(${trajet.departLat}, ${trajet.departLng});

        var options = {
            center: latlng,
            zoom: 19,
            mapTypeId: google.maps.MapTypeId.ROADMAP
        };

        var carte = new google.maps.Map(document.getElementById("map"), options);

        var direction = new google.maps.DirectionsRenderer({
            map   : carte
        });

        var request = {
            origin      : new google.maps.LatLng(${trajet.departLat}, ${trajet.departLng}),
            destination : new google.maps.LatLng(${trajet.arriveeLat}, ${trajet.arriveeLng}),
            travelMode  : google.maps.DirectionsTravelMode.DRIVING // Type de transport
        };

        var directionsService = new google.maps.DirectionsService(); // Service de calcul d'itinéraire
        directionsService.route(request, function(response, status){ // Envoie de la requête pour calculer le parcours
            if(status == google.maps.DirectionsStatus.OK){
                direction.setDirections(response); // Trace l'itinéraire sur la carte et les différentes étapes du parcours
            }
        });
    }

    $(document).ready(function() {
        initialiser();
        console.log("OK");
    })
</script>
</body>
</html>