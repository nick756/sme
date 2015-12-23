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
        <h1 class="report-header" style="width: 100%; margin-bottom: 0;">${statement?.company?.name}</h1>
        <h1 class="report-subheader" style="margin-top: 5px;">Income Statement for Period:&nbsp;from&nbsp;<g:formatDate format="dd/MM/yyyy" date="${statement.dateFrom}"/>
            to&nbsp;<g:formatDate format="dd/MM/yyyy" date="${statement.dateTill}"/>
        </h1>

        <table class="report-content">
            <tr style="background: #fff;">
                <td colspan="2" class="no-border" style="border-bottom: 1px solid #000;"></td>
                <td class="no-border righted header" style="border-bottom: 1px solid #000;">RM</td>
                <td class="no-border righted header" style="border-bottom: 1px solid #000;">RM</td>
            </tr>
            <g:each in="${statement?.sections}" var="section" status="i">
                <tr style="background: #fff;">

                    <g:if test="${section?.group.code == 5 || section?.group.code == 15 || section?.group.code == 20}">
                        <td class="no-border">Less:</td>
                    </g:if>
                    <g:if test="${section?.group.code == 10}">
                        <td class="no-border">Add:</td>
                    </g:if>       
                    <g:if test="${section?.group.code == 1}">
                        <td class="no-border"></td>
                    </g:if>

                    <td colspan="3" class="no-border">
                <u>${section?.group?.name}</u>
            </td>
        </tr>
        <% sum = 0 %>
        <g:each in="${section?.lines}" var="line" status="k">
            <tr style="background: #fff;">
                <td class="no-border"></td>
                <td class="no-border">${line.type.name_EN}</td>

                    <!--td class="no-border"></td-->

                <g:if test="${k == section?.lines.size() - 1}">
                    <td class="no-border righted" style="border-bottom: 1px solid #000;">
                    </g:if>
                    <g:else>
                    <td class="no-border righted">
                    </g:else>
                    <g:formatNumber format="#,##0.00" number="${line.amount}"/>
                </td>
            <%-- Sections Total output --%>
                <g:if test="${k == 0}">
                    <g:if test="${section?.group?.code == 1 || section?.group?.code == 10}">
                        <td class="no-border righted" rowspan="${section?.lines.size()}" style="vertical-align: bottom;">
                            ${formatNumber(format: '#,##0.00', number: section.amountTotal)}
                        </g:if>
                        <g:else>
                        <td class="no-border righted" rowspan="${section?.lines.size()}" style="vertical-align: bottom; border-bottom: 1px solid #000;">
                            (${formatNumber(format: '#,##0.00', number: section.amountTotal)})
                        </g:else>
                    </td>                    
                </g:if>

            </tr>
        </g:each>

        <%-- Post Sections output: Profit summaries or empty line --%>
        <g:if test="${section?.group?.code == 1 || section?.group?.code == 10}">
            <tr style="background: #fff;">
                <td class="no-border" colspan="4">&nbsp;</td>
            </tr>
        </g:if>  

        <g:if test="${section?.group?.code == 5}">
            <tr style="background: #fff;">
                <td class="no-border"></td>
                <td class="no-border header" colspan="2"><h1 class="report-subheader" style="font-size: 10pt;">GROSS PROFIT</h1></td>
                <td class="no-border righted header"><g:formatNumber format="#,##0.00" number="${statement.grossProfit}"/></td>
            </tr>
        </g:if> 

        <g:if test="${section?.group?.code == 15}">
            <tr style="background: #fff;">
                <td class="no-border"></td>
                <td class="no-border header" colspan="2"><h1 class="report-subheader" style="font-size: 10pt;">NET PROFIT BEFORE TAX</h1></td>
                <td class="no-border righted header"><g:formatNumber format="#,##0.00" number="${statement.netProfitBT}"/></td>
            </tr>
        </g:if> 

        <g:if test="${section?.group?.code == 20}">
            <tr style="background: #fff;">
                <td class="no-border"></td>
                <td class="no-border header" colspan="2"><h1 class="report-subheader" style="font-size: 10pt;">NET PROFIT AFTER TAX</h1></td>
                <td class="no-border righted header" style="border-bottom: 4px double #000;"><g:formatNumber format="#,##0.00" number="${statement.netProfitAT}"/></td>
            </tr>
        </g:if> 

    </g:each>

</table>
</body>
</html>
