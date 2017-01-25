/*
 * Profile.java
 *
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package com.asap.catalog.dao;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import org.hibernate.annotations.Type;

/**
 * Entity class Profile
 *
 * @author asapunov
 */
@Entity
@Table (name = "profile")
public class Profile extends Component implements Serializable {
    
   
    @Lob
    @Column (name = "picture_binary", length=2097152)
    private  byte[] picture_binary;
    
    @ManyToOne(cascade=CascadeType.ALL)
    @JoinColumn (name="userId")
    private User user;
    
    @Type( type = "text")
    private String description;
    
    @Column( name = "theName")
    private String name;
    
    @Column(name = "thePosition")
    private String position;
    
    private Boolean deleted;
    
    @Temporal(value = TemporalType.DATE)
    @Column ( name = "theDate")
    private Date date;
    
    private String phone;
    
    /** Creates a new instance of Profile */
    public Profile () {
        deleted = false;
        setDate(new Date());
        name=" ";
    }
    
  
 
    
    /**
     * Returns a hash code value for the object.  This implementation computes
     * a hash code value based on the id fields in this object.
     * @return a hash code value for this object.
     */
    @Override
    public int hashCode () {
        int hash = 0;
        hash += (this.id != null ? this.id.hashCode () : 0);
        return hash;
    }
    
    /**
     * Determines whether another object is equal to this Profile.  The result is
     * <code>true</code> if and only if the argument is not null and is a Profile object that
     * has the same id field values as this object.
     * @param object the reference object with which to compare
     * @return <code>true</code> if this object is the same as the argument;
     * <code>false</code> otherwise.
     */
    @Override
    public boolean equals (Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Profile)) {
            return false;
        }
        Profile other = (Profile)object;
        if (this.id != other.id && (this.id == null || !this.id.equals (other.id))) return false;
        return true;
    }
    
    public byte[] getPicture_binary () {
        return picture_binary;
    }
    
    public void setPicture_binary (byte[] picture_binary) {
        this.picture_binary = picture_binary;
    }
    
    public User getUser () {
        return user;
    }
    
    public void setUser (User user) {
        this.user = user;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean getDeleted() {
        return deleted;
    }

    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;
    }

    public String getTitle() {
        return "Profile";
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
