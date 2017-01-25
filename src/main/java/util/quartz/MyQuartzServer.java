package util.quartz;

import java.util.Date;
import org.quartz.JobDetail;
import org.quartz.SchedulerException;
import org.quartz.Trigger;
import org.quartz.TriggerUtils;
import org.quartz.impl.StdSchedulerFactory;

public class MyQuartzServer {

    public static void main(String[] args) {
        MyQuartzServer server = new MyQuartzServer();

        try {
            server.startScheduler();
        } catch (SchedulerException ex) {
            ex.printStackTrace();
        }
    }

    public void startScheduler() throws SchedulerException {

        // Use the factory to create a Scheduler instance
        org.quartz.Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();

        // JobDetail holds the definition for Jobs
        JobDetail jobDetail =  new JobDetail("Print", org.quartz.Scheduler.DEFAULT_GROUP,Print.class);

        // Store job parameters to be used within execute()
        jobDetail.getJobDataMap().put("FTP_HOST","\\home\\cavaness\\inbound");

        // Other neccessary Job parameters here

        // Create a Trigger that fires every 60 seconds
        Trigger trigger = TriggerUtils.makeSecondlyTrigger(1);
        trigger.setStartTime(new Date());
        trigger.setName("60secs trigger");
        trigger.setGroup("triggaz");
        
        // Setup the Job and Trigger with the Scheduler
        scheduler.scheduleJob(jobDetail, trigger );
        
        // Start the Scheduler running
        scheduler.start();
    }
}