<%@ page contentType="text/html;charset=UTF-8" %>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title><g:layoutTitle default="SiFAR"/></title>
        <link rel="shortcut icon" href="${assetPath(src: 'favicon_2.ico')}" type="image/x-icon">
        <link rel="stylesheet" href="${resource(dir:'stylesheets',file:'application.css')}" />
        <asset:javascript src="application.js"/>
        <asset:javascript src="jquery-1.11.1.js"/>
        <asset:javascript src="jquery-ui-1.10.3.custom.js"/>
        <link href='http://fonts.googleapis.com/css?family=Poiret+One|Muli|Economica|Quicksand|Jura|Orbitron' rel='stylesheet' type='text/css'>

        <g:layoutHead/>
        <!--g:javascript library="application"/-->
        <!--g:javascript library="jquery"/-->
        <!--g:javascript library="jquery-ui"/--> 
        <r:layoutResources />
    </head>
<body>
    <div class="top-header">
        <div class="top-header-cell-left">
            <img style="display: inline-block;" src="${resource(dir: 'images', file: 'user_login.png')}"/>
        </div>
        <div class="top-header-cell-middle">
            ${session.user?.name} (${session.user?.role?.name})
        </div>
        <div class="top-header-cell-right">
            <g:link  controller="login" action="logout">
                <img style="width: 24px; height: 24px; float: right;" src="${resource(dir: 'images', file: 'sign_out.png')}" title="<g:message code='actions.login.logout'/>">
            </g:link>
        </div>
    </div>

    <div style="display: table; width: 100%; vertical-align: middle;">
        <!--div style="display: table-cell;"><img style="display: inline-block; width: 200px; height: 200px;" src="${resource(dir: 'images', file: 'cube.jpg')}"></div-->
        <div style="width: 90%; height: auto; margin-top: 0px; padding: 3px 0 3px 0; display: table-cell; vertical-align: middle;">
            <span style="font-family: 'Quicksand', sans-serif; margin-left: 10px; font-size: 46px; color: #218C8D;">
                ${session?.company.name}
            </span>
        </div>
    </div>

    <div class="menu-pane">
        
        <div class="menu-pane-row">
            <g:link controller="smehome" action="index" class="tile" style="color: white;">
                <img src="${resource(dir: 'images', file: 'data_analysis.png')}">
                <br/>                
                <g:message code="sme.captions.transactions"/>
            </g:link> 
        </div>
        <div class="menu-pane-row">
            <g:link controller="credit" action="index" class="tile" style="color: white;">
                <img src="${resource(dir: 'images', file: 'data_analysis.png')}">
                <br/>                
                <g:message code="sme.captions.credittransactions"/>
            </g:link> 
        </div>        
        <div class="menu-pane-row">
            <g:link controller="smestatement" action="index" class="tile" style="color: white;">
                <img src="${resource(dir: 'images', file: 'statement_white.png')}">
                <br/>
                <g:message code="sme.captions.statements" default="Profile" />
            </g:link> 
        </div>         
        <div class="menu-pane-row">
            <g:link controller="smehome" action="profile" class="tile" style="color: white;">
                <img src="${resource(dir: 'images', file: 'date_settings_white.png')}">
                <br/>
                <g:message code="sme.captions.profile" default="Profile" />
            </g:link> 
        </div>         
        <div class="menu-pane-row">
            <g:link controller="smebilling" action="index" class="tile" style="color: white;">
                <img src="${resource(dir: 'images', file: 'bill.png')}">
                <br/>
                <g:message code="default.application.billing" default="Billing" />
            </g:link> 
        </div>
       
    </div>
    <div style="float: right; width: 80%; display: inline-block; overflow: hidden;">
        <g:layoutBody/>
    </div>
</body>
</html>
