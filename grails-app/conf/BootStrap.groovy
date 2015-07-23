import com.sme.entities.*

class BootStrap {
    def grailsApplication
    
    def init = { servletContext ->
        
        def userRole
        
        if(Month.list().size() == 0) {
            new Month(number: 1, name: 'JAN').save(flush: true)
            new Month(number: 2, name: 'FEB').save(flush: true)
            new Month(number: 3, name: 'MAR').save(flush: true)
            new Month(number: 4, name: 'APR').save(flush: true)
            new Month(number: 5, name: 'MAY').save(flush: true)
            new Month(number: 6, name: 'JUN').save(flush: true)
            new Month(number: 7, name: 'JUL').save(flush: true)
            new Month(number: 8, name: 'AUG').save(flush: true)
            new Month(number: 9, name: 'SEP').save(flush: true)
            new Month(number: 10, name: 'OCT').save(flush: true)
            new Month(number: 11, name: 'NOV').save(flush: true)
            new Month(number: 12, name: 'DEC').save(flush: true)
            
            println ''
            println "Months populated: ${Month.count()} instances"
        }
        
        if(AccountType.list().size() == 0) {
            println "\n... populating Account Types"
            
            new AccountType(
                code: 1,
                name: 'Capital'
            ).save()
            
            new AccountType(
                code: 2,
                name: 'Current Liabilities'
            ).save()            
            
            new AccountType(
                code: 3,
                name: 'Long Term Liabilities'
            ).save() 
            
            new AccountType(
                code: 4,
                name: 'Fixed Assets'
            ).save()            
            
            new AccountType(
                code: 5,
                name: 'Current Assets'
            ).save() 
            
            new AccountType(
                code: 6,
                name: 'Cost of Goods Sold'
            ).save() 
            
            new AccountType(
                code: 7,
                name: 'Income'
            ).save()  
            
            new AccountType(
                code: 8,
                name: 'Other Income'
            ).save() 
            
            new AccountType(
                code: 9,
                name: 'Expenses'
            ).save() 
            
            println "Total " + AccountType.count() + " instances created"
        }
        
        if(GenericOperation.list().size() == 0) {
            println "\n... creating list of Generic Operation Types"
            
            new GenericOperation(
                code: 1,
                name: 'Additional Capital',
                inbound: true,
                outbound: false,
                accountType: AccountType.findByCode(1)
            ).save()
            
            new GenericOperation(
                code: 2,
                name: 'Advance from Directors',
                inbound: true,
                outbound: false,
                accountType: AccountType.findByCode(1)
            ).save()       
            
            new GenericOperation(
                code: 3,
                name: 'Capital Injection',
                inbound: true,
                outbound: false,
                accountType: AccountType.findByCode(1)
            ).save()    
            
            new GenericOperation(
                code: 4,
                name: 'Grant Received',
                inbound: true,
                outbound: false,
                accountType: AccountType.findByCode(8)
            ).save()    
            
            new GenericOperation(
                code: 5,
                name: 'Loan Received',
                inbound: true,
                outbound: false,
                accountType: AccountType.findByCode(3)
            ).save()     
            
            new GenericOperation(
                code: 6,
                name: 'Purchase of Vehicle',
                inbound: false,
                outbound: true,
                accountType: AccountType.findByCode(4)
            ).save() 
            
            new GenericOperation(
                code: 7,
                name: 'Purchase of Plants and Machineries',
                inbound: false,
                outbound: true,
                accountType: AccountType.findByCode(4)
            ).save()    
            
            new GenericOperation(
                code: 8,
                name: 'Purchase of Office Equipment',
                inbound: false,
                outbound: true,
                accountType: AccountType.findByCode(4)
            ).save()  
            
            new GenericOperation(
                code: 9,
                name: 'Loan Repayment',
                inbound: false,
                outbound: true,
                accountType: AccountType.findByCode(3)
            ).save() 
            
            new GenericOperation(
                code: 10,
                name: 'Renovations',
                inbound: false,
                outbound: true,
                accountType: AccountType.findByCode(4)
            ).save() 
            
            new GenericOperation(
                code: 11,
                name: 'Purchase of Furniture/Fittings',
                inbound: false,
                outbound: true,
                accountType: AccountType.findByCode(4)
            ).save()             
            
            new GenericOperation(
                code: 12,
                name: 'Cash in Hands',
                inbound: false,
                outbound: true,
                accountType: AccountType.findByCode(5)
            ).save() 
            
            new GenericOperation(
                code: 13,
                name: 'Cash in Bank',
                inbound: false,
                outbound: true,
                accountType: AccountType.findByCode(5)
            ).save()  
            
            new GenericOperation(
                code: 14,
                name: 'Sales',
                inbound: true,
                outbound: false,
                accountType: AccountType.findByCode(7)
            ).save()     
            
            new GenericOperation(
                code: 15,
                name: 'Raw Materials',
                inbound: false,
                outbound: true,
                accountType: AccountType.findByCode(6)
            ).save() 
            
            new GenericOperation(
                code: 16,
                name: 'Wages',
                inbound: false,
                outbound: true,
                accountType: AccountType.findByCode(6)
            ).save() 
            
            new GenericOperation(
                code: 17,
                name: 'Carriage Inwards',
                inbound: false,
                outbound: true,
                accountType: AccountType.findByCode(6)
            ).save()  
            
            new GenericOperation(
                code: 18,
                name: 'Production Cost',
                inbound: false,
                outbound: true,
                accountType: AccountType.findByCode(9)
            ).save() 
            
            new GenericOperation(
                code: 19,
                name: 'Accommodation Cost',
                inbound: false,
                outbound: true,
                accountType: AccountType.findByCode(9)
            ).save() 
            
            new GenericOperation(
                code: 20,
                name: 'Advertisement',
                inbound: false,
                outbound: true,
                accountType: AccountType.findByCode(9)
            ).save() 
            
            new GenericOperation(
                code: 21,
                name: 'Bank Charges',
                inbound: false,
                outbound: true,
                accountType: AccountType.findByCode(9)
            ).save()
            
            new GenericOperation(
                code: 22,
                name: 'Entertainment',
                inbound: false,
                outbound: true,
                accountType: AccountType.findByCode(9)
            ).save()
            
            new GenericOperation(
                code: 23,
                name: 'EPF and SOCSO',
                inbound: false,
                outbound: true,
                accountType: AccountType.findByCode(9)
            ).save()
            
            new GenericOperation(
                code: 24,
                name: 'Legal Fees',
                inbound: false,
                outbound: true,
                accountType: AccountType.findByCode(9)
            ).save() 
            
            new GenericOperation(
                code: 25,
                name: 'Maintenance of Office and Equipment',
                inbound: false,
                outbound: true,
                accountType: AccountType.findByCode(9)
            ).save() 
            
            new GenericOperation(
                code: 26,
                name: 'Maintenance of Vehicle',
                inbound: false,
                outbound: true,
                accountType: AccountType.findByCode(9)
            ).save()
            
            new GenericOperation(
                code: 27,
                name: 'Marketing and Promotion',
                inbound: false,
                outbound: true,
                accountType: AccountType.findByCode(9)
            ).save() 
            
            new GenericOperation(
                code: 28,
                name: 'Medical Expenses',
                inbound: false,
                outbound: true,
                accountType: AccountType.findByCode(9)
            ).save() 
            
            new GenericOperation(
                code: 29,
                name: 'Office Expenses',
                inbound: false,
                outbound: true,
                accountType: AccountType.findByCode(9)
            ).save()
            
            new GenericOperation(
                code: 30,
                name: 'Printing and Stationaries',
                inbound: false,
                outbound: true,
                accountType: AccountType.findByCode(9)
            ).save()
            
            new GenericOperation(
                code: 31,
                name: 'Rental of Premise',
                inbound: false,
                outbound: true,
                accountType: AccountType.findByCode(9)
            ).save() 
            
            new GenericOperation(
                code: 32,
                name: 'Salaries and Allowances',
                inbound: false,
                outbound: true,
                accountType: AccountType.findByCode(9)
            ).save()
            
            new GenericOperation(
                code: 33,
                name: 'Pantry Expenses',
                inbound: false,
                outbound: true,
                accountType: AccountType.findByCode(9)
            ).save()
            
            new GenericOperation(
                code: 34,
                name: 'Telephone, Fax and Internet',
                inbound: false,
                outbound: true,
                accountType: AccountType.findByCode(9)
            ).save() 
            
            new GenericOperation(
                code: 35,
                name: 'Travelling Cost',
                inbound: false,
                outbound: true,
                accountType: AccountType.findByCode(9)
            ).save() 
            
            new GenericOperation(
                code: 36,
                name: 'Water and Electricity',
                inbound: false,
                outbound: true,
                accountType: AccountType.findByCode(9)
            ).save()             
            
            println "Total " + GenericOperation.count() + " instance created"
        }
        
        //  Initiating list of States
        
        if(State.list().size() == 0) {
            println "\n...populating State instances"
        }
        
        if(com.sme.entities.UserRole.list().size() == 0) {
            println ""
            
            new com.sme.entities.UserRole(
                code: 1,
                name: "Administrator"
            ).save()
            
            new com.sme.entities.UserRole(
                code: 2,
                name: "SME Operator"
            ).save() 
            
            println ""
            println "User Role Instances created"
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
        
        if(com.sme.entities.User.list().size() == 0) {
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
        }
        
        if(Business.list().size() == 0) {
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
            
            //  Creation of default Business Profiles
            
            if(GenericProfile.list().size() == 0) {
                def prof = new GenericProfile(
                    code: 1,
                    name: 'Trading Activities'
                )
                
                prof.save(flush: true);
                
               
                //  Auto-populating Profile 'Trading Activities'
                
                for(int i = 0; i < 36; i++) {
                    prof.addToOperations(
                        new ProfileLink(
                            operation: GenericOperation.findByCode(i + 1),
                            profile: prof,
                            active: true
                        ).save(flush: true)
                    )                    
                }
                
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
                    println "- " + it?.operation?.code + " " + it?.operation?.name
                }
            }
        }
        
        parseBusinessFile();
        
        //  Adding Operator User for Mobile Interface
        
        new User(
            name: 'Vladimir Gundartsev',
            login: 'vlad',
            passw: '1234',
            role: UserRole.findByCode(2),
            company: Business.get(1)
        ).save()
        
        def opUser = new User(
            name: 'Andreano Choppolo',
            login: 'andrea',
            passw: '1234',
            role: UserRole.findByCode(2),
            company: Business.get(2)
        ).save(flush: true)        
        
        println ''
        println 'SME Operator Users created'
        
        def inst = Business.get(2)
        inst.addToUsers(opUser)
        println "${inst?.name}: ${inst?.users}"
           
    }
    
    def destroy = {
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
}
