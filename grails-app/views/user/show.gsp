
<%@ page import="com.sme.entities.User" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="adminpage">
		<g:set var="entityName" value="${message(code: 'user.label', default: 'User')}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#show-user" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<!--li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li-->
				<li><g:link class="list" action="index"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="show-user" class="content scaffold-show" role="main">
			<h1><g:message code="default.show.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<ol class="property-list user">
			
				<g:if test="${userInstance?.name}">
				<li class="fieldcontain">
					<span id="name-label" class="property-label"><g:message code="user.name.label" default="Name" /></span>
					
						<span class="property-value" aria-labelledby="name-label"><g:fieldValue bean="${userInstance}" field="name"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${userInstance?.login}">
				<li class="fieldcontain">
					<span id="login-label" class="property-label"><g:message code="user.login.label" default="Login" /></span>
					
						<span class="property-value" aria-labelledby="login-label"><g:fieldValue bean="${userInstance}" field="login"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${userInstance?.passw}">
				<li class="fieldcontain">
					<span id="passw-label" class="property-label"><g:message code="user.passw.label" default="Passw" /></span>
					
						<span class="property-value" aria-labelledby="passw-label"><g:fieldValue bean="${userInstance}" field="passw"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${userInstance?.contactNo}">
				<li class="fieldcontain">
					<span id="contactNo-label" class="property-label"><g:message code="user.contactNo.label" default="Contact No" /></span>
					
						<span class="property-value" aria-labelledby="contactNo-label"><g:fieldValue bean="${userInstance}" field="contactNo"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${userInstance?.email}">
				<li class="fieldcontain">
					<span id="email-label" class="property-label"><g:message code="user.email.label" default="Email" /></span>
					
						<span class="property-value" aria-labelledby="email-label"><g:fieldValue bean="${userInstance}" field="email"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${userInstance?.role}">
				<li class="fieldcontain">
					<span id="role-label" class="property-label"><g:message code="user.role.label" default="Role" /></span>
					
						<span class="property-value" aria-labelledby="role-label"><g:link controller="userRole" action="show" id="${userInstance?.role?.id}">${userInstance?.role?.encodeAsHTML()}</g:link></span>
					
				</li>
				</g:if>
			
				<g:if test="${userInstance?.company}">
				<li class="fieldcontain">
					<span id="company-label" class="property-label"><g:message code="user.company.label" default="Company" /></span>
					
						<span class="property-value" aria-labelledby="company-label"><g:link controller="business" action="show" id="${userInstance?.company?.id}">${userInstance?.company?.encodeAsHTML()}</g:link></span>
					
				</li>
				</g:if>
			
			</ol>
			<g:form url="[resource:userInstance, action:'delete']" method="DELETE">
				<fieldset class="buttons">
					<g:link class="edit" action="edit" resource="${userInstance}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
					<g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
				</fieldset>
			</g:form>
		</div>
	</body>
</html>
