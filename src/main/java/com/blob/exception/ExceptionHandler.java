/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.blob.exception;

import com.asap.configuration.Configuration;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.exception.AutoExceptionHandler;
import org.mortena.mail.Email;

/**
 *
 * @author Morten
 */
public class ExceptionHandler implements AutoExceptionHandler{

    public Resolution handle(Exception exception, HttpServletRequest req, HttpServletResponse res){
        String send = Configuration.getInstance().getProps().getProperty("exception.sendException");
        if ("yes".equals(send)){
            try {
                File file = File.createTempFile("Exception_" + new Date().getTime(), ".txt");
                FileWriter fileWriter = new FileWriter(file);
                PrintWriter pw = new PrintWriter(fileWriter);
                exception.printStackTrace(pw);
                pw.flush();
                pw.close();
                Email email = new Email();
                email.setTo("jonasjensen+bugreport@leaddoubler.com");
                email.setProperty("request", req);
                email.setFrom("no-reply@leaddoubler.com");
                email.setTemplateName("BugReport");
                email.setSubject("Bug report");
                email.setProperty("response", res);
                email.setProperty("exception", exception);
                email.setProperty("browser", req.getHeader("User-Agent"));
                email.addAttachment(file);
                email.send();
            } catch (IOException ex) {
                Logger.getLogger(ExceptionHandler.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        else{
            exception.printStackTrace();
        }
        return new ForwardResolution("/errorPages/new.jsp");
    }

}
