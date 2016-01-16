package com.sme.actions

import com.sme.entities.*
import com.sme.util.*

class ReportController {

    def incomeStatementService
    
    def index() { }
    
    /**
     *  List of Companies in printable view: set filtering options are 
     *  considered
     */
    def companieslist() {
        def filterName      = ''
        def filterAccount   = ''
        def filterCity      = ''
        def result = []
        
        if(params?.filterName) {
            filterName = params.filterName
        }
        
        if(params?.filterAccount) {
            filterAccount = params.filterAccount
        }
        
        if(params?.filterCity) {
            filterCity = params.filterCity
        }
        
        result = Business.createCriteria().list() {
            if(filterName != '') {
                ilike('name', "%${filterName}%")
            }
            
            if(filterAccount != '') {
                ilike('accountNo', "${filterAccount}%")
            }
            
            if(filterCity != '') {
                ilike('city', "%${filterCity}%")
            }
            
            order('name')
        }
        
        [
            businesslist: result
        ]
    }
    
    /**
     *  Structured Report on Credit Operations for a given Business. Called only
     *  from SME Group. Grouping is done by prefixes entered into 'description'
     *  field of BusinessTransactions: identified by token '::' with following
     *  triming. Default GSP page 'creditreport' is to be used for displaying (in
     *  new window).
     *  
     *  Passed Parameters (as on 14/12/2015):
     *  -   businessID
     **/
    def creditreport() {

        /*def businessID      = ''
        def filterAccount   = ''
        def filterCity      = ''
        def result = []*/
        
        def transactions1 = []
        def transactions2 = []
        
        if(!session.user) {
            redirect (controller: 'login', action: 'index')
            return
        }
        
        def user = session?.user
        def companyID = session?.company?.id
        def company
        
        def filterDateFrom = params?.filterDateFrom ?: null
        def filterDateTill = params?.filterDateTill ?: null
        
        def filterOperation = params?.filterOperationType
        
        def filterOperationType = null
        
        if(params.filterOperation != null && params.filterOperation?.id != "") {
            //println "...passed: ${params.filterOperationType?.id}"
            filterOperationType = GenericOperation.get(new Integer(params?.filterOperation?.id))
        }
        
        if(!user.isAttached()) {
            user.attach()
        }
        
        company = Business.get(companyID)
        
        params.offset = params.offset ?: 0
        params.max = params.max ?: 10       
        
        transactions1 = BusinessTransaction.createCriteria().list(params) {
            eq('company', company) 
            
            operationType {
                or {
                    and {
                        eq('code', 2000)
                    }
                    
                    eq('code', 515)
                }
            }
            
            if(filterDateFrom != null) {
                ge('transactionDate', filterDateFrom)
            }
            
            if(filterDateTill) {
                le('transactionDate', filterDateTill)
            }
            
            if(filterOperationType) {
                eq('operationType', filterOperationType)
            }
            
            /*and {
            order('transactionDate', 'desc')
            order('id', 'desc')
            }*/
            order('operationType', 'desc')
            order('transactionDate')
        }
        
        transactions2 = BusinessTransaction.createCriteria().list(params) {
            eq('company', company) 
            
            operationType {
                or {
                    and {
                        eq('code', 2005)
                    }
                    
                    eq('code', 510)
                }
            }
            
            if(filterDateFrom != null) {
                ge('transactionDate', filterDateFrom)
            }
            
            if(filterDateTill) {
                le('transactionDate', filterDateTill)
            }
            
            if(filterOperationType) {
                eq('operationType', filterOperationType)
            }
            
            /*and {
            order('transactionDate', 'desc')
            order('id', 'desc')
            }*/
            order('operationType', 'desc')
            order('transactionDate')
        }
        
        [
            transactions1:   transactions1,
            transactions2:   transactions2,
            companyName:    company.name
            /*counter:        counter,
            params:         params,
            errMessage:     params.errMessage,
            successMsg:     params.successMsg,
            msgDetails:     params.msgDetails,
            filterDateFrom: filterDateFrom,
            filterDateTill: filterDateTill,
            
            filterOperation: filterOperationType*/
        ]
        
    }
    
    /**
     *  Generation of PNL Report by instance of PNLStatement
     */
    def pnlstatement() {
        def statement
        def lines
        def sections
        
        if(params?.id) {
            statement = PNLStatement.get(new Integer(params?.id))
        }
        
        sections = statement.sections.asList().sort{it.group.code}
        
        sections.each {sect ->
            lines       = sect.lines.asList().sort{it.type.code}
            sect.lines  = lines
        }
        
        statement.sections = sections
        
        lines = null
        sections = null
        
        [
            statement: statement
        ]
    }
    
