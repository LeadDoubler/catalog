/*
 * QuartzServlet.java
 *
 * Created on 13. maj 2007, 15:46
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */
package com.blob.pas.agent;

import java.lang.reflect.Modifier;
import java.text.ParseException;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

import net.sourceforge.stripes.util.ResolverUtil;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.quartz.CronTrigger;
import org.quartz.JobDetail;
import org.quartz.SchedulerException;
import org.quartz.Trigger;
import org.quartz.TriggerUtils;
import org.quartz.impl.StdSchedulerFactory;

import util.HibernateUtil;

import com.blob.pas.agent.Agent.AgentType;
import com.blob.pas.systemlog.AgentLog;

/**
 *
 * @author mortenandersen
 */
@SuppressWarnings("serial")
public class QuartzServlet extends HttpServlet {
    //Thread thread = new AgentThread();

    private static final Logger logger = Logger.getLogger(QuartzServlet.class);

    private org.quartz.Scheduler scheduler;

    /**
     * Creates a new instance of QuartzServlet
     */
    public QuartzServlet() {

    }

    public void destroy() {
        try {
            scheduler.shutdown();
        } catch (SchedulerException ex) {
            ex.printStackTrace();
        }
    }

    public void start() throws SchedulerException {
        // Use the factory to create a Scheduler instance
        scheduler = StdSchedulerFactory.getDefaultScheduler();

        logger.info("Starting scheduler " + scheduler.getSchedulerName() + ", instance id: " + scheduler.getSchedulerInstanceId()
                + ", clustered: " + scheduler.getMetaData().isJobStoreClustered());

        // Start the Scheduler running
        scheduler.start();

        ResolverUtil<Agent> resolver = new ResolverUtil<Agent>();
        resolver.findImplementations(Agent.class, "com");
        Set<Class<? extends Agent>> agents = resolver.getClasses();
        for (Class<?> agentClass : agents) {
            if (logger.isDebugEnabled()) {
                logger.debug("Found agent candidate: " + agentClass.getName());
            }
            // aConf.addAnnotatedClass(component);
            if (!agentClass.isInterface() && !Modifier.isAbstract(agentClass.getModifiers())) {
                logger.info("Processing agent " + agentClass.getName());
                try {
                    Agent agent = (Agent) agentClass.newInstance();

                    if (agent.isEnabled()) {
                        scheduleAgentJob(agent);
                    } else {
                        removeAgentJob(agent);
                    }
                } catch (Exception e) {
                    logger.error("Error processing agent " + agentClass.getName(), e);
                }
            }
        }

    }

    private void removeAgentJob(Agent agent) throws SchedulerException {
        scheduler.deleteJob(agent.getName(), agent.getGroup());
        logger.info("Agent " + agent.getName() + " removed");
    }

    private void scheduleAgentJob(final Agent agent) throws SchedulerException {

        // JobDetail holds the definition for Jobs
        JobDetail jobDetail = new JobDetail();
        jobDetail.setJobClass(agent.getJob().getClass());
        jobDetail.setName(agent.getName());
        jobDetail.setGroup(agent.getGroup());

        boolean skipJob = false;
        try {
            JobDetail storedJobDetail = scheduler.getJobDetail(agent.getName(), agent.getGroup());
            if (logger.isDebugEnabled()) {
                logger.debug("Stored job detail: " + storedJobDetail);
            }
            if (storedJobDetail != null) {
                skipJob = true;
            }
        } catch (SchedulerException e) {
        }

        if (skipJob) {
            logger.info("Job [" + agent.getName() + "," + agent.getGroup() + "] already exists, skipping...");
            return;
        }

        if (logger.isDebugEnabled()) {
            logger.debug("Job [" + agent.getName() + "," + agent.getGroup() + "] does not exist, scheduling...");
        }

        Trigger trigger = null;

        if (AgentType.CRON.equals(agent.getType())) {
            // create cron trigger
            try {
                trigger = new CronTrigger(agent.getName(), agent.getGroup(), agent.getCronExpression());
            } catch (ParseException e) {
                logger.error("Error creating cron trigger for expression " + agent.getCronExpression());
                throw new SchedulerException(e);
            }
        } else if (AgentType.DAILY.equals(agent.getType())) {
            trigger = TriggerUtils.makeDailyTrigger(0, 0);
        } else if (agent.getSecondsBetweenRun() == DefaultAgent.DAY) {
            trigger = TriggerUtils.makeDailyTrigger(0, 0);
        } else {
            trigger = TriggerUtils.makeSecondlyTrigger(agent.getSecondsBetweenRun());
        }

        if (AgentType.CRON != agent.getType()) {
            Session session = HibernateUtil.getSessionFactory().openSession();
            AgentLog agentLog = (AgentLog) session.createCriteria(AgentLog.class)
                    .add(Restrictions.eq("agent", agent.getClass()))
                    .addOrder(Order.desc("created"))
                    .setMaxResults(1)
                    .uniqueResult();
            trigger.setStartTime(agentLog == null ? agent.getStartDate() : agentLog.getCreated());
            trigger.setName(agent.getName());
            trigger.setGroup(agent.getGroup());
            session.close();
            session = null;
        }

        // Setup the Job and Trigger with the Scheduler
        scheduler.scheduleJob(jobDetail, trigger);

        logger.info("Scheduled agent " + agent.getName() + ", next fire time: " + trigger.getNextFireTime());

    }

    public void init() throws ServletException {
        try {
            start();
        } catch (SchedulerException ex) {
            //ex.printStackTrace();
        }

        /*super.init();
        System.out.println("Starting Quartz");
        JobDetail jd = new JobDetail("TestJOB",null, AgentController.class);
        SimpleTrigger trigger = new SimpleTrigger("trigger1", "group1");
        trigger.setRepeatCount(10);
        trigger.setRepeatInterval(1000); // milliseconds
        trigger.setStartTime(new Date()); //Start now
        try {
           SchedulerFactory schedFact = new StdSchedulerFactory();         
           org.quartz.Scheduler scheduler = schedFact.getScheduler();
           scheduler.scheduleJob(jd,trigger);
        } catch (SchedulerException e) {
            //log.warn("An error occurred - "+e.getMessage());
            e.printStackTrace(); 
        }     
         */
 /*   
            SchedulerFactory schedFact = new org.quartz.impl.StdSchedulerFactory();
            Scheduler sched = schedFact.getScheduler();        
            sched.start();
            JobDetail jobDetail = new JobDetail("todoAgent",null,AgentController.class);
            Trigger trigger = TriggerUtils.makeSecondlyTrigger();//.makeHourlyTrigger(); // fire every hour
            trigger.setStartTime( new Date() );  
            trigger.setName("todoAgent");
            sched.scheduleJob(jobDetail, trigger);*/
    }

}
