<%@ page contentType="text/html;charset=UTF-8" %>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="layout" content="adminpage"/>
        <title>SIFAR</title>
    </head>
    <body>
        <div style="width: 100%; background: #D0E8F4; display: inline-block; text-align: right; border-bottom: 0px solid #6285C7; border-top: 0px solid #6285C7; padding: 5px 0;">
            <g:link style="float: left;" action="index" params="['max': params.max, 'offset': params.offset]"><img class="image-link" style="margin-left: 5px;" src="${resource(dir: 'images', file: 'arrow_left.png')}" title="<g:message code='actions.back'/>"/></g:link>
            <g:link action="export" params="['max': params.max, 'offset': params.offset]"><img class="image-link" style="margin-right: 5px;" src="${resource(dir: 'images', file: 'sorting.png')}" title="<g:message code='actions.business.export'/>"/></g:link>
        </div> 

        <table class="righted-content">
            <caption>Backup History of current Database instance</caption>
            <tr>
                <th rowspan="2" style="vertical-align: middle; width: 3%;">No</th>
                <th rowspan="2" style="vertical-align: middle; width: 15%;">Date/Time</th>
                <th rowspan="2" style="vertical-align: middle; width: 14%;">Size (Bytes)</th>
                <th colspan="4" style="vertical-align: middle;">Exported</th>
                <%--th rowspan="2" style="vertical-align: middle;">Operator</th --%>
            </tr>
            <tr>
                <th>Users</th>
                <th>Companies</th>
                <th>Transactions</th>
                <th>Bills</th>
            </tr>
            <g:each in="${history}" var="record" status="index">
                <tr class="${(index % 2) == 0 ? 'even' : 'odd'}" title="Backup performed by: ${record?.operator}">
                    <td class="no-border centered">${index + 1}</td>
                    <td class="no-border centered"><g:formatDate format="dd/MM/yyyy HH:mm" date="${record.backupDate}"/></td>
                    <td class="no-border centered"><g:formatNumber format="#,##0" number="${record.size}"/></td>
                    <td class="no-border centered" style="width: 15%;"><g:formatNumber format="#,##0" number="${record.numberUsers}"/></td>
                    <td class="no-border centered" style="width: 15%;"><g:formatNumber format="#,##0" number="${record.numberCompanies}"/></td>
                    <td class="no-border centered" style="width: 15%;"><g:formatNumber format="#,##0" number="${record.numberTransactions}"/></td>
                    <td class="no-border centered" style="width: 15%;"><g:formatNumber format="#,##0" number="${record.numberBills}"/></td>
                    <%--td class="no-border">${record?.operator.take(20)}</td --%>
                </tr>
            </g:each>
        </table>
    </body>
</html>
