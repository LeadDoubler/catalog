/*
 * HTMLMailUtil.java
 *
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */
package com.asap.util;

import java.io.File;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.net.URL;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;
import java.util.logging.Level;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.activation.URLDataSource;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.NoSuchProviderException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import org.apache.log4j.Logger;

import au.id.jericho.lib.html.HTMLElementName;
import au.id.jericho.lib.html.OutputDocument;
import au.id.jericho.lib.html.Source;
import au.id.jericho.lib.html.StartTag;

import com.asap.configuration.Configuration;

import freemarker.template.Template;
import freemarker.template.TemplateException;
import java.util.ArrayList;
import javax.mail.Authenticator;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.util.ByteArrayDataSource;

/**
 *
 * @author asapunov
 */
public class HTMLMailSender {

    Logger logger = Logger.getLogger(HTMLMailSender.class);

    private Template template;

    private Map dataModel;

    private String subject;

    private InternetAddress fromAddress;

    private Set<InternetAddress> toAddresses = new LinkedHashSet<InternetAddress>();

    private String messageText;

    private String headerFile = "header.ftl";

    private String footerFile = "footer.ftl";

    private String senderAddress;
    private String senderPassword;
    private String bcc, cc;

    public HTMLMailSender() {
        super();
    }

    public void createAndSendMessage(Session mailSession, Transport t) throws Exception, MessagingException, TemplateException, IOException, NumberFormatException {
        MimeMessage message = new MimeMessage(mailSession);

        message.setSubject(subject, "UTF-8");
        message.setFrom(getFromAddress());
        message.setHeader("Content-Type", "text/plain; charset=UTF-8");

        if (this.getSenderAddress() != null) {
            InternetAddress ia = new InternetAddress(this.getSenderAddress());
            message.setFrom(ia);
        }
        for (InternetAddress addr : toAddresses) {
            message.addRecipient(Message.RecipientType.TO, addr);
        }
        this.createContent();

        MimeMultipart multipart = new MimeMultipart();
        addAttachments(multipart, messageText);
        message.setContent(multipart);

        //message.setContent("This is a test", "text/plain");
        //message.addRecipient(Message.RecipientType.TO,new InternetAddress("mm@redantenna.com"));
        t.sendMessage(message, message.getRecipients(Message.RecipientType.TO));
        t.close();
        //props.put("mail.smtp.starttls.enable","true");
    }

    public void createContent() throws IOException, TemplateException {

        if (template != null) {
            //System.out.println("Sending mail using template");
            dataModel.put("headerFile", headerFile);
            dataModel.put("footerFile", footerFile);
            // resolve result of Template
            Writer writer = new StringWriter();
            template.setEncoding("UTF-8");
            template.setOutputEncoding("UTF-8");
            template.process(dataModel, writer);
            messageText = replaceDanishLetters(writer.toString());
        }
    }

    public void setTemplate(Template template) {
        this.template = template;
    }

