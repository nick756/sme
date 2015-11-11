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
                line = "${business.internalID}#"
                line += "${business.profile?.code}#"
                line += "${business.industry?.code}#"
                line += "${business.name}#"
                line += "${business.accountNo}#${business.regNumber}#"
                line += "${business?.incorpDate?.format('dd/MM/yyyy')}#"
                line += "${business?.registrationDate?.format('dd/MM/yyyy')}#"
                line += "${business.address}#${business.city}"
                
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
                line += "${record?.email}"
                
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
                eq('actual', 1)
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
                line += "${operation.mirrorBank?.code}"
               
                out.println line
                counter++
            }
        }
        
        return counter
    }
}
