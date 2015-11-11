<%--
  Created by IntelliJ IDEA.
  User: julien
  Date: 30/09/15
  Time: 08:53
--%>

<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta name="layout" content="main" />
</head>

<body>
<div class="container">
    <div class="row">
        <div class="col-xs-12">
            <g:form id="ajoutTrajetForm" name="formAjoutTrajet" url="[controller: 'trajet', action: 'ajouterTrajetPost']">
                <div class="row">
                    <g:hasErrors bean="${trajet}">
                        <div class="alert alert-danger">
                            <ul>
                                <g:eachError var="err" bean="${trajet}">
                                    <li>${err}</li>
                                </g:eachError>
                            </ul>
                        </div>
                    </g:hasErrors>
                </div>

                <g:render template="form" />
                <g:submitButton id="submitAjout" name="create" class="save btn btn-primary btn-lg pull-right" value="${message(code: 'runninglama.trajet.button.create.label', default: 'Ajouter le trajet')}" />

            </g:form>
        </div>
    </div>
</div>
<script src="https://maps.googleapis.com/maps/api/js?v=3.exp&libraries=places"></script>
<script src="<g:resource dir="js" file="ajoutTrajetMap.js" />"></script>
</body>
</html>