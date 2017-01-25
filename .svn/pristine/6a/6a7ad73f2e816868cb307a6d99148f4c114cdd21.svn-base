/*
 * Recipient.java
 *
 * Created on 3. oktober 2007, 19:04
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package com.asap.web.subscription;

import com.asap.catalog.dao.Component;
import com.asap.catalog.dao.User;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 *
 * @author mortenandersen
 */
@Entity
@Table (name = "recipient")
public class Subscription extends Component{
    
  
    /** Creates a new instance of Recipient */
    public Subscription() {
    }

   
    
    private String email;

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    
    @OneToOne
    private User user;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
    
}
