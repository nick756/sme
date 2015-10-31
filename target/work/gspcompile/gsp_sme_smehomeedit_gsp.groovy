import com.sme.entities.*
import org.codehaus.groovy.grails.plugins.metadata.GrailsPlugin
import org.codehaus.groovy.grails.web.pages.GroovyPage
import org.codehaus.groovy.grails.web.taglib.*
import org.codehaus.groovy.grails.web.taglib.exceptions.GrailsTagException
import org.springframework.web.util.*
import grails.util.GrailsUtil

class gsp_sme_smehomeedit_gsp extends GroovyPage {
public String getGroovyPageFileName() { "/WEB-INF/grails-app/views/smehome/edit.gsp" }
public Object run() {
Writer out = getOut()
Writer expressionOut = getExpressionOut()
registerSitemeshPreprocessMode()
printHtmlPart(0)
printHtmlPart(1)
createTagBody(1, {->
printHtmlPart(2)
invokeTag('captureMeta','sitemesh',6,['gsp_sm_xmlClosingForEmptyTag':(""),'http-equiv':("Content-Type"),'content':("text/html; charset=UTF-8")],-1)
printHtmlPart(2)
invokeTag('captureMeta','sitemesh',7,['gsp_sm_xmlClosingForEmptyTag':("/"),'name':("layout"),'content':("smepage")],-1)
printHtmlPart(2)
createTagBody(2, {->
createClosureForHtmlPart(3, 3)
invokeTag('captureTitle','sitemesh',8,[:],3)
})
invokeTag('wrapTitleTag','sitemesh',8,[:],2)
printHtmlPart(4)
})
invokeTag('captureHead','sitemesh',9,[:],1)
printHtmlPart(4)
createTagBody(1, {->
printHtmlPart(5)
createTagBody(2, {->
printHtmlPart(6)
expressionOut.print(resource(dir: 'images', file: 'arrow_left.png'))
printHtmlPart(7)
invokeTag('message','g',12,['code':("actions.back")],-1)
printHtmlPart(8)
})
invokeTag('link','g',12,['style':("float: left; margin-left: 5px;"),'action':("index"),'params':(['max': params.max, 'offset': params.offset, 'id': businessInstance?.id])],2)
printHtmlPart(9)
createTagBody(2, {->
printHtmlPart(10)
createTagBody(3, {->
printHtmlPart(11)
if(true && (error in org.springframework.validation.FieldError)) {
printHtmlPart(12)
expressionOut.print(error.field)
printHtmlPart(13)
}
printHtmlPart(14)
invokeTag('message','g',20,['error':(error)],-1)
printHtmlPart(15)
})
invokeTag('eachError','g',21,['bean':(businessTransactionInstance),'var':("error")],3)
printHtmlPart(16)
})
invokeTag('hasErrors','g',23,['bean':(businessTransactionInstance)],2)
printHtmlPart(17)
createTagBody(2, {->
printHtmlPart(18)
invokeTag('hiddenField','g',26,['name':("operator"),'value':(session?.user.name)],-1)
printHtmlPart(18)
invokeTag('hiddenField','g',27,['name':("company.id"),'value':(session?.company.id)],-1)
printHtmlPart(18)
invokeTag('hiddenField','g',28,['name':("id"),'value':(businessTransactionInstance.id)],-1)
printHtmlPart(18)
invokeTag('hiddenField','g',29,['name':("version"),'value':(businessTransactionInstance?.version)],-1)
printHtmlPart(19)
invokeTag('message','g',31,['code':("businesstransaction.type.label")],-1)
printHtmlPart(20)
invokeTag('select','g',32,['class':("select-list"),'name':("operationType.id"),'from':(GenericOperation.list()),'value':(businessTransactionInstance?.operationType.id),'optionKey':("id")],-1)
printHtmlPart(21)
invokeTag('message','g',34,['code':("businesstransaction.remarks.label")],-1)
printHtmlPart(20)
invokeTag('textField','g',35,['class':("text-input"),'name':("transactionRemarks"),'value':(businessTransactionInstance?.transactionRemarks)],-1)
printHtmlPart(22)
invokeTag('message','g',37,['code':("businesstransaction.date.label")],-1)
printHtmlPart(20)
invokeTag('datePicker','g',38,['class':("text-input"),'name':("transactionDate"),'value':(businessTransactionInstance?.transactionDate),'precision':("day")],-1)
printHtmlPart(23)
invokeTag('message','g',40,['code':("businesstransaction.amount.label")],-1)
printHtmlPart(20)
invokeTag('textField','g',41,['class':("text-input"),'name':("transactionAmount"),'value':(businessTransactionInstance?.transactionAmount)],-1)
printHtmlPart(23)
invokeTag('message','g',43,['code':("businesstransaction.paymentmode.label")],-1)
printHtmlPart(20)
createTagBody(3, {->
printHtmlPart(24)
expressionOut.print(it.label)
printHtmlPart(25)
expressionOut.print(it.radio)
printHtmlPart(26)
})
invokeTag('radioGroup','g',46,['name':("cash"),'labels':(radioLabels),'values':([1, 0]),'value':("0")],3)
printHtmlPart(27)
invokeTag('message','g',49,['code':("actions.login.submit")],-1)
printHtmlPart(28)
invokeTag('actionSubmit','g',50,['class':("myButton"),'action':("updatetransaction"),'value':(message(code: 'default.button.update.label', default: 'Update'))],-1)
printHtmlPart(29)
})
invokeTag('form','g',51,['action':("updatetransaction"),'method':("PUT"),'params':(['offset': params.offset])],2)
printHtmlPart(30)
})
invokeTag('captureBody','sitemesh',54,[:],1)
printHtmlPart(31)
}
public static final Map JSP_TAGS = new HashMap()
protected void init() {
	this.jspTags = JSP_TAGS
}
public static final String CONTENT_TYPE = 'text/html;charset=UTF-8'
public static final long LAST_MODIFIED = 1444804522136L
public static final String EXPRESSION_CODEC = 'html'
public static final String STATIC_CODEC = 'none'
public static final String OUT_CODEC = 'none'
public static final String TAGLIB_CODEC = 'none'
}
