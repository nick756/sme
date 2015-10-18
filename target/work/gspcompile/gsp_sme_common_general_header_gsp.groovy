import org.codehaus.groovy.grails.plugins.metadata.GrailsPlugin
import org.codehaus.groovy.grails.web.pages.GroovyPage
import org.codehaus.groovy.grails.web.taglib.*
import org.codehaus.groovy.grails.web.taglib.exceptions.GrailsTagException
import org.springframework.web.util.*
import grails.util.GrailsUtil

class gsp_sme_common_general_header_gsp extends GroovyPage {
public String getGroovyPageFileName() { "/WEB-INF/grails-app/views/common/_general_header.gsp" }
public Object run() {
Writer out = getOut()
Writer expressionOut = getExpressionOut()
registerSitemeshPreprocessMode()
printHtmlPart(0)
expressionOut.print(resource(dir: 'images', file: 'user_login.png'))
printHtmlPart(1)
expressionOut.print(session.user?.name)
printHtmlPart(2)
expressionOut.print(session.user?.role?.name)
printHtmlPart(3)
createTagBody(1, {->
printHtmlPart(4)
expressionOut.print(resource(dir: 'images', file: 'sign_out.png'))
printHtmlPart(5)
})
invokeTag('link','g',7,['class':("redirect-indicator"),'controller':("login"),'action':("logout")],1)
printHtmlPart(6)
expressionOut.print(resource(dir: 'images', file: 'cube.jpg'))
printHtmlPart(7)
invokeTag('message','g',13,['code':("default.application.title")],-1)
printHtmlPart(8)
}
public static final Map JSP_TAGS = new HashMap()
protected void init() {
	this.jspTags = JSP_TAGS
}
public static final String CONTENT_TYPE = 'text/html;charset=UTF-8'
public static final long LAST_MODIFIED = 1433832332789L
public static final String EXPRESSION_CODEC = 'html'
public static final String STATIC_CODEC = 'none'
public static final String OUT_CODEC = 'html'
public static final String TAGLIB_CODEC = 'none'
}
