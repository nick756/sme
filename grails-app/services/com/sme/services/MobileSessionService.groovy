package com.sme.services

import java.util.concurrent.ConcurrentHashMap
import grails.transaction.Transactional
import com.sme.entities.*
import com.sme.util.MobileUser
import groovy.time.*

/**
 *  Managing mobile Sessions, standard HTTP sessions do not work here
 */ 
@Transactional
class MobileSessionService {
    
    ConcurrentHashMap loggedUsers = [:]
       
    /**
    Validating time out only
     */
    def validateTimeout(Long key) {
        TimeDuration duration 
        TimeDuration timeout = new TimeDuration(0, 10, 0, 0)  //  Fixed for the time being
        
        if(loggedUsers.get(key)) {
            duration = TimeCategory.minus(new Date(), loggedUsers[key]?.lastOperation)
        
            if(duration > timeout) {
                remove(key)
                return false
            }
            else {
                updateOperationTime(key)
            }
        }
        else return false
    }
    
    def updateOperationTime(Long key) {
        if(loggedUsers.get(key)) {
            loggedUsers[key].lastOperation = new Date()
            return true
        }
        else {
            return false
        }
    }
   
    /**
     *  User authentication and validation done in controller
     */
    def addUser(User user) {
        
        if(!loggedUsers.containsKey(user?.id)) {
            loggedUsers.put(user?.id, new MobileUser(
                    loggedTime: new Date(),
                    lastOperation: new Date(),
                    user: user
                )
            )
        }
        else {
            loggedUsers[user?.id].lastOperation = new Date()
        }
        
        return user?.id
    }
    
    def remove(Long key) {
        loggedUsers.remove(key)
    }
    
    def getUser(Long key) {
        return loggedUsers[key]?.user
    }
    
    def getLastOperationTime(Long key) {
        if(key) {
            return loggedUsers[key].lastOperation
        }
    }
}
