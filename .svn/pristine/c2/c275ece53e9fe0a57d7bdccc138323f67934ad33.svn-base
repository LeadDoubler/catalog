/*
 * Configuration.java
 *
 * Created on 15. august 2007, 14:47
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package com.asap.configuration;

import java.util.Locale;
import java.util.Properties;

/**
 *
 * @author mortenandersen
 */
public class Configuration {
    
    private Properties props = new Properties();
    
     public Properties getProps() {
        return props;
    }

    public void setProps(Properties props) {
        this.props = props;
    }
    
    public static Configuration configuration;
    
    /** Creates a new instance of Configuration */
    private Configuration() {
        
    }
    
    public static Configuration getInstance(){
        if (configuration == null){
            configuration = new Configuration();
        }
        return configuration;
    }

    public static String getLocalizedProperty(String key, Locale locale){
        String result = getInstance().getProps().getProperty(key+"_"+locale.toString());
        if (result == null){
            result = getInstance().getProps().getProperty(key+"_"+locale.getLanguage());
        }
        if (result == null){
            result = getInstance().getProps().getProperty(key);
        }
        return result;
    }
    
}
