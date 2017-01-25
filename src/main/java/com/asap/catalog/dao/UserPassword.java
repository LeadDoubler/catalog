/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.asap.catalog.dao;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;

/**
 *
 * @author Jonas
 */
@Entity
public class UserPassword extends Component implements Serializable {

    private String password;
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Date changedAt;
    @ManyToOne
    private User user;

    /**
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password the password to set
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * @return the changedAt
     */
    public Date getChangedAt() {
        return changedAt;
    }

    /**
     * @param changedAt the changedAt to set
     */
    public void setChangedAt(Date changedAt) {
        this.changedAt = changedAt;
    }

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

}
