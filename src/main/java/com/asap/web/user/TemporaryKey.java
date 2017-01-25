/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.asap.web.user;

import com.asap.catalog.dao.Component;
import com.asap.catalog.dao.User;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;

/**
 *
 * @author mortenmatras
 */
@Entity
public class TemporaryKey extends Component{
    @ManyToOne()
    private User user;
    private String storedKey;
    @Temporal(value = javax.persistence.TemporalType.TIMESTAMP)
    private Date generatedTime;

    /**
     * @return the user
     */
    public User getUser() {
        return user;
    }

    /**
     * @param user the user to set
     */
    public void setUser(User user) {
        this.user = user;
    }

    /**
     * @return the key
     */
    public String getStoredKey() {
        return storedKey;
    }

    /**
     * @param key the key to set
     */
    public void setStoredKey(String key) {
        this.storedKey = key;
    }

    /**
     * @return the generatedTime
     */
    public Date getGeneratedTime() {
        return generatedTime;
    }

    /**
     * @param generatedTime the generatedTime to set
     */
    public void setGeneratedTime(Date generatedTime) {
        this.generatedTime = generatedTime;
    }

}
