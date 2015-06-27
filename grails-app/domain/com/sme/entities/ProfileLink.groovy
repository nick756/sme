package com.sme.entities

class ProfileLink {
    
    Date dateCreated
    boolean active = true
    
    static belongsTo = [
        operation: GenericOperation,
        profile: GenericProfile
    ]
    
    static constraints = {
        dateCreated     nullable: true
        operation       nullable: true
        profile         nullable: true
    }
}
