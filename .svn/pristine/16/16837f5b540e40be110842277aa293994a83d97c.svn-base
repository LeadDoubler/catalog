/*
 * Email.java
 *
 * Created on 15. oktober 2007, 08:15
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */
package org.mortena.mail;

import java.io.File;
import java.io.StringWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.annotations.AccessType;

import util.HibernateUtil;

import com.asap.catalog.dao.Component;
import com.asap.configuration.ConfigurationManager;
import com.asap.util.HTMLMailSender;
import com.blob.pas.systemlog.EmailLog;

import freemarker.ext.beans.BeansWrapper;
import freemarker.ext.beans.ResourceBundleModel;
import freemarker.template.Configuration;
import freemarker.template.DefaultObjectWrapper;
import freemarker.template.Template;
import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

/**
 *
 * @author Morten Matras
 */
@Entity
@Table(name = "system_email")
public class Email extends Component implements java.io.Serializable {

    private static final Logger logger = Logger.getLogger(Email.class);

    public static final String TEMPLATEPATH = "/WEB-INF/view/mail/template/";
    private static String dirPath;

    public static final int STATUS_OK = 0;
    public static final int STATUS_QUEUED = 1;
    public static final int STATUS_SENDING = 2;
    public static final int STATUS_ASYNC = 3;

    @Transient
    private String templatePath;

    private String senderAddress;
    private String senderPassword;
    private String bcc;
    private String cc;

    public void setBcc(String bcc) {
        this.bcc = bcc;
    }

    public String getBcc() {
        return bcc;
    }

    public void setCc(String cc) {
        this.cc = cc;
    }

    public String getCc() {
        return cc;
    }

    private String subject;

    @Column(name = "sendTo")
    private String to;

    @Column(name = "sendFrom")
    private String from;

    @Transient
    private List<File> attachments;

    @Transient
    private Locale locale;

    @Transient
    private Map properties;

    @Lob
    @AccessType("property")
    private String content;

    private Date created;

    private Date sent;

    private int status = STATUS_OK;

    /**
     * Refers to the name of the file except .ftl in the TEMPLATEPATH folder.
     * Example: templateName="Test" refers to the file: TEMPLATEPATH/Test.ftl
     */
    @Transient
    private String templateName;

    @Transient
    private String templateEncoding = "UTF-8";

    public String getContent() {
        if (content == null) {
            try {
                // resolve result of Template
                Writer writer = new StringWriter();
                Configuration cfg = new Configuration();

                if (locale != null) {
                    cfg.setLocale(locale);
                }

                cfg.setDefaultEncoding("UTF-8");
                cfg.setDirectoryForTemplateLoading(new File(com.asap.configuration.Configuration.getInstance().getProps().getProperty("rootPath")));
                //cfg.setDirectoryForTemplateLoading(arg0)
                BeansWrapper objectWrapper = new DefaultObjectWrapper();
                cfg.setObjectWrapper(objectWrapper);
                //System.out.println("templatepath = " + getTemplatePath());
                Template template = cfg.getTemplate(getTemplatePath());
                //cfg.setEncoding(new Locale("da_DK"),"utf-8");
                //String val = template.getOutputEncoding();
                template.setEncoding(templateEncoding);
                template.setOutputEncoding(templateEncoding);
                //System.out.println("getting content...");
                bundle = ResourceBundle.getBundle("StripesResources");
                if (bundle != null) {
                    ResourceBundleModel bm = new ResourceBundleModel(getBundle(), objectWrapper);
                    if (bm != null) {
                        setProperty("bundle", bm);
                    }
                }

                template.process(getProperties(), writer);

                String messageText = writer.toString();
                // messageText = HTMLMailSender.replaceDanishLetters(messageText);
                content = messageText;
            } catch (Exception ex) {
                logger.error("Error getting content for email to + " + to + ", template: " + templateName, ex);
            }
        }
        return content;
    }

    @Transient
    private ResourceBundle bundle;

    public String getType() {
        return this.getClass().getName().substring(this.getClass().getPackage().getName().length() + 1);
    }

    @SuppressWarnings("unused")
    private String getPath() {
        return getDirPath() + getTemplatePath();
    }

    @SuppressWarnings("unchecked")
    public void setProperty(String key, Object value) {
        if (properties == null) {
            properties = new HashMap();
        }
        properties.put(key, value);
    }

    /**
     * Creates a new instance of Email
     */
    public Email() {
    }

