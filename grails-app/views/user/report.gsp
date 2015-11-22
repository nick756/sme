<!--
  To change this license header, choose License Headers in Project Properties.
  To change this template file, choose Tools | Templates
  and open the template in the editor.
-->

<%@ page contentType="text/html;charset=UTF-8" %>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="layout" content="reportpage">
        <title>SIFAR Users</title>
    </head>
    <body>
        <div class="datetime">Generated on:&nbsp;${new Date().format("dd/MM/yyyy HH:mm")}</div>
        <h1 class="report-header">List of Users</h1>
        <table class="report-content" style="margin-top: 5px; border-collapse: separate;">
            <tr>
                <td class="centered header shaded">No</td>
                <td class="centered header shaded">Name</td>
                <td class="centered header shaded">Login</td>
                <td class="centered header shaded">Role</td>
                <td class="centered header shaded">Email</td>
                <td class="centered header shaded">Account No</td>
                <td class="centered header shaded">Business</td>
            </tr>
            <g:each in="${usersList}" status="index" var="userInstance">
                <tr style="background: #fff;">
                    <td class="centered">${index + 1}</td>
                    <td>${userInstance?.name}</td>
                    <td>${userInstance?.login}</td>
                    <td class="centered">${userInstance?.role?.name}</td>
                    <td>${userInstance?.email}</td>
                    <td>${userInstance?.company?.accountNo}</td>
                    <td>${userInstance?.company?.name}</td>
                </tr>
            </g:each>
        </table>
    </body>
</html>
