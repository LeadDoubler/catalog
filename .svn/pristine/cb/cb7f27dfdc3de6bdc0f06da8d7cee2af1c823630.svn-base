/*
 * EmailInitiator.java
 *
 * Created on 15. oktober 2007, 09:47
 */

package org.mortena.mail;

import java.io.File;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.Vector;

import javax.servlet.*;
import javax.servlet.http.*;
import net.sourceforge.stripes.util.ResolverUtil;

/**
 *
 * @author mortenandersen
 * @version
 */
public class EmailInitiator extends HttpServlet {
    
     public void init() throws ServletException{   
         EmailManager manager = EmailManager.getInstance();
         File dir = new File(this.getServletContext().getRealPath(Email.TEMPLATEPATH));
         for (File file : dir.listFiles()){
             if (manager.getTemplates() == null){
                 manager.setTemplates(new ArrayList());
             }
             manager.getTemplates().add(file);
             //System.out.println("file : "+file+" added to manager");
         }
     /*    ResolverUtil<Email> resolver = new ResolverUtil<Email>();
         List<String> l = new Vector();
         l.add("WEB-INF/classes");
         resolver.setLocationFilters(l);
         resolver.loadImplementationsFromContextClassloader(Email.class);
         
         Set<Class<? extends Email>> emails = resolver.getClasses();
         for (Class emailClass : emails) {
			// aConf.addAnnotatedClass(component);
			if (!emailClass.isInterface() && !Modifier.isAbstract(emailClass.getModifiers())) {
                            if ( ! emailClass.equals(Email.class) ) {
                                System.out.println("Adding email = " + emailClass.getName());
				try {
					Email email = (Email) emailClass.newInstance();
                                        email.setDirPath(this.getServletContext().getRealPath(""));
                                        manager.getRegisteredEmails().add(email);
					//scheduleAgentJob(agent);
				} catch (Exception e) {
					e.printStackTrace();
				}
                            }
			}
		}*/
     }
}
