<%@ page import="com.sme.entities.*" %>

<html>
	<head>
		<meta name="layout" content="adminpage">
		<g:set var="entityName" value="${message(code: 'genericOperation.label', default: 'GenericOperation')}" />
		<title><g:message code="default.create.label" args="[entityName]" /></title>
	</head>
	<body>
            <div class="action-header">
                <g:link style="float: left; margin-left: 5px;" controller="genericOperation" action="index" params="['max': params.max, 'offset': params.offset, 'id': genericOperationInstance?.id]"><img class="image-link" style="margin-left: 5px;" src="${resource(dir: 'images', file: 'arrow_left.png')}" title="<g:message code='actions.back'/>"/></g:link>
            </div> 
        
		<!--<div id="create-genericOperation" class="content scaffold-create" role="main">-->
                <h1 class="sub-title"><g:message code="default.create.label" args="[entityName]" /></h1>
                
                <div class="edit-form-box">
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<g:hasErrors bean="${genericOperationInstance}">
			<ul class="errors" role="alert">
				<g:eachError bean="${genericOperationInstance}" var="error">
				<li <g:if test="${error in org.springframework.validation.FieldError}">data-field-id="${error.field}"</g:if>><g:message error="${error}"/></li>
				</g:eachError>
			</ul>
			</g:hasErrors>
			<g:form url="[resource:genericOperationInstance, action:'save']">
                            
                            <label class="edit-form mand"><g:message code="genericOperation.code.label"/></label>
                            <g:textField class="text-input" name="code" value="${genericOperationInstance?.code}"/>
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
                            <label class="edit-form mand"><g:message code="genericOperation.mirrorCash.label"/></label>
                            <g:select class="select-list" name="mirrorCash.id" from="${Mirror}" value="${genericOperationInstance?.mirrorCash?.id}" optionKey="id"/>
                            <br/>
                            <label class="edit-form mand"><g:message code="genericOperation.mirrorBank.label"/></label>
                            <g:select class="select-list" name="mirrorBank.id" from="${Mirror}" value="${genericOperationInstance?.mirrorBank?.id}" optionKey="id"/>
                            <br/>
                            <label class="edit-form mand"><g:message code="genericOperation.inbound.label"/></label>
                            <g:checkBox name="inbound" value="${genericOperationInstance?.inbound}"/>
                            <br/>
                            <label class="edit-form mand"><g:message code="genericOperation.outbound.label"/></label>
                            <g:checkBox name="outbound" value="${genericOperationInstance?.outbound}" />
                            <br/>
                            <label class="edit-form mand"><g:message code="genericOperation.actual.label"/></label>
                            <g:textField class="text-input" name="actual" value="${genericOperationInstance?.actual}"/>
                            <br/>
                            
                            
                            <!--<label class="edit-form mand"><g:message code="genericOperation.active.label"/></label>
                            

                            <g:if test="${genericOperationInstance?.actual > 0}">
                            <g:set var="visible" value="${true}"/>
                            </g:if>
                            <g:else>
                                <g:set var="visible" value="${false}"/>
                            </g:else>
                            <g:textField class="text-input" name="name" value="${visible}" disabled="true"/>
                            <br/>

                            <fieldset class="form">
                                    <g:render template="form"/>
                            </fieldset>
                            <fieldset class="buttons">
                                    <g:submitButton name="create" class="save" value="${message(code: 'default.button.create.label', default: 'Create')}" />
                            </fieldset>-->
                            <hr color="#9CC7F2" style="margin-bottom: 10px;"/>
                            <input type="submit" value="<g:message code='actions.login.submit'/>" class="myButton" />
			</g:form>
		</div>
	</body>
</html>
