package com.asap.catalog.dao;

import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author Jens Rosenberg
 */
@Entity
@Table(name = "contact")
public class Contact extends Component{
    
   
    private String name;
    
    private String email;
    
    private String phone;
    
    private String message;
    
    private String result;
    
    @Temporal(TemporalType.TIMESTAMP)
    private Date time;
    
    /** Creates a new instance of Contact */
    public Contact() {
        
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

   

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }    

    public String getTitle() {
        return "contact";
    }
}
