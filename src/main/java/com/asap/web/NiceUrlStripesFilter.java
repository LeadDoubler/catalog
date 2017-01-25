/*
 * NiceUrlStripesFilter.java
 *
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package com.asap.web;

import com.asap.security.StripesSecurityFilter;
import com.asap.security.StripesSecurityManager;
import javax.servlet.FilterConfig;
import net.sourceforge.stripes.action.ActionBeanContext;
import net.sourceforge.stripes.config.BootstrapPropertyResolver;
import net.sourceforge.stripes.config.Configuration;
import com.asap.core.cleanurl.CleanUrl;
import com.asap.core.cleanurl.CleanUrlActionResolver;
import java.io.IOException;
import java.util.List;
import javax.servlet.Filter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import net.sourceforge.stripes.config.RuntimeConfiguration;
import net.sourceforge.stripes.controller.ActionResolver;
import net.sourceforge.stripes.controller.StripesFilter;
import javax.servlet.FilterChain;
import net.sourceforge.stripes.exception.StripesServletException;
import net.sourceforge.stripes.util.ReflectUtil;

/**
 *
 * @author asapunov
 */
public class NiceUrlStripesFilter extends StripesFilter {
    
    private static final String LIST_REGEX = "/[a-zA-Z]*/list";
    private static final String VIEW_FULL_REGEX = "/[a-zA-Z]*/\\d*/view";
    private static final String VIEW_REGEX = "/[a-zA-Z]*/\\d*";
    private static final String EDIT_REGEX = "/[a-zA-Z]*/\\d*/edit";
    private static final String ADD_REGEX = "/[a-zA-Z]*/add";
    
    
    Configuration configuration;
    public static final String RUN_CUSTOM_VALIDATION_WHEN_ERRORS =
            "Validation.InvokeValidateWhenErrorsExist";
    /** Creates a new instance of NiceUrlStripesFilter */
    public NiceUrlStripesFilter () {
    }
    
    public void doFilter (
            ServletRequest servletRequest,
            ServletResponse servletResponse,
            FilterChain filterChain) throws IOException, ServletException {
            long time = System.currentTimeMillis();
        HttpServletRequest request = (HttpServletRequest)servletRequest;
        HttpServletResponse response = (HttpServletResponse)servletResponse;
        
        String URI = request.getRequestURI ();
        String contextPath =  request.getContextPath ();
        boolean startWith = URI.startsWith (contextPath);
        if  (startWith) {
            //Remove contextpath
            URI = URI.substring (contextPath.length ());
        }
        
        if (!URI.contains ("/") && URI.trim ().length () != 0) {
            //Forwards to page show. The URI is really a shortcut to a page.
            String newPath = contextPath + "/page/Show.action?page=" + URI;
            response.sendRedirect (newPath);
        }
        else {
            if (URI.matches (LIST_REGEX)) {                 
                response.sendRedirect (prepareList (contextPath, URI));
            } else if (URI.matches (VIEW_REGEX)) {
                response.sendRedirect (prepareView (contextPath, URI));
            } else if (URI.matches (VIEW_FULL_REGEX)) {
                response.sendRedirect (prepareFullView (contextPath, URI));
            } else if (URI.matches (EDIT_REGEX)) {
                response.sendRedirect (prepareEdit (contextPath, URI));
            } else if (URI.matches (ADD_REGEX)) {
                response.sendRedirect (prepareAdd (contextPath, URI));
            } else {
                long time2 = System.currentTimeMillis();
                filterChain.doFilter (request, response);
                long time3 = System.currentTimeMillis();
                //System.out.println("starting niceUrl = "+(time2-time));
                //System.out.println("the rest = "+(time3-time2));
            }
        }
        
        
    }
    
    private String prepareView (String context, String URI) {
        // url like /entity/id
        StringBuilder result = new StringBuilder ();
        result.append (context);
        int i = URI.indexOf ('/') + 1;
        int j = URI.indexOf ('/', i);
        
        if (i != j){
            String entity = URI.substring (i,j);
            String entityWithUp = entity.substring (0,1).toUpperCase () + entity.substring (1);
            String id = URI.substring (j + 1, URI.length ());
            result.append ('/');
            result.append (entity);
            result.append ('/');
            result.append (entityWithUp);
            result.append (".action?view&");
            result.append (entity);
            result.append ("=");
            result.append (id);
        }
        return result.toString ();
    }
    
    private String prepareList (String context, String URI) {
        // url like /entity/list
        StringBuilder result = new StringBuilder ();
        result.append (context);
        int i = URI.indexOf ('/') + 1;
        int j = URI.indexOf ('/', i);
        
        if (i != j){
            String entity = URI.substring (i,j);
            String entityWithUp = entity.substring (0,1).toUpperCase () + entity.substring (1);
            result.append ('/');
            result.append (entity);
            result.append ('/');
            result.append (entityWithUp);
            result.append (".action?list");
        }
        return result.toString ();
    }
    
    private String prepareFullView (String context, String URI) {
                // url like /entity/id/view
        StringBuilder result = new StringBuilder ();
        result.append (context);
        int i = URI.indexOf ('/') + 1;
        int j = URI.indexOf ('/', i);
        int k = URI.indexOf ('/', j + 1);
        
        if (i != j){
            String entity = URI.substring (i,j);
            String entityWithUp = entity.substring (0,1).toUpperCase () + entity.substring (1);
            String id = URI.substring (j + 1, k + 1);
            result.append ('/');
            result.append (entity);
            result.append ('/');
            result.append (entityWithUp);
            result.append (".action?view&");
            result.append (entity);
            result.append ("=");
            result.append (id);
        }
        return result.toString ();
    }
    
    private String prepareEdit (String context, String URI) {
                        // url like /entity/id/edit
        StringBuilder result = new StringBuilder ();
        result.append (context);
        int i = URI.indexOf ('/') + 1;
        int j = URI.indexOf ('/', i);
        int k = URI.indexOf ('/', j + 1);
        
        if (i != j){
            String entity = URI.substring (i,j);
            String entityWithUp = entity.substring (0,1).toUpperCase () + entity.substring (1);
            String id = URI.substring (j + 1, k + 1);
            result.append ('/');
            result.append (entity);
            result.append ('/');
            result.append (entityWithUp);
            result.append (".action?edit&");
            result.append (entity);
            result.append ("=");
            result.append (id);
        }
        return result.toString ();
    }
    
    private String prepareAdd (String context, String URI) {
                // url like /entity/add
        StringBuilder result = new StringBuilder ();
        result.append (context);
        int i = URI.indexOf ('/') + 1;
        int j = URI.indexOf ('/', i);
        
        if (i != j){
            String entity = URI.substring (i,j);
            String entityWithUp = entity.substring (0,1).toUpperCase () + entity.substring (1);
            result.append ('/');
            result.append (entity);
            result.append ('/');
            result.append (entityWithUp);
            result.append (".action?add");
        }
        return result.toString ();
    }
    
}
