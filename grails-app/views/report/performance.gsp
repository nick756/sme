<%@ page contentType="text/html;charset=UTF-8" %>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="layout" content="reportpage"/>
        <title>SIFAR</title>

        <script type="text/javascript">
      var points = ${raw(series01)}
      var series02 = ${raw(series02)}
      var series03 = ${raw(series03)}
      var series04 = ${raw(series04)}
      var series05 = ${raw(series05)}

      var titleTxt = "Performance for ${businessInstance?.name}";
            titleTxt = titleTxt.replace('&amp;', '&');
            titleTxt = titleTxt.replace('&#39;', "'");

            window.onload = function () {
            var chart = new CanvasJS.Chart("chart",
            {
            title:{
            text: "",
            fontWeight: "normal"
            },

            axisX: {
            title: "Period YY/MM",
            //gridColor: 'lightblue',
            //gridThickness: 1,
            //interval: 2
            },
            axisY: {
            title: "Amount (RM)"
            },

            data: [
            {
            type: "column",
            name: "Net Profit",
            markerType: "circle",
            showInLegend: true,
            color: "#50ff50",    
            lineThickness: 1,
            markerSize: 10,
            toolTipContent: "{name}<br/>Period: {label}<br/>Amount: RM{y}",
            dataPoints: series05
            },         
            {
            type: "line",
            name: "Cost of Sales",
            markerType: "square",
            showInLegend: true,
            color: "#20B2AA",  
            lineThickness: 1,
            toolTipContent: "{name}<br/>Period: {label}<br/>Amount: RM{y}",
            dataPoints: series02
            },
            {
            type: "line",
            name: "Gross Profit",
            markerType: "circle",
            showInLegend: true,
            color: "#0000b0",
            lineThickness: 1,
            toolTipContent: "{name}<br/>Period: {label}<br/>Amount: RM{y}",
            dataPoints: series03
            },
            {
            type: "line",
            name: "Expenses",
            markerType: "square",
            showInLegend: true,
            color: "#b00000",
            lineThickness: 1,
            toolTipContent: "{name}<br/>Period: {label}<br/>Amount: RM{y}",
            dataPoints: series04
            },
            {
            type: "line",
            name: "Sales",
            markerType: "circle",
            showInLegend: true,
            color: "#F08080",  
            lineThickness: 1,
            toolTipContent: "{name}<br/>Period: {label}<br/>Amount: RM{y}",
            dataPoints: points
            }            

            ]
            });

            chart.render();
            }

        </script> 

    </head>
    <body>
        <div class="datetime">Generated on:&nbsp;<g:formatDate format="dd/MM/yyyy HH:mm" date="${new Date()}"/></div>
        <h1 class="report-header" style="margin-bottom: 0;">${businessInstance?.name}</h1>
        <h1 class="report-subheader" style="width: 100%; margin-bottom: 20px;">
            Performance evaluation
            <g:if test="${cumulative}">
                (Cumulative Report)
            </g:if>
        </h1>
        <br/>
        <table style="width: 100%; margin-top: 15px;">
            <tr style="background: #fff;">
                <td style="padding: 0;"><div id="chart" style="height: 350px; width: 99%;"></div></td>
            </tr>
        </table>
        <br/>
        <table class="report-content">
            <tr style="background: #fff;">
                <td rowspan="2" class="no-border"></td>
                <td class="centered" colspan="12">${year - 1}</td>
                <!--td class="centered" colspan="12">${year}</td-->
            </tr>
            <tr style="background: #fff;">
                <td width="7%" class="centered">01</td>
                <td width="7%" class="centered">02</td>
                <td width="7%" class="centered">03</td>
                <td width="7%" class="centered">04</td>
                <td width="7%" class="centered">05</td>
                <td width="7%" class="centered">06</td>
                <td width="7%" class="centered">07</td>
                <td width="7%" class="centered">08</td>
                <td width="7%" class="centered">09</td>
                <td width="7%" class="centered">10</td>
                <td width="7%" class="centered">11</td>
                <td width="7%" class="centered">12</td>
            </tr>
            <g:each in="${statements}" var="statement" status="i">
                <tr style="background: #fff;">
                    <td>${statement?.key}</td>
                    <g:each in="${statement?.value}" var="item" status="k">
                        <g:if test="${k < 12}">
                            <td class="righted"><g:formatNumber format="#,##0.00" number="${item}"/></td>
                        </g:if>
                    </g:each>
                </tr>
            </g:each>
        </table>
        <br/>
        <table class="report-content">
            <tr style="background: #fff;">
                <td rowspan="2" class="no-border"></td>
                <td class="centered" colspan="12">${year}</td>
            </tr>
            <tr style="background: #fff;">
                <td width="7%" class="centered">01</td>
                <td width="7%" class="centered">02</td>
                <td width="7%" class="centered">03</td>
                <td width="7%" class="centered">04</td>
                <td width="7%" class="centered">05</td>
                <td width="7%" class="centered">06</td>
                <td width="7%" class="centered">07</td>
                <td width="7%" class="centered">08</td>
                <td width="7%" class="centered">09</td>
                <td width="7%" class="centered">10</td>
                <td width="7%" class="centered">11</td>
                <td width="7%" class="centered">12</td>
            </tr>
            <g:each in="${statements}" var="statement" status="i">
                <tr style="background: #fff;">
                    <td>${statement?.key}</td>
                    <g:each in="${statement?.value}" var="item" status="k">
                        <g:if test="${k > 11 && k < 24}">
                        <td class="righted"><g:formatNumber format="#,##0.00" number="${item}"/></td>
                        </g:if>
                    </g:each>
                </tr>
            </g:each>
        </table>        
    </body>
</html>
