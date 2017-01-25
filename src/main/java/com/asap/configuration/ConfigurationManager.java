/*
 * ConfigurationManager.java
 *
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package com.asap.configuration;

import com.asap.catalog.dao.Configuration;
import util.HibernateUtil;
import java.util.List;
import org.hibernate.criterion.Example;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author asapunov
 */
public class ConfigurationManager {
    
    /** Creates a new instance of ConfigurationManager */
    public ConfigurationManager() {
        
    }
    
    /**
     * Method for settgin a parameter.
     * Used from a code.
     * This method helps to set a parameter using a customer and site namespace
     */
    public static void setParameter(String name, String value) {
        name = SoftwareConfiguration.getDatabaseConfigurationParameterPrefix() + name;
        if (name!= null && value!= null) {
            Configuration configuration = getConfiguration(name);
            if (configuration == null ){
                System.out.println("Configuration == null - creating new");
                configuration = new Configuration(name, value);
            }
            else{
                System.out.println("configuration found - id="+configuration.getId());
                configuration.setValue(value);
            }
            util.HibernateUtil.getSessionFactory().getCurrentSession().saveOrUpdate(configuration);
        }
    }
    
    /**
     * This method sets a Long parameter
     * Used from a code.
     */
    public static void setLongParameter(String name, Long value) {
        if (name != null && value != null)
            setParameter(name, value.toString());
    }
    
    public static Configuration getConfiguration(String name){
        Configuration configurationExample = new Configuration(name);
        configurationExample.setName(name);
        Configuration configuration = (Configuration) util.HibernateUtil.getSessionFactory().getCurrentSession().createCriteria(Configuration.class).add(Example.create(configurationExample)).uniqueResult();
        return configuration;
    }
    
    /**
     * Method for getting a parameter.
     * Used from code.
     * This method helps to get a parameter using a customer and site namesapce
     */
    public static String getParameter(String name) {
        name = SoftwareConfiguration.getDatabaseConfigurationParameterPrefix() + name;
        Configuration configurationExample = new Configuration(name);
        configurationExample.setName(name);
        try{
            /*Session session = HibernateUtil.getSessionFactory().getCurrentSession();
            if ( ! session.isConnected() ){
                session.beginTransaction();
            }*/
            Configuration configuration = (Configuration) util.HibernateUtil.getSessionFactory().openSession().createCriteria(Configuration.class).add(Example.create(configurationExample)).uniqueResult();
            if (configuration!= null) {
                return configuration.getValue();
            }
            else{
                return com.asap.configuration.Configuration.getInstance().getProps().getProperty(name);
            }
        }catch(Exception e){
            e.printStackTrace();
        }
       
        return null;
    }
    
    /**
     * Method for getting a parameter with selecting a default value which will be returned if parameter not setted.
     * Used from code.
     * This method helps to get a parameter using a customer and site namesapce
     */
    public static String getParameter(String name, String defaultValue) {
        name = SoftwareConfiguration.getDatabaseConfigurationParameterPrefix() + name;
        Configuration configurationExample = new Configuration(name);
        configurationExample.setName(name);
        Configuration configuration = (Configuration) util.HibernateUtil.getSessionFactory().getCurrentSession().createCriteria(Configuration.class).add(Example.create(configurationExample)).uniqueResult();
        if (configuration!= null)
            return configuration.getValue();
        return defaultValue;
    }
    
    /**
     * Method for getting a Long parameter.
     * Used from code.
     * This method helps to get a parameter using a customer and site namesapce
     */
    public static Long getLongParameter(String name) {
        String parameter = getParameter(name);
        if (parameter != null) {
            return new Long(parameter);
        }
        return null;
    }
    
    /**
     * Method for getting a Long parameter with selecting a default value which will be returned if parameter not setted.
     * Used from code.
     * This method helps to get a parameter using a customer and site namesapce
     */
    public static Long getLongParameter(String name, Long defaultValue) {
        String parameter = getParameter(name);
        if (parameter != null) {
            return new Long(parameter);
        }
        return defaultValue;
    }
    
    
    
    /**
     * Method for getting a Configuration object by Name.
     * Don't use for getting a parameter
     * For getting a parameter use a getParameter(String string) instead
     */
    public static Configuration getConfigurationByName(String name) {
        name = SoftwareConfiguration.getDatabaseConfigurationParameterPrefix() + name;
        Configuration configurationExample = new Configuration(name);
        configurationExample.setName(name);
        Configuration configuration = (Configuration) util.HibernateUtil.getSessionFactory().getCurrentSession().createCriteria(Configuration.class).add(Example.create(configurationExample)).uniqueResult();
        return configuration;
    }
    
    /**
     * Method for getting Cofiguration object by ID
     * Don't use it for getting a parameter
     * For getting a parameter use a getParameter(Long long) instead
     */
    public static Configuration getConfigurationById(Long id) {
        List<Configuration> configurations = util.HibernateUtil.getSessionFactory().getCurrentSession().createCriteria(Configuration.class).add(Restrictions.eq("id", id)).list();
        if (configurations!= null &&  configurations.size()>0) {
            return configurations.get(0);
        }
        return null;
    }
    
    /**
     * Method for setting a Configuration object
     * Don't use for setting a parameter from code
     */
    public static void setConfiguration(Configuration configuration) {
        configuration.setName(SoftwareConfiguration.getDatabaseConfigurationParameterPrefix() + configuration.getName());
        util.HibernateUtil.getSessionFactory().getCurrentSession().saveOrUpdate(configuration);
    }
    
    /**
     * This method  helps to remove a parameter by ID
     */
    public static void removeParameter(Long id) {
        Configuration configuration = getConfigurationById(id);
        util.HibernateUtil.getSessionFactory().getCurrentSession().delete(configuration);
    }
    
    
    /**
     * This method helps to get a List of parameters in used namespace.
     */
    public static List<Configuration> getParametersList() {
        List<Configuration> configuration = util.HibernateUtil.getSessionFactory().getCurrentSession().createCriteria(Configuration.class).add(Restrictions.like("name", SoftwareConfiguration.getDatabaseConfigurationParameterPrefix()+"%")).list();
        return configuration;
    }
    
    
    
}
