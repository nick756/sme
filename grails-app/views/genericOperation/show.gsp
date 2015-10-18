<%@ page import="com.sme.entities.GenericOperation" %>
<html>
    <head>
        <meta name="layout" content="adminpage">
        <title>SIFAR</title>
    </head>
    <body>
        <a href="#show-genericOperation" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
        <div class="nav" role="navigation">
            <ul>
                <li><g:link class="home" action="index"><g:message code="genericOperation.list"/></g:link></li>
                <!--li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li-->
                </ul>
            </div>

        <h1 class="sub-title">${genericOperationInstance?.name}</h1>
        <div class="edit-form-box">
            <label class="edit-form mand"><g:message code="genericOperation.code.label"/></label>
            <g:textField class="text-input" name="name" value="${genericOperationInstance?.code}"/>
            <br/>            
            <label class="edit-form mand"><g:message code="genericOperation.name.label"/> (MY)</label>
            <g:textField class="text-input" name="name" value="${genericOperationInstance?.name}"/>
            <br/>
            <label class="edit-form mand"><g:message code="genericOperation.name.label"/> (EN)</label>
            <g:textField class="text-input" name="name" value="${genericOperationInstance?.name_EN}"/>
            <br/>            
            <label class="edit-form mand"><g:message code="genericOperation.accounttype.label"/></label>
            <g:textField class="text-input" name="name" value="${genericOperationInstance?.accountType?.name}"/>
            <br/>
            <label class="edit-form mand"><g:message code="genericOperation.inbound.label"/></label>
            <g:textField class="text-input" name="name" value="${genericOperationInstance?.inbound}"/>
            <br/>
            <label class="edit-form mand"><g:message code="genericOperation.outbound.label"/></label>
            <g:textField class="text-input" name="name" value="${genericOperationInstance?.outbound}"/>
            <br/> 
            <label class="edit-form mand"><g:message code="genericOperation.active.label"/></label>

            <g:if test="${genericOperationInstance?.actual > 0}">
                <g:set var="visible" value="${true}"/>
            </g:if>
            <g:else>
                <g:set var="visible" value="${false}"/>
            </g:else>
            <g:textField class="text-input" name="name" value="${visible}"/>
            <br/>
    </div>



            <g:form url="[resource:genericOperationInstance, action:'delete']" method="DELETE">
            <fieldset class="buttons">
                <g:link class="edit" action="edit" resource="${genericOperationInstance}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
                <g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
            </fieldset>
        </g:form>
    </div>
</body>
</html>
