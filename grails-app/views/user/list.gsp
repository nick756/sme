<html>
	<head>
            <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
            <meta name="layout" content="adminpage">
            <title>SIFAR</title>
	</head>
	<body>
            <div class="action-header">
                <g:link controller="user" action="create" params="['max': params.max, 'offset': params.offset]"><img class="image-link" style="margin-right: 5px;" src="${resource(dir: 'images', file: 'add_record.png')}" title="<g:message code='actions.user.add'/>"/></g:link>
                <g:link style="float: right;" target="_blank" controller="user" action="report" params="['search_name': searchByName, 'search_login': searchByLogin, 'search_company': searchByCompany, 'search_account': searchByAccount]"><img class="image-link" style="margin-left: 5px;" src="${resource(dir: 'images', file: 'printer.png')}" title="<g:message code='actions.print'/>"/></g:link>
            </div>
            <table class="righted-content" style="border: none; background: #fff; margin-top: 0;">
                <caption style="background: #fff; padding: 0; border: none;"><g:message code="user.list"/></caption>
                <tr>
                    <td colspan="4" style="border: none; margin: 0; padding: 10px 0; background: #D0E8F4;">
                        <g:form action="list" params="['offset': 0]">
                            <label class="edit-form"><g:message code="user.name.label"/></label>
                            <g:textField class="text-input" name="search_name" value="${searchByName}"/>
                            <br/>
                            <label class="edit-form"><g:message code="user.login.label"/></label>
                            <g:textField class="text-input" name="search_login" value="${searchByLogin}"/>  
                            <br/>
                            <label class="edit-form"><g:message code="business.accountno.label"/></label>
                            <g:textField class="text-input" name="search_account" value="${searchByAccount}"/>  
                            <br/>                            
                            <label class="edit-form"><g:message code="user.company"/></label>
                            <g:textField class="text-input" name="search_company" value="${searchByCompany}"/> 
                            <input type="submit" value="<g:message code='default.application.filter.show'/>" class="myButton" style="float: right; margin-right: 10px;"/>
                        </g:form>
                    </td>
                </tr>
                <tr>
                    <g:sortableColumn property="name" title="${message(code: 'user.name.label', default: 'Name')}" />
                    <g:sortableColumn property="login" title="${message(code: 'user.login.label', default: 'Login')}" />
                    <th><g:message code="business.accountno.label"/></th>
                    <th><g:message code="user.company"/></th>
                </tr>
                <g:each in="${userInstanceList}" status="i" var="userInstance">
                    <tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
                    <td class="no-border"><g:link action="show" id="${userInstance.id}" style="text-decoration: none;">${userInstance.name}</g:link></td>
                    <td class="no-border">${userInstance?.login}</td>
                    <td class="no-border">${userInstance?.company?.accountNo}</td>
                    <td class="no-border">${userInstance?.company?.name}</td>
                    </tr>
                </g:each>
            </table>
            <div class="pagination">
                    <g:paginate total="${userInstanceCount ?: 0}" params="['search_name': searchByName, 'search_login': searchByLogin, 'search_company': searchByCompany, 'search_account': searchByAccount]"/>
            </div>
            <div class="summary-label">
                <g:message code="default.application.totalrecords"/>&nbsp;${userInstanceCount}
            </div>
	</body>
</html>
