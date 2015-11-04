<%@ page contentType="text/html;charset=UTF-8" %>

<html>
    <head>
        <meta name="layout" content="adminpage"/>
        <title>SIFAR</title>
    </head>
    <body>
        <div style="width: 100%; background: #D0E8F4; 
            display: inline-block; text-align: right; 
            border-bottom: 0px solid #6285C7; 
            border-top: 0px solid #6285C7; padding: 5px 0;">
            <g:link style="float: right;" target="_blank" controller="report" action="companieslist" params="['filterName': params?.filterName, 'filterAccount': params?.filterAccount, 'filterCity': params?.filterCity]"><img class="image-link" style="margin-left: 5px;" src="${resource(dir: 'images', file: 'printer.png')}" title="<g:message code='actions.print'/>"/></g:link>
            <g:link 
                controller="adminHome" action="createinstance" 
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
            <tr style="background: #d0d0d0;">
                <td colspan="6" style="border: none; margin: 0; padding: 10px 0; background: #dadada;">
                    <g:form action="index" params="['offset': 0]">
                        <label class="edit-form"><g:message code="business.name.label"/></label>
                        <g:textField class="text-input" name="filterName" value="${params?.filterName}"/>
                        <br/>
                        <label class="edit-form"><g:message code="business.accountno.label"/></label>
                        <g:textField class="text-input" name="filterAccount" value="${params?.filterAccount}"/>  
                        <br/>
                        <label class="edit-form"><g:message code="business.city.label"/></label>
                        <g:textField class="text-input" name="filterCity" value="${params?.filterCity}"/>  
                        <input type="submit" value="<g:message code='default.application.filter.show'/>" class="myButton" style="float: right; margin-right: 10px;"/>
                    </g:form>                                        
                </td>
            </tr>
            <tr>
                <th>No</th>
                <g:sortableColumn property="name" title="${message(code: 'business.name.label')}" params="['filterName': params?.filterName, 'filterAccount': params?.filterAccount, 'filterCity': params.filterCity]"/>
                <th><g:message code="business.accountno.label"/></th>
                <th><g:message code="business.registrationdate.label"/></th>
                <th><g:message code="business.city.label"/></th>
                <th><g:message code="business.userscount.label"/></th>
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
                        ${businessInstance?.accountNo}
                    </td>
                    <td class="no-border centered">
                            <g:formatDate format="dd/MM/yyyy" date="${businessInstance?.registrationDate}"/>
                    </td>
                    <td class="no-border">${businessInstance?.city}</td>
                    <td class="no-border centered">${businessInstance?.users.asList().size()}</td>
                </tr>
            </g:each>

        </table>
        <div class="pagination" style="display: inline-block; width: 100%; float: right;">
            <g:paginate total="${businessInstanceCount ?: 0}" params="['filterName': params?.filterName, 'filterAccount': params?.filterAccount, 'filterCity': params.filterCity]"/>
        </div>
        <div class="summary-label">
            <g:message code="default.application.totalrecords"/>&nbsp;${businessInstanceCount}
        </div>
    </body>
</html>
