package com.sme.services

import grails.transaction.Transactional
import com.sme.entities.*

@Transactional(readOnly = true)
class ExportEntitiesService {
    def path = 'C:/export/'
    
    def exportBusinesses() {
        
        def fileName = 'businesses.txt'
        def counter = 0
        def line
        
        def businesses = Business.createCriteria().list() {
            order('name')
        }
        
        //  Populate with ID to be used in export: must be used for exporting
        //  all other entities associated with Business
        
        businesses.each {business ->
            business.internalID = business.id
            business.save(flush: true)
        }
        
        new File("${path}${fileName}").withWriter() {out ->
            businesses.each {business ->
                line = "${business.internalID}#"                                //  00
                line += "${business.profile?.code}#"                            //  01
                line += "${business.industry?.code}#"                           //  02
                line += "${business.name}#"                                     //  03
                line += "${business.accountNo}#"                                //  04
                line += "${business.regNumber}#"                                //  05
                line += "${business?.incorpDate?.format('dd/MM/yyyy')}#"        //  06
                line += "${business?.registrationDate?.format('dd/MM/yyyy')}#"  //  07
                line += "${business.address}#"                                  //  08
                line += "${business.city}#"                                     //  09
                
                if(business.bank) {
                    line += "${business.bank?.code}#"                           //  10
                }
                else {
                    line += "0#"
                }
                
                //  Contact Information (added on 08/05/2016)
                line += "${business?.contactPerson1 == null ? '*** NOT AVAILABLE' : business?.contactPerson1}#"  //  11
                line += "${business?.contactNumber1 == null ? '*** NOT AVAILABLE' : business?.contactNumber1}#"  //  12
                line += "${business?.contactPerson2}#"                          //  13
                line += "${business?.contactNumber2}#"                          //  14
                
                //  Billing related Fields
                line += "${business?.startBillingDate?.format('dd/MM/yyyy')}#"   //  15
                line += "${business?.nextBillingDate?.format('dd/MM/yyyy')}#"    //  16
                line += "${business?.billingType?.code}#"                       //  17
                line += "${business?.rate}#"                                    //  18
                line += "${business?.gracePeriod}#"                             //  19
                line += "${business?.freeServices}"                             //  20
                
                line = line.replaceAll("\n", '')
                line = line.replaceAll("\r", '')
                out.println line
                counter++
            }
        }
        
        return counter
    }
    
    def exportUsers() {
        def fileName = 'users.txt'
        def counter = 0
        def line
        
        def userList = User.list()
        
        new File("${path}${fileName}").withWriter() {out ->
            userList.each {record ->
                if(record.company) {
                    line = "${record.company.internalID}#"
                }
                else {
                    line = "0#"
                }
                
                line += "${record.role.code}#"
                line += "${record.name}#"
                line += "${record.login}#"
                line += "${record.passw}#"
                line += "${record?.contactNo}#"
                line += "${record?.email}#"
                
                if(record.bank) {
                    line += "${record.bank?.code}"
                }
                else {
                    line += "0"
                }
                
                line = line.replaceAll('\n', '')
                out.println line
                counter++                
            }
        }
        
        return counter
    }
    
    def exportTransactions() {
        def fileName = 'transactions.txt'
        def counter = 0
        def line 
        
        def transactions = BusinessTransaction.createCriteria().list() {
            operationType {
                or {
                    eq('actual', 1)
                    eq('code', 1020)
                    eq('code', 1030)
                    eq('code', 2000)
                    eq('code', 2005)
                }
            }
            
            order('transactionDate')
        }
        
        new File("${path}${fileName}").withWriter() {out ->
            transactions.each {record ->
                line  = "${record.company.internalID}#"
                line += "${record.operationType.code}#"
                line += "${record.transactionDate.format('dd/MM/yyyy')}#"
                line += "${record.transactionAmount}#"
                line += "${record.transactionRemarks}#"
                line += "${record?.operator}#"
                line += "${record.cash}"
                
                line = line.replaceAll("\"", '')
                line = line.replaceAll("\'", '')
                line = line.replaceAll('\n', '')
                
                out.println line
                counter++                
            }
        }
        
        return counter
    }
    
