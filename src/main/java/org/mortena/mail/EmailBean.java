/*
 * EmailBean.java
 *
 * Created on 15. oktober 2007, 14:08
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
public class EmailBean {
    
    /** Creates a new instance of EmailBean */
    public EmailBean() {
    }
    
    public List<Email> getEmails(){
        return EmailManager.getInstance().getRegisteredEmails();
    }
    
      public List<File> getTemplates(){
        return EmailManager.getInstance().getTemplates();
    }
      
      public List<String> getFileNamesWithoutFTL(){
          List<String> filenames = new ArrayList();
          for ( File file : EmailManager.getInstance().getTemplates() ){
            filenames.add(file.getName().substring(0,file.getName().lastIndexOf(".")));    
          }
          System.out.println("filenames "+filenames);
          return filenames;
      }
}
