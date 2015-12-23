<!--
  To change this license header, choose License Headers in Project Properties.
  To change this template file, choose Tools | Templates
  and open the template in the editor.
-->

<%@ page contentType="text/html;charset=UTF-8" %>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="layout" content="smepage"/>
        <title>SIFAR</title>
    </head>
    <body>
        <div class="tabs-container">
            <ul class="tabs-sec">
                <li class="selected-item">Cash Flow Statement</li>
                <li class="unselected-item"><g:link action="income"><g:message code="pnlstatement.page.label"/></g:link></li>
            </ul>
        </div>
        <div class="action-header">
            <g:link style="float: right; margin-right: 5px;" action="delete" params="['max': params.max, 'offset': params.offset]" onclick="return confirm('Are you sure?');"><img class="image-link" style="margin-left: 5px;" src="${resource(dir: 'images', file: 'data_delete.png')}" title="<g:message code='actions.delete'/>"/></g:link>
            <g:link style="float: right; margin-right: 5px;" action="create" params="['max': params.max, 'offset': params.offset]"><img class="image-link" style="margin-left: 5px;" src="${resource(dir: 'images', file: 'add_record.png')}" title="<g:message code='actions.add'/>"/></g:link>
        </div>
        <table class="righted-content">
            <caption><g:message code="cfstatement.summary.label"/></caption>
            <tr>
                <th><g:message code="cfstatement.year.label"/></th>
                <g:sortableColumn property="month" title="${message(code: 'cfstatement.month.label')}" />
                <th><g:message code="cfstatement.openingbalance.label"/></th>
                <th><g:message code="cfstatement.inboundamount.label"/></th>
                <th><g:message code="cfstatement.outboundamount.label"/></th>
                <th><g:message code="cfstatement.resultamount.label"/></th>
                <th><g:message code="cfstatement.cumulativeamount.label"/></th>
                <th></th>
            </tr> 
            
            <g:each status="index" in="${statements}" var="item">
                
                <g:if test="${new Integer(params.offset) > 0}">
                    <g:set var="offset" value="${new Integer(params.offset)}"/>
                </g:if>
                <g:else><g:set var="offset" value="${0}"/></g:else>

                <tr class="${(index % 2) == 0 ? 'even' : 'odd'}">
                    <td class="no-border centered">${item?.year}</td>
                    <td class="no-border centered">${item?.month}</td>
                    <td class="no-border righted"><g:formatNumber number="${item?.openingBalance}" format="#,##0.00" /></td>
                    <td class="no-border righted"><g:formatNumber number="${item?.inflow}" format="#,##0.00" /></td>
                    <td class="no-border righted"><g:formatNumber number="${item?.outflow}" format="#,##0.00" /></td>
                    
                    <g:if test="${item?.nettAmount >= 0}">
                        <td class="no-border righted">
                    </g:if>
                    <g:else>
                        <td class="no-border righted" style="color: #fa0000;">
                    </g:else>
                    
                    <g:formatNumber number="${item?.nettAmount}" format="#,##0.00" /></td>
                    <td class="righted"><g:formatNumber number="${item?.cumulativeAmount}" format="#,##0.00" /></td>
                    <td class="no-border centered">
                        <g:link  target="_blank" controller="adminHome" action="statementdetails" params="['id': item?.id, 'businessId': businessInstance?.id]">
                            <img src="${resource(dir: 'images', file: 'icons/printer01_16x16.png')}" title="<g:message code='actions.print'/>"/>
                        </g:link>
                    </td>
                </tr>
                
            </g:each>
        </table>
        <div class="pagination" style="display: inline-block; width: 99%; float: right;">
            <g:paginate total="${totalCount ?: 0}"/>
        </div>
        <div class="summary-label">
            <g:message code="default.application.totalrecords"/>&nbsp;${totalCount}
        </div>  
    </body>
</html>
