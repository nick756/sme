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
                <li class="unselected-item"><g:link action="index">Cash Flow Statement</g:link></li>
                <li class="selected-item">Income Statement</li>
            </ul>
        </div>
        <div class="action-header">
            <g:link style="float: right;" action="deleteincome" params="['max': params.max, 'offset': params.offset]" onclick="return confirm('Are you sure?');"><img class="image-link" style="margin-right: 5px;" src="${resource(dir: 'images', file: 'data_delete.png')}" title="<g:message code='actions.delete'/>"/></g:link>
            <g:link style="float: right;" action="createincome" params="['max': params.max, 'offset': params.offset]"><img class="image-link" style="margin-right: 5px;" src="${resource(dir: 'images', file: 'add_record.png')}" title="<g:message code='actions.add'/>"/></g:link>
        </div>
        <table class="righted-content">
            <caption><g:message code="pnlstatement.summary.label"/></caption>
            <tr>
                <th><g:message code="pnlstatement.year.label"/></th>
                <th><g:message code="pnlstatement.dateFrom.label"/></th>
                <th><g:message code="pnlstatement.dateTill.label"/></th>
                <th><g:message code="pnlstatement.grossProfit.label"/></th>
                <th><g:message code="pnlstatement.netProfitBT.label"/></th>
                <th><g:message code="pnlstatement.netProfitAT.label"/></th>
                <th></th>
            </tr>
            <g:each status="index" in="${statements}" var="item">
                
                <g:if test="${new Integer(params.offset) > 0}">
                    <g:set var="offset" value="${new Integer(params.offset)}"/>
                </g:if>
                <g:else><g:set var="offset" value="${0}"/></g:else>

                <tr class="${(index % 2) == 0 ? 'even' : 'odd'}">
                    <td class="no-border centered">
                        <g:if test="${item?.month > 0}">
                            ${item?.year}
                        </g:if>
                        <g:else>
                            <div style="background: #0000ff; color: #fff; padding: 0px 4px; display: inline-block;">${item?.year}</div>
                        </g:else>
                    </td>
                    <td class="no-border centered"><g:formatDate date="${item?.dateFrom}" format="dd/MM/yyyy" /></td>
                    <td class="no-border centered"><g:formatDate date="${item?.dateTill}" format="dd/MM/yyyy" /></td>
                    <td class="no-border righted"><g:formatNumber number="${item?.grossProfit}" format="#,##0.00"/></td>
                    <td class="no-border righted">
                        <g:if test="${item?.netProfitBT > 0}">
                            <g:formatNumber number="${item?.netProfitBT}" format="#,##0.00"/>
                        </g:if>
                        <g:else>
                            <b><font style="color: #df0000">(<g:formatNumber number="${-item?.netProfitBT}" format="#,##0.00"/>)</font></b>
                        </g:else>
                    </td>
                    <td class="no-border righted">
                        <g:if test="${item?.netProfitAT > 0}">
                            <g:formatNumber number="${item?.netProfitAT}" format="#,##0.00"/>
                        </g:if>
                        <g:else>
                            <b><font style="color: #df0000">(<g:formatNumber number="${-item?.netProfitAT}" format="#,##0.00"/>)</font></b>
                        </g:else>
                    </td>
                    <td class="no-border centered">
                        <g:link  target="_blank" controller="report" action="pnlstatement" params="['id': item?.id]">
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
