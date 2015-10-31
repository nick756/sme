import org.codehaus.groovy.grails.plugins.metadata.GrailsPlugin
import org.codehaus.groovy.grails.web.pages.GroovyPage
import org.codehaus.groovy.grails.web.taglib.*
import org.codehaus.groovy.grails.web.taglib.exceptions.GrailsTagException
import org.springframework.web.util.*
import grails.util.GrailsUtil

class gsp_sme_smestatementindex_gsp extends GroovyPage {
public String getGroovyPageFileName() { "/WEB-INF/grails-app/views/smestatement/index.gsp" }
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
invokeTag('captureMeta','sitemesh',12,['gsp_sm_xmlClosingForEmptyTag':("/"),'name':("layout"),'content':("smepage")],-1)
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
createTagBody(2, {->
printHtmlPart(6)
expressionOut.print(resource(dir: 'images', file: 'data_delete.png'))
printHtmlPart(7)
invokeTag('message','g',17,['code':("actions.delete")],-1)
printHtmlPart(8)
})
invokeTag('link','g',17,['style':("float: right; margin-right: 5px;"),'action':("delete"),'params':(['max': params.max, 'offset': params.offset]),'onclick':("return confirm('Are you sure?');")],2)
printHtmlPart(9)
createTagBody(2, {->
printHtmlPart(6)
expressionOut.print(resource(dir: 'images', file: 'add_record.png'))
printHtmlPart(7)
invokeTag('message','g',18,['code':("actions.add")],-1)
printHtmlPart(8)
})
invokeTag('link','g',18,['style':("float: right; margin-right: 5px;"),'action':("create"),'params':(['max': params.max, 'offset': params.offset])],2)
printHtmlPart(10)
invokeTag('message','g',21,['code':("cfstatement.summary.label")],-1)
printHtmlPart(11)
invokeTag('message','g',23,['code':("cfstatement.year.label")],-1)
printHtmlPart(12)
invokeTag('sortableColumn','g',24,['property':("month"),'title':(message(code: 'cfstatement.month.label'))],-1)
printHtmlPart(13)
invokeTag('message','g',25,['code':("cfstatement.openingbalance.label")],-1)
printHtmlPart(14)
invokeTag('message','g',26,['code':("cfstatement.inboundamount.label")],-1)
printHtmlPart(14)
invokeTag('message','g',27,['code':("cfstatement.outboundamount.label")],-1)
printHtmlPart(14)
invokeTag('message','g',28,['code':("cfstatement.resultamount.label")],-1)
printHtmlPart(14)
invokeTag('message','g',29,['code':("cfstatement.cumulativeamount.label")],-1)
printHtmlPart(15)
loop:{
int index = 0
for( item in (statements) ) {
printHtmlPart(16)
if(true && (new Integer(params.offset) > 0)) {
printHtmlPart(17)
invokeTag('set','g',36,['var':("offset"),'value':(new Integer(params.offset))],-1)
printHtmlPart(18)
}
else {
invokeTag('set','g',38,['var':("offset"),'value':(0)],-1)
}
printHtmlPart(19)
expressionOut.print((index % 2) == 0 ? 'even' : 'odd')
printHtmlPart(20)
expressionOut.print(item?.year)
printHtmlPart(21)
expressionOut.print(item?.month)
printHtmlPart(22)
invokeTag('formatNumber','g',43,['number':(item?.openingBalance),'format':("#,##0.00")],-1)
printHtmlPart(22)
invokeTag('formatNumber','g',44,['number':(item?.inflow),'format':("#,##0.00")],-1)
printHtmlPart(22)
invokeTag('formatNumber','g',45,['number':(item?.outflow),'format':("#,##0.00")],-1)
printHtmlPart(23)
if(true && (item?.nettAmount >= 0)) {
printHtmlPart(24)
}
else {
printHtmlPart(25)
}
printHtmlPart(26)
invokeTag('formatNumber','g',54,['number':(item?.nettAmount),'format':("#,##0.00")],-1)
printHtmlPart(27)
invokeTag('formatNumber','g',55,['number':(item?.cumulativeAmount),'format':("#,##0.00")],-1)
printHtmlPart(28)
createTagBody(3, {->
printHtmlPart(29)
expressionOut.print(resource(dir: 'images', file: 'icons/printer01_16x16.png'))
printHtmlPart(7)
invokeTag('message','g',58,['code':("actions.print")],-1)
printHtmlPart(30)
})
invokeTag('link','g',59,['target':("_blank"),'controller':("adminHome"),'action':("statementdetails"),'params':(['id': item?.id, 'businessId': businessInstance?.id])],3)
printHtmlPart(31)
index++
}
}
printHtmlPart(32)
invokeTag('paginate','g',66,['total':(totalCount ?: 0)],-1)
printHtmlPart(33)
invokeTag('message','g',69,['code':("default.application.totalrecords")],-1)
printHtmlPart(34)
expressionOut.print(totalCount)
printHtmlPart(35)
})
invokeTag('captureBody','sitemesh',71,[:],1)
printHtmlPart(36)
}
public static final Map JSP_TAGS = new HashMap()
protected void init() {
	this.jspTags = JSP_TAGS
}
public static final String CONTENT_TYPE = 'text/html;charset=UTF-8'
public static final long LAST_MODIFIED = 1445147791796L
public static final String EXPRESSION_CODEC = 'html'
public static final String STATIC_CODEC = 'none'
public static final String OUT_CODEC = 'none'
public static final String TAGLIB_CODEC = 'none'
}
