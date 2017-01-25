/*
 * ConfigurationActionBean.java
 *
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package com.asap.web.configuration;

import com.asap.catalog.dao.Configuration;
import com.asap.configuration.ConfigurationManager;
import com.asap.security.Role;
import com.asap.security.Secure;
import com.asap.web.CatalogActionBean;
import java.util.ArrayList;
import java.util.List;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.Resolution;

/**
 *
 * @author asapunov
 */
@Secure(role=Role.ADMINISTRATOR)
public class ConfigurationActionBean extends CatalogActionBean {  
    
    private String name;
    
    private String value;
    
    private Long idConf;
    
    private String nameConf;
    
    private String valueConf;
    
    private List<String> errors = new ArrayList<String>();
    
    /** Creates a new instance of ConfigurationActionBean */
    public ConfigurationActionBean() {
    }
    
    public Resolution save(){
        Configuration configuration = ConfigurationManager.getConfigurationByName(nameConf);
        if (configuration == null){
            Configuration configuration2 = ConfigurationManager.getConfigurationById(idConf);
            if (configuration2 != null) {
                configuration2.setName(nameConf);
                configuration2.setValue(valueConf);
                ConfigurationManager.setConfiguration(configuration2);
            } else {
                ConfigurationManager.setParameter(nameConf, valueConf);
            }
        } else {
            if (configuration.getId().equals(idConf)) {
                configuration.setName(nameConf);
                configuration.setValue(valueConf);
                ConfigurationManager.setConfiguration(configuration);
            } else {
                getErrors().add("Can't add or save a parameter. This name exists");
            }
        }
        persist();
        return new ForwardResolution("/configuration/configurationManager.jsp");
    }
    
    public Resolution add(){
        Configuration configuration = ConfigurationManager.getConfigurationByName(name);
        if (configuration == null) {
            ConfigurationManager.setParameter(name, this.value);
        } else {
            getErrors().add("Can't add or save a parameter. This name exists");
        }
        persist();
        return new ForwardResolution("/configuration/configurationManager.jsp");
    }
    
    @DefaultHandler
    public Resolution manager(){
        return new ForwardResolution("/configuration/configurationManager.jsp");
    }
    public Resolution delete(){
        ConfigurationManager.removeParameter(idConf);
        return new ForwardResolution("/configuration/configurationManager.jsp");
    }
    
    public List<Configuration> getConfigurations() {
        List<Configuration> configurationsList = ConfigurationManager.getParametersList();
        return configurationsList;
    }
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public String getValue() {
        return value;
    }
    
    public void setValue(String value) {
        this.value = value;
    }
    
    public List<String> getErrors() {
        return errors;
    }
    
    public void setErrors(List<String> errors) {
        this.errors = errors;
    }
    
    public String getNameConf() {
        return nameConf;
    }
    
    public void setNameConf(String nameConf) {
        this.nameConf = nameConf;
    }
    
    public String getValueConf() {
        return valueConf;
    }
    
    public void setValueConf(String valueConf) {
        this.valueConf = valueConf;
    }
    
    public Long getIdConf() {
        return idConf;
    }
    
    public void setIdConf(Long idConf) {
        this.idConf = idConf;
    }
    
    
}