    def exportProfiles() {
        def fileName = 'profiles.txt'
        def counter = 0
        def line   
        def header
        
        def profiles = GenericProfile.createCriteria().list() {
            order('code')
        }
        
        new File("${path}${fileName}").withWriter() {out ->
            profiles.each {profile ->
                line = "${profile.code}#"
                line += "${profile.name}#"
                
                header = line
                
                def links = profile.operations
                
                links.each {record ->
                    line = header
                    line += "${record.operation.code}"
                    
                    out.println line
                }
                
                counter++
            }
        }
        
        return counter
    }
    
    def exportOperations() {
        def fileName = 'operations.txt'
        def counter = 0
        def line   
        
        def operations = GenericOperation.createCriteria().list() {
            order('code')
        }
        
        //  Updated on 15/12/2015 to accomodated PNLGroup and PNLSubGroup
        //  references
        new File("${path}${fileName}").withWriter() {out ->
            operations.each {operation ->
                line  = "${operation.code}#"
                line += "${operation.accountType?.code}#"
                line += "${operation.group?.code}#"
                line += "${operation.name}#"
                line += "${operation.name_EN}#"
                line += "${operation.inbound}#"
                line += "${operation.outbound}#"
                line += "${operation.actual}#"
                line += "${operation.mirrorCash?.code}#"
                line += "${operation.mirrorBank?.code}#"
                line += "${operation.pnlGroup?.code}#"
                line += "${operation.pnlSubGroup?.code}"
               
                out.println line
                counter++
            }
        }
        
        return counter
    }
    
    def getBackupSize() {
        Long size = 0
        File file
        
        def files = [
            'profiles.txt',
            "businesses.txt",
            "users.txt",
            "operations.txt",
            "transactions.txt",
            "agencies.txt",
            "bills.txt"
        ]
        
        files.each {fileName ->
            file = new File("${path}${fileName}")
            size += file.length()
        }
        
        return size
    }
    
    def exportAgencies() {
        def fileName = 'agencies.txt'
        def counter = 0
        def line 
        
        def agencies = LendingAgency.list()
        
        new File("${path}${fileName}").withWriter() {out ->
            agencies.each {agency ->
                ++counter 
                
                line  = "${agency.code}#"
                line += "${agency.name}#"
                line += "${agency.shortName}#"
                line += "${agency.address}#"
                line += "${agency.city}#"
                line += "${agency.contactPhone}#"
                line += "${agency.contactEmail}"
                
                out.println line
            }
        }
        
        return counter
    }
    
    /**
     *  Exporting Bills, if any, into file 'bills.txt'
     */
    def exportBills() {
        def fileName = 'bills.txt'
        def counter = 0
        def line  
        
        def bills = Bill.list() 
            
        new File("${path}${fileName}").withWriter() {out ->
            bills.each {record ->
                line  = "${record.company.internalID}#"                     //  00
                line += "${record.dateCreated?.format('dd/MM/yyyy')}#"      //  01
                line += "${record.dueDate?.format('dd/MM/yyyy')}#"          //  02
                line += "${record.periodFrom?.format('dd/MM/yyyy')}#"       //  03
                line += "${record.periodTill?.format('dd/MM/yyyy')}#"       //  04
                line += "${record.gracePeriodTill?.format('dd/MM/yyyy')}#"  //  05
                line += "${record.paymentDate?.format('dd/MM/yyyy')}#"      //  06
                line += "${record.confirmationDate?.format('dd/MM/yyyy')}#" //  07
                line += "${record.updateDate?.format('dd/MM/yyyy')}#"       //  08
                
                line += "${record.invoiceNumber}#"                          //  09  
                line += "${record.billingType?.code}#"                      //  10
                line += "${record.amount}#"                                 //  11
                line += "${record.amountPaid}#"                             //  12
                line += "${record.paymentMode?.code}#"                      //  13
                line += "${record.paymentReference}#"                       //  14
                line += "${record.remarks}#"                                //  14
                line += "${record.confirmationRemarks}#"                    //  16
                line += "${record.paid}#"                                   //  17
                line += "${record.outstanding}#"                            //  18
                line += "${record.writtenOff}#"                             //  19
                line += "${record.confirmed}#"                              //  20
                line += "${record.createdBy}#"                              //  21
                line += "${record.confirmedBy}#"                            //  22
                line += "${record.updatedBy}"                               //  23
                
                line = line.replaceAll("\"", '')
                line = line.replaceAll("\'", '')
                line = line.replaceAll('\n', '')
                
                out.println line
                counter++                
            }
        }
        
        return counter
    }
}
