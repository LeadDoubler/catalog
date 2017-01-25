/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.blob.step;

import com.asap.catalog.dao.Component;
import com.asap.catalog.dao.User;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;

/**
 *
 * @author Morten
 */
@Entity
public class Step extends Component{

    /**
     * @return the status
     */
    public StepStatus getStatus() {
        return status;
    }

    /**
     * @param status the status to set
     */
    public void setStatus(StepStatus status) {
        this.status = status;
    }

    /**
     * @return the title
     */
    public String getTitle() {
        return title;
    }

    /**
     * @param title the title to set
     */
    public void setTitle(String title) {
        this.title = title;
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

    /**
     * @return the done
     */
    public Date getDone() {
        return done;
    }

    /**
     * @param done the done to set
     */
    public void setDone(Date done) {
        this.done = done;
    }

    /**
     * @return the scheduledFor
     */
    public Date getScheduledFor() {
        return scheduledFor;
    }

    /**
     * @param scheduledFor the scheduledFor to set
     */
    public void setScheduledFor(Date scheduledFor) {
        this.scheduledFor = scheduledFor;
    }

    public enum StepStatus{
        SCHEDULED,DOING,DONE;
    }

    private StepStatus status;

    private String title;
    @ManyToOne
    private User user;
    @Temporal(value = javax.persistence.TemporalType.TIMESTAMP)
    private Date done;

    @Temporal(value = javax.persistence.TemporalType.TIMESTAMP)
    private Date scheduledFor;

}
