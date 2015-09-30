
<%@ page import="com.runninglama.Vehicule" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'vehicule.label', default: 'Vehicule')}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#show-vehicule" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="list" action="index"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="show-vehicule" class="content scaffold-show" role="main">
			<h1><g:message code="default.show.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<ol class="property-list vehicule">
			
				<g:if test="${vehiculeInstance?.nb_place}">
				<li class="fieldcontain">
					<span id="nb_place-label" class="property-label"><g:message code="vehicule.nb_place.label" default="Nbplace" /></span>
					
						<span class="property-value" aria-labelledby="nb_place-label"><g:fieldValue bean="${vehiculeInstance}" field="nb_place"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${vehiculeInstance?.marque}">
				<li class="fieldcontain">
					<span id="marque-label" class="property-label"><g:message code="vehicule.marque.label" default="Marque" /></span>
					
						<span class="property-value" aria-labelledby="marque-label"><g:fieldValue bean="${vehiculeInstance}" field="marque"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${vehiculeInstance?.modele}">
				<li class="fieldcontain">
					<span id="modele-label" class="property-label"><g:message code="vehicule.modele.label" default="Modele" /></span>
					
						<span class="property-value" aria-labelledby="modele-label"><g:fieldValue bean="${vehiculeInstance}" field="modele"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${vehiculeInstance?.annee}">
				<li class="fieldcontain">
					<span id="annee-label" class="property-label"><g:message code="vehicule.annee.label" default="Annee" /></span>
					
						<span class="property-value" aria-labelledby="annee-label"><g:formatDate date="${vehiculeInstance?.annee}" /></span>
					
				</li>
				</g:if>
			
				<g:if test="${vehiculeInstance?.kilometrage}">
				<li class="fieldcontain">
					<span id="kilometrage-label" class="property-label"><g:message code="vehicule.kilometrage.label" default="Kilometrage" /></span>
					
						<span class="property-value" aria-labelledby="kilometrage-label"><g:fieldValue bean="${vehiculeInstance}" field="kilometrage"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${vehiculeInstance?.type}">
				<li class="fieldcontain">
					<span id="type-label" class="property-label"><g:message code="vehicule.type.label" default="Type" /></span>
					
						<span class="property-value" aria-labelledby="type-label"><g:fieldValue bean="${vehiculeInstance}" field="type"/></span>
					
				</li>
				</g:if>
			
			</ol>
			<g:form url="[resource:vehiculeInstance, action:'delete']" method="DELETE">
				<fieldset class="buttons">
					<g:link class="edit" action="edit" resource="${vehiculeInstance}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
					<g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
				</fieldset>
			</g:form>
		</div>
	</body>
</html>
