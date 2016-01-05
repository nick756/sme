<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="com.sme.entities.*" %>
<%@ page import="com.sme.util.*" %>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="layout" content="reportpage"/>
        <title>SIFAR</title>
    </head>
    <body>
        <div class="datetime">${new Date().format("dd/MM/yyyy HH:mm")}</div>
        <h1 class="report-header" style="margin-bottom: 2px;">Performance Report</h1>
        <h1 class="report-subheader" style="width: 100%; margin-top: 5px; margin-bottom: 5px;">
            For Period:&nbsp;from&nbsp;<g:formatDate format="dd/MM/yyyy" date="${dateFrom}"/>
            to&nbsp;<g:formatDate format="dd/MM/yyyy" date="${dateTill}"/>
        </h1>
        <table class="report-content" style="border-collapse: separate;">
            <tr style="background: #fff;">
                <td class="centered header shaded">No</td>
                <td class="centered header shaded" colspan="2">Company</td>
                <td class="centered header shaded">Sales</td>
                <td class="centered header shaded">Gross<br/>Profit</td>
                <td class="centered header shaded">Other<br/>Income</td>
                <td class="centered header shaded">Operational<br/>Expenses</td>
                <td class="centered header shaded">Net Profit<br/>before Tax</td>
                <td class="centered header shaded">Net Profit<br/>after Tax</td>
            </tr>
            <g:each in="${statements}" var="statement" status="index">
                <tr style="background: #fff;">
                    <td class="centered">${index + 1}</td>
                    <td class="centered no-border" width="1%">
                        <g:if test="${statement.amountProfitBT < 0}">
                            <img class="image-link" style="border: none;" src="${resource(dir: 'images', file: 'icons/negative.png')}" />
                        </g:if>
                    </td>                    
                    <td>${statement.company}</td>

                    <td class="righted"><g:formatNumber format="#,##0.00" number="${statement.amountSales}"/></td>
                    <td class="righted"><g:formatNumber format="#,##0.00" number="${statement.amountProfitGross}"/></td>
                    <td class="righted"><g:formatNumber format="#,##0.00" number="${statement.amountIncome}"/></td>
                    <td class="righted"><g:formatNumber format="#,##0.00" number="${statement.amountExpense}"/></td>
                    <td class="righted"><g:formatNumber format="#,##0.00" number="${statement.amountProfitBT}"/></td>
                    <td class="righted"><g:formatNumber format="#,##0.00" number="${statement.amountProfitAT}"/></td>
                </tr>
            </g:each>
        </table>
    </body>
</html>