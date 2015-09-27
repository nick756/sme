<%@ page contentType="text/html;charset=UTF-8" %>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="layout" content="smepage"/>
        <title>SIFAR</title>
    </head>
    <body>
        <div class="action-header">
            <g:link style="float: right; margin-right: 5px;" action="create" params="['max': params.max, 'offset': params.offset, 'id': businessInstance?.id]"><img class="image-link" style="margin-left: 5px;" src="${resource(dir: 'images', file: 'add_record.png')}" title="<g:message code='actions.transaction.add'/>"/></g:link>
        </div>
        <table class="righted-content">
            <caption><g:message code="default.application.transactionslist"/></caption>
                <tr>
                    <th>No</th>
                    <th><g:message code="businesstransaction.date.label"/></th>
                    <th><g:message code="businesstransaction.type.label"/></th>
                    <th><g:message code="businesstransaction.amount.label"/></th>
                    <th><g:message code="businesstransaction.remarks.label"/></th>
                    <th><g:message code="businesstransaction.operator.label"/></th>
                </tr>
                <g:each in="${transactions}" status="index" var="transInstance">

                    <g:if test="${new Integer(params.offset) > 0}">
                    <g:set var="offset" value="${new Integer(params.offset)}"/>
                    </g:if>
                    <g:else><g:set var="offset" value="${0}"/></g:else> 

                    <tr class="${(index % 2) == 0 ? 'even' : 'odd'}">
                        <td class="no-border centered">${offset + index + 1}</td>
                        <td class="no-border centered">${formatDate(format:'dd/MM/yyyy', date:transInstance?.transactionDate)}</td>
                        <td class="no-border">${transInstance?.operationType}</td>
                        <td class="no-border righted"><g:formatNumber number="${transInstance?.transactionAmount}" format="#,##0.00" /></td>
                        <td class="no-border">${transInstance?.transactionRemarks}</td>
                        <td class="no-border">${transInstance?.operator}</td>
                    </tr>

                </g:each>
        </table>
        <div class="pagination" style="display: inline-block; width: 100%; float: right;">
            <g:paginate total="${counter ?: 0}"/>
        </div>  
        <div class="summary-label">
            <g:message code="default.application.totalrecords"/>&nbsp;${counter}
        </div>        
    </body>
</html>
