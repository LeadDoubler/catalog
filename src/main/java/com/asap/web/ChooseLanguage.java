/*
 * ChooseLanguage.java
 *
 * Created on 23. april 2007, 18:59
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package com.asap.web;

import com.asap.web.*;
//import com.asap.catalog.dao.Page;
import java.util.Locale;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import net.sourceforge.stripes.action.RedirectResolution;
import net.sourceforge.stripes.action.Resolution;
import org.mortena.stripes.conf.StripesLocalePicker;

/**
 *
 * @author mortenandersen
 */
public class ChooseLanguage extends CatalogActionBean{
    
    /** Creates a new instance of ChooseLanguage */
    public ChooseLanguage() {
    }
    
    public Resolution chooseLanguage(){
        this.getContext().getRequest().getSession().setAttribute(StripesLocalePicker.LOCALE,this.getContext().getRequest().getLocale());
        return new RedirectResolution("/");
    }
    
    public Resolution changeLanguage(){
        changeLanguage(language,country,this.getContext().getRequest());
        return new RedirectResolution("/");
    }
    
    public void changeLanguage(String country,String language, HttpServletRequest request){
        Locale locale = new Locale(language,country);
        HttpSession session = request.getSession(true);
        session.setAttribute(StripesLocalePicker.LOCALE,locale);
    }
    
    private String country;
    
    private String language;

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }
    
    
    
}
