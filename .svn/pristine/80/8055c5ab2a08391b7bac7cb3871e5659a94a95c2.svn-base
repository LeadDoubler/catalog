/*
 * Agent.java
 *
 * Created on 26. maj 2007, 09:01
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package com.blob.pas.agent;

import java.util.Date;
import org.quartz.Job;

/** 
 *
 * @author mortenandersen
 */
public interface Agent {

    public enum AgentType{
        DAILY,SECONDLY,WEEKLY,CRON;
    }
    
    public Job getJob();

    public AgentType getType();
    
    public Date getStartDate();
    
    public String getName();
    
    public String getGroup();
    
    public int getSecondsBetweenRun();
    
    /**
     * Returns cron expression for scheduling
     */
    public String getCronExpression();
    
    /**
     * Whether to log the job to database
     */
    public boolean logResult();
    
    /**
     * Job is enabled?
     */
    public boolean isEnabled();
    
}
