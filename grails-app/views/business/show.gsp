
<%@ page import="com.sme.entities.Business" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'business.label', default: 'Business')}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#show-business" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="list" action="index"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="show-business" class="content scaffold-show" role="main">
			<h1><g:message code="default.show.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<ol class="property-list business">
			
				<g:if test="${businessInstance?.name}">
				<li class="fieldcontain">
					<span id="name-label" class="property-label"><g:message code="business.name.label" default="Name" /></span>
					
						<span class="property-value" aria-labelledby="name-label"><g:fieldValue bean="${businessInstance}" field="name"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${businessInstance?.regNumber}">
				<li class="fieldcontain">
					<span id="regNumber-label" class="property-label"><g:message code="business.regNumber.label" default="Reg Number" /></span>
					
						<span class="property-value" aria-labelledby="regNumber-label"><g:fieldValue bean="${businessInstance}" field="regNumber"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${businessInstance?.incorpDate}">
				<li class="fieldcontain">
					<span id="incorpDate-label" class="property-label"><g:message code="business.incorpDate.label" default="Incorp Date" /></span>
					
						<span class="property-value" aria-labelledby="incorpDate-label"><g:formatDate date="${businessInstance?.incorpDate}" /></span>
					
				</li>
				</g:if>
			
				<g:if test="${businessInstance?.address}">
				<li class="fieldcontain">
					<span id="address-label" class="property-label"><g:message code="business.address.label" default="Address" /></span>
					
						<span class="property-value" aria-labelledby="address-label"><g:fieldValue bean="${businessInstance}" field="address"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${businessInstance?.city}">
				<li class="fieldcontain">
					<span id="city-label" class="property-label"><g:message code="business.city.label" default="City" /></span>
					
						<span class="property-value" aria-labelledby="city-label"><g:fieldValue bean="${businessInstance}" field="city"/></span>
					
				</li>
				</g:if>
			
			</ol>
			<g:form url="[resource:businessInstance, action:'delete']" method="DELETE">
				<fieldset class="buttons">
					<g:link class="edit" action="edit" resource="${businessInstance}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
					<g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
				</fieldset>
			</g:form>
		</div>
	</body>
</html>
