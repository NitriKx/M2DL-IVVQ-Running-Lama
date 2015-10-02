<%--
  Created by IntelliJ IDEA.
  User: loicleger
  Date: 25/09/15
  Time: 16:38
--%>

<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <meta name="layout" content="main"/>
    <title></title>
    <style type="text/css" media="screen">
        .form-signin
        {
        max-width: 330px;
        padding: 15px;
        margin: 0 auto;
        }
        .form-signin .form-signin-heading, .form-signin .checkbox
        {
        margin-bottom: 10px;
        }
        .form-signin .checkbox
        {
        font-weight: normal;
        }
        .form-signin .form-control
        {
        position: relative;
        font-size: 16px;
        height: auto;
        padding: 10px;
        -webkit-box-sizing: border-box;
        -moz-box-sizing: border-box;
        box-sizing: border-box;
        }
        .form-signin .form-control:focus
        {
        z-index: 2;
        }
        .form-signin input[type="text"]
        {
        margin-bottom: -1px;
        border-bottom-left-radius: 0;
        border-bottom-right-radius: 0;
        }
        .form-signin input[type="password"]
        {
        margin-bottom: 10px;
        border-top-left-radius: 0;
        border-top-right-radius: 0;
        }
        .account-wall
        {
        margin-top: 20px;
        padding: 40px 0px 20px 0px;
        background-color: #f7f7f7;
        -moz-box-shadow: 0px 2px 2px rgba(0, 0, 0, 0.3);
        -webkit-box-shadow: 0px 2px 2px rgba(0, 0, 0, 0.3);
        box-shadow: 0px 2px 2px rgba(0, 0, 0, 0.3);
        }
        .login-title
        {
        color: #555;
        font-size: 18px;
        font-weight: 400;
        display: block;
        }
        .profile-img
        {
        width: 96px;
        height: 96px;
        margin: 0 auto 10px;
        display: block;
        -moz-border-radius: 50%;
        -webkit-border-radius: 50%;
        border-radius: 50%;
        }
        .need-help
        {
        margin-top: 10px;
        }
        .new-account
        {
        display: block;
        margin-top: 10px;
        }
    </style>
</head>

<body>
    <div class="container">
        <div class="row">
            <div class="col-sm-6 col-md-4 col-md-offset-4">
                <h1 class="text-center login-title">Se Connecter pour continuer sur le site</h1>
                <div class="account-wall">
                    <img class="profile-img" src="https://lh5.googleusercontent.com/-b0-k99FZlyE/AAAAAAAAAAI/AAAAAAAAAAA/eu7opA4byxI/photo.jpg?sz=120"
                         alt="">
                    <g:if test="${message}">
                        <div class="alert alert-danger">
                            <p>${message}</p>
                        </div>
                    </g:if>
                    <g:form class="form-signin" url="[controller:'utilisateur',action:'connexionPost']">
                        <input name="pseudo" type="text" class="form-control" value="${fieldValue(bean:utilisateur,field:'pseudo')}" placeholder="Pseudo" required autofocus>
                        <input name="mdp" type="password" class="form-control" value="${fieldValue(bean:utilisateur,field:'motDePasse')}" placeholder="Mot de Passe" required>
                        <button class="btn btn-lg btn-primary btn-block" type="submit">
                            Se Connecter</button>
                        <label class="checkbox pull-left">
                            <input type="checkbox" value="remember-me">
                            Se Souvenir de Moi
                        </label>
                        <a href="#" class="pull-right need-help">Need help? </a><span class="clearfix"></span>
                    </g:form>
                </div>
                <a href="#" class="text-center new-account">Créer un compte</a>
            </div>
        </div>
    </div>

</body>
</html>