import org.codehaus.groovy.grails.plugins.metadata.GrailsPlugin
import org.codehaus.groovy.grails.web.pages.GroovyPage
import org.codehaus.groovy.grails.web.taglib.*
import org.codehaus.groovy.grails.web.taglib.exceptions.GrailsTagException
import org.springframework.web.util.*
import grails.util.GrailsUtil

class gsp_sme_adminHomeshow_gsp extends GroovyPage {
public String getGroovyPageFileName() { "/WEB-INF/grails-app/views/adminHome/show.gsp" }
public Object run() {
Writer out = getOut()
Writer expressionOut = getExpressionOut()
registerSitemeshPreprocessMode()
printHtmlPart(0)
createTagBody(1, {->
printHtmlPart(1)
invokeTag('captureMeta','sitemesh',5,['gsp_sm_xmlClosingForEmptyTag':("/"),'name':("layout"),'content':("adminpage")],-1)
printHtmlPart(1)
createTagBody(2, {->
createClosureForHtmlPart(2, 3)
invokeTag('captureTitle','sitemesh',6,[:],3)
})
invokeTag('wrapTitleTag','sitemesh',6,[:],2)
printHtmlPart(3)
})
invokeTag('captureHead','sitemesh',7,[:],1)
printHtmlPart(3)
createTagBody(1, {->
printHtmlPart(4)
createTagBody(2, {->
printHtmlPart(5)
expressionOut.print(resource(dir: 'images', file: 'arrow_left.png'))
printHtmlPart(6)
invokeTag('message','g',10,['code':("actions.back")],-1)
printHtmlPart(7)
})
invokeTag('link','g',10,['style':("float: left;"),'action':("index"),'params':(['max': params.max, 'offset': params.offset])],2)
printHtmlPart(8)
createTagBody(2, {->
printHtmlPart(9)
expressionOut.print(resource(dir: 'images', file: 'edit.png'))
printHtmlPart(6)
invokeTag('message','g',11,['code':("actions.edit")],-1)
printHtmlPart(7)
})
invokeTag('link','g',11,['action':("edit"),'params':(['max': params.max, 'offset': params.offset, 'id': businessInstance?.id])],2)
printHtmlPart(8)
createTagBody(2, {->
printHtmlPart(9)
expressionOut.print(resource(dir: 'images', file: 'employee.png'))
printHtmlPart(6)
invokeTag('message','g',12,['code':("actions.members")],-1)
printHtmlPart(7)
})
invokeTag('link','g',12,['action':("index"),'params':(['max': params.max, 'offset': params.offset])],2)
printHtmlPart(8)

/* g:link action="index" params="['max': params.max, 'offset': params.offset]"><img class="image-link" src="${resource(dir: 'images', file: 'list.png')}" title="<g:message code='actions.profile'/>"/></g:link */

printHtmlPart(8)
createTagBody(2, {->
printHtmlPart(10)
expressionOut.print(resource(dir: 'images', file: 'calculator.png'))
printHtmlPart(6)
invokeTag('message','g',14,['code':("actions.transactions")],-1)
printHtmlPart(7)
})
invokeTag('link','g',14,['action':("listtransactions"),'params':(['max': params.max, 'offset': 0, 'id': businessInstance?.id])],2)
printHtmlPart(11)
expressionOut.print(businessInstance?.name)
printHtmlPart(12)
invokeTag('message','g',20,['code':("business.name.label")],-1)
printHtmlPart(13)
invokeTag('textField','g',21,['class':("text-input"),'name':("name"),'value':(businessInstance?.name),'readonly':("true")],-1)
printHtmlPart(14)
invokeTag('message','g',23,['code':("business.profile.label")],-1)
printHtmlPart(13)
invokeTag('textField','g',24,['class':("text-input"),'name':("name"),'value':(businessInstance?.profile?.name),'readonly':("true")],-1)
printHtmlPart(15)
invokeTag('message','g',26,['code':("business.regNumber.label"),'default':("No")],-1)
printHtmlPart(13)
invokeTag('textField','g',27,['class':("text-input"),'name':("regNumber"),'value':(businessInstance?.regNumber),'readonly':("true")],-1)
printHtmlPart(14)
invokeTag('message','g',29,['code':("business.industry.label"),'default':("No")],-1)
printHtmlPart(13)
invokeTag('textField','g',30,['class':("text-input"),'name':("regNumber"),'value':(businessInstance?.industry?.name),'readonly':("true")],-1)
printHtmlPart(16)
invokeTag('message','g',32,['code':("business.incorpDate.label"),'default':("Incorp Date")],-1)
printHtmlPart(13)
invokeTag('textField','g',33,['class':("text-input"),'name':("regNumber"),'value':(formatDate(format:'dd/MM/yyyy', date:businessInstance?.incorpDate)),'readonly':("true")],-1)
printHtmlPart(17)
invokeTag('message','g',35,['code':("business.address.label"),'default':("Address")],-1)
printHtmlPart(13)
invokeTag('textArea','g',36,['class':("text-input"),'name':("address"),'value':(businessInstance?.address),'readonly':("true")],-1)
printHtmlPart(14)
invokeTag('message','g',38,['code':("business.city.label"),'default':("City")],-1)
printHtmlPart(13)
invokeTag('textField','g',39,['class':("text-input"),'name':("address"),'value':(businessInstance?.city),'readonly':("true")],-1)
printHtmlPart(18)
})
invokeTag('captureBody','sitemesh',43,[:],1)
printHtmlPart(19)
}
public static final Map JSP_TAGS = new HashMap()
protected void init() {
	this.jspTags = JSP_TAGS
}
public static final String CONTENT_TYPE = 'text/html;charset=UTF-8'
public static final long LAST_MODIFIED = 1442913019912L
public static final String EXPRESSION_CODEC = 'html'
public static final String STATIC_CODEC = 'none'
public static final String OUT_CODEC = 'html'
public static final String TAGLIB_CODEC = 'none'
}
