<%@ page import="com.sme.entities.GenericOperation" %>



<div class="fieldcontain ${hasErrors(bean: genericOperationInstance, field: 'code', 'error')} required">
	<label for="code">
		<g:message code="genericOperation.code.label" default="Code" />
		<span class="required-indicator">*</span>
	</label>
	<g:field name="code" type="number" value="${genericOperationInstance.code}" required=""/>

</div>

<div class="fieldcontain ${hasErrors(bean: genericOperationInstance, field: 'name', 'error')} required">
	<label for="name">
		<g:message code="genericOperation.name.label" default="Name" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="name" required="" value="${genericOperationInstance?.name}"/>

</div>

<div class="fieldcontain ${hasErrors(bean: genericOperationInstance, field: 'accountType', 'error')} required">
	<label for="accountType">
		<g:message code="genericOperation.accountType.label" default="Account Type" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="accountType" name="accountType.id" from="${com.sme.entities.AccountType.list()}" optionKey="id" required="" value="${genericOperationInstance?.accountType?.id}" class="many-to-one"/>

</div>

<div class="fieldcontain ${hasErrors(bean: genericOperationInstance, field: 'profiles', 'error')} ">
	<label for="profiles">
		<g:message code="genericOperation.profiles.label" default="Profiles" />
		
	</label>
	
<ul class="one-to-many">
<g:each in="${genericOperationInstance?.profiles?}" var="p">
    <li><g:link controller="profileLink" action="show" id="${p.id}">${p?.encodeAsHTML()}</g:link></li>
</g:each>
<li class="add">
<g:link controller="profileLink" action="create" params="['genericOperation.id': genericOperationInstance?.id]">${message(code: 'default.add.label', args: [message(code: 'profileLink.label', default: 'ProfileLink')])}</g:link>
</li>
</ul>


</div>

<div class="fieldcontain ${hasErrors(bean: genericOperationInstance, field: 'inbound', 'error')} ">
	<label for="inbound">
		<g:message code="genericOperation.inbound.label" default="Inbound" />
		
	</label>
	<g:checkBox name="inbound" value="${genericOperationInstance?.inbound}" />

</div>

<div class="fieldcontain ${hasErrors(bean: genericOperationInstance, field: 'outbound', 'error')} ">
	<label for="outbound">
		<g:message code="genericOperation.outbound.label" default="Outbound" />
		
	</label>
	<g:checkBox name="outbound" value="${genericOperationInstance?.outbound}" />

</div>

