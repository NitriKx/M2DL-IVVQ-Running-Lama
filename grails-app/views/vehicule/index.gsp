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
        <h2>Gestionnaire de véhicules</h2>

        <g:link class="btn btn-default" action="create">Ajouter un véhicule</g:link>

        <div id="list-vehicule" class="content scaffold-list" role="main">
            <g:if test="${flash.message}">
                <div class="message" role="status">${flash.message}</div>
            </g:if>
            <div class="table-responsive">
                <table class="table">
                    <thead>
                    <tr>

                        <g:sortableColumn property="nb_place" title="${message(code: 'vehicule.nb_place.label', default: 'Nbplace')}" />

                        <g:sortableColumn property="marque" title="${message(code: 'vehicule.marque.label', default: 'Marque')}" />

                        <g:sortableColumn property="modele" title="${message(code: 'vehicule.modele.label', default: 'Modele')}" />

                        <g:sortableColumn property="annee" title="${message(code: 'vehicule.annee.label', default: 'Annee')}" />

                        <g:sortableColumn property="kilometrage" title="${message(code: 'vehicule.kilometrage.label', default: 'Kilometrage')}" />

                        <g:sortableColumn property="type" title="${message(code: 'vehicule.type.label', default: 'Type')}" />

                        <th></th>

                        <th></th>

                    </tr>
                    </thead>
                    <tbody>
                    <g:each in="${vehiculeInstanceList}" status="i" var="vehiculeInstance">
                        <tr class="${(i % 2) == 0 ? 'even' : 'odd'}">

                            <td>${fieldValue(bean: vehiculeInstance, field: "nb_place")}</td>

                            <td>${fieldValue(bean: vehiculeInstance, field: "marque")}</td>

                            <td>${fieldValue(bean: vehiculeInstance, field: "modele")}</td>

                            <td><g:formatDate date="${vehiculeInstance.annee}" format="yyyy"/></td>

                            <td>${fieldValue(bean: vehiculeInstance, field: "kilometrage")}</td>

                            <td>${fieldValue(bean: vehiculeInstance, field: "type")}</td>

                            <td>
                                <g:link class="btn btn-default" action="edit" id="${vehiculeInstance.id}">Modifier</g:link>
                            </td>

                            <td>

                                <g:form url="[resource:vehiculeInstance]" name="formDelete" method="DELETE">
                                    <g:actionSubmit class="btn btn-default" action="delete" value="Supprimer"></g:actionSubmit>
                                </g:form>

                            </td>

                        </tr>
                    </g:each>
                    </tbody>
                </table>
            </div>
            <div class="pagination">
                <g:paginate total="${vehiculeInstanceCount ?: 0}" />
            </div>
        </div>
    </div>

</div>
<!-- /.container -->
</body>
</html>
