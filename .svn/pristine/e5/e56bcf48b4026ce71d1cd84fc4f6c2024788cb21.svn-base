package com.blob.mail;

import java.util.Date;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.mortena.mail.Email;

import util.HibernateUtil;

import com.asap.configuration.Configuration;
import com.asap.configuration.ConfigurationManager;
import com.asap.util.HTMLMailSender;
import com.blob.pas.systemlog.EmailLog;

/**
 * Service for mail sending.
 *
 * TODO consider configuring with Spring
 *
 * @author Oleg
 *
 */
public class MailService {

    private static final Logger logger = Logger.getLogger(MailService.class);

    private static MailService instance;

    // disallow direct creation
    private MailService() {
    }

    /**
     * Returns instance of MailService, one per application.
     */
    public static final MailService getInstance() {
        if (instance == null) {
            instance = new MailService();
        }
        return instance;
    }

    /**
     * Sends e-mail synchronously
     *
     * @param email e-mail object to send
     * @see org.mortena.mail.Email
     */
    public void send(Email email) {
        HTMLMailSender sender = new HTMLMailSender();
        try {
            for (String toAddress : email.getTo().split("[;,]")) {
                sender.addToAddress(toAddress);
            }
            if (email.getBcc() != null) {
                sender.setBcc(email.getBcc());
            }
            if (email.getCc() != null) {
                sender.setCc(email.getCc());
            }
            if (email.getFrom() == null) {
                String specificFrom = ConfigurationManager.getParameter(email.getType() + ".mail.from");
                if (specificFrom == null) {
                    if (ConfigurationManager.getParameter("mail.from") != null) {
                        sender.setFromAddress(ConfigurationManager.getParameter("mail.from"));
                    } else {
                        sender.setFromAddress(com.asap.configuration.Configuration.getInstance().getProps().getProperty("mail.from"));
                    }
                } else {
                    sender.setFromAddress(specificFrom);
                }
            } else {
                sender.setFromAddress(email.getFrom());
            }
            if (sender.getFromAddress() == null) {
                sender.setFromAddress(
                        com.asap.configuration.Configuration.getInstance().getProps().getProperty("mail.from"));
            }

            String subject = email.getSubject();

            if (subject == null) {
                subject = ConfigurationManager.getParameter(email.getType() + ".subject");
            }

            String subjectPrefix = Configuration.getInstance().getProps().getProperty("mail.subject.prefix");
            if (!subjectPrefix.trim().equals("")) {
                subject = subjectPrefix + " " + subject;
            }

            sender.setSubject(subject);

            sender.setSenderAddress(email.getSenderAddress());
            sender.setSenderPassword(email.getSenderPassword());
            String messageText = email.getContent();

            sender.setMessageText(messageText);
            String sendMails = ConfigurationManager.getParameter("sendMails");
            sender.setAttachments(email.getAttachments());
            if (sendMails == null || sendMails.length() == 0) {
                sendMails = com.asap.configuration.Configuration.getInstance().getProps().getProperty("sendMails");
            }
            if (sendMails != null && sendMails.equals("yes")) {
                sender.sendMail();
            }
            EmailLog log = new EmailLog();
            log.setContent(messageText);
            log.setEmail(email.getTo());
            log.setSubject(email.getSubject());
            Session session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            session.save(log);
            session.getTransaction().commit();
            session.close();
        } catch (Exception e) {
            logger.error("Error sending email to " + email.getTo() + ", " + e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * Sends e-mail asynchronously
     *
     * @param email e-mail object to send
     * @see org.mortena.mail.Email
     */
    public void sendAsync(final Email email) {

        // save to db
        email.setCreated(new Date());
        email.setStatus(Email.STATUS_ASYNC);

        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.save(email);
        session.getTransaction().commit();
        session.close();

        // send asynchronously
        try {
            Thread t = new Thread(new Runnable() {
                @Override
                public void run() {
                    send(email);
                }
            });
            t.start();
        } catch (Exception e) {
            logger.error("Error sending email to " + email.getTo(), e);
        }
    }

    /*
	public void sendAsync2(Email email) {
		// persist email object
		email.setStatus( Email.STATUS_QUEUED );
		email.setCreated( new Date() );
		String subjectPrefix = Configuration.getInstance().getProps().getProperty("mail.subject.prefix");
		if (subjectPrefix != null && !subjectPrefix.trim().equals("")){
                    email.setSubject(subjectPrefix + " " + email.getSubject());
                }
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		session.save(email);
		session.getTransaction().commit();
        session.close();
	}
     */
}
