<%--
  Created by IntelliJ IDEA.
  User: julien
  Date: 30/09/15
  Time: 08:53
--%>

<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <meta name="layout" content="main" />
</head>

<div class="row">
    <div class="col-xs-12">
        <g:each var="trajet" in="${lesTrajets}">
            <div class="panel panel-default">
                <div class="panel-heading">${trajet.depart} -> ${trajet.arrivee}</div>
                <div class="panel-body">
                    Panel content
                </div>
            </div>
        </g:each>
    </div>
</div>

</html>