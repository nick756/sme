<%@ page import="com.sme.entities.*" %>

<div class="edit-form-box">
    <label class="edit-form mand"><g:message code="genericOperation.code.label"/></label>
    <g:textField class="text-input" name="name" value="${genericOperationInstance?.code}" disabled="true" />
    <br/>            
    <label class="edit-form mand"><g:message code="genericOperation.name.label"/> (MY)</label>
    <g:textField class="text-input" name="name" value="${genericOperationInstance?.name}"/>
    <br/>
    <label class="edit-form mand"><g:message code="genericOperation.name.label"/> (EN)</label>
    <g:textField class="text-input" name="name_EN" value="${genericOperationInstance?.name_EN}"/>
    <br/>
    <label class="edit-form mand"><g:message code="genericOperation.accounttype.label"/></label>
    <g:select class="select-list" name="accountType.id" from="${AccountType.list()}" value="${genericOperationInstance?.accountType?.id}" optionKey="id"/>
    <br/>
    <label class="edit-form mand"><g:message code="genericOperation.inbound.label"/></label>
    <g:textField class="text-input" name="name" value="${genericOperationInstance?.inbound}" disabled="true"/>
    <br/>
    <label class="edit-form mand"><g:message code="genericOperation.outbound.label"/></label>
    <g:textField class="text-input" name="name" value="${genericOperationInstance?.outbound}" disabled="true"/>
    <br/> 
    <label class="edit-form mand"><g:message code="genericOperation.active.label"/></label>

    <g:if test="${genericOperationInstance?.actual > 0}">
    <g:set var="visible" value="${true}"/>
    </g:if>
    <g:else>
        <g:set var="visible" value="${false}"/>
    </g:else>
    <g:textField class="text-input" name="name" value="${visible}" disabled="true"/>
    <br/>
</div>


