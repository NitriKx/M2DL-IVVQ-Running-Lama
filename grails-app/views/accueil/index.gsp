<!DOCTYPE html>
<html>
<head>
    <meta name="layout" content="main"/>
</head>
<body>

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
            <div class="fill" style="background-image:url('http://placehold.it/1900x1080&text=Slide One');"></div>
            <div class="carousel-caption">
                <h2>Caption 1</h2>
            </div>
        </div>
        <div class="item">
            <div class="fill" style="background-image:url('http://placehold.it/1900x1080&text=Slide Two');"></div>
            <div class="carousel-caption">
                <h2>Caption 2</h2>
            </div>
        </div>
        <div class="item">
            <div class="fill" style="background-image:url('http://placehold.it/1900x1080&text=Slide Three');"></div>
            <div class="carousel-caption">
                <h2>Caption 3</h2>
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

<!-- Page Content -->
<div class="container">

    <!-- Marketing Icons Section -->
    <div class="row">
        <div class="col-lg-12">
            <h1 class="page-header">
                Bienvenue sur Running Lama
            </h1>
            <p>Running Lama est un site communautaire permettant de fédérer une communauté de covoiturage. Cette application permet à ses utilisateurs de s'inscrire / se connecter afin de gérer une flotte de véhicules. Chaque utilisateur a ensuite la possibilité de créer des trajets ou de s'inscrire a des trajets proposés par des membres de la communauté. A l'issu du trajet, un participant note le conducteur. </p>
            <center><g:link controller="utilisateur" action="inscription" class="btn btn-success">Commencer maintenant</g:link></center>
        </div>
    </div>
    <!-- /.row -->

    <!-- Portfolio Section -->
    <div class="row">
        <div class="col-lg-12">
            <h2 class="page-header">Rechercher un trajet</h2>
        </div>

        <g:form class="inline-form" id="rechercheForm" name="formRechercherTrajet" url="[controller:'trajet',action:'rechercherTrajet']">
            <div class="form-group col-md-5">
                <input class="form-control" id="start" name="depart_google" type="text" placeholder="Départ">
            </div>
            <div class="form-group col-md-5">
                <input class="form-control" id="end" name="arrivee_google" type="text" placeholder="Arrivée">
            </div>
            <button type="submit" class="btn btn-default col-md-2">Rechercher</button>
        </g:form>
    </div>
    <!-- /.row -->

    <!-- Call to Action Section -->
    <div class="well">
        <div class="row">
            <div class="col-md-8">
                <p>Vous avez la possibilité d'accéder à un formulaire de recherche complet (prix, dates aller et retour, note moyenne du conducteur, etc...)</p>
            </div>
            <div class="col-md-4">
                <a class="btn btn-lg btn-default btn-block" href="#">Effectuer une recherche complexe</a>
            </div>
        </div>
    </div>

    <hr>

    <div class="row">
        <div class="col-md-6">
            <h2 class="page-header">Les derniers utilisateurs</h2>

            <ul class="media-list">
                <g:each in="${lesDerniersUtilisateurs}">

                <li class="media">
                    <div class="media-left col-md-2">
                        <a href="#">
                            <img src="http://placehold.it/64x64">
                        </a>
                    </div>
                    <div class="media-body col-md-10">
                        <h4 class="media-heading">${it.prenom} ${it.nom}</h4>
                        XX ans, inscrit le ${it.dateInscription}
                    </div>
                </li>
                </g:each>
            </ul>
        </div>
        <div class="col-md-6">
            <h2 class="page-header">Les derniers trajets</h2>
            <g:each var="trajet" in="${lesTrajets}">
                <li><g:link controller="trajet" action="voirTrajet" params="[id: trajet.id]">${trajet.depart} -> ${trajet.arrivee}</g:link></li>
            </g:each>
        </div>
    </div>

    <!-- Footer -->
    <footer>
        <div class="row">
            <div class="col-lg-12">
            </div>
        </div>
    </footer>

</div>
<!-- /.container -->
<script src="js/jquery.js"></script>
<script src="https://maps.googleapis.com/maps/api/js?v=3.exp&libraries=places"></script>

<script type="text/javascript">
$(document).ready(function() {

    $('.carousel').carousel()

    // This example displays an address form, using the autocomplete feature
// of the Google Places API to help users fill in the information.

    var placeSearch, autocomplete;
    var componentForm = {
        street_number: 'short_name',
        route: 'long_name',
        locality: 'long_name',
        administrative_area_level_1: 'short_name',
        country: 'long_name',
        postal_code: 'short_name'
    };

    function initAutocomplete() {
        // Create the autocomplete object, restricting the search to geographical
        // location types.
        autocomplete = new google.maps.places.Autocomplete(
                /** @type {!HTMLInputElement} */(document.getElementById('start')),
                {types: ['geocode']});

        autocomplete2 = new google.maps.places.Autocomplete(
                /** @type {!HTMLInputElement} */(document.getElementById('end')),
                {types: ['geocode']});

        // When the user selects an address from the dropdown, populate the address
        // fields in the form.
        autocomplete.addListener('place_changed', fillInAddress);
        autocomplete2.addListener('place_changed', fillInAddress);

    }

    function fillInAddress() {
        // Get the place details from the autocomplete object.
        var place = autocomplete.getPlace();

        for (var component in componentForm) {
            document.getElementById(component).value = '';
            document.getElementById(component).disabled = false;
        }

        // Get each component of the address from the place details
        // and fill the corresponding field on the form.
        for (var i = 0; i < place.address_components.length; i++) {
            var addressType = place.address_components[i].types[0];
            if (componentForm[addressType]) {
                var val = place.address_components[i][componentForm[addressType]];
                document.getElementById(addressType).value = val;
            }
        }
    }

    initAutocomplete();
});
</script>
</body>
</html>
