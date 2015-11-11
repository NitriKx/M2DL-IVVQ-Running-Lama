<%--
  Created by IntelliJ IDEA.
  User: Benoît Sauvère
  Date: 28/10/15
  Time: 09:34
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
            <g:form name="formAjoutTrajet" url="[controller:'trajet',action:'updateTrajet']">
                <g:hiddenField name="id" value="${trajet?.id}" />
                <g:hiddenField name="version" value="${trajet?.version}" />

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

                <g:submitButton name="create" class="save btn btn-primary btn-lg pull-right" value="${message(code: 'runninglama.trajet.button.update.label', default: 'Modifier le trajet')}" />
            </g:form>
        </div>
    </div>
</div>

<script src="https://maps.googleapis.com/maps/api/js?v=3.exp&libraries=places"></script>
<script src="<g:resource dir="js" file="ajoutTrajetMap.js" />"></script>

</body>
</html>