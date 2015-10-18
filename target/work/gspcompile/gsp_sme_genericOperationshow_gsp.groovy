import com.sme.entities.GenericOperation
import org.codehaus.groovy.grails.plugins.metadata.GrailsPlugin
import org.codehaus.groovy.grails.web.pages.GroovyPage
import org.codehaus.groovy.grails.web.taglib.*
import org.codehaus.groovy.grails.web.taglib.exceptions.GrailsTagException
import org.springframework.web.util.*
import grails.util.GrailsUtil

class gsp_sme_genericOperationshow_gsp extends GroovyPage {
public String getGroovyPageFileName() { "/WEB-INF/grails-app/views/genericOperation/show.gsp" }
public Object run() {
Writer out = getOut()
Writer expressionOut = getExpressionOut()
registerSitemeshPreprocessMode()
printHtmlPart(0)
createTagBody(1, {->
printHtmlPart(1)
invokeTag('captureMeta','sitemesh',4,['gsp_sm_xmlClosingForEmptyTag':(""),'name':("layout"),'content':("adminpage")],-1)
printHtmlPart(1)
createTagBody(2, {->
createClosureForHtmlPart(2, 3)
invokeTag('captureTitle','sitemesh',5,[:],3)
})
invokeTag('wrapTitleTag','sitemesh',5,[:],2)
printHtmlPart(3)
})
invokeTag('captureHead','sitemesh',6,[:],1)
printHtmlPart(3)
createTagBody(1, {->
printHtmlPart(4)
invokeTag('message','g',8,['code':("default.link.skip.label"),'default':("Skip to content&hellip;")],-1)
printHtmlPart(5)
createTagBody(2, {->
invokeTag('message','g',11,['code':("genericOperation.list")],-1)
})
invokeTag('link','g',11,['class':("home"),'action':("index")],2)
printHtmlPart(6)
createTagBody(2, {->
invokeTag('message','g',12,['code':("default.new.label"),'args':([entityName])],-1)
})
invokeTag('link','g',12,['class':("create"),'action':("create")],2)
printHtmlPart(7)
expressionOut.print(genericOperationInstance?.name)
printHtmlPart(8)
invokeTag('message','g',18,['code':("genericOperation.code.label")],-1)
printHtmlPart(9)
invokeTag('textField','g',19,['class':("text-input"),'name':("name"),'value':(genericOperationInstance?.code)],-1)
printHtmlPart(10)
invokeTag('message','g',21,['code':("genericOperation.name.label")],-1)
printHtmlPart(11)
invokeTag('textField','g',22,['class':("text-input"),'name':("name"),'value':(genericOperationInstance?.name)],-1)
printHtmlPart(12)
invokeTag('message','g',24,['code':("genericOperation.name.label")],-1)
printHtmlPart(13)
invokeTag('textField','g',25,['class':("text-input"),'name':("name"),'value':(genericOperationInstance?.name_EN)],-1)
printHtmlPart(10)
invokeTag('message','g',27,['code':("genericOperation.accounttype.label")],-1)
printHtmlPart(9)
invokeTag('textField','g',28,['class':("text-input"),'name':("name"),'value':(genericOperationInstance?.accountType?.name)],-1)
printHtmlPart(12)
invokeTag('message','g',30,['code':("genericOperation.inbound.label")],-1)
printHtmlPart(9)
invokeTag('textField','g',31,['class':("text-input"),'name':("name"),'value':(genericOperationInstance?.inbound)],-1)
printHtmlPart(12)
invokeTag('message','g',33,['code':("genericOperation.outbound.label")],-1)
printHtmlPart(9)
invokeTag('textField','g',34,['class':("text-input"),'name':("name"),'value':(genericOperationInstance?.outbound)],-1)
printHtmlPart(14)
invokeTag('message','g',36,['code':("genericOperation.active.label")],-1)
printHtmlPart(15)
if(true && (genericOperationInstance?.actual > 0)) {
printHtmlPart(16)
invokeTag('set','g',39,['var':("visible"),'value':(true)],-1)
printHtmlPart(17)
}
else {
printHtmlPart(16)
invokeTag('set','g',42,['var':("visible"),'value':(false)],-1)
printHtmlPart(17)
}
printHtmlPart(17)
invokeTag('textField','g',44,['class':("text-input"),'name':("name"),'value':(visible)],-1)
printHtmlPart(18)
createTagBody(2, {->
printHtmlPart(19)
createTagBody(3, {->
invokeTag('message','g',52,['code':("default.button.edit.label"),'default':("Edit")],-1)
})
invokeTag('link','g',52,['class':("edit"),'action':("edit"),'resource':(genericOperationInstance)],3)
printHtmlPart(16)
invokeTag('actionSubmit','g',53,['class':("delete"),'action':("delete"),'value':(message(code: 'default.button.delete.label', default: 'Delete')),'onclick':("return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');")],-1)
printHtmlPart(20)
})
invokeTag('form','g',55,['url':([resource:genericOperationInstance, action:'delete']),'method':("DELETE")],2)
printHtmlPart(21)
})
invokeTag('captureBody','sitemesh',57,[:],1)
printHtmlPart(22)
}
public static final Map JSP_TAGS = new HashMap()
protected void init() {
	this.jspTags = JSP_TAGS
}
public static final String CONTENT_TYPE = 'text/html;charset=UTF-8'
public static final long LAST_MODIFIED = 1444486348160L
public static final String EXPRESSION_CODEC = 'html'
public static final String STATIC_CODEC = 'none'
public static final String OUT_CODEC = 'html'
public static final String TAGLIB_CODEC = 'none'
}
