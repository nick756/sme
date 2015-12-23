import com.sme.entities.*
import com.sme.services.*

class BootStrap {
    def grailsApplication
    def businessTransactionService
    
    def init = { servletContext ->
        
        def userRole
        
        if(Month.list().size() == 0) {
            new Month(number:  1, name: 'JAN').save(flush: true)
            new Month(number:  2, name: 'FEB').save(flush: true)
            new Month(number:  3, name: 'MAR').save(flush: true)
            new Month(number:  4, name: 'APR').save(flush: true)
            new Month(number:  5, name: 'MAY').save(flush: true)
            new Month(number:  6, name: 'JUN').save(flush: true)
            new Month(number:  7, name: 'JUL').save(flush: true)
            new Month(number:  8, name: 'AUG').save(flush: true)
            new Month(number:  9, name: 'SEP').save(flush: true)
            new Month(number: 10, name: 'OCT').save(flush: true)
            new Month(number: 11, name: 'NOV').save(flush: true)
            new Month(number: 12, name: 'DEC').save(flush: true)
            
            println ''
            println "Months populated: ${Month.count()} instances"
        }
        
        if(AccountType.list().size() == 0) {
            println "\n... populating Account Types"
            
            new AccountType(code: 1, name: 'Capital').save()
            new AccountType(code: 2, name: 'Current Liabilities').save()            
            new AccountType(code: 3, name: 'Long Term Liabilities').save() 
            new AccountType(code: 4, name: 'Fixed Assets').save()            
            new AccountType(code: 5, name: 'Current Assets').save() 
            new AccountType(code: 6, name: 'Cost of Goods Sold').save() 
            new AccountType(code: 7, name: 'Income').save()  
            new AccountType(code: 8, name: 'Other Income').save() 
            new AccountType(code: 9, name: 'Expenses').save() 
            
            println "Total " + AccountType.count() + " instances created"
        }
        
        if(CFGroup.list().size() == 0){
            println ''
            println 'Creating Cash Flow Groups'
            
            new CFGroup(code: 1, name: 'Cash Inflow').save(flush: true)
            new CFGroup(code: 2, name: 'Cost of Sales').save(flush: true)
            new CFGroup(code: 3, name: 'Operational Expenses').save(flush: true)
            new CFGroup(code: 4, name: 'Capital Expenditures').save(flush: true)  
            new CFGroup(code: 5, name: 'Cash in Hands').save(flush: true) 
            new CFGroup(code: 6, name: 'Cash at Bank').save(flush: true)
            
            println "Total ${CFGroup.count()} instances created"
        }
        
        //  Added on 15/12/2015: All references during export are added to
        //  the end of respective lines
        
        if(PNLSubGroup.list().size() == 0) {
            println ''
            println 'Creating PNL Sub Groups'
            
            new PNLSubGroup(code:  5, name: 'Purchase of Raw materials').save(flush: true)
            new PNLSubGroup(code: 10, name: 'Production Wages').save(flush: true)
            new PNLSubGroup(code: 15, name: 'Factory Overheads').save(flush: true)
            
            println "Total ${PNLSubGroup.count()} instances created"
        }
        
        if(PNLGroup.list().size() == 0) {
            println ''
            println 'Creating PNL Main Groups'
            
            new PNLGroup(code:  1, name: 'Sales').save(flush: true)
            new PNLGroup(code:  5, name: 'Cost of Sales').save(flush: true)
            new PNLGroup(code: 10, name: 'Other Income').save(flush: true)
            new PNLGroup(code: 15, name: 'Administrative and Operational Expenses').save(flush: true)
            new PNLGroup(code: 20, name: 'Taxation').save(flush: true)
            
            println "Total ${PNLGroup.count()} instances created"
        }
        
        /***********************************************************************
         * Restoring list of GenericOperation from backup file
         **********************************************************************/
        if(GenericOperation.list().size() == 0) {
            println ''
            println "******** IMPORT OF GENERIC OPERATION INSTANCES ***********"
 
            def path = "C:/export/operations.txt"
            def file = new File("${path}")
            def fileContent = file.text
            def counter = 0
            
            println "File Size: ${file.length()} bytes"
            println '**********************************************************'
            
            fileContent.splitEachLine('#') {fields ->
                def accountType = AccountType.findByCode(fields[1])
                def group = CFGroup.findByCode(fields[2])
                Integer cashCode
                Integer bankCode
                Integer pnlGroupCode
                Integer pnlSubGroupCode
                
                PNLGroup pnlGroup = null
                PNLSubGroup pnlSubGroup = null
                
                if(fields[8] != 'null') {
                    cashCode = new Integer(fields[8])
                }
                else {
                    cashCode = 0
                }
                
                if(fields[9] != 'null') {
                    bankCode = new Integer(fields[9])
                }
                else {
                    bankCode = 0
                }
                
                try {
                    if(fields[10] != 'null') {
                        pnlGroupCode = new Integer(fields[10])
                        pnlGroup = PNLGroup.findByCode(pnlGroupCode)
                    }
                
                    if(fields[11] != 'null') {
                        pnlSubGroupCode = new Integer(fields[11])
                        pnlSubGroup = PNLSubGroup.findByCode(pnlSubGroupCode)
                    }

                }
                catch (Exception e) {
                    
                }
                
                new GenericOperation(
                    code:           fields[0],
                    name:           fields[3],
                    name_EN:        fields[4],
                    inbound:        fields[5],
                    outbound:       fields[6],
                    actual:         fields[7],
                    mirrorCashCode: cashCode,
                    mirrorBankCode: bankCode,
                    accountType:    accountType,
                    group:          group,
                    pnlGroup:       pnlGroup,
                    pnlSubGroup:    pnlSubGroup
                ).save(flush: true)
            }
            
            println "Total " + GenericOperation.count() + " instances restored from file"
            println "... updating peer settings"
            
            def opList = GenericOperation.createCriteria().list() {
                order('code')
            }
            
            opList.each {operation ->
                //print "Processing ${operation.code}: ${operation.name_EN}"
                
                if(operation.mirrorCashCode > 0) {
                    def mirrorCash = GenericOperation.findByCode(operation.mirrorCashCode)
                    operation.mirrorCash = mirrorCash
                    //print " ... cash mirror added ${mirrorCash.code}"
                    counter++
                }
                if(operation.mirrorBankCode > 0) {
                    def mirrorBank = GenericOperation.findByCode(operation.mirrorBankCode)
                    operation.mirrorBank = mirrorBank
                    //print " ... bank mirror added ${mirrorBank.code}"
                    counter++
                }
            }
            
            println "Total ${counter} Peers reinstated"
        }
        
        //  Initiating list of States
        
        if(State.list().size() == 0) {
            println "\n...populating State instances"
        }
        
        if(UserRole.list().size() == 0) {
            println ""
            
            new com.sme.entities.UserRole(code: 1, name: "Administrator").save()
            new com.sme.entities.UserRole(code: 2, name: "SME Operator").save() 
            new com.sme.entities.UserRole(code: 3, name: "Bank/Agency Operator").save() 
            
            println ""
            println "User Role Instances created: ${UserRole.count()}"
        }
        
        if(Industry.list().size() == 0) {
            println "\n... initiating Industry list"
            
            new Industry(code: 1, name: 'Trading').save()
            new Industry(code: 2, name: 'Services').save()
            new Industry(code: 3, name: 'Food Manufacturing').save()
            new Industry(code: 4, name: 'Manufacturing').save()
            new Industry(code: 5, name: 'Tourism and Hospitality').save()
            new Industry(code: 6, name: 'Restorant').save()
            
            println "List of Industries created: " + Industry.count() + " instances"
        }
        
        /***********************************************************************
         *  Creation of Genric Profile instances
         * ********************************************************************/
            
        if(GenericProfile.list().size() == 0) {
            def prof01 = new GenericProfile(
                code: 1,
                name: 'Trading Activities'
            )
            
            def prof02 = new GenericProfile(
                code: 2,
                name: 'Food Manufacturing Activities'
            ).save(flush: true)   
                
            def prof03 = new GenericProfile(
                code: 3,
                name: 'Restorant/Food Stall Operations'
            ).save(flush: true)             
                
            prof01.save(flush: true);

            def commonList = GenericOperation.createCriteria().list() {
                or {
                    lt('code', 1000)
                    eq('code', 1020)
                    eq('code', 1030)
                }
            }
               
            //  Auto-populating Profile 'Trading Activities'
            
            commonList.each {item ->
                prof01.addToOperations(
                    new ProfileLink(
                        operation: item,
                        profile: prof01,
                        active: true
                    )
                )
                
                prof02.addToOperations(
                    new ProfileLink(
                        operation: item,
                        profile: prof02,
                        active: true
                    )
                )
                
                prof03.addToOperations(
                    new ProfileLink(
                        operation: item,
                        profile: prof03,
                        active: true
                    )
                )                
            }
                
            prof01.save(flush: true)  
            prof02.save(flush: true)
            prof03.save(flush: true)
                
            println ""
            println "Generic Profiles created: ${GenericProfile.count()} instances"
            println "Operations assigned to ${prof01.name}: ${prof01?.operations.asList().size()}"
            println "Operations assigned to ${prof02.name}: ${prof02?.operations.asList().size()}"
            println "Operations assigned to ${prof03.name}: ${prof03?.operations.asList().size()}"
        }
        
        if(!LendingAgency.list()) {
            new LendingAgency (
                code:           1,
                name:           'Bank Simpanan Nasional',
                shortName:      'BSN',
                city:           'Kuala Lumpur',
                contactEmail:   'info@mail.com'
            ).save(flush: true)
        }
        
        /***********************************************************************
         *  Importing/Restoring Business instances
         * ********************************************************************/
        if(Business.list().size() == 0) {
            importBusinesses()
        }
        
        /***********************************************************************
         *  Import of saved Users
         * ********************************************************************/
        if(!User.list()) {
            importUsers()
        }
        
        if(BusinessTransaction.list().size() == 0) {
            importTransactions()
        }
    }

    
    def destroy = {
    }
    
