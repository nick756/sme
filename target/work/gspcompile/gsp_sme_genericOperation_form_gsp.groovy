import com.sme.entities.*
import org.codehaus.groovy.grails.plugins.metadata.GrailsPlugin
import org.codehaus.groovy.grails.web.pages.GroovyPage
import org.codehaus.groovy.grails.web.taglib.*
import org.codehaus.groovy.grails.web.taglib.exceptions.GrailsTagException
import org.springframework.web.util.*
import grails.util.GrailsUtil

class gsp_sme_genericOperation_form_gsp extends GroovyPage {
public String getGroovyPageFileName() { "/WEB-INF/grails-app/views/genericOperation/_form.gsp" }
public Object run() {
Writer out = getOut()
Writer expressionOut = getExpressionOut()
registerSitemeshPreprocessMode()
printHtmlPart(0)
invokeTag('message','g',4,['code':("genericOperation.code.label")],-1)
printHtmlPart(1)
invokeTag('textField','g',5,['class':("text-input"),'name':("name"),'value':(genericOperationInstance?.code),'disabled':("true")],-1)
printHtmlPart(2)
invokeTag('message','g',7,['code':("genericOperation.name.label")],-1)
printHtmlPart(3)
invokeTag('textField','g',8,['class':("text-input"),'name':("name"),'value':(genericOperationInstance?.name)],-1)
printHtmlPart(4)
invokeTag('message','g',10,['code':("genericOperation.name.label")],-1)
printHtmlPart(5)
invokeTag('textField','g',11,['class':("text-input"),'name':("name_EN"),'value':(genericOperationInstance?.name_EN)],-1)
printHtmlPart(4)
invokeTag('message','g',13,['code':("genericOperation.accounttype.label")],-1)
printHtmlPart(1)
invokeTag('select','g',14,['class':("select-list"),'name':("accountType.id"),'from':(AccountType.list()),'value':(genericOperationInstance?.accountType?.id),'optionKey':("id")],-1)
printHtmlPart(4)
invokeTag('message','g',16,['code':("genericOperation.inbound.label")],-1)
printHtmlPart(1)
invokeTag('textField','g',17,['class':("text-input"),'name':("name"),'value':(genericOperationInstance?.inbound),'disabled':("true")],-1)
printHtmlPart(4)
invokeTag('message','g',19,['code':("genericOperation.outbound.label")],-1)
printHtmlPart(1)
invokeTag('textField','g',20,['class':("text-input"),'name':("name"),'value':(genericOperationInstance?.outbound),'disabled':("true")],-1)
printHtmlPart(6)
invokeTag('message','g',22,['code':("genericOperation.active.label")],-1)
printHtmlPart(7)
if(true && (genericOperationInstance?.actual > 0)) {
printHtmlPart(8)
invokeTag('set','g',25,['var':("visible"),'value':(true)],-1)
printHtmlPart(8)
}
else {
printHtmlPart(9)
invokeTag('set','g',28,['var':("visible"),'value':(false)],-1)
printHtmlPart(8)
}
printHtmlPart(8)
invokeTag('textField','g',30,['class':("text-input"),'name':("name"),'value':(visible),'disabled':("true")],-1)
printHtmlPart(10)
}
public static final Map JSP_TAGS = new HashMap()
protected void init() {
	this.jspTags = JSP_TAGS
}
public static final String CONTENT_TYPE = 'text/html;charset=UTF-8'
public static final long LAST_MODIFIED = 1444227235937L
public static final String EXPRESSION_CODEC = 'html'
public static final String STATIC_CODEC = 'none'
public static final String OUT_CODEC = 'none'
public static final String TAGLIB_CODEC = 'none'
}
