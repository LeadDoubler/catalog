/*
 * CompanyActionBean.java
 *
 * Created on 1. maj 2007, 08:10
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package com.blob.pas;

import com.asap.web.CatalogActionBean;
import java.util.ArrayList;
import java.util.List;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.DontValidate;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.Resolution;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.criterion.Expression;
import org.hibernate.criterion.Order;

/**
 * 
 * @author mortenandersen
 */ 
public abstract class DefActionBean extends CatalogActionBean{
    final String PREPATH = "/WEB-INF/view";
    public abstract String getType();
    public abstract Object getComponent();
    public abstract List<Object> getComponents();
    public abstract String getEditorFolder();
    
    public String getViewFolder(){
        return getEditorFolder();
    } 
    
    public String getListFolder(){
        return getForwardFolder();
    }
            
    public String getForwardFolder(){
        return getType();
    } 
    
    public String getCapType(){
        return getType().substring(0,1).toUpperCase()+getType().substring(1);
    }
    
    public Resolution view(){
        return new ForwardResolution(PREPATH+"/"+getForwardFolder()+"/"+this.getContext().getEventName()+".jsp");
    }
    
    @DefaultHandler
    @DontValidate
    public Resolution list(){        
        return new ForwardResolution(PREPATH+"/"+getListFolder()+"/"+this.getContext().getEventName()+".jsp");
    }
    
    public Resolution edit(){
        return new ForwardResolution(PREPATH+"/"+getForwardFolder()+"/"+this.getContext().getEventName()+".jsp");
    }
    
    public Resolution inline(){
        return new ForwardResolution(PREPATH+"/"+getEditorFolder()+"/"+this.getContext().getEventName()+".jsp");
    }
    
    public Resolution delete(){
        getSession().delete(getComponent());
        persist(getComponent());
        return new ForwardResolution(PREPATH+"/"+getForwardFolder()+"/"+this.getContext().getEventName()+".jsp");
    }
    
    public Resolution inlineSave(){
        persist(getComponent());
        return new ForwardResolution(PREPATH+"/"+getViewFolder()+"/view.jsp");
    }

    public Resolution save(){
        persist(getComponent());
        return new ForwardResolution(PREPATH+"/"+getForwardFolder()+"/view.jsp");
    }    
}
