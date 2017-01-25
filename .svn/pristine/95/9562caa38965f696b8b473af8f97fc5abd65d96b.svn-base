/*
 * Sms.java
 *
 * Created on 31. maj 2007, 09:03
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package util;

import com.asap.configuration.ConfigurationManager;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 *
 * @author Jens Rosenberg
 */
public class Sms {
    
    private String target;
    private String sender;
    private String message;
    private String status;
    private Date date;
    private String data;
    
    /** Creates a new instance of Sms */
    public Sms(String to,String from,String message) {
       try {
            this.target = URLEncoder.encode(to,"ISO-8859-1");
            this.sender = URLEncoder.encode(from,"ISO-8859-1");
            this.message = URLEncoder.encode(message,"ISO-8859-1");
        } catch (UnsupportedEncodingException ex) {
            ex.printStackTrace();
        }
        this.date = new Date();
        
        String username = ConfigurationManager.getParameter("rmgusername");
        String password = ConfigurationManager.getParameter("rmgpassword");
        
        data = "http://www.nemsms.dk/sms.asp?username="+username+"&password="+password;
    }
    
    public void send(){
        try {
            String params = "&sender="+sender+"&target="+target+"&message="+message;
            String url = data+params;
            System.out.println(url);
            status = URLUtil.getContentFrom(new URL(url));
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        System.out.println("SMS-send() - To:"+target+" From:"+sender+" Message:"+message+" Result:"+status);
    }
    
    public Date getDate(){
        return date;
    }
    
    public String getStatus(){
        return status;
    }
    
}
