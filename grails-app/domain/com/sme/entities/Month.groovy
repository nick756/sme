package com.sme.entities

class Month {
    Integer number
    String name
    
    static constraints = {
    }
    
    public String toString() {
        "${String.format('%1$2s', number).replace(' ', '0')} ${name}"
    }
}
