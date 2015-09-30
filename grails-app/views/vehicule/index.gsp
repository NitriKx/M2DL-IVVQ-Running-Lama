
<%@ page import="com.runninglama.Vehicule" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'vehicule.label', default: 'Vehicule')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#list-vehicule" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="list-vehicule" class="content scaffold-list" role="main">
			<h1><g:message code="default.list.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
				<div class="message" role="status">${flash.message}</div>
			</g:if>
			<table>
			<thead>
					<tr>
					
						<g:sortableColumn property="nb_place" title="${message(code: 'vehicule.nb_place.label', default: 'Nbplace')}" />
					
						<g:sortableColumn property="marque" title="${message(code: 'vehicule.marque.label', default: 'Marque')}" />
					
						<g:sortableColumn property="modele" title="${message(code: 'vehicule.modele.label', default: 'Modele')}" />
					
						<g:sortableColumn property="annee" title="${message(code: 'vehicule.annee.label', default: 'Annee')}" />
					
						<g:sortableColumn property="kilometrage" title="${message(code: 'vehicule.kilometrage.label', default: 'Kilometrage')}" />
					
						<g:sortableColumn property="type" title="${message(code: 'vehicule.type.label', default: 'Type')}" />
					
					</tr>
				</thead>
				<tbody>
				<g:each in="${vehiculeInstanceList}" status="i" var="vehiculeInstance">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
					
						<td><g:link action="show" id="${vehiculeInstance.id}">${fieldValue(bean: vehiculeInstance, field: "nb_place")}</g:link></td>
					
						<td>${fieldValue(bean: vehiculeInstance, field: "marque")}</td>
					
						<td>${fieldValue(bean: vehiculeInstance, field: "modele")}</td>
					
						<td><g:formatDate date="${vehiculeInstance.annee}" /></td>
					
						<td>${fieldValue(bean: vehiculeInstance, field: "kilometrage")}</td>
					
						<td>${fieldValue(bean: vehiculeInstance, field: "type")}</td>
					
					</tr>
				</g:each>
				</tbody>
			</table>
			<div class="pagination">
				<g:paginate total="${vehiculeInstanceCount ?: 0}" />
			</div>
		</div>
	</body>
</html>
