<%@ page contentType="text/html;charset=UTF-8" %>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="layout" content="reportpage"/>
        <title>SiFAR</title>
    </head>
    <body>
        <div class="datetime">Generated on: <g:formatDate format="dd/MM/yyyy HH:mm" date="${new Date()}"/></div>
        <div style="display: block; vertical-align: middle;">
            <img style="float: left; width: 30%; height: 9%; margin-right: 15px;" src="${resource(dir: 'images', file: 'sifar_logo_3.png')}"/>
            <h1 class="report-header" style="margin-top: 30px;">BinaPavo Data Intelligence Sdn Bhd</h1>
        </div>
        <div style="width: 99%; margin-top: 10px; padding: 0; display: block; border-bottom: 1px solid #a0a0a0; overflow: hidden;">
            <h1 class="report-subheader" style="display: block; width: auto; letter-spacing: 3px;">INVOICE</h1>
        </div>
        <div class="left-panel" style="border: 1px solid #a0a0a0; padding: 10px 0;">
            <table class="report-content" style="vertical-align: top;">
                <tr>
                    <td class="no-border">Customer</td>
                    <td class="no-border" style="width: 0.5%">:</td>
                    <td class="no-border">${objectInstance?.company?.name}</td>
                </tr>
                <tr style="vertical-align: top;">
                    <td class="no-border" style="vertical-align: top;">Address</td>
                    <td class="no-border" style="width: 0.5%; vertical-align: top;">:</td>
                    <td class="no-border" style="vertical-align: top;">${objectInstance?.company?.address}</td>
                </tr>                
            </table>
        </div>
        <div class="right-panel" style="vertical-align: top; border: 0px solid #999999; margin-bottom: 10px;">
            <table class="report-content">
                <tr>
                    <td class="no-border">Date</td>
                    <td class="no-border" style="width: 0.5%">:</td>
                    <td class="no-border"><g:formatDate format="dd/MM/yyyy" date="${objectInstance?.dueDate}"/></td>
                </tr>  
                <tr>
                    <td class="no-border">Invoice No</td>
                    <td class="no-border" style="width: 0.5%">:</td>
                    <td class="no-border">${objectInstance?.invoiceNumber}</td>
                </tr> 
                <tr style="height: 10px;"></tr>
                <tr>
                    <td class="no-border">Billing Type</td>
                    <td class="no-border" style="width: 0.5%">:</td>
                    <td class="no-border">${objectInstance?.billingType}</td>
                </tr> 
                <tr>
                    <td class="no-border">Registration Date</td>
                    <td class="no-border" style="width: 0.5%">:</td>
                    <td class="no-border"><g:formatDate format="dd/MM/yyyy" date="${objectInstance?.company?.registrationDate}"/></td>
                </tr> 
                <tr>
                    <td class="no-border">Billing Starts From</td>
                    <td class="no-border" style="width: 0.5%">:</td>
                    <td class="no-border"><g:formatDate format="dd/MM/yyyy" date="${objectInstance?.company?.startBillingDate}"/></td>
                </tr>                 
            </table>
        </div>
        <div style="display: block; height: 25px; overflow: hidden; width: 99%;"></div>
        <table class="report-content" style="margin-top: 25px;">
            <tr>
                <td class="no-border centered shaded header" style="padding: 5px 3px;">No</td>
                <td class="no-border centered shaded header" style="padding: 5px 3px;">Description</td>
                <td class="no-border righted shaded header"  style="padding: 5px 3px;"width="25%">Amount (RM)</td>
            </tr>
            <tr style="height: 20px;"></tr>
            <tr style="background: white;">
                <td class="no-border centered padded3_5">1</td>
                <td class="no-border padded3_5">
                    SiFAR Services for the Period from <g:formatDate format="dd/MM/yyyy" date="${objectInstance?.periodFrom}"/>
                    to <g:formatDate format="dd/MM/yyyy" date="${objectInstance?.periodTill}"/>
                </td>
                <td class="no-border righted padded3_5"><g:formatNumber format="#,##0.00" number="${objectInstance?.amount}"/></td>
            </tr>
            <tr style="background: white;">
                <td colspan="3" class="no-border" style="border-bottom: 1px solid #a0a0a0; height: 20px;"></td>
            </tr>
            <tr style="background: white;">
                <td colspan="2" class="no-border righted header padded3_5">TOTAL</td>
                <td class="no-border righted header"><g:formatNumber format="#,##0.00" number="${objectInstance?.amount}"/></td>
            </tr>
        </table>
        <div style="width: 100%; height: 30px;">&nbsp;</div>
        <table class="report-content">
            <tr style="background: #fff;">
                <td class="no-border">
                Please notify the Company within 7 days should there be any discrepancy, otherwise all 
                record transaction will be treated correct and no amendment could be made. All cheques 
                should be crossed and payable to <b>BinaPavo Data Intelligence Sdn Bhd</b>,
                A/C No. <b>5621 0666 6491</b>, Maybank. 
                <br/><br/>
                Termination of service may be implemented if payment is not received within 30 days upon Invoice date. 
                </td>
            </tr>
            <tr style="background: white; height: 40px;"></tr>
            <tr style="background: white;">
                <td class="no-border centered">
                    THIS IS A COMPUTER GENERATED DOCUMENT AND NO SIGNATURE IS REQUIRED
                </td>
            </tr>
        </table>
    </body>
</html>
