<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="com.sme.entities.*" %>
<%@ page import="com.sme.util.*" %>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="layout" content="reportpage"/>
        <title>SIFAR</title>
        
<script type="text/javascript">
    var points = ${raw(points)}
window.onload = function () {
        CanvasJS.addColorSet("pieColors",
            [
                "#A0A0A0",  //  No Data Entr
                "#FF4040",  //  With Losses
                "#EFEF00",  //  Poor Performing
                "#40FF40"   //  Profitable
            ]
        );
        
	var chart = new CanvasJS.Chart("chartContainer",
	{
                colorSet: "pieColors",
		title:{
			text: ""
		},
                animationEnabled: true,
		legend:{
			verticalAlign: "center",
			horizontalAlign: "left",
			fontSize: 20,
			fontFamily: "Tahoma"        
		},
		data: [
		{        
			type: "pie",       
			indexLabelFontFamily: "Tahoma",       
			indexLabelFontSize: 16,
			indexLabel: "{label} {y}%",
			startAngle:-20,      
			showInLegend: true,
			toolTipContent:"{legendText} {y}%",
			dataPoints: points
		}
		]
	});
	chart.render();
}
</script>

    </head>
    <body>
        <% 
            def counter = 0 
            def firstPage = true
        %>
        <div class="datetime">${new Date().format("dd/MM/yyyy HH:mm")}</div>
        <h1 class="report-header" style="margin-bottom: 2px;">Performance Report</h1>
        <h1 class="report-subheader" style="width: 100%; margin-top: 5px; margin-bottom: 5px;">
            For Period:&nbsp;from&nbsp;<g:formatDate format="dd/MM/yyyy" date="${dateFrom}"/>
            <g:if test="${!uptoDate}">
                to&nbsp;<g:formatDate format="dd/MM/yyyy" date="${dateTill}"/>
            </g:if>
            <g:else>
                up to the Date
            </g:else>
        </h1>
        <br/>
        <table>
            <tr>
                <td style="padding: 0 0 0 0;">
                    <div id="chartContainer" style="width: 100%; height:  350px; display: block;" border="1"></div>
                </td>
            </tr>
        </table>
        <table class="report-content" style="border-collapse: separate;">
            <tr style="background: #fff;">
                <td class="centered header shaded">No</td>
                <td class="centered header shaded" colspan="2">Company</td>
                <td class="centered header shaded" width="10%">Sales</td>
                <td class="centered header shaded" width="10%">Gross<br/>Profit</td>
                <td class="centered header shaded" width="10%">Other<br/>Income</td>
                <td class="centered header shaded" width="10%">Operational<br/>Expenses</td>
                <td class="centered header shaded" width="10%">Net Profit<br/>before Tax</td>
                <td class="centered header shaded" width="10%">Net Profit<br/>after Tax</td>
            </tr>
            <g:each in="${statements}" var="statement" status="index">
                <% counter++ %>
                <%--
                    First Page - 19 lines
                    Consequent - 29 lines
                --%>
                
                <g:if test="${counter == 20 && firstPage}">
                </table>
                <p style="page-break-before: always">
                <table class="report-content" style="border-collapse: separate;">
                    <tr>
                        <td class="centered header shaded">No</td>
                        <td class="centered header shaded" colspan="2">Company</td>
                        <td class="centered header shaded" width="10%">Sales</td>
                        <td class="centered header shaded" width="10%">Gross<br/>Profit</td>
                        <td class="centered header shaded" width="10%">Other<br/>Income</td>
                        <td class="centered header shaded" width="10%">Operational<br/>Expenses</td>
                        <td class="centered header shaded" width="10%">Net Profit<br/>before Tax</td>
                        <td class="centered header shaded" width="10%">Net Profit<br/>after Tax</td>
                    </tr>                    
                    <% 
                        counter = 0 
                        firstPage = false
                    %>
                </g:if>  
                <g:if test="${counter == 29 && !firstPage}">
                </table>
                <p style="page-break-before: always">
                <table class="report-content" style="border-collapse: separate;">
                    <tr>
                        <td class="centered header shaded">No</td>
                        <td class="centered header shaded" colspan="2">Company</td>
                        <td class="centered header shaded" width="10%">Sales</td>
                        <td class="centered header shaded" width="10%">Gross<br/>Profit</td>
                        <td class="centered header shaded" width="10%">Other<br/>Income</td>
                        <td class="centered header shaded" width="10%">Operational<br/>Expenses</td>
                        <td class="centered header shaded" width="10%">Net Profit<br/>before Tax</td>
                        <td class="centered header shaded" width="10%">Net Profit<br/>after Tax</td>
                    </tr>                    
                    <% 
                        counter = 0 
                    %>
                </g:if> 
                <tr style="background: #fff;">
                    <td class="centered">${index + 1}</td>
                    <td class="centered no-border" width="1%">
                        <g:if test="${statement.amountProfitBT < 0}">
                            <img class="image-link" style="border: none;" src="${resource(dir: 'images', file: 'icons/circle_red.png')}" />
                        </g:if>
                        <g:else>
                            <g:if test="${statement.amountProfitBT >= 0 && statement.amountProfitBT < 1000}">
                            <img class="image-link" style="border: none;" src="${resource(dir: 'images', file: 'icons/circle_yellow.png')}" />
                            </g:if>
                            <g:else>
                                <img class="image-link" style="border: none;" src="${resource(dir: 'images', file: 'icons/circle_green.png')}" />
                            </g:else>
                    </g:else>
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