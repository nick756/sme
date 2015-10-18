import org.codehaus.groovy.grails.plugins.metadata.GrailsPlugin
import org.codehaus.groovy.grails.web.pages.GroovyPage
import org.codehaus.groovy.grails.web.taglib.*
import org.codehaus.groovy.grails.web.taglib.exceptions.GrailsTagException
import org.springframework.web.util.*
import grails.util.GrailsUtil

class gsp_sme_layoutsreportpage_gsp extends GroovyPage {
public String getGroovyPageFileName() { "/WEB-INF/grails-app/views/layouts/reportpage.gsp" }
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
createTagBody(2, {->
createTagBody(3, {->
invokeTag('layoutTitle','g',12,['default':("SIFAR")],-1)
})
invokeTag('captureTitle','sitemesh',12,[:],3)
})
invokeTag('wrapTitleTag','sitemesh',12,[:],2)
printHtmlPart(3)
expressionOut.print(assetPath(src: 'favicon_1.ico'))
printHtmlPart(4)
expressionOut.print(resource(dir:'stylesheets',file:'application.css'))
printHtmlPart(5)
invokeTag('layoutHead','g',16,[:],-1)
printHtmlPart(6)
})
invokeTag('captureHead','sitemesh',17,[:],1)
printHtmlPart(6)
createTagBody(1, {->
printHtmlPart(2)
invokeTag('layoutBody','g',19,[:],-1)
printHtmlPart(6)
})
invokeTag('captureBody','sitemesh',20,[:],1)
printHtmlPart(7)
}
public static final Map JSP_TAGS = new HashMap()
protected void init() {
	this.jspTags = JSP_TAGS
}
public static final String CONTENT_TYPE = 'text/html;charset=UTF-8'
public static final long LAST_MODIFIED = 1443348301584L
public static final String EXPRESSION_CODEC = 'html'
public static final String STATIC_CODEC = 'none'
public static final String OUT_CODEC = 'html'
public static final String TAGLIB_CODEC = 'none'
}
