import org.codehaus.groovy.grails.plugins.metadata.GrailsPlugin
import org.codehaus.groovy.grails.web.pages.GroovyPage
import org.codehaus.groovy.grails.web.taglib.*
import org.codehaus.groovy.grails.web.taglib.exceptions.GrailsTagException
import org.springframework.web.util.*
import grails.util.GrailsUtil

class gsp_sme_layoutsadminpage_gsp extends GroovyPage {
public String getGroovyPageFileName() { "/WEB-INF/grails-app/views/layouts/adminpage.gsp" }
public Object run() {
Writer out = getOut()
Writer expressionOut = getExpressionOut()
registerSitemeshPreprocessMode()
printHtmlPart(0)
createTagBody(1, {->
printHtmlPart(1)
invokeTag('captureMeta','sitemesh',5,['gsp_sm_xmlClosingForEmptyTag':(""),'http-equiv':("Content-Type"),'content':("text/html; charset=UTF-8")],-1)
printHtmlPart(1)
createTagBody(2, {->
createTagBody(3, {->
invokeTag('layoutTitle','g',6,['default':("SME")],-1)
})
invokeTag('captureTitle','sitemesh',6,[:],3)
})
invokeTag('wrapTitleTag','sitemesh',6,[:],2)
printHtmlPart(2)
expressionOut.print(resource(dir:'stylesheets',file:'application.css'))
printHtmlPart(3)
invokeTag('javascript','asset',8,['src':("application.js")],-1)
printHtmlPart(4)
invokeTag('javascript','asset',9,['src':("jquery-1.11.1.js")],-1)
printHtmlPart(4)
invokeTag('javascript','asset',10,['src':("jquery-ui-1.10.3.custom.js")],-1)
printHtmlPart(5)
invokeTag('layoutHead','g',13,[:],-1)
printHtmlPart(6)
invokeTag('layoutResources','r',17,[:],-1)
printHtmlPart(7)
})
invokeTag('captureHead','sitemesh',18,[:],1)
printHtmlPart(7)
createTagBody(1, {->
printHtmlPart(8)
expressionOut.print(resource(dir: 'images', file: 'user_login.png'))
printHtmlPart(9)
expressionOut.print(session.user?.name)
printHtmlPart(10)
expressionOut.print(session.user?.role?.name)
printHtmlPart(11)
createTagBody(2, {->
printHtmlPart(12)
expressionOut.print(resource(dir: 'images', file: 'sign_out.png'))
printHtmlPart(13)
invokeTag('message','g',29,['code':("actions.login.logout")],-1)
printHtmlPart(14)
})
invokeTag('link','g',30,['controller':("login"),'action':("logout")],2)
printHtmlPart(15)
expressionOut.print(resource(dir: 'images', file: 'cube.jpg'))
printHtmlPart(16)
invokeTag('message','g',37,['code':("default.application.title")],-1)
printHtmlPart(17)
createTagBody(2, {->
printHtmlPart(18)
expressionOut.print(resource(dir: 'images', file: 'office.png'))
printHtmlPart(19)
invokeTag('message','g',46,['code':("business.list")],-1)
printHtmlPart(20)
})
invokeTag('link','g',47,['controller':("adminHome"),'action':("index"),'class':("tile"),'style':("color: white;")],2)
printHtmlPart(21)
createTagBody(2, {->
printHtmlPart(18)
expressionOut.print(resource(dir: 'images', file: 'customers.png'))
printHtmlPart(22)
invokeTag('message','g',53,['code':("user.list")],-1)
printHtmlPart(20)
})
invokeTag('link','g',54,['controller':("user"),'action':("index"),'class':("tile"),'style':("color: white;")],2)
printHtmlPart(21)
createTagBody(2, {->
printHtmlPart(18)
expressionOut.print(resource(dir: 'images', file: 'bill.png'))
printHtmlPart(22)
invokeTag('message','g',60,['code':("default.application.billing"),'default':("Billing")],-1)
printHtmlPart(20)
})
invokeTag('link','g',61,['controller':("user"),'action':("index"),'class':("tile"),'style':("color: white;")],2)
printHtmlPart(21)
createTagBody(2, {->
printHtmlPart(18)
expressionOut.print(resource(dir: 'images', file: 'date_settings_white.png'))
printHtmlPart(22)
invokeTag('message','g',67,['code':("default.application.profiles"),'default':("Profiles")],-1)
printHtmlPart(20)
})
invokeTag('link','g',68,['controller':("profile"),'action':("index"),'class':("tile"),'style':("color: white;")],2)
printHtmlPart(23)
createTagBody(2, {->
printHtmlPart(18)
expressionOut.print(resource(dir: 'images', file: 'customize.png'))
printHtmlPart(22)
invokeTag('message','g',74,['code':("default.application.settings")],-1)
printHtmlPart(20)
})
invokeTag('link','g',75,['controller':("genericOperation"),'action':("index"),'class':("tile"),'style':("color: white;")],2)
printHtmlPart(24)
invokeTag('layoutBody','g',79,[:],-1)
printHtmlPart(25)
})
invokeTag('captureBody','sitemesh',81,[:],1)
printHtmlPart(26)
}
public static final Map JSP_TAGS = new HashMap()
protected void init() {
	this.jspTags = JSP_TAGS
}
public static final String CONTENT_TYPE = 'text/html;charset=UTF-8'
public static final long LAST_MODIFIED = 1443869446340L
public static final String EXPRESSION_CODEC = 'html'
public static final String STATIC_CODEC = 'none'
public static final String OUT_CODEC = 'html'
public static final String TAGLIB_CODEC = 'none'
}
