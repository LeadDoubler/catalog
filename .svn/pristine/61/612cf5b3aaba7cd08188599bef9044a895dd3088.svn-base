package com.asap.configuration;

import com.asap.json.JSONReader;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.hibernate.HibernateException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 *
 * @author asapunov
 */
public class ExtendedConfiguration extends HttpServlet {

    private static org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(ExtendedConfiguration.class);

    public ExtendedConfiguration() {
    }

    public Properties getProperties() {
        return Configuration.getInstance().getProps();

    }

    @Override
    public void init() throws ServletException {
        //Setting the rootPath property for later usage.
        Configuration.getInstance().getProps().put("rootPath", this.getServletContext().getRealPath(""));

        String path = this.getInitParameter("path");
        String realPath = this.getServletContext().getRealPath(path);
        initializeConfiguration(realPath);
    }

    public void initializeConfiguration(String realPath) throws HibernateException {
        File dir = new File(realPath);
        logger.debug("Dir = " + dir.getPath());
        for (File file : dir.listFiles()) {
            logger.debug("File = " + file.getPath());
            if (file.getName().endsWith(".properties")) {
                try {
                    Properties props = new Properties();
                    props.load(new FileInputStream(file));
                    Enumeration enumeration = props.keys();
                    while (enumeration.hasMoreElements()) {
                        String key = (String) enumeration.nextElement();
                        logger.debug("Adding parameter " + key);
                        Configuration.getInstance().getProps().put(key, props.getProperty(key).trim());
                        logger.debug("Added parameter " + key);
                    }
                } catch (IOException ex) {
                    Logger.getLogger(ExtendedConfiguration.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else if (file.getName().endsWith(".json")) {
                byte[] buffer = new byte[1024];
                BufferedInputStream bufferedInput;
                StringBuilder stringBuilder = new StringBuilder();
                try {
                    //TODO: Optimmistation needed
                    bufferedInput = new BufferedInputStream(new FileInputStream(file));
                    int bytesRead = 0;
                    while ((bytesRead = bufferedInput.read(buffer)) != -1) {
                        String chunk = new String(buffer, 0, bytesRead);
                        stringBuilder.append(chunk);
                    }
                    bufferedInput.close();
                } catch (FileNotFoundException ex) {
                    Logger.getLogger(ExtendedConfiguration.class.getName()).log(Level.SEVERE, null, ex);
                } catch (IOException ex) {
                    Logger.getLogger(ExtendedConfiguration.class.getName()).log(Level.SEVERE, null, ex);
                }
                JSONReader reader = new JSONReader();
                Object object = reader.read(stringBuilder.toString());
                if (object instanceof HashMap) {
                    Map map = (HashMap) object;
                    Configuration.getInstance().getProps().put(map.get("title"), map.get("configuration"));
                    Logger.getLogger(ExtendedConfiguration.class.getName()).log(Level.SEVERE, "Configuration loaded: " + map.get("title"));
                }
            } else {
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
                        String param = element.getAttribute("name");
                        String value = element.getAttribute("value");
                        Configuration.getInstance().getProps().put(param, value);
                    }
                } catch (SAXException ex) {
                    Logger.getLogger(ExtendedConfiguration.class.getName()).log(Level.SEVERE, null, ex);
                } catch (IOException ex) {
                    Logger.getLogger(ExtendedConfiguration.class.getName()).log(Level.SEVERE, null, ex);
                } catch (ParserConfigurationException ex) {
                    Logger.getLogger(ExtendedConfiguration.class.getName()).log(Level.SEVERE, null, ex);
                }
                Configuration.getInstance().getProps().put("rootPath", this.getServletContext().getRealPath(""));
                util.HibernateUtil.getSessionFactory().getCurrentSession().getTransaction().commit();
            }
        }
        //logger.debug("SMTP = "+Configuration.getInstance().getProps().getProperty("smtp"));
        //logger.debug("SMTP = "+Configuration.getInstance().getProps().getProperty("smtp"));
    }
}
