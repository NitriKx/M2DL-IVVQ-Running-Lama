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
                <p><i class="fa fa-clock-o fa-2x"></i>
                </p>
                <p>${trajet.dateAller}</p>
            </div>
            <div class="col-md-5">
                <div id="map-${trajet.id}" style="width: auto; height:200px;"></div>
            </div>
            <div class="col-md-6">
                <h3>
                    <g:link controller="trajet" action="voirTrajet" params="[id:trajet.id]"> ${trajet.depart} &rarr; ${trajet.arrivee}</g:link>
                </h3>
                <p>Proposé par <a href="#">${trajet.conducteur.prenom} ${trajet.conducteur.nom}</a>
                </p>
                <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur.</p>
                <g:link class="btn btn-primary" controller="trajet" action="voirTrajet" params="[id:trajet.id]">Voir les details <i class="fa fa-angle-right"></i></g:link>
            </div>
        </div>
        <br>
        <!-- /.row -->
    </g:each>
</div>
<!-- /.container -->
<script type="text/javascript" src="http://maps.google.com/maps/api/js?sensor=false&language=fr"></script>

<script>

    function initialiser(lat1, lng1, lat2, lng2, map) {
        var latlng = new google.maps.LatLng(0, 0);

        var direction = new google.maps.DirectionsRenderer({
            map   : map
        });

        var request = {
            origin      : new google.maps.LatLng(lat1, lng1),
            destination : new google.maps.LatLng(lat2, lng2),
            travelMode  : google.maps.DirectionsTravelMode.DRIVING // Type de transport
        };

        var directionsService = new google.maps.DirectionsService(); // Service de calcul d'itinéraire
        directionsService.route(request, function(response, status){ // Envoie de la requête pour calculer le parcours
            if(status == google.maps.DirectionsStatus.OK){
                direction.setDirections(response); // Trace l'itinéraire sur la carte et les différentes étapes du parcours
            }
        });
    }


    function initialize() {
            <g:each var="trajet" in="${lesTrajets}">
            var latlng = new google.maps.LatLng(${trajet.departLat}, ${trajet.departLng});

            var options = {
                center: latlng,
                zoom: 19,
                mapTypeId: google.maps.MapTypeId.ROADMAP
            };

            var carte = new google.maps.Map(document.getElementById("map-${trajet.id}"), options);
            initialiser(${trajet.departLat}, ${trajet.departLng}, ${trajet.arriveeLat}, ${trajet.arriveeLng}, carte);
            </g:each>
    }

    google.maps.event.addDomListener(window, "load", initialize);

</script>
</body>
</html>
