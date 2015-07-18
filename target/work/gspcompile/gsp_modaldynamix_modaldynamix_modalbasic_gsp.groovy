import org.codehaus.groovy.grails.plugins.metadata.GrailsPlugin
import org.codehaus.groovy.grails.web.pages.GroovyPage
import org.codehaus.groovy.grails.web.taglib.*
import org.codehaus.groovy.grails.web.taglib.exceptions.GrailsTagException
import org.springframework.web.util.*
import grails.util.GrailsUtil

@GrailsPlugin(name='modaldynamix', version='0.13')
class gsp_modaldynamix_modaldynamix_modalbasic_gsp extends GroovyPage {
public String getGroovyPageFileName() { "/WEB-INF/plugins/modaldynamix-0.13/grails-app/views/modaldynamix/_modalbasic.gsp" }
public Object run() {
Writer out = getOut()
Writer expressionOut = getExpressionOut()
registerSitemeshPreprocessMode()
printHtmlPart(0)
expressionOut.print(attrs.id)
printHtmlPart(1)
expressionOut.print(attrs.close)
printHtmlPart(2)
expressionOut.print(attrs.title)
printHtmlPart(3)
invokeTag('render','g',15,['template':(attrs.modalTemplatePage),'model':([attrs:attrs,params:params,definedParams:definedParams])],-1)
printHtmlPart(4)
if(true && (attrs.modalFooterPage)) {
printHtmlPart(5)
invokeTag('render','g',22,['template':(attrs.modalFooterPage),'model':([attrs:attrs,params:params,definedParams:definedParams])],-1)
printHtmlPart(6)
}
else {
printHtmlPart(5)
expressionOut.print(attrs.footer.encodeAsRaw())
printHtmlPart(6)
}
printHtmlPart(7)
invokeTag('render','g',31,['template':(attrs.modalDimension),'model':([attrs:attrs])],-1)
printHtmlPart(8)
createClosureForHtmlPart(9, 1)
invokeTag('javascript','g',39,[:],1)
printHtmlPart(8)
}
public static final Map JSP_TAGS = new HashMap()
protected void init() {
	this.jspTags = JSP_TAGS
}
public static final String CONTENT_TYPE = 'text/html;charset=UTF-8'
public static final long LAST_MODIFIED = 1427341114000L
public static final String EXPRESSION_CODEC = 'html'
public static final String STATIC_CODEC = 'none'
public static final String OUT_CODEC = 'none'
public static final String TAGLIB_CODEC = 'none'
}
