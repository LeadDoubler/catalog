package com.asap.security;

import com.asap.security.resourse.SecurityResource;
import java.lang.reflect.Method;

import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import net.sourceforge.stripes.action.ActionBean;
import net.sourceforge.stripes.action.ActionBeanContext;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.config.Configuration;
import net.sourceforge.stripes.controller.ActionResolver;
import net.sourceforge.stripes.controller.ExecutionContext;
import net.sourceforge.stripes.controller.Interceptor;
import net.sourceforge.stripes.controller.Intercepts;
import net.sourceforge.stripes.controller.LifecycleStage;
import net.sourceforge.stripes.exception.StripesServletException;

import com.asap.catalog.dao.User;
import com.asap.catalog.dao.manager.ResourceManager;
import com.asap.exception.AuthorizationException;
import com.asap.web.CatalogActionBeanContext;

@Intercepts (value = LifecycleStage.HandlerResolution)
public class SecurityInterceptor implements Interceptor {
    
	private static Logger logger = Logger.getLogger(SecurityInterceptor.class);
	
    public Resolution intercept (ExecutionContext ctx) throws Exception {
        long time = System.currentTimeMillis();
        final Configuration config = StripesSecurityFilter.getConfiguration ();
        final StripesSecurityManager securityManager = StripesSecurityFilter
                .getSecurityManager ();
        // final Resolution unauthorizedResolution = StripesSecurityFilter
        // .getUnauthorizedResolution();
        final String unauthorizedURL = StripesSecurityFilter
                .getUnauthorizedURL ();
        final ActionBeanContext context = ctx.getActionBeanContext ();
        final ActionResolver resolver = config.getActionResolver ();
        final Class beanClass = resolver.getActionBeanType (getRequestedPath (context.getRequest ()));
        final String targetURL = getRequestedPath (context.getRequest ());
        //System.out.println (getRequestedPath (context.getRequest ()));
        try {
            if (beanClass != null) {                
                if (verifyConfigurableManager (beanClass, securityManager,context)){
                    long time2 = System.currentTimeMillis();
                   // System.out.println("SecurityInterceptor - start = "+(time2-time));
                    return ctx.proceed ();
                }
                
                final Secure beanSecure = getSecureAnnotationFromClass (beanClass);
                
                if (beanSecure != null){
                    authenticateUser (beanSecure, securityManager, context);
                }
                String eventName = resolver.getEventName (beanClass, context);
                context.setEventName (eventName);
                final Method handler;
                if (eventName != null) {
                    handler = resolver.getHandler (beanClass, eventName);
                } else {
                    handler = resolver.getDefaultHandler (beanClass);
                    if (handler != null) {
                        context.setEventName (resolver.getHandledEvent (handler));
                    }
                }
                // Insist that we have a handler
                if (handler == null) {
                    throw new StripesServletException (
                            "No handler method found for request with  ActionBean ["
                            + beanClass.getName ()
                            + "] and eventName [ " + eventName + "]");
                }
                if (handler != null) {
                    
                    Secure methodSecure = (Secure) handler
                            .getAnnotation (Secure.class);
                    
                    if (methodSecure != null)
                        authenticateUser (methodSecure, securityManager, context);
                }
            }
        } catch (AuthorizationException ex) {
                if (unauthorizedURL != null) {
                    HttpServletRequest request = context.getRequest();
                    if( ! request.getServletPath().startsWith("/user/Login.action")) {
                    User user = null;
                    Object object = request.getSession().getAttribute("user");
                    if (object != null) {
                            if (object instanceof User) {
                                    user = (User) object;
                            }
                    }
                    if (user == null){
                        String params = "?";
                        for( Object key : request.getParameterMap().keySet() ){
                            String sKey = key.toString();
                            Object value =  request.getParameter(sKey);// request.getParameterMap().get(key);
                            params = params+sKey+"="+value+"&";
                        }
                        String targetUrl = request.getServletPath()+params;
                       // String targetUrl = URLEncoder.encode(servletRequest.getServletPath()+params , "UTF-8");
                        request.getSession().setAttribute("targetUrl",targetUrl);
                        String tURL = targetUrl.replaceAll("&","BLOBANDBLOB");
                        request.getSession().setAttribute("tURL",tURL);        
                        logger.debug ("Super - tURL = "+targetUrl);
                    }
                }
                return new ForwardResolution (unauthorizedURL + "?targetUrl="
                        + targetURL);
            }
            throw ex;
        }
        long time2 = System.currentTimeMillis();
        //System.out.println("SecurityInterceptor - beanClass = null - start = "+(time2-time));
                   
        return ctx.proceed ();
    }
    
    private boolean verifyConfigurableManager (Class beanClass,
            final StripesSecurityManager securityManager,
            ActionBeanContext context) throws AuthorizationException {
        final Map<String, SecurityResource> resourceConfiguration = ResourceManager.getResourceConfiguration();
        final String canonicalName = beanClass.getCanonicalName();
        final SecurityResource definition = resourceConfiguration.get(canonicalName);
        // TODO Auto-generated method stub
        if (definition == null){
            logger.debug("definition = "+definition+" for bean = "+beanClass);
            return true;
        }
        Role role = definition.getRole ();
        if (Role.GUEST.equals(role))
            return false;
        if (securityManager.isUserInRole (role, context)) {
            return true;
        }
        throw new AuthorizationException ();
    }
    
    
    /**
     *Checks:
     *1) If role == null - The method shouldn't have been used - throw exception
     *2) If currentUser != null - the method is allowed if the request parameter = the user logged in.
     *3) The user can proceed if the defined role for the method in the actionbean is below 
     *the role of the user logged in.
     **/
    private void authenticateUser (final Secure secure,
            final StripesSecurityManager securityManager,
            final ActionBeanContext context) throws ServletException {
        if (secure.role () == null) {
            throw new AuthorizationException ();
        }
        if (secure.currentUser ()) {
            try {
                User user = ((CatalogActionBeanContext) context).getUser ();
                if (user != null) {
                    String userID = context.getRequest ().getParameter ("user");
                    if (user.getId ().equals (new Long(userID))) {
                        return;
                    }
                }
            } catch (Exception e) {
                // ignore
            }
        }
        if (secure.role () != null) {
            Role role = secure.role ();
            if (securityManager.isUserInRole (role, context)) {
                return;
            }
            throw new AuthorizationException ();
        }
        
    }
    
    protected String getRequestedPath (HttpServletRequest request) {
        String servletPath = request.getServletPath ();
        String pathInfo = request.getPathInfo ();
        return (servletPath == null ? "" : servletPath)
        + (pathInfo == null ? "" : pathInfo);
    }
    
    private Secure getSecureAnnotationFromClass (final Class clazz) {
        
        Secure beanSecure = (Secure) clazz.getAnnotation (Secure.class);
        if (beanSecure == null) {
            
            Class parent = clazz.getSuperclass ();
            if (ActionBean.class.isAssignableFrom (parent))
                return getSecureAnnotationFromClass (parent);
            else
                return null;
        } else
            return beanSecure;
    }
}