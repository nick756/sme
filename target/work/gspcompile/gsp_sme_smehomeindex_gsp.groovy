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
invokeTag('link','g',11,['style':("float: right; margin-right: 5px;"),'action':("create"),'params':(['max': params.max, 'offset': params.offset])],2)
printHtmlPart(8)
if(true && (errMessage != null)) {
printHtmlPart(9)
expressionOut.print(errMessage)
printHtmlPart(10)
}
printHtmlPart(1)
if(true && (successMsg != null)) {
printHtmlPart(11)
expressionOut.print(successMsg)
printHtmlPart(12)
expressionOut.print(msgDetails)
printHtmlPart(10)
}
printHtmlPart(13)
invokeTag('message','g',20,['code':("default.application.transactionslist")],-1)
printHtmlPart(14)
invokeTag('message','g',23,['code':("businesstransaction.date.label")],-1)
printHtmlPart(15)
invokeTag('message','g',24,['code':("businesstransaction.type.label")],-1)
printHtmlPart(15)
invokeTag('message','g',25,['code':("businesstransaction.amount.label")],-1)
printHtmlPart(15)
invokeTag('message','g',26,['code':("businesstransaction.remarks.label")],-1)
printHtmlPart(16)
invokeTag('message','g',27,['code':("businesstransaction.operator.label")],-1)
printHtmlPart(17)
loop:{
int index = 0
for( transInstance in (transactions) ) {
printHtmlPart(18)
if(true && (new Integer(params.offset) > 0)) {
printHtmlPart(19)
invokeTag('set','g',33,['var':("offset"),'value':(new Integer(params.offset))],-1)
printHtmlPart(19)
}
else {
invokeTag('set','g',35,['var':("offset"),'value':(0)],-1)
}
printHtmlPart(20)
expressionOut.print((index % 2) == 0 ? 'even' : 'odd')
printHtmlPart(21)
expressionOut.print(offset + index + 1)
printHtmlPart(22)
expressionOut.print(formatDate(format:'dd/MM/yyyy', date:transInstance?.transactionDate))
printHtmlPart(23)
expressionOut.print(transInstance?.operationType.toString())
printHtmlPart(24)
invokeTag('formatNumber','g',41,['number':(transInstance?.transactionAmount),'format':("#,##0.00")],-1)
printHtmlPart(23)
expressionOut.print(transInstance?.transactionRemarks)
printHtmlPart(25)
expressionOut.print(transInstance?.operator)
printHtmlPart(26)
createTagBody(3, {->
printHtmlPart(27)
expressionOut.print(resource(dir: 'images', file: 'icons/report_delete16x16.png'))
printHtmlPart(6)
invokeTag('message','g',46,['code':("actions.transaction.delete")],-1)
printHtmlPart(28)
})
invokeTag('link','g',47,['action':("delete"),'params':(['max': params.max, 'offset': params.offset, 'id': transInstance.id]),'onclick':("return confirm('Are you sure?')")],3)
printHtmlPart(29)
createTagBody(3, {->
printHtmlPart(27)
expressionOut.print(resource(dir: 'images', file: 'icons/report_next16x16.png'))
printHtmlPart(6)
invokeTag('message','g',51,['code':("actions.edit")],-1)
printHtmlPart(28)
})
invokeTag('link','g',52,['action':("edit"),'params':(['max': params.max, 'offset': params.offset, 'id_edit': transInstance.id])],3)
printHtmlPart(30)
index++
}
}
printHtmlPart(31)
invokeTag('paginate','g',59,['total':(counter ?: 0)],-1)
printHtmlPart(32)
invokeTag('message','g',62,['code':("default.application.totalrecords")],-1)
printHtmlPart(33)
expressionOut.print(counter)
printHtmlPart(34)
})
invokeTag('captureBody','sitemesh',64,[:],1)
printHtmlPart(35)
}
public static final Map JSP_TAGS = new HashMap()
protected void init() {
	this.jspTags = JSP_TAGS
}
public static final String CONTENT_TYPE = 'text/html;charset=UTF-8'
public static final long LAST_MODIFIED = 1445159769569L
public static final String EXPRESSION_CODEC = 'html'
public static final String STATIC_CODEC = 'none'
public static final String OUT_CODEC = 'none'
public static final String TAGLIB_CODEC = 'none'
}
