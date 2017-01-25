/*
 * XMLConfiguration.java
 *
 * Created on 15. august 2007, 14:14
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */
package com.asap.configuration;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Enumeration;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 *
 * @author mortenandersen
 */
public class XMLConfiguration extends HttpServlet {
	
	private static org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(XMLConfiguration.class);

    public Properties getProperties() {
        return Configuration.getInstance().getProps();

    }
    

    public void init() throws ServletException {
        System.out.println("XMLConfiguration - initiated");
        //Setting the rootPath property for later usage.
        Configuration.getInstance().getProps().put("rootPath",this.getServletContext().getRealPath(""));
        //logger.debug("SMTP = "+Configuration.getInstance().getProps().getProperty("smtp"));
        String path = this.getInitParameter("path");
        String realPath = this.getServletContext().getRealPath(path);
        File dir = new File(realPath);
        logger.debug("Dir = " + dir.getPath());
        for (File file : dir.listFiles()) {
            logger.debug("File = " + file.getPath());
            if (file.getName().endsWith(".properties")) {
                try {
                    Properties props = new Properties();
                    props.load(new FileInputStream(file));
                    Enumeration enumeration = props.keys();
                    while (enumeration.hasMoreElements()){
                        String key = (String) enumeration.nextElement();
                        logger.debug("Adding parameter "+key);
                        Configuration.getInstance().getProps().put(key,props.getProperty(key).trim());
                        //ConfigurationManager.setParameter(key, props.getProperty(key));
                        logger.debug("Added parameter "+key);
                    }
                } catch (IOException ex) {
                    ex.printStackTrace();
                    Logger.getLogger(XMLConfiguration.class.getName()).log(Level.SEVERE, null, ex);
                }
            } 
            else {
                DocumentBuilderFactory docBuilderFactory = DocumentBuilderFactory.newInstance();
                try {
                    DocumentBuilder docBuilder = docBuilderFactory.newDocumentBuilder();
                    Document doc = docBuilder.parse(file);
                    NodeList params = doc.getElementsByTagName("param");
                    int totalParam = params.getLength();
                    for (int s = 0; s < params.getLength(); s++) {
                        logger.debug("Element = " + params.item(s));
                        Element element = (Element) params.item(s);
                        logger.debug("Element = " + element.getNodeName());
                        String param = element.getAttribute("name");  //element.getElementsByTagName("param-name").item(0).getNodeValue();
                        String value = element.getAttribute("value");//element.getElementsByTagName("param-value").item(0).getNodeValue();
                        //ConfigurationManager.setParameter(param, value);
                    Configuration.getInstance().getProps().put(param,value);
        /*                if (ConfigurationManager.getParameter(param) == null){
                    HibernateUtil.getSessionFactory().openSession();
                    ConfigurationManager.setParameter(param, value);
                    HibernateUtil.getSessionFactory().getCurrentSession().close();
                    }
                     * */
                    }

                } catch (SAXException ex) {
                    ex.printStackTrace();
                } catch (IOException ex) {
                    ex.printStackTrace();
                } catch (ParserConfigurationException ex) {
                    ex.printStackTrace();
                }
                Configuration.getInstance().getProps().put("rootPath",this.getServletContext().getRealPath(""));
                util.HibernateUtil.getSessionFactory().getCurrentSession().getTransaction().commit();
            }
        }
        
    //logger.debug("SMTP = "+Configuration.getInstance().getProps().getProperty("smtp"));
    }

    /** Creates a new instance of XMLConfiguration */
    public XMLConfiguration() {
    }
}
