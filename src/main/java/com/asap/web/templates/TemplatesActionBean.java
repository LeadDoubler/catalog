/*
 * TemplatesActionBean.java
 *
 * Created on 11. oktober 2007, 13:10
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */
package com.asap.web.templates;

import com.asap.security.Role;
import com.asap.security.Secure;
import com.asap.web.CatalogActionBean;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.RedirectResolution;
import net.sourceforge.stripes.action.Resolution;

/**
 *
 * @author mortenandersen
 */
public class TemplatesActionBean extends CatalogActionBean {

    /**
     * Creates a new instance of TemplatesActionBean
     */
    public TemplatesActionBean() {
    }

    private String file;
    private String text = "";

    @Secure(role = Role.MODERATOR)
    @DefaultHandler
    public Resolution edit() {
        try {
            BufferedReader in = new BufferedReader(
                    new FileReader(
                            this.getContext().getServletContext().getRealPath(file)
                    )
            );
            String str;
            while ((str = in.readLine()) != null) {
                text = text + str + "\n";
            }
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new ForwardResolution("/templates/edit.jsp");
    }

    @Secure(role = Role.MODERATOR)
    public Resolution save() {
        File fileToSave = new File(this.getContext().getServletContext().getRealPath(file));
        try {
            BufferedWriter out = new BufferedWriter(new FileWriter(
                    this.getContext().getServletContext().getRealPath(file)
            ));
            out.write(text);
            out.close();
        } catch (IOException e) {
        }
        return new RedirectResolution("/templates/saved.jsp?file=" + file);
    }

    public String getFile() {
        return file;
    }

    public void setFile(String file) {
        this.file = file;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

}
