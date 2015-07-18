import org.codehaus.groovy.grails.plugins.metadata.GrailsPlugin
import org.codehaus.groovy.grails.web.pages.GroovyPage
import org.codehaus.groovy.grails.web.taglib.*
import org.codehaus.groovy.grails.web.taglib.exceptions.GrailsTagException
import org.springframework.web.util.*
import grails.util.GrailsUtil

@GrailsPlugin(name='modaldynamix', version='0.13')
class gsp_modaldynamix_modaldynamix_modaldymension_gsp extends GroovyPage {
public String getGroovyPageFileName() { "/WEB-INF/plugins/modaldynamix-0.13/grails-app/views/modaldynamix/_modaldymension.gsp" }
public Object run() {
Writer out = getOut()
Writer expressionOut = getExpressionOut()
registerSitemeshPreprocessMode()
createTagBody(1, {->
printHtmlPart(0)
expressionOut.print(attrs.id)
printHtmlPart(1)
if(true && (attrs.position)) {
printHtmlPart(2)
expressionOut.print(attrs.position)
printHtmlPart(3)
}
printHtmlPart(4)
if(true && (attrs.margintop)) {
printHtmlPart(5)
expressionOut.print(attrs.top)
printHtmlPart(6)
}
printHtmlPart(7)
if(true && (attrs.margintop)) {
printHtmlPart(8)
expressionOut.print(attrs.margintop)
printHtmlPart(6)
}
printHtmlPart(7)
if(true && (attrs.left)) {
printHtmlPart(9)
expressionOut.print(attrs.left)
printHtmlPart(6)
}
printHtmlPart(7)
if(true && (attrs.right)) {
printHtmlPart(10)
expressionOut.print(attrs.right)
printHtmlPart(6)
}
printHtmlPart(7)
if(true && (attrs.marginright)) {
printHtmlPart(11)
expressionOut.print(attrs.marginright)
printHtmlPart(6)
}
printHtmlPart(7)
if(true && (attrs.left)) {
printHtmlPart(12)
expressionOut.print(attrs.marginleft)
printHtmlPart(6)
}
printHtmlPart(7)
if(true && (attrs.height)) {
printHtmlPart(7)
if(true && (((attrs.height.indexOf('px')>-1)||(attrs.height.indexOf('em')>-1)||(attrs.height.indexOf('auto')>-1)))) {
printHtmlPart(13)
expressionOut.print(attrs.height)
printHtmlPart(6)
}
else {
printHtmlPart(14)
expressionOut.print(attrs.calctype)
expressionOut.print(attrs.height)
printHtmlPart(15)
}
printHtmlPart(7)
}
printHtmlPart(7)
if(true && (attrs.width)) {
printHtmlPart(7)
if(true && (((attrs.width.indexOf('px')>-1)||(attrs.width.indexOf('em')>-1)||(attrs.width.indexOf('auto')>-1)))) {
printHtmlPart(16)
expressionOut.print(attrs.width)
printHtmlPart(6)
}
else {
printHtmlPart(17)
expressionOut.print(attrs.calctype)
expressionOut.print(attrs.width)
printHtmlPart(15)
}
printHtmlPart(7)
}
printHtmlPart(7)
if(true && (attrs.overflow)) {
printHtmlPart(18)
expressionOut.print(attrs.overflow)
printHtmlPart(6)
}
printHtmlPart(7)
if(true && (attrs.bodyheight)) {
printHtmlPart(7)
if(true && (((attrs.bodyheight.indexOf('px')>-1)||(attrs.bodyheight.indexOf('em')>-1)||(attrs.bodyheight.indexOf('auto')>-1)))) {
printHtmlPart(19)
expressionOut.print(attrs.bodyheight)
printHtmlPart(6)
}
else {
printHtmlPart(20)
expressionOut.print(attrs.calctype)
expressionOut.print(attrs.bodyheight)
printHtmlPart(15)
}
printHtmlPart(7)
}
printHtmlPart(7)
if(true && (attrs.bodywidth)) {
printHtmlPart(21)
expressionOut.print(attrs.bodywidth)
printHtmlPart(6)
}
printHtmlPart(22)
})
invokeTag('javascript','g',1,[:],1)
}
public static final Map JSP_TAGS = new HashMap()
protected void init() {
	this.jspTags = JSP_TAGS
}
public static final String CONTENT_TYPE = 'text/html;charset=UTF-8'
public static final long LAST_MODIFIED = 1427270394000L
public static final String EXPRESSION_CODEC = 'html'
public static final String STATIC_CODEC = 'none'
public static final String OUT_CODEC = 'none'
public static final String TAGLIB_CODEC = 'none'
}
