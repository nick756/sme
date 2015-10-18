import org.codehaus.groovy.grails.plugins.metadata.GrailsPlugin
import org.codehaus.groovy.grails.web.pages.GroovyPage
import org.codehaus.groovy.grails.web.taglib.*
import org.codehaus.groovy.grails.web.taglib.exceptions.GrailsTagException
import org.springframework.web.util.*
import grails.util.GrailsUtil

@GrailsPlugin(name='modaldynamix', version='0.13')
class gsp_modaldynamix_modaldynamix_modalSelfPostForm_gsp extends GroovyPage {
public String getGroovyPageFileName() { "/WEB-INF/plugins/modaldynamix-0.13/grails-app/views/modaldynamix/_modalSelfPostForm.gsp" }
public Object run() {
Writer out = getOut()
Writer expressionOut = getExpressionOut()
registerSitemeshPreprocessMode()
printHtmlPart(0)
expressionOut.print(attrs.id)
printHtmlPart(1)
expressionOut.print(attrs.id)
printHtmlPart(2)
expressionOut.print(attrs?.title)
printHtmlPart(3)
if(true && (attrs.fromPlugin)) {
printHtmlPart(4)
invokeTag('loadATemplate','g',18,['fromPlugin':(attrs.fromPlugin),'template':(attrs.modalTemplatePage)],-1)
printHtmlPart(5)
}
else {
printHtmlPart(4)
invokeTag('render','g',22,['template':(attrs.modalTemplatePage),'model':([attrs:attrs,params:params,definedParams:definedParams])],-1)
printHtmlPart(5)
}
printHtmlPart(6)
if(true && (attrs.modalFooterPage)) {
printHtmlPart(5)
invokeTag('render','g',30,['template':(attrs.modalFooterPage),'model':([attrs:attrs,params:params,definedParams:definedParams])],-1)
printHtmlPart(7)
}
else {
printHtmlPart(5)
expressionOut.print(attrs.footer.encodeAsRaw())
printHtmlPart(7)
}
printHtmlPart(8)
invokeTag('render','g',43,['template':(attrs.modalDimension),'model':([attrs:attrs])],-1)
printHtmlPart(9)
createTagBody(1, {->
printHtmlPart(10)
expressionOut.print(attrs.formId)
printHtmlPart(11)
expressionOut.print(attrs.formId)
printHtmlPart(12)
expressionOut.print(attrs.formId)
printHtmlPart(13)
expressionOut.print(attrs.formId)
printHtmlPart(14)
expressionOut.print(attrs.id)
printHtmlPart(15)
expressionOut.print(attrs.domain)
printHtmlPart(16)
expressionOut.print(attrs.divId)
printHtmlPart(17)
expressionOut.print(attrs.fromPlugin)
printHtmlPart(18)
expressionOut.print(attrs.returnController)
printHtmlPart(19)
expressionOut.print(createLink(controller:"${attrs.queryController}", action: "${attrs.queryAction}"))
printHtmlPart(20)
expressionOut.print(attrs.divId)
printHtmlPart(21)
if(true && (attrs.clearckeditor)) {
printHtmlPart(22)
expressionOut.print(attrs.clearckeditor)
printHtmlPart(23)
expressionOut.print(attrs.clearckeditor)
printHtmlPart(24)
}
printHtmlPart(25)
if(true && (!attrs.disablecheck.equals('true'))) {
printHtmlPart(26)
expressionOut.print(createLink(controller:"${attrs.queryController}", action: "${attrs.queryAction}"))
printHtmlPart(27)
expressionOut.print(attrs.divId)
printHtmlPart(28)
}
printHtmlPart(29)
})
invokeTag('javascript','g',93,[:],1)
printHtmlPart(9)
}
public static final Map JSP_TAGS = new HashMap()
protected void init() {
	this.jspTags = JSP_TAGS
}
public static final String CONTENT_TYPE = 'text/html;charset=UTF-8'
public static final long LAST_MODIFIED = 1427341120000L
public static final String EXPRESSION_CODEC = 'html'
public static final String STATIC_CODEC = 'none'
public static final String OUT_CODEC = 'html'
public static final String TAGLIB_CODEC = 'none'
}
