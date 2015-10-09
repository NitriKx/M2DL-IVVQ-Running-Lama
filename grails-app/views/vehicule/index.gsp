
<%@ page import="com.runninglama.Vehicule" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'vehicule.label', default: 'Vehicule')}" />
		<title><g:message cals ="btn btn-default" code="default.list.label" args="[entityName]" /></title>
	</head>
	<body>
		<!--<a href="#list-vehicule" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		-->
		<g:form name="formCreate" method="PUT">
			<g:actionSubmit class="btn btn-default" action="create" value="Ajouter un véhicule"></g:actionSubmit>
		</g:form>

		<div id="list-vehicule" class="content scaffold-list" role="main">
			<h1><g:message code="default.list.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
				<div class="message" role="status">${flash.message}</div>
			</g:if>
			<div class="table-responsive">
				<table class="table">
				<thead>
						<tr>

							<th>VehiculeInstance</th>

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

							<td>${vehiculeInstance}/${vehiculeInstanceCount}</td>

							<td><g:link action="show" id="${vehiculeInstance.id}">${fieldValue(bean: vehiculeInstance, field: "nb_place")}</g:link></td>

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
	</body>
</html>
