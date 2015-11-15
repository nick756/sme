<%@ page contentType="text/html;charset=UTF-8" %>
<%@page import="com.sme.entities.*" %>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="layout" content="smepage"/>
        <title>SIFAR</title>
    </head>
    <body>
        <div class="action-header">
            <g:link style="float: left; margin-left: 5px;" action="index" params="['max': params.max, 'offset': params.offset, 'id': businessInstance?.id, 'filterDateFrom': params?.filterDateFrom, 'filterDateTill': params?.filterDateTill, 'filterOperation.id': params?.filterOperation?.id]"><img class="image-link" style="margin-left: 5px;" src="${resource(dir: 'images', file: 'arrow_left.png')}" title="<g:message code='actions.back'/>"/></g:link>
        </div>

        <div class="edit-form-box">
            
            <g:hasErrors bean="${businessTransactionInstance}">
            <ul class="errors" role="alert">
                    <g:eachError bean="${businessTransactionInstance}" var="error">
                    <li <g:if test="${error in org.springframework.validation.FieldError}">data-field-id="${error.field}"</g:if>><g:message error="${error}"/></li>
                    </g:eachError>
            </ul>
            </g:hasErrors>
                        
            <g:form action="updatetransaction" method="PUT" params="['offset': params.offset, 'filterDateFrom': params?.filterDateFrom, 'filterDateTill': params?.filterDateTill, 'filterOperation.id': params?.filterOperation?.id]">
                <g:hiddenField name="operator" value="${session?.user.name}"/>
                <g:hiddenField name="company.id" value="${session?.company.id}"/>
                <g:hiddenField name="id" value="${businessTransactionInstance.id}"/>
                <g:hiddenField name="version" value="${businessTransactionInstance?.version}" />
                
                <label class="edit-form mand"><g:message code="businesstransaction.type.label"/></label>
                <g:select class="select-list" name="operationType.id" from="${GenericOperation.list()}" value="${businessTransactionInstance?.operationType.id}" optionKey="id"/>
                <br/>                
                <label class="edit-form mand"><g:message code="businesstransaction.remarks.label"/></label>
                <g:textField class="text-input" name="transactionRemarks" value="${businessTransactionInstance?.transactionRemarks}"/>
                <br/> 
                <label class="edit-form mand"><g:message code="businesstransaction.date.label"/></label>
                <g:datePicker class="text-input" name="transactionDate" value="${businessTransactionInstance?.transactionDate}" precision="day"/>
                <br/>
                <label class="edit-form mand"><g:message code="businesstransaction.amount.label"/></label>
                <g:textField class="text-input" name="transactionAmount" value="${businessTransactionInstance?.transactionAmount}"/>
                <br/>
                <label class="edit-form mand"><g:message code="businesstransaction.paymentmode.label"/></label>
                <g:radioGroup name="cash" labels="${radioLabels}" values="[1, 0]" value="0">
                    ${it.label}&nbsp;&nbsp;${it.radio}&nbsp;&nbsp;&nbsp;&nbsp;
                </g:radioGroup>                

                <hr color="#9CC7F2" style="margin-bottom: 10px;"/>
                <!--input type="submit" value="<g:message code='actions.login.submit'/>" class="myButton" /-->
                <g:actionSubmit class="myButton" action="updatetransaction" value="${message(code: 'default.button.update.label', default: 'Update')}" />
            </g:form>                  

        </div>  
    </body>
</html>
