package com.sme.entities

class AccountType {
    
    Integer code
    String name

    static constraints = {
    }
    
    public String toString() {
        String.format('%1$2s', code).replace(' ', '0') + " " + name
    }
}
