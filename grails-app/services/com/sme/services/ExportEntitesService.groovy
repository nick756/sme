package com.sme.services

import grails.transaction.Transactional
import com.sme.entities.*

@Transactional(readOnly = true)
class ExportEntitesService {

    def exportBusinesses() {
        def path = 'C:/export/'
        def fileName = 'businesses.txt'
        def counter = 0
        
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
            business.each {business ->
                def line = "${business.iternalID},${business.name},"
                line += "${business.accountNo},${business.regNumber},"
                line += "${new Date().parse("d/M/yyyy", business?.incorpDate)},"
                line += "${new Date().parse("d/M/yyyy", business?.registrationDate)},"
                line += "${business.address},${business.city}"
                
                out.writeln line
                counter++
            }
        }
        
        return counter
    }
}
