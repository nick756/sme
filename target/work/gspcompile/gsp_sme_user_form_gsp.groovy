import com.sme.entities.User
import org.codehaus.groovy.grails.plugins.metadata.GrailsPlugin
import org.codehaus.groovy.grails.web.pages.GroovyPage
import org.codehaus.groovy.grails.web.taglib.*
import org.codehaus.groovy.grails.web.taglib.exceptions.GrailsTagException
import org.springframework.web.util.*
import grails.util.GrailsUtil

class gsp_sme_user_form_gsp extends GroovyPage {
public String getGroovyPageFileName() { "/WEB-INF/grails-app/views/user/_form.gsp" }
public Object run() {
Writer out = getOut()
Writer expressionOut = getExpressionOut()
registerSitemeshPreprocessMode()
printHtmlPart(0)
expressionOut.print(hasErrors(bean: userInstance, field: 'name', 'error'))
printHtmlPart(1)
invokeTag('message','g',7,['code':("user.name.label"),'default':("Name")],-1)
printHtmlPart(2)
invokeTag('textField','g',10,['name':("name"),'required':(""),'value':(userInstance?.name)],-1)
printHtmlPart(3)
expressionOut.print(hasErrors(bean: userInstance, field: 'login', 'error'))
printHtmlPart(4)
invokeTag('message','g',16,['code':("user.login.label"),'default':("Login")],-1)
printHtmlPart(2)
invokeTag('textField','g',19,['name':("login"),'required':(""),'value':(userInstance?.login)],-1)
printHtmlPart(3)
expressionOut.print(hasErrors(bean: userInstance, field: 'passw', 'error'))
printHtmlPart(5)
invokeTag('message','g',25,['code':("user.passw.label"),'default':("Passw")],-1)
printHtmlPart(2)
invokeTag('textField','g',28,['name':("passw"),'maxlength':("10"),'required':(""),'value':(userInstance?.passw)],-1)
printHtmlPart(3)
expressionOut.print(hasErrors(bean: userInstance, field: 'contactNo', 'error'))
printHtmlPart(6)
invokeTag('message','g',34,['code':("user.contactNo.label"),'default':("Contact No")],-1)
printHtmlPart(7)
invokeTag('textField','g',37,['name':("contactNo"),'value':(userInstance?.contactNo)],-1)
printHtmlPart(3)
expressionOut.print(hasErrors(bean: userInstance, field: 'email', 'error'))
printHtmlPart(8)
invokeTag('message','g',43,['code':("user.email.label"),'default':("Email")],-1)
printHtmlPart(7)
invokeTag('field','g',46,['type':("email"),'name':("email"),'value':(userInstance?.email)],-1)
printHtmlPart(3)
expressionOut.print(hasErrors(bean: userInstance, field: 'role', 'error'))
printHtmlPart(9)
invokeTag('message','g',52,['code':("user.role.label"),'default':("Role")],-1)
printHtmlPart(2)
invokeTag('select','g',55,['id':("role"),'name':("role.id"),'from':(com.sme.entities.UserRole.list()),'optionKey':("id"),'required':(""),'value':(userInstance?.role?.id),'class':("many-to-one")],-1)
printHtmlPart(3)
expressionOut.print(hasErrors(bean: userInstance, field: 'company', 'error'))
printHtmlPart(10)
invokeTag('message','g',61,['code':("user.company.label"),'default':("Company")],-1)
printHtmlPart(7)
invokeTag('select','g',64,['id':("company"),'name':("company.id"),'from':(com.sme.entities.Business.list()),'optionKey':("id"),'value':(userInstance?.company?.id),'class':("many-to-one"),'noSelection':(['null': ''])],-1)
printHtmlPart(11)
}
public static final Map JSP_TAGS = new HashMap()
protected void init() {
	this.jspTags = JSP_TAGS
}
public static final String CONTENT_TYPE = 'text/html;charset=UTF-8'
public static final long LAST_MODIFIED = 1433224875181L
public static final String EXPRESSION_CODEC = 'html'
public static final String STATIC_CODEC = 'none'
public static final String OUT_CODEC = 'none'
public static final String TAGLIB_CODEC = 'none'
}
