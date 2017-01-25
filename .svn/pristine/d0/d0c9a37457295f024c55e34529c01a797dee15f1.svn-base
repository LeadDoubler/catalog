/*
 * QuartzActionBean.java
 *
 * Created on 24. maj 2007, 13:39
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package com.asap.web;

import java.util.Date;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.StreamingResolution;
import org.quartz.JobDetail;
import org.quartz.SchedulerException;
import org.quartz.Trigger;
import org.quartz.TriggerUtils;
import org.quartz.impl.StdSchedulerFactory;
import util.quartz.Print;

/**
 *
 * @author Jens Rosenberg
 */
public class QuartzActionBean extends CatalogActionBean{
    private org.quartz.Scheduler scheduler;
    Class schedulerclass = Print.class;
    
    public Resolution start() throws SchedulerException{
        // Use the factory to create a Scheduler instance
        scheduler = StdSchedulerFactory.getDefaultScheduler();
        
        // JobDetail holds the definition for Jobs
        JobDetail jobDetail =  new JobDetail(schedulerclass.getName(),org.quartz.Scheduler.DEFAULT_GROUP,schedulerclass);

        // Store job parameters to be used within execute()
        jobDetail.getJobDataMap().put("printtext","Yes sir!");

        // Other neccessary Job parameters here

        // Create a Trigger that fires every 60 seconds
        Trigger trigger = TriggerUtils.makeSecondlyTrigger(1);
        trigger.setStartTime(new Date());
        trigger.setName("60secs trigger");
        trigger.setGroup("triggaz");
        
        try {
            // Setup the Job and Trigger with the Scheduler
            scheduler.scheduleJob(jobDetail, trigger );
        } catch (SchedulerException ex) {
            ex.printStackTrace();
        }
        
        // Start the Scheduler running
        scheduler.start();
        
        return new StreamingResolution("text/html","Scheduler started..."+getContext().getServletContext().getInitParameter("link") ) ;
        
    }
    
    public Resolution stop() throws SchedulerException{
        scheduler = StdSchedulerFactory.getDefaultScheduler();
        if(scheduler.isShutdown()){
          return new StreamingResolution("text/html","Scheduler allready stopped...");
        }
        
        scheduler.shutdown();
        return new StreamingResolution("text/html","Scheduler stopped...");
    }
}
