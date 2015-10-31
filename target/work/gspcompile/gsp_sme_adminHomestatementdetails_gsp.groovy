import com.sme.entities.*
import org.codehaus.groovy.grails.plugins.metadata.GrailsPlugin
import org.codehaus.groovy.grails.web.pages.GroovyPage
import org.codehaus.groovy.grails.web.taglib.*
import org.codehaus.groovy.grails.web.taglib.exceptions.GrailsTagException
import org.springframework.web.util.*
import grails.util.GrailsUtil

class gsp_sme_adminHomestatementdetails_gsp extends GroovyPage {
public String getGroovyPageFileName() { "/WEB-INF/grails-app/views/adminHome/statementdetails.gsp" }
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
invokeTag('captureMeta','sitemesh',7,['gsp_sm_xmlClosingForEmptyTag':("/"),'name':("layout"),'content':("reportpage")],-1)
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
printHtmlPart(2)

def sum = 0

printHtmlPart(5)
expressionOut.print(new Date().format("dd/MM/yyyy HH:mm"))
printHtmlPart(6)
expressionOut.print(company?.name)
printHtmlPart(7)
expressionOut.print(summary?.year)
printHtmlPart(8)
invokeTag('formatNumber','g',15,['format':("00"),'number':(summary?.month)],-1)
printHtmlPart(9)
loop:{
int i = 0
for( group in (groups) ) {
printHtmlPart(10)
expressionOut.print(group?.key)
printHtmlPart(11)
if(true && (group?.value.size() == 0)) {
printHtmlPart(12)
}
printHtmlPart(13)

sum = 0

printHtmlPart(13)
loop:{
int k = 0
for( trans in (group.value) ) {
printHtmlPart(14)
expressionOut.print(k+1)
printHtmlPart(15)
invokeTag('formatDate','g',37,['format':("dd/MM/yyyy"),'date':(trans.transactionDate)],-1)
printHtmlPart(16)
expressionOut.print(trans.transactionRemarks)
printHtmlPart(17)
invokeTag('formatNumber','g',41,['format':("#,##0.00"),'number':(trans.transactionAmount)],-1)
printHtmlPart(18)

sum += trans.transactionAmount

printHtmlPart(19)
k++
}
}
printHtmlPart(20)
expressionOut.print(formatNumber(format: '#,##0.00', number: sum))
printHtmlPart(21)
i++
}
}
printHtmlPart(22)
invokeTag('formatNumber','g',56,['format':("#,##0.00"),'number':(summary?.outflow)],-1)
printHtmlPart(23)
invokeTag('formatNumber','g',60,['format':("#,##0.00"),'number':(summary?.nettAmount)],-1)
printHtmlPart(24)
invokeTag('formatNumber','g',65,['format':("#,##0.00"),'number':(summary?.openingBalance)],-1)
printHtmlPart(25)
invokeTag('formatNumber','g',69,['format':("#,##0.00"),'number':(summary?.nettAmount)],-1)
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
public static final long LAST_MODIFIED = 1443353716408L
public static final String EXPRESSION_CODEC = 'html'
public static final String STATIC_CODEC = 'none'
public static final String OUT_CODEC = 'none'
public static final String TAGLIB_CODEC = 'none'
}
