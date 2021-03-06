<!DOCTYPE html>
<html>
<head>
    <meta name="layout" content="main"/>
</head>
<body>

<!-- Page Content -->
<div class="container">

    <!-- Marketing Icons Section -->
    <div class="row">
        <div class="col-lg-12">
            <h1 class="page-header">
                Bienvenue sur Running Lama
            </h1>
            <p>Running Lama est un site communautaire permettant de fédérer une communauté de covoiturage. Cette application permet à ses utilisateurs de s'inscrire / se connecter afin de gérer une flotte de véhicules. Chaque utilisateur a ensuite la possibilité de créer des trajets ou de s'inscrire a des trajets proposés par des membres de la communauté. A l'issu du trajet, un participant note le conducteur. </p>
            <center>
                <g:if test="${session.utilisateur}">
                    <g:link controller="trajet" action="liste" class="btn btn-success">Commencer maintenant</g:link></center>
                </g:if>
                <g:else>
                    <g:link controller="utilisateur" action="inscription" class="btn btn-success">Commencer maintenant</g:link></center>
                </g:else>
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
