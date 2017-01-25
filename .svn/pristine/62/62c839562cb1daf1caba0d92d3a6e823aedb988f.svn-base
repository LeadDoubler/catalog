/*
 * SubscriptionActionBean.java
 *
 * Created on 3. oktober 2007, 19:05
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package com.asap.web.subscription;

import com.asap.security.Role;
import com.asap.security.Secure;
import com.blob.pas.DefaultActionBean;
import java.util.List;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.validation.EmailTypeConverter;
import net.sourceforge.stripes.validation.Validate;
import net.sourceforge.stripes.validation.ValidateNestedProperties;

/**
 *
 * @author mortenandersen
 */
public class SubscriptionActionBean extends DefaultActionBean{
    
    @ValidateNestedProperties({ 
        @Validate(field = "email", required = true, converter = EmailTypeConverter.class)
    })
    private Subscription subscription;
    
    /** Creates a new instance of SubscriptionActionBean */
    public SubscriptionActionBean() {
    }

    public String getType() {
        return "subscription";
    }

    public Object getComponent() {
       return subscription; 
    }

    public List<Object> getComponents() {
        return getSession().createCriteria(Subscription.class).list();
    }

    public String getEditorFolder() {
        return "subscription";
    }

    public Subscription getSubscription() {
        return subscription;
    }

    public void setSubscription(Subscription subscription) {
        this.subscription = subscription;
    }
    
    @Secure (role=Role.MODERATOR)
    @DefaultHandler
    public Resolution list(){
        return super.list();
    }
    
    @Secure (role=Role.MODERATOR)
    public Resolution inlineList(){
        return super.forward();
    }
    
    
}
