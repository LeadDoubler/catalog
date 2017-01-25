/*
 * EmailManager.java
 *
 * Created on 15. oktober 2007, 09:50
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package org.mortena.mail;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author mortenandersen
 */
public class EmailManager {
    
    private List<Email> registeredEmails; 
    
    private List<File> templates;
    
    private static EmailManager emailManager;
    
    /** Creates a new instance of EmailManager */
    private EmailManager() {
        registeredEmails = new ArrayList();
    }
    
    public static EmailManager getInstance(){
        if (emailManager == null ){
            emailManager = new EmailManager();
        }
        return emailManager;
    }

    public List<Email> getRegisteredEmails() {
        return registeredEmails;
    }

    public void setRegisteredEmails(List<Email> registeredEmails) {
        this.registeredEmails = registeredEmails;
    }

    public List<File> getTemplates() {
        return templates;
    }

    public void setTemplates(List<File> templates) {
        this.templates = templates;
    }

    
}
