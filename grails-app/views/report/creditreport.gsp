<%@ page contentType="text/html;charset=UTF-8" %>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="layout" content="reportpage"/>
        <title>SIFAR</title>
    </head>
    <body>
        <%
            def totalIn = 0
            def totalOut = 0
            def groupSuppliers = 0
            
            if(params?.groupSuppliers != null) {
                groupSuppliers = new Integer(params?.groupSuppliers)
            }
        %>
        
        <h1 class="report-header">${companyName}</h1><br/>
        
        <div class="datetime">
            ${new Date().format("dd/MM/yyyy HH:mm")}</div>
        <br/><br/>
        <hr/><br/>
        
        <g:if test="${groupSuppliers == 0}">
            
            <!--NO GROOUPING BY SUPPLIERS START-->
            
            <h1 class="report-header">Purchase Transactions</h1>

            <table class="report-content" style="border-collapse: separate;">
                <tr>
                    <td class="centered header shaded">No</td>
                    <td class="centered header shaded">Transaction Date</td>
                    <td class="centered header shaded">Description</td>
                    <td class="centered header shaded">Remarks</td>
                    <td class="centered header shaded">RM</td>
                    <td class="centered header shaded">RM</td>
                </tr>

                <g:each in="${transactions1}" status="index" var="transInstance">

                    <g:if test="${new Integer(params.offset) > 0}">
                    <g:set var="offset" value="${new Integer(params.offset)}"/>
                    </g:if>
                    <g:else><g:set var="offset" value="${0}"/></g:else> 

                    <tr style="background: #fff;">
                        <td class="centered">${offset + index + 1}</td>
                        <td class="centered">${formatDate(format:'dd/MM/yyyy', date:transInstance?.transactionDate)}</td>
                        <td>${transInstance?.operationType.toString()}</td>
                        <td>${transInstance?.transactionRemarks}</td>

                        <g:if test="${transInstance?.operationType.code.toString() == '2000'}">
                            <td>&nbsp;</td>
                            <td class="righted"><g:formatNumber number="${transInstance?.transactionAmount}" format="#,##0.00" /></td>
                            <% totalOut = totalOut + transInstance?.transactionAmount %>
                        </g:if>

                        <g:if test="${transInstance?.operationType.code.toString() == '515'}">
                            <td class="righted"><g:formatNumber number="${transInstance?.transactionAmount}" format="#,##0.00" /></td>
                            <td>&nbsp;</td>
                            <% totalIn = totalIn + transInstance?.transactionAmount %>
                        </g:if>               
                    </tr>
                </g:each>

                <tr style="background: #fff; font-weight: bold;">
                    <td class="no-border righted" colspan="5">Balance:</td>
                    <td class="righted"><g:formatNumber number="${totalOut - totalIn}" format="#,##0.00" /></td>
                </tr>

            </table>
            
            <h1 class="report-header">Sales Transactions</h1>
            
            <table class="report-content" style="border-collapse: separate;">
                <tr>
                    <td class="centered header shaded">No</td>
                    <td class="centered header shaded">Transaction Date</td>
                    <td class="centered header shaded">Description</td>
                    <td class="centered header shaded">Remarks</td>
                    <td class="centered header shaded">RM</td>
                    <td class="centered header shaded">RM</td>
                </tr>

                <% 
                    totalIn = 0
                    totalOut = 0
                %>

                <g:each in="${transactions2}" status="index" var="transInstance">

                    <g:if test="${new Integer(params.offset) > 0}">
                    <g:set var="offset" value="${new Integer(params.offset)}"/>
                    </g:if>
                    <g:else><g:set var="offset" value="${0}"/></g:else> 

                    <tr style="background: #fff;">
                        <td class="centered">${offset + index + 1}</td>
                        <td class="centered">${formatDate(format:'dd/MM/yyyy', date:transInstance?.transactionDate)}</td>
                        <td>${transInstance?.operationType.toString()}</td>
                        <td>${transInstance?.transactionRemarks}</td>

                        <g:if test="${transInstance?.operationType.code.toString() == '2005'}">
                            <td>&nbsp;</td>
                            <td class="righted"><g:formatNumber number="${transInstance?.transactionAmount}" format="#,##0.00" /></td>
                            <% totalOut = totalOut + transInstance?.transactionAmount %>
                        </g:if>

                        <g:if test="${transInstance?.operationType.code.toString() == '510'}">
                            <td class="righted"><g:formatNumber number="${transInstance?.transactionAmount}" format="#,##0.00" /></td>
                            <td>&nbsp;</td>
                            <% totalIn = totalIn + transInstance?.transactionAmount %>
                        </g:if>               
                    </tr>
                </g:each>

                <tr style="background: #fff; font-weight: bold;">
                    <td class="no-border righted" colspan="5">Balance:</td>
                    <td class="righted"><g:formatNumber number="${totalOut - totalIn}" format="#,##0.00" /></td>
                </tr>

            </table>

            <!--NO GROOUPING BY SUPPLIERS START-->
                        
        </g:if>
        
        <g:else>
            Grouped report here
        </g:else>
                
    </body>
</html>
