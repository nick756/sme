<%@page contentType="text/html;charset=UTF-8" %>
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
                <label class="edit-form mand"><g:message code="business.industry.label"/></label>
                <g:select class="select-list" name="industry.id" from="${Industry.list()}" value="${businessInstance?.industry?.id}" optionKey="id"/>
                <br/>                
                <label class="edit-form mand"><g:message code="business.profile.label"/></label>
                <g:select class="select-list" name="profile.id" from="${GenericProfile.list()}" value="${businessInstance?.profile?.id}" optionKey="id"/>
                <br/><br/>

                <label class="edit-form"><g:message code="business.bank.label"/></label>
                <g:select class="select-list" name="bank.id" from="${LendingAgency.list()}" value="${businessInstance?.bank?.id}" optionKey="id" noSelection="['':'']" default="none"/>
                <br/>                
                <label class="edit-form"><g:message code="business.acccountno.label" default="Account Number"/></label>
                <g:textField class="text-input" name="accountNo" value="${businessInstance?.accountNo}"/>
                <br/>
                <label class="edit-form mand"><g:message code="business.regNumber.label" default="ROC Number"/></label>
                <g:textField class="text-input" name="regNumber" value="${businessInstance?.regNumber}"/>
                <br/>
                <label class="edit-form"><g:message code="business.incorpDate.label" default="Incorp Date"/></label>
                <g:datePicker class="select-list" name="incorpDate" value="${businessInstance?.incorpDate}" noSelection="${['': '--']}" default="none" precision="day"/>
                <br/>
                <label class="edit-form"><g:message code="business.registrationdate.label" default="Registration Date"/></label>
                <g:datePicker class="select-list" name="registrationDate" value="${businessInstance?.registrationDate}" noSelection="${['': '--']}" default="none" precision="day"/>
                <!--g:textField class="text-input" name="incorpDate" value="${formatDate(format:'dd/MM/yyyy', date:businessInstance?.incorpDate)}"/-->
                <br/>                
                <label class="edit-form"><g:message code="business.address.label" default="Address"/></label>
                <g:textArea class="text-input" name="address" value="${businessInstance?.address}"/>
                <br/>
                <label class="edit-form mand"><g:message code="business.city.label" default="City"/></label>
                <g:textField class="text-input" name="city" value="${businessInstance?.city}"/>   
                <br/><br/>
                <label class="edit-form mand"><g:message code="business.contactPerson1.label" default="Contact Person"/></label>
                <g:textField class="text-input" name="contactPerson1" value="${businessInstance?.contactPerson1}"/>
                <br/>
                <label class="edit-form mand"><g:message code="business.contactNumber1.label" default="Contact Number"/></label>
                <g:textField class="text-input" name="contactNumber1" value="${businessInstance?.contactNumber1}"/>
                <br/>
                <label class="edit-form"><g:message code="business.contactPerson2.label" default="Contact Person"/></label>
                <g:textField class="text-input" name="contactPerson2" value="${businessInstance?.contactPerson2}"/>
                <br/>
                <label class="edit-form"><g:message code="business.contactNumber2.label" default="Contact Number"/></label>
                <g:textField class="text-input" name="contactNumber2" value="${businessInstance?.contactNumber2}"/>
                <br/>                
                
                <fieldset style="border: 1px solid #AFAFAF; margin-bottom: 5px; padding-top: 5px; background: #EFEFEF;">
                    <legend style="border: 1px solid #526C85; padding: 2px 8px; background: #526C85; color: #FFF;"><g:message code="messages.fieldset.billing.label"/></legend>
                    <br/>
                    <label class="edit-form mand"><g:message code="business.billingType.label"/></label>
                    <g:select class="select-list" name="billingType?.id" from="${BillingType?.list()}" value="${businessInstance?.billingType?.id}" optionKey="id" noSelection="['':'']" default="none"/>
                    <br/>
                    <label class="edit-form mand"><g:message code="business.freeServices.label"/></label>
                    <g:checkBox name="freeServices" value="${businessInstance?.freeServices}"/>
                    <br/>
                    <label class="edit-form"><g:message code="business.startBillingDate.label"/></label>
                    <g:datePicker class="select-list" name="startBillingDate" value="${businessInstance?.startBillingDate}" noSelection="${['': '--']}" default="none" precision="day"/>
                    <br/>
                    <label class="edit-form"><g:message code="business.rate.label"/></label>
                    <g:textField class="text-input" name="rate" value="${businessInstance?.rate}"/>
                    <br/>
                    <label class="edit-form"><g:message code="business.gracePeriod.label"/></label>
                    <g:textField class="text-input" name="gracePeriod" value="${businessInstance?.gracePeriod}"/>                    
                </fieldset>

                <hr color="#9CC7F2" style="margin-bottom: 10px;"/>
                <input type="submit" value="<g:message code='actions.login.submit'/>" class="myButton" />
            </g:form>
            
        </div>
        
    </body>
</html>
