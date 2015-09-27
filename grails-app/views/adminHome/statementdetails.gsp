<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="com.sme.entities.*" %>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="layout" content="reportpage"/>
        <title>SIFAR</title>
    </head>
    <body>
        <% def sum = 0 %>
        <div class="datetime">${new Date().format("dd/MM/yyyy HH:mm")}</div>
        <h1 class="report-header" style="width: 100%;">
            ${company?.name}: Cash Flow for
            ${summary?.year}/<g:formatNumber format="00" number="${summary?.month}"/>
        </h1>
        
        <table class="report-content">
            <g:each in="${groups}" var="group" status="i">
                <tr style="background: #fff;">
                    <td colspan="3" class="no-border">
                        <h1 class="report-subheader">${group?.key}</h1>
                    </td>
                </tr>
                <g:if test="${group?.value.size() == 0}">
                    <tr style="background: #fff;">
                        <td colspan="4" class="centered">
                            No Entries are found
                        </td>
                    </tr>
                </g:if>
                <% sum = 0 %>
                <g:each in="${group.value}" var="trans" status="k">
                    <tr style="background: #fff;">
                        <td class="centered">${k+1}</td>
                        <td>
                            <g:formatDate format="dd/MM/yyyy" date="${trans.transactionDate}"/>&nbsp;
                            ${trans.transactionRemarks}
                        </td>
                        <td class="righted">
                            <g:formatNumber format="#,##0.00" number="${trans.transactionAmount}"/>
                            <% sum += trans.transactionAmount %>
                        </td>
                    </tr>
                </g:each>
                <tr style="background: #fff;">
                    <td colspan="2" class="no-border righted">Total</td>
                    <td class="righted">
                        ${formatNumber(format: '#,##0.00', number: sum)}
                    </td>
                </tr>
            </g:each>
            <tr style="background: #fff; height: 10px;">&nbsp;</tr>
            <tr style="background: #fff;">
                <td colspan="2" class="header">Total Cash Outflow</td>
                <td class="righted header"><g:formatNumber format="#,##0.00"  number="${summary?.outflow}" /></td>
            </tr>
            <tr style="background: #fff;">
                <td colspan="2" class="header">Surplus/Deficit</td>
                <td class="righted header"><g:formatNumber format="#,##0.00"  number="${summary?.nettAmount}" /></td>
            </tr> 
            <tr style="background: #fff; height: 10px;">&nbsp;</tr>
            <tr style="background: #fff;">
                <td colspan="2" class="header">Balance B/F</td>
                <td class="righted header"><g:formatNumber format="#,##0.00"  number="${summary?.openingBalance}" /></td>
            </tr>    
            <tr style="background: #fff;">
                <td colspan="2" class="header">Balance C/F</td>
                <td class="righted header"><g:formatNumber format="#,##0.00"  number="${summary?.nettAmount}" /></td>
            </tr>
        </table>
    </body>
</html>
