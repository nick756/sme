<%@ page contentType="text/html;charset=UTF-8" %>
<%@ import com.sme.entities.* %>

<html>
    <head>
        <meta name="layout" content="adminpage"/>
        <title>Business Profiles</title>
    </head>
    <body>
        <div style="width: 100%; background: #D0E8F4; display: inline-block; text-align: right; border-bottom: 0px solid #6285C7; border-top: 0px solid #6285C7; padding: 5px 0;">
            <g:link action="add" params="['max': params.max, 'offset': params.offset]"><img class="image-link" style="margin-right: 5px;" src="${resource(dir: 'images', file: 'add_record.png')}" title="<g:message code='actions.profile.add'/>"/></g:link>
        </div>
        <table class="righted-content">
            <caption><g:message code="genericProfile.caption"/></caption>
            <tr>
                <th>No</th>
                <g:sortableColumn property="name" title="${message(code: 'genericProfile.name.label')}" />
                <th><g:message code="genericProfile.code.label"/></th>
                <th><g:message code="genericProfile.operations.label"/></th>
            </tr>

            <g:each status="index" in="${profiles}" var="profileInstance">

                <g:if test="${params.offset > 0}"><g:set var="offset" value="${new Integer(params.offset)}"/></g:if>
                <g:else><g:set var="offset" value="${0}"/></g:else>

                <tr class="${(index % 2) == 0 ? 'even' : 'odd'}">
                    <td class="no-border centered">${offset + index + 1}</td>
                    <td class="no-border"><g:link class="action-link" controller="profile" action="show" id="${profileInstance?.id}" params="['max': params.max, 'offset': params.offset]">${profileInstance?.name}</g:link></td>
                    <td class="no-border centered">${profileInstance?.code}</td>
                    <td class="no-border centered">${profileInstance?.operations?.size()}</td>
                </tr>
            </g:each>

        </table>
        <div class="pagination" style="display: inline-block; width: 100%; float: right;">
            <g:paginate total="${profileCount ?: 0}" />
        </div>
        <div class="summary-label">
            <g:message code="default.application.totalrecords"/>&nbsp;${profileCount}
        </div>

    </body>
</html>