    /***************************************************************************
     *  Import of previously saved instances of Business
     **************************************************************************/
    void importBusinesses() {
        if(Business.list().size() == 0) {
            def pathBusiness = "C:/export/businesses.txt"
            def file = new File("${pathBusiness}")
            def content = file.text
            
            def industry    = null
            def profile     = null
            def agency      = null
            
            Date registrationDate   = null
            Date incorpDate         = null
            Integer count = 0
            Integer intID
            
            println ''
            println '******************* Import of Businesses *****************'
            println "File size: ${file.length()} bytes"
            println '**********************************************************'
            
            content.splitEachLine('#') {fields ->
                industry            = null
                profile             = null
                incorpDate          = null
                registrationDate    = null
                agency              = null
                
                //System.out.print "Processing ${++count} Record: ${fields[3]}\r"
                
                try {
                    intID = new Integer(fields[0])
                }
                catch(Exception e) {
                    println "************* Problem: ***********************"
                    println "${fields[0]} -- ${fields[1]} -- ${fields[2]} -- ${fields[3]}"
                    println '**********************************************'
                }
                
                if(fields[1] != 'null') {
                    profile = GenericProfile.findByCode(new Integer(fields[1]))
                }
                
                if(fields[2] != 'null' && fields[2] != '0') {
                    industry = Industry.findByCode(new Integer(fields[2]))
                }
                
                if(fields[6] != 'null') {
                    incorpDate = new Date().parse("dd/MM/yyyy", fields[6])
                }
                
                if(fields[7] != 'null') {
                    registrationDate = new Date().parse("dd/MM/yyyy", fields[7])
                }
                
                if(fields[10] != '0') {
                    agency = LendingAgency.findByCode(new Integer(fields[10]))
                }
                
                new Business(
                    internalID:         intID,
                    name:               fields[3],
                    accountNo:          fields[4] == 'null' ? '':fields[4],
                    regNumber:          fields[5] == 'null' ? '':fields[5],
                    incorpDate:         incorpDate,
                    registrationDate:   registrationDate,
                    address:            fields[8] == 'null' ? '':fields[8],
                    city:               fields[9] == 'null' ? '':fields[9],
                    industry:           industry,
                    profile:            profile,
                    bank:               agency
                ).save(flush: true)
            }
            
            println "Total Business instance imported: ${Business.list().size()}"        
        }
    }
    
