package org.mortena.mail;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Iterator;
import java.util.Properties;
import java.util.Vector;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.activation.DataHandler;
import javax.activation.URLDataSource;
import javax.mail.BodyPart;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.internet.ParseException;
import javax.servlet.http.HttpServletRequest;

import util.URLUtil;

import com.asap.util.HTMLMailSender;

/**
 * Created by IntelliJ IDEA. User: mortenandersen Date: 2007-03-17 Time:
 * 11:15:12 To change this template use File | Settings | File Templates.
 */
public class HTMLMailController {

	static String host = "localhost";

	public static void sendHTMLMail(InternetAddress from,
			InternetAddress recipient, String subject, String content,
			String link) throws MessagingException {
		String linkstyle = "";// "style=\"color:#8CAC2B;\" ";
		MimeMessage message = createMessage(from, recipient, subject, content,
				link, linkstyle);
		try {
			Transport.send(message);
		} catch (ParseException pe) {
			StackTraceElement[] s = pe.getStackTrace();
			for (int j = 0; j < s.length; j++) {
				StackTraceElement stackTraceElement = s[j];
				// Notifier.log.warn(stackTraceElement.getFileName()+" line =
				// "+stackTraceElement.getLineNumber());
			}
		}
		// Notifier.log.info("Message has been sent to
		// "+recipient.getAddress());
	}

	public static void sendHTMLMessageViaJSP(HttpServletRequest request,
			String from, String to, String file, String subject,
			String paramList) {
		String link = null;
		link = URLUtil.getLink(request);
		//link = "http://localhost:8080/";
		sendHTMail(from, to, link, file, paramList, subject);
	}

	public static void sendHTMail(String from,String  to,String  link,String  file,String  paramList,String  subject){
        try {
            InternetAddress fromA = new InternetAddress(from);
            // System.out.println("MAILMAIL:"+from);
            InternetAddress toA = new InternetAddress(to);
            // System.out.println("MAILMAIL:"+to);
            // Notifier.log.info("Getting content from: "+new
			// URL(link+file+paramList));

            System.out.println("Getting content from: "+link+file+paramList);
            String content = URLUtil.getContentFrom(new URL(link+file+paramList));
            
            HTMLMailSender sender = new HTMLMailSender();
            sender.setToAddress(to);
            sender.setFromAddress(from);
            sender.setSubject(subject);
            sender.setMessageText(content);

            sender.sendMail();
            
            /*
			 * MimeMessage message =
			 * createMessage(fromA,toA,subject,content,link,"");
			 * 
			 * 
			 * Transport.send(message);
			 */
        } catch (AddressException e) {
            e.printStackTrace();  // To change body of catch statement use
									// File | Settings | File Templates.
        } catch (MessagingException e) {
            e.printStackTrace();  // To change body of catch statement use
									// File | Settings | File Templates.
        } catch (MalformedURLException e) {
            e.printStackTrace();  // To change body of catch statement use
									// File | Settings | File Templates.
        } catch (IOException e) {
            e.printStackTrace();  // To change body of catch statement use
									// File | Settings | File Templates.
        }
    }

	public static MimeMessage createMessage(InternetAddress from,
			InternetAddress recipient, String subject, String content,
			String link, String linkstyle) throws MessagingException {
		Properties props = System.getProperties();
		props.put("mail.smtp.host", host);
		Session session = Session.getDefaultInstance(props, null);
		// Define message
		MimeMessage message = new MimeMessage(session);
		// Set the from address
		message.setFrom(from);
		// Set the to address
		message.addRecipient(javax.mail.Message.RecipientType.TO, recipient);
		// message.setSubject(subject, "UTF-8");
		message.setSubject(subject, "ISO8859-1");

		// This HTML mail have to 2 part, the BODY and the embedded image
		//
		// MimeMultipart multipart = new MimeMultipart("alternative");
		// first part (the html)
		// BodyPart bodyPart = new MimeBodyPart();
		MimeMultipart messagePart = new MimeMultipart("related");

		// Uncomment to send using MultiPart...
		// message.setContent(messagePart);
		// bodyPart.setContent(messagePart);
		// multipart.addBodyPart(bodyPart);
		// SAXBuilder builder = new SAXBuilder();
		// try {
		String html = "<html><body>" + content + "</body></html>";
		// Notifier.log.info("Document = \n\n"+html+"\n\n");
		Vector v = new Vector();
		// Document doc = new XMLParser().parse(html);//builder.build(new
		// StringReader(html));
		// log.info("Document has been built");
		html = embedImages(html, v, link, messagePart);
		html = replaceDanishLetters(html);
		html = Pattern.compile("<a ", Pattern.CASE_INSENSITIVE).matcher(html)
				.replaceAll("<a " + linkstyle);
		MimeBodyPart htmlPart = new MimeBodyPart();
		htmlPart.setContent(html, "text/html");
		messagePart.addBodyPart(htmlPart);

		for (Iterator iterator = v.iterator(); iterator.hasNext();) {
			BodyPart bodyPart = (BodyPart) iterator.next();
			messagePart.addBodyPart(bodyPart);
		}
		message.setContent(html, "text/html");
		return message;
	}

	private static String embedImages(String content, Vector v, String link,
			MimeMultipart messagePart) throws MessagingException {
            if(content == null){
                return content;
            }
		String html;
		Pattern imgP = Pattern.compile("src=\"[^\"]+");
		Matcher myMatcher = imgP.matcher(content);
		StringBuffer myStringBuffer = new StringBuffer();

		int i = 0;
		while (myMatcher.find()) {
			String src = myMatcher.group().substring(5);
			// Notifier.log.info(src);
			String srcReplacement = src;
			if (src.startsWith("/")) {
				srcReplacement = link + src;
			}
			// srcReplacement = embedImage(v, src, link, i, messagePart);
			// if (checkIfThisMatchShouldBeReplaced()) {
			myMatcher.appendReplacement(myStringBuffer, "src=\""
					+ srcReplacement);
			// }
			i++;
		}
		myMatcher.appendTail(myStringBuffer);
		html = myStringBuffer.toString();
		return html;
	}

	private static BodyPart getImageBodyPart(String src, String link, int i,
			MimeMultipart multipart) throws MessagingException {
		if (src.startsWith("/")) {
			src = link + src;
		}
		int lastSlash = src.lastIndexOf("/");
		String beforeLastSlash = src.substring(0, lastSlash);
		String afterLastSlash = src.substring(lastSlash + 1, src.length());
		// Notifier.log.info("before = "+beforeLastSlash+" after =
		// "+afterLastSlash);
		String afterSlashNoSpaces = afterLastSlash.replaceAll(" ", "%20");
		src = beforeLastSlash + "/" + afterSlashNoSpaces;
		BodyPart imageBodyPart = getImageBodyPart(src);
		imageBodyPart.setHeader("Content-ID", "<image-" + i + ">");
		return imageBodyPart;
	}

	private static BodyPart getImageBodyPart(String src)
			throws MessagingException {
		BodyPart imageBodyPart = new MimeBodyPart();
		URL url = null;
		try {
			url = new URL(src);
		} catch (MalformedURLException e) {
			e.printStackTrace(); // To change body of catch statement use
									// File | Settings | File Templates.
		}
		URLDataSource image = new URLDataSource(url);
		imageBodyPart.setDataHandler(new DataHandler(image));
		return imageBodyPart;
	}

	/**
	 * TODO Remove this, use UTF-8 to send mail messages
	 */
	public static String replaceDanishLetters(String html) {
		return html;
	}
}
