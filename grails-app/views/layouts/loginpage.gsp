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
            <center>
            <div style="width: 70%; display: table; margin-top: 15px;">
                    <div style="width: 40%; display: table-cell">
                        <img style="width: 257px; height: 168px; display: inline-block;" src="${resource(dir: 'images', file: 'sifar_logo.png')}">
                    </div>
                    <div style="text-align: center; vertical-align: middle; display: table-cell;">
                        <h1 class="login-title" style="display: inline-block; margin-top: -30px;"><g:message code="default.application.title"/></h1>
                    </div>
            </div>
            </center>
        </div>
        
        <g:layoutBody/>
        
        <center>
        <div style="font-family: 'Muli', sans-serif; font-size: 12pt; margin-top: 15px;">
        HELP DESK : info@mysifar.com / +6010-226-2887
        <br/><br/>
        <b>KCOMMERCE (M) SDN BHD (867123-P)</b><br/>
        32-1A, Kompleks Otomobil Shah Alam, Jalan Pahat J 15/J, Seksyen 15.<br/>40000 Shah Alam Selangor, MALAYSIA
        </div>
        </center>
    </body>
</html>
