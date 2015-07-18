import org.codehaus.groovy.grails.plugins.metadata.GrailsPlugin
import org.codehaus.groovy.grails.web.pages.GroovyPage
import org.codehaus.groovy.grails.web.taglib.*
import org.codehaus.groovy.grails.web.taglib.exceptions.GrailsTagException
import org.springframework.web.util.*
import grails.util.GrailsUtil

class gsp_sme_layoutsloginpage_gsp extends GroovyPage {
public String getGroovyPageFileName() { "/WEB-INF/grails-app/views/layouts/loginpage.gsp" }
public Object run() {
Writer out = getOut()
Writer expressionOut = getExpressionOut()
registerSitemeshPreprocessMode()
printHtmlPart(0)
printHtmlPart(1)
createTagBody(1, {->
printHtmlPart(2)
invokeTag('captureMeta','sitemesh',11,['gsp_sm_xmlClosingForEmptyTag':(""),'http-equiv':("Content-Type"),'content':("text/html; charset=UTF-8")],-1)
printHtmlPart(2)
createTagBody(2, {->
createTagBody(3, {->
invokeTag('layoutTitle','g',12,['default':("SME")],-1)
})
invokeTag('captureTitle','sitemesh',12,[:],3)
})
invokeTag('wrapTitleTag','sitemesh',12,[:],2)
printHtmlPart(3)
expressionOut.print(resource(dir:'stylesheets',file:'application.css'))
printHtmlPart(4)
invokeTag('layoutHead','g',15,[:],-1)
printHtmlPart(5)
})
invokeTag('captureHead','sitemesh',16,[:],1)
printHtmlPart(5)
createTagBody(1, {->
printHtmlPart(6)
if(true && (session?.user)) {
printHtmlPart(7)
expressionOut.print(session.user?.name)
printHtmlPart(8)
}
else {
printHtmlPart(9)
createTagBody(3, {->
printHtmlPart(10)
expressionOut.print(resource(dir: 'images', file: 'my.png'))
printHtmlPart(11)
})
invokeTag('link','g',25,['action':("index"),'controller':("login"),'params':([lang: 'ms'])],3)
printHtmlPart(12)
createTagBody(3, {->
printHtmlPart(13)
expressionOut.print(resource(dir: 'images', file: 'gb.png'))
printHtmlPart(11)
})
invokeTag('link','g',28,['action':("index"),'controller':("login"),'params':([lang: 'en_US'])],3)
printHtmlPart(14)
}
printHtmlPart(15)
invokeTag('message','g',32,['code':("default.application.title")],-1)
printHtmlPart(16)
invokeTag('layoutBody','g',35,[:],-1)
printHtmlPart(5)
})
invokeTag('captureBody','sitemesh',36,[:],1)
printHtmlPart(17)
}
public static final Map JSP_TAGS = new HashMap()
protected void init() {
	this.jspTags = JSP_TAGS
}
public static final String CONTENT_TYPE = 'text/html;charset=UTF-8'
public static final long LAST_MODIFIED = 1433840047205L
public static final String EXPRESSION_CODEC = 'html'
public static final String STATIC_CODEC = 'none'
public static final String OUT_CODEC = 'none'
public static final String TAGLIB_CODEC = 'none'
}
