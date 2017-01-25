/*
 * SeminarActionBean.java
 *
 * Created on 14. februar 2007, 15:04
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package com.asap.catalog.extensions.seminar.web.seminar;

import com.asap.catalog.dao.Category;
import com.asap.catalog.extensions.seminar.Term;
import com.asap.security.Role;
import com.asap.security.Secure;
import java.util.List;

import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.RedirectResolution;
import net.sourceforge.stripes.action.Resolution;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import util.HibernateUtil;

import com.asap.catalog.extensions.seminar.Seminar;
import com.asap.catalog.extensions.seminar.Event;
import com.asap.web.CatalogActionBean;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.LinkedList;

/**
 *
 * @author mortenandersen
 */
public class SeminarActionBean extends CatalogActionBean {
    
    private Seminar seminar;
    
    public Seminar getSeminar() {
        return seminar;
    }
    
    public void setSeminar(Seminar seminar) {
        this.seminar = seminar;
    }
    
    @Secure(role = Role.MODERATOR)
    public Resolution save() {
        persist(seminar);
        persist(seminar.getEvent());
        return new RedirectResolution("/seminar/Seminar.action?view&seminar="+seminar);
    }
    
    @Secure(role = Role.MODERATOR)
    public Resolution edit() {
        if(seminar == null){
            seminar = new Seminar();
        }
        
        if(seminar.getEvent() == null){
            Event event = new Event();
            persist(event);
            seminar.setEvent(event);
        }
        return new ForwardResolution("/seminar/edit.jsp");
    }
    
    @DefaultHandler
    public Resolution list() {
        return new ForwardResolution("/seminar/list.jsp");
    }
    
    public Resolution view() {
        return new ForwardResolution("/seminar/view.jsp");
    }
}
