package com.asap.web.contact;
import com.asap.catalog.dao.Contact;
import com.asap.configuration.ConfigurationManager;
import com.asap.security.Role;
import com.asap.security.Secure;
import com.asap.web.*;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.GregorianCalendar;
import java.util.List;

import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.DontValidate;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.HandlesEvent;
import net.sourceforge.stripes.action.RedirectResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.validation.EmailTypeConverter;
import net.sourceforge.stripes.validation.Validate;
import net.sourceforge.stripes.validation.ValidateNestedProperties;
import org.hibernate.criterion.Order;
import org.hibernate.property.Getter;
import util.Sms;


/*
 * Calculator.java
 *
 * Created on 3. april 2007, 11:31
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

/**
 *
 * @author Jens Rosenberg
 */
public class ContactActionBean extends CatalogActionBean{
    @ValidateNestedProperties({            
        @Validate(field = "name", required = true),
        @Validate(field = "phone", required = true),
        @Validate(field = "message", required = false),
        @Validate(field = "email", required = false, converter = EmailTypeConverter.class)
    })
    private Contact contact;
   
    public Resolution send() {
        try{
        /* Sikrer at ingen bliver sendt dobbelt */
        Contact lastcontact = (Contact) getSession().createCriteria(Contact.class).addOrder(Order.desc("id")).setMaxResults(1).uniqueResult();
        if(lastcontact != null && lastcontact.getEmail() != null ){
            if( lastcontact.getEmail().equals(contact.getEmail()) && 
                lastcontact.getPhone().equals(contact.getPhone()) && 
                lastcontact.getName().equals(contact.getName()) && 
                lastcontact.getMessage().equals(contact.getMessage())){

                return new ForwardResolution("/contact/result.jsp");
            }
        }
        
        //String peter = "20802940";
        String smsReceiver = ConfigurationManager.getParameter("sms-receiver");
        
        String sender_str = ConfigurationManager.getParameter("sms-subject");
        String email = getContact().getEmail(); if(email == null || email == "") email = "(ikke angivet)";
        String message = getContact().getMessage(); if(message == null || message == "") message = "(tom besked)";
        
        Calendar cal = new GregorianCalendar();
        cal.setTimeInMillis(System.currentTimeMillis());
        getContact().setTime(cal.getTime());
        
        String message_to_reciever = "Kontakt: "+getContact().getName()+" Tlf: "+getContact().getPhone()+" Email: "+email+" Besked: '"+message+"'";
        //String message_to_sender = "Tak for din henvendelse. Vi vil snarest muligt kontakte dig med et konkret tilbud. Mvh A.Z.tagteknik";
        
        Sms sms = new Sms(smsReceiver,sender_str,message_to_reciever);
        
        sms.send();
        getContact().setResult(sms.getStatus());
        
        persist(contact);
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return new ForwardResolution("/contact/result.jsp");
    }
    
    @DontValidate
    public List<Contact> getAllContacts(){
        return getSession().createCriteria(Contact.class).addOrder(Order.desc("time")).list();
    }
       
    @DefaultHandler
    @DontValidate
    public Resolution edit() {
        return new ForwardResolution("/contact/edit.jsp");
    }
    
    @Secure(role = Role.MODERATOR)
    @DontValidate
    public Resolution list() {
        return new ForwardResolution("/contact/list.jsp");
    }
    
    public Contact getContact(){
        return contact;
    }
    public void setContact(Contact contact){
        this.contact = contact;
    }
}
