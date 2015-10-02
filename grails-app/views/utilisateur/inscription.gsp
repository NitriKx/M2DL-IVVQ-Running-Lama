<%--
  Created by IntelliJ IDEA.
  User: julien
  Date: 30/09/15
  Time: 08:53
--%>

<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>An Example Page</title>
    <meta name="layout" content="main" />
</head>

<div class="row">
    <div class="col-xs-12 col-sm-8 col-md-6 col-sm-offset-2 col-md-offset-3">
        <g:form name="formInscription" url="[controller:'utilisateur',action:'inscriptionPost']">
            <h2>Inscription <small>Totalement gratuit, pour toujours.</small></h2>
            <hr class="colorgraph">
            <div class="row">
                <g:hasErrors bean="${utilisateur}">
                    <div class="alert alert-danger">
                    <ul>
                        <g:eachError var="err" bean="${utilisateur}">
                            <li>${err}</li>
                        </g:eachError>
                    </ul>
                    </div>
                </g:hasErrors>
                <g:if test="${utilisateur?.hasErrors() == false}">
                    <div class="alert alert-success">
                        <p>Votre inscription a bien été enregistrée.</p>
                    </div>
                </g:if>
            </div>
            <div class="row">
                <div class="col-xs-12 col-sm-6 col-md-6">
                    <div class="form-group">
                        <input type="text" name="nom" id="nom" class="form-control input-lg" value="${fieldValue(bean:utilisateur,field:'nom')}" placeholder="Nom" tabindex="1">
                    </div>
                </div>
                <div class="col-xs-12 col-sm-6 col-md-6">
                    <div class="form-group">
                        <input type="text" name="prenom" id="prenom" class="form-control input-lg" value="${fieldValue(bean:utilisateur,field:'prenom')}" placeholder="Prénom" tabindex="2">
                    </div>
                </div>
            </div>
            <div class="form-group">
                <input type="text" name="pseudo" id="pseudo" class="form-control input-lg" placeholder="Pseudo" value="${fieldValue(bean:utilisateur,field:'pseudo')}" tabindex="3">
            </div>
            <div class="form-group">
                <input type="email" name="email" id="email" class="form-control input-lg" placeholder="Adresse Email" value="${fieldValue(bean:utilisateur,field:'email')}" tabindex="4">
            </div>
            <div class="row">
                <div class="col-xs-12 col-sm-6 col-md-6">
                    <div class="form-group">
                        <input type="password" name="motDePasse" id="motDePasse" value="${fieldValue(bean:utilisateur,field:'motDePasse')}" class="form-control input-lg" placeholder="Mot de passe"  tabindex="5">
                    </div>
                </div>
                <div class="col-xs-12 col-sm-6 col-md-6">
                    <div class="form-group">
                        <input type="password" name="motDePasseConfirmation" id="motDePasseConfirmation" class="form-control input-lg" value="${fieldValue(bean:utilisateur,field:'motDePasseConfirmation')}" placeholder="Mot de passe (confirmation)" tabindex="6">
                    </div>
                </div>
            </div>
            <div class="row">
                <div class="col-xs-12 col-sm-6 col-md-6">
                    <div class="form-group">
                        <g:datePicker name="dateNaissance" value="${new Date()}" precision="day"/>

                    </div>
                </div>
                <div class="col-xs-12 col-sm-6 col-md-6">
                    <div class="form-group">
                        <input type="text" name="telephone" id="telephone" class="form-control input-lg" value="${fieldValue(bean:utilisateur,field:'telephone')}" placeholder="Téléphone" tabindex="6">
                    </div>
                </div>
            </div>

            <hr class="colorgraph">
            <div class="row">
                <div class="col-xs-12 col-md-6"><input type="submit" value="M'inscrire" class="btn btn-primary btn-block btn-lg" tabindex="7"></div>
                <div class="col-xs-12 col-md-6"><g:link controller="utilisateur" action="connexion" class="btn btn-success btn-block btn-lg">Se connecter</g:link></div>
            </div>
        </g:form>
    </div>
</div>
<!-- Modal -->
<div class="modal fade" id="t_and_c_m" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
                <h4 class="modal-title" id="myModalLabel">Terms & Conditions</h4>
            </div>
            <div class="modal-body">
                <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit. Similique, itaque, modi, aliquam nostrum at sapiente consequuntur natus odio reiciendis perferendis rem nisi tempore possimus ipsa porro delectus quidem dolorem ad.</p>
                <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit. Similique, itaque, modi, aliquam nostrum at sapiente consequuntur natus odio reiciendis perferendis rem nisi tempore possimus ipsa porro delectus quidem dolorem ad.</p>
                <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit. Similique, itaque, modi, aliquam nostrum at sapiente consequuntur natus odio reiciendis perferendis rem nisi tempore possimus ipsa porro delectus quidem dolorem ad.</p>
                <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit. Similique, itaque, modi, aliquam nostrum at sapiente consequuntur natus odio reiciendis perferendis rem nisi tempore possimus ipsa porro delectus quidem dolorem ad.</p>
                <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit. Similique, itaque, modi, aliquam nostrum at sapiente consequuntur natus odio reiciendis perferendis rem nisi tempore possimus ipsa porro delectus quidem dolorem ad.</p>
                <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit. Similique, itaque, modi, aliquam nostrum at sapiente consequuntur natus odio reiciendis perferendis rem nisi tempore possimus ipsa porro delectus quidem dolorem ad.</p>
                <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit. Similique, itaque, modi, aliquam nostrum at sapiente consequuntur natus odio reiciendis perferendis rem nisi tempore possimus ipsa porro delectus quidem dolorem ad.</p>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-primary" data-dismiss="modal">I Agree</button>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal-dialog -->
</div><!-- /.modal -->

<script>
    $(function () {
        $('.button-checkbox').each(function () {

            // Settings
            var $widget = $(this),
                    $button = $widget.find('button'),
                    $checkbox = $widget.find('input:checkbox'),
                    color = $button.data('color'),
                    settings = {
                        on: {
                            icon: 'glyphicon glyphicon-check'
                        },
                        off: {
                            icon: 'glyphicon glyphicon-unchecked'
                        }
                    };

            // Event Handlers
            $button.on('click', function () {
                $checkbox.prop('checked', !$checkbox.is(':checked'));
                $checkbox.triggerHandler('change');
                updateDisplay();
            });
            $checkbox.on('change', function () {
                updateDisplay();
            });

            // Actions
            function updateDisplay() {
                var isChecked = $checkbox.is(':checked');

                // Set the button's state
                $button.data('state', (isChecked) ? "on" : "off");

                // Set the button's icon
                $button.find('.state-icon')
                        .removeClass()
                        .addClass('state-icon ' + settings[$button.data('state')].icon);

                // Update the button's color
                if (isChecked) {
                    $button
                            .removeClass('btn-default')
                            .addClass('btn-' + color + ' active');
                }
                else {
                    $button
                            .removeClass('btn-' + color + ' active')
                            .addClass('btn-default');
                }
            }

            // Initialization
            function init() {

                updateDisplay();

                // Inject the icon if applicable
                if ($button.find('.state-icon').length == 0) {
                    $button.prepend('<i class="state-icon ' + settings[$button.data('state')].icon + '"></i>');
                }
            }
            init();
        });
    });
</script>
</html>