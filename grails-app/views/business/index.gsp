
<%@ page import="com.sme.entities.Business" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'business.label', default: 'Business')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#list-business" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="list-business" class="content scaffold-list" role="main">
			<h1><g:message code="default.list.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
				<div class="message" role="status">${flash.message}</div>
			</g:if>
			<table>
			<thead>
					<tr>
					
						<g:sortableColumn property="name" title="${message(code: 'business.name.label', default: 'Name')}" />
					
						<g:sortableColumn property="regNumber" title="${message(code: 'business.regNumber.label', default: 'Reg Number')}" />
					
						<g:sortableColumn property="incorpDate" title="${message(code: 'business.incorpDate.label', default: 'Incorp Date')}" />
					
						<g:sortableColumn property="address" title="${message(code: 'business.address.label', default: 'Address')}" />
					
						<g:sortableColumn property="city" title="${message(code: 'business.city.label', default: 'City')}" />
					
					</tr>
				</thead>
				<tbody>
				<g:each in="${businessInstanceList}" status="i" var="businessInstance">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
					
						<td><g:link action="show" id="${businessInstance.id}">${fieldValue(bean: businessInstance, field: "name")}</g:link></td>
					
						<td>${fieldValue(bean: businessInstance, field: "regNumber")}</td>
					
						<td><g:formatDate date="${businessInstance.incorpDate}" /></td>
					
						<td>${fieldValue(bean: businessInstance, field: "address")}</td>
					
						<td>${fieldValue(bean: businessInstance, field: "city")}</td>
					
					</tr>
				</g:each>
				</tbody>
			</table>
			<div class="pagination">
				<g:paginate total="${businessInstanceCount ?: 0}" />
			</div>
		</div>
	</body>
</html>
