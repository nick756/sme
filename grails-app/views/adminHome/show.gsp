<%@ page contentType="text/html;charset=UTF-8" %>

<html>
    <head>
        <meta name="layout" content="adminpage"/>
        <title>SME</title>
  <script type="text/javascript">
      var points = ${raw(jsOut)};
      var titleTxt = "Usage Statistics for ${businessInstance?.name}";
      titleTxt = titleTxt.replace('&amp;', '&');
      titleTxt = titleTxt.replace('&#39;', "'");
       
  window.onload = function () {
    var chart = new CanvasJS.Chart("chartContainer",
    {
      title:{
        text: titleTxt
      },
      
      axisX: {
        title: "Period YY/MM"
      },
      axisY: {
        title: "Transactions"
      },
      
      data: [
        {
          type: "column",
          color: "#526c85",
          fontWeight: "normal",
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
            <div id="chartContainer" style="height: 300px; width: 100%;"></div>
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
            <br/>
            
            <label class="edit-form"><g:message code="business.contactPerson1.label" default="City"/></label>
            <g:textField class="text-input" name="contactPerson1" value="${businessInstance?.contactPerson1}" readonly="true"/>                   
            <br/>
            <label class="edit-form"><g:message code="business.contactNumber1.label"/></label>
            <g:textField class="text-input" name="contactNumber1" value="${businessInstance?.contactNumber1}" readonly="true"/>                   
            <br/>
            <label class="edit-form"><g:message code="business.contactPerson2.label"/></label>
            <g:textField class="text-input" name="contactPerson2" value="${businessInstance?.contactPerson2}" readonly="true"/>                   
            <br/>
            <label class="edit-form"><g:message code="business.contactNumber2.label"/></label>
            <g:textField class="text-input" name="contactNumber2" value="${businessInstance?.contactNumber2}" readonly="true"/>                   
            <br/>
            
            <fieldset style="border: 1px solid #AFAFAF; margin-bottom: 5px; padding-top: 5px; background: #EFEFEF;">
                <legend style="border: 1px solid #526C85; padding: 2px 8px; background: #526C85; color: #FFF;"><g:message code="messages.fieldset.billing.label"/></legend>
                <br/>
                <label class="edit-form"><g:message code="business.billingType.label"/></label>
                <g:textField class="text-input" name="billingType?.id" value="${businessInstance?.billingType}" readonly="true"/>
                <br/>
                <label class="edit-form"><g:message code="business.freeServices.label"/></label>
                <g:textField name="freeServices" value="${businessInstance?.freeServices ? 'Yes / Ya' : 'No / Tidak'}" readonly="true"/>
                <br/>
                
                <label class="edit-form"><g:message code="business.activated.label"/></label>
                <g:textField name="activated" value="${businessInstance?.activated ? 'Yes / Ya' : 'No / Tidak'}" readonly="true"/>
                <br/>                
                <label class="edit-form"><g:message code="business.activationDate.label"/></label>
                <g:textField class="text-input" name="activationDate" value="${formatDate(format: 'dd/MM/yyyy', date: businessInstance?.activationDate)}" readonly="true"/>
                <br/>                
                
                <label class="edit-form"><g:message code="business.startBillingDate.label"/></label>
                <g:textField class="text-input" name="startBillingDate" value="${formatDate(format: 'dd/MM/yyyy', date: businessInstance?.startBillingDate)}" readonly="true"/>
                <br/>
                <label class="edit-form"><g:message code="business.rate.label"/></label>
                <g:textField class="text-input" name="rate" value="${formatNumber(format: '#,##0.00', number: businessInstance?.rate)}" readonly="true"/>
                <br/>
                <label class="edit-form"><g:message code="billingType.trialPeriod.label"/></label>
                <g:textField class="text-input" name="trialPeriod" value="${businessInstance?.billingType?.trialPeriod}" readonly="true"/>                 
                <br/>
                <label class="edit-form"><g:message code="business.gracePeriod.label"/></label>
                <g:textField class="text-input" name="gracePeriod" value="${businessInstance?.gracePeriod}" readonly="true"/>                
            </fieldset>
        </div>
    </body>
</html>
