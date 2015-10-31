import org.codehaus.groovy.grails.plugins.metadata.GrailsPlugin
import org.codehaus.groovy.grails.web.pages.GroovyPage
import org.codehaus.groovy.grails.web.taglib.*
import org.codehaus.groovy.grails.web.taglib.exceptions.GrailsTagException
import org.springframework.web.util.*
import grails.util.GrailsUtil

class gsp_sme_profileshow_gsp extends GroovyPage {
public String getGroovyPageFileName() { "/WEB-INF/grails-app/views/profile/show.gsp" }
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
printHtmlPart(5)
createTagBody(2, {->
printHtmlPart(6)
expressionOut.print(resource(dir: 'images', file: 'arrow_left.png'))
printHtmlPart(7)
invokeTag('message','g',11,['code':("actions.back")],-1)
printHtmlPart(8)
})
invokeTag('link','g',11,['style':("float: left;"),'action':("index"),'params':(['max': params.max, 'offset': params.offset])],2)
printHtmlPart(9)
createTagBody(2, {->
printHtmlPart(10)
expressionOut.print(resource(dir: 'images', file: 'add_record.png'))
printHtmlPart(7)
invokeTag('message','g',12,['code':("actions.profile.add")],-1)
printHtmlPart(8)
})
invokeTag('link','g',12,['action':("add"),'params':(['max': params.max, 'offset': params.offset])],2)
printHtmlPart(11)
expressionOut.print(profileInstance?.name)
printHtmlPart(12)
invokeTag('message','g',16,['code':("genericProfile.code.label")],-1)
printHtmlPart(13)
invokeTag('textField','g',17,['class':("text-input"),'name':("name"),'value':(profileInstance?.code),'readonly':("true")],-1)
printHtmlPart(14)
invokeTag('message','g',19,['code':("genericProfile.name.label")],-1)
printHtmlPart(13)
invokeTag('textField','g',20,['class':("text-input"),'name':("name"),'value':(profileInstance?.name),'readonly':("true")],-1)
printHtmlPart(14)
invokeTag('message','g',22,['code':("default.date.created")],-1)
printHtmlPart(13)
invokeTag('textField','g',23,['class':("text-input"),'name':("name"),'value':(formatDate(format: 'dd/MM/yyyy', date: profileInstance?.dateCreated)),'readonly':("true")],-1)
printHtmlPart(14)
invokeTag('message','g',25,['code':("default.date.lastUpdated")],-1)
printHtmlPart(13)
invokeTag('textField','g',26,['class':("text-input"),'name':("name"),'value':(formatDate(format: 'dd/MM/yyyy', date: profileInstance?.lastUpdated)),'readonly':("true")],-1)
printHtmlPart(15)
invokeTag('message','g',28,['code':("")],-1)
printHtmlPart(16)
createTagBody(2, {->
printHtmlPart(17)
invokeTag('message','g',33,['code':("genericOperation.list")],-1)
printHtmlPart(18)
invokeTag('message','g',36,['code':("genericOperation.active.label")],-1)
printHtmlPart(19)
invokeTag('sortableColumn','g',37,['property':("code"),'title':(message(code: 'genericOperation.code.label')),'titleKey':("genericOperation.code"),'action':("show")],-1)
printHtmlPart(20)
invokeTag('sortableColumn','g',38,['property':("name"),'title':(message(code: 'genericOperation.name.label')),'titleKey':("genericOperation.name")],-1)
printHtmlPart(21)
invokeTag('message','g',39,['code':("genericOperation.inbound.label")],-1)
printHtmlPart(22)
invokeTag('message','g',40,['code':("genericOperation.outbound.label")],-1)
printHtmlPart(19)
invokeTag('sortableColumn','g',41,['property':("accountType"),'title':(message(code: 'genericOperation.accounttype.label')),'titleKey':("genericOperation.accountType.name")],-1)
printHtmlPart(23)
loop:{
int index = 0
for( operationInstance in (operationList) ) {
printHtmlPart(24)
if(true && (new Integer(params.offset) > 0)) {
invokeTag('set','g',46,['var':("offset"),'value':(new Integer(params.offset))],-1)
}
else {
invokeTag('set','g',47,['var':("offset"),'value':(0)],-1)
}
printHtmlPart(25)
expressionOut.print((index % 2) == 0 ? 'even' : 'odd')
printHtmlPart(26)
invokeTag('checkBox','g',50,['name':("checkbox.${operationInstance?.id}"),'value':("")],-1)
printHtmlPart(27)
if(true && (operationInstance?.active)) {
printHtmlPart(28)
}
else {
printHtmlPart(29)
}
printHtmlPart(27)
expressionOut.print(operationInstance?.operation?.code)
printHtmlPart(30)
expressionOut.print(operationInstance?.operation?.name)
printHtmlPart(31)
expressionOut.print(operationInstance?.operation?.inbound ? '+': '-')
printHtmlPart(31)
expressionOut.print(operationInstance?.operation?.outbound ? '+':'-')
printHtmlPart(27)
expressionOut.print(operationInstance?.operation?.accountType?.name)
printHtmlPart(32)
index++
}
}
printHtmlPart(33)
invokeTag('paginate','g',62,['total':(operationsCount ?: 0),'params':(['id': profileInstance?.id])],-1)
printHtmlPart(34)
invokeTag('message','g',64,['code':("actions.login.submit")],-1)
printHtmlPart(35)
})
invokeTag('form','g',65,['name':("updateChb"),'controller':("profile"),'action':("show"),'params':(['update': 1, 'max': params.max, 'offset': params.offset, 'id': profileInstance?.id])],2)
printHtmlPart(36)
invokeTag('message','g',67,['code':("default.application.totalrecords")],-1)
printHtmlPart(37)
expressionOut.print(operationsCount)
printHtmlPart(38)
})
invokeTag('captureBody','sitemesh',70,[:],1)
printHtmlPart(39)
}
public static final Map JSP_TAGS = new HashMap()
protected void init() {
	this.jspTags = JSP_TAGS
}
public static final String CONTENT_TYPE = 'text/html;charset=UTF-8'
public static final long LAST_MODIFIED = 1437557351453L
public static final String EXPRESSION_CODEC = 'html'
public static final String STATIC_CODEC = 'none'
public static final String OUT_CODEC = 'none'
public static final String TAGLIB_CODEC = 'none'
}
