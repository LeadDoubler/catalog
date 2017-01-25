/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.asap.configuration;
import java.io.File;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class ConfigurationContextListener implements ServletContextListener{

	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		System.out.println("ServletContextListener destroyed");
	}

        //Run this before web application is started
	@Override
	public void contextInitialized(ServletContextEvent servletContextEvent) {
		System.out.println("ServletContextListener started");
                ServletContext context = servletContextEvent.getServletContext();

                try{
                    new ExtendedConfiguration().initializeConfiguration(context.getRealPath("WEB-INF/conf"));
                }catch(Exception e){
                    e.printStackTrace();
                }
	}
}
