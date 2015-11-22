<%@page contentType="text/html;charset=UTF-8" %>
<%@page import="com.sme.entities.*" %>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="layout" content="smepage"/>
        <title>SIFAR</title>
    </head>
    <body>
        <div class="action-header">
            <g:link style="float: left; margin-left: 5px;" action="index" params="['max': params.max, 'offset': params.offset, 'id': businessInstance?.id]"><img class="image-link" style="margin-left: 5px;" src="${resource(dir: 'images', file: 'arrow_left.png')}" title="<g:message code='actions.transaction.add'/>"/></g:link>
            <g:link style="float: right; margin-right: 5px;" action="editprofile" params="['max': params.max, 'offset': params.offset, 'id': businessInstance?.id]"><img class="image-link" src="${resource(dir: 'images', file: 'edit.png')}" title="<g:message code='actions.edit'/>"/></g:link>
        </div> 
        
        <div class="edit-form-box">

            <label class="edit-form"><g:message code="business.name.label"/></label>
            <g:textField class="text-input" name="name" value="${businessInstance?.name}" readonly="true"/>
            <br/>
            <label class="edit-form"><g:message code="business.accountno.label"/></label>
            <g:textField class="text-input" name="accountNo" value="${businessInstance?.accountNo}" readonly="true"/>
            <br/>            
            <label class="edit-form"><g:message code="business.profile.label"/></label>
            <g:textField class="text-input" name="name" value="${businessInstance?.profile?.name}" readonly="true"/>
            <br/>            
            <label class="edit-form"><g:message code="business.regNumber.label" default="No"/></label>
            <g:textField class="text-input" name="regNumber" value="${businessInstance?.regNumber}" readonly="true"/>
            <br/>
            <label class="edit-form"><g:message code="business.industry.label" default="No"/></label>
            <g:textField class="text-input" name="regNumber" value="${businessInstance?.industry?.name}" readonly="true"/>
            <br/>     
            <label class="edit-form"><g:message code="business.incorpDate.label" default="Incorp Date"/></label>
            <g:textField class="text-input" name="regNumber" value="${formatDate(format:'dd/MM/yyyy', date:businessInstance?.incorpDate)}" readonly="true"/>
            <br/>  
            <label class="edit-form"><g:message code="business.address.label" default="Address"/></label>
            <g:textArea class="text-input" name="address" value="${businessInstance?.address}" readonly="true"/>
            <br/>
            <label class="edit-form"><g:message code="business.city.label" default="City"/></label>
            <g:textField class="text-input" name="address" value="${businessInstance?.city}" readonly="true"/>                   

        </div>        
    </body>
</html>