    /***************************************************************************
     *  Importing Users from backup file
     **************************************************************************/
    void importUsers() {
        println ''
        println '**************** IMPORT OF USER INSTANCES ********************'
        
        def path    = "C:/export/users.txt"
        def file    = new File("${path}")
        def content = file.text
        def businessInstance
        def roleInstance
        def bankInstance
        
        Integer businessID
        Integer roleID
        Integer bankID
        Integer counter = 0
        
        println "File size: ${file.length()} bytes"
        println '**************************************************************'
        
        content.splitEachLine('#') {fields ->
            businessInstance = null
            roleInstance     = null
            bankInstance     = null
            ++counter
            
            try {
                businessID = new Integer(fields[0])
            }
            catch(Exception e) {
                println "*** Issue detected: Business ID not found at Line ${counter}"
                println "*** ${fields[0]} -- ${fields[1]} -- ${fields[2]} -- ${fields[3]}"
            }
            
            if(businessID > 0) {
                businessInstance = Business.findByInternalID(businessID)
            }
            
            if(!businessInstance && businessID > 0) {
                println "*** Issue detected: Business Instance not found at Line ${counter}"
                println "*** ${fields[0]} -- ${fields[1]} -- ${fields[2]} -- ${fields[3]}"              
            }
            
            try {
                roleID = new Integer(fields[1])
            }
            catch(Exception e) {
                println "*** Issue detected: Role ID not found at Line ${counter}"
                println "*** ${fields[0]} -- ${fields[1]} -- ${fields[2]} -- ${fields[3]}"              
            }
            
            roleInstance = UserRole.findByCode(roleID)
            
            try {
                bankID = new Integer(fields[7])
            }
            catch(Exception e) {
                println "*** Issue detected: Bank ID not found at Line ${counter}"
                println "*** ${fields[0]} -- ${fields[1]} -- ${fields[2]} -- ${fields[3]}"                 
            }
            
            if(bankID > 0) {
                bankInstance = LendingAgency.findByCode(bankID)
            }
            
            new User(
                name:       fields[2],
                login:      fields[3],
                passw:      fields[4],
                contactNo:  fields[5] == 'null' ? '':fields[5],
                email:      fields[6] == 'null' ? '':fields[6],
                role:       roleInstance,
                company:    businessInstance,
                bank:       bankInstance
            ).save(flush: true)
        }
        
        println "Users import completed: imported ${User.list().size()} Records"
    }
    
    /***************************************************************************
     *  Restore of Transactions backup
     **************************************************************************/
    void importTransactions() {
        println ''
        println '****************** IMPORT OF TRANSACTIONS ********************'
        
        def path    = "C:/export/transactions.txt"
        def file    = new File("${path}")
        def content = file.text
        def company
        Date transDate
        boolean correct 
        GenericOperation oper
        def transAmount
        
        Integer count = 0
        
        println "File size: ${file.length()} bytes"
        println '**************************************************************'
        
        content.splitEachLine('#') {fields ->
            correct = true
            company = null
            oper    = null
            ++count

            try {
                company = Business.findByInternalID(new Integer(fields[0]))
            }
            catch(Exception e) {
                company = null
                correct = false
                println '******* Problem detected: Business not found *********'
                println "Line ${count}: Business ID = ${fields[0]}"
                println "Amount: ${fields[3]} Date: ${fields[2]}"
                println '***************** Skipped ****************************'
            }
            
            try {
                oper = GenericOperation.findByCode(new Integer(fields[1]))
            }
            catch(Exception e) {
                company = null
                correct = false
                println '*** Problem detected: GenericOperation not found *****'
                println "Line ${count}: Oper ID = ${fields[1]}"
                println "Amount: ${fields[3]} Date: ${fields[2]}"
                println '***************** Skipped ****************************'                
            }
            
            try {
                transDate = new Date().parse("dd/MM/yyyy", fields[2])
            }
            catch(Exception e) {
                company = null
                correct = false
                println '**** Problem detected: transactionDate not found *****'
                println "Line ${count}: Business ID = ${fields[0]}"
                println "Amount: ${fields[3]} Date: ${fields[2]}"
                println '***************** Skipped ****************************'                  
            }
            
            transAmount = new Double(fields[3])
            
            if(transAmount > 0) {
                def transInstance = new BusinessTransaction(
                    operationType:      oper,
                    transactionDate:    transDate,
                    transactionAmount:  transAmount,
                    transactionRemarks: fields[4],
                    operator:           fields[5],
                    cash:               new Integer(fields[6]),
                    company:            company
                ).save(flush: true)
            
                businessTransactionService.createPeer(transInstance, false)
            }
            else {
                println "*** Zero Transaction at ${count} Line: ${fields[2]} - ${fields[3]} SKIPPED"
            }
        }
        
        println ''
        println "Transactions import completed: ${count} Records processed"
    }
    
