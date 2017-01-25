/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.blob.pas.systemlog;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import org.hibernate.annotations.Type;

/**
 *
 * @author mortenmatras
 */
@Entity
@DiscriminatorValue("Email")
public class EmailLog extends SystemLog implements java.io.Serializable{
    
    private String email;
    private String subject;
    @Type(type="text")
    private String content;

    public String getEmail() {
        return email;
    }
 
    public void setEmail(String email) {
        this.email = email;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

}