    /**
     * Helper constructor to get the basic setup created. Use this to be sure
     * you con't forget anything. After calling this call setProperty with the
     * properties needed in the mail and the use the @method send() to send it.
     *
     * @param subject
     * @param to
     * @param templateName
     */
    public Email(String subject, String to, String templateName) {
        this.subject = subject;
        this.to = to;
        this.templateName = templateName;
    }

    public String getTemplatePath() {
        if (templatePath == null && templateName == null) {
            return TEMPLATEPATH + this.getType() + ".ftl";
        } else if (templateName != null) {
            return TEMPLATEPATH + getTemplateName() + ".ftl";
        }
        return templatePath;
    }

    public void setTemplatePath(String templatePath) {
        this.templatePath = templatePath;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    /**
     * Returns "to" field. Can return multiple e-mails separated with comma
     */
    public String getTo() {
        return to;
    }

    /**
     * Set "to" field. Can receive multiple e-mails separated with comma
     */
    public void setTo(String to) {
        this.to = to;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public Map getProperties() {
        return properties;
    }

    private String getConfiguredSubject() {
        return ConfigurationManager.getParameter(getType() + ".subject");
    }

    public void send() {
        HTMLMailSender sender = new HTMLMailSender();

        try {
            for (String toAddress : getTo().split("[;,]")) {
                sender.addToAddress(toAddress);
            }
            if (getFrom() == null) {
                String specificFrom = ConfigurationManager.getParameter(this.getType() + ".mail.from");
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
                sender.setFromAddress(getFrom());
            }
            if (sender.getFromAddress() == null) {
                sender.setFromAddress(
                        com.asap.configuration.Configuration.getInstance().getProps().getProperty("mail.from"));
            }
            if (getSubject() == null) {
                sender.setSubject(getConfiguredSubject());
            } else {
                sender.setSubject(subject);
            }
            String messageText = getContent();

            sender.setMessageText(messageText);
            String sendMails = ConfigurationManager.getParameter("sendMails");
            sender.setAttachments(attachments);
            if (sendMails == null || sendMails.length() == 0) {
                sendMails = com.asap.configuration.Configuration.getInstance().getProps().getProperty("sendMails");
            }
            if (sendMails != null && sendMails.equals("yes")) {
                sender.sendMail();
            }
            EmailLog log = new EmailLog();
            log.setContent(messageText);
            log.setEmail(to);
            log.setSubject(subject);
            Session session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            session.save(log);
            session.getTransaction().commit();
            session.close();
        } catch (Exception e) {
            logger.error("Error sending e-mail to " + to, e);
        }
    }

    public static String getDirPath() {
        return dirPath;
    }

    public static void setDirPath(String aDirPath) {
        dirPath = aDirPath;
    }

    public String getTemplateName() {
        return templateName;
    }

    public void setTemplateName(String templateName) {
        this.templateName = templateName;
    }

    public void setContent(String content) {
        this.content = content;
    }

    /**
     * @return the locale
     */
    public Locale getLocale() {
        return locale;
    }

    /**
     * @param locale the locale to set
     */
    public void setLocale(Locale locale) {
        this.locale = locale;
    }

    /**
     * @return the attachments
     */
    public List<File> getAttachments() {
        return attachments;
    }

    /**
     * @param attachments the attachments to set
     */
    public void setAttachments(List<File> attachments) {
        this.attachments = attachments;
    }

    public boolean addAttachment(File file) {
        if (attachments == null) {
            attachments = new ArrayList<File>();
        }
        return attachments.add(file);
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public Date getSent() {
        return sent;
    }

    public void setSent(Date sent) {
        this.sent = sent;
    }

    @Override
    public String toString() {
        return "Email #" + getId() + " [to " + getTo() + ", subject " + getSubject() + "]";
    }

    private static final long serialVersionUID = 7242464154921482387L;

    /**
     * @return the bundle
     */
    public ResourceBundle getBundle() {
        return bundle;
    }

    /**
     * @param bundle the bundle to set
     */
    public void setBundle(ResourceBundle bundle) {
        this.bundle = bundle;
    }

    public void setTemplateEncoding(String templateEncoding) {
        this.templateEncoding = templateEncoding;
    }

    /**
     * @return the senderAddress
     */
    public String getSenderAddress() {
        return senderAddress;
    }

    /**
     * @param senderAddress the senderAddress to set
     */
    public void setSenderAddress(String senderAddress) {
        this.senderAddress = senderAddress;
    }

    /**
     * @return the senderPassword
     */
    public String getSenderPassword() {
        return senderPassword;
    }

    /**
     * @param senderPassword the senderPassword to set
     */
    public void setSenderPassword(String senderPassword) {
        this.senderPassword = senderPassword;
    }

}
