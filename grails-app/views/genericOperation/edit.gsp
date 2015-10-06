<%@ page import="com.sme.entities.GenericOperation" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="adminpage">
		<g:set var="entityName" value="${message(code: 'genericOperation.label', default: 'GenericOperation')}" />
		<title><g:message code="default.edit.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#edit-genericOperation" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><g:link class="home" action="show" params="['id': genericOperationInstance?.id]"><g:message code="default.home.label"/></g:link></li>
				<li><g:link class="list" action="index"><g:message code="genericOperation.list" /></g:link></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="edit-genericOperation" class="content scaffold-edit" role="main">
			<h1><g:message code="default.edit.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<g:hasErrors bean="${genericOperationInstance}">
			<ul class="errors" role="alert">
				<g:eachError bean="${genericOperationInstance}" var="error">
				<li <g:if test="${error in org.springframework.validation.FieldError}">data-field-id="${error.field}"</g:if>><g:message error="${error}"/></li>
				</g:eachError>
			</ul>
			</g:hasErrors>
			<g:form url="[resource:genericOperationInstance, action:'update']" method="PUT" >
				<g:hiddenField name="version" value="${genericOperationInstance?.version}" />
				<fieldset class="form">
					<g:render template="form"/>
				</fieldset>
				<fieldset class="buttons">
					<g:actionSubmit class="save" action="update" value="${message(code: 'default.button.update.label', default: 'Update')}" />
				</fieldset>
			</g:form>
		</div>
	</body>
</html>
