/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package util.exceptionHandling;

import com.asap.util.HTMLMailSender;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.internet.AddressException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.exception.AutoExceptionHandler;

/**
 *
 * @author mortenmatras
 */
public class DefaultExceptionHandler implements AutoExceptionHandler{
    
    public Resolution handle(Exception exception, HttpServletRequest req, HttpServletResponse res){
        try {
            exception.printStackTrace();
            StringBuffer sb = new StringBuffer();
            sb.append("request = " + req.getServletPath() + req.getQueryString() + "\n\n");
            for (StackTraceElement element : exception.getStackTrace()) {
                sb.append(element.toString() + "\n");
            }
            HTMLMailSender sender = new HTMLMailSender();
            sender.setSubject("Error");
            sender.setFromAddress("kodekongen-error@gmail.com");
            sender.setToAddress("kodekongen-errorreceived@gmail.com");
            sender.setMessageText(sb.toString());
            req.setAttribute("errors", sb.toString());
            sender.sendMail();
        } catch (AddressException ex) {
            Logger.getLogger(DefaultExceptionHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
        return new ForwardResolution("/Error.jsp");
    }
}
