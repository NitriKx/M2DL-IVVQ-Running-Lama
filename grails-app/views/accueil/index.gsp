<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main"/>
	</head>
	<body>

    <div id="myCarousel" class="carousel slide" data-ride="carousel" style="margin-top:20px;">
        <!-- Carousel indicators -->
        <ol class="carousel-indicators">
            <li data-target="#myCarousel" data-slide-to="0" class="active"></li>
            <li data-target="#myCarousel" data-slide-to="1"></li>
            <li data-target="#myCarousel" data-slide-to="2"></li>
        </ol>
        <!-- Wrapper for carousel items -->
        <div class="carousel-inner">
            <div class="item active">
                <img src="http://placehold.it/1200x400?text=Slide+1" alt="slide1">
            </div>
            <div class="item">
                <img src="http://placehold.it/1200x400?text=Slide+2" alt="slide2">
            </div>
            <div class="item">
                <img src="http://placehold.it/1200x400?text=Slide+3" alt="slide3">
            </div>
        </div>
        <!-- Carousel controls -->
        <a class="carousel-control left" href="#myCarousel" data-slide="prev">
            <span class="glyphicon glyphicon-chevron-left"></span>
        </a>
        <a class="carousel-control right" href="#myCarousel" data-slide="next">
            <span class="glyphicon glyphicon-chevron-right"></span>
        </a>
    </div>

    <div class="row" style="margin-top: 20px;">
        <div class="col-md-3">
            <div class="panel panel-default">
                <div class="panel-heading">Les derniers utilisateurs</div>
                <div class="panel-body">
                    <ul>
                        <g:each in="${lesDerniersUtilisateurs}">
                            <li>${it.nom} ${it.prenom}</li>
                        </g:each>
                    </ul>
                </div>
            </div>
            <div class="panel panel-default">
                <div class="panel-heading">Les derniers trajets</div>
                <div class="panel-body">
                    Basic panel example
                </div>
            </div>
        </div>
        <div class="col-md-9">
            <div class="panel panel-default">
                <div class="panel-heading">Rechercher un trajet</div>
                <div class="panel-body">
                    Basic panel example
                </div>
            </div>
        </div>
    </div>
    <script>
        $('.carousel').carousel()
    </script>
	</body>
</html>