    /**
     *  Generation of Income Statements for all applicable companies
     **/
    def consolidatedincome() {
        Integer year
        Integer quarter
        def statements = []
        Date dateFrom = null
        Date dateTill = null
        def companies
        IncomeSummary summary
        def uptoDate = false
        
        def bank = null
        
        if(params?.bankID) {
            bank = LendingAgency.get(params?.bankID)
        }
        
        year = new Integer(params?.period_year)
        def yearCurr = new Date().year + 1900
        
        if(year == yearCurr) uptoDate = true
        
        if(params.quarter) {
            quarter = new Integer(params.quarter)
        }
        else {
            quarter = 0
        }
        
        companies = Business.createCriteria().list() {
            if(bank != null) {
                eq('bank', bank)
            }
            
            not {
                ilike("accountNo", "test%")
            }
            
            order('name')
        }

        //  Data for Pie Chart generation
        BigDecimal quantTotal
        BigDecimal quantZero   = 0
        BigDecimal quantPoor   = 0
        BigDecimal quantNear   = 0
        BigDecimal quantGood   = 0
        
        BigDecimal ratioTotal
        BigDecimal ratioZero = 0
        BigDecimal ratioPoor = 0
        BigDecimal ratioNear = 0
        BigDecimal ratioGood = 0
        
        quantTotal = companies.size()
        
        companies.each{comp ->
            if(incomeStatementService.getAllPNLTransactions(comp, year, 0).size() > 0) {
                summary = new IncomeSummary(
                    company:            comp.name,
                    companyID:          comp.id,
                    year:               year,
                    quarter:            quarter,
                    prefix:             'Q',
                    month:              0,
                    amountSales:        0,
                    amountCost:         0,
                    amountProfitGross:  0,
                    amountIncome:       0,
                    amountExpense:      0,
                    amountTax:          0,
                    amountProfitBT:     0,
                    amountProfitAT:     0
                )
                
                summary.assignPeriod()
                summary.createCaption()
                
                incomeStatementService.calculateInMemory(summary, comp)
                statements << summary
                
                if(summary.amountProfitBT <= 0) {
                    quantPoor++
                }
                else if(summary.amountProfitBT < 1000) {
                    quantNear++
                }
                else {
                    quantGood++
                }
                
                if(!dateFrom) {
                    dateFrom = summary.dateFrom
                    dateTill = summary.dateTill
                }
            }
            else {
                quantZero++
            }
        }
        
        //ratioZero = quantZero / quantTotal * 100.0

        ratioZero = quantZero.divide(quantTotal, 5, BigDecimal.ROUND_HALF_UP).multiply(100.0 as BigDecimal).setScale(2, BigDecimal.ROUND_HALF_UP)
        ratioPoor = quantPoor.divide(quantTotal, 5, BigDecimal.ROUND_HALF_UP).multiply(100.0 as BigDecimal).setScale(2, BigDecimal.ROUND_HALF_UP)
        ratioNear = quantNear.divide(quantTotal, 5, BigDecimal.ROUND_HALF_UP).multiply(100.0 as BigDecimal).setScale(2, BigDecimal.ROUND_HALF_UP)
        ratioGood = quantGood.divide(quantTotal, 5, BigDecimal.ROUND_HALF_UP).multiply(100.0 as BigDecimal).setScale(2, BigDecimal.ROUND_HALF_UP)        
        
        def points = "[\n"
        points += "{y: ${ratioZero}, legendText: \"Businesses with no Data Entry\", label: \"No Data Entry\"},\n"
        points += "{y: ${ratioPoor}, legendText: \"Businesses with Loss\", label: \"Businesses with Loss\", exploded: true},\n"
        points += "{y: ${ratioNear}, legendText: \"Businesses with Poor Performance\", label: \"Poor Performing\"},\n"
        points += "{y: ${ratioGood}, legendText: \"Profitable Businesses\", label: \"Profitable\"}\n"
        points += "]\n"
        
        println ''
        println 'Controller: report.consolidatedincome'
        println "Total Businesses:          ${quantTotal}"
        println "Records w/o data entries:  ${quantZero} - ${ratioZero}"
        println "Business with losses:      ${quantPoor} - ${ratioPoor}"
        println "Businesses near losses:    ${quantNear} - ${ratioNear}"
        println "Good Performers:           ${quantGood} - ${ratioGood}"
        
        [
            uptoDate:   uptoDate,
            dateFrom:   dateFrom,
            dateTill:   dateTill,
            statements: statements,
            points: points
        ]
    }
    
