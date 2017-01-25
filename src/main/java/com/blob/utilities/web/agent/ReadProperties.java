/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.blob.utilities.web.agent;

import com.asap.configuration.Configuration;
import com.asap.configuration.XMLConfiguration;

import com.asap.web.CatalogActionBean;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Enumeration;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.StreamingResolution;

/**
 *
 * @author Morten
 */
public class ReadProperties extends CatalogActionBean{

    public Resolution run(){
        XMLConfiguration confReader = new XMLConfiguration();
        String realPath = this.getContext().getServletContext().getRealPath("/WEB-INF/conf");
        File dir = new File(realPath);
        System.out.println("Dir = " + dir.getPath());
        for (File file : dir.listFiles()) {
            System.out.println("File = " + file.getPath());
            if (file.getName().endsWith(".properties")) {
                try {
                    Properties props = new Properties();
                    props.load(new FileInputStream(file));
                    Enumeration enumeration = props.keys();
                    while (enumeration.hasMoreElements()){
                        String key = (String) enumeration.nextElement();
                        System.out.println("Adding parameter "+key);
                        Configuration.getInstance().getProps().put(key,props.getProperty(key).trim());
                        //ConfigurationManager.setParameter(key, props.getProperty(key));
                        System.out.println("Added parameter "+key);
                    }
                } catch (IOException ex) {
                    ex.printStackTrace();
                    Logger.getLogger(XMLConfiguration.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return new StreamingResolution("text/html","Read finished");
    }

}
