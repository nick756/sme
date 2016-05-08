<%@ page contentType="text/html;charset=UTF-8" %>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="layout" content="reportpage"/>
        <title>SIFAR</title>
    </head>
    <body>
        <%
            def amountTotal = 0
            def counter = 0
            def firstPage = true
            def maxLines = 40
        %>
        <div class="datetime">Report generated on: <g:formatDate format="dd/MM/yyyy HH:mm" date="${new Date()}"/></div>
        <h1 class="report-header">Pending Invoices</h1>
        <table class="report-content" style="border-collapse: separate;">
            <tr>
                <td class="no-border righted" width="30%">Due Date From</td>
                <td>
                    <g:if test="${searchData['searchDueDateFrom']}"><g:formatDate format="dd/MM/yyyy" date="${searchData['searchDueDateFrom']}"/></g:if>
                    <g:else>N/A</g:else>
                </td>
                <td class="no-border" width="30%">&nbsp;</td>
            </tr>
            <tr>
                <td class="no-border righted">Due Date Till</td>
                <td>
                    <g:if test="${searchData['searchDueDateTill']}"><g:formatDate format="dd/MM/yyyy" date="${searchData['searchDueDateTill']}"/></g:if>
                    <g:else>N/A</g:else>
                </td>
                <td class="no-border">&nbsp;</td>
            </tr> 
            <tr>
                <td class="no-border righted">Company Name Pattern</td>
                <td>
                    <g:if test="${searchData['searchPendingCompany']}">${searchData['searchPendingCompany']}</g:if>
                    <g:else>N/A</g:else>
                </td>
                <td class="no-border">&nbsp;</td>
            </tr>
            <tr>
                <td class="no-border righted">SIFAR Registration From</td>
                <td>
                    <g:if test="${searchData['searchRegistrationFrom']}"><g:formatDate format="dd/MM/yyyy" date="${searchData['searchRegistrationFrom']}"/></g:if>
                    <g:else>N/A</g:else>
                </td>
                <td class="no-border">&nbsp;</td>
            </tr>            
        </table>
        <hr size="1" color="#fff" style="margin: 10px 0;"/>
        <table class="report-content" style="border-collapse: separate;">
            <tr style="background: white;">
                <td class="header shaded centered">No</td>
                <td class="header shaded centered">Company</td>
                <td class="header shaded centered">Invoice</td>
                <td class="header shaded centered">Due Date</td>
                <td class="header shaded centered">Grace Period</td>
                <td class="header shaded centered">Amount</td>
            </tr>
            <g:each in="${instancesList}" var="objectInstance" status="index">
                <%
                    amountTotal += objectInstance?.amount
                    counter++
                %>
                <g:if test="${counter == maxLines}">
                    </table>
                    <p style="page-break-before: always">  
                    <table class="report-content" style="border-collapse: separate;">
                        <tr style="background: white;">
                            <td class="header shaded centered">No</td>
                            <td class="header shaded centered">Company</td>
                            <td class="header shaded centered">Invoice</td>
                            <td class="header shaded centered">Due Date</td>
                            <td class="header shaded centered">Grace Period</td>
                            <td class="header shaded centered">Amount</td>
                        </tr>
                        <%
                            counter = 0
                            
                            if(firstPage) {
                                maxLines = 46
                                firstPage = false
                            }
                        %>
                </g:if>
                <tr style="background: white;">
                    <td class="centered">${index + 1}</td>
                    <td>${objectInstance?.company?.name}</td>
                    <td class="centered">${objectInstance?.invoiceNumber}</td>
                    <td class="centered"><g:formatDate format="dd/MM/yyyy" date="${objectInstance?.dueDate}"/></td>
                    <td class="centered"><g:formatDate format="dd/MM/yyyy" date="${objectInstance?.gracePeriodTill}"/></td>
                    <td class="righted"><g:formatNumber format="#,##0.00" number="${objectInstance?.amount}"/></td>
                </tr>
            </g:each>
            <tr style="background: white;">
                <td class="no-border righted header" colspan="5">TOTAL OUTSTANDING FOR THE PERIOD</td>
                <td class="righted header"><g:formatNumber format="#,##0.00" number="${amountTotal}"/></td>
            </tr>
        </table>
    </body>
</html>
