<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="com.sme.entities.GenericOperation" %>

<html>
    <head>
        <meta name="layout" content="adminpage"/>
        <title>SIFAR</title>
    </head>
    <body>
        <g:if test="${flash.message}">
            <div class="message" role="status">${flash.message}</div>
        </g:if>
        <table class="righted-content" style="background: #fff; border: none;">
            <caption style="background: #fff;">List of Operation Types</caption>
            <tr>

                <g:sortableColumn property="code" title="${message(code: 'genericOperation.code.label', default: 'Code')}" />

                <g:sortableColumn property="name" title="${message(code: 'genericOperation.name.label', default: 'Name')}" />

                <th><g:message code="genericOperation.accountType.label" default="Account Type" /></th>

                    <g:sortableColumn property="inbound" title="${message(code: 'genericOperation.inbound.label', default: 'Inbound')}" />
                    <g:sortableColumn property="outbound" title="${message(code: 'genericOperation.outbound.label', default: 'Outbound')}" />

            </tr>
            <g:each in="${genericOperationInstanceList}" status="i" var="genericOperationInstance">
                <tr class="${(i % 2) == 0 ? 'even' : 'odd'}">

                    <td class="no-border centered"><g:link action="show" id="${genericOperationInstance.id}">${genericOperationInstance.code}</g:link></td>

                    <td class="no-border"><g:link action="show" id="${genericOperationInstance.id}">${genericOperationInstance}</g:link></td>

                    <td class="no-border">${genericOperationInstance.accountType?.name}</td>

                    <td class="no-border"><g:formatBoolean boolean="${genericOperationInstance.inbound}" /></td>
                    <td class="no-border"><g:formatBoolean boolean="${genericOperationInstance.outbound}" /></td>

                </tr>
            </g:each>
        </table>
        <div class="pagination">
            <g:paginate total="${genericOperationInstanceCount ?: 0}" />
        </div>
    </body>
</html>
