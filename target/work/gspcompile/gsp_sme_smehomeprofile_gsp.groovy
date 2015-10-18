import com.sme.entities.*
import org.codehaus.groovy.grails.plugins.metadata.GrailsPlugin
import org.codehaus.groovy.grails.web.pages.GroovyPage
import org.codehaus.groovy.grails.web.taglib.*
import org.codehaus.groovy.grails.web.taglib.exceptions.GrailsTagException
import org.springframework.web.util.*
import grails.util.GrailsUtil

class gsp_sme_smehomeprofile_gsp extends GroovyPage {
public String getGroovyPageFileName() { "/WEB-INF/grails-app/views/smehome/profile.gsp" }
public Object run() {
Writer out = getOut()
Writer expressionOut = getExpressionOut()
registerSitemeshPreprocessMode()
printHtmlPart(0)
printHtmlPart(1)
createTagBody(1, {->
printHtmlPart(2)
invokeTag('captureMeta','sitemesh',6,['gsp_sm_xmlClosingForEmptyTag':(""),'http-equiv':("Content-Type"),'content':("text/html; charset=UTF-8")],-1)
printHtmlPart(2)
invokeTag('captureMeta','sitemesh',7,['gsp_sm_xmlClosingForEmptyTag':("/"),'name':("layout"),'content':("smepage")],-1)
printHtmlPart(2)
createTagBody(2, {->
createClosureForHtmlPart(3, 3)
invokeTag('captureTitle','sitemesh',8,[:],3)
})
invokeTag('wrapTitleTag','sitemesh',8,[:],2)
printHtmlPart(4)
})
invokeTag('captureHead','sitemesh',9,[:],1)
printHtmlPart(4)
createTagBody(1, {->
printHtmlPart(5)
createTagBody(2, {->
printHtmlPart(6)
expressionOut.print(resource(dir: 'images', file: 'arrow_left.png'))
printHtmlPart(7)
invokeTag('message','g',12,['code':("actions.transaction.add")],-1)
printHtmlPart(8)
})
invokeTag('link','g',12,['style':("float: left; margin-left: 5px;"),'action':("index"),'params':(['max': params.max, 'offset': params.offset, 'id': businessInstance?.id])],2)
printHtmlPart(9)
createTagBody(2, {->
printHtmlPart(10)
expressionOut.print(resource(dir: 'images', file: 'edit.png'))
printHtmlPart(7)
invokeTag('message','g',13,['code':("actions.edit")],-1)
printHtmlPart(8)
})
invokeTag('link','g',13,['style':("float: right; margin-right: 5px;"),'action':("editprofile"),'params':(['max': params.max, 'offset': params.offset, 'id': businessInstance?.id])],2)
printHtmlPart(11)
invokeTag('message','g',18,['code':("business.name.label")],-1)
printHtmlPart(12)
invokeTag('textField','g',19,['class':("text-input"),'name':("name"),'value':(businessInstance?.name),'readonly':("true")],-1)
printHtmlPart(13)
invokeTag('message','g',21,['code':("business.profile.label")],-1)
printHtmlPart(12)
invokeTag('textField','g',22,['class':("text-input"),'name':("name"),'value':(businessInstance?.profile?.name),'readonly':("true")],-1)
printHtmlPart(14)
invokeTag('message','g',24,['code':("business.regNumber.label"),'default':("No")],-1)
printHtmlPart(12)
invokeTag('textField','g',25,['class':("text-input"),'name':("regNumber"),'value':(businessInstance?.regNumber),'readonly':("true")],-1)
printHtmlPart(13)
invokeTag('message','g',27,['code':("business.industry.label"),'default':("No")],-1)
printHtmlPart(12)
invokeTag('textField','g',28,['class':("text-input"),'name':("regNumber"),'value':(businessInstance?.industry?.name),'readonly':("true")],-1)
printHtmlPart(15)
invokeTag('message','g',30,['code':("business.incorpDate.label"),'default':("Incorp Date")],-1)
printHtmlPart(12)
invokeTag('textField','g',31,['class':("text-input"),'name':("regNumber"),'value':(formatDate(format:'dd/MM/yyyy', date:businessInstance?.incorpDate)),'readonly':("true")],-1)
printHtmlPart(16)
invokeTag('message','g',33,['code':("business.address.label"),'default':("Address")],-1)
printHtmlPart(12)
invokeTag('textArea','g',34,['class':("text-input"),'name':("address"),'value':(businessInstance?.address),'readonly':("true")],-1)
printHtmlPart(13)
invokeTag('message','g',36,['code':("business.city.label"),'default':("City")],-1)
printHtmlPart(12)
invokeTag('textField','g',37,['class':("text-input"),'name':("address"),'value':(businessInstance?.city),'readonly':("true")],-1)
printHtmlPart(17)
})
invokeTag('captureBody','sitemesh',40,[:],1)
printHtmlPart(18)
}
public static final Map JSP_TAGS = new HashMap()
protected void init() {
	this.jspTags = JSP_TAGS
}
public static final String CONTENT_TYPE = 'text/html;charset=UTF-8'
public static final long LAST_MODIFIED = 1442728231205L
public static final String EXPRESSION_CODEC = 'html'
public static final String STATIC_CODEC = 'none'
public static final String OUT_CODEC = 'html'
public static final String TAGLIB_CODEC = 'none'
}
