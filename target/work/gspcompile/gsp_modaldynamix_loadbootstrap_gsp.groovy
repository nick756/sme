import org.codehaus.groovy.grails.plugins.metadata.GrailsPlugin
import org.codehaus.groovy.grails.web.pages.GroovyPage
import org.codehaus.groovy.grails.web.taglib.*
import org.codehaus.groovy.grails.web.taglib.exceptions.GrailsTagException
import org.springframework.web.util.*
import grails.util.GrailsUtil

@GrailsPlugin(name='modaldynamix', version='0.13')
class gsp_modaldynamix_loadbootstrap_gsp extends GroovyPage {
public String getGroovyPageFileName() { "/WEB-INF/plugins/modaldynamix-0.13/grails-app/views/_loadbootstrap.gsp" }
public Object run() {
Writer out = getOut()
Writer expressionOut = getExpressionOut()
registerSitemeshPreprocessMode()
printHtmlPart(0)
if(true && (enduser.verifyAppVersion().equals('resources'))) {
printHtmlPart(1)
expressionOut.print(createLink(uri: '/css/bootstrap.min.css'))
printHtmlPart(2)
expressionOut.print(createLink(uri: '/js/bootstrap.min.js'))
printHtmlPart(3)
}
else {
printHtmlPart(4)
invokeTag('stylesheet','asset',10,['href':("bootstrap.min.css")],-1)
printHtmlPart(5)
invokeTag('javascript','asset',11,['src':("bootstrap.min.js")],-1)
printHtmlPart(5)
invokeTag('javascript','asset',12,['src':("jquery-ui.min.js")],-1)
printHtmlPart(6)
}
printHtmlPart(6)
}
public static final Map JSP_TAGS = new HashMap()
protected void init() {
	this.jspTags = JSP_TAGS
}
public static final String CONTENT_TYPE = 'text/html;charset=UTF-8'
public static final long LAST_MODIFIED = 1427270394000L
public static final String EXPRESSION_CODEC = 'html'
public static final String STATIC_CODEC = 'none'
public static final String OUT_CODEC = 'none'
public static final String TAGLIB_CODEC = 'none'
}
