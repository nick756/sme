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
            <g:link style="float: left; margin-left: 5px;" action="profile" params="['max': params.max, 'offset': params.offset, 'id': businessInstance?.id]"><img class="image-link" style="margin-left: 5px;" src="${resource(dir: 'images', file: 'arrow_left.png')}" title="<g:message code='actions.transaction.add'/>"/></g:link>
        </div> 
        
        <div class="edit-form-box">
            
            <g:hasErrors bean="${businessInstance}">
            <ul class="errors" role="alert">
                    <g:eachError bean="${businessInstance}" var="error">
                    <li <g:if test="${error in org.springframework.validation.FieldError}">data-field-id="${error.field}"</g:if>><g:message error="${error}"/></li>
                    </g:eachError>
            </ul>
            </g:hasErrors>
                        
            <g:form action="updateprofile" method="PUT">
                <g:hiddenField name="operator" value="${session?.user.name}"/>
                <g:hiddenField name="company.id" value="${session?.company.id}"/>
                <g:hiddenField name="id" value="${businessInstance.id}"/>
                <g:hiddenField name="version" value="${businessInstance?.version}" />
                
                <label class="edit-form mand"><g:message code="business.name.label"/></label>
                <g:textField class="text-input" name="name" value="${businessInstance?.name}"/>
                <br/>
                <label class="edit-form"><g:message code="business.accountno.label"/></label>
                <g:textField class="text-input" name="accountNo" value="${businessInstance?.accountNo}"/>
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
                <!--input type="submit" value="<g:message code='actions.login.submit'/>" class="myButton" /-->
                <g:actionSubmit class="myButton" action="updateprofile" value="${message(code: 'default.button.update.label', default: 'Update')}" />
            </g:form>                  

        </div>        
    </body>
</html>
