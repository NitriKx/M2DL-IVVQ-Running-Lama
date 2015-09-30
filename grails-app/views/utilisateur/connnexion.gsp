<%--
  Created by IntelliJ IDEA.
  User: loicleger
  Date: 25/09/15
  Time: 16:38
--%>

<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title></title>
</head>

<body>
    <g:hasErrors bean="${utilisateurInstance}">
        <ul class="errors" role="alert">
            <g:eachError bean="${utilisateurInstance}" var="error">
                <li <g:if test="${error in org.springframework.validation.FieldError}">data-field-id="${error.field}"</g:if>><g:message error="${error}"/></li>
            </g:eachError>
        </ul>
    </g:hasErrors>
    <g:form url="[resource:utilisateurInstance, action:'connexion']">
        <div class="form-group">
            <label >Pseudo :</label>
            <input type="text" class="form-control" id="pseudo">
        </div>
        <div class="form-group">
            <label>Mot de passe:</label>
            <input type="password" class="form-control" id="mdp">
        </div>
        <div class="checkbox">
            <label><input type="checkbox"> Se souvenir de moi</label>
        </div>
        <button type="submit" class="btn btn-default">Se Connecter</button>
    </g:form>

</body>
</html>