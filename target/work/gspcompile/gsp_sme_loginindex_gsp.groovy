import org.codehaus.groovy.grails.plugins.metadata.GrailsPlugin
import org.codehaus.groovy.grails.web.pages.GroovyPage
import org.codehaus.groovy.grails.web.taglib.*
import org.codehaus.groovy.grails.web.taglib.exceptions.GrailsTagException
import org.springframework.web.util.*
import grails.util.GrailsUtil

class gsp_sme_loginindex_gsp extends GroovyPage {
public String getGroovyPageFileName() { "/WEB-INF/grails-app/views/login/index.gsp" }
public Object run() {
Writer out = getOut()
Writer expressionOut = getExpressionOut()
registerSitemeshPreprocessMode()
printHtmlPart(0)
createTagBody(1, {->
printHtmlPart(1)
invokeTag('captureMeta','sitemesh',5,['gsp_sm_xmlClosingForEmptyTag':("/"),'name':("layout"),'content':("loginpage")],-1)
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
if(true && (!session?.user)) {
printHtmlPart(5)
createTagBody(3, {->
printHtmlPart(6)
invokeTag('message','g',13,['code':("actions.login.name")],-1)
printHtmlPart(7)
invokeTag('textField','g',14,['name':("login"),'class':("text-input"),'style':("width: 250px;")],-1)
printHtmlPart(8)
invokeTag('message','g',16,['code':("actions.login.password")],-1)
printHtmlPart(7)
invokeTag('passwordField','g',17,['name':("passw"),'class':("text-input"),'style':("width: 250px;")],-1)
printHtmlPart(9)
invokeTag('message','g',20,['code':("actions.login.submit")],-1)
printHtmlPart(10)
})
invokeTag('form','g',21,['name':("loginForm"),'controller':("login"),'action':("process")],3)
printHtmlPart(5)
invokeTag('renderErrors','g',22,['bean':(loginCmd)],-1)
printHtmlPart(11)
}
else {
printHtmlPart(12)
invokeTag('message','g',25,['code':("actions.login.logged")],-1)
printHtmlPart(13)
expressionOut.print(session?.user?.name)
printHtmlPart(14)
createTagBody(3, {->
printHtmlPart(15)
invokeTag('message','g',28,['code':("actions.login.logout")],-1)
printHtmlPart(16)
})
invokeTag('link','g',30,['class':("log-out-link"),'controller':("login"),'action':("logout")],3)
printHtmlPart(11)
}
printHtmlPart(17)
})
invokeTag('captureBody','sitemesh',34,[:],1)
printHtmlPart(18)
}
public static final Map JSP_TAGS = new HashMap()
protected void init() {
	this.jspTags = JSP_TAGS
}
public static final String CONTENT_TYPE = 'text/html;charset=UTF-8'
public static final long LAST_MODIFIED = 1434185215490L
public static final String EXPRESSION_CODEC = 'html'
public static final String STATIC_CODEC = 'none'
public static final String OUT_CODEC = 'html'
public static final String TAGLIB_CODEC = 'none'
}
