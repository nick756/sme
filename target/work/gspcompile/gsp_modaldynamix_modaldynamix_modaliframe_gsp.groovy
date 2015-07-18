import org.codehaus.groovy.grails.plugins.metadata.GrailsPlugin
import org.codehaus.groovy.grails.web.pages.GroovyPage
import org.codehaus.groovy.grails.web.taglib.*
import org.codehaus.groovy.grails.web.taglib.exceptions.GrailsTagException
import org.springframework.web.util.*
import grails.util.GrailsUtil

@GrailsPlugin(name='modaldynamix', version='0.13')
class gsp_modaldynamix_modaldynamix_modaliframe_gsp extends GroovyPage {
public String getGroovyPageFileName() { "/WEB-INF/plugins/modaldynamix-0.13/grails-app/views/modaldynamix/_modaliframe.gsp" }
public Object run() {
Writer out = getOut()
Writer expressionOut = getExpressionOut()
registerSitemeshPreprocessMode()
printHtmlPart(0)
expressionOut.print(attrs.id)
printHtmlPart(1)
expressionOut.print(attrs.id)
printHtmlPart(2)
expressionOut.print(attrs.formId)
printHtmlPart(3)
expressionOut.print(attrs.close)
printHtmlPart(4)
expressionOut.print(attrs.title)
printHtmlPart(5)
expressionOut.print(attrs.iframezoom)
printHtmlPart(6)
expressionOut.print(attrs.iframewidth)
printHtmlPart(7)
expressionOut.print(attrs.iframeheight)
printHtmlPart(8)
expressionOut.print(attrs.iframemargin)
printHtmlPart(9)
expressionOut.print(attrs.iframepadding)
printHtmlPart(10)
expressionOut.print(attrs.iframetransparency)
printHtmlPart(11)
expressionOut.print(attrs.url)
printHtmlPart(12)
if(true && (attrs.modalFooterPage)) {
printHtmlPart(13)
invokeTag('render','g',26,['template':(attrs.modalFooterPage),'model':([attrs:attrs,params:params,definedParams:definedParams])],-1)
printHtmlPart(14)
}
else {
printHtmlPart(13)
expressionOut.print(attrs.footer.encodeAsRaw())
printHtmlPart(14)
}
printHtmlPart(15)
expressionOut.print(attrs.formId)
printHtmlPart(16)
expressionOut.print(attrs.close)
printHtmlPart(17)
invokeTag('render','g',1,['template':(attrs.modalJsTemplate),'model':([attrs:attrs,params:params,definedParams:definedParams])],-1)
}
public static final Map JSP_TAGS = new HashMap()
protected void init() {
	this.jspTags = JSP_TAGS
}
public static final String CONTENT_TYPE = 'text/html;charset=UTF-8'
public static final long LAST_MODIFIED = 1427341108000L
public static final String EXPRESSION_CODEC = 'html'
public static final String STATIC_CODEC = 'none'
public static final String OUT_CODEC = 'none'
public static final String TAGLIB_CODEC = 'none'
}
