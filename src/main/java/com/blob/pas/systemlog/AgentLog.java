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
@DiscriminatorValue("Agent")
public class AgentLog extends SystemLog implements java.io.Serializable{
    
    private Class agent;

    public Class getAgent() {
        return agent;
    }

    public void setAgent(Class agent) {
        this.agent = agent;
    }
    
}
