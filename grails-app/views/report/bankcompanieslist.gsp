<%@ page contentType="text/html;charset=UTF-8" %>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="layout" content="reportpage"/>
        <title>SIFAR</title>
    </head>
    <body>
        <% def counter = 0 %>
        <div class="datetime">${new Date().format("dd/MM/yyyy HH:mm")}</div>
        <h1 class="report-header">Registered Companies</h1>
        <table class="report-content" style="border-collapse: separate;">
            <tr>
                <td class="centered header shaded">No</td>
                <td class="centered header shaded">Company</td>
                <td class="centered header shaded">ROB/ROC No</td>
                <td class="centered header shaded">Account No</td>
                <td class="centered header shaded">Registration</td>
                <td class="centered header shaded">City</td>
            </tr>
            <g:each in="${businesslist}" var="company" status="i">
                <% counter++ %>
                <g:if test="${counter == 48}">
                    </table>
                    <p style="page-break-before: always">
                    <table class="report-content" style="border-collapse: separate;">
                        <tr>
                            <td class="centered header shaded">No</td>
                            <td class="centered header shaded">Company</td>
                            <td class="centered header shaded">ROB/ROC No</td>
                            <td class="centered header shaded">Account No</td>
                            <td class="centered header shaded">Registration</td>
                            <td class="centered header shaded">City</td>
                        </tr>                    
                    <% counter = 0 %>
                </g:if>
                <tr style="background: #fff;">
                    <td class="centered">${i + 1}</td>
                    <td>${company.name}</td>
                    <td class="centered">${company.regNumber}</td>
                    <td class="centered">${company?.accountNo}</td>
                    <td class="centered"><g:formatDate format="dd/MM/yyyy" date="${company?.registrationDate}"/></td>
                    <td>${company?.city}</td>
                </tr>
            </g:each>
        </table>
    </body>
</html>
