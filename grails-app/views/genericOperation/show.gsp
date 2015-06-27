
<%@ page import="com.sme.entities.GenericOperation" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'genericOperation.label', default: 'GenericOperation')}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#show-genericOperation" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="list" action="index"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="show-genericOperation" class="content scaffold-show" role="main">
			<h1><g:message code="default.show.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<ol class="property-list genericOperation">
			
				<g:if test="${genericOperationInstance?.dateCreated}">
				<li class="fieldcontain">
					<span id="dateCreated-label" class="property-label"><g:message code="genericOperation.dateCreated.label" default="Date Created" /></span>
					
						<span class="property-value" aria-labelledby="dateCreated-label"><g:formatDate date="${genericOperationInstance?.dateCreated}" /></span>
					
				</li>
				</g:if>
			
				<g:if test="${genericOperationInstance?.lastUpdated}">
				<li class="fieldcontain">
					<span id="lastUpdated-label" class="property-label"><g:message code="genericOperation.lastUpdated.label" default="Last Updated" /></span>
					
						<span class="property-value" aria-labelledby="lastUpdated-label"><g:formatDate date="${genericOperationInstance?.lastUpdated}" /></span>
					
				</li>
				</g:if>
			
				<g:if test="${genericOperationInstance?.code}">
				<li class="fieldcontain">
					<span id="code-label" class="property-label"><g:message code="genericOperation.code.label" default="Code" /></span>
					
						<span class="property-value" aria-labelledby="code-label"><g:fieldValue bean="${genericOperationInstance}" field="code"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${genericOperationInstance?.name}">
				<li class="fieldcontain">
					<span id="name-label" class="property-label"><g:message code="genericOperation.name.label" default="Name" /></span>
					
						<span class="property-value" aria-labelledby="name-label"><g:fieldValue bean="${genericOperationInstance}" field="name"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${genericOperationInstance?.accountType}">
				<li class="fieldcontain">
					<span id="accountType-label" class="property-label"><g:message code="genericOperation.accountType.label" default="Account Type" /></span>
					
						<span class="property-value" aria-labelledby="accountType-label"><g:link controller="accountType" action="show" id="${genericOperationInstance?.accountType?.id}">${genericOperationInstance?.accountType?.encodeAsHTML()}</g:link></span>
					
				</li>
				</g:if>
			
				<g:if test="${genericOperationInstance?.profiles}">
				<li class="fieldcontain">
					<span id="profiles-label" class="property-label"><g:message code="genericOperation.profiles.label" default="Profiles" /></span>
					
						<g:each in="${genericOperationInstance.profiles}" var="p">
						<span class="property-value" aria-labelledby="profiles-label"><g:link controller="profileLink" action="show" id="${p.id}">${p?.encodeAsHTML()}</g:link></span>
						</g:each>
					
				</li>
				</g:if>
			
				<g:if test="${genericOperationInstance?.inbound}">
				<li class="fieldcontain">
					<span id="inbound-label" class="property-label"><g:message code="genericOperation.inbound.label" default="Inbound" /></span>
					
						<span class="property-value" aria-labelledby="inbound-label"><g:formatBoolean boolean="${genericOperationInstance?.inbound}" /></span>
					
				</li>
				</g:if>
			
				<g:if test="${genericOperationInstance?.outbound}">
				<li class="fieldcontain">
					<span id="outbound-label" class="property-label"><g:message code="genericOperation.outbound.label" default="Outbound" /></span>
					
						<span class="property-value" aria-labelledby="outbound-label"><g:formatBoolean boolean="${genericOperationInstance?.outbound}" /></span>
					
				</li>
				</g:if>
			
			</ol>
			<g:form url="[resource:genericOperationInstance, action:'delete']" method="DELETE">
				<fieldset class="buttons">
					<g:link class="edit" action="edit" resource="${genericOperationInstance}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
					<g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
				</fieldset>
			</g:form>
		</div>
	</body>
</html>
