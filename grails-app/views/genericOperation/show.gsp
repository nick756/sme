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
                            
                            <label class="edit-form mand"><g:message code="genericOperation.group.label"/></label>
                            <g:textField class="text-input" name="group.id" value="${genericOperationInstance?.group?.name}" readonly="readonly" />
                            <br/>
                            <label class="edit-form"><g:message code="genericOperation.pnlGroup.label"/></label>
                            <g:textField class="text-input" name="pnlGroup.id" value="${genericOperationInstance?.pnlGroup?.name}" readonly="readonly" />
                            <br/>
                            <label class="edit-form"><g:message code="genericOperation.pnlSubGroup.label"/></label>
                            <g:textField class="text-input" name="pnlSubGroup.id" value="${genericOperationInstance?.pnlSubGroup?.name}" readonly="readonly" />
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

            </div>
</body>
</html>
