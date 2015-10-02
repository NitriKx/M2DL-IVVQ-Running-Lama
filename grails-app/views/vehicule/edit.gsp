<%@ page import="com.runninglama.Vehicule" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'vehicule.label', default: 'Vehicule')}" />
		<title><g:message code="default.edit.label" args="[entityName]" /></title>
	</head>
	<body>
			<g:hasErrors bean="${vehiculeInstance}">
			<ul class="errors" role="alert">
				<g:eachError bean="${vehiculeInstance}" var="error">
				<li <g:if test="${error in org.springframework.validation.FieldError}">data-field-id="${error.field}"</g:if>><g:message error="${error}"/></li>
				</g:eachError>
			</ul>
			</g:hasErrors>
			<g:form url="[resource:vehiculeInstance, action:'update']" method="PUT" >
				<g:hiddenField name="version" value="${vehiculeInstance?.version}" />
				<fieldset class="form">
					<g:render template="form"/>
				</fieldset>
				<fieldset class="buttons">
					<g:actionSubmit class="save btn btn-default" action="update" value="${message(code: 'default.button.update.label', default: 'Update')}" />
				</fieldset>
			</g:form>
		</div>
	</body>
</html>
