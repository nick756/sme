import com.sme.entities.*
import org.codehaus.groovy.grails.plugins.metadata.GrailsPlugin
import org.codehaus.groovy.grails.web.pages.GroovyPage
import org.codehaus.groovy.grails.web.taglib.*
import org.codehaus.groovy.grails.web.taglib.exceptions.GrailsTagException
import org.springframework.web.util.*
import grails.util.GrailsUtil

class gsp_sme_smehomeeditprofile_gsp extends GroovyPage {
public String getGroovyPageFileName() { "/WEB-INF/grails-app/views/smehome/editprofile.gsp" }
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
invokeTag('link','g',12,['style':("float: left; margin-left: 5px;"),'action':("profile"),'params':(['max': params.max, 'offset': params.offset, 'id': businessInstance?.id])],2)
printHtmlPart(9)
createTagBody(2, {->
printHtmlPart(10)
createTagBody(3, {->
printHtmlPart(11)
if(true && (error in org.springframework.validation.FieldError)) {
printHtmlPart(12)
expressionOut.print(error.field)
printHtmlPart(13)
}
printHtmlPart(14)
invokeTag('message','g',20,['error':(error)],-1)
printHtmlPart(15)
})
invokeTag('eachError','g',21,['bean':(businessInstance),'var':("error")],3)
printHtmlPart(16)
})
invokeTag('hasErrors','g',23,['bean':(businessInstance)],2)
printHtmlPart(17)
createTagBody(2, {->
printHtmlPart(18)
invokeTag('hiddenField','g',26,['name':("operator"),'value':(session?.user.name)],-1)
printHtmlPart(18)
invokeTag('hiddenField','g',27,['name':("company.id"),'value':(session?.company.id)],-1)
printHtmlPart(18)
invokeTag('hiddenField','g',28,['name':("id"),'value':(businessInstance.id)],-1)
printHtmlPart(18)
invokeTag('hiddenField','g',29,['name':("version"),'value':(businessInstance?.version)],-1)
printHtmlPart(19)
invokeTag('message','g',31,['code':("business.name.label")],-1)
printHtmlPart(20)
invokeTag('textField','g',32,['class':("text-input"),'name':("name"),'value':(businessInstance?.name)],-1)
printHtmlPart(21)
invokeTag('message','g',34,['code':("business.profile.label")],-1)
printHtmlPart(20)
invokeTag('select','g',35,['class':("select-list"),'name':("profile.id"),'from':(GenericProfile.list()),'value':(businessInstance?.profile?.id),'optionKey':("id")],-1)
printHtmlPart(22)
invokeTag('message','g',38,['code':("business.regNumber.label"),'default':("ROC Number")],-1)
printHtmlPart(20)
invokeTag('textField','g',39,['class':("text-input"),'name':("regNumber"),'value':(businessInstance?.regNumber)],-1)
printHtmlPart(23)
invokeTag('message','g',42,['code':("business.industry.label")],-1)
printHtmlPart(20)
invokeTag('select','g',43,['class':("select-list"),'name':("industry.id"),'from':(Industry.list()),'value':(businessInstance?.industry?.id),'optionKey':("id")],-1)
printHtmlPart(24)
invokeTag('message','g',45,['code':("business.incorpDate.label"),'default':("Incorp Date")],-1)
printHtmlPart(20)
invokeTag('datePicker','g',46,['class':("select-list"),'name':("incorpDate"),'value':(businessInstance?.incorpDate),'precision':("day")],-1)
printHtmlPart(25)
expressionOut.print(formatDate(format:'dd/MM/yyyy', date:businessInstance?.incorpDate))
printHtmlPart(26)
invokeTag('message','g',49,['code':("business.address.label"),'default':("Address")],-1)
printHtmlPart(20)
invokeTag('textArea','g',50,['class':("text-input"),'name':("address"),'value':(businessInstance?.address)],-1)
printHtmlPart(21)
invokeTag('message','g',52,['code':("business.city.label"),'default':("City")],-1)
printHtmlPart(20)
invokeTag('textField','g',53,['class':("text-input"),'name':("city"),'value':(businessInstance?.city)],-1)
printHtmlPart(27)
invokeTag('message','g',55,['code':("actions.login.submit")],-1)
printHtmlPart(28)
invokeTag('actionSubmit','g',56,['class':("myButton"),'action':("updateprofile"),'value':(message(code: 'default.button.update.label', default: 'Update'))],-1)
printHtmlPart(29)
})
invokeTag('form','g',57,['action':("updateprofile"),'method':("PUT")],2)
printHtmlPart(30)
})
invokeTag('captureBody','sitemesh',60,[:],1)
printHtmlPart(31)
}
public static final Map JSP_TAGS = new HashMap()
protected void init() {
	this.jspTags = JSP_TAGS
}
public static final String CONTENT_TYPE = 'text/html;charset=UTF-8'
public static final long LAST_MODIFIED = 1442814303407L
public static final String EXPRESSION_CODEC = 'html'
public static final String STATIC_CODEC = 'none'
public static final String OUT_CODEC = 'html'
public static final String TAGLIB_CODEC = 'none'
}