    /**
     *  Emulation of Transactions for first Company
     */
    void emulateTransactions() {
        def company = Business.get(2)
        def operator = company?.users[0]
        BusinessTransaction trans
        
        println ""
        println "Transactions emulation"
        println "Company  : ${company.name}"
        println "Operator : ${operator?.name}"
        
        //  January entries
        
        def listing = company.businessTransactions
        println "Initial list ${listing}"
        
        trans =  new BusinessTransaction(
            operationType:      GenericOperation.findByCode(1),
            transactionDate:    new Date().copyWith(year: 2015, month: 0, dayOfMonth: 3),
            transactionAmount:  50000.0,
            transactionRemarks: 'Capital Injection',
            operator:           "${operator?.name}",
            company:            company,
            cash:               0
        )
        
        listing = []
        
        
        if(trans == null) {
            println '*** Instance of BusinessTransaction was not created'
        }
        
        if(!trans.validate()) {
            println trans.errors
        }
        trans.save()
        
        listing << trans
       
        
        trans =
        new BusinessTransaction(
            operationType:      GenericOperation.findByCode(2),
            transactionDate:    new Date().copyWith(year: 2015, month: 0, dayOfMonth: 8),
            transactionAmount:  20000.0,
            transactionRemarks: 'Advance from Directors',
            operator:           "${operator?.name}",
            company:            company,
            cash:               0
        ).save(flush: true) 
        
        listing << trans
        
        trans = 
        new BusinessTransaction(
            operationType:      GenericOperation.findByCode(34),
            transactionDate:    new Date().copyWith(year: 2015, month: 0, dayOfMonth: 8),
            transactionAmount:  150.0,
            transactionRemarks: 'Telephone Bill',
            operator:           "${operator?.name}",
            company:            company,
            cash:               0
        ).save(flush: true) 
        listing << trans
        
        trans = 
        new BusinessTransaction(
            operationType:      GenericOperation.findByCode(32),
            transactionDate:    new Date().copyWith(year: 2015, month: 0, dayOfMonth: 25),
            transactionAmount:  5400.0,
            transactionRemarks: 'Salary for January',
            operator:           "${operator?.name}",
            company:            company,
            cash:               0
        ).save(flush: true) 
        listing << trans
        
        trans = 
        new BusinessTransaction(
            operationType:      GenericOperation.findByCode(6),
            transactionDate:    new Date().copyWith(year: 2015, month: 0, dayOfMonth: 30),
            transactionAmount:  45000.0,
            transactionRemarks: 'Vehicle Purchase: Proton Persona',
            operator:           "${operator?.name}",
            company:            company,
            cash:               0
        ).save(flush: true) 
        listing << trans
        
        //  February entries
        
        trans = 
        new BusinessTransaction(
            operationType:      GenericOperation.findByCode(14),
            transactionDate:    new Date().copyWith(year: 2015, month: 1, dayOfMonth: 1),
            transactionAmount:  8500.0,
            transactionRemarks: 'Sales',
            operator:           "${operator?.name}",
            company:            company,
            cash:               1
        ).save(flush: true) 
        listing << trans
        
        trans = 
        new BusinessTransaction(
            operationType:      GenericOperation.findByCode(14),
            transactionDate:    new Date().copyWith(year: 2015, month: 1, dayOfMonth: 3),
            transactionAmount:  5000.0,
            transactionRemarks: 'Sales',
            operator:           "${operator?.name}",
            company:            company,
            cash:               0
        ).save(flush: true) 
        listing << trans
        
        trans = 
        new BusinessTransaction(
            operationType:      GenericOperation.findByCode(14),
            transactionDate:    new Date().copyWith(year: 2015, month: 1, dayOfMonth: 10),
            transactionAmount:  6540.0,
            transactionRemarks: 'Sales',
            operator:           "${operator?.name}",
            company:            company,
            cash:               0
        ).save(flush: true) 
        listing << trans
        
        trans = 
        new BusinessTransaction(
            operationType:      GenericOperation.findByCode(36),
            transactionDate:    new Date().copyWith(year: 2015, month: 1, dayOfMonth: 11),
            transactionAmount:  550.80,
            transactionRemarks: 'Untility Bills',
            operator:           "${operator?.name}",
            company:            company,
            cash:               0
        ).save(flush: true) 
        listing << trans
        
        trans = 
        new BusinessTransaction(
            operationType:      GenericOperation.findByCode(10),
            transactionDate:    new Date().copyWith(year: 2015, month: 1, dayOfMonth: 14),
            transactionAmount:  8500.0,
            transactionRemarks: 'Small Office Renovation',
            operator:           "${operator?.name}",
            company:            company,
            cash:               0
        ).save(flush: true) 
        listing << trans
        
        trans = 
        new BusinessTransaction(
            operationType:      GenericOperation.findByCode(32),
            transactionDate:    new Date().copyWith(year: 2015, month: 1, dayOfMonth: 25),
            transactionAmount:  5400.0,
            transactionRemarks: 'Salary for February',
            operator:           "${operator?.name}",
            company:            company,
            cash:               0
        ).save(flush: true) 
        listing << trans
        
        trans = 
        new BusinessTransaction(
            operationType:      GenericOperation.findByCode(34),
            transactionDate:    new Date().copyWith(year: 2015, month: 1, dayOfMonth: 26),
            transactionAmount:  150.0,
            transactionRemarks: 'Telephone Bill',
            operator:           "${operator?.name}",
            company:            company,
            cash:               0
        ).save(flush: true) 
        listing << trans
        
        //  March entries

        trans = 
        new BusinessTransaction(
            operationType:      GenericOperation.findByCode(14),
            transactionDate:    new Date().copyWith(year: 2015, month: 2, dayOfMonth: 1),
            transactionAmount:  8500.0,
            transactionRemarks: 'Sales',
            operator:           "${operator?.name}",
            company:            company,
            cash:               0
        ).save(flush: true) 
        listing << trans
        
        trans = 
        new BusinessTransaction(
            operationType:      GenericOperation.findByCode(14),
            transactionDate:    new Date().copyWith(year: 2015, month: 2, dayOfMonth: 3),
            transactionAmount:  5000.0,
            transactionRemarks: 'Sales',
            operator:           "${operator?.name}",
            company:            company,
            cash:               0
        ).save(flush: true) 
        listing << trans
        
        trans = 
        new BusinessTransaction(
            operationType:      GenericOperation.findByCode(14),
            transactionDate:    new Date().copyWith(year: 2015, month: 2, dayOfMonth: 10),
            transactionAmount:  6540.0,
            transactionRemarks: 'Sales',
            operator:           "${operator?.name}",
            company:            company,
            cash:               0
        ).save(flush: true) 
        listing << trans
        
        trans = 
        new BusinessTransaction(
            operationType:      GenericOperation.findByCode(36),
            transactionDate:    new Date().copyWith(year: 2015, month: 2, dayOfMonth: 11),
            transactionAmount:  540.80,
            transactionRemarks: 'Untility Bills',
            operator:           "${operator?.name}",
            company:            company,
            cash:               0
        ).save(flush: true) 
        listing << trans
        
        trans = 
        new BusinessTransaction(
            operationType:      GenericOperation.findByCode(33),
            transactionDate:    new Date().copyWith(year: 2015, month: 2, dayOfMonth: 14),
            transactionAmount:  1500.0,
            transactionRemarks: 'Pantries',
            operator:           "${operator?.name}",
            company:            company,
            cash:               0
        ).save(flush: true) 
        listing << trans
        
        trans = 
        new BusinessTransaction(
            operationType:      GenericOperation.findByCode(32),
            transactionDate:    new Date().copyWith(year: 2015, month: 2, dayOfMonth: 25),
            transactionAmount:  5400.0,
            transactionRemarks: 'Salary for March',
            operator:           "${operator?.name}",
            company:            company,
            cash:               0
        ).save(flush: true) 
        listing << trans
        
        trans = 
        new BusinessTransaction(
            operationType:      GenericOperation.findByCode(34),
            transactionDate:    new Date().copyWith(year: 2015, month: 2, dayOfMonth: 28),
            transactionAmount:  150.0,
            transactionRemarks: 'Telephone Bill',
            operator:           "${operator?.name}",
            company:            company,
            cash:               0
        ).save(flush: true) 
        listing << trans
        
        trans = 
        new BusinessTransaction(
            operationType:      GenericOperation.findByCode(4),
            transactionDate:    new Date().copyWith(year: 2015, month: 2, dayOfMonth: 30),
            transactionAmount:  10000.0,
            transactionRemarks: 'Grant from Government',
            operator:           "${operator?.name}",
            company:            company,
            cash:               0
        ).save(flush: true) 
        listing << trans
        
        trans = 
        new BusinessTransaction(
            operationType:      GenericOperation.findByCode(8),
            transactionDate:    new Date().copyWith(year: 2015, month: 2, dayOfMonth: 31),
            transactionAmount:  18000.0,
            transactionRemarks: 'Office Equipment',
            operator:           "${operator?.name}",
            company:            company,
            cash:               0
        ).save(flush: true) 
        listing << trans
        
        //  April entries
        
        trans = 
        new BusinessTransaction(
            operationType:      GenericOperation.findByCode(14),
            transactionDate:    new Date().copyWith(year: 2015, month: 3, dayOfMonth: 1),
            transactionAmount:  8500.0,
            transactionRemarks: 'Sales',
            operator:           "${operator?.name}",
            company:            company,
            cash:               0
        ).save(flush: true) 
        listing << trans
        
        trans = 
        new BusinessTransaction(
            operationType:      GenericOperation.findByCode(14),
            transactionDate:    new Date().copyWith(year: 2015, month: 3, dayOfMonth: 3),
            transactionAmount:  5000.0,
            transactionRemarks: 'Sales',
            operator:           "${operator?.name}",
            company:            company,
            cash:               0
        ).save(flush: true) 
        listing << trans
        
        trans = 
        new BusinessTransaction(
            operationType:      GenericOperation.findByCode(14),
            transactionDate:    new Date().copyWith(year: 2015, month: 3, dayOfMonth: 10),
            transactionAmount:  6540.0,
            transactionRemarks: 'Sales',
            operator:           "${operator?.name}",
            company:            company,
            cash:               0
        ).save(flush: true) 
        listing << trans
        
        trans = 
        new BusinessTransaction(
            operationType:      GenericOperation.findByCode(36),
            transactionDate:    new Date().copyWith(year: 2015, month: 3, dayOfMonth: 11),
            transactionAmount:  540.80,
            transactionRemarks: 'Untility Bills',
            operator:           "${operator?.name}",
            company:            company,
            cash:               0
        ).save(flush: true) 
        listing << trans
        
        trans = 
        new BusinessTransaction(
            operationType:      GenericOperation.findByCode(32),
            transactionDate:    new Date().copyWith(year: 2015, month: 3, dayOfMonth: 25),
            transactionAmount:  5800.0,
            transactionRemarks: 'Salary for April',
            operator:           "${operator?.name}",
            company:            company,
            cash:               0
        ).save(flush: true) 
        listing << trans
        
        trans = 
        new BusinessTransaction(
            operationType:      GenericOperation.findByCode(34),
            transactionDate:    new Date().copyWith(year: 2015, month: 3, dayOfMonth: 28),
            transactionAmount:  150.0,
            transactionRemarks: 'Telephone Bill',
            operator:           "${operator?.name}",
            company:            company,
            cash:               0
        ).save(flush: true) 
        listing << trans
        
        trans = 
        new BusinessTransaction(
            operationType:      GenericOperation.findByCode(10),
            transactionDate:    new Date().copyWith(year: 2015, month: 3, dayOfMonth: 30),
            transactionAmount:  15000.0,
            transactionRemarks: 'Car repair',
            operator:           "${operator?.name}",
            company:            company,
            cash:               0
        ).save(flush: true) 
        listing << trans
        
        //  May enrties
        
        trans = 
        new BusinessTransaction(
            operationType:      GenericOperation.findByCode(14),
            transactionDate:    new Date().copyWith(year: 2015, month: 4, dayOfMonth: 1),
            transactionAmount:  850.0,
            transactionRemarks: 'Sales',
            operator:           "${operator?.name}",
            company:            company,
            cash:               1
        ).save(flush: true)
        listing << trans
            
        trans = 
        new BusinessTransaction(
            operationType:      GenericOperation.findByCode(8),
            transactionDate:    new Date().copyWith(year: 2015, month: 4, dayOfMonth: 3),
            transactionAmount:  2850.0,
            transactionRemarks: 'Laptop',
            operator:           "${operator?.name}",
            company:            company,
            cash:               0
        ).save(flush: true)            
        listing << trans
        
        trans =
        new BusinessTransaction(
            operationType:      GenericOperation.findByCode(14),
            transactionDate:    new Date().copyWith(year: 2015, month: 4, dayOfMonth: 4),
            transactionAmount:  670.0,
            transactionRemarks: 'Sales',
            operator:           "${operator?.name}",
            company:            company,
            cash:               1
        ).save(flush: true)            
        listing << trans
        
        trans = 
        new BusinessTransaction(
            operationType:      GenericOperation.findByCode(29),
            transactionDate:    new Date().copyWith(year: 2015, month: 4, dayOfMonth: 6),
            transactionAmount:  1500.0,
            transactionRemarks: 'Office cleaning',
            operator:           "${operator?.name}",
            company:            company,
            cash:               0
        ).save(flush: true)            
        listing << trans

        company.businessTransactions = listing
        company.save(flush: true)

        println "Total Transactions added: ${company?.businessTransactions.size()}"
    }
    
