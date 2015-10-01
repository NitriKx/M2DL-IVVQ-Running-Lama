<%@ page import="com.runninglama.Vehicule" %>

<div class="form-group fieldcontain ${hasErrors(bean: vehiculeInstance, field: 'nb_place', 'error')} required">
	<label for="nb_place">
		<g:message code="vehicule.nb_place.label" default="Seating Capacity" />
		<span class="required-indicator">*</span>
	</label>
	<g:field class="form-control" name="nb_place" type="number" min="1" value="${vehiculeInstance.nb_place}" required="" placeholder="1" />
</div>



<div class="form-group fieldcontain ${hasErrors(bean: vehiculeInstance, field: 'marque', 'error')} required">
	<label for="marque">
		<g:message code="vehicule.marque.label" default="Marque" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="marque" required="" value="${vehiculeInstance?.marque}"/>
</div>

<!--

CES CHAMPS DOIVENT ÊTRE MIS À JOUR AVEC LE THÈME BOOTSTRAP

<div class="fieldcontain ${hasErrors(bean: vehiculeInstance, field: 'modele', 'error')} required">
	<label for="modele">
		<g:message code="vehicule.modele.label" default="Modele" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="modele" required="" value="${vehiculeInstance?.modele}"/>

</div>

<div class="fieldcontain ${hasErrors(bean: vehiculeInstance, field: 'annee', 'error')} required">
	<label for="annee">
		<g:message code="vehicule.annee.label" default="Annee" />
		<span class="required-indicator">*</span>
	</label>
	<g:datePicker name="annee" precision="day"  value="${vehiculeInstance?.annee}"  />

</div>

<div class="fieldcontain ${hasErrors(bean: vehiculeInstance, field: 'kilometrage', 'error')} required">
	<label for="kilometrage">
		<g:message code="vehicule.kilometrage.label" default="Kilometrage" />
		<span class="required-indicator">*</span>
	</label>
	<g:field name="kilometrage" type="number" value="${vehiculeInstance.kilometrage}" required=""/>

</div>

<div class="fieldcontain ${hasErrors(bean: vehiculeInstance, field: 'type', 'error')} required">
<label for="type">
	<g:message code="vehicule.type.label" default="Type" />
	<span class="required-indicator">*</span>
</label>
<g:select name="type" from="${com.runninglama.TypeVehicule?.values()}" keys="${com.runninglama.TypeVehicule.values()*.name()}" required="" value="${vehiculeInstance?.type?.name()}" />
-->