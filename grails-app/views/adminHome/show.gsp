<%@ page contentType="text/html;charset=UTF-8" %>

<html>
    <head>
        <meta name="layout" content="adminpage"/>
        <title>SME</title>
        
  <script type="text/javascript">
      ${jsOut}
       
  window.onload = function () {
    var chart = new CanvasJS.Chart("chartContainer",
    {
      title:{
        text: "Usage Statistics"
      },
      
      data: [
        {
          type: "line",
          color: "#454545",
          dataPoints: points
        }
      ]
    });

    chart.render();
  }
  </script>        
        
    </head>
    <body>
        <div style="width: 100%; background: #D0E8F4; display: inline-block; text-align: right; border-bottom: 0px solid #6285C7; border-top: 0px solid #6285C7; padding: 5px 0;">
            <g:link style="float: left;" action="index" params="['max': params.max, 'offset': params.offset]"><img class="image-link" style="margin-left: 5px;" src="${resource(dir: 'images', file: 'arrow_left.png')}" title="<g:message code='actions.back'/>"/></g:link>
            <g:link action="edit" params="['max': params.max, 'offset': params.offset, 'id': businessInstance?.id]"><img class="image-link" src="${resource(dir: 'images', file: 'edit.png')}" title="<g:message code='actions.edit'/>"/></g:link>
            <%-- <g:link action="index" params="['max': params.max, 'offset': params.offset]"><img class="image-link" src="${resource(dir: 'images', file: 'employee.png')}" title="<g:message code='actions.members'/>"/></g:link> --%>
            <% /* g:link action="index" params="['max': params.max, 'offset': params.offset]"><img class="image-link" src="${resource(dir: 'images', file: 'list.png')}" title="<g:message code='actions.profile'/>"/></g:link */ %>
            <g:link action="listtransactions" params="['max': params.max, 'offset': 0, 'id': businessInstance?.id]"><img class="image-link" style="margin-right: 5px;" src="${resource(dir: 'images', file: 'calculator.png')}" title="<g:message code='actions.transactions'/>"/></g:link>
            </div>

        <h1 class="sub-title">${businessInstance?.name}</h1>
        <div class="edit-form-box">

            <label class="edit-form"><g:message code="business.name.label"/></label>
            <g:textField class="text-input" name="name" value="${businessInstance?.name}" readonly="true"/>
            <br/>
            <label class="edit-form"><g:message code="business.industry.label" default="No"/></label>
            <g:textField class="text-input" name="regNumber" value="${businessInstance?.industry?.name}" readonly="true"/>
            <br/>               
            <label class="edit-form"><g:message code="business.profile.label"/></label>
            <g:textField class="text-input" name="name" value="${businessInstance?.profile?.name}" readonly="true"/>
            <br/><br/>
            <label class="edit-form"><g:message code="business.bank.label"/></label>
            <g:textField class="text-input" name="bank?.id" value="${businessInstance?.bank?.name}" readonly="true"/>
            <br/>            
            <label class="edit-form"><g:message code="business.accountno.label" default="Account"/></label>
            <g:textField class="text-input" name="regNumber" value="${businessInstance?.accountNo}" readonly="true"/>
            <br/>
            <label class="edit-form"><g:message code="business.regNumber.label" default="No"/></label>
            <g:textField class="text-input" name="regNumber" value="${businessInstance?.regNumber}" readonly="true"/>
            <br/>
            <label class="edit-form"><g:message code="business.registrationdate.label" default="Registration Date"/></label>
            <g:textField class="text-input" name="regNumber" value="${formatDate(format:'dd/MM/yyyy', date:businessInstance?.registrationDate)}" readonly="true"/>
            <br/>  
            <label class="edit-form"><g:message code="business.incorpDate.label" default="Incorp Date"/></label>
            <g:textField class="text-input" name="regNumber" value="${formatDate(format:'dd/MM/yyyy', date:businessInstance?.incorpDate)}" readonly="true"/>
            <br/>  
            <label class="edit-form"><g:message code="business.address.label" default="Address"/></label>
            <g:textArea class="text-input" name="address" value="${businessInstance?.address}" readonly="true"/>
            <br/>
            <label class="edit-form"><g:message code="business.city.label" default="City"/></label>
            <g:textField class="text-input" name="address" value="${businessInstance?.city}" readonly="true"/>                   

        </div>
        
        <div id="chartContainer" style="height: 300px; width: 100%;"></div>
  
    </body>
</html>
