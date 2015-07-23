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
        <table class="righted-content">
            <caption><g:message code="default.application.transactionslist"/></caption>
            <tr>
                <td colspan= "6" class="no-border" style="background: #d0e8f4; padding: 10px 5px 10px 5px;">
                    <g:form action="listtransactions" params="['offset': params?.offset, 'max':params?.max]">
                        <g:hiddenField name="instId" value="${businessInstance?.id}"/> 
                        <select name="filterOption" id="filterOption" style="width: 300px;">
                            <option value="1" ${operationFilter == 1 ? 'selected':''}><g:message code="default.application.filter.all"/></option>
                            <option value="2" ${operationFilter == 2 ? 'selected':''}><g:message code="default.application.filter.lastmonth"/></option>
                            <option value="3" ${operationFilter == 3 ? 'selected':''}><g:message code="default.application.filter.dates"/></option>
                        </select>
                        <g:message code="default.application.dates.range"/>
                        <g:textField class="text-input" style="width: 150px;" name="dateFrom" value="${formatDate(format: 'dd/MM/yyyy', date: dateFrom)}"/>
                        <g:textField class="text-input" style="width: 150px;" name="dateTill" value="${formatDate(format: 'dd/MM/yyyy', date: dateTill)}"/>
                        <input type="submit" value="<g:message code='default.application.filter.show'/>" class="myButton" style="float: right;"/>
                    </g:form>
                </td>
            </tr>
            <g:if test="${transactionsList.size() > 0}">                
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
            </g:if>
            <g:else>
                <tr>
                    <td class="no-border" colspan="6">
                        <h1 class="sub-title" style="color: #d13f31;"><g:message code="default.application.notransactionsfound"/></h1>
                    </td>
                </tr>
            </g:else>
        </table>
        <div class="pagination" style="display: inline-block; width: 100%; float: right;">
            <g:paginate total="${transactionCount ?: 0}" params="['id': businessInstance?.id, 'filterOption': operationFilter, 'dateFrom': dateFrom, 'dateTill': dateTill]"/>
        </div>  
        <div class="summary-label">
            <g:message code="default.application.totalrecords"/>&nbsp;${transactionCount}
        </div>        
    </body>
</html>