    void parseBusinessFile() {
        def filePath = "resources/data.txt"
        def fileContent = grailsApplication.mainContext.getResource("classpath:$filePath").file
        def count = 0;
        
        println "\nImporting from file: ${filePath}"
        
        fileContent.splitEachLine(',') {fields ->
            new Business(
                name:       fields[0],
                regNumber:  fields[1],
                city:       fields[2]
            ).save()
            
            println "${++count} ... Business instance imported for ${fields[0]}"
        }
        
        println "Total Business instances created: ${Business.count()}"
    }
    
    /***************************************************************************
     *  Correcting GenericOperation instances to comply with latest changes: the
     *  scope of types remains, and must be attanded manually if required
     **************************************************************************/
    void updateGenericOperationTypes() {
        println ''
        println 'Updating list of Generic Operations'

        def updates = 0
        
        
        
        def cashHandOp = GenericOperation.findByCode(1000)
        def cashBankOp = GenericOperation.findByCode(1010)
        
        if(cashHandOp == null) {
            cashHandOp = new GenericOperation(
                code:           1000,
                name:           'Cash in Hands',
                inbound:        false,
                outbound:       true,
                accountType:    AccountType.findByCode(5),
                group:          CFGroup.findByCode(5),
                actual:         0
            ).save(flush: true) 
            
            println "Operation created: ${cashHandOp}"
        }
        
        if(cashBankOp == null) {
            cashBankOp = new GenericOperation(
                code:           1010,
                name:           'Cash at Bank',
                inbound:        false,
                outbound:       true,
                accountType:    AccountType.findByCode(5),
                group:          CFGroup.findByCode(6),
                actual:         0
            ).save(flush: true) 
            
            println "Operation created: ${cashBankOp}"
        } 
        
        println 'Cheking for existance of mirrored Operations'
        
        if(!GenericOperation.findByCode(1020)) {
            new GenericOperation(
                code: 1020,
                name: 'Cash Withdrawal',
                inbound: false,
                outbound: true,
                accountType: AccountType.findByCode(5),
                group: CFGroup.findByCode(5),
                actual: 0,
                mirrorCash: cashBankOp
            ).save(flush: true) 
            
            println '... Cash Withdrawal created'
        }
        else {
            println '... Cash Withdrawal exists'
        }
        
        if(!GenericOperation.findByCode(1030)) {
            new GenericOperation(
                code: 1030,
                name: 'Cash Deposit to Bank',
                inbound: false,
                outbound: true,
                accountType: AccountType.findByCode(5),
                group: CFGroup.findByCode(6),
                actual: 0,
                mirrorCash: cashHandOp
            ).save(flush: true)
            
            println '... Cash Deposit created'
        }
        else {
            println '... Cash Deposit exists'
        }
        
        if(!GenericOperation.findByCode(8)) {
            def opCode8 = new GenericOperation(
                code:           8,
                name:           'Pembelian Peralatan Ofis',
                name_EN:        'Purchase of Office Equipment',
                inbound:        false,
                outbound:       true,
                accountType:    AccountType.findByCode(4),
                group:          CFGroup.findByCode(4),
                actual:         1,
                mirrorCash:     cashHandOp,
                mirrorBank:     cashBankOp
            )
            
            if(opCode8.validate()) {
                opCode8.save(flush: true)
                println "*** Operation with Code 8 has been added"
            }
            else {
                opCode8 = null
                println '*** Creation of Operation with Code 8 failed'
            }
            
        }
        
        def operations = GenericOperation.list()
        println "Total items found: ${operations.size()}"
        
        operations.each {
            switch(it?.code) {
            case [1, 2, 3, 4, 5, 14]:
                it.mirrorCash = cashHandOp
                it.mirrorBank = cashBankOp
                it.actual = 1
                it.group = CFGroup.findByCode(1)
                it.save()
                updates++
                break
                
            case [15, 16, 17, 18]:
                it.mirrorCash = cashHandOp
                it.mirrorBank = cashBankOp
                it.actual = 1
                it.group = CFGroup.findByCode(2)
                it.save()
                updates++
                break  
                
            case 19..37:
                it.mirrorCash = cashHandOp
                it.mirrorBank = cashBankOp
                it.actual = 1
                it.group = CFGroup.findByCode(3)
                it.save()
                updates++
                break 
                
            case [6, 7, 8, 9, 10, 11]:
                it.mirrorCash = cashHandOp
                it.mirrorBank = cashBankOp
                it.actual = 1
                it.group = CFGroup.findByCode(4)
                it.save()
                updates++
                break                
            }
        }
        
        println "Total Operations updated: ${updates}"
    }   //  End of 'updateGenericOperationTypes'
    
