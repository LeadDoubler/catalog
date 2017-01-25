/*
 * FlintboxPatentbors.java
 *
 * Created on 22. maj 2007, 09:21
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package com.asap.web;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.net.Socket;
import java.net.URL;
import javax.net.SocketFactory;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLSocketFactory;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.StreamingResolution;
import util.URLUtil;

/**
 *
 * @author Jens Rosenberg
 */
public class FlintboxPatentbors extends CatalogActionBean{
    
    private String uid = "patentborsadmin";// - valid flintbox Location Manager account username    
    private String pwd = "password";// - valid password
    private String contactemail = "ton@adm.dtu.dk";// - email address of Tech Manager account

    private String action;//- add- update- publish- unpublish

    private String title;// - 200 char
    private String shortdescription;// - 2000 char
    private String patent;// - no | yes | pending
    private String patentnumber;// - 30 char
    private String filenumber;// - 50 char
    private String website;// - 256 char
    private String description;// - text
    private String potentialapplications;// - text
    private String additionalinfo;// - text
    
    
    /** Creates a new instance of FlintboxPatentbors */
    public FlintboxPatentbors() {
        title = "patentbors project";
        shortdescription = "project for testing";
        patent = "yes";
        patentnumber = "patent 123";
        filenumber = "file 456";
        website = "www.test.com";
        description = "project description";
        potentialapplications = "this project has potential";
        additionalinfo = "additional information";
    }
    
    @DefaultHandler
    public Resolution add() {
        
        action = "add";
        String send = "https://patentbors.ripe.ca/technology_inteum.asp?uid="+uid+"&pwd="+pwd+"&action="+action+"&contactemail="+contactemail+"&title="+title+"&shortdescription="+shortdescription+"&patent="+patent+"&patentnumber="+patentnumber+"&filenumber="+filenumber+"&website="+website+"&description="+description+"&potentialapplications="+potentialapplications+"&additionalinfo="+additionalinfo;
        String result = "";
        
        try {
            int port = 443;
            String hostname = "hostname";
            SocketFactory socketFactory = SSLSocketFactory.getDefault();
            Socket socket = socketFactory.createSocket(send, port);

            // Create streams to securely send and receive data to the server
            socket.getInputStream();
            InputStream in = socket.getInputStream();
        
            // Read from in and write to out...
            int input;
            while ((input = in.read()) == -1){
                result += input; 
            }

            // Close the socket
            in.close();
        } catch(IOException e) {
        }
        
        System.out.println("Patentbors:"+result);
        return new StreamingResolution("text/html",result);
    }
    
}
