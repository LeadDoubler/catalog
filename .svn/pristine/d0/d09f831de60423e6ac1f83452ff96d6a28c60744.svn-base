/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.blob.web.contextListener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import org.apache.commons.logging.LogFactory;
import util.HibernateUtil;

/**
 *
 * @author mortenmatras
 */
public class CatalogServletContextListener implements ServletContextListener{

    public void contextInitialized(ServletContextEvent arg0) {
        System.out.println("System starts up - ServletContext "+arg0);
    }

    public void contextDestroyed(ServletContextEvent arg0) {
        System.out.println("System stops" + arg0);
        LogFactory.release(Thread.currentThread().getContextClassLoader());
        HibernateUtil.getSessionFactory().close();
    }

}
