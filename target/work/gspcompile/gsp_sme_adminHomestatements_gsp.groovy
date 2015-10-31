import com.sme.entities.*
import org.codehaus.groovy.grails.plugins.metadata.GrailsPlugin
import org.codehaus.groovy.grails.web.pages.GroovyPage
import org.codehaus.groovy.grails.web.taglib.*
import org.codehaus.groovy.grails.web.taglib.exceptions.GrailsTagException
import org.springframework.web.util.*
import grails.util.GrailsUtil

class gsp_sme_adminHomestatements_gsp extends GroovyPage {
public String getGroovyPageFileName() { "/WEB-INF/grails-app/views/adminHome/statements.gsp" }
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
invokeTag('captureMeta','sitemesh',7,['gsp_sm_xmlClosingForEmptyTag':("/"),'name':("layout"),'content':("adminpage")],-1)
printHtmlPart(2)
createTagBody(2, {->
createTagBody(3, {->
invokeTag('message','g',8,['code':("pnlstatement.page.label")],-1)
})
invokeTag('captureTitle','sitemesh',8,[:],3)
})
invokeTag('wrapTitleTag','sitemesh',8,[:],2)
printHtmlPart(3)
})
invokeTag('captureHead','sitemesh',12,[:],1)
printHtmlPart(4)
createTagBody(1, {->
printHtmlPart(5)
createTagBody(2, {->
printHtmlPart(6)
expressionOut.print(resource(dir: 'images', file: 'arrow_left.png'))
printHtmlPart(7)
invokeTag('message','g',15,['code':("actions.back")],-1)
printHtmlPart(8)
})
invokeTag('link','g',15,['style':("float: left;"),'action':("listtransactions"),'params':(['max': params.max, 'offset': params.offset, 'id': businessInstance?.id])],2)
printHtmlPart(9)
createTagBody(2, {->
printHtmlPart(10)
expressionOut.print(resource(dir: 'images', file: 'data_delete.png'))
printHtmlPart(7)
invokeTag('message','g',16,['code':("actions.back")],-1)
printHtmlPart(8)
})
invokeTag('link','g',16,['style':("float: right;"),'action':("deleteAllStatements"),'params':(['max': params.max, 'offset': params.offset, 'id': businessInstance?.id])],2)
printHtmlPart(11)
expressionOut.print(businessInstance?.name)
printHtmlPart(12)
createTagBody(2, {->
printHtmlPart(13)
invokeTag('hiddenField','g',21,['name':("instID"),'value':(businessInstance?.id)],-1)
printHtmlPart(14)
invokeTag('message','g',22,['code':("pnlstatement.year.label")],-1)
printHtmlPart(15)
invokeTag('datePicker','g',23,['name':("period"),'class':("select-list"),'precision':("year"),'relativeYears':([-1..1]),'value':(yearInst)],-1)
printHtmlPart(16)
invokeTag('message','g',25,['code':("pnlstatement.month.label")],-1)
printHtmlPart(15)
invokeTag('select','g',26,['class':("select-list"),'style':("width: 200px;"),'name':("month.id"),'noSelection':(['null':'']),'from':(Month.list()),'value':(monthInst?.id),'optionKey':("id")],-1)
printHtmlPart(17)
expressionOut.print(message(code: 'actions.generate'))
printHtmlPart(18)
})
invokeTag('form','g',29,['action':("statements"),'params':(['max': params.max, 'offset': params.offset])],2)
printHtmlPart(19)
if(true && (errMessage)) {
printHtmlPart(20)
expressionOut.print(errMessage)
printHtmlPart(21)
}
printHtmlPart(22)
invokeTag('message','g',37,['code':("cfstatement.page.label")],-1)
printHtmlPart(23)
invokeTag('message','g',39,['code':("cfstatement.year.label")],-1)
printHtmlPart(24)
invokeTag('sortableColumn','g',40,['property':("month"),'title':(message(code: 'cfstatement.month.label'))],-1)
printHtmlPart(25)
invokeTag('message','g',41,['code':("cfstatement.openingbalance.label")],-1)
printHtmlPart(26)
invokeTag('message','g',42,['code':("cfstatement.inboundamount.label")],-1)
printHtmlPart(26)
invokeTag('message','g',43,['code':("cfstatement.outboundamount.label")],-1)
printHtmlPart(26)
invokeTag('message','g',44,['code':("cfstatement.resultamount.label")],-1)
printHtmlPart(26)
invokeTag('message','g',45,['code':("cfstatement.cumulativeamount.label")],-1)
printHtmlPart(27)
loop:{
int index = 0
for( item in (statementList) ) {
printHtmlPart(28)
if(true && (new Integer(params.offset) > 0)) {
printHtmlPart(29)
invokeTag('set','g',52,['var':("offset"),'value':(new Integer(params.offset))],-1)
printHtmlPart(13)
}
else {
invokeTag('set','g',54,['var':("offset"),'value':(0)],-1)
}
printHtmlPart(30)
expressionOut.print((index % 2) == 0 ? 'even' : 'odd')
printHtmlPart(31)
expressionOut.print(item?.year)
printHtmlPart(32)
expressionOut.print(item?.month)
printHtmlPart(33)
invokeTag('formatNumber','g',59,['number':(item?.openingBalance),'format':("#,##0.00")],-1)
printHtmlPart(33)
invokeTag('formatNumber','g',60,['number':(item?.inflow),'format':("#,##0.00")],-1)
printHtmlPart(33)
invokeTag('formatNumber','g',61,['number':(item?.outflow),'format':("#,##0.00")],-1)
printHtmlPart(34)
if(true && (item?.nettAmount >= 0)) {
printHtmlPart(35)
}
else {
printHtmlPart(36)
}
printHtmlPart(37)
invokeTag('formatNumber','g',70,['number':(item?.nettAmount),'format':("#,##0.00")],-1)
printHtmlPart(38)
invokeTag('formatNumber','g',71,['number':(item?.cumulativeAmount),'format':("#,##0.00")],-1)
printHtmlPart(39)
createTagBody(3, {->
printHtmlPart(40)
expressionOut.print(resource(dir: 'images', file: 'details.png'))
printHtmlPart(7)
invokeTag('message','g',74,['code':("actions.back")],-1)
printHtmlPart(41)
})
invokeTag('link','g',75,['target':("_blank"),'action':("statementdetails"),'params':(['max': params.max, 'offset': params.offset, 'id': item?.id, 'businessId': businessInstance?.id])],3)
printHtmlPart(42)
index++
}
}
printHtmlPart(43)
})
invokeTag('captureBody','sitemesh',82,[:],1)
printHtmlPart(44)
}
public static final Map JSP_TAGS = new HashMap()
protected void init() {
	this.jspTags = JSP_TAGS
}
public static final String CONTENT_TYPE = 'text/html;charset=UTF-8'
public static final long LAST_MODIFIED = 1443950552141L
public static final String EXPRESSION_CODEC = 'html'
public static final String STATIC_CODEC = 'none'
public static final String OUT_CODEC = 'none'
public static final String TAGLIB_CODEC = 'none'
}
