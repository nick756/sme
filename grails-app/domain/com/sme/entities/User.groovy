package com.sme.entities

/*
    The only Entity for managing access to the System, conditional navigation
    by User.role
*/
class User implements Serializable {

    String name
    String login
    String passw
    String contactNo
    String email
    
    UserRole role
    Business company    //  Must be presented if role.code == 2
    
    static constraints = {
        name        nullable: false
        login       nullable: false, unique: true
        passw       nullable: false, blank: false, password: true, size: 4..10
        contactNo   nullable: true
        email       email: true, nullable: true
        role        nullable: false, blank: false
        company     nullable: true, validator: {value, obj ->
                        return !(value == null && obj.role?.code == 2)
                    }
    }
    
    public String toString() {
        login + ": " + name
    }
}
