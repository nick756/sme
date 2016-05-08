<%@ page contentType="text/html;charset=UTF-8" %>

<html>
    <head>
        <meta name="layout" content="adminpage"/>
        <title>SIFAR</title>
    </head>
    <body>
        <div class="tabs-container">
            <ul class="tabs-sec">
                <li class="unselected-item"><g:link action="index"><g:message code="tabs.bill.pending.label"/></g:link></li>
                <li class="selected-item"><g:message code="tabs.bill.paid.label"/></li>
            </ul>
        </div>        
        <div class="action-header">
            <g:link style="float: right;" target="_blank" controller="report" action="paidbills" params="[]"><img class="image-link" style="margin-right: 5px;" src="${resource(dir: 'images', file: 'printer.png')}" title="<g:message code='actions.print'/>"/></g:link>
        </div>
        <table class="righted-content">
            <caption><g:message code="default.application.paid.bills.label"/></caption>
            <tr style="background: #d0d0d0;">
                <td colspan="9" style="border: none; margin: 0; padding: 10px 0; background: #dadada;">
                    <g:form action="paidbills" params="['offset': 0]">
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
                        <g:textField class="text-input" name="searchPaidCompany" value="${searchData['searchPaidCompany']}"/>                        
                        <input type="submit" value="<g:message code='default.application.filter.show'/>" class="myButton" style="float: right; margin-right: 10px;"/>
                        <g:link class="myButton" style="float: right; margin-right: 10px; padding: 6px 51px;" action="resetsearch" params="['target': 'paidbills']"><g:message code="default.application.filter.reset"/></g:link>
                    </g:form>                                        
                </td>
            </tr>
            <tr>
                <th>No</th>
                <th colspan="2"><g:message code="business.name.label"/></th>
                <th><g:message code="bill.invoiceNumber.label"/></th>
                <th><g:message code="bill.dueDate.label"/></th>
                <th><g:message code="bill.amount.label"/></th>
                <th><g:message code="bill.paymentDate.label"/></th>
                <th></th>
            </tr>
            <g:if test="${instancesList.size() == 0}">
                <tr><td colspan="7" class="no-border centered"><g:message code='messages.empty.list'/></td></tr>
            </g:if>
            <g:else>
            <g:each status="index" in="${instancesList}" var="objectInstance">

                <g:if test="${new Integer(params.offset) > 0}">
                <g:set var="offset" value="${new Integer(params.offset)}"/>
                </g:if>
                <g:else><g:set var="offset" value="${0}"/></g:else>

                <tr class="${(index % 2) == 0 ? 'even' : 'odd'}">
                    <td class="no-border centered">${offset + index + 1}</td>
                    <td class="no-border centered">
                        <g:if test="${objectInstance?.confirmed}">
                            <span style="color: #00A000;">&#10004;</span>
                        </g:if>
                        <g:else>
                            <span style="color: #DF0000;">&#10006;</span>
                        </g:else>
                    </td>
                    <td class="no-border">${objectInstance?.company?.name}</td>
                    <td class="no-border centered">${objectInstance?.invoiceNumber}</td>
                    <td class="no-border centered"><g:formatDate format="dd/MM/yyyy" date="${objectInstance?.dueDate}"/></td>
                    <td class="no-border righted"><g:formatNumber format="#,##0.00" number="${objectInstance?.amount}"/></td>
                    <td class="no-border centered"><g:formatDate format="dd/MM/yyyy" date="${objectInstance?.paymentDate}"/></td>
                    <td class="no-border centered">
                        <g:link style="text-decoration: none;" target="_blank" controller="report" action="printinvoice" params="['id': objectInstance.id]">
                            <img style="border: none; outline: none; text-decoration: none; margin-right: 5px;" src="${resource(dir: 'images', file: 'icons/printer01_16x16.png')}" title="<g:message code='actions.print'/>"/>
                        </g:link>
                        <g:link action="confirm" style="text-decoration: none;" params="['id': objectInstance.id, 'filterDateFrom': params?.filterDateFrom, 'filterDateTill': params?.filterDateTill, 'filterOperation.id': params?.filterOperation?.id]">
                            <img src="${resource(dir: 'images', file: 'icons/report_next16x16.png')}" title="<g:message code='actions.edit'/>"/>
                        </g:link>                        
                    </td>
                </tr>
            </g:each>
            </g:else>
        </table>
        <div class="pagination" style="display: inline-block; width: 100%; float: right;">
            <g:paginate total="${counter ?: 0}" params="['filterName': params?.filterName, 'filterAccount': params?.filterAccount, 'filterCity': params.filterCity]"/>
        </div>
        
        <div class="summary-label">
            <g:message code="default.application.totalrecords"/>&nbsp;${counter}
        </div>
    </body>
</html>
