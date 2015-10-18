import org.codehaus.groovy.grails.plugins.metadata.GrailsPlugin
import org.codehaus.groovy.grails.web.pages.GroovyPage
import org.codehaus.groovy.grails.web.taglib.*
import org.codehaus.groovy.grails.web.taglib.exceptions.GrailsTagException
import org.springframework.web.util.*
import grails.util.GrailsUtil

@GrailsPlugin(name='modaldynamix', version='0.13')
class gsp_modaldynamix_loadmodalbutton_gsp extends GroovyPage {
public String getGroovyPageFileName() { "/WEB-INF/plugins/modaldynamix-0.13/grails-app/views/_loadmodalbutton.gsp" }
public Object run() {
Writer out = getOut()
Writer expressionOut = getExpressionOut()
registerSitemeshPreprocessMode()
printHtmlPart(0)
expressionOut.print(attrs.id)
printHtmlPart(1)
expressionOut.print(attrs.style)
printHtmlPart(2)
expressionOut.print(attrs.id)
printHtmlPart(3)
expressionOut.print(attrs.id)
printHtmlPart(4)
expressionOut.print(attrs.title)
printHtmlPart(5)
expressionOut.print(attrs.value)
printHtmlPart(6)
expressionOut.print(attrs.id)
printHtmlPart(7)
expressionOut.print(attrs.divId)
printHtmlPart(8)
}
public static final Map JSP_TAGS = new HashMap()
protected void init() {
	this.jspTags = JSP_TAGS
}
public static final String CONTENT_TYPE = 'text/html;charset=UTF-8'
public static final long LAST_MODIFIED = 1427270394000L
public static final String EXPRESSION_CODEC = 'html'
public static final String STATIC_CODEC = 'none'
public static final String OUT_CODEC = 'html'
public static final String TAGLIB_CODEC = 'none'
}
