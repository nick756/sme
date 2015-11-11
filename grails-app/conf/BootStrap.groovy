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
        
        //  Restoring list og GenericOperation from backup file
        //  --------------------------------------------------------------------
        if(GenericOperation.list().size() == 0) {
            println "\n... creating list of Generic Operation Types"
            
//            def filePath = "resources/operations.txt"
//            def fileContent = grailsApplication.mainContext.getResource("classpath:$filePath").file
//            def count = 0;   
            def path = "C:/export/operations.txt"
            def fileContent = new File("${path}").text
            
            fileContent.splitEachLine('#') {fields ->
                def accountType = AccountType.findByCode(fields[1])
                def group = CFGroup.findByCode(fields[2])
                Integer cashCode
                Integer bankCode
                
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
                    group:          group
                ).save(flush: true)
            }
            
            println "Total " + GenericOperation.count() + " instances restored from file"
            println "... updating peer settings"
            
            def opList = GenericOperation.createCriteria().list() {
                order('code')
            }
            
            opList.each {operation ->
                print "Processing ${operation.code}: ${operation.name_EN}"
                
                if(operation.mirrorCashCode > 0) {
                    def mirrorCash = GenericOperation.findByCode(operation.mirrorCashCode)
                    operation.mirrorCash = mirrorCash
                    print " ... cash mirror added ${mirrorCash.code}"
                }
                if(operation.mirrorBankCode > 0) {
                    def mirrorBank = GenericOperation.findByCode(operation.mirrorBankCode)
                    operation.mirrorBank = mirrorBank
                    print " ... bank mirror added ${mirrorBank.code}"
                }
                
            }
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
        
        if(Business.list().size() == 10000) {       //  Disabled
            def industry1 = Industry.findByCode(1)
            def industry2 = Industry.findByCode(2)
            def industry3 = Industry.findByCode(3)
            def industry4 = Industry.findByCode(4)
            
            new Business(
                dateCreated: new Date(),
                name: "Amortization Services Sdn Bhd",
                regNumber: "12345-A",
                industry: industry1,
                city: "Kuala Lumpur"
            ).save()
            
            new Business(
                dateCreated: new Date(),
                name: "Voluntary Services Sdn Bhd",
                regNumber: "12346-A",
                industry: industry2,
                city: "Kuala Lumpur"
            ).save() 
            
            new Business(
                dateCreated: new Date(),
                name: "Superior Constructors Sdn Bhd",
                regNumber: "12345-B",
                industry: industry4,
                city: "Melaka"
            ).save()
            
            new Business(
                dateCreated: new Date(),
                name: "Outstanding Design Sdn Bhd",
                regNumber: "12347-A",
                industry: industry2,
                city: "Kuala Lumpur"
            ).save() 
            
            println "\nList of Businesses created: " + Business.count() + " instances"
            
            //parseBusinessFile();
        }
        
        //  Creation of default Business Profiles
            
        if(GenericProfile.list().size() == 0) {
            def prof = new GenericProfile(
                code: 1,
                name: 'Trading Activities'
            )
                
            prof.save(flush: true);
                
               
            //  Auto-populating Profile 'Trading Activities'
            
            GenericOperation.list().each {operationType ->
                prof.addToOperations(
                    new ProfileLink(
                        operation: operationType,
                        profile: prof,
                        active: true
                    )
                )
            }
                
            //            for(int i = 0; i < 36; i++) {
            //                
            //                if(i != 11 && i != 12) {
            //                    prof.addToOperations(
            //                        new ProfileLink(
            //                            operation: GenericOperation.findByCode(i + 1),
            //                            profile: prof,
            //                            active: true
            //                        ).save(flush: true)
            //                    ) 
            //                }
            //            }
                
            new GenericProfile(
                code: 2,
                name: 'Food Manufacturing Activities'
            ).save()   
                
            new GenericProfile(
                code: 3,
                name: 'Restorant/Food Stall Operations'
            ).save()                 
                
            println ""
            println "\nGeneric Profiles created: ${GenericProfile.count()} instances"
            println "Profile \'${prof.name}\' has been added Operations:"
            def list = prof.getOperations().asList().sort{it.operation.code}
                
            list.each {
                println "- ${String.format('%1$4s', it?.operation?.code)} ${it?.operation?.name}"
                //println "- " + it?.operation?.code + " " + it?.operation?.name
            }
        }
        
        /***********************************************************************
         *  Importing/Restoring Business instances
         * ********************************************************************/
        
        if(Business.list().size() == 0) {
            def pathBusiness = "C:/export/businesses.txt"
            def file = new File("${pathBusiness}")
            def content = file.text
            
            def industry    = null
            def profile     = null
            Date registrationDate   = null
            Date incorpDate         = null
            Integer count = 0
            Integer intID
            
            println ''
            println '------------- Import of Businesses -----------------'
            println "File size: ${file.length()} bytes"
            
            content.splitEachLine('#') {fields ->
                industry = null
                profile  = null
                incorpDate = null
                registrationDate = null
                
                println "Processing ${++count} Record: ${fields[3]}"
                
                if(fields[0] != 'null') {
                    try {
                        intID = new Integer(fields[0])
                    }
                    catch(Exception e) {
                        println "************* Problem:"
                        println "${fields[0]} -- ${fields[1]} -- ${fields[2]} -- ${fields[3]}"
                    }
                }
                
                if(fields[1] != 'null') {
                    profile = GenericProfile.findByCode(new Integer(fields[1]))
                }
                
                if(fields[2] != 'null' && fields[2] != 0) {
                    industry = Industry.findByCode(new Integer(fields[2]))
                }
                
                if(fields[6] != 'null') {
                    incorpDate = new Date().parse("dd/MM/yyyy", fields[6])
                }
                
                if(fields[7] != 'null') {
                    registrationDate = new Date().parse("dd/MM/yyyy", fields[7])
                }
                
                new Business(
                    internalID: intID,
                    name:       fields[3],
                    accountNo:  fields[4],
                    regNumber:  fields[5],
                    incorpDate: incorpDate,
                    registrationDate:   registrationDate,
                    address:            fields[8],
                    city:               fields[9],
                    industry:           industry,
                    profile:            profile
                ).save(flush: true)
            }
            
            println "Total instance imported: ${Business.list().size()}"
        }
        
        //  Creating default Users
        
        if(User.list().size() == 0) {
            
            userRole = UserRole.findByCode(1)
            
            println "\nUser Role selected: " + userRole
            
            def user = new com.sme.entities.User (
                dateCreated: new Date(),
                name: "Nikolay",
                login: "nick",
                passw: "1234"
            )
            
            user.role = userRole
            user.save()
            
            new User(
                dateCreated: new Date(),
                name: 'Mohar',
                login: 'mohar',
                passw: '1234',
                role: userRole
            ).save()
            
            println "User ${user?.name} created"
            
            //  Creating Operators and assigning to a Company
            
            def vlad = new User(
                name: 'Vladimir Gundartsev',
                login: 'vlad',
                passw: '1234',
                role: UserRole.findByCode(2),
                company: Business.get(1)
            ).save(flush: true)
        
            def opUser1 = new User(
                name: 'Andreano Choppolo',
                login: 'andrea',
                passw: '1234',
                role: UserRole.findByCode(2),
                company: Business.get(2)
            ).save(flush: true)
        
            def opUser2 = new User(
                name: 'Nikolay',
                login: 'nick_sme',
                passw: '1234',
                role: UserRole.findByCode(2),
                company: Business.get(2)
            ).save(flush: true)          
        
            println ''
            println 'SME Operator Users created:'
        
            def inst = Business.get(2)
            inst.addToUsers(opUser1)
            inst.addToUsers(opUser2)
        
            println "${inst?.name}: ${inst?.users}"
        
            Business.get(1).addToUsers(vlad)
            println "${Business.get(1).name}: ${Business.get(1).users}"            
        }        

        //  Emulation of Business Transactions
        
        if(!BusinessTransaction.list()) {
            //emulateTransactions()
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
         *  Unconditional verification and updates for changes
         * ********************************************************************/
        
        println ''
        println '**************************************************************'
        println '         VERIFICATION OF MIGRATION ASPECTS'
        println '**************************************************************'
        println ''
        
        //updateGenericOperationTypes()
        //createTransactionsPeers()
        //translateOperationTypes()
    }

    
    def destroy = {
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
     *  Verifying and updating existing BusinessTransaction instances
     **************************************************************************/
    
    void createTransactionsPeers() {
        println ''
        println 'Verification of created BusinessTransaction inctances:'
        
        def transactions = BusinessTransaction.list()
        def cashBankType = GenericOperation.findByCode(1010)
        
        println "Total Records found: ${transactions.size()}"
        
        transactions.each {transaction ->
            if(transaction?.cash == null) {
                transaction.cash = 0 // Default value
                transaction.save()
                
                println "Defaulted to Bank Operation: ${transaction}"
            }
            
            //  Correcting 'Cash at Bank' entries
            
            if(transaction.operationType?.code == 13) {
                transaction.operationType = cashBankType
                transaction.save(flush: true)
                
                println "Corrected Transaction: ${transaction} to Code 1010"
            }
            
            if(transaction.operationType.actual > 0 && transaction?.peer == null) {
                println "Peer missing for: ${transaction}"
                businessTransactionService.createPeer(transaction)
            }
        }
    }   //  End of 'createTransactionPeers()'
    
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