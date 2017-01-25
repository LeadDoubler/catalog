/*
 * DefaultAgent.java
 *
 * Created on 13. juni 2007, 11:37
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */
package com.blob.pas.agent;

import java.util.Date;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.StaleObjectStateException;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import util.HibernateUtil;


/**
 *
 * @author mortenandersen
 */
public abstract class DefaultAgent implements Agent, Job {

    private static final org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(DefaultAgent.class);

    public static final int MINUTE = 60;
    public static final int HOUR = 60 * 60;
    public static final int DAY = 24 * HOUR;
    public static final int WEEK = 7 * DAY;
    public static final int MONTH = 31 * DAY;

    /** Creates a new instance of DefaultAgent */
    public DefaultAgent() {
    }

    public AgentType getType() {
        return AgentType.SECONDLY;
    }

    public Job getJob() {
        return this;
    }

    public abstract void execute();

    public Date getStartDate() {
        return new Date();
    }

    public String getName() {
        return this.getClass().getName();
    }

    public String getGroup() {
        return "Agent";
    }

    /**
     *Must return number of seconds in 14 days
     */
    public int getSecondsBetweenRun() {
        return DAY;
    }


    /* (non-Javadoc)
     * @see org.quartz.Job#execute(org.quartz.JobExecutionContext)
     */
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        SessionFactory sf = HibernateUtil.getSessionFactory();
        setSession(sf.getCurrentSession());

        try {
            if (logger.isDebugEnabled()) {
                logger.debug("Executing job " + getClass().getName() + " fire time: " + jobExecutionContext.getFireTime());
            }

            getSession().beginTransaction();

            try {
                this.execute();
            } catch (Exception e) {
                logger.error("Error executing job " + getClass().getName(), e);
            }
            if (logResult()) {
                /*AgentLog log = new AgentLog();
                log.setAgent(this.getClass());
                getSession().save(log);
                */

            }

            getSession().getTransaction().commit();

        } catch (StaleObjectStateException staleEx) {
            // Rollback, close everything, possibly compensate for any permanent changes
            // during the conversation, and finally restart business conversation. Maybe
            // give the user of the application a chance to merge some of his work with
            // fresh data... what you do here depends on your applications design.
            logger.error("Error running job " + getClass().getName(), staleEx);
            staleEx.printStackTrace();
        } catch (Throwable ex) {
            logger.error("Error running job " + getClass().getName(), ex);
            try {
                if (getSession().getTransaction().isActive()) {
                    getSession().getTransaction().rollback();
                }
            } catch (Throwable rbEx) {
            }

            // Let others handle it... maybe another interceptor for exceptions?
            ex.printStackTrace();
        } finally {
            if (session.isOpen()) {
                session.close();
            }
            session = null;
        }

    }
    private Session session;

    public Session getSession() {
        return session;
    }

    /**
     * @param session the session to set
     */
    public void setSession(Session session) {
        this.session = session;
    }

    public String getCronExpression() {
        return null;
    }

    public boolean logResult() {
        return true; // log jobs by default
    }
    
    public boolean isEnabled() {
        return true; // jobs are enabled by default
    }
}
