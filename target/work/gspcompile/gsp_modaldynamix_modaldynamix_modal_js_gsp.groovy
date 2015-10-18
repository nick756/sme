import org.codehaus.groovy.grails.plugins.metadata.GrailsPlugin
import org.codehaus.groovy.grails.web.pages.GroovyPage
import org.codehaus.groovy.grails.web.taglib.*
import org.codehaus.groovy.grails.web.taglib.exceptions.GrailsTagException
import org.springframework.web.util.*
import grails.util.GrailsUtil

@GrailsPlugin(name='modaldynamix', version='0.13')
class gsp_modaldynamix_modaldynamix_modal_js_gsp extends GroovyPage {
public String getGroovyPageFileName() { "/WEB-INF/plugins/modaldynamix-0.13/grails-app/views/modaldynamix/_modal-js.gsp" }
public Object run() {
Writer out = getOut()
Writer expressionOut = getExpressionOut()
registerSitemeshPreprocessMode()
invokeTag('render','g',1,['template':(attrs.modalDimension),'model':([attrs:attrs])],-1)
printHtmlPart(0)
createTagBody(1, {->
printHtmlPart(1)
expressionOut.print(attrs.formId)
printHtmlPart(2)
expressionOut.print(attrs.id)
printHtmlPart(3)
expressionOut.print(attrs.domain)
printHtmlPart(4)
expressionOut.print(attrs.divId)
printHtmlPart(5)
expressionOut.print(attrs.fromPlugin)
printHtmlPart(6)
expressionOut.print(attrs.returnController)
printHtmlPart(7)
if(true && (attrs.clearckeditor)) {
printHtmlPart(8)
expressionOut.print(attrs.clearckeditor)
printHtmlPart(9)
expressionOut.print(attrs.clearckeditor)
printHtmlPart(10)
}
printHtmlPart(11)
expressionOut.print(createLink(controller:"${attrs.queryController}", action: "${attrs.queryAction}"))
printHtmlPart(12)
expressionOut.print(attrs.divId)
printHtmlPart(13)
if(true && (!attrs.disablecheck.equals('true'))) {
printHtmlPart(14)
expressionOut.print(createLink(controller:"${attrs.queryController}", action: "${attrs.queryAction}"))
printHtmlPart(15)
expressionOut.print(attrs.divId)
printHtmlPart(16)
}
printHtmlPart(17)
})
invokeTag('javascript','g',31,[:],1)
printHtmlPart(0)
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
