package com.sme.services

import java.util.concurrent.ConcurrentHashMap
import grails.transaction.Transactional
import com.sme.entities.*
import com.sme.util.MobileUser
import groovy.time.*

@Transactional
class MobileSessionService {
    
    ConcurrentHashMap loggedUsers = [:]
    TimeDuration timout = new TimeDuration(1, 0, 0, 0)
    
    /**
    Validating time out only
     */
    def validateTimeout(Long key) {
        TimeDuration duration 
        
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
    User authentication and validation done in controller
     */
    def addUser(User user) {
        MobileUser mUser
        
        if(!loggedUsers.containsKey(user?.id)) {
            mUser = new MobileUser(
                loggedTime: new Date(),
                lastOperation: new Date(),
                user: user
            )
            
            loggedUsers.put(user?.id, mUser)
        }
        
        return user?.id
    }
    
    def remove(Long key) {
        loggedUsers.remove(key)
    }
    
    def getUser(Long key, Object value) {
        
    }
}
