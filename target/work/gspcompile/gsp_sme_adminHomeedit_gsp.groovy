import com.sme.entities.*
import org.codehaus.groovy.grails.plugins.metadata.GrailsPlugin
import org.codehaus.groovy.grails.web.pages.GroovyPage
import org.codehaus.groovy.grails.web.taglib.*
import org.codehaus.groovy.grails.web.taglib.exceptions.GrailsTagException
import org.springframework.web.util.*
import grails.util.GrailsUtil

class gsp_sme_adminHomeedit_gsp extends GroovyPage {
public String getGroovyPageFileName() { "/WEB-INF/grails-app/views/adminHome/edit.gsp" }
public Object run() {
Writer out = getOut()
Writer expressionOut = getExpressionOut()
registerSitemeshPreprocessMode()
printHtmlPart(0)
printHtmlPart(1)
createTagBody(1, {->
printHtmlPart(2)
invokeTag('captureMeta','sitemesh',6,['gsp_sm_xmlClosingForEmptyTag':("/"),'name':("layout"),'content':("adminpage")],-1)
printHtmlPart(2)
createTagBody(2, {->
createClosureForHtmlPart(3, 3)
invokeTag('captureTitle','sitemesh',7,[:],3)
})
invokeTag('wrapTitleTag','sitemesh',7,[:],2)
printHtmlPart(4)
})
invokeTag('captureHead','sitemesh',8,[:],1)
printHtmlPart(4)
createTagBody(1, {->
printHtmlPart(5)
createTagBody(2, {->
printHtmlPart(6)
expressionOut.print(resource(dir: 'images', file: 'arrow_left.png'))
printHtmlPart(7)
invokeTag('message','g',11,['code':("actions.back")],-1)
printHtmlPart(8)
})
invokeTag('link','g',11,['style':("float: left;"),'action':("show"),'params':(['max': params.max, 'offset': params.offset, 'id': businessInstance?.id])],2)
printHtmlPart(9)
expressionOut.print(businessInstance?.name)
printHtmlPart(10)
createTagBody(2, {->
printHtmlPart(11)
invokeTag('message','g',17,['code':("business.name.label")],-1)
printHtmlPart(12)
invokeTag('textField','g',18,['class':("text-input"),'name':("name"),'value':(businessInstance?.name)],-1)
printHtmlPart(13)
invokeTag('message','g',20,['code':("business.profile.label")],-1)
printHtmlPart(12)
invokeTag('select','g',21,['class':("select-list"),'name':("profile.id"),'from':(GenericProfile.list()),'value':(businessInstance?.profile?.id),'optionKey':("id")],-1)
printHtmlPart(13)
invokeTag('message','g',23,['code':("business.regNumber.label"),'default':("ROC Number")],-1)
printHtmlPart(12)
invokeTag('textField','g',24,['class':("text-input"),'name':("regNumber"),'value':(businessInstance?.regNumber)],-1)
printHtmlPart(13)
invokeTag('message','g',26,['code':("business.industry.label")],-1)
printHtmlPart(12)
invokeTag('select','g',27,['class':("select-list"),'name':("industry.id"),'from':(Industry.list()),'value':(businessInstance?.industry?.id),'optionKey':("id")],-1)
printHtmlPart(14)
invokeTag('message','g',29,['code':("business.incorpDate.label"),'default':("Incorp Date")],-1)
printHtmlPart(12)
invokeTag('datePicker','g',30,['class':("select-list"),'name':("incorpDate"),'value':(businessInstance?.incorpDate),'precision':("day")],-1)
printHtmlPart(15)
expressionOut.print(formatDate(format:'dd/MM/yyyy', date:businessInstance?.incorpDate))
printHtmlPart(16)
invokeTag('message','g',33,['code':("business.address.label"),'default':("Address")],-1)
printHtmlPart(12)
invokeTag('textArea','g',34,['class':("text-input"),'name':("address"),'value':(businessInstance?.address)],-1)
printHtmlPart(13)
invokeTag('message','g',36,['code':("business.city.label"),'default':("City")],-1)
printHtmlPart(12)
invokeTag('textField','g',37,['class':("text-input"),'name':("city"),'value':(businessInstance?.city)],-1)
printHtmlPart(17)
invokeTag('message','g',39,['code':("actions.login.submit")],-1)
printHtmlPart(18)
})
invokeTag('form','g',40,['url':([resource:businessInstance, action:'update']),'method':("PUT")],2)
printHtmlPart(19)
})
invokeTag('captureBody','sitemesh',44,[:],1)
printHtmlPart(20)
}
public static final Map JSP_TAGS = new HashMap()
protected void init() {
	this.jspTags = JSP_TAGS
}
public static final String CONTENT_TYPE = 'text/html;charset=UTF-8'
public static final long LAST_MODIFIED = 1442317648779L
public static final String EXPRESSION_CODEC = 'html'
public static final String STATIC_CODEC = 'none'
public static final String OUT_CODEC = 'html'
public static final String TAGLIB_CODEC = 'none'
}
