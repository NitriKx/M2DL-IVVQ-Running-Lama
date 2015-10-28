<div class="row">
    <div class="col-md-6">
        <div class="panel panel-default" style="min-height: 380px;">
            <div class="panel-heading">Informations trajet</div>

            <div class="panel-body">
                <input type="hidden" name="departLat" value="${fieldValue(bean: trajet, field: 'departLat')}" id="departLat">
                <input type="hidden" name="departLng" value="${fieldValue(bean: trajet, field: 'departLng')}" id="departLng">
                <input type="hidden" name="arriveeLat" value="${fieldValue(bean: trajet, field: 'arriveeLat')}" id="arriveeLat">
                <input type="hidden" name="arriveeLng" value="${fieldValue(bean: trajet, field: 'arriveeLng')}" id="arriveeLng">
                <input type="hidden" name="total" id="total">
                <input type="hidden" name="methode" id="methode" value="perso">

                <div class="form-group">
                    <label for="start">Départ</label>
                    <input class="form-control input-lg" id="start"
                           value="${fieldValue(bean: trajet, field: 'depart')}" name="depart" type="text"
                           placeholder="">
                </div>

                <div class="form-group">
                    <label for="end">Arrivée</label>
                    <input class="form-control input-lg" id="end" name="arrivee"
                           value="${fieldValue(bean: trajet, field: 'arrivee')}" type="text" placeholder="">
                </div>

                <div class="form-group">
                    <label>Date aller</label>
                    <g:datePicker name="dateAller" value="${fieldValue(bean: trajet, field: 'dateAller')}"
                                  precision="day"/>
                </div>

                <div class="form-group">
                    <label>Date retour</label>
                    <g:datePicker name="dateRetour" value="${fieldValue(bean: trajet, field: 'dateRetour')}"
                                  precision="day"/>
                </div>

                <center><button class="btn btn-success btn-lg" id="submitGoogle">Générer trajet</button>
                </center>
            </div>
        </div>
    </div>
    <div class="col-md-6">
        <div class='map-wrapper' style="height:380px; width: 100%; margin:0; padding:0;">
            <div id="map" style="height:100%; width: 100%;">
                <p>Veuillez patienter pendant le chargement de la carte...</p>
            </div>
        </div>
    </div>
</div>

<div class="row">
    <div class="col-md-6">
        <div class="panel panel-default">
            <div class="panel-heading">Choix du véhicule</div>

            <div class="panel-body">
                <div class="form-group">
                    <label>Véhicule</label>
                    <g:select optionKey="id" name="trajet.vehicule" from="${listeVehicules}"
                              value="${trajet?.vehicule?.id}" noSelection="['': '-Choisir un véhicule-']"/>
                </div>

                <div class="form-group">
                    <label for="nbPlaces">Nombre de place</label>
                    <input class="form-control input-lg" id="nbPlaces"
                           value="${fieldValue(bean: trajet, field: 'nombrePlace')}" name="nombrePlace"
                           type="number" placeholder="">
                </div>
            </div>
        </div>
    </div>

    <div class="col-md-6">
        <div class="panel panel-default">
            <div class="panel-heading">Informations complementaires</div>

            <div class="panel-body">
                <div class="form-group">
                    <label for="nbPlaces">Prix par place</label>
                    <input class="form-control input-lg" id="prix"
                           value="${fieldValue(bean: trajet, field: 'prix')}" name="prix" type="number"
                           placeholder="">
                </div>

                <div class="form-group">
                    <label for="nbPlaces">Commentaires</label>
                    <textarea class="form-control input-lg" id=""
                              value="${fieldValue(bean: trajet, field: 'commentaire')}"
                              name="commentaire"></textarea>
                </div>
            </div>
        </div>
    </div>
</div>