/*
 * Component.java
 *
 * Created on 14. maj 2007, 21:34
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package com.asap.catalog.dao;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.Transient;

/**
 *
 * @author mortenandersen
 */
@MappedSuperclass
public abstract class Component implements java.io.Serializable{
    
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    protected Long id;
    

   
    public Long getId() {
        return id;
    }
    
    public String getTitle(){
        return this.getClass().getName();
    }
     
    public String toString(){
        if (getId() == null){
            return "";
        }
        else{
            return getId().toString();
        }
    }

  

}
