import org.codehaus.groovy.grails.plugins.metadata.GrailsPlugin
import org.codehaus.groovy.grails.web.pages.GroovyPage
import org.codehaus.groovy.grails.web.taglib.*
import org.codehaus.groovy.grails.web.taglib.exceptions.GrailsTagException
import org.springframework.web.util.*
import grails.util.GrailsUtil

class gsp_sme_smehomeindex_gsp extends GroovyPage {
public String getGroovyPageFileName() { "/WEB-INF/grails-app/views/smehome/index.gsp" }
public Object run() {
Writer out = getOut()
Writer expressionOut = getExpressionOut()
registerSitemeshPreprocessMode()
printHtmlPart(0)
createTagBody(1, {->
printHtmlPart(1)
invokeTag('captureMeta','sitemesh',5,['gsp_sm_xmlClosingForEmptyTag':(""),'http-equiv':("Content-Type"),'content':("text/html; charset=UTF-8")],-1)
printHtmlPart(1)
invokeTag('captureMeta','sitemesh',6,['gsp_sm_xmlClosingForEmptyTag':("/"),'name':("layout"),'content':("smepage")],-1)
printHtmlPart(1)
createTagBody(2, {->
createClosureForHtmlPart(2, 3)
invokeTag('captureTitle','sitemesh',7,[:],3)
})
invokeTag('wrapTitleTag','sitemesh',7,[:],2)
printHtmlPart(3)
})
invokeTag('captureHead','sitemesh',8,[:],1)
printHtmlPart(3)
createTagBody(1, {->
printHtmlPart(4)
createTagBody(2, {->
printHtmlPart(5)
expressionOut.print(resource(dir: 'images', file: 'add_record.png'))
printHtmlPart(6)
invokeTag('message','g',11,['code':("actions.transaction.add")],-1)
printHtmlPart(7)
})
invokeTag('link','g',11,['style':("float: right; margin-right: 5px;"),'action':("create"),'params':(['max': params.max, 'offset': params.offset, 'id': businessInstance?.id])],2)
printHtmlPart(8)
invokeTag('message','g',14,['code':("default.application.transactionslist")],-1)
printHtmlPart(9)
invokeTag('message','g',17,['code':("businesstransaction.date.label")],-1)
printHtmlPart(10)
invokeTag('message','g',18,['code':("businesstransaction.type.label")],-1)
printHtmlPart(10)
invokeTag('message','g',19,['code':("businesstransaction.amount.label")],-1)
printHtmlPart(10)
invokeTag('message','g',20,['code':("businesstransaction.remarks.label")],-1)
printHtmlPart(10)
invokeTag('message','g',21,['code':("businesstransaction.operator.label")],-1)
printHtmlPart(11)
loop:{
int index = 0
for( transInstance in (transactions) ) {
printHtmlPart(12)
if(true && (new Integer(params.offset) > 0)) {
printHtmlPart(13)
invokeTag('set','g',26,['var':("offset"),'value':(new Integer(params.offset))],-1)
printHtmlPart(13)
}
else {
invokeTag('set','g',28,['var':("offset"),'value':(0)],-1)
}
printHtmlPart(14)
expressionOut.print((index % 2) == 0 ? 'even' : 'odd')
printHtmlPart(15)
expressionOut.print(offset + index + 1)
printHtmlPart(16)
expressionOut.print(formatDate(format:'dd/MM/yyyy', date:transInstance?.transactionDate))
printHtmlPart(17)
expressionOut.print(transInstance?.operationType)
printHtmlPart(18)
invokeTag('formatNumber','g',34,['number':(transInstance?.transactionAmount),'format':("#,##0.00")],-1)
printHtmlPart(17)
expressionOut.print(transInstance?.transactionRemarks)
printHtmlPart(17)
expressionOut.print(transInstance?.operator)
printHtmlPart(19)
index++
}
}
printHtmlPart(20)
invokeTag('paginate','g',42,['total':(counter ?: 0)],-1)
printHtmlPart(21)
invokeTag('message','g',45,['code':("default.application.totalrecords")],-1)
printHtmlPart(22)
expressionOut.print(counter)
printHtmlPart(23)
})
invokeTag('captureBody','sitemesh',47,[:],1)
printHtmlPart(24)
}
public static final Map JSP_TAGS = new HashMap()
protected void init() {
	this.jspTags = JSP_TAGS
}
public static final String CONTENT_TYPE = 'text/html;charset=UTF-8'
public static final long LAST_MODIFIED = 1442317917129L
public static final String EXPRESSION_CODEC = 'html'
public static final String STATIC_CODEC = 'none'
public static final String OUT_CODEC = 'html'
public static final String TAGLIB_CODEC = 'none'
}
