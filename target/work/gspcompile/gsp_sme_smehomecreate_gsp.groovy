import com.sme.entities.*
import org.codehaus.groovy.grails.plugins.metadata.GrailsPlugin
import org.codehaus.groovy.grails.web.pages.GroovyPage
import org.codehaus.groovy.grails.web.taglib.*
import org.codehaus.groovy.grails.web.taglib.exceptions.GrailsTagException
import org.springframework.web.util.*
import grails.util.GrailsUtil

class gsp_sme_smehomecreate_gsp extends GroovyPage {
public String getGroovyPageFileName() { "/WEB-INF/grails-app/views/smehome/create.gsp" }
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
invokeTag('message','g',12,['code':("actions.transaction.add")],-1)
printHtmlPart(8)
})
invokeTag('link','g',12,['style':("float: left; margin-left: 5px;"),'action':("index"),'params':(['max': params.max, 'offset': params.offset, 'id': businessInstance?.id])],2)
printHtmlPart(9)
createTagBody(2, {->
printHtmlPart(10)
invokeTag('hiddenField','g',17,['name':("operator"),'value':(session?.user.name)],-1)
printHtmlPart(10)
invokeTag('hiddenField','g',18,['name':("company.id"),'value':(session?.company.id)],-1)
printHtmlPart(11)
invokeTag('message','g',20,['code':("businesstransaction.type.label")],-1)
printHtmlPart(12)
invokeTag('select','g',21,['class':("select-list"),'name':("operationType.id"),'from':(GenericOperation.list()),'optionKey':("id")],-1)
printHtmlPart(13)
invokeTag('message','g',23,['code':("businesstransaction.remarks.label")],-1)
printHtmlPart(12)
invokeTag('textField','g',24,['class':("text-input"),'name':("transactionRemarks"),'value':(businessTransactionInstance?.transactionRemarks)],-1)
printHtmlPart(14)
invokeTag('message','g',26,['code':("businesstransaction.amount.label")],-1)
printHtmlPart(12)
invokeTag('textField','g',27,['class':("text-input"),'name':("transactionAmount"),'value':(businessTransactionInstance?.transactionAmount)],-1)
printHtmlPart(13)
invokeTag('message','g',29,['code':("businesstransaction.date.label"),'default':("Incorp Date")],-1)
printHtmlPart(12)
invokeTag('datePicker','g',30,['class':("select-list"),'name':("transactionDate"),'value':(businessTransactionInstance?.transactionDate),'precision':("day")],-1)
printHtmlPart(15)
createTagBody(3, {->
printHtmlPart(16)
expressionOut.print(it.label)
printHtmlPart(17)
expressionOut.print(it.radio)
printHtmlPart(18)
})
invokeTag('radioGroup','g',37,['name':("cash"),'labels':(['Cash', 'Bank/Cheque']),'values':([1, 0]),'value':("0")],3)
printHtmlPart(19)
invokeTag('message','g',39,['code':("actions.login.submit")],-1)
printHtmlPart(20)
})
invokeTag('form','g',40,['action':("save"),'method':("POST")],2)
printHtmlPart(21)
})
invokeTag('captureBody','sitemesh',42,[:],1)
printHtmlPart(22)
}
public static final Map JSP_TAGS = new HashMap()
protected void init() {
	this.jspTags = JSP_TAGS
}
public static final String CONTENT_TYPE = 'text/html;charset=UTF-8'
public static final long LAST_MODIFIED = 1443936597958L
public static final String EXPRESSION_CODEC = 'html'
public static final String STATIC_CODEC = 'none'
public static final String OUT_CODEC = 'html'
public static final String TAGLIB_CODEC = 'none'
}
