<%@ page contentType="text/html;charset=UTF-8" %>

<html>
    <head>
        <meta name="layout" content="adminpage"/>
        <title>SME</title>
    </head>
    <body>
        <div style="width: 100%; background: #D0E8F4; 
            display: inline-block; text-align: right; 
            border-bottom: 0px solid #6285C7; 
            border-top: 0px solid #6285C7; padding: 5px 0;">
            
            <g:link 
                controller="business" action="create" 
                params="['max': params.max, 'offset': params.offset]">
                    <img class="image-link" 
                        style="margin-right: 5px;" 
                        src="${resource(dir: 'images', file: 'add_record.png')}" 
                        title="<g:message code='actions.business.add'/>"
                    />
            </g:link>
        </div>
        <table class="righted-content">
            <caption><g:message code="business.caption"/></caption>
            <tr>
                <th>No</th>
                <g:sortableColumn property="name" 
                    title="${message(code: 'business.name.label')}" />
                <th><g:message code="business.regNumber.label"/></th>
                <th><g:message code="business.incorpDate.label"/></th>
                <th>City</th>
            </tr>

            <g:each status="index" in="${businesses}" var="businessInstance">

                <g:if test="${new Integer(params.offset) > 0}">
                    <g:set var="offset" value="${new Integer(params.offset)}"/>
                </g:if>
                <g:else><g:set var="offset" value="${0}"/></g:else>

                <tr class="${(index % 2) == 0 ? 'even' : 'odd'}">
                    <td class="no-border centered">${offset + index + 1}</td>
                    <td class="no-border">
                        <g:link class="action-link" 
                            controller="adminHome" 
                            action="show" id="${businessInstance?.id}" 
                            params="['max': params.max, 'offset': params.offset]">
                                    ${businessInstance?.name}
                        </g:link>
                    </td>
                    <td class="no-border centered">
                        ${businessInstance?.regNumber}
                    </td>
                    <td class="no-border centered">
                            <g:formatDate format="dd/MM/yyyy" date="${businessInstance?.incorpDate}"/>
                    </td>
                    <td class="no-border">${businessInstance?.city}</td>
                </tr>
            </g:each>

        </table>
        <div class="pagination" style="display: inline-block; width: 100%; float: right;">
            <g:paginate total="${businessInstanceCount ?: 0}" />
        </div>
        <div class="summary-label">
            <g:message code="default.application.totalrecords"/>&nbsp;${businessInstanceCount}
        </div>
    </body>
</html>
