<div style="width: 100%; background: #fff; margin-top: 4px; border-bottom: 1px solid #218C8D; height: 40px; display: table; vertical-align: middle; overflow: hidden; color: #555;">
    <div style="font-family: 'Muli', sans-serif; font-size: 12px; width: 3%; display: table-cell; height: 40px; vertical-align: middle; color: #555;">
        <img style="display: inline-block;" src="${resource(dir: 'images', file: 'user_login.png')}"/>
    </div>
    <div style="font-family: 'Muli', sans-serif; font-size: 16px; margin-left: 0; width: auto; display: table-cell; height: 40px; vertical-align: middle; color: #555;">${session.user?.name} (${session.user?.role?.name})</div>
    <div style="font-family: verdana; text-align: right; color: #555; padding-right: 8px; width: auto; display: table-cell; height: 40px; vertical-align: middle;">
        <g:link class="redirect-indicator" controller="login" action="logout"><img style="width: 24px; height: 24px; float: right;" src="${resource(dir: 'images', file: 'sign_out.png')}"></g:link>
    </div>
    </div>
    <div style="display: table; width: 100%; vertical-align: middle;">
        <!--div style="display: table-cell;"><img style="display: inline-block; width: 200px; height: 200px;" src="${resource(dir: 'images', file: 'cube.jpg')}"></div-->
        <div style="width: 90%; height: auto; margin-top: 0px; padding: 3px 0 3px 0; display: table-cell; vertical-align: middle;">
            <span style="font-family: 'Quicksand', sans-serif; margin-left: 10px; font-size: 46px; color: #218C8D;"><g:message code="default.application.title"/></span>
        </div>
    </div>
