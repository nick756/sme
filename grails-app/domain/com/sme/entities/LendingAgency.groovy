package com.sme.entities

class LendingAgency implements Serializable {
    
    Integer code
    String name
    String shortName
    //String shartName
    String address
    String city
    String contact
    String contactPhone
    String contactEmail
    
    static hasMany = [
        customers:  Business,
        users:      User
    ]
    
    static constraints = {
        code            unique: true
        shortName       maxSize: 6
        address         nullable: true
        city            nullable: true
        customers       nullable: true
        users           nullable: true
        contact         nullable: true
        contactPhone    nullable: true
        contactEmail    email: true
        //shartName       nullable: true
    }
    
    static mapping = {
        sort 'name'
    }
    
    public String toString() {
        "${String.format('%1$4s', code)} ${name}"
    }
}
