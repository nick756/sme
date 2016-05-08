<%@ page contentType="text/html;charset=UTF-8" %>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="layout" content="smepage"/>
        <title>SIFAR</title>
    </head>
    <body>
        <div class="action-header" style="height: 50px;">
            <%--g:link style="float: right;" controller="smebilling" action="create" params="['max': params.max, 'offset': params.offset]"><img class="image-link" style="margin-right: 5px;" src="${resource(dir: 'images', file: 'add_record.png')}" title="<g:message code='actions.transaction.add'/>"/></g:link--%>
        </div>
        <g:if test="${errMessage != null}">
            <div class="error-message">${errMessage}</div>
        </g:if>
        <g:if test="${successMsg != null}">
            <div class="success-message">${successMsg}<br/>${msgDetails}</div>
        </g:if>            
        <table class="righted-content" style="width: 100%; position: relative;">
            <caption><g:message code="default.application.bills.list"/></caption>
            <tr style="background: #aaa393;">
                <td colspan="8" style="border: none; margin: 0; padding: 10px 0; background: #dadada;">
                    <g:form action="index" params="['offset': 0]">
                        <label class="edit-form"><g:message code="filter.dateFrom.label"/></label>
                        <g:datePicker class="text-input" name="filterDateFrom" precision="day" value="${filterDateFrom}" noSelection="['':'']" default="none" relativeYears="[-2..0]"/>
                        <br/>
                        <label class="edit-form"><g:message code="filter.dateTill.label"/></label>
                        <g:datePicker class="text-input" name="filterDateTill" precision="day" value="${filterDateTill}" noSelection="['':'']" default="none" relativeYears="[-2..0]"/>
                        
                        <input type="submit" value="<g:message code='default.application.filter.show'/>" class="myButton" style="float: right; margin-right: 10px;"/>
                    </g:form>                                        
                </td>
            </tr>            
            <tr>
                <th>No</th>
                <th><g:message code="bill.invoiceNumber.label"/></th>
                <th><g:message code="bill.dueDate.label"/></th>
                <th><g:message code="bill.periodFrom.label"/></th>
                <th><g:message code="bill.periodTill.label"/></th>
                <th><g:message code="bill.amount.label"/></th>
                <th><g:message code="bill.paid.label"/></th-->
                <th></th>
            </tr>
            <g:if test="${instancesList?.size() > 0}">
                <g:each in="${instancesList}" status="index" var="objectInstance">

                    <g:if test="${new Integer(params.offset) > 0}">
                    <g:set var="offset" value="${new Integer(params.offset)}"/>
                    </g:if>
                    <g:else><g:set var="offset" value="${0}"/></g:else> 

                    <tr class="${(index % 2) == 0 ? 'even' : 'odd'}">
                        <td class="no-border centered">${offset + index + 1}</td>
                        <td class="no-border centered">${objectInstance?.invoiceNumber}</td>
                        <td class="no-border centered">${formatDate(format:'dd/MM/yyyy', date:objectInstance?.dueDate)}</td>
                        <td class="no-border centered">${formatDate(format:'dd/MM/yyyy', date:objectInstance?.periodFrom)}</td>
                        <td class="no-border centered">${formatDate(format:'dd/MM/yyyy', date:objectInstance?.periodTill)}</td>
                        <td class="no-border righted"><g:formatNumber number="${objectInstance?.amount}" format="#,##0.00" /></td>
                        <td class="no-border centered">
                            <g:if test="${objectInstance?.paid}">
                                <span style="color: #00A000;">&#10004;</span>
                            </g:if>
                            <g:else>
                                <span style="color: #DF0000;">&#10006;</span>
                            </g:else>
                        </td>
                        <td class="no-border centered">
                            <g:link controller="report" action="printinvoice" target="_blank" style="text-decoration: none;" params="['id': objectInstance.id]">
                                <img style="margin-right: 5px;" src="${resource(dir: 'images', file: 'icons/printer01_16x16.png')}" title="<g:message code='actions.print'/>"/>
                            </g:link>
                            <g:link action="edit" controller="smebilling" style="text-decoration: none;" params="['max': params.max, 'id': objectInstance.id, 'offset': params.offset, 'filterDateFrom': params?.filterDateFrom, 'filterDateTill': params?.filterDateTill, 'filterOperation.id': params?.filterOperation?.id]">
                                <img src="${resource(dir: 'images', file: 'icons/report_next16x16.png')}" title="<g:message code='actions.edit'/>"/>
                            </g:link>
                        </td>                        
                    </tr>
                </g:each>
            </g:if>
            <g:else>
                <tr>
                    <td class="no-border centered" colspan="8"><g:message code="messages.empty.list"/></td>
                </tr>
            </g:else>
        </table>
        <div class="pagination" style="display: inline-block; width: 100%; float: right;">
            <g:paginate total="${counter ?: 0}" params="['filterDateFrom': params?.filterDateFrom, 'filterDateTill': params?.filterDateTill]"/>
        </div>  
        <div class="summary-label">
            <g:message code="default.application.totalrecords"/>&nbsp;${instancesList ? instancesList?.size():0}
        </div> 
    </body>
</html>
