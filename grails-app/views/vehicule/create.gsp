<%@ page import="com.runninglama.Vehicule" %>
<!DOCTYPE html>
<html>
<head>
    <meta name="layout" content="main"/>
</head>
<body>

<!-- Page Content -->
<div class="container" style="margin-top: 25px;">

    <div class="row">
        <h2>Gestionnaire de véhicules <small>Ajouter</small></h2>
        <div class="col-md-6 col-md-offset-3">
            <g:hasErrors bean="${vehiculeInstance}">
                <div class="alert alert-danger" role="alert">
                    <ul>
                        <g:eachError bean="${vehiculeInstance}" var="error">
                            <li <g:if test="${error in org.springframework.validation.FieldError}">data-field-id="${error.field}"</g:if>><g:message error="${error}"/></li>
                        </g:eachError>
                    </ul>
                </div>
            </g:hasErrors>

            <g:form url="[resource:vehiculeInstance, action:'save']" >
                <g:render template="form"/>
                <br>
                <g:submitButton name="create" class="save btn btn-success pull-right" value="Ajouter le véhicule" />
            </g:form>
        </div>
    </div>

</div>
<!-- /.container -->
</body>
</html>