    /***************************************************************************
     *  Adding English Translation to Operation Types
     **************************************************************************/
    
    void translateOperationTypes() {
        def opType
        int counter = 0
        
        println ''
        println 'Adding English Translation to Operation Types:'
        
        opType = GenericOperation.findByCode(1000)
        
        if(opType == null) {
            println 'No instance for Code: 1000'
        }
        
        if(opType.name_EN == null || opType.name_EN == '') {
            opType.name_EN = 'Cash in Hands'
            opType.save()
            counter++
        }
        
        opType = GenericOperation.findByCode(1010)

        if(opType == null) {
            println 'No instance for Code: 1010'
        }
        
        if(opType.name_EN == null || opType.name_EN == '') {
            opType.name_EN = 'Cash at Bank'
            opType.save()
            counter++
        }
        
        opType = GenericOperation.findByCode(1020)
        
        if(opType == null) {
            println 'No instance for Code: 1020'
        }
        
        if(opType.name_EN == null || opType.name_EN == '') {
            opType.name_EN = 'Cash Withdrawal'
            opType.save()
            counter++
        } 
        
        opType = GenericOperation.findByCode(1030)
        
        if(opType == null) {
            println 'No instance for Code: 1030'
        }
        
        if(opType.name_EN == null || opType.name_EN == '') {
            opType.name_EN = 'Cash Deposit to Bank'
            opType.save()
            counter++
        }
        
        //  In and Out Operations
        
        opType = GenericOperation.findByCode(1)
        
        if(opType == null) {
            println 'No instance for Code: 1'
        }
        
        if(opType.name_EN == null || opType.name_EN == '') {
            opType.name_EN = 'Additional Capital'
            opType.save()
            counter++
        }
        
        opType = GenericOperation.findByCode(2)
        
        if(opType == null) {
            println 'No instance for Code: 2'
        }
        
        if(opType.name_EN == null || opType.name_EN == '') {
            opType.name_EN = 'Advances from Directors'
            opType.save()
            counter++
        } 
        
        opType = GenericOperation.findByCode(3)
        
        if(opType == null) {
            println 'No instance for Code: 3'
        }
        
        if(opType.name_EN == null || opType.name_EN == '') {
            opType.name_EN = 'Capital Injection'
            opType.save()
            counter++
        }
        
        opType = GenericOperation.findByCode(4)
        
        if(opType == null) {
            println 'No instance for Code: 4'
        }        
        
        if(opType.name_EN == null || opType.name_EN == '') {
            opType.name_EN = 'Grant Received'
            opType.save()
            counter++
        }
        
        opType = GenericOperation.findByCode(5)
        
        if(opType == null) {
            println 'No instance for Code: 5'
        }        
        
        if(opType.name_EN == null || opType.name_EN == '') {
            opType.name_EN = 'Loan Received'
            opType.save()
            counter++
        } 
        
        opType = GenericOperation.findByCode(6)
        
        if(opType == null) {
            println 'No instance for Code: 6'
        }        
        
        if(opType.name_EN == null || opType.name_EN == '') {
            opType.name_EN = 'Purchase of Vehicle'
            opType.save()
            counter++
        }
        
        opType = GenericOperation.findByCode(7)
        
        if(opType == null) {
            println 'No instance for Code: 7'
        }
        
        
        if(opType.name_EN == null || opType.name_EN == '') {
            opType.name_EN = 'Purchase of Plants and Machineries'
            opType.save()
            counter++
        }
        
        opType = GenericOperation.findByCode(8)
        
        if(opType == null) {
            println '*** No instance for Code: 8'
            println '... skipped'
        }
        else {
            if(opType.name_EN == null || opType.name_EN == '') {
                opType.name_EN = 'Purchase of Office Equipment'
                opType.save()
                counter++
            } 
        }
        
        opType = null
        opType = GenericOperation.findByCode(9)
        
        if(opType == null) {
            println 'No instance for Code: 9'
        }
        
        
        if(opType.name_EN == null || opType.name_EN == '') {
            opType.name_EN = 'Loan Repayment'
            opType.save()
            counter++
        }
        
        opType = GenericOperation.findByCode(10)
        
        if(opType == null) {
            println 'No instance for Code: 10'
        }        
        
        if(opType.name_EN == null || opType.name_EN == '') {
            opType.name_EN = 'Renovations'
            opType.save()
            counter++
        }
        
        opType = GenericOperation.findByCode(11)
        
        if(opType == null) {
            println 'No instance for Code: 12'
        }        
        
        if(opType.name_EN == null || opType.name_EN == '') {
            opType.name_EN = 'Purchase of Furniture/Fittings'
            opType.save()
            counter++
        } 
        
        opType = GenericOperation.findByCode(14)
        
        if(opType == null) {
            println '*** No instance for Code: 14'
            println '... skipped'
        }         
        else {
            if(opType.name_EN == null || opType.name_EN == '') {
                opType.name_EN = 'Sales'
                opType.save()
                counter++
            }
        }
        
        opType = GenericOperation.findByCode(15)
        
        if(opType == null) {
            println '*** No instance for Code: 15'
            println '... skipped'
        }         
        else {        
        
            if(opType.name_EN == null || opType.name_EN == '') {
                opType.name_EN = 'Raw Materials'
                opType.save()
                counter++
            }
        }
        
        opType = GenericOperation.findByCode(16)
        
        if(opType == null) {
            println '*** No instance for Code: 16'
            println '... skipped'
        }         
        else {         
            if(opType.name_EN == null || opType.name_EN == '') {
                opType.name_EN = 'Wages'
                opType.save()
                counter++
            } 
        }
        
        opType = GenericOperation.findByCode(17)
        
        if(opType == null) {
            println '*** No instance for Code: 17'
            println '... skipped'
        }         
        else {         
            if(opType.name_EN == null || opType.name_EN == '') {
                opType.name_EN = 'Carriage Inwards'
                opType.save()
                counter++
            }
        }
        
        opType = GenericOperation.findByCode(18)
        
        if(opType == null) {
            println '*** No instance for Code: 18'
            println '... skipped'
        }         
        else {         
            if(opType.name_EN == null || opType.name_EN == '') {
                opType.name_EN = 'Production Cost'
                opType.save()
                counter++
            }
        }
        
        opType = GenericOperation.findByCode(19)
        
        if(opType == null) {
            println '*** No instance for Code: 19'
            println '... skipped'
        }         
        else {         
            if(opType.name_EN == null || opType.name_EN == '') {
                opType.name_EN = 'Accomodation Cost'
                opType.save()
                counter++
            } 
        }
        
        opType = GenericOperation.findByCode(20)
        
        if(opType == null) {
            println '*** No instance for Code: 20'
            println '... skipped'
        }         
        else {         
            if(opType.name_EN == null || opType.name_EN == '') {
                opType.name_EN = 'Advertisement'
                opType.save()
                counter++
            }
        }
        
        opType = GenericOperation.findByCode(21)
        
        if(opType == null) {
            println '*** No instance for Code: 21'
            println '... skipped'
        }         
        else {         
            if(opType.name_EN == null || opType.name_EN == '') {
                opType.name_EN = 'Bank Charges'
                opType.save()
                counter++
            }
        }
        
        opType = GenericOperation.findByCode(22)
        
        if(opType == null) {
            println '*** No instance for Code: 22'
            println '... skipped'
        }         
        else {         
            if(opType.name_EN == null || opType.name_EN == '') {
                opType.name_EN = 'Entertainment'
                opType.save()
                counter++
            } 
        }
        
        opType = GenericOperation.findByCode(23)
        
        if(opType == null) {
            println '*** No instance for Code: 23'
            println '... skipped'
        }         
        else {         
            if(opType.name_EN == null || opType.name_EN == '') {
                opType.name_EN = 'EPF and SOCSO'
                opType.save()
                counter++
            }
        }
        
        opType = GenericOperation.findByCode(24)
        
        if(opType == null) {
            println '*** No instance for Code: 24'
            println '... skipped'
        }         
        else {         
            if(opType.name_EN == null || opType.name_EN == '') {
                opType.name_EN = 'Legal Fees'
                opType.save()
                counter++
            }
        }
        
        opType = GenericOperation.findByCode(25)
        
        if(opType == null) {
            println '*** No instance for Code: 25'
            println '... skipped'
        }         
        else {         
            if(opType.name_EN == null || opType.name_EN == '') {
                opType.name_EN = 'Maintenance of Office and Equipment'
                opType.save()
                counter++
            } 
        }
        
        opType = GenericOperation.findByCode(26)
        
        if(opType == null) {
            println '*** No instance for Code: 26'
            println '... skipped'
        }         
        else {         
            if(opType.name_EN == null || opType.name_EN == '') {
                opType.name_EN = 'Maintenance of Vehicle'
                opType.save()
                counter++
            }
        }
        
        opType = GenericOperation.findByCode(27)
        
        if(opType.name_EN == null || opType.name_EN == '') {
            opType.name_EN = 'Marketing and Promotion'
            opType.save()
            counter++
        }
        
        opType = GenericOperation.findByCode(28)
        
        if(opType == null) {
            println '*** No instance for Code: 28'
            println '... skipped'
        }         
        else {         
            if(opType.name_EN == null || opType.name_EN == '') {
                opType.name_EN = 'Medical Expenses'
                opType.save()
                counter++
            } 
        }
        
        opType = GenericOperation.findByCode(29)
        
        if(opType == null) {
            println '*** No instance for Code: 29'
            println '... skipped'
        }         
        else {         
            if(opType.name_EN == null || opType.name_EN == '') {
                opType.name_EN = 'Office Expenses'
                opType.save()
                counter++
            }
        }
        
        opType = GenericOperation.findByCode(30)
        
        if(opType == null) {
            println '*** No instance for Code: 30'
            println '... skipped'
        }         
        else {         
            if(opType.name_EN == null || opType.name_EN == '') {
                opType.name_EN = 'Printing and Stationaries'
                opType.save()
                counter++
            }
        }
        
        opType = GenericOperation.findByCode(31)
        
        if(opType == null) {
            println '*** No instance for Code: 31'
            println '... skipped'
        }         
        else {         
            if(opType.name_EN == null || opType.name_EN == '') {
                opType.name_EN = 'Rental of Premises'
                opType.save()
                counter++
            } 
        }
        
        opType = GenericOperation.findByCode(32)
        
        if(opType == null) {
            println '*** No instance for Code: 32'
            println '... skipped'
        }         
        else {         
            if(opType.name_EN == null || opType.name_EN == '') {
                opType.name_EN = 'Salaries and Allowances'
                opType.save()
                counter++
            }
        }
        
        opType = GenericOperation.findByCode(33)
        
        if(opType == null) {
            println '*** No instance for Code: 33'
            println '... skipped'
        }         
        else {         
            if(opType.name_EN == null || opType.name_EN == '') {
                opType.name_EN = 'Pantry Expenses'
                opType.save()
                counter++
            }
        }
        
        opType = GenericOperation.findByCode(34)
        
        if(opType == null) {
            println '*** No instance for Code: 34'
            println '... skipped'
        }         
        else {         
            if(opType.name_EN == null || opType.name_EN == '') {
                opType.name_EN = 'Telephone, Fax and Internet'
                opType.save()
                counter++
            } 
        }
        
        opType = GenericOperation.findByCode(35)
        
        if(opType == null) {
            println '*** No instance for Code: 35'
            println '... skipped'
        }         
        else {         
            if(opType.name_EN == null || opType.name_EN == '') {
                opType.name_EN = 'Travelling Cost'
                opType.save()
                counter++
            }
        }
        
        opType = GenericOperation.findByCode(36)
        
        if(opType == null) {
            println '*** No instance for Code: 36'
            println '... skipped'
        }         
        else {         
            if(opType.name_EN == null || opType.name_EN == '') {
                opType.name_EN = 'Water and Electricity'
                opType.save()
                counter++
            }
        }
        
        opType = GenericOperation.findByCode(37)
        
        if(opType == null) {
            println '*** No instance for Code: 37'
            println '... skipped'
        }         
        else { 
            if(opType.name_EN == null || opType.name_EN == '') {
                opType.name_EN = 'Sales Return'
                opType.save()
                counter++
            }
        }
        
        println '...completed'
        println "${counter} Records were added translation"
        
    }   //  End of 'translateOperationTypes()'
}