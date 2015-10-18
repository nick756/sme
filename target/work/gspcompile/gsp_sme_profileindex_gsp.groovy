import org.codehaus.groovy.grails.plugins.metadata.GrailsPlugin
import org.codehaus.groovy.grails.web.pages.GroovyPage
import org.codehaus.groovy.grails.web.taglib.*
import org.codehaus.groovy.grails.web.taglib.exceptions.GrailsTagException
import org.springframework.web.util.*
import grails.util.GrailsUtil

class gsp_sme_profileindex_gsp extends GroovyPage {
public String getGroovyPageFileName() { "/WEB-INF/grails-app/views/profile/index.gsp" }
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
expressionOut.print(resource(dir: 'images', file: 'add_record.png'))
printHtmlPart(7)
invokeTag('message','g',11,['code':("actions.profile.add")],-1)
printHtmlPart(8)
})
invokeTag('link','g',11,['action':("add"),'params':(['max': params.max, 'offset': params.offset])],2)
printHtmlPart(9)
invokeTag('message','g',14,['code':("genericProfile.caption")],-1)
printHtmlPart(10)
invokeTag('sortableColumn','g',17,['property':("name"),'title':(message(code: 'genericProfile.name.label'))],-1)
printHtmlPart(11)
invokeTag('message','g',18,['code':("genericProfile.code.label")],-1)
printHtmlPart(12)
invokeTag('message','g',19,['code':("genericProfile.operations.label")],-1)
printHtmlPart(13)
loop:{
int index = 0
for( profileInstance in (profiles) ) {
printHtmlPart(14)
if(true && (params.offset > 0)) {
invokeTag('set','g',24,['var':("offset"),'value':(new Integer(params.offset))],-1)
}
else {
invokeTag('set','g',25,['var':("offset"),'value':(0)],-1)
}
printHtmlPart(15)
expressionOut.print((index % 2) == 0 ? 'even' : 'odd')
printHtmlPart(16)
expressionOut.print(offset + index + 1)
printHtmlPart(17)
createTagBody(3, {->
expressionOut.print(profileInstance?.name)
})
invokeTag('link','g',29,['class':("action-link"),'controller':("profile"),'action':("show"),'id':(profileInstance?.id),'params':(['max': params.max, 'offset': params.offset])],3)
printHtmlPart(18)
expressionOut.print(profileInstance?.code)
printHtmlPart(18)
expressionOut.print(profileInstance?.operations?.size())
printHtmlPart(19)
index++
}
}
printHtmlPart(20)
invokeTag('paginate','g',37,['total':(profileCount ?: 0)],-1)
printHtmlPart(21)
invokeTag('message','g',40,['code':("default.application.totalrecords")],-1)
printHtmlPart(22)
expressionOut.print(profileCount)
printHtmlPart(23)
})
invokeTag('captureBody','sitemesh',43,[:],1)
printHtmlPart(24)
}
public static final Map JSP_TAGS = new HashMap()
protected void init() {
	this.jspTags = JSP_TAGS
}
public static final String CONTENT_TYPE = 'text/html;charset=UTF-8'
public static final long LAST_MODIFIED = 1435296832324L
public static final String EXPRESSION_CODEC = 'html'
public static final String STATIC_CODEC = 'none'
public static final String OUT_CODEC = 'html'
public static final String TAGLIB_CODEC = 'none'
}
