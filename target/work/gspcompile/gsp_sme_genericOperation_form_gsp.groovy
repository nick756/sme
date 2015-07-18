import com.sme.entities.GenericOperation
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
expressionOut.print(hasErrors(bean: genericOperationInstance, field: 'code', 'error'))
printHtmlPart(1)
invokeTag('message','g',7,['code':("genericOperation.code.label"),'default':("Code")],-1)
printHtmlPart(2)
invokeTag('field','g',10,['name':("code"),'type':("number"),'value':(genericOperationInstance.code),'required':("")],-1)
printHtmlPart(3)
expressionOut.print(hasErrors(bean: genericOperationInstance, field: 'name', 'error'))
printHtmlPart(4)
invokeTag('message','g',16,['code':("genericOperation.name.label"),'default':("Name")],-1)
printHtmlPart(2)
invokeTag('textField','g',19,['name':("name"),'required':(""),'value':(genericOperationInstance?.name)],-1)
printHtmlPart(3)
expressionOut.print(hasErrors(bean: genericOperationInstance, field: 'accountType', 'error'))
printHtmlPart(5)
invokeTag('message','g',25,['code':("genericOperation.accountType.label"),'default':("Account Type")],-1)
printHtmlPart(2)
invokeTag('select','g',28,['id':("accountType"),'name':("accountType.id"),'from':(com.sme.entities.AccountType.list()),'optionKey':("id"),'required':(""),'value':(genericOperationInstance?.accountType?.id),'class':("many-to-one")],-1)
printHtmlPart(3)
expressionOut.print(hasErrors(bean: genericOperationInstance, field: 'profiles', 'error'))
printHtmlPart(6)
invokeTag('message','g',34,['code':("genericOperation.profiles.label"),'default':("Profiles")],-1)
printHtmlPart(7)
for( p in (genericOperationInstance?.profiles) ) {
printHtmlPart(8)
createTagBody(2, {->
expressionOut.print(p?.encodeAsHTML())
})
invokeTag('link','g',40,['controller':("profileLink"),'action':("show"),'id':(p.id)],2)
printHtmlPart(9)
}
printHtmlPart(10)
createTagBody(1, {->
expressionOut.print(message(code: 'default.add.label', args: [message(code: 'profileLink.label', default: 'ProfileLink')]))
})
invokeTag('link','g',43,['controller':("profileLink"),'action':("create"),'params':(['genericOperation.id': genericOperationInstance?.id])],1)
printHtmlPart(11)
expressionOut.print(hasErrors(bean: genericOperationInstance, field: 'inbound', 'error'))
printHtmlPart(12)
invokeTag('message','g',52,['code':("genericOperation.inbound.label"),'default':("Inbound")],-1)
printHtmlPart(13)
invokeTag('checkBox','g',55,['name':("inbound"),'value':(genericOperationInstance?.inbound)],-1)
printHtmlPart(3)
expressionOut.print(hasErrors(bean: genericOperationInstance, field: 'outbound', 'error'))
printHtmlPart(14)
invokeTag('message','g',61,['code':("genericOperation.outbound.label"),'default':("Outbound")],-1)
printHtmlPart(13)
invokeTag('checkBox','g',64,['name':("outbound"),'value':(genericOperationInstance?.outbound)],-1)
printHtmlPart(15)
}
public static final Map JSP_TAGS = new HashMap()
protected void init() {
	this.jspTags = JSP_TAGS
}
public static final String CONTENT_TYPE = 'text/html;charset=UTF-8'
public static final long LAST_MODIFIED = 1434512014061L
public static final String EXPRESSION_CODEC = 'html'
public static final String STATIC_CODEC = 'none'
public static final String OUT_CODEC = 'none'
public static final String TAGLIB_CODEC = 'none'
}
