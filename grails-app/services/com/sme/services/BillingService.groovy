package com.sme.services

import grails.transaction.Transactional
import com.sme.entities.*

/**
 *  Managing all aspects of Billing, for all defined User Groups, except Bank
 *  Operators (as on 15/04/2016)
 */
@Transactional
class BillingService {

    /**
     *  Instantiating Invoicing Parameters (Billing) for a given Company,
     *  subject to presence of registrationDate.
     */
    def initiateInvoicing(Business company) {
        def registrationDate
        def startBillingDate
        def trialPeriod
        def defaultBilling = BillingType.findByCode(20)    //  Monthly Billing
        Date currentDate = new Date().clearTime()
        
        //  Billing starts from 01/05/2016
        Date cutOffDate = new Date().copyWith(year: 2016, month: 4, dayOfMonth: 1).clearTime()
        
        //println "initiateInvoicing: cutOffDate = ${cutOffDate}"
        
        if(company.billingType && company.billingType.code == 10) {
            return true
        }
        else if(company.billingType && company.billingType.code > 10) {
            if(company?.startBillingDate && company?.rate) {
                return true
            }
        }
        
        if(!company?.registrationDate) {
            company?.rate = 0
            company?.freeServices = true
            company?.gracePeriod = 0
            company?.billingType = BillingType.findByCode(10)
            company.save(flush: true)
            
            return false
        }

        if(company?.activated && company?.activationDate) {
            if(!company?.billingType) {
                company.billingType = defaultBilling
            }
        
            company.rate = company?.billingType?.defaultAmount
            company.freeServices = false
            company.gracePeriod = defaultBilling?.gracePeriod
        
            registrationDate = company?.registrationDate
            trialPeriod = company?.billingType?.trialPeriod
            //startBillingDate = registrationDate + trialPeriod
            startBillingDate = company?.activationDate + trialPeriod
        
            if(startBillingDate < cutOffDate) {
                startBillingDate = cutOffDate
            }
        
            if(startBillingDate.getAt(Calendar.DAY_OF_MONTH) != 1) {
                def month = startBillingDate.month
                def year = startBillingDate.year + 1900

                def nextYear    = month == 11 ? year + 1 : year
                def nextMonth   = month == 11 ? 0 : month + 1
            
                startBillingDate = new Date().copyWith(year: nextYear, month: nextMonth, dayOfMonth: 1).clearTime()
            }
        
            company.startBillingDate    = startBillingDate
            company.nextBillingDate     = startBillingDate
            company.save(flush: true)
        }
        return true
    }
    
    /**
     *  Activation of Company upon first detected login
     */
    def activateBusiness(Business businessInstance) {
        if(!businessInstance) {
            return false
        }
        
        businessInstance?.activated = true
        businessInstance?.blocked = false
        businessInstance?.activationDate = new Date().clearTime()
        businessInstance.save(flush: true)
        
        return this.initiateInvoicing(businessInstance)
    }
    
    def createNewInvoice(Business company, Date effectiveDate) {
        if(!company?.startBillingDate || company?.freeServices) {
            return false
        }
    }
    
    /**
     *  Creation of new Invoices (Bills) for all registered Companies. Creation
     *  is conditional, subject to nextBillingDate field of each Company.
     *  <br/>
     *  As a rule, Current Date is being used for evaluation.
     */
    def evaluateCurrentBilling(Date evaluationDate, String operator) {
        def businesses = Business.list()
        BigDecimal  amountDue
        Date        gracePeriodTill
        Date        invoiceDate
        Date        nextBillingDate
        Integer     counterProcessed = 0
        Integer     counterCreated = 0
        Integer     counterInstantiated = 0
        
        def invoiceNumber
        
        def year, month
        def nextYear
        def nextMonth
        def newBill
        
        businesses.each {business ->
            ++counterProcessed
            
            if(!business?.billingType) {
                this.initiateInvoicing(business)
                ++counterInstantiated
            }
            
            if(business?.billingType && business?.startBillingDate <= evaluationDate) {
                if(business?.nextBillingDate <= evaluationDate && !business.freeServices) {
                    
                    amountDue       = business?.rate
                    invoiceDate     = business?.nextBillingDate
                    gracePeriodTill = invoiceDate + business?.gracePeriod
                    
                    gracePeriodTill.clearTime()
                    
                    year    = invoiceDate.year + 1900
                    month   = invoiceDate.month
                    
                    if(business?.billingType?.code == 20) {         //  Monthly Billing
                        nextYear    = month == 11 ? year + 1 : year
                        nextMonth   = month == 11 ? 0 : month + 1
                    }
                    else if(business?.billingType?.code == 30) {    //  Annual Billing
                        nextMonth = month
                        nextYear = year + 1
                    }
                    
                    nextBillingDate = new Date().copyWith(year: nextYear, month: nextMonth, dayOfMonth: 1).clearTime()
                    
                    invoiceNumber = ''
                    invoiceNumber += "${invoiceDate.year + 1900}"
                    invoiceNumber += "${String.format('%1$2s', invoiceDate.month + 1).replace(' ', '0')}${String.format('%1$2s', invoiceDate.getAt(Calendar.DAY_OF_MONTH)).replace(' ', '0')}"
                    invoiceNumber += '/' + String.format('%1$6s', business?.id).replaceAll(' ', '0')

                    newBill = new Bill(
                        dateCreated:        new Date(),
                        dueDate:            invoiceDate,
                        gracePeriodTill:    gracePeriodTill,
                        periodFrom:         invoiceDate,
                        periodTill:         nextBillingDate,
                        invoiceNumber:      invoiceNumber,
                        billingType:        business?.billingType,
                        amount:             amountDue,
                        company:            business,
                        paid:               false,
                        outstanding:        true,
                        writtenOff:         false,
                        confirmed:          false,
                        createdBy:          operator
                    )
                    
                    if(!newBill.save()) {
                        println "*** Validation failed:"
                        newBill.errors.allErrors.each{
                            println it
                        }
                    }
                    else {
                        business.addToBills(newBill)
                    
                        business.nextBillingDate = nextBillingDate
                        business.save()
                    
                        ++counterCreated
                    }
                }
            }
        }
        
        [
            "Billing Evaluation Completed",
            "Total Records processed: ${counterProcessed}",
            counterInstantiated > 0 ? "Instantiated Records: ${counterInstantiated}" : '',
            "New due Invoices Created: ${counterCreated}"
        ]
    }
    
    /**
     *  Checking whether outstanding Invoices exist for a given Company
     */
    def checkForOutstandingInvoices(Business company) {
        def instancesList = Bill.createCriteria().list() {
            eq('company', company)
            eq('outstanding', true)
        }
        
        if(instancesList.size() > 0) {
            instancesList = null
            return true
        }
        else {
            instancesList = null
            return false
        }
    }
}
