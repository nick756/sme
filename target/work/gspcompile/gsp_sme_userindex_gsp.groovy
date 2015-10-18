import com.sme.entities.User
import org.codehaus.groovy.grails.plugins.metadata.GrailsPlugin
import org.codehaus.groovy.grails.web.pages.GroovyPage
import org.codehaus.groovy.grails.web.taglib.*
import org.codehaus.groovy.grails.web.taglib.exceptions.GrailsTagException
import org.springframework.web.util.*
import grails.util.GrailsUtil

class gsp_sme_userindex_gsp extends GroovyPage {
public String getGroovyPageFileName() { "/WEB-INF/grails-app/views/user/index.gsp" }
public Object run() {
Writer out = getOut()
Writer expressionOut = getExpressionOut()
registerSitemeshPreprocessMode()
printHtmlPart(0)
printHtmlPart(1)
createTagBody(1, {->
printHtmlPart(2)
invokeTag('captureMeta','sitemesh',6,['gsp_sm_xmlClosingForEmptyTag':(""),'name':("layout"),'content':("adminpage")],-1)
printHtmlPart(2)
invokeTag('set','g',7,['var':("entityName"),'value':(message(code: 'user.label', default: 'User'))],-1)
printHtmlPart(2)
createTagBody(2, {->
createTagBody(3, {->
invokeTag('message','g',8,['code':("default.list.label"),'args':([entityName])],-1)
})
invokeTag('captureTitle','sitemesh',8,[:],3)
})
invokeTag('wrapTitleTag','sitemesh',8,[:],2)
printHtmlPart(3)
})
invokeTag('captureHead','sitemesh',9,[:],1)
printHtmlPart(3)
createTagBody(1, {->
printHtmlPart(4)
invokeTag('message','g',11,['code':("default.link.skip.label"),'default':("Skip to content&hellip;")],-1)
printHtmlPart(5)
createTagBody(2, {->
printHtmlPart(6)
expressionOut.print(resource(dir: 'images', file: 'arrow_left.png'))
printHtmlPart(7)
invokeTag('message','g',14,['code':("actions.back")],-1)
printHtmlPart(8)
})
invokeTag('link','g',14,['style':("float: left;"),'controller':("adminHome"),'action':("index"),'params':(['max': params.max, 'offset': params.offset])],2)
printHtmlPart(9)
createTagBody(2, {->
invokeTag('message','g',15,['code':("default.new.label"),'args':([entityName])],-1)
})
invokeTag('link','g',15,['class':("create"),'action':("create")],2)
printHtmlPart(10)
invokeTag('message','g',19,['code':("default.list.label"),'args':([entityName])],-1)
printHtmlPart(11)
if(true && (flash.message)) {
printHtmlPart(12)
expressionOut.print(flash.message)
printHtmlPart(13)
}
printHtmlPart(14)
invokeTag('sortableColumn','g',27,['property':("name"),'title':(message(code: 'user.name.label', default: 'Name'))],-1)
printHtmlPart(15)
invokeTag('sortableColumn','g',29,['property':("login"),'title':(message(code: 'user.login.label', default: 'Login'))],-1)
printHtmlPart(15)
invokeTag('sortableColumn','g',31,['property':("passw"),'title':(message(code: 'user.passw.label', default: 'Passw'))],-1)
printHtmlPart(15)
invokeTag('sortableColumn','g',33,['property':("contactNo"),'title':(message(code: 'user.contactNo.label', default: 'Contact No'))],-1)
printHtmlPart(15)
invokeTag('sortableColumn','g',35,['property':("email"),'title':(message(code: 'user.email.label', default: 'Email'))],-1)
printHtmlPart(16)
invokeTag('message','g',37,['code':("user.role.label"),'default':("Role")],-1)
printHtmlPart(17)
loop:{
int i = 0
for( userInstance in (userInstanceList) ) {
printHtmlPart(18)
expressionOut.print((i % 2) == 0 ? 'even' : 'odd')
printHtmlPart(19)
createTagBody(3, {->
expressionOut.print(fieldValue(bean: userInstance, field: "name"))
})
invokeTag('link','g',46,['action':("show"),'id':(userInstance.id)],3)
printHtmlPart(20)
expressionOut.print(fieldValue(bean: userInstance, field: "login"))
printHtmlPart(20)
expressionOut.print(fieldValue(bean: userInstance, field: "passw"))
printHtmlPart(20)
expressionOut.print(fieldValue(bean: userInstance, field: "contactNo"))
printHtmlPart(20)
expressionOut.print(fieldValue(bean: userInstance, field: "email"))
printHtmlPart(20)
expressionOut.print(fieldValue(bean: userInstance, field: "role"))
printHtmlPart(21)
expressionOut.print(userInstance?.company?.name)
printHtmlPart(22)
i++
}
}
printHtmlPart(23)
invokeTag('paginate','g',64,['total':(userInstanceCount ?: 0)],-1)
printHtmlPart(24)
})
invokeTag('captureBody','sitemesh',67,[:],1)
printHtmlPart(25)
}
public static final Map JSP_TAGS = new HashMap()
protected void init() {
	this.jspTags = JSP_TAGS
}
public static final String CONTENT_TYPE = 'text/html;charset=UTF-8'
public static final long LAST_MODIFIED = 1442283074375L
public static final String EXPRESSION_CODEC = 'html'
public static final String STATIC_CODEC = 'none'
public static final String OUT_CODEC = 'html'
public static final String TAGLIB_CODEC = 'none'
}
