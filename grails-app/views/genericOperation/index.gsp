
<%@ page import="com.sme.entities.GenericOperation" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'genericOperation.label', default: 'GenericOperation')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#list-genericOperation" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="list-genericOperation" class="content scaffold-list" role="main">
			<h1><g:message code="default.list.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
				<div class="message" role="status">${flash.message}</div>
			</g:if>
			<table>
			<thead>
					<tr>
					
						<g:sortableColumn property="dateCreated" title="${message(code: 'genericOperation.dateCreated.label', default: 'Date Created')}" />
					
						<g:sortableColumn property="lastUpdated" title="${message(code: 'genericOperation.lastUpdated.label', default: 'Last Updated')}" />
					
						<g:sortableColumn property="code" title="${message(code: 'genericOperation.code.label', default: 'Code')}" />
					
						<g:sortableColumn property="name" title="${message(code: 'genericOperation.name.label', default: 'Name')}" />
					
						<th><g:message code="genericOperation.accountType.label" default="Account Type" /></th>
					
						<g:sortableColumn property="inbound" title="${message(code: 'genericOperation.inbound.label', default: 'Inbound')}" />
					
					</tr>
				</thead>
				<tbody>
				<g:each in="${genericOperationInstanceList}" status="i" var="genericOperationInstance">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
					
						<td><g:link action="show" id="${genericOperationInstance.id}">${fieldValue(bean: genericOperationInstance, field: "dateCreated")}</g:link></td>
					
						<td><g:formatDate date="${genericOperationInstance.lastUpdated}" /></td>
					
						<td>${fieldValue(bean: genericOperationInstance, field: "code")}</td>
					
						<td>${fieldValue(bean: genericOperationInstance, field: "name")}</td>
					
						<td>${fieldValue(bean: genericOperationInstance, field: "accountType")}</td>
					
						<td><g:formatBoolean boolean="${genericOperationInstance.inbound}" /></td>
					
					</tr>
				</g:each>
				</tbody>
			</table>
			<div class="pagination">
				<g:paginate total="${genericOperationInstanceCount ?: 0}" />
			</div>
		</div>
	</body>
</html>
