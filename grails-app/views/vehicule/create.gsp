<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'vehicule.label', default: 'Vehicule')}" />
		<title><g:message code="default.create.label" args="[entityName]" /></title>
	</head>
	<body>

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
            <g:submitButton name="create" class="save btn btn-default" value="${message(code: 'default.button.create.label', default: 'Create')}" />
		</g:form>

	</body>
</html>
