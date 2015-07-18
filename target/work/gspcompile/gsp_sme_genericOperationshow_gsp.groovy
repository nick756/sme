import com.sme.entities.GenericOperation
import org.codehaus.groovy.grails.plugins.metadata.GrailsPlugin
import org.codehaus.groovy.grails.web.pages.GroovyPage
import org.codehaus.groovy.grails.web.taglib.*
import org.codehaus.groovy.grails.web.taglib.exceptions.GrailsTagException
import org.springframework.web.util.*
import grails.util.GrailsUtil

class gsp_sme_genericOperationshow_gsp extends GroovyPage {
public String getGroovyPageFileName() { "/WEB-INF/grails-app/views/genericOperation/show.gsp" }
public Object run() {
Writer out = getOut()
Writer expressionOut = getExpressionOut()
registerSitemeshPreprocessMode()
printHtmlPart(0)
printHtmlPart(1)
createTagBody(1, {->
printHtmlPart(2)
invokeTag('captureMeta','sitemesh',6,['gsp_sm_xmlClosingForEmptyTag':(""),'name':("layout"),'content':("main")],-1)
printHtmlPart(2)
invokeTag('set','g',7,['var':("entityName"),'value':(message(code: 'genericOperation.label', default: 'GenericOperation'))],-1)
printHtmlPart(2)
createTagBody(2, {->
createTagBody(3, {->
invokeTag('message','g',8,['code':("default.show.label"),'args':([entityName])],-1)
})
invokeTag('captureTitle','sitemesh',8,[:],3)
})
invokeTag('wrapTitleTag','sitemesh',8,[:],2)
printHtmlPart(3)
})
invokeTag('captureHead','sitemesh',9,[:],1)
printHtmlPart(3)
createTagBody(1, {->
printHtmlPart(4)
invokeTag('message','g',11,['code':("default.link.skip.label"),'default':("Skip to content&hellip;")],-1)
printHtmlPart(5)
expressionOut.print(createLink(uri: '/'))
printHtmlPart(6)
invokeTag('message','g',14,['code':("default.home.label")],-1)
printHtmlPart(7)
createTagBody(2, {->
invokeTag('message','g',15,['code':("default.list.label"),'args':([entityName])],-1)
})
invokeTag('link','g',15,['class':("list"),'action':("index")],2)
printHtmlPart(8)
createTagBody(2, {->
invokeTag('message','g',16,['code':("default.new.label"),'args':([entityName])],-1)
})
invokeTag('link','g',16,['class':("create"),'action':("create")],2)
printHtmlPart(9)
invokeTag('message','g',20,['code':("default.show.label"),'args':([entityName])],-1)
printHtmlPart(10)
if(true && (flash.message)) {
printHtmlPart(11)
expressionOut.print(flash.message)
printHtmlPart(12)
}
printHtmlPart(13)
if(true && (genericOperationInstance?.dateCreated)) {
printHtmlPart(14)
invokeTag('message','g',28,['code':("genericOperation.dateCreated.label"),'default':("Date Created")],-1)
printHtmlPart(15)
invokeTag('formatDate','g',30,['date':(genericOperationInstance?.dateCreated)],-1)
printHtmlPart(16)
}
printHtmlPart(17)
if(true && (genericOperationInstance?.lastUpdated)) {
printHtmlPart(18)
invokeTag('message','g',37,['code':("genericOperation.lastUpdated.label"),'default':("Last Updated")],-1)
printHtmlPart(19)
invokeTag('formatDate','g',39,['date':(genericOperationInstance?.lastUpdated)],-1)
printHtmlPart(16)
}
printHtmlPart(17)
if(true && (genericOperationInstance?.code)) {
printHtmlPart(20)
invokeTag('message','g',46,['code':("genericOperation.code.label"),'default':("Code")],-1)
printHtmlPart(21)
invokeTag('fieldValue','g',48,['bean':(genericOperationInstance),'field':("code")],-1)
printHtmlPart(16)
}
printHtmlPart(17)
if(true && (genericOperationInstance?.name)) {
printHtmlPart(22)
invokeTag('message','g',55,['code':("genericOperation.name.label"),'default':("Name")],-1)
printHtmlPart(23)
invokeTag('fieldValue','g',57,['bean':(genericOperationInstance),'field':("name")],-1)
printHtmlPart(16)
}
printHtmlPart(17)
if(true && (genericOperationInstance?.accountType)) {
printHtmlPart(24)
invokeTag('message','g',64,['code':("genericOperation.accountType.label"),'default':("Account Type")],-1)
printHtmlPart(25)
createTagBody(3, {->
expressionOut.print(genericOperationInstance?.accountType?.encodeAsHTML())
})
invokeTag('link','g',66,['controller':("accountType"),'action':("show"),'id':(genericOperationInstance?.accountType?.id)],3)
printHtmlPart(16)
}
printHtmlPart(17)
if(true && (genericOperationInstance?.profiles)) {
printHtmlPart(26)
invokeTag('message','g',73,['code':("genericOperation.profiles.label"),'default':("Profiles")],-1)
printHtmlPart(27)
for( p in (genericOperationInstance.profiles) ) {
printHtmlPart(28)
createTagBody(4, {->
expressionOut.print(p?.encodeAsHTML())
})
invokeTag('link','g',76,['controller':("profileLink"),'action':("show"),'id':(p.id)],4)
printHtmlPart(29)
}
printHtmlPart(30)
}
printHtmlPart(17)
if(true && (genericOperationInstance?.inbound)) {
printHtmlPart(31)
invokeTag('message','g',84,['code':("genericOperation.inbound.label"),'default':("Inbound")],-1)
printHtmlPart(32)
invokeTag('formatBoolean','g',86,['boolean':(genericOperationInstance?.inbound)],-1)
printHtmlPart(16)
}
printHtmlPart(17)
if(true && (genericOperationInstance?.outbound)) {
printHtmlPart(33)
invokeTag('message','g',93,['code':("genericOperation.outbound.label"),'default':("Outbound")],-1)
printHtmlPart(34)
invokeTag('formatBoolean','g',95,['boolean':(genericOperationInstance?.outbound)],-1)
printHtmlPart(16)
}
printHtmlPart(35)
createTagBody(2, {->
printHtmlPart(36)
createTagBody(3, {->
invokeTag('message','g',103,['code':("default.button.edit.label"),'default':("Edit")],-1)
})
invokeTag('link','g',103,['class':("edit"),'action':("edit"),'resource':(genericOperationInstance)],3)
printHtmlPart(37)
invokeTag('actionSubmit','g',104,['class':("delete"),'action':("delete"),'value':(message(code: 'default.button.delete.label', default: 'Delete')),'onclick':("return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');")],-1)
printHtmlPart(38)
})
invokeTag('form','g',106,['url':([resource:genericOperationInstance, action:'delete']),'method':("DELETE")],2)
printHtmlPart(39)
})
invokeTag('captureBody','sitemesh',108,[:],1)
printHtmlPart(40)
}
public static final Map JSP_TAGS = new HashMap()
protected void init() {
	this.jspTags = JSP_TAGS
}
public static final String CONTENT_TYPE = 'text/html;charset=UTF-8'
public static final long LAST_MODIFIED = 1434512013473L
public static final String EXPRESSION_CODEC = 'html'
public static final String STATIC_CODEC = 'none'
public static final String OUT_CODEC = 'none'
public static final String TAGLIB_CODEC = 'none'
}
