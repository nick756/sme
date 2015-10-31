import com.sme.entities.*
import org.codehaus.groovy.grails.plugins.metadata.GrailsPlugin
import org.codehaus.groovy.grails.web.pages.GroovyPage
import org.codehaus.groovy.grails.web.taglib.*
import org.codehaus.groovy.grails.web.taglib.exceptions.GrailsTagException
import org.springframework.web.util.*
import grails.util.GrailsUtil

class gsp_sme_smestatementcreate_gsp extends GroovyPage {
public String getGroovyPageFileName() { "/WEB-INF/grails-app/views/smestatement/create.gsp" }
public Object run() {
Writer out = getOut()
Writer expressionOut = getExpressionOut()
registerSitemeshPreprocessMode()
printHtmlPart(0)
printHtmlPart(1)
createTagBody(1, {->
printHtmlPart(2)
invokeTag('captureMeta','sitemesh',7,['gsp_sm_xmlClosingForEmptyTag':(""),'http-equiv':("Content-Type"),'content':("text/html; charset=UTF-8")],-1)
printHtmlPart(3)
invokeTag('captureMeta','sitemesh',8,['gsp_sm_xmlClosingForEmptyTag':("/"),'name':("layout"),'content':("smepage")],-1)
printHtmlPart(3)
createTagBody(2, {->
createClosureForHtmlPart(4, 3)
invokeTag('captureTitle','sitemesh',9,[:],3)
})
invokeTag('wrapTitleTag','sitemesh',9,[:],2)
printHtmlPart(5)
})
invokeTag('captureHead','sitemesh',13,[:],1)
printHtmlPart(6)
createTagBody(1, {->
printHtmlPart(7)
createTagBody(2, {->
printHtmlPart(8)
expressionOut.print(resource(dir: 'images', file: 'arrow_left.png'))
printHtmlPart(9)
invokeTag('message','g',16,['code':("actions.back")],-1)
printHtmlPart(10)
})
invokeTag('link','g',16,['style':("float: left; margin-left: 5px;"),'action':("index"),'params':(['max': params.max, 'offset': params.offset])],2)
printHtmlPart(11)
invokeTag('message','g',18,['code':("cfstatement.new")],-1)
printHtmlPart(12)
if(true && (errMsg != null)) {
printHtmlPart(13)
expressionOut.print(errMsg)
printHtmlPart(14)
}
printHtmlPart(15)
createTagBody(2, {->
printHtmlPart(16)
invokeTag('message','g',25,['code':("pnlstatement.year.label")],-1)
printHtmlPart(17)
invokeTag('datePicker','g',26,['name':("period"),'class':("select-list"),'precision':("year"),'relativeYears':([-1..1]),'value':(yearInst)],-1)
printHtmlPart(18)
invokeTag('message','g',28,['code':("pnlstatement.month.label")],-1)
printHtmlPart(17)
invokeTag('select','g',29,['class':("select-list"),'style':("width: 200px;"),'name':("month.id"),'noSelection':(['null':'']),'from':(Month.list()),'value':(monthInst?.id),'optionKey':("id")],-1)
printHtmlPart(19)
expressionOut.print(message(code: 'actions.generate'))
printHtmlPart(20)
})
invokeTag('form','g',32,['action':("generate"),'params':(['max': params.max, 'offset': params.offset])],2)
printHtmlPart(21)
})
invokeTag('captureBody','sitemesh',34,[:],1)
printHtmlPart(22)
}
public static final Map JSP_TAGS = new HashMap()
protected void init() {
	this.jspTags = JSP_TAGS
}
public static final String CONTENT_TYPE = 'text/html;charset=UTF-8'
public static final long LAST_MODIFIED = 1445143038485L
public static final String EXPRESSION_CODEC = 'html'
public static final String STATIC_CODEC = 'none'
public static final String OUT_CODEC = 'none'
public static final String TAGLIB_CODEC = 'none'
}