    public void setDataModel(Map dataModel) {
        this.dataModel = dataModel;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public void setFromAddress(InternetAddress fromAddress) {
        this.fromAddress = fromAddress;
    }

    public void setFromAddress(String fromAddress) throws AddressException {
        this.fromAddress = new InternetAddress(fromAddress);
    }

    public void setToAddress(String toAddress) throws AddressException {
        addToAddress(toAddress);
    }

    public void addToAddress(String toAddress) throws AddressException {
        toAddresses.add(new InternetAddress(toAddress));
    }

    private InternetAddress getBccAddress() throws AddressException {
        return new InternetAddress(this.getBcc());
    }

    private InternetAddress getCcAddress() throws AddressException {
        return new InternetAddress(this.getCc());
    }

    public void setMessageText(String messageText) {
        this.messageText = messageText;
    }

    String user;
    String psw;

    public void sendMail() {
        String smtp = Configuration.getInstance().getProps().getProperty("smtp");
        String transport = Configuration.getInstance().getProps().getProperty("smtp.transport");
        String auth = Configuration.getInstance().getProps().getProperty("smtp.auth");
        String port = Configuration.getInstance().getProps().getProperty("smtp.port");
        user = Configuration.getInstance().getProps().getProperty("smtp.user");
        psw = Configuration.getInstance().getProps().getProperty("smtp.psw");
        String useAuthenticator = Configuration.getInstance().getProps().getProperty("smtp.useAuth");
        if (this.getSenderAddress() != null) {
            user = this.getSenderAddress();
        }
        if (this.getSenderPassword() != null) {
            psw = this.getSenderPassword();
        }
        try {
            Properties props = new Properties();

            if (transport == null) {
                transport = "smtp";
            }
            if ("smtp.sendgrid.net".equals(smtp)) {
                //System.out.println("Using sendgrid username ="+user+" psw="+psw);
                props.put("mail.transport.protocol", "smtp");
                props.put("mail.smtp.host", smtp);
                props.put("mail.smtp.port", 587);
                props.put("mail.smtp.auth", "true");
                Authenticator authenticator = new SMTPAuthenticator();
                Session mailSession = Session.getDefaultInstance(props, authenticator);
                mailSession.setDebug(true);
                Transport t = mailSession.getTransport();
                t.connect();
                createAndSendMessage(mailSession, t);
            } else if ("smtps".equals(transport)) {
                props.put("mail.transport.protocol", "smtps");
                props.put("mail.smtps.host", smtp);
                props.put("mail.smtps.auth", "true");
                // props.put("mail.smtps.quitwait", "false");
                Session mailSession;
                if ("true".equals(useAuthenticator)) {
                    mailSession = Session.getDefaultInstance(props, new SMTPAuthenticator());
                } else {
                    mailSession = Session.getDefaultInstance(props);
                }
                mailSession.setDebug(false);

                Transport t = mailSession.getTransport();
                t.connect(smtp, Integer.parseInt(port), user, psw);
                createAndSendMessage(mailSession, t);
            } else {
                props.setProperty("mail.transport.protocol", transport);

                if (smtp == null || smtp.length() == 0) {
                    smtp = "localhost";
                }

                props.put("mail." + transport + ".host", smtp);

                if (auth != null) {
                    props.put("mail." + transport + ".auth", auth);
                }
                Session mailSession = Session.getDefaultInstance(props, null);
                //Session mailSession = prepareSMTPSession();
                mailSession.setDebug(true);

                MimeMessage message = new MimeMessage(mailSession);
                //logger.debug("message = "+message+" subject = "+subject+" from: "+this.getFromAddress());
                message.setSubject(subject);

                message.setFrom(getFromAddress());
                if (this.getSenderAddress() != null) {
                    InternetAddress ia = new InternetAddress(this.getSenderAddress());
                    message.setFrom(ia);
                }
                for (InternetAddress addr : toAddresses) {
                    message.addRecipient(Message.RecipientType.TO, addr);
                }
                try {
                    if (this.getBcc() != null) {
                        message.addRecipient(Message.RecipientType.BCC, this.getBccAddress());
                    }
                    if (this.getCc() != null) {
                        message.addRecipient(Message.RecipientType.CC, this.getCcAddress());
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                Transport t = mailSession.getTransport();
                if ("smtps".equals(transport)) {
                    t.connect(smtp, Integer.parseInt(port), user, psw);
                }
                createMessage(message, mailSession.getTransport());
            }
        } catch (NoSuchProviderException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        } catch (MessagingException ex) {
            ex.printStackTrace();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private Session prepareSMTPSession() throws Exception {
        //try {
        Properties props = System.getProperties();
        props.put("mail.smtp.host", "localhost");
        Session session = Session.getDefaultInstance(props, null);
        return session;
        /*
			Properties props = new Properties();
			props.setProperty("mail.transport.protocol", "smtp");
                        String smtp = Configuration.getInstance().getProps().getProperty("smtp");
                        if (smtp == null || smtp.length()==0){
                            smtp = "localhost";
                        }
			props.setProperty("mail.smtp.host", smtp);
                        System.out.println("smtp server = "+smtp);
			//props.put("mail.smtp.localhost", InetAddress.getLocalHost().getHostName());

			return Session.getDefaultInstance(props, null);
                 **/
 /*} catch (UnknownHostException e) {
			// TODO: Override Exception
                 * 
                 * 
                 * 
			throw new Exception("Exception occured");
		}*/
    }

    private void createMessage(Message message, Transport transport)
            throws MessagingException, IOException, Exception {
        createContent();
        //System.out.println("Sending mail using HTML");
        sendHTMLMessage(transport, message);
    }

    /**
     * TODO Remove this, use UTF-8 to send mail messages
     */
    public static String replaceDanishLetters(String html) {
        return html;
    }

    private void sendHTMLMessage(final Transport transport,
            final Message message) throws Exception, MessagingException {
        MimeMultipart multipart = resolveImages(messageText);
        message.setContent(multipart);
        if (!transport.isConnected()) {
            transport.connect();
        }

        transport.sendMessage(message, message
                .getRecipients(Message.RecipientType.TO));
        transport.close();
    }

    private MimeMultipart resolveImages(String result)
            throws MessagingException, Exception {
        MimeMultipart multipart = new MimeMultipart("related");
        Map<String, String> map = process(result, multipart);
        for (String name : map.keySet()) {
            String value = map.get(name);
            MimeBodyPart messageBodyPartWithIMG = new MimeBodyPart();
//			List<String> contentLanguage = new ArrayList<String>();
//			contentLanguage.add("da");
//			messageBodyPartWithIMG
//					.setContentLanguage((String[]) contentLanguage.toArray());
            URL url = new URL(value);
            if (url == null) {
                continue;
            }
            DataSource fds = new URLDataSource(url);
            messageBodyPartWithIMG.setDataHandler(new DataHandler(fds));
            messageBodyPartWithIMG.setHeader("Content-ID", "<" + name + ">");
            messageBodyPartWithIMG.setHeader("Content-Language", "da");
            messageBodyPartWithIMG.setHeader("Content-Type",
                    "text/html; charset=UTF-8");

            multipart.addBodyPart(messageBodyPartWithIMG);
        }

        return multipart;
    }

    private Map<String, String> process(String string, MimeMultipart multipart)
            throws Exception {
        Map<String, String> map = new HashMap<String, String>();
        int index = 0;
        Source source = new Source(string);
        source.setLogWriter(new OutputStreamWriter(System.out));
        source.fullSequentialParse();
        OutputDocument outputDocument = new OutputDocument(source);
        List startTags = source.findAllStartTags(HTMLElementName.IMG);
        for (Iterator i = startTags.iterator(); i.hasNext();) {
            StartTag imageElement = (StartTag) i.next();
            String src = imageElement.getAttributeValue("src");
            if (src == null) {
                continue;
            }
            String pictureName = "image" + index++;
            String htmlText = "<img src=\"cid:" + pictureName + "\">";
            outputDocument.replace(imageElement, htmlText);
            map.put(pictureName, src);
        }
        addhtmlToMail(multipart, outputDocument);
        return map;
    }

    private void addhtmlToMail(MimeMultipart multipart,
            OutputDocument outputDocument) throws IOException,
            MessagingException {
        BodyPart messageBodyPart = new MimeBodyPart();
        Writer writer = new StringWriter();
        outputDocument.writeTo(writer);
        String result = writer.toString();
        messageBodyPart.setContent(result, "text/html");
        messageBodyPart.setHeader("Content-Language", "da");
        messageBodyPart.setHeader("Content-Type", "text/html; charset=UTF-8");
        multipart.addBodyPart(messageBodyPart);
    }

    public void setFooterFile(String footerFile) {
        this.footerFile = footerFile;
    }

    public void setHeaderFile(String headerFile) {
        this.headerFile = headerFile;
    }

    public InternetAddress getFromAddress() {
        return fromAddress;
    }

    private void addAttachments(MimeMultipart multipart, String message) {
        try {
            MimeBodyPart mbp_file = null;
            mbp_file = new MimeBodyPart();
            mbp_file.setText(message, "UTF-8", "html");
            multipart.addBodyPart(mbp_file);
            if (attachments != null && !attachments.isEmpty()) {
                for (File file : attachments) {
                    mbp_file = new MimeBodyPart();
                    /*
                    FileDataSource fds = new FileDataSource(file);
                    mbp_file.setDataHandler(new DataHandler(fds));
                    mbp_file.setFileName(fds.getName());
                     */
                    mbp_file.attachFile(file);
                    multipart.addBodyPart(mbp_file);
                }
                // mbp_file.setContent(message, "text/html; charset=utf-8");
            }
        } catch (MessagingException ex) {
            java.util.logging.Logger.getLogger(HTMLMailSender.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception e) {
            //System.out.println("Attachment adding (HTMLMailSender:393) failed"+e.getMessage());
        }
    }

    private List<File> attachments;

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

    /**
     * @return the bcc
     */
    public String getBcc() {
        return bcc;
    }

    /**
     * @param bcc the bcc to set
     */
    public void setBcc(String bcc) {
        this.bcc = bcc;
    }

    /**
     * @return the cc
     */
    public String getCc() {
        return cc;
    }

    /**
     * @param cc the cc to set
     */
    public void setCc(String cc) {
        this.cc = cc;
    }

}