    /**
     *  Performance of individual selected company, for current and previous
     *  years (monthly Income Statements)
     **/
    def performance(Business businessInstance) {
        Integer yearCurrent     = new Date().year + 1900
        Integer yearPrevious    = yearCurrent - 1
        Integer currentMonth    = new Date().month
        def years = []
        boolean cumulative = false
        
        if(params?.cumulative) {
            cumulative = true
        }
        
        years << yearPrevious
        years << yearCurrent
        
        def salesData       = []
        def salesCostData   = []
        def profitGrossData = []
        def expensesData    = []
        def incomeData      = []
        def netProfitData   = []
        def captions        = []
        
        //  Statements for adjacent years
        
        years.each{year ->
            12.times {
                def summary = new IncomeSummary(
                    company:            businessInstance.name,
                    companyID:          businessInstance.id,
                    year:               year,
                    quarter:            0,
                    prefix:             '',
                    month:              it + 1,
                    amountSales:        0,
                    amountCost:         0,
                    amountProfitGross:  0,
                    amountIncome:       0,
                    amountExpense:      0,
                    amountTax:          0,
                    amountProfitBT:     0,
                    amountProfitAT:     0            
                )
            
                summary.assignPeriod(cumulative)
                summary.createCaption()
                
                //  Exclude operations over future periods for Cumulative Reports
                
                if(year == yearPrevious || (year == yearCurrent && it <= currentMonth)) {
                    incomeStatementService.calculateInMemory(summary, businessInstance)
                }
            
                salesData       << summary.amountSales
                salesCostData   << summary.amountCost
                profitGrossData << summary.amountProfitGross
                netProfitData   << summary.amountProfitBT
                expensesData    << summary.amountExpense
                captions        << summary.caption
                
                summary = null
            }
        }
        
        def series01 = "[\n"    //  Sales data
        def series02 = "[\n"    //  Cost of sales
        def series03 = "[\n"    //  Gross Profit
        def series04 = "[\n"    //  Expenses
        def series05 = "[\n"    //  Net Profit BT
        
        salesData.eachWithIndex{sale, index ->
            if(index > 0) {
                series01 += ",\n"
                series02 += ",\n"
                series03 += ",\n"
                series04 += ",\n"
                series05 += ",\n"
            }
            
            series01 += "{y: ${sale}, label: ${captions[index]}}"
            series02 += "{y: ${salesCostData[index]}, label: ${captions[index]}}"
            series03 += "{y: ${profitGrossData[index]}, label: ${captions[index]}}"
            series04 += "{y: ${expensesData[index]}, label: ${captions[index]}}"
            series05 += "{y: ${netProfitData[index]}, label: ${captions[index]}}"
        }
        
        series01 += "\n]"
        series02 += "\n]"
        series03 += "\n]"
        series04 += "\n]"
        series05 += "\n]"
        
        def statements = [:]
        
        statements << ["Sales": salesData]
        statements << ["Cost of Sales": salesCostData]
        statements << ["Gross Profit": profitGrossData]
        statements << ["Operational Expenses": expensesData]
        statements << ["Net Profit before Tax": netProfitData]
        
        [
            series01: series01,
            series02: series02,
            series03: series03,
            series04: series04,
            series05: series05,
            businessInstance: businessInstance,
            statements: statements,
            year: yearCurrent,
            cumulative: cumulative
        ]
    }
    
    def bankcompanieslist() {
        def filterName      = ''
        def filterAccount   = ''
        def filterCity      = ''
        
        def bankID = session?.user.bank.id
        def bank
        
        bank = Business.get(bankID)
        
        def result = []
        
        if(params?.filterName) {
            filterName = params.filterName
        }
        
        if(params?.filterAccount) {
            filterAccount = params.filterAccount
        }
        
        if(params?.filterCity) {
            filterCity = params.filterCity
        }
        
        result = Business.createCriteria().list() {
            eq('bank', bank) 
            
            if(filterName != '') {
                ilike('name', "%${filterName}%")
            }
            
            if(filterAccount != '') {
                ilike('accountNo', "${filterAccount}%")
            }
            
            if(filterCity != '') {
                ilike('city', "%${filterCity}%")
            }
            
            order('name')
        }
        
        [
            businesslist: result
        ]
    }
}
