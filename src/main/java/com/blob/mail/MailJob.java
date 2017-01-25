package com.blob.mail;

import java.util.Date;
import java.util.List;
import java.util.logging.Level;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.LockMode;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.mortena.mail.Email;

import util.HibernateUtil;

import com.blob.pas.agent.DefaultAgent;

/**
 * Job to send e-mails asynchronously 
 * 
 * @author Oleg
 *
 */
public class MailJob extends DefaultAgent {

	private static final Logger logger = Logger.getLogger(MailJob.class);
	
	@Override
	public void execute() {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException ex) {
            java.util.logging.Logger.getLogger(MailJob.class.getName()).log(Level.SEVERE, null, ex);
        }
		Session session = HibernateUtil.getSessionFactory().openSession();
		
		// Lock e-mails in database while changing status to SENDING
		session.beginTransaction();
        Criteria crit = session.createCriteria(Email.class).add(Restrictions.eq("status", Email.STATUS_QUEUED));
        List<Email> queuedEmails = crit.list();
        if (logger.isDebugEnabled()) {
            logger.debug("E-mails in queue: " + queuedEmails.size());
        }
        for (Email email : queuedEmails) {
        	email.setStatus( Email.STATUS_SENDING );
        	session.update(email);
        }
        session.getTransaction().commit();
        
        
        session.beginTransaction();
        for (Email email : queuedEmails) {
        	if (logger.isDebugEnabled()) {
				logger.debug("Sending mail " + email);
			}
        	MailService.getInstance().send(email);
        	email.setStatus( Email.STATUS_OK );
        	email.setSent( new Date() );
        	session.update(email);
        }
        
        session.getTransaction().commit();
        session.close();
	}
	
	@Override
	public AgentType getType() {
		return AgentType.CRON;
	}
	
	@Override
	public String getCronExpression() {
		return "0 0/1 * * * ?"; // every minute
	}
	
	@Override
	public boolean logResult() {
		return false; // do not log mail jobs
	}
	
	@Override
	public boolean isEnabled() {
		return false;
	}

}
