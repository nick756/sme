<%@ page import="com.sme.entities.Business" %>



<div class="fieldcontain ${hasErrors(bean: businessInstance, field: 'name', 'error')} required">
	<label for="name">
		<g:message code="business.name.label" default="Name" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="name" required="" value="${businessInstance?.name}"/>

</div>

<div class="fieldcontain ${hasErrors(bean: businessInstance, field: 'regNumber', 'error')} required">
	<label for="regNumber">
		<g:message code="business.regNumber.label" default="Reg Number" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="regNumber" required="" value="${businessInstance?.regNumber}"/>

</div>

<div class="fieldcontain ${hasErrors(bean: businessInstance, field: 'incorpDate', 'error')} ">
	<label for="incorpDate">
		<g:message code="business.incorpDate.label" default="Incorp Date" />
		
	</label>
	<g:datePicker name="incorpDate" precision="day"  value="${businessInstance?.incorpDate}" default="none" noSelection="['': '']" />

</div>

<div class="fieldcontain ${hasErrors(bean: businessInstance, field: 'address', 'error')} ">
	<label for="address">
		<g:message code="business.address.label" default="Address" />
		
	</label>
	<g:textArea name="address" cols="40" rows="5" maxlength="1024" value="${businessInstance?.address}"/>

</div>

<div class="fieldcontain ${hasErrors(bean: businessInstance, field: 'city', 'error')} ">
	<label for="city">
		<g:message code="business.city.label" default="City" />
		
	</label>
	<g:textField name="city" value="${businessInstance?.city}"/>

</div>

