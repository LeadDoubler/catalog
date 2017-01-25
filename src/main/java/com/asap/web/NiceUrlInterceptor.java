/*
 * NiceUrlInterceptor.java
 *
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package com.asap.web;

import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.controller.ExecutionContext;
import net.sourceforge.stripes.controller.Interceptor;
import net.sourceforge.stripes.controller.Intercepts;
import net.sourceforge.stripes.controller.LifecycleStage;

/**
 *
 * @author asapunov
 */
@Intercepts ({LifecycleStage.ActionBeanResolution})

public class NiceUrlInterceptor implements Interceptor {
    
    private static final String LIST_REGEX = "/[a-zA-Z]*/list";
    private static final String VIEW_FULL_REGEX = "/[a-zA-Z]*/\\d*/view";
    private static final String VIEW_REGEX = "/[a-zA-Z]*/\\d*";
    private static final String EDIT_REGEX = "/[a-zA-Z]*/\\d*/edit";
    private static final String ADD_REGEX = "/[a-zA-Z]*/add";
    
    public Resolution intercept (ExecutionContext ctx) throws Exception {
        String requestURI = ctx.getActionBeanContext ().getRequest ().getRequestURI ();
        System.out.println (requestURI);
        if (requestURI.matches (".*\\.action")) {
//            return new ForwardResolution (newPath);
            return ctx.proceed ();
        } else if (requestURI.matches (".*\\.jsp")) {
            return new ForwardResolution (requestURI);
//            return ctx.proceed ();
        } else {
            String contextPath =  ctx.getActionBeanContext ().getRequest ().getContextPath ();
            boolean startWith = requestURI.startsWith (contextPath);
            if  (startWith) {
                //Remove contextpath
                requestURI = requestURI.substring (contextPath.length ());
            }
            if (!requestURI.contains ("/") && requestURI.trim ().length () != 0) {
                String newPath = contextPath + "/page/Show.action?page=" + requestURI;
                return new ForwardResolution (newPath);
            } else {
                if (requestURI.matches (LIST_REGEX)) {
                    return new ForwardResolution (prepareList (contextPath, requestURI));
                } else if (requestURI.matches (VIEW_REGEX)) {
                    return new ForwardResolution (prepareView (contextPath, requestURI));
                } else if (requestURI.matches (VIEW_FULL_REGEX)) {
                    return new ForwardResolution (prepareFullView (contextPath, requestURI));
                } else if (requestURI.matches (EDIT_REGEX)) {
                    return new ForwardResolution (prepareEdit (contextPath, requestURI));
                } else if (requestURI.matches (ADD_REGEX)) {
                    return new ForwardResolution (prepareAdd (contextPath, requestURI));
                } else {
                    return ctx.proceed ();
                }
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
    
//     public void doFilter (
//            ServletRequest servletRequest,
//            ServletResponse servletResponse,
//            FilterChain filterChain) throws IOException, ServletException {
//
//        HttpServletRequest request = (HttpServletRequest)servletRequest;
//        HttpServletResponse response = (HttpServletResponse)servletResponse;
//
//        String URI = request.getRequestURI ();
//        String contextPath =  request.getContextPath ();
//        boolean startWith = URI.startsWith (contextPath);
//        if  (startWith) {
//            //Remove contextpath
//            URI = URI.substring (contextPath.length ());
//        }
//
//        if (!URI.contains ("/") && URI.trim ().length () != 0) {
//            //Forwards to page show. The URI is really a shortcut to a page.
//            String newPath = contextPath + "/page/Show.action?page=" + URI;
//            response.sendRedirect (newPath);
//        }
//        else {
//            if (URI.matches (LIST_REGEX)) {
//                response.sendRedirect (prepareList (contextPath, URI));
//            } else if (URI.matches (VIEW_REGEX)) {
//                response.sendRedirect (prepareView (contextPath, URI));
//            } else if (URI.matches (VIEW_FULL_REGEX)) {
//                response.sendRedirect (prepareFullView (contextPath, URI));
//            } else if (URI.matches (EDIT_REGEX)) {
//                response.sendRedirect (prepareEdit (contextPath, URI));
//            } else if (URI.matches (ADD_REGEX)) {
//                response.sendRedirect (prepareAdd (contextPath, URI));
//            } else {
//                filterChain.doFilter (request, response);
//            }
//        }
//
//    }
//
    
}
