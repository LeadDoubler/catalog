/*
 * ExceptionHandler.java
 *
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package com.asap.web;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.Resolution;

import com.asap.util.HTMLMailSender;

import freemarker.template.Configuration;
import freemarker.template.Template;
import net.sourceforge.stripes.exception.ExceptionHandler;

/**
 * 
 * @author asapunov
 */
public class MailExceptionHandler implements ExceptionHandler {

	/** Creates a new instance of ExceptionHandler */
	public MailExceptionHandler() {
	}
        
       

	public void handle(Throwable exception, HttpServletRequest req,
			HttpServletResponse res) {
		try {
                    System.out.println("Exception thrown: "+exception.getMessage());
                    exception.printStackTrace();
			Configuration cfg = new Configuration();
			cfg.setServletContextForTemplateLoading(req.getSession()
					.getServletContext(), "template");
			String requestURI = req.getRequestURI();
			Map parameterMap = req.getParameterMap();
			StringWriter writer = new StringWriter();
			PrintWriter printWriter = new PrintWriter(writer);
			exception.printStackTrace(printWriter);

			Map root = new HashMap();

			root.put("requestURI", requestURI);
			root.put("parameterMap", parameterMap);
			root.put("stackTrace", writer.getBuffer().toString());

			Template t = cfg.getTemplate("error.ftl");

			HTMLMailSender htmlMailSender = new HTMLMailSender();
			htmlMailSender.setTemplate(t);
			htmlMailSender.setDataModel(root);
			htmlMailSender.setFromAddress("some@blobcom.com");
			htmlMailSender.setToAddress("bughandler@blobcom.com");
			htmlMailSender.setSubject("Exception notification");

			htmlMailSender.sendMail();

		} catch (Exception e) {
			// ignored
		}
		//return new ForwardResolution("/error.jsp");
	}

    public void init(net.sourceforge.stripes.config.Configuration configuration) throws Exception {
    }
}
