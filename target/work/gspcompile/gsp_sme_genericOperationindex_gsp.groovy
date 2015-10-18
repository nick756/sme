import com.sme.entities.GenericOperation
import org.codehaus.groovy.grails.plugins.metadata.GrailsPlugin
import org.codehaus.groovy.grails.web.pages.GroovyPage
import org.codehaus.groovy.grails.web.taglib.*
import org.codehaus.groovy.grails.web.taglib.exceptions.GrailsTagException
import org.springframework.web.util.*
import grails.util.GrailsUtil

class gsp_sme_genericOperationindex_gsp extends GroovyPage {
public String getGroovyPageFileName() { "/WEB-INF/grails-app/views/genericOperation/index.gsp" }
public Object run() {
Writer out = getOut()
Writer expressionOut = getExpressionOut()
registerSitemeshPreprocessMode()
printHtmlPart(0)
printHtmlPart(1)
createTagBody(1, {->
printHtmlPart(2)
invokeTag('captureMeta','sitemesh',6,['gsp_sm_xmlClosingForEmptyTag':("/"),'name':("layout"),'content':("adminpage")],-1)
printHtmlPart(2)
createTagBody(2, {->
createClosureForHtmlPart(3, 3)
invokeTag('captureTitle','sitemesh',7,[:],3)
})
invokeTag('wrapTitleTag','sitemesh',7,[:],2)
printHtmlPart(4)
})
invokeTag('captureHead','sitemesh',8,[:],1)
printHtmlPart(4)
createTagBody(1, {->
printHtmlPart(2)
if(true && (flash.message)) {
printHtmlPart(5)
expressionOut.print(flash.message)
printHtmlPart(6)
}
printHtmlPart(7)
invokeTag('message','g',14,['code':("genericOperation.list")],-1)
printHtmlPart(8)
invokeTag('sortableColumn','g',17,['property':("code"),'title':(message(code: 'genericOperation.code.label', default: 'Code'))],-1)
printHtmlPart(9)
invokeTag('sortableColumn','g',19,['property':("name"),'title':(message(code: 'genericOperation.name.label', default: 'Name'))],-1)
printHtmlPart(10)
invokeTag('message','g',21,['code':("genericOperation.accountType.label"),'default':("Account Type")],-1)
printHtmlPart(11)
invokeTag('sortableColumn','g',23,['property':("inbound"),'title':(message(code: 'genericOperation.inbound.label', default: 'Inbound'))],-1)
printHtmlPart(12)
invokeTag('sortableColumn','g',24,['property':("outbound"),'title':(message(code: 'genericOperation.outbound.label', default: 'Outbound'))],-1)
printHtmlPart(13)
loop:{
int i = 0
for( genericOperationInstance in (genericOperationInstanceList) ) {
printHtmlPart(14)
expressionOut.print((i % 2) == 0 ? 'even' : 'odd')
printHtmlPart(15)
createTagBody(3, {->
expressionOut.print(genericOperationInstance.code)
})
invokeTag('link','g',30,['action':("show"),'id':(genericOperationInstance.id)],3)
printHtmlPart(16)
createTagBody(3, {->
expressionOut.print(genericOperationInstance)
})
invokeTag('link','g',32,['action':("show"),'id':(genericOperationInstance.id)],3)
printHtmlPart(16)
expressionOut.print(genericOperationInstance.accountType?.name)
printHtmlPart(16)
invokeTag('formatBoolean','g',36,['boolean':(genericOperationInstance.inbound)],-1)
printHtmlPart(17)
invokeTag('formatBoolean','g',37,['boolean':(genericOperationInstance.outbound)],-1)
printHtmlPart(18)
i++
}
}
printHtmlPart(19)
invokeTag('paginate','g',43,['total':(genericOperationInstanceCount ?: 0)],-1)
printHtmlPart(20)
})
invokeTag('captureBody','sitemesh',45,[:],1)
printHtmlPart(21)
}
public static final Map JSP_TAGS = new HashMap()
protected void init() {
	this.jspTags = JSP_TAGS
}
public static final String CONTENT_TYPE = 'text/html;charset=UTF-8'
public static final long LAST_MODIFIED = 1444486538692L
public static final String EXPRESSION_CODEC = 'html'
public static final String STATIC_CODEC = 'none'
public static final String OUT_CODEC = 'html'
public static final String TAGLIB_CODEC = 'none'
}
