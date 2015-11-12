$(document).ready(function () {

    var lastResponse;

    // Auto complete des champs pour la localisation Google maps
    var options = {
        types: ['(cities)']
    };
    var inputStart = document.getElementById('start');
    autocompleteStart = new google.maps.places.Autocomplete(inputStart, options);

    var inputEnd = document.getElementById('end');
    autocompleteEnd = new google.maps.places.Autocomplete(inputEnd, options);


    var rendererOptions = {
        draggable: true
    };
    var directionsDisplay = new google.maps.DirectionsRenderer(rendererOptions);
    var directionsService = new google.maps.DirectionsService();
    var map;

    var australia = new google.maps.LatLng(-25.274398, 133.775136);

    function initialize() {

        var france = new google.maps.LatLng(46.227638, 2.213749);
        var mapOptions = {
            zoom: 5,
            center: france
        }
        map = new google.maps.Map(document.getElementById('map'), mapOptions);
        directionsDisplay.setMap(map);

        google.maps.event.addListener(directionsDisplay, 'directions_changed', function () {
            computeTotalDistance(directionsDisplay.getDirections());
        });


    }


    function computeTotalDistance(response) {
        console.log(response);
        dureeSeconde = response.routes[0].legs[0].duration.value;
        var d = new Date(dureeSeconde * 1000); // js fonctionne en milisecondes
        var t = [];
        t.push(d.getHours() - 1);
        t.push(d.getMinutes());
        duree = t.join(':');
        distanceKilometre = response.routes[0].legs[0].distance.value;
        distance = distanceKilometre / 1000;
        depart_lat = response.routes[0].legs[0].start_location.k;
        depart_lon = response.routes[0].legs[0].start_location.A;
        arrive_lat = response.routes[0].legs[0].end_location.k;
        arrive_lon = response.routes[0].legs[0].end_location.A;

//            $('#duree').timepicker('setTime', duree);
        $('#distance').val(Math.round(distance));
        $('#departLat').attr('value', depart_lat);
        $('#departLng').attr('value', depart_lon);
        $('#arriveeLat').attr('value', arrive_lat);
        $('#arriveeLng').attr('value', arrive_lon);

        lastResponse = response;
    }

    google.maps.event.addDomListener(window, 'load', initialize);


    // Gestion de la MAP
    var directionsDisplay;

    var optionsService = {
        draggable: true
    };
    var directionsService = new google.maps.DirectionsService(optionsService);
    var map;

    function computeTotalDistance(result) {
        var total = 0;
        var myroute = result.routes[0];
        for (var i = 0; i < myroute.legs.length; i++) {
            total += myroute.legs[i].distance.value;
        }
        total = total / 1000.0;
        document.getElementById('total').innerHTML = total + ' km';
    }


    $('#submitGoogle').click(function (e) {
        e.preventDefault();
        var start = document.getElementById('start').value;
        var end = document.getElementById('end').value;
        var request = {
            origin: start,
            destination: end,
            optimizeWaypoints: true,
            travelMode: google.maps.TravelMode.DRIVING,
        };

        directionsService.route(request, function (response, status) {
            if (status == google.maps.DirectionsStatus.OK) {
                directionsDisplay.setDirections(response);
                console.log(response);
                dureeSeconde = response.routes[0].legs[0].duration.value;
                var d = new Date(dureeSeconde * 1000); // js fonctionne en milisecondes
                var t = [];
                t.push(d.getHours() - 1);
                t.push(d.getMinutes());
                duree = t.join(':');
                distanceKilometre = response.routes[0].legs[0].distance.value;
                distance = distanceKilometre / 1000;
                depart_lat = response.routes[0].legs[0].start_location.lat();
                depart_lon = response.routes[0].legs[0].start_location.lng();
                arrive_lat = response.routes[0].legs[0].end_location.lat();
                arrive_lon = response.routes[0].legs[0].end_location.lng();

//         $('#duree').timepicker('setTime', duree);
                $('#distance').val(Math.round(distance));
                $('#departLat').attr('value', depart_lat);
                $('#departLng').attr('value', depart_lon);
                $('#arriveeLat').attr('value', arrive_lat);
                $('#arriveeLng').attr('value', arrive_lon);
                $('#methode').attr('value', 'google');
            }
        });
    });

    $('#submitAjout').click(function(e){
        e.preventDefault();
        var start = document.getElementById('start').value;
        var end = document.getElementById('end').value;
        var request = {
            origin:start,
            destination:end,
            optimizeWaypoints: true,
            travelMode: google.maps.TravelMode.DRIVING,
        };

        directionsService.route(request, function(response, status) {
            if (status == google.maps.DirectionsStatus.OK) {
                directionsDisplay.setDirections(response);
                console.log(response);
                dureeSeconde = response.routes[0].legs[0].duration.value;
                var d = new Date(dureeSeconde * 1000); // js fonctionne en milisecondes
                var t = [];
                t.push(d.getHours()-1);
                t.push(d.getMinutes());
                duree =  t.join(':');
                distanceKilometre = response.routes[0].legs[0].distance.value;
                distance = distanceKilometre / 1000;
                depart_lat = response.routes[0].legs[0].start_location.lat();
                depart_lon = response.routes[0].legs[0].start_location.lng();
                arrive_lat = response.routes[0].legs[0].end_location.lat();
                arrive_lon = response.routes[0].legs[0].end_location.lng();

                $('#distance').val(Math.round(distance));
                $('#departLat').attr('value', depart_lat);
                $('#departLng').attr('value', depart_lon);
                $('#arriveeLat').attr('value', arrive_lat);
                $('#arriveeLng').attr('value', arrive_lon);
                $('#methode').attr('value', 'google');

                $('#ajoutTrajetForm').submit();
            }
            else
            {
                alert("Départ et/ou arrivée invalide(s)");
            }
        });
    });

});