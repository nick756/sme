<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="com.sme.entities.GenericOperation" %>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="layout" content="smepage"/>
        <title>SIFAR</title>
    </head>
    <body>
        
        <%  
            def filterTypeList = GenericOperation.createCriteria().list() {
                or {
                    ge('code', 2000)
                    eq('code', 510)
                    eq('code', 515)
                }
                and{
                    order('outbound')
                    order('code')
                }
            } 
        %>
        <div class="action-header">
            <g:link style="float: right; margin-right: 5px;" action="create" params="['max': params.max, 'offset': params.offset]"><img class="image-link" style="margin-left: 5px;" src="${resource(dir: 'images', file: 'add_record.png')}" title="<g:message code='actions.transaction.add'/>"/></g:link>
        </div>
        <g:if test="${errMessage != null}">
            <div class="error-message">${errMessage}</div>
        </g:if>
        <g:if test="${successMsg != null}">
            <div class="success-message">${successMsg}<br/>${msgDetails}</div>
        </g:if>            
        <table class="righted-content">
            <caption><g:message code="default.application.credittransactions"/></caption>
            <tr style="background: #d0d0d0;">
                <td colspan="7" style="border: none; margin: 0; padding: 10px 0; background: #dadada;">
                    <g:form action="index" params="['offset': 0]">
                        <label class="edit-form"><g:message code="filter.dateFrom.label"/></label>
                        <g:datePicker class="text-input" name="filterDateFrom" precision="day" value="${filterDateFrom}" noSelection="['':'']" default="none" relativeYears="[-2..0]"/>
                        <br/>
                        <label class="edit-form"><g:message code="filter.dateTill.label"/></label>
                        <g:datePicker class="text-input" name="filterDateTill" precision="day" value="${filterDateTill}" noSelection="['':'']" default="none" relativeYears="[-2..0]"/>
                        <br/>                        
                        <label class="edit-form"><g:message code="filter.operationType.label"/></label>
                        <g:select class="text-input" name="filterOperation.id" from="${filterTypeList}" value="${filterOperation?.id}" optionKey="id" noSelection="['':'']" default="none"/>  
                        <input type="submit" value="<g:message code='default.application.filter.show'/>" class="myButton" style="float: right; margin-right: 10px;"/>
                    </g:form>                                        
                </td>
            </tr>            
                <tr>
                    <th>No</th>
                    <th><g:message code="businesstransaction.date.label"/></th>
                    <th><g:message code="businesstransaction.type.label"/></th>
                    <th><g:message code="businesstransaction.amount.label"/></th>
                    <th><g:message code="businesstransaction.remarks.label"/></th>
                    <!--th><g:message code="businesstransaction.operator.label"/></th-->
                    <th colspan="2"></th>
                </tr>
                <g:each in="${transactions}" status="index" var="transInstance">

                    <g:if test="${new Integer(params.offset) > 0}">
                    <g:set var="offset" value="${new Integer(params.offset)}"/>
                    </g:if>
                    <g:else><g:set var="offset" value="${0}"/></g:else> 

                    <tr class="${(index % 2) == 0 ? 'even' : 'odd'}">
                        <td class="no-border centered">${offset + index + 1}</td>
                        <td class="no-border centered">${formatDate(format:'dd/MM/yyyy', date:transInstance?.transactionDate)}</td>
                        <td class="no-border">${transInstance?.operationType.toString()}</td>
                        <td class="no-border righted"><g:formatNumber number="${transInstance?.transactionAmount}" format="#,##0.00" /></td>
                        <td class="no-border">${transInstance?.transactionRemarks}</td>
                        <!--td class="no-border">${transInstance?.operator}</td-->
                        <td class="no-border centered">
                            <g:link action="delete" params="['max': params.max, 'offset': params.offset, 'id': transInstance.id]" onclick="return confirm('Are you sure?')">
                                <img src="${resource(dir: 'images', file: 'icons/report_delete16x16.png')}" title="<g:message code='actions.transaction.delete'/>"/>
                            </g:link>
                        </td>
                        <td class="no-border centered">
                            <g:link action="edit" params="['max': params.max, 'offset': params.offset, 'id_edit': transInstance.id, 'filterDateFrom': params?.filterDateFrom, 'filterDateTill': params?.filterDateTill, 'filterOperation.id': params?.filterOperation?.id]">
                                <img src="${resource(dir: 'images', file: 'icons/report_next16x16.png')}" title="<g:message code='actions.edit'/>"/>
                            </g:link>
                        </td>                        
                    </tr>

                </g:each>
        </table>
        <div class="pagination" style="display: inline-block; width: 100%; float: right;">
            <g:paginate total="${counter ?: 0}" params="['filterDateFrom': params?.filterDateFrom, 'filterDateTill': params?.filterDateTill, 'filterOperation.id': params?.filterOperation?.id]"/>
        </div>  
        <div class="summary-label">
            <g:message code="default.application.totalrecords"/>&nbsp;${counter}
        </div>        
    </body>
</html>
