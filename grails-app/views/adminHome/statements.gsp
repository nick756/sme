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
        
        <g:if test="${errMessage}">
            <div style="display: block; width: 100%; color: #b00000; font-size: 14pt;">${errMessage}</div>
        </g:if>
        
        <table class="righted-content">
            <caption><g:message code="cfstatement.page.label"/></caption>
            <tr>
                <th><g:message code="cfstatement.year.label"/></th>
                <g:sortableColumn property="month" title="${message(code: 'cfstatement.month.label')}" />
                <th><g:message code="cfstatement.openingbalance.label"/></th>
                <th><g:message code="cfstatement.inboundamount.label"/></th>
                <th><g:message code="cfstatement.outboundamount.label"/></th>
                <th><g:message code="cfstatement.resultamount.label"/></th>
                <th><g:message code="cfstatement.cumulativeamount.label"/></th>
                <th></th>
            </tr> 

            <g:each status="index" in="${statementList}" var="item">
                
                <g:if test="${new Integer(params.offset) > 0}">
                    <g:set var="offset" value="${new Integer(params.offset)}"/>
                </g:if>
                <g:else><g:set var="offset" value="${0}"/></g:else>

                <tr class="${(index % 2) == 0 ? 'even' : 'odd'}">
                    <td class="no-border centered">${item?.year}</td>
                    <td class="no-border centered">${item?.month}</td>
                    <td class="no-border righted"><g:formatNumber number="${item?.openingBalance}" format="#,##0.00" /></td>
                    <td class="no-border righted"><g:formatNumber number="${item?.inflow}" format="#,##0.00" /></td>
                    <td class="no-border righted"><g:formatNumber number="${item?.outflow}" format="#,##0.00" /></td>
                    
                    <g:if test="${item?.nettAmount >= 0}">
                        <td class="no-border righted">
                    </g:if>
                    <g:else>
                        <td class="no-border righted" style="color: #fa0000;">
                    </g:else>
                    
                    <g:formatNumber number="${item?.nettAmount}" format="#,##0.00" /></td>
                    <td class="righted"><g:formatNumber number="${item?.cumulativeAmount}" format="#,##0.00" /></td>
                    <td class="no-border centered">
                        <g:link  target="_blank" action="statementdetails" params="['max': params.max, 'offset': params.offset, 'id': item?.id, 'businessId': businessInstance?.id]">
                            <img class="image-link" src="${resource(dir: 'images', file: 'details.png')}" title="<g:message code='actions.back'/>"/>
                        </g:link>
                    </td>
                </tr>
                
            </g:each>
                
        </table>
    </body>
</html>
