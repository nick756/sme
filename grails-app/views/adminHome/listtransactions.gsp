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
            <g:link action="statements" params="['max': params.max, 'offset': params.offset, 'id': businessInstance?.id]"><img class="image-link" style="margin-right: 5px;" src="${resource(dir: 'images', file: 'statement.png')}" title="<g:message code='actions.statement'/>"/></g:link>
        </div>      

        <h1 class="sub-title">${businessInstance?.name}</h1>
        <div class="edit-form-box">
            <%--
            <label class="edit-form"><g:message code="business.name.label"/></label>
            <g:textField class="text-input" name="name" value="${businessInstance?.name}" readonly="true"/>
            <br/>
            --%>
            <label class="edit-form"><g:message code="business.profile.label"/></label>
            <g:textField class="text-input" name="name" value="${businessInstance?.profile?.name}" readonly="true"/>

        </div>  
        
        <table class="righted-content">
            <caption><g:message code="default.application.transactionslist"/></caption>
            <tr>
                <td colspan="5" class="no-border" style="padding: 0;">
                    
                    <%-- Inner Table for overriding separated borders --%>
                    <table border="0" class="righted-content" style="border-collapse: collapse; border: none; padding: 0; margin: 0;">
                        
                        <%-- Filtering (search) Form --%>
                        <g:form action="listtransactions" params="['offset': 0, 'max':params?.max]">
                            <g:hiddenField name="instId" value="${businessInstance?.id}"/>  
                            <tr>
                                <td colspan="3" class="no-border" style="background: #D0E8F4;">
                                    <g:message code="default.application.filter.type"/>
                                </td>
                                <td colspan="3" class="no-border" style="background: #D0E8F4;">
                                    <select name="filterOption" id="filterOption" style="width: 300px;">
                                        <option value="1" ${operationFilter == 1 ? 'selected':''}><g:message code="default.application.filter.all"/></option>
                                        <option value="2" ${operationFilter == 2 ? 'selected':''}><g:message code="default.application.filter.lastmonth"/></option>
                                        <option value="3" ${operationFilter == 3 ? 'selected':''}><g:message code="default.application.filter.dates"/></option>
                                    </select>  
                                    
                                </td>
                            </tr>
                            <tr>
                                <td colspan="3" class="no-border" style="background: #D0E8F4;">
                                    <g:message code="default.application.filter.from"/>
                                </td>
                                <td colspan="3" class="no-border" style="background: #D0E8F4;">
                                    <g:textField class="text-input" style="width: 150px;" name="dateFrom" value="${dateFrom}"/>
                                </td>
                            </tr>
                            <tr>
                                <td colspan="3" class="no-border" style="background: #D0E8F4;">
                                    <g:message code="default.application.filter.till"/>
                                </td>
                                <td colspan="3" class="no-border" style="background: #D0E8F4;">
                                    <g:textField class="text-input" style="width: 150px;" name="dateTill" value="${dateTill}"/>
                                    <input type="submit" value="<g:message code='default.application.filter.show'/>" class="myButton" style="float: right;"/>
                                </td>
                            </tr>                
                        </g:form>
                        
                    </table>
                    
                </td>
            </tr>
            
            <%
                def remarks
                def title
            %>

            <g:if test="${transactionsList.size() > 0}">                
                <tr>
                    <th>No</th>
                    <th><g:message code="businesstransaction.date.label"/></th>
                    <th><g:message code="businesstransaction.type.label"/></th>
                    <th><g:message code="businesstransaction.remarks.label"/></th>
                    <th><g:message code="businesstransaction.amount.label"/></th>
                    
                </tr>

                <g:each in="${transactionsList}" status="index" var="transInstance">

                    <g:if test="${new Integer(params.offset) > 0}">
                    <g:set var="offset" value="${new Integer(params.offset)}"/>
                    </g:if>
                    <g:else><g:set var="offset" value="${0}"/></g:else> 

                    <tr class="${(index % 2) == 0 ? 'even' : 'odd'}">
                        <%
                            title = "${message(code: 'businesstransaction.date.label')}:\t\t${formatDate(format: 'dd/MM/yyyy', date: transInstance?.transactionDate)}\n"
                            title += "Type:\t\t${transInstance?.operationType}\nAmount:\t\tRM ${formatNumber(format: '#,##0.00', number: transInstance?.transactionAmount)}\n"
                            title += "Description:\t${transInstance?.transactionRemarks}"
                            title += "\nOperator:\t${transInstance?.operator}"
                        %>
                        <td class="no-border centered">${offset + index + 1}</td>
                        <td class="no-border centered" title="${title}">${formatDate(format:'dd/MM/yyyy', date:transInstance?.transactionDate)}</td>
                        <td class="no-border" title="${title}">
                            <%
                            remarks = transInstance?.operationType.toString()
                            if(remarks.length() > 40) {
                                remarks = remarks.take(40) + '...'
                            }
                            %>
                            ${remarks}
                        </td>
                        
                        <td class="no-border" title="${title}">
                            <%
                            remarks = transInstance?.transactionRemarks
                            if(transInstance?.transactionRemarks.length() > 40) {
                                remarks = remarks.getAt(0..39) + '...'
                            }
                            %>
                            ${remarks}
                        </td>
                        <td class="no-border righted" title="${title}"><g:formatNumber number="${transInstance?.transactionAmount}" format="#,##0.00" /></td>
                    </tr>

                </g:each>
            </g:if>
            <g:else>
                <tr>
                    <td class="no-border" colspan="5">
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
