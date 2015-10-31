import org.codehaus.groovy.grails.plugins.metadata.GrailsPlugin
import org.codehaus.groovy.grails.web.pages.GroovyPage
import org.codehaus.groovy.grails.web.taglib.*
import org.codehaus.groovy.grails.web.taglib.exceptions.GrailsTagException
import org.springframework.web.util.*
import grails.util.GrailsUtil

class gsp_sme_userindex_gsp extends GroovyPage {
public String getGroovyPageFileName() { "/WEB-INF/grails-app/views/user/index.gsp" }
public Object run() {
Writer out = getOut()
Writer expressionOut = getExpressionOut()
registerSitemeshPreprocessMode()
printHtmlPart(0)
createTagBody(1, {->
printHtmlPart(1)
invokeTag('captureMeta','sitemesh',3,['gsp_sm_xmlClosingForEmptyTag':(""),'http-equiv':("Content-Type"),'content':("text/html; charset=UTF-8")],-1)
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
createTagBody(2, {->
printHtmlPart(5)
expressionOut.print(resource(dir: 'images', file: 'add_record.png'))
printHtmlPart(6)
invokeTag('message','g',9,['code':("actions.user.add")],-1)
printHtmlPart(7)
})
invokeTag('link','g',9,['controller':("user"),'action':("create"),'params':(['max': params.max, 'offset': params.offset])],2)
printHtmlPart(8)
createTagBody(2, {->
printHtmlPart(9)
expressionOut.print(resource(dir: 'images', file: 'printer.png'))
printHtmlPart(6)
invokeTag('message','g',10,['code':("actions.print")],-1)
printHtmlPart(7)
})
invokeTag('link','g',10,['style':("float: right;"),'target':("_blank"),'controller':("user"),'action':("report")],2)
printHtmlPart(10)
invokeTag('message','g',13,['code':("user.list")],-1)
printHtmlPart(11)
invokeTag('sortableColumn','g',15,['property':("name"),'title':(message(code: 'user.name.label', default: 'Name'))],-1)
printHtmlPart(12)
invokeTag('sortableColumn','g',16,['property':("login"),'title':(message(code: 'user.login.label', default: 'Login'))],-1)
printHtmlPart(13)
invokeTag('message','g',17,['code':("user.company")],-1)
printHtmlPart(14)
loop:{
int i = 0
for( userInstance in (userInstanceList) ) {
printHtmlPart(15)
expressionOut.print((i % 2) == 0 ? 'even' : 'odd')
printHtmlPart(16)
expressionOut.print(userInstance?.name)
printHtmlPart(17)
expressionOut.print(userInstance?.login)
printHtmlPart(17)
expressionOut.print(userInstance?.company?.name)
printHtmlPart(18)
i++
}
}
printHtmlPart(19)
invokeTag('paginate','g',28,['total':(userInstanceCount ?: 0)],-1)
printHtmlPart(20)
})
invokeTag('captureBody','sitemesh',30,[:],1)
printHtmlPart(21)
}
public static final Map JSP_TAGS = new HashMap()
protected void init() {
	this.jspTags = JSP_TAGS
}
public static final String CONTENT_TYPE = 'text/html;charset=UTF-8'
public static final long LAST_MODIFIED = 1445767188372L
public static final String EXPRESSION_CODEC = 'html'
public static final String STATIC_CODEC = 'none'
public static final String OUT_CODEC = 'none'
public static final String TAGLIB_CODEC = 'none'
}
