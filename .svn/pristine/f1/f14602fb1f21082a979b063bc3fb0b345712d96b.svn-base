/*
 * Configuration.java
 *
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package com.asap.catalog.dao;

import com.asap.configuration.SoftwareConfiguration;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import org.hibernate.annotations.Type;


/**
 *
 * @author asapunov
 */
@Entity()
@Table(name = "configuration")
public class Configuration extends Component{
    
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    @Column(unique = true)    
    private String name;
    
    @Type(type="text")
    private String value;
    /** Creates a new instance of Configuration */
    public Configuration(String name, String value) {
        this.name=name;
        this.value = value;
    }
    
    /** Creates a new instance of Configuration by name
     *  Using only for creating an Example for hibernate request
     */
    public Configuration(String name) {
        this.name=name;
    }
    
    /** Creates a new instance of Configuration by name
     *  Default constructor for hibernate needs
     */
    public Configuration() {
        
    }
    
    /** Creates a new instance of Configuration be ID
     *  Using only for creating an Example for hibernate request
     */
    public Configuration(Long id) {
        this.id=id;
    }
    
    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    public String getName() {
        return name;
    }
    
    public String getWithoutNamespaceName() {
        String prefix = SoftwareConfiguration.getDatabaseConfigurationParameterPrefix();
        if (name.startsWith(prefix)) {
            int  index = name.indexOf(prefix);
            if (index != -1) {
                return name.substring(index + prefix.length());
            }
        }
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
    
}
