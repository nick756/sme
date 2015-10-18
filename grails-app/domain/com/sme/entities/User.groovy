package com.sme.entities

/*
The only Entity for managing access to the System, conditional navigation
by User.role
 */
class User implements Serializable {

    static transients = [
        'passwOld',
        'passwNew',
        'passwRep'
    ]
    
    String name
    String login
    String passw
    String contactNo
    String email
    
    //  For managing passwords by Users themselves
    
    String passwOld
    String passwNew
    String passwRep
    
    UserRole role
    Business company    //  Must be presented if role.code == 2
    
    static constraints = {
        name        nullable: false
        login       nullable: false, unique: true
        passw       nullable: false, blank: false, password: true, size: 4..10
        contactNo   nullable: true
        email       email: true, nullable: true
        role        nullable: false, blank: false
        passwOld    nullable: true, blank: true
        passwNew    nullable: true, blank: true
        passwRep    nullable: true, blank: true
        company     nullable: true, validator: {value, obj ->
                    return !(value == null && obj.role?.code == 2)
        }
    }
    
    static mapping = {
        sort 'name'
    }    
    
    public String toString() {
        login + ": " + name
    }
}
