/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.asap.web;

import com.asap.configuration.Configuration;

/**
 *
 * @author Morten
 */
public class ConfigHandler {

    private String key;

    public String getProperty(){
        return Configuration.getInstance().getProps().getProperty(getKey());
    }

    /**
     * @return the key
     */
    public String getKey() {
        return key;
    }

    /**
     * @param key the key to set
     */
    public void setKey(String key) {
        this.key = key;
    }

}
