/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.asap.web.mail;

import com.asap.web.CatalogActionBean;
import java.util.List;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.Resolution;
import org.mortena.mail.Email;
import com.asap.catalog.dao.User;


/**
 *
 * @author mortenmatras
 */
public class EmailActionBean extends CatalogActionBean{
    
    
    private Email email;
    private List<String> receivers;
    private List<User> users;
    private String url;
    
    public Resolution init(){
        return new ForwardResolution("/mail/edit.jsp");
    }
    
    public Resolution send(){
        
        if (getReceivers() != null){
            for (String receiver : getReceivers()){
                getEmail().setTo(receiver);
                getEmail().send();
            }
        }
        if (getUsers() != null){
            for (User user : getUsers()){
                getEmail().setTo(user.getEmail());
                getEmail().send();
            }
        }
        return new ForwardResolution("/mail/mailSent.jsp");
    }

    public Email getEmail() {
        return email;
    }

    public void setEmail(Email email) {
        this.email = email;
    }

    public List<String> getReceivers() {
        return receivers;
    }

    public void setReceivers(List<String> receivers) {
        this.receivers = receivers;
    }
    
        public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

}
