<%@ page contentType="text/html;charset=UTF-8" %>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="layout" content="adminpage"/>
        <title>Transactions</title>
    </head>
    <body>
    <body>
        <div style="width: 100%; background: #D0E8F4; display: inline-block; text-align: right; border-bottom: 0px solid #6285C7; border-top: 0px solid #6285C7; padding: 5px 0;">
            <g:link style="float: left;" action="show" params="['max': params.max, 'offset': params.offset, 'id': businessInstance?.id]"><img class="image-link" style="margin-left: 5px;" src="${resource(dir: 'images', file: 'arrow_left.png')}" title="<g:message code='actions.back'/>"/></g:link>
            </div>      

        <h1 class="sub-title">${businessInstance?.name}</h1>
        <div class="edit-form-box">

            <label class="edit-form"><g:message code="business.name.label"/></label>
            <g:textField class="text-input" name="name" value="${businessInstance?.name}" readonly="true"/>
            <br/>
            <label class="edit-form"><g:message code="business.profile.label"/></label>
            <g:textField class="text-input" name="name" value="${businessInstance?.profile?.name}" readonly="true"/>

        </div>  

        <g:if test="${transactionsList.size() > 0}">
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
                
                <g:each in="${transactionsList}" status="index" var="transInstance">
                    
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
        </g:if>
        <g:else>
            <h1 class="sub-title" style="color: #d13f31;"><g:message code="default.application.notransactionsfound"/></h1>
        </g:else>

    </body>
</html>
