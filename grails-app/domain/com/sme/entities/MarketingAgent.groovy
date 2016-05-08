package com.sme.entities

class MarketingAgent {
    
    String name
    String contactPhone
    String email
    String address
    String city
    State state
    String remarks

    static constraints = {
        name            nullable: false
        contactPhone    nullable: true
        email           nullable: true, email: true
        address         nullable: true
        city            nullable: true
        state           nullable: true
        remarks         nullable: true; maxSize: 1024
    }
    
    static mapping = {
        sort 'name'
    }
    
    public String toString() {
        name
    }
}
