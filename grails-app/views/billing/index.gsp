<%@ page contentType="text/html;charset=UTF-8" %>

<html>
    <head>
        <meta name="layout" content="adminpage"/>
        <title>SIFAR</title>
    </head>
    <body>
        <div class="tabs-container">
            <ul class="tabs-sec">
                <li class="selected-item"><g:message code="tabs.bill.pending.label"/></li>
                <li class="unselected-item"><g:link action="paidbills"><g:message code="tabs.bill.paid.label"/></g:link></li>
            </ul>
        </div>        
        <div class="action-header">
            <g:link style="float: right;" target="_blank" controller="report" action="pendingbills" params="[]"><img class="image-link" style="margin-right: 5px;" src="${resource(dir: 'images', file: 'printer.png')}" title="<g:message code='actions.print'/>"/></g:link>
            <g:link style="float: right;" controller="billing" action="evaluate" params="[]"><img class="image-link" style="margin-right: 5px;" src="${resource(dir: 'images', file: 'data_refresh.png')}" title="<g:message code='actions.refresh.billing'/>"/></g:link>
        </div>
        <table class="righted-content">
            <caption><g:message code="default.application.pending.bills.label"/></caption>
            <g:if test="${message != null}">
                <tr>
                    <td colspan="7" class="no-border centered" style="padding: 0;">
                        <div class="success-message" style="margin: 0;">
                            <g:each in="${message}" var="line" status="k">
                                <g:if test="${line != ''}">${line}<br/></g:if>
                            </g:each>
                        </div>
                    </td>
                </tr>
            </g:if>
            <tr style="background: #d0d0d0;">
                <td colspan="9" style="border: none; margin: 0; padding: 10px 0; background: #dadada;">
                    <g:form action="index" params="['offset': 0, 'resetPendingOffset': true]">
                        <label class="edit-form"><g:message code="filter.searchDueDateFrom.label"/></label>
                        <g:datePicker class="text-input" name="searchDueDateFrom" precision="day" value="${searchData['searchDueDateFrom']}" noSelection="['':'']" default="none" relativeYears="[-2..0]"/>
                        <br/>
                        <label class="edit-form"><g:message code="filter.searchDueDateTill.label"/></label>
                        <g:datePicker class="text-input" name="searchDueDateTill" precision="day" value="${searchData['searchDueDateTill']}" noSelection="['':'']" default="none" relativeYears="[-2..0]"/>
                        <br/>
                        <label class="edit-form"><g:message code="filter.searchRegistrationFrom.label"/></label>
                        <g:datePicker class="text-input" name="searchRegistrationFrom" precision="day" value="${searchData['searchRegistrationFrom']}" noSelection="['':'']" default="none" relativeYears="[-2..0]"/>
                        
                        <br/>
                        <label class="edit-form"><g:message code="business.name.label"/></label>
                        <g:textField class="text-input" name="searchPendingCompany" value="${searchData['searchPendingCompany']}"/>
                        <input type="submit" value="<g:message code='default.application.filter.show'/>" class="myButton" style="float: right; margin-right: 10px;"/>
                        <g:link class="myButton" style="float: right; margin-right: 5px; padding: 6px 51px;" action="resetsearch" params="['target': 'index']"><g:message code="default.application.filter.reset"/></g:link>
                        
                    </g:form>                                        
                </td>
            </tr>
            <tr>
                <th>No</th>
                <th><g:message code="business.name.label"/></th>
                <th><g:message code="bill.invoiceNumber.label"/></th>
                <th><g:message code="bill.dueDate.label"/></th>
                <th><g:message code="bill.amount.label"/></th>
                <th><g:message code="bill.gracePeriodTill.label"/></th>
                <th></th>
            </tr>
            <g:if test="${instancesList?.size() == 0}">
                <tr><td colspan="7" class="no-border centered" style="background: #FFF;"><g:message code='messages.empty.list'/></td></tr>
            </g:if>
            <g:else>
            <g:each status="index" in="${instancesList}" var="objectInstance">

                <g:if test="${new Integer(params.offset) > 0}">
                <g:set var="offset" value="${new Integer(params.offset)}"/>
                </g:if>
                <g:else><g:set var="offset" value="${0}"/></g:else>

                <tr class="${(index % 2) == 0 ? 'even' : 'odd'}">
                    <td class="no-border centered">${offset + index + 1}</td>
                    <td class="no-border">${objectInstance?.company?.name}</td>
                    <td class="no-border centered">${objectInstance?.invoiceNumber}</td>
                    <td class="no-border centered"><g:formatDate format="dd/MM/yyyy" date="${objectInstance?.dueDate}"/></td>
                    <td class="no-border righted"><g:formatNumber format="#,##0.00" number="${objectInstance?.amount}"/></td>
                    <td class="no-border centered"><g:formatDate format="dd/MM/yyyy" date="${objectInstance?.gracePeriodTill}"/></td>
                    <td class="no-border centered">
                        <g:link target="_blank" controller="report" action="printinvoice" params="['id': objectInstance.id]">
                            <img style="border: none; outline: none; text-decoration: none;" src="${resource(dir: 'images', file: 'icons/printer01_16x16.png')}" title="<g:message code='actions.print'/>"/>
                        </g:link>
                    </td>
                </tr>
            </g:each>
            </g:else>
        </table>
        <div class="pagination" style="display: inline-block; width: 100%; float: right;">
            <g:paginate total="${counter ?: 0}" params="['resetPendingOffset': true]"/>
        </div>
        
        <div class="summary-label">
            <g:message code="default.application.totalrecords"/>&nbsp;${counter}
        </div>
    </body>
</html>
