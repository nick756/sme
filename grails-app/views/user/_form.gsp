<%@ page import="com.sme.entities.User" %>



<div class="fieldcontain ${hasErrors(bean: userInstance, field: 'name', 'error')} required">
	<label for="name">
		<g:message code="user.name.label" default="Name" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="name" required="" value="${userInstance?.name}"/>

</div>

<div class="fieldcontain ${hasErrors(bean: userInstance, field: 'login', 'error')} required">
	<label for="login">
		<g:message code="user.login.label" default="Login" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="login" required="" value="${userInstance?.login}"/>

</div>

<div class="fieldcontain ${hasErrors(bean: userInstance, field: 'passw', 'error')} required">
	<label for="passw">
		<g:message code="user.passw.label" default="Passw" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="passw" maxlength="10" required="" value="${userInstance?.passw}"/>

</div>

<div class="fieldcontain ${hasErrors(bean: userInstance, field: 'contactNo', 'error')} ">
	<label for="contactNo">
		<g:message code="user.contactNo.label" default="Contact No" />
		
	</label>
	<g:textField name="contactNo" value="${userInstance?.contactNo}"/>

</div>

<div class="fieldcontain ${hasErrors(bean: userInstance, field: 'email', 'error')} ">
	<label for="email">
		<g:message code="user.email.label" default="Email" />
		
	</label>
	<g:field type="email" name="email" value="${userInstance?.email}"/>

</div>

<div class="fieldcontain ${hasErrors(bean: userInstance, field: 'role', 'error')} required">
	<label for="role">
		<g:message code="user.role.label" default="Role" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="role" name="role.id" from="${com.sme.entities.UserRole.list()}" optionKey="id" required="" value="${userInstance?.role?.id}" class="many-to-one"/>

</div>

<div class="fieldcontain ${hasErrors(bean: userInstance, field: 'company', 'error')} ">
	<label for="company">
		<g:message code="user.company.label" default="Company" />
		
	</label>
	<g:select id="company" name="company.id" from="${com.sme.entities.Business.list()}" optionKey="id" value="${userInstance?.company?.id}" class="many-to-one" noSelection="['null': '']"/>

</div>

