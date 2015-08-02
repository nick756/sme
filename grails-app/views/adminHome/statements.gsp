<!--
  To change this license header, choose License Headers in Project Properties.
  To change this template file, choose Tools | Templates
  and open the template in the editor.
-->

<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="com.sme.entities.*" %>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="layout" content="adminpage"/>
        <title><g:message code="pnlstatement.page.label"/></title>
        <style>
            #period_year {width: 200px;}
        </style>
    </head>
    <body>
        <div class="action-header">
            <g:link style="float: left;" action="listtransactions" params="['max': params.max, 'offset': params.offset, 'id': businessInstance?.id]"><img class="image-link" style="margin-left: 5px;" src="${resource(dir: 'images', file: 'arrow_left.png')}" title="<g:message code='actions.back'/>"/></g:link>
        </div>
        <h1 class="sub-title" style="width: 100%;">${businessInstance?.name}</h1>
        <div class="edit-form-box" style="width: 100%; display: block; float: left;">
            <g:form action="statements" params="['max': params.max, 'offset': params.offset]">
                <g:hiddenField name="instID" value="${businessInstance?.id}"/>
                <label class="edit-form"><g:message code="pnlstatement.year.label"/></label>
                <g:datePicker name="period" class="select-list" precision="year" relativeYears="[-1..1]" value="${yearInst}" />
                <br/>
                <label class="edit-form"><g:message code="pnlstatement.month.label"/></label>
                <g:select class="select-list" style="width: 200px;" name="month.id" noSelection="${['null':'']}" from="${Month.list()}" value="${monthInst?.id}" optionKey="id" />
                <br/>
                <input type="submit" value="${message(code: 'actions.generate')}" class="myButton" style="margin-left: 205px;"/>
            </g:form>
        </div>
    </body>
</html>
