/*
 * ManagerConfiguration.java
 *
 * Created on 3. april 2007, 13:49
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package com.asap.catalog.dao.manager;

import java.util.List;
import java.util.Set;

/**
 *
 * @author mortenandersen
 */
public class ManagerConfiguration {
    private Set<Class<? extends Manager>> managers;
    private static ManagerConfiguration managerConfiguration;
    
    /** Creates a new instance of ManagerConfiguration */
    private ManagerConfiguration() {
    }
    
    public static ManagerConfiguration getInstance(){
        if (managerConfiguration == null){
            managerConfiguration = new ManagerConfiguration();
        }
        return managerConfiguration;
    }

    public Set<Class<? extends Manager>> getManagers() {
        return managers;
    }

    public void setManagers(Set<Class<? extends Manager>> managers) {
        this.managers = managers;
    }
    
    
    
    
    
    
    
}
