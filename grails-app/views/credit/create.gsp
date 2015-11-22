<%@page contentType="text/html;charset=UTF-8" %>
<%@page import="com.sme.entities.*" %>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="layout" content="smepage"/>
        <title>SIFAR</title>
    </head>
    <body>
<%
    def operationsList = GenericOperation.createCriteria().list() {
        or {
            lt('code', 1000)
            eq('code', 1020)
            eq('code', 1030)
        }
    }
%>
        <div class="action-header">
            <g:link style="float: left; margin-left: 5px;" action="index" params="['max': params.max, 'offset': params.offset, 'id': businessInstance?.id]"><img class="image-link" style="margin-left: 5px;" src="${resource(dir: 'images', file: 'arrow_left.png')}" title="<g:message code='actions.back'/>"/></g:link>
        </div> 
        
        <g:hasErrors bean="${businessInstance}">
        <ul class="errors" role="alert">
                <g:eachError bean="${businessInstance}" var="error">
                <li <g:if test="${error in org.springframework.validation.FieldError}">data-field-id="${error.field}"</g:if>><g:message error="${error}"/></li>
                </g:eachError>
        </ul>
        </g:hasErrors>
            
        <div class="edit-form-box">
            <g:form action="save" method="POST" >
                <g:hiddenField name="operator" value="${session?.user.name}"/>
                <g:hiddenField name="company.id" value="${session?.company.id}"/>
                
                <label class="edit-form mand"><g:message code="businesstransaction.type.label"/></label>
                <g:select class="select-list" name="operationType.id" from="${opList}" optionKey="id"/>
                <br/>
                <label class="edit-form mand"><g:message code="businesstransaction.remarks.label"/></label>
                <g:textField class="text-input" name="transactionRemarks" value="${businessInstance?.transactionRemarks}"/>
                <br/>  
                <label class="edit-form mand"><g:message code="businesstransaction.amount.label"/></label>
                <g:textField class="text-input" name="transactionAmount" value="${businessInstance?.transactionAmount}"/>
                <br/>
                <label class="edit-form mand"><g:message code="business`transaction.date.label" default="Incorp Date"/></label>
                <g:datePicker class="select-list" name="transactionDate" value="${businessInstance?.transactionDate}" precision="day"/>
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
