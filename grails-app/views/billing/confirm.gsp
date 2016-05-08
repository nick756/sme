<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="com.sme.entities.*" %>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="layout" content="adminpage"/>
        <title>SIFAR</title>
    </head>
    <body>
        <div class="action-header">
            <g:link style="float: left;" action="paidbills" params="['offset': params.offset]"><img class="image-link" style="margin-left: 5px;" src="${resource(dir: 'images', file: 'arrow_left.png')}" title="<g:message code='actions.transaction.add'/>"/></g:link>
        </div>
        <h1 class="sub-title"><g:message code="bill.invoice.caption" default="Invoice"/>:&nbsp;${objectInstance?.invoiceNumber}</h1>
        <g:if test="${errorMessage != null}">
            <div class="error-message">${errorMessage}</div>
        </g:if>     
        <div class="edit-form-box">
                        
            <g:form action="update" method="POST">
                <g:hiddenField name="id" value="${objectInstance.id}"/>
                <%-- Fieldset for entering Confirmation Details --%>
                <fieldset style="border: 1px solid #AFAFAF; margin-bottom: 5px; padding-top: 5px; background: #EFEFEF;">
                    <legend style="border: 1px solid #526C85; padding: 2px 15px; background: #FFF; color: #526C85;"><g:message code="messages.fieldset.paymentConfirmation.label"/></legend>
                    <label class="edit-form mand"><g:message code="bill.confirmationDate.label"/></label>
                    <g:datePicker class="text-input" name="confirmationDate" value="${objectInstance?.confirmationDate}" precision="day" relativeYears="${[-2..0]}" noSelection="['':'']" default="none"/>
                    <br/>
                    <label class="edit-form"><g:message code="bill.confirmationRemarks.label"/></label>
                    <g:textField class="text-input" name="confirmationRemarks" value="${objectInstance?.confirmationRemarks}"/>                    
                </fieldset>

                <fieldset style="border: 1px solid #AFAFAF; margin-bottom: 5px; padding-top: 5px; background: #FFF;">
                    <legend style="border: 1px solid #526C85; padding: 2px 15px; background: #FFF; color: #526C85;"><g:message code="messages.fieldset.payment.label"/></legend>
                    <br/>
                    <label class="edit-form"><g:message code="bill.amountPaid.label"/></label>
                    <div class="text-input" style="border: 1px solid #afafaf;"><g:formatNumber format="#,##0.00" number="${objectInstance?.amountPaid}"/></div>
                    <br/>
                    <label class="edit-form"><g:message code="bill.paymentDate.label"/></label>
                    <div class="text-input" style="border: 1px solid #afafaf;"><g:formatDate format="dd/MM/yyyy" date="${objectInstance?.paymentDate}"/></div> 
                    <br/>
                    <label class="edit-form"><g:message code="bill.paymentMode.label"/></label>
                    <div class="text-input" style="border: 1px solid #afafaf;">${objectInstance?.paymentMode}</div>
                    <br/>
                    <label class="edit-form"><g:message code="bill.paymentReference.label"/></label>
                    <g:textField class="text-input" name="paymentReference" value="${objectInstance?.paymentReference}" readOnly="true"/>
                </fieldset>                
                <label class="edit-form"><g:message code="bill.billingType.label"/></label>
                <div class="text-input" style="border: 1px solid #afafaf;">${objectInstance?.billingType}</div>
                <br/>                 
                <label class="edit-form"><g:message code="bill.dueDate.label"/></label>
                <div class="text-input" style="border: 1px solid #afafaf;"><g:formatDate format="dd/MM/yyyy" date="${objectInstance?.dueDate}"/></div> 
                <br/>   
                <label class="edit-form"><g:message code="bill.periodFrom.label"/></label>
                <div class="text-input" style="border: 1px solid #afafaf;"><g:formatDate format="dd/MM/yyyy" date="${objectInstance?.periodFrom}"/></div>
                <br/> 
                <label class="edit-form"><g:message code="bill.periodTill.label"/></label>
                <div class="text-input" style="border: 1px solid #afafaf;"><g:formatDate format="dd/MM/yyyy" date="${objectInstance?.periodTill}"/></div>
                <br/> 
                <label class="edit-form"><g:message code="bill.amount.label"/></label>
                <g:textField class="text-input" name="amount" value="${formatNumber(format: '#,##0.00', number: objectInstance?.amount)}" readOnly="true"/>
                <br/>   
                <label class="edit-form"><g:message code="bill.gracePeriodTill.label"/></label>
                <div class="text-input" style="border: 1px solid #afafaf;"><g:formatDate format="dd/MM/yyyy" date="${objectInstance?.gracePeriodTill}"/></div>


                <hr color="#9CC7F2" style="margin-bottom: 10px;"/>
                <!--input type="submit" value="<g:message code='actions.login.submit'/>" class="myButton" /-->
                <g:actionSubmit class="myButton" action="update" value="${message(code: 'default.button.update.label', default: 'Update')}" />
            </g:form>                  

        </div>  
    </body>
</html>
