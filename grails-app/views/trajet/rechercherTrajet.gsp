
<%@ page import="com.runninglama.Trajet" %>
<!DOCTYPE html>
<html>
<head>
    <meta name="layout" content="main">
    <g:set var="entityName" value="${message(code: 'trajet.label', default: 'Trajet')}" />
    <title><g:message code="default.list.label" args="[entityName]" /></title>
</head>
<body>
<a href="#list-trajet" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
<div class="nav" role="navigation">
    <ul>
        <li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
        <li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
    </ul>
</div>
<div id="list-trajet" class="content scaffold-list" role="main">
    <h1><g:message code="default.list.label" args="[entityName]" /></h1>
    <g:if test="${flash.message}">
        <div class="message" role="status">${flash.message}</div>
    </g:if>
    <div class="table-responsive">
        <table class="table">
            <thead>
            <tr>

                <g:sortableColumn property="depart" title="${message(code: 'trajet.depart.label', default: 'Depart')}" />

                <g:sortableColumn property="arrivee" title="${message(code: 'trajet.arrivee.label', default: 'Arrivee')}" />

                %{--<g:sortableColumn property="arriveeLat" title="${message(code: 'trajet.arriveeLat.label', default: 'Arrivee Lat')}" />--}%

                %{--<g:sortableColumn property="arriveeLng" title="${message(code: 'trajet.arriveeLng.label', default: 'Arrivee Lng')}" />--}%

                <g:sortableColumn property="commentaire" title="${message(code: 'trajet.commentaire.label', default: 'Commentaire')}" />

                <g:sortableColumn property="dateAller" title="${message(code: 'trajet.dateAller.label', default: 'Date Aller')}" />

                <g:sortableColumn property="dateRetour" title="${message(code: 'trajet.dateRetour.label', default: 'Date Retour')}" />

                <g:sortableColumn property="prix" title="${message(code: 'trajet.prix.label', default: 'Prix')}" />

                <th><g:message code="trajet.conducteur.label" default="Conducteur" /></th>

                <th></th>

            </tr>
            </thead>
            <tbody>
            <g:each in="${trajetInstanceList}" status="i" var="trajetInstance">
                <tr class="${(i % 2) == 0 ? 'even' : 'odd'}">

                    <td><g:link action="show" id="${trajetInstance.id}">${fieldValue(bean: trajetInstance, field: "depart")}</g:link></td>

                    <td>${fieldValue(bean: trajetInstance, field: "arrivee")}</td>

                    %{--<td>${fieldValue(bean: trajetInstance, field: "arriveeLat")}</td>--}%

                    %{--<td>${fieldValue(bean: trajetInstance, field: "arriveeLng")}</td>--}%

                    <td>${fieldValue(bean: trajetInstance, field: "commentaire")}</td>

                    <td><g:formatDate date="${trajetInstance.dateAller}" format="dd/MM/yyyy"/></td>

                    <td><g:formatDate date="${trajetInstance.dateRetour}" format="dd/MM/yyyy"/></td>

                    <td>${fieldValue(bean: trajetInstance, field: "prix")}</td>

                    <td>${fieldValue(bean: trajetInstance, field: "conducteur")}</td>

                    <td>
                        <g:link class="btn btn-info" action="voirTrajet" id="${trajetInstance.id}">Voir</g:link>
                    </td>
                </tr>
            </g:each>
            </tbody>
        </table>
    </div>
    <div class="pagination">
        <g:paginate total="${trajetInstanceCount ?: 0}" />
    </div>
</div>
</body>
</html>
