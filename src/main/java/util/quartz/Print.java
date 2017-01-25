package util.quartz;

import java.io.File;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.quartz.Job;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

public class Print implements Job {
    
    public void execute(JobExecutionContext context) throws JobExecutionException {
        String string = (String) context.getJobDetail().getJobDataMap().get("printtext");
        
        System.out.println(string);
    }
}