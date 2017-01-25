/*
 * EmailActionBean.java
 *
 * Created on 15. oktober 2007, 11:59
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package com.asap.web.email;

import com.asap.configuration.ConfigurationManager;
import com.asap.web.CatalogActionBean;
import freemarker.template.Configuration;
import freemarker.template.DefaultObjectWrapper;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.util.Locale;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.Resolution;
import org.mortena.mail.Email;

/**
 *
 * @author mortenandersen
 */
public class EmailActionBean extends CatalogActionBean {
    
    private String classname;
    private String name;
    private String subject;
    private String from;
    private String text = "";
    private String template;
    
    public Resolution list(){
        return new ForwardResolution("/WEB-INF/view/email/list.jsp");
    }
    
    public Resolution test() {
        try {
            Email email;
            email = (Email) Class.forName(classname).newInstance();
            email.setTo(this.getContext().getUser().getEmail());
            email.send();
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        } catch (IllegalAccessException ex) {
            ex.printStackTrace();
        } catch (InstantiationException ex) {
            ex.printStackTrace();
        }
        return new ForwardResolution("/WEB-INF/view/email/send.jsp");
    }
    
      public Resolution testTemplate() {
        Email email = new Email();
        email.setTemplateName(template);
        email.setTo(this.getContext().getUser().getEmail());
        email.setSubject("Test email");
        email.send();
        return new ForwardResolution("/WEB-INF/view/email/send.jsp");
    }
      
    public Resolution editTemplate(){
        File file = new File(this.getContext().getServletContext().getRealPath(Email.TEMPLATEPATH+template+".ftl"));
        System.out.println("file = "+file);
        try {
            BufferedReader in = new BufferedReader(
                    new FileReader(file
                        )
                    );
        String str;
        while ((str = in.readLine()) != null) {
            text = text+str+"\n";
        }
        in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new ForwardResolution("/WEB-INF/view/email/edit.jsp");
    }
    
    public Resolution preview() {
        try {
            String path = this.getContext().getServletContext().getRealPath("emailTemplate/emailTemp.ftl");
            File tempFile = new File(path);
            tempFile.getParentFile().mkdirs();
            tempFile.createNewFile();
            try {
                BufferedWriter out = new BufferedWriter(new FileWriter( path ) );
                out.write(text);
                out.close();
                } catch (IOException e) {
            }
            Writer writer = new StringWriter();
            Configuration cfg = new Configuration();
            cfg.setObjectWrapper(new DefaultObjectWrapper());
            Template previewTemplate = cfg.getTemplate(path);
            cfg.setEncoding(new Locale("da_DK"), "UTF-8");
            String val = previewTemplate.getOutputEncoding();
            previewTemplate.setEncoding("UTF-8");
            previewTemplate.setOutputEncoding("UTF-8");
            previewTemplate.process(new Properties(), writer);
            preview = writer.toString();
            return new ForwardResolution("/WEB-INF/view/email/preview.jsp");
        } catch (TemplateException ex) {
            Logger.getLogger(EmailActionBean.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(EmailActionBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        return new ForwardResolution("Error.jsp");
    }
    
    private String preview;
    
    public Resolution edit(){
        from = ConfigurationManager.getParameter(name+".mail.from");
        subject = ConfigurationManager.getParameter(name+".subject");
        try {
            BufferedReader in = new BufferedReader(
                    new FileReader(
                        this.getContext().getServletContext().getRealPath(Email.TEMPLATEPATH+"/"+name+".ftl") 
                        )
                    );
        String str;
        while ((str = in.readLine()) != null) {
            text = text+str+"\n";
        }
        in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new ForwardResolution("/WEB-INF/view/email/edit.jsp");
    }
    
    public Resolution save(){
        ConfigurationManager.setParameter(name+".mail.from",from);
        ConfigurationManager.setParameter(name+".subject", subject);
        File fileToSave = new File( this.getContext().getServletContext().getRealPath(Email.TEMPLATEPATH+"/"+name+".ftl") ) ;
        try {
        BufferedWriter out = new BufferedWriter(new FileWriter(
                this.getContext().getServletContext().getRealPath(Email.TEMPLATEPATH+template+".ftl")
                ));
        out.write(text);
        out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new ForwardResolution("/WEB-INF/view/email/saved.jsp");
    }
    
    /** Creates a new instance of EmailActionBean */
    public EmailActionBean() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getClassname() {
        return classname;
    }

    public void setClassname(String classname) {
        this.classname = classname;
    }

    public String getTemplate() {
        return template;
    }

    public void setTemplate(String template) {
        this.template = template;
    }

    public String getPreview() {
        return preview;
    }

    public void setPreview(String preview) {
        this.preview = preview;
    }
    
}
