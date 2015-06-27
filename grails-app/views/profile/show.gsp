<%@ page contentType="text/html;charset=UTF-8" %>
<%@ import com.sme.entities.* %>

<html>
    <head>
        <meta name="layout" content="adminpage"/>
        <title>Business Profile Details</title>
    </head>
    <body>
        <div style="width: 100%; background: #D0E8F4; display: inline-block; text-align: right; border-bottom: 0px solid #6285C7; border-top: 0px solid #6285C7; padding: 5px 0;">
            <g:link style="float: left;" action="index" params="['max': params.max, 'offset': params.offset]"><img class="image-link" style="margin-left: 5px;" src="${resource(dir: 'images', file: 'arrow_left.png')}" title="<g:message code='actions.back'/>"/></g:link>
            <g:link action="add" params="['max': params.max, 'offset': params.offset]"><img class="image-link" style="margin-right: 5px;" src="${resource(dir: 'images', file: 'add_record.png')}" title="<g:message code='actions.profile.add'/>"/></g:link>
        </div>
        <h1 class="sub-title">${profileInstance?.name}</h1>
        <div class="edit-form-box">
            <label class="edit-form"><g:message code="genericProfile.code.label"/></label>
            <g:textField class="text-input" name="name" value="${profileInstance?.code}" readonly="true"/>
            <br/>
            <label class="edit-form"><g:message code="genericProfile.name.label"/></label>
            <g:textField class="text-input" name="name" value="${profileInstance?.name}" readonly="true"/>
            <br/>
            <label class="edit-form"><g:message code="default.date.created"/></label>
            <g:textField class="text-input" name="name" value="${formatDate(format: 'dd/MM/yyyy', date: profileInstance?.dateCreated)}" readonly="true"/>
            <br/>
            <label class="edit-form"><g:message code="default.date.lastUpdated"/></label>
            <g:textField class="text-input" name="name" value="${formatDate(format: 'dd/MM/yyyy', date: profileInstance?.lastUpdated)}" readonly="true"/>              
        </div>
            <h1 class="sub-title"><g:message code=""/></h1>
         
        <g:form name="updateChb" controller='profile' action="show" params="['update': 1, 'max': params.max, 'offset': params.offset, 'id': profileInstance?.id]">            
        <table class="righted-content">
            
            <caption><g:message code="genericOperation.list"/></caption>
            <tr>
                <th></th>
                <th><g:message code="genericOperation.active.label"/></th>
                <g:sortableColumn property="code" title="${message(code: 'genericOperation.code.label')}" titleKey="genericOperation.code" action="show"/>
                <g:sortableColumn property="name" title="${message(code: 'genericOperation.name.label')}" titleKey="genericOperation.name"/>
                <th><g:message code="genericOperation.inbound.label"/></th>
                <th><g:message code="genericOperation.outbound.label"/></th>
                <g:sortableColumn property="accountType" title="${message(code: 'genericOperation.accounttype.label')}" titleKey="genericOperation.accountType.name"/>
            </tr>

            <g:each status="index" in="${operationList}" var="operationInstance">

                <g:if test="${new Integer(params.offset) > 0}"><g:set var="offset" value="${new Integer(params.offset)}"/></g:if>
                <g:else><g:set var="offset" value="${0}"/></g:else>

                <tr class="${(index % 2) == 0 ? 'even' : 'odd'}">
                    <td class="no-border centered" style="vertical-align: middle;"><g:checkBox name="checkbox.${operationInstance?.id}" value=""/></td>
                    <td class="no-border centered"><g:if test="${operationInstance?.active}">YES</g:if><g:else>-</g:else></td>
                    <td class="no-border centered">${operationInstance?.operation?.code}</td>
                    <td class="no-border">${operationInstance?.operation?.name}</td>
                    <td class="no-border centered" style="font-weight: bold;">${operationInstance?.operation?.inbound ? '+': '-'}</td>
                    <td class="no-border centered" style="font-weight: bold;">${operationInstance?.operation?.outbound ? '+':'-'}</td>
                    <td class="no-border centered">${operationInstance?.operation?.accountType?.name}</td>
                </tr>
            </g:each>

        </table>
        <div class="pagination" style="display: block; width: 100%; float: right;">
            <g:paginate total="${operationsCount ?: 0}" params="['id': profileInstance?.id]"/>
        </div>
        <input type="submit" style="float: right; display: inline-block; margin-top: 15px;" value="<g:message code='actions.login.submit'/>" class="myButton" />
        </g:form>        
        <div class="summary-label">
            <g:message code="default.application.totalrecords"/>&nbsp;${operationsCount}
        </div>

    </body>
</html>
