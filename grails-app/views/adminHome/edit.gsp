<%@ page contentType="text/html;charset=UTF-8" %>
<%@page import="com.sme.entities.*" %>

<html>
    <head>
        <meta name="layout" content="adminpage"/>
        <title>SME</title>
    </head>
    <body>
        <div style="width: 100%; background: #D0E8F4; display: inline-block; text-align: right; border-bottom: 0px solid #6285C7; border-top: 0px solid #6285C7; padding: 5px 0;">
            <g:link style="float: left;" action="show" params="['max': params.max, 'offset': params.offset, 'id': businessInstance?.id]"><img class="image-link" style="margin-left: 5px;" src="${resource(dir: 'images', file: 'arrow_left.png')}" title="<g:message code='actions.back'/>"/></g:link>
            </div>
        <h1 class="sub-title">${businessInstance?.name}</h1>
        <div class="edit-form-box">
            
            <g:form url="[resource:businessInstance, action:'update']" method="PUT" >
                <label class="edit-form mand"><g:message code="business.name.label"/></label>
                <g:textField class="text-input" name="name" value="${businessInstance?.name}"/>
                <br/>
                <label class="edit-form mand"><g:message code="business.profile.label"/></label>
                <g:select class="select-list" name="profile.id" from="${GenericProfile.list()}" value="${businessInstance?.profile?.id}" optionKey="id"/>
                <br/>
                <label class="edit-form mand"><g:message code="business.regNumber.label" default="ROC Number"/></label>
                <g:textField class="text-input" name="regNumber" value="${businessInstance?.regNumber}"/>
                <br/>
                <label class="edit-form mand"><g:message code="business.industry.label"/></label>
                <g:select class="select-list" name="industry.id" from="${Industry.list()}" value="${businessInstance?.industry?.id}" optionKey="id"/>
                <br/>
                <label class="edit-form"><g:message code="business.incorpDate.label" default="Incorp Date"/></label>
                <g:datePicker class="select-list" name="incorpDate" value="${businessInstance?.incorpDate}" precision="day"/>
                <!--g:textField class="text-input" name="incorpDate" value="${formatDate(format:'dd/MM/yyyy', date:businessInstance?.incorpDate)}"/-->
                <br/>                
                <label class="edit-form"><g:message code="business.address.label" default="Address"/></label>
                <g:textArea class="text-input" name="address" value="${businessInstance?.address}"/>
                <br/>
                <label class="edit-form mand"><g:message code="business.city.label" default="City"/></label>
                <g:textField class="text-input" name="city" value="${businessInstance?.city}"/>                   
                <hr color="#9CC7F2" style="margin-bottom: 10px;"/>
                <input type="submit" value="<g:message code='actions.login.submit'/>" class="myButton" />
            </g:form>
            
        </div>
        
    </body>
</html>
