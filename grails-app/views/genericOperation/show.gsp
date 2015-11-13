<%@ page import="com.sme.entities.*" %>
<html>
    <head>
        <meta name="layout" content="adminpage">
        <title>SIFAR</title>
    </head>
    <body>
            <div class="action-header">
                <g:link style="float: left; margin-left: 5px;" controller="genericOperation" action="index" params="['max': params.max, 'offset': params.offset, 'id': genericOperationInstance?.id]"><img class="image-link" style="margin-left: 5px;" src="${resource(dir: 'images', file: 'arrow_left.png')}" title="<g:message code='actions.back'/>"/></g:link>
                <g:link action="edit" params="['max': params.max, 'offset': params.offset, 'id': genericOperationInstance?.id]"><img class="image-link" style="margin-right: 5px;" src="${resource(dir: 'images', file: 'edit.png')}" title="<g:message code='actions.edit'/>"/></g:link>
            </div> 
                    
            <h1 class="sub-title">${genericOperationInstance?.name}</h1>
                    <div class="edit-form-box">
                    <!--<g:if test="${flash.message}">
                    <div class="message" role="status">${flash.message}</div>
                    </g:if>
                    <g:hasErrors bean="${genericOperationInstance}">
                    <ul class="errors" role="alert">
                            <g:eachError bean="${genericOperationInstance}" var="error">
                            <li <g:if test="${error in org.springframework.validation.FieldError}">data-field-id="${error.field}"</g:if>><g:message error="${error}"/></li>
                            </g:eachError>
                    </ul>
                    </g:hasErrors>
                    <g:form url="[resource:genericOperationInstance, action:'save']" >-->

                        <label class="edit-form mand"><g:message code="genericOperation.code.label"/></label>
                        <g:textField class="text-input" name="code" value="${genericOperationInstance?.code}" readonly="readonly" />
                        <br/>            
                        <label class="edit-form mand"><g:message code="genericOperation.name.label"/> (MY)</label>
                        <g:textField class="text-input" name="name" value="${genericOperationInstance?.name}" readonly="readonly" />
                        <br/>
                        <label class="edit-form mand"><g:message code="genericOperation.name.label"/> (EN)</label>
                        <g:textField class="text-input" name="name_EN" value="${genericOperationInstance?.name_EN}" readonly="readonly" />
                        <br/>
                        <label class="edit-form mand"><g:message code="genericOperation.accounttype.label"/></label>
                        <g:textField class="text-input" name="accountType.id" value="${genericOperationInstance?.accountType?.name}" readonly="readonly" />
                        <br/>
                        <label class="edit-form mand"><g:message code="genericOperation.mirrorCash.label"/></label>
                        <g:textField class="text-input" name="mirrorCash.id" value="${genericOperationInstance?.mirrorCash?.name}" readonly="readonly" />
                        <br/>
                        <label class="edit-form mand"><g:message code="genericOperation.mirrorBank.label"/></label>
                        <g:textField class="text-input" name="mirrorBank.id" value="${genericOperationInstance?.mirrorBank?.name}" readonly="readonly" />
                        <br/>
                        <label class="edit-form mand"><g:message code="genericOperation.inbound.label"/></label>
                        <g:checkBox name="inbound" value="${genericOperationInstance?.inbound}" disabled="true" />
                        <br/>
                        <label class="edit-form mand"><g:message code="genericOperation.outbound.label"/></label>
                        <g:checkBox name="outbound" value="${genericOperationInstance?.outbound}" disabled="true" />
                        <br/>
                        <label class="edit-form mand"><g:message code="genericOperation.actual.label"/></label>
                        <g:textField class="text-input" name="actual" value="${genericOperationInstance?.actual}" readonly="readonly" />
                        <br/>


                        <!--<label class="edit-form mand"><g:message code="genericOperation.active.label"/></label>


                        <g:if test="${genericOperationInstance?.actual > 0}">
                        <g:set var="visible" value="${true}"/>
                        </g:if>
                        <g:else>
                            <g:set var="visible" value="${false}"/>
                        </g:else>
                        <g:textField class="text-input" name="name" value="${visible}" readonly="readonly" />
                        <br/>

                        <fieldset class="form">
                                <g:render template="form"/>
                        </fieldset>
                        <fieldset class="buttons">
                                <g:submitButton name="create" class="save" value="${message(code: 'default.button.create.label', default: 'Create')}" />
                        </fieldset>
                        <hr color="#9CC7F2" style="margin-bottom: 10px;"/>
                        <input type="submit" value="<g:message code='actions.login.submit'/>" class="myButton" />
                    </g:form>-->
            </div>
</body>
</html>
