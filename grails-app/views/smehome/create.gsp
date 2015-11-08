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
            <g:link style="float: left; margin-left: 5px;" action="index" params="['max': params.max, 'offset': params.offset, 'id': businessInstance?.id]"><img class="image-link" style="margin-left: 5px;" src="${resource(dir: 'images', file: 'arrow_left.png')}" title="<g:message code='actions.back'/>"/></g:link>
        </div> 
        
        <g:hasErrors bean="${businessTransactionInstance}">
        <ul class="errors" role="alert">
                <g:eachError bean="${businessTransactionInstance}" var="error">
                <li <g:if test="${error in org.springframework.validation.FieldError}">data-field-id="${error.field}"</g:if>><g:message error="${error}"/></li>
                </g:eachError>
        </ul>
        </g:hasErrors>
            
        <div class="edit-form-box">
            <g:form action="save" method="POST" >
                <g:hiddenField name="operator" value="${session?.user.name}"/>
                <g:hiddenField name="company.id" value="${session?.company.id}"/>
                
                <label class="edit-form mand"><g:message code="businesstransaction.type.label"/></label>
                <g:select class="select-list" name="operationType.id" from="${GenericOperation.list()}" optionKey="id"/>
                <br/>
                <label class="edit-form mand"><g:message code="businesstransaction.remarks.label"/></label>
                <g:textField class="text-input" name="transactionRemarks" value="${businessTransactionInstance?.transactionRemarks}"/>
                <br/>  
                <label class="edit-form mand"><g:message code="businesstransaction.amount.label"/></label>
                <g:textField class="text-input" name="transactionAmount" value="${businessTransactionInstance?.transactionAmount}"/>
                <br/>
                <label class="edit-form mand"><g:message code="businesstransaction.date.label" default="Incorp Date"/></label>
                <g:datePicker class="select-list" name="transactionDate" value="${businessTransactionInstance?.transactionDate}" precision="day"/>
                <br/>
                
                <g:radioGroup name="cash" labels="['Cash', 'Bank/Cheque']" values="[1, 0]" value="0">
                    <label class="edit-form">${it.label}</label>
                    ${it.radio}
                    <br/>
                </g:radioGroup>
                <hr color="#9CC7F2" style="margin-bottom: 10px;"/>
                <input type="submit" value="<g:message code='actions.login.submit'/>" class="myButton" />
            </g:form>
        </div>
    </body>
</html>
