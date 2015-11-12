<%@ page contentType="text/html;charset=UTF-8" %>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="layout" content="adminpage"/>
        <title>SIFAR</title>
    </head>
    <body>
        <h1 class="sub-title">Export Procedure</h1>
        <div class="edit-form-box">
            <label class="edit-form" style="width: 350px;">Exported Operation Types</label>
            <g:textField class="text-input" name="name" value="${operCount}" readonly="true"/>
            <br/>    
            <label class="edit-form" style="width: 350px;">Exported Profile Records</label>
            <g:textField class="text-input" name="name" value="${profCount}" readonly="true"/>
            <br/>
            <label class="edit-form" style="width: 350px;">Exported Agency/Bank Records</label>
            <g:textField class="text-input" name="name" value="${agenCount}" readonly="true"/>
            <br/>            
            <label class="edit-form" style="width: 350px;">Exported Companies</label>
            <g:textField class="text-input" name="name" value="${busiCount}" readonly="true"/>
            <br/>
            <label class="edit-form" style="width: 350px;">Exported User Records</label>
            <g:textField class="text-input" name="name" value="${userCount}" readonly="true"/>
            <br/> 
            <label class="edit-form" style="width: 350px;">Exported Transaction Records</label>
            <g:textField class="text-input" name="name" value="${tranCount}" readonly="true"/>
            <br/>
        </div>
        <table class="righted-content">
            <caption>Backup History of current Database instance</caption>
            <th>No</th>
            <th>Date/Time</th>
            <th>Backup Size</th>
            <g:each in="${history}" var="record" status="index">
                <tr class="${(index % 2) == 0 ? 'even' : 'odd'}">
                    <td class="no-border centered">${index + 1}</td>
                    <td class="no-border centered"><g:formatDate format="dd/MM/yyyy HH:mm" date="${record.backupDate}"/></td>
                    <td class="no-border"><g:formatNumber format="#,##0" number="${record.size}"/> bytes</td>
                </tr>
            </g:each>
        </table>
    </body>
</html>
