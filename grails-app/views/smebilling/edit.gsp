<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="com.sme.entities.*" %>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="layout" content="smepage"/>
        <title>SIFAR</title>
    </head>
    <body>
        <div class="action-header">
            <g:link style="float: left;" action="index" params="['offset': params.offset]"><img class="image-link" style="margin-right: 5px;" src="${resource(dir: 'images', file: 'arrow_left.png')}" title="<g:message code='actions.transaction.add'/>"/></g:link>
        </div>
        <h1 class="sub-title"><g:message code="bill.invoice.caption" default="Invoice"/>:&nbsp;${objectInstance?.invoiceNumber}</h1>
        <g:if test="${errorMessage != null}">
            <div class="error-message">${errorMessage}</div>
        </g:if>     
        <div class="edit-form-box">
                        
            <g:form action="update" method="POST">
                <g:hiddenField name="id" value="${objectInstance.id}"/>
                
                <label class="edit-form"><g:message code="bill.billingType.label"/></label>
                ${objectInstance?.billingType}
                <br/>                 
                <label class="edit-form"><g:message code="bill.dueDate.label"/></label>
                <g:datePicker class="text-input" name="dueDate" value="${objectInstance?.dueDate}" precision="day" readOnly="true"/>
                <br/>   
                <label class="edit-form"><g:message code="bill.periodFrom.label"/></label>
                <g:datePicker class="text-input" name="periodFrom" value="${objectInstance?.periodFrom}" precision="day" readOnly="true"/>
                <br/> 
                <label class="edit-form"><g:message code="bill.periodTill.label"/></label>
                <g:datePicker class="text-input" name="periodTill" value="${objectInstance?.periodTill}" precision="day" readOnly="true"/>
                <br/> 
                <label class="edit-form"><g:message code="bill.amount.label"/></label>
                <g:textField class="text-input" name="amount" value="${formatNumber(format: '#,##0.00', number: objectInstance?.amount)}" readOnly="true"/>
                <br/>   
                <label class="edit-form"><g:message code="bill.gracePeriodTill.label"/></label>
                <g:datePicker class="text-input" name="gracePeriodTill" value="${objectInstance?.gracePeriodTill}" precision="day" readOnly="true"/>
                <br/> 
                <fieldset style="border: 1px solid #AFAFAF; margin-bottom: 5px; padding-top: 5px; background: #EFEFEF;">
                    <legend style="border: 1px solid #526C85; padding: 2px 15px; background: #FFF; color: #526C85;"><g:message code="messages.fieldset.payment.label"/></legend>
                    <br/>
                    <label class="edit-form mand"><g:message code="bill.amountPaid.label"/></label>
                    <g:textField class="text-input" name="amountPaid" value="${objectInstance?.amountPaid}"/>
                    <br/>
                    <label class="edit-form mand"><g:message code="bill.paymentDate.label"/></label>
                    <g:datePicker class="text-input" name="paymentDate" value="${objectInstance?.paymentDate}" precision="day" relativeYears="${[-2..0]}" noSelection="['':'']" default="none"/>
                    <br/>
                    <label class="edit-form mand"><g:message code="bill.paymentMode.label"/></label>
                    <g:select class="select-list" name="paymentMode.id" from="${PaymentMode?.list()}" value="${objectInstance?.paymentMode?.id}" optionKey="id" noSelection="['':'']" default="none"/>
                    <br/>
                    <label class="edit-form mand"><g:message code="bill.paymentReference.label"/></label>
                    <g:textField class="text-input" name="paymentReference" value="${objectInstance?.paymentReference}"/>
                </fieldset>
                
              

                <hr color="#9CC7F2" style="margin-bottom: 10px;"/>
                <!--input type="submit" value="<g:message code='actions.login.submit'/>" class="myButton" /-->
                <g:actionSubmit class="myButton" action="update" value="${message(code: 'default.button.update.label', default: 'Update')}" />
            </g:form>                  

        </div>  
    </body>
</html>
