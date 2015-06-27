<!--
  To change this license header, choose License Headers in Project Properties.
  To change this template file, choose Tools | Templates
  and open the template in the editor.
-->

<%@ page contentType="text/html;charset=UTF-8" %>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title><g:layoutTitle default="SME"/></title>
        <link rel="stylesheet" href="${resource(dir:'stylesheets',file:'application.css')}" />
        <link href='http://fonts.googleapis.com/css?family=Poiret+One|Muli|Economica|Quicksand|Jura|Orbitron' rel='stylesheet' type='text/css'>
        <g:layoutHead/>
    </head>
    <body>
        <div class="login-header">
            <g:if test="${session?.user}">
                <div class="user-details">Logged User: ${session.user?.name}</div>
            </g:if>
            <g:else>
                <g:link action="index" controller="login" params="[lang: 'ms']">
                    <img class="lang-selector" style="margin-left: 10px;" src="${resource(dir: 'images', file: 'my.png')}">
                </g:link>    
                <g:link action="index" controller="login" params="[lang: 'en_US']">
                    <img class="lang-selector" src="${resource(dir: 'images', file: 'gb.png')}">
                </g:link>
            </g:else>
        </div>
        <div class="login-title-box">
            <h1 class="login-title"><g:message code="default.application.title"/></h1>
        </div>
        
        <g:layoutBody/>
    </body>
</html>
