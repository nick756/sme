<html>
	<head>
            <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
            <meta name="layout" content="adminpage">
            <title>SIFAR</title>
	</head>
	<body>
            <div class="action-header">
                <g:link controller="user" action="create" params="['max': params.max, 'offset': params.offset]"><img class="image-link" style="margin-right: 5px;" src="${resource(dir: 'images', file: 'add_record.png')}" title="<g:message code='actions.user.add'/>"/></g:link>
                <g:link style="float: right;" target="_blank" controller="user" action="report"><img class="image-link" style="margin-left: 5px;" src="${resource(dir: 'images', file: 'printer.png')}" title="<g:message code='actions.print'/>"/></g:link>
            </div>
            <table class="righted-content" style="border: none; background: #fff;">
                <!--caption style="background: #fff; padding: 0; border: none;"><g:message code="user.list"/></caption-->
                <tr>
                    <g:sortableColumn property="name" title="${message(code: 'user.name.label', default: 'Name')}" />
                    <g:sortableColumn property="login" title="${message(code: 'user.login.label', default: 'Login')}" />
                    <th><g:message code="user.company"/></th>
                </tr>
                <g:each in="${userInstanceList}" status="i" var="userInstance">
                    <tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
                    <td class="no-border">${userInstance?.name}</td>
                    <td class="no-border">${userInstance?.login}</td>
                    <td class="no-border">${userInstance?.company?.name}</td>
                    </tr>
                </g:each>
            </table>
            <div class="pagination">
                    <g:paginate total="${userInstanceCount ?: 0}" />
            </div>
	</body>
</html>
