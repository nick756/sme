import org.codehaus.groovy.grails.plugins.metadata.GrailsPlugin
import org.codehaus.groovy.grails.web.pages.GroovyPage
import org.codehaus.groovy.grails.web.taglib.*
import org.codehaus.groovy.grails.web.taglib.exceptions.GrailsTagException
import org.springframework.web.util.*
import grails.util.GrailsUtil

@GrailsPlugin(name='modaldynamix', version='0.13')
class gsp_modaldynamix_modaldynamix_modalRemoteForm_gsp extends GroovyPage {
public String getGroovyPageFileName() { "/WEB-INF/plugins/modaldynamix-0.13/grails-app/views/modaldynamix/_modalRemoteForm.gsp" }
public Object run() {
Writer out = getOut()
Writer expressionOut = getExpressionOut()
registerSitemeshPreprocessMode()
printHtmlPart(0)
expressionOut.print(attrs.id)
printHtmlPart(1)
if(true && (flash.message)) {
printHtmlPart(2)
expressionOut.print(flash.message)
printHtmlPart(3)
}
printHtmlPart(4)
createTagBody(1, {->
printHtmlPart(5)
expressionOut.print(attrs?.title)
printHtmlPart(6)
if(true && (attrs.fromPlugin)) {
printHtmlPart(7)
invokeTag('loadATemplate','g',28,['fromPlugin':(attrs.fromPlugin),'template':(attrs.modalTemplatePage),'model':([attrs:attrs, params:params, definedParams:definedParams])],-1)
printHtmlPart(8)
}
else {
printHtmlPart(7)
invokeTag('render','g',32,['template':(attrs.modalTemplatePage),'model':([attrs:attrs, params:params,definedParams:definedParams])],-1)
printHtmlPart(8)
}
printHtmlPart(9)
invokeTag('submitToRemote','g',38,['class':("myformsubmit"),'url':([controller:attrs.submitController, action:attrs.submitAction]),'update':(attrs.id),'onComplete':("${attrs.formId}CloseModal()"),'value':(attrs.submitValue)],-1)
printHtmlPart(10)
if(true && (attrs.modalFooterPage)) {
printHtmlPart(8)
invokeTag('render','g',47,['template':(attrs.modalFooterPage),'model':([attrs:attrs])],-1)
printHtmlPart(11)
}
else {
printHtmlPart(8)
expressionOut.print(attrs.footer.encodeAsRaw())
printHtmlPart(11)
}
printHtmlPart(12)
})
invokeTag('formRemote','g',55,['id':("1"),'name':(attrs.formId),'class':("form-horizontal"),'url':([controller:attrs.submitController, action:attrs.submitAction]),'update':(id),'onComplete':("${attrs.formId}CloseModal()")],1)
printHtmlPart(13)
invokeTag('render','g',1,['template':(attrs.modalJsTemplate),'model':([attrs:attrs])],-1)
}
public static final Map JSP_TAGS = new HashMap()
protected void init() {
	this.jspTags = JSP_TAGS
}
public static final String CONTENT_TYPE = 'text/html;charset=UTF-8'
public static final long LAST_MODIFIED = 1427336438000L
public static final String EXPRESSION_CODEC = 'html'
public static final String STATIC_CODEC = 'none'
public static final String OUT_CODEC = 'html'
public static final String TAGLIB_CODEC = 'none'
}
