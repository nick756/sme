import org.codehaus.groovy.grails.plugins.metadata.GrailsPlugin
import org.codehaus.groovy.grails.web.pages.GroovyPage
import org.codehaus.groovy.grails.web.taglib.*
import org.codehaus.groovy.grails.web.taglib.exceptions.GrailsTagException
import org.springframework.web.util.*
import grails.util.GrailsUtil

class gsp_sme_adminHomeindex_gsp extends GroovyPage {
public String getGroovyPageFileName() { "/WEB-INF/grails-app/views/adminHome/index.gsp" }
public Object run() {
Writer out = getOut()
Writer expressionOut = getExpressionOut()
registerSitemeshPreprocessMode()
printHtmlPart(0)
createTagBody(1, {->
printHtmlPart(1)
invokeTag('captureMeta','sitemesh',5,['gsp_sm_xmlClosingForEmptyTag':("/"),'name':("layout"),'content':("adminpage")],-1)
printHtmlPart(1)
createTagBody(2, {->
createClosureForHtmlPart(2, 3)
invokeTag('captureTitle','sitemesh',6,[:],3)
})
invokeTag('wrapTitleTag','sitemesh',6,[:],2)
printHtmlPart(3)
})
invokeTag('captureHead','sitemesh',7,[:],1)
printHtmlPart(3)
createTagBody(1, {->
printHtmlPart(4)
createTagBody(2, {->
printHtmlPart(5)
expressionOut.print(resource(dir: 'images', file: 'add_record.png'))
printHtmlPart(6)
invokeTag('message','g',20,['code':("actions.business.add")],-1)
printHtmlPart(7)
})
invokeTag('link','g',22,['controller':("business"),'action':("create"),'params':(['max': params.max, 'offset': params.offset])],2)
printHtmlPart(8)
invokeTag('message','g',25,['code':("business.caption")],-1)
printHtmlPart(9)
invokeTag('sortableColumn','g',29,['property':("name"),'title':(message(code: 'business.name.label'))],-1)
printHtmlPart(10)
invokeTag('message','g',30,['code':("business.regNumber.label")],-1)
printHtmlPart(11)
invokeTag('message','g',31,['code':("business.incorpDate.label")],-1)
printHtmlPart(12)
loop:{
int index = 0
for( businessInstance in (businesses) ) {
printHtmlPart(13)
if(true && (new Integer(params.offset) > 0)) {
printHtmlPart(14)
invokeTag('set','g',38,['var':("offset"),'value':(new Integer(params.offset))],-1)
printHtmlPart(15)
}
else {
invokeTag('set','g',40,['var':("offset"),'value':(0)],-1)
}
printHtmlPart(16)
expressionOut.print((index % 2) == 0 ? 'even' : 'odd')
printHtmlPart(17)
expressionOut.print(offset + index + 1)
printHtmlPart(18)
createTagBody(3, {->
printHtmlPart(19)
expressionOut.print(businessInstance?.name)
printHtmlPart(20)
})
invokeTag('link','g',50,['class':("action-link"),'controller':("adminHome"),'action':("show"),'id':(businessInstance?.id),'params':(['max': params.max, 'offset': params.offset])],3)
printHtmlPart(21)
expressionOut.print(businessInstance?.regNumber)
printHtmlPart(22)
invokeTag('formatDate','g',56,['format':("dd/MM/yyyy"),'date':(businessInstance?.incorpDate)],-1)
printHtmlPart(23)
expressionOut.print(businessInstance?.city)
printHtmlPart(24)
index++
}
}
printHtmlPart(25)
invokeTag('paginate','g',64,['total':(businessInstanceCount ?: 0)],-1)
printHtmlPart(26)
invokeTag('message','g',67,['code':("default.application.totalrecords")],-1)
printHtmlPart(27)
expressionOut.print(businessInstanceCount)
printHtmlPart(28)
})
invokeTag('captureBody','sitemesh',69,[:],1)
printHtmlPart(29)
}
public static final Map JSP_TAGS = new HashMap()
protected void init() {
	this.jspTags = JSP_TAGS
}
public static final String CONTENT_TYPE = 'text/html;charset=UTF-8'
public static final long LAST_MODIFIED = 1442210842153L
public static final String EXPRESSION_CODEC = 'html'
public static final String STATIC_CODEC = 'none'
public static final String OUT_CODEC = 'html'
public static final String TAGLIB_CODEC = 'none'
}
