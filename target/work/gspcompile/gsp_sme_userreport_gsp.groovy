import org.codehaus.groovy.grails.plugins.metadata.GrailsPlugin
import org.codehaus.groovy.grails.web.pages.GroovyPage
import org.codehaus.groovy.grails.web.taglib.*
import org.codehaus.groovy.grails.web.taglib.exceptions.GrailsTagException
import org.springframework.web.util.*
import grails.util.GrailsUtil

class gsp_sme_userreport_gsp extends GroovyPage {
public String getGroovyPageFileName() { "/WEB-INF/grails-app/views/user/report.gsp" }
public Object run() {
Writer out = getOut()
Writer expressionOut = getExpressionOut()
registerSitemeshPreprocessMode()
printHtmlPart(0)
printHtmlPart(1)
createTagBody(1, {->
printHtmlPart(2)
invokeTag('captureMeta','sitemesh',11,['gsp_sm_xmlClosingForEmptyTag':(""),'http-equiv':("Content-Type"),'content':("text/html; charset=UTF-8")],-1)
printHtmlPart(2)
invokeTag('captureMeta','sitemesh',12,['gsp_sm_xmlClosingForEmptyTag':(""),'name':("layout"),'content':("reportpage")],-1)
printHtmlPart(2)
createTagBody(2, {->
createClosureForHtmlPart(3, 3)
invokeTag('captureTitle','sitemesh',13,[:],3)
})
invokeTag('wrapTitleTag','sitemesh',13,[:],2)
printHtmlPart(4)
})
invokeTag('captureHead','sitemesh',14,[:],1)
printHtmlPart(4)
createTagBody(1, {->
printHtmlPart(5)
expressionOut.print(new Date().format("dd/MM/yyyy HH:mm"))
printHtmlPart(6)
loop:{
int index = 0
for( userInstance in (usersList) ) {
printHtmlPart(7)
expressionOut.print(index + 1)
printHtmlPart(8)
expressionOut.print(userInstance?.name)
printHtmlPart(8)
expressionOut.print(userInstance?.login)
printHtmlPart(9)
expressionOut.print(userInstance?.role?.name)
printHtmlPart(8)
expressionOut.print(userInstance?.email)
printHtmlPart(8)
expressionOut.print(userInstance?.company?.name)
printHtmlPart(10)
index++
}
}
printHtmlPart(11)
})
invokeTag('captureBody','sitemesh',38,[:],1)
printHtmlPart(12)
}
public static final Map JSP_TAGS = new HashMap()
protected void init() {
	this.jspTags = JSP_TAGS
}
public static final String CONTENT_TYPE = 'text/html;charset=UTF-8'
public static final long LAST_MODIFIED = 1444977199520L
public static final String EXPRESSION_CODEC = 'html'
public static final String STATIC_CODEC = 'none'
public static final String OUT_CODEC = 'none'
public static final String TAGLIB_CODEC = 'none'
}
