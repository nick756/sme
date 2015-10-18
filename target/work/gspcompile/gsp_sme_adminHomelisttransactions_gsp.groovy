import org.codehaus.groovy.grails.plugins.metadata.GrailsPlugin
import org.codehaus.groovy.grails.web.pages.GroovyPage
import org.codehaus.groovy.grails.web.taglib.*
import org.codehaus.groovy.grails.web.taglib.exceptions.GrailsTagException
import org.springframework.web.util.*
import grails.util.GrailsUtil

class gsp_sme_adminHomelisttransactions_gsp extends GroovyPage {
public String getGroovyPageFileName() { "/WEB-INF/grails-app/views/adminHome/listtransactions.gsp" }
public Object run() {
Writer out = getOut()
Writer expressionOut = getExpressionOut()
registerSitemeshPreprocessMode()
printHtmlPart(0)
createTagBody(1, {->
printHtmlPart(1)
invokeTag('captureMeta','sitemesh',5,['gsp_sm_xmlClosingForEmptyTag':(""),'http-equiv':("Content-Type"),'content':("text/html; charset=UTF-8")],-1)
printHtmlPart(1)
invokeTag('captureMeta','sitemesh',6,['gsp_sm_xmlClosingForEmptyTag':("/"),'name':("layout"),'content':("adminpage")],-1)
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
expressionOut.print(resource(dir: 'images', file: 'arrow_left.png'))
printHtmlPart(6)
invokeTag('message','g',12,['code':("actions.back")],-1)
printHtmlPart(7)
})
invokeTag('link','g',12,['style':("float: left;"),'action':("show"),'params':(['max': params.max, 'offset': params.offset, 'id': businessInstance?.id])],2)
printHtmlPart(8)
createTagBody(2, {->
printHtmlPart(9)
expressionOut.print(resource(dir: 'images', file: 'statement.png'))
printHtmlPart(6)
invokeTag('message','g',13,['code':("actions.statement")],-1)
printHtmlPart(7)
})
invokeTag('link','g',13,['action':("statements"),'params':(['max': params.max, 'offset': params.offset, 'id': businessInstance?.id])],2)
printHtmlPart(10)
expressionOut.print(businessInstance?.name)
printHtmlPart(11)
invokeTag('message','g',19,['code':("business.profile.label")],-1)
printHtmlPart(12)
invokeTag('textField','g',21,['class':("text-input"),'name':("name"),'value':(businessInstance?.profile?.name),'readonly':("true")],-1)
printHtmlPart(13)
invokeTag('message','g',24,['code':("default.application.transactionslist")],-1)
printHtmlPart(14)
createTagBody(2, {->
printHtmlPart(15)
invokeTag('hiddenField','g',34,['name':("instId"),'value':(businessInstance?.id)],-1)
printHtmlPart(16)
invokeTag('message','g',37,['code':("default.application.filter.type")],-1)
printHtmlPart(17)
expressionOut.print(operationFilter == 1 ? 'selected':'')
printHtmlPart(18)
invokeTag('message','g',43,['code':("default.application.filter.all")],-1)
printHtmlPart(19)
expressionOut.print(operationFilter == 2 ? 'selected':'')
printHtmlPart(18)
invokeTag('message','g',44,['code':("default.application.filter.lastmonth")],-1)
printHtmlPart(20)
expressionOut.print(operationFilter == 3 ? 'selected':'')
printHtmlPart(18)
invokeTag('message','g',45,['code':("default.application.filter.dates")],-1)
printHtmlPart(21)
invokeTag('message','g',49,['code':("default.application.filter.from")],-1)
printHtmlPart(22)
invokeTag('textField','g',54,['class':("text-input"),'style':("width: 150px;"),'name':("dateFrom"),'value':(dateFrom)],-1)
printHtmlPart(23)
invokeTag('message','g',57,['code':("default.application.filter.till")],-1)
printHtmlPart(22)
invokeTag('textField','g',62,['class':("text-input"),'style':("width: 150px;"),'name':("dateTill"),'value':(dateTill)],-1)
printHtmlPart(24)
invokeTag('message','g',64,['code':("default.application.filter.show")],-1)
printHtmlPart(25)
})
invokeTag('form','g',65,['action':("listtransactions"),'params':(['offset': 0, 'max':params?.max])],2)
printHtmlPart(26)
if(true && (transactionsList.size() > 0)) {
printHtmlPart(27)
invokeTag('message','g',70,['code':("businesstransaction.date.label")],-1)
printHtmlPart(28)
invokeTag('message','g',73,['code':("businesstransaction.type.label")],-1)
printHtmlPart(28)
invokeTag('message','g',76,['code':("businesstransaction.amount.label")],-1)
printHtmlPart(28)
invokeTag('message','g',78,['code':("businesstransaction.remarks.label")],-1)
printHtmlPart(28)
invokeTag('message','g',80,['code':("businesstransaction.operator.label")],-1)
printHtmlPart(29)
loop:{
int index = 0
for( transInstance in (transactionsList) ) {
printHtmlPart(30)
if(true && (new Integer(params.offset) > 0)) {
printHtmlPart(31)
invokeTag('set','g',83,['var':("offset"),'value':(new Integer(params.offset))],-1)
printHtmlPart(31)
}
else {
invokeTag('set','g',86,['var':("offset"),'value':(0)],-1)
}
printHtmlPart(32)
expressionOut.print((index % 2) == 0 ? 'even' : 'odd')
printHtmlPart(33)
expressionOut.print(offset + index + 1)
printHtmlPart(34)
expressionOut.print(formatDate(format:'dd/MM/yyyy', date:transInstance?.transactionDate))
printHtmlPart(35)
expressionOut.print(transInstance?.operationType)
printHtmlPart(36)
invokeTag('formatNumber','g',95,['number':(transInstance?.transactionAmount),'format':("#,##0.00")],-1)
printHtmlPart(35)
expressionOut.print(transInstance?.transactionRemarks)
printHtmlPart(35)
expressionOut.print(transInstance?.operator)
printHtmlPart(37)
index++
}
}
printHtmlPart(8)
}
else {
printHtmlPart(38)
invokeTag('message','g',99,['code':("default.application.notransactionsfound")],-1)
printHtmlPart(39)
}
printHtmlPart(40)
invokeTag('paginate','g',109,['total':(transactionCount ?: 0),'params':(['id': businessInstance?.id, 'filterOption': operationFilter, 'dateFrom': dateFrom, 'dateTill': dateTill])],-1)
printHtmlPart(41)
invokeTag('message','g',112,['code':("default.application.totalrecords")],-1)
printHtmlPart(42)
expressionOut.print(transactionCount)
printHtmlPart(43)
})
invokeTag('captureBody','sitemesh',113,[:],1)
printHtmlPart(44)
}
public static final Map JSP_TAGS = new HashMap()
protected void init() {
	this.jspTags = JSP_TAGS
}
public static final String CONTENT_TYPE = 'text/html;charset=UTF-8'
public static final long LAST_MODIFIED = 1444192700913L
public static final String EXPRESSION_CODEC = 'html'
public static final String STATIC_CODEC = 'none'
public static final String OUT_CODEC = 'html'
public static final String TAGLIB_CODEC = 'none'
}
