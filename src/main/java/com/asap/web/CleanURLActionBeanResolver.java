/*
 * CleanURLActionBeanResolver.java
 *
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package com.asap.web;

import net.sourceforge.stripes.action.ActionBean;
import net.sourceforge.stripes.action.ActionBeanContext;
import net.sourceforge.stripes.controller.NameBasedActionResolver;
import net.sourceforge.stripes.exception.StripesServletException;

/**
 *
 * @author asapunov
 */
public class CleanURLActionBeanResolver extends NameBasedActionResolver {
    
    private static final String LIST_REGEX = "/[a-zA-Z]*/list";
    private static final String VIEW_FULL_REGEX = "/[a-zA-Z]*/\\d*/view";
    private static final String VIEW_REGEX = "/[a-zA-Z]*/\\d*";
    private static final String EDIT_REGEX = "/[a-zA-Z]*/\\d*/edit";
    private static final String ADD_REGEX = "/[a-zA-Z]*/add";
    
    /** Creates a new instance of CleanURLActionBeanResolver */
    public CleanURLActionBeanResolver () {
        super ();
        System.out.println ("CleanURLActionBeanResolver initialization");
    }
    
    @Override
    public ActionBean getActionBean (ActionBeanContext actionBeanContext, String requestURI) throws StripesServletException {
        System.out.println ("ActionBeanResolver");
        System.out.println (requestURI);
        if (requestURI.matches (".*\\.action")) {
            return super.getActionBean (actionBeanContext, requestURI);
        } else if (requestURI.matches (".*\\.jsp")) {
//            return new;
            return null;
        } else {
            String contextPath =  actionBeanContext.getRequest ().getContextPath ();
            boolean startWith = requestURI.startsWith (contextPath);
            if  (startWith) {
                requestURI = requestURI.substring (contextPath.length ());
            }
            if (!requestURI.contains ("/") && requestURI.trim ().length () != 0) {
                String newPath = contextPath + "/page/Show.action?page=" + requestURI;
                return super.getActionBean (actionBeanContext, newPath);
                
            } else {
                if (requestURI.matches (LIST_REGEX)) {
//                    actionBeanContext.setEventName ("list");
                    return super.getActionBean (actionBeanContext, prepareList (contextPath, requestURI));
                } else if (requestURI.matches (VIEW_REGEX)) {
                    return super.getActionBean (actionBeanContext,prepareView (contextPath, requestURI));
                } else if (requestURI.matches (VIEW_FULL_REGEX)) {
                    return super.getActionBean (actionBeanContext,prepareFullView (contextPath, requestURI));
                } else if (requestURI.matches (EDIT_REGEX)) {
                    return super.getActionBean (actionBeanContext,prepareEdit (contextPath, requestURI));
                } else if (requestURI.matches (ADD_REGEX)) {
                    return super.getActionBean (actionBeanContext,prepareAdd (contextPath, requestURI));
                } else {
                    return super.getActionBean (actionBeanContext, requestURI);
                }
            }
        }
    }
    
    private String prepareView (String context, String URI) {
        // url like /entity/id
        StringBuilder result = new StringBuilder ();
//        result.append (context);
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
//        result.append (context);
        int i = URI.indexOf ('/') + 1;
        int j = URI.indexOf ('/', i);
        
        if (i != j){
            String entity = URI.substring (i,j);
            String entityWithUp = entity.substring (0,1).toUpperCase () + entity.substring (1);
            result.append ('/');
            result.append (entity);
            result.append ('/');
            result.append (entityWithUp);
            result.append (".action");
        }
        return result.toString ();
    }
    
    private String prepareFullView (String context, String URI) {
        // url like /entity/id/view
        StringBuilder result = new StringBuilder ();
//        result.append (context);
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
//        result.append (context);
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
//        result.append (context);
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
