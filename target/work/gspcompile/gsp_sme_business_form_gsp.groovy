import com.sme.entities.Business
import org.codehaus.groovy.grails.plugins.metadata.GrailsPlugin
import org.codehaus.groovy.grails.web.pages.GroovyPage
import org.codehaus.groovy.grails.web.taglib.*
import org.codehaus.groovy.grails.web.taglib.exceptions.GrailsTagException
import org.springframework.web.util.*
import grails.util.GrailsUtil

class gsp_sme_business_form_gsp extends GroovyPage {
public String getGroovyPageFileName() { "/WEB-INF/grails-app/views/business/_form.gsp" }
public Object run() {
Writer out = getOut()
Writer expressionOut = getExpressionOut()
registerSitemeshPreprocessMode()
printHtmlPart(0)
expressionOut.print(hasErrors(bean: businessInstance, field: 'name', 'error'))
printHtmlPart(1)
invokeTag('message','g',7,['code':("business.name.label"),'default':("Name")],-1)
printHtmlPart(2)
invokeTag('textField','g',10,['name':("name"),'required':(""),'value':(businessInstance?.name)],-1)
printHtmlPart(3)
expressionOut.print(hasErrors(bean: businessInstance, field: 'regNumber', 'error'))
printHtmlPart(4)
invokeTag('message','g',16,['code':("business.regNumber.label"),'default':("Reg Number")],-1)
printHtmlPart(2)
invokeTag('textField','g',19,['name':("regNumber"),'required':(""),'value':(businessInstance?.regNumber)],-1)
printHtmlPart(3)
expressionOut.print(hasErrors(bean: businessInstance, field: 'incorpDate', 'error'))
printHtmlPart(5)
invokeTag('message','g',25,['code':("business.incorpDate.label"),'default':("Incorp Date")],-1)
printHtmlPart(6)
invokeTag('datePicker','g',28,['name':("incorpDate"),'precision':("day"),'value':(businessInstance?.incorpDate),'default':("none"),'noSelection':(['': ''])],-1)
printHtmlPart(3)
expressionOut.print(hasErrors(bean: businessInstance, field: 'address', 'error'))
printHtmlPart(7)
invokeTag('message','g',34,['code':("business.address.label"),'default':("Address")],-1)
printHtmlPart(6)
invokeTag('textArea','g',37,['name':("address"),'cols':("40"),'rows':("5"),'maxlength':("1024"),'value':(businessInstance?.address)],-1)
printHtmlPart(3)
expressionOut.print(hasErrors(bean: businessInstance, field: 'city', 'error'))
printHtmlPart(8)
invokeTag('message','g',43,['code':("business.city.label"),'default':("City")],-1)
printHtmlPart(6)
invokeTag('textField','g',46,['name':("city"),'value':(businessInstance?.city)],-1)
printHtmlPart(9)
}
public static final Map JSP_TAGS = new HashMap()
protected void init() {
	this.jspTags = JSP_TAGS
}
public static final String CONTENT_TYPE = 'text/html;charset=UTF-8'
public static final long LAST_MODIFIED = 1432266760398L
public static final String EXPRESSION_CODEC = 'html'
public static final String STATIC_CODEC = 'none'
public static final String OUT_CODEC = 'none'
public static final String TAGLIB_CODEC = 'none'
}
