/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.blob.pas.systemlog;

import com.asap.catalog.dao.Component;
import java.util.Date;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;
import javax.persistence.Temporal;

/**
 *
 * @author mortenmatras
 */
@Entity
@Table (name="SystemLog")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "log_type", discriminatorType = DiscriminatorType.STRING)
@DiscriminatorValue("SYSTEM_LOG")
public class SystemLog extends Component implements java.io.Serializable{
    
    private String text;
    private String url;

    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Date created = new Date();
    
    
    
    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

  

}
