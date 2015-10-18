import org.codehaus.groovy.grails.plugins.metadata.GrailsPlugin
import org.codehaus.groovy.grails.web.pages.GroovyPage
import org.codehaus.groovy.grails.web.taglib.*
import org.codehaus.groovy.grails.web.taglib.exceptions.GrailsTagException
import org.springframework.web.util.*
import grails.util.GrailsUtil

class gsp_sme_layoutssmepage_gsp extends GroovyPage {
public String getGroovyPageFileName() { "/WEB-INF/grails-app/views/layouts/smepage.gsp" }
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
expressionOut.print(assetPath(src: 'favicon_1.ico'))
printHtmlPart(3)
expressionOut.print(resource(dir:'stylesheets',file:'application.css'))
printHtmlPart(4)
invokeTag('javascript','asset',9,['src':("application.js")],-1)
printHtmlPart(1)
invokeTag('javascript','asset',10,['src':("jquery-1.11.1.js")],-1)
printHtmlPart(1)
invokeTag('javascript','asset',11,['src':("jquery-ui-1.10.3.custom.js")],-1)
printHtmlPart(5)
invokeTag('layoutHead','g',14,[:],-1)
printHtmlPart(6)
invokeTag('layoutResources','r',18,[:],-1)
printHtmlPart(7)
})
invokeTag('captureHead','sitemesh',19,[:],1)
printHtmlPart(8)
createTagBody(1, {->
printHtmlPart(9)
expressionOut.print(resource(dir: 'images', file: 'user_login.png'))
printHtmlPart(10)
expressionOut.print(session.user?.name)
printHtmlPart(11)
expressionOut.print(session.user?.role?.name)
printHtmlPart(12)
createTagBody(2, {->
printHtmlPart(13)
expressionOut.print(resource(dir: 'images', file: 'sign_out.png'))
printHtmlPart(14)
invokeTag('message','g',30,['code':("actions.login.logout")],-1)
printHtmlPart(15)
})
invokeTag('link','g',31,['controller':("login"),'action':("logout")],2)
printHtmlPart(16)
expressionOut.print(resource(dir: 'images', file: 'cube.jpg'))
printHtmlPart(17)
expressionOut.print(session?.company.name)
printHtmlPart(18)
createTagBody(2, {->
printHtmlPart(19)
expressionOut.print(resource(dir: 'images', file: 'data_analysis.png'))
printHtmlPart(20)
invokeTag('message','g',50,['code':("sme.captions.transactions")],-1)
printHtmlPart(21)
})
invokeTag('link','g',51,['controller':("smehome"),'action':("index"),'class':("tile"),'style':("color: white;")],2)
printHtmlPart(22)
createTagBody(2, {->
printHtmlPart(19)
expressionOut.print(resource(dir: 'images', file: 'date_settings_white.png'))
printHtmlPart(23)
invokeTag('message','g',57,['code':("sme.captions.profile"),'default':("Profile")],-1)
printHtmlPart(21)
})
invokeTag('link','g',58,['controller':("smehome"),'action':("profile"),'class':("tile"),'style':("color: white;")],2)
printHtmlPart(24)
createTagBody(2, {->
printHtmlPart(19)
expressionOut.print(resource(dir: 'images', file: 'bill.png'))
printHtmlPart(23)
invokeTag('message','g',64,['code':("default.application.billing"),'default':("Billing")],-1)
printHtmlPart(21)
})
invokeTag('link','g',65,['controller':("smehome"),'action':("index"),'class':("tile"),'style':("color: white;")],2)
printHtmlPart(25)
invokeTag('layoutBody','g',70,[:],-1)
printHtmlPart(26)
})
invokeTag('captureBody','sitemesh',72,[:],1)
printHtmlPart(27)
}
public static final Map JSP_TAGS = new HashMap()
protected void init() {
	this.jspTags = JSP_TAGS
}
public static final String CONTENT_TYPE = 'text/html;charset=UTF-8'
public static final long LAST_MODIFIED = 1442814435616L
public static final String EXPRESSION_CODEC = 'html'
public static final String STATIC_CODEC = 'none'
public static final String OUT_CODEC = 'html'
public static final String TAGLIB_CODEC = 'none'
}
