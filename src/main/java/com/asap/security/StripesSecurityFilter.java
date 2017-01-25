package com.asap.security;

import com.asap.catalog.dao.User;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.Iterator;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.config.BootstrapPropertyResolver;
import net.sourceforge.stripes.controller.ExecutionContext;
import net.sourceforge.stripes.controller.Interceptor;
import net.sourceforge.stripes.controller.LifecycleStage;
import net.sourceforge.stripes.controller.StripesFilter;
import net.sourceforge.stripes.exception.StripesServletException;
import net.sourceforge.stripes.util.Log;
import net.sourceforge.stripes.util.ReflectUtil;

public class StripesSecurityFilter extends StripesFilter {

    /**
     * Key used to lookup the name of the SecurityManager class used to
     * configure Stripes security. This class must implement
     * StipesSecurityManager.
     */
    public static final String SECURITY_MANAGER = "SecurityManager.Class";

    /**
     * Key used to lookup the url to be used to forward if the user is
     * unauthorized.
     */
    public static final String UNAUTHORIZED_RESOLUTION = "UnauthorizedResolutionURL";

    private static Log log = Log.getInstance(StripesSecurityFilter.class);

    private static StripesSecurityManager securityManager;

    private static Resolution unauthorizedResolution;

    private static String unauthorizedURL = "/user/Login.action?init";

    public static StripesSecurityManager getSecurityManager() {
        return securityManager;
    }

    public static Resolution getUnauthorizedResolution() {
        return unauthorizedResolution;
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        BootstrapPropertyResolver bootstrap = new BootstrapPropertyResolver(
                filterConfig);
        String securityMangerClassName = bootstrap
                .getProperty(SECURITY_MANAGER);

        //unauthorizedURL = bootstrap.getProperty (UNAUTHORIZED_RESOLUTION);
        // Set up the Security Manager
        if (securityMangerClassName != null) {
            try {
                Class clazz = ReflectUtil.findClass(securityMangerClassName);
                this.securityManager = (StripesSecurityManager) clazz
                        .newInstance();
            } catch (Exception e) {
                log
                        .fatal(
                                e,
                                "Could not instantiate specified SecurityManager. Class name specified was ",
                                "[", securityMangerClassName, "].");
                throw new StripesServletException(
                        "Could not instantiate specified Configuration. "
                        + "Class name specified was ["
                        + securityMangerClassName + "].", e);
            }
        } else {
            log
                    .fatal(
                            "Could not instantiate specified SecurityManager. Class name specified was ",
                            "[", securityMangerClassName, "].");
            throw new StripesServletException(
                    "Could not instantiate specified Configuration. "
                    + "Class name specified was ["
                    + securityMangerClassName + "].");

        }

        if (unauthorizedURL != null) {
            unauthorizedResolution = new ForwardResolution(unauthorizedURL);
        } else {
            log.warn("Could not find unauthorizedURL ", "URL specified was [",
                    unauthorizedURL, "].");
            unauthorizedResolution = null;

        }
        super.init(filterConfig);
    }

    public static String getUnauthorizedURL() {
        return unauthorizedURL;
    }

    public static void setUnauthorizedURL(String unauthorizedURL) {
        StripesSecurityFilter.unauthorizedURL = unauthorizedURL;
    }

    public void doFilter(ServletRequest request,
            ServletResponse response,
            FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest servletRequest = (HttpServletRequest) request;
        HttpServletResponse servletResponse = (HttpServletResponse) response;
        long time = System.currentTimeMillis();
        //System.out.println (servletRequest.getRequestURI ());
        //System.out.println ("StripesSecurityFilter");
        try {
            if (servletRequest.getRequestURI().endsWith(".jsp")) {
                chain.doFilter(request, response);
            } else {
                long time2 = System.currentTimeMillis();
                //System.out.println ("dofilter continues unauthorizedURL = "+this.getUnauthorizedURL());
                super.doFilter(request, response, chain);
                long time3 = System.currentTimeMillis();
                // System.out.println("starting StripesSecurityFilter = "+(time2-time));
                //System.out.println("the rest = "+(time3-time2));

                //System.out.println("Super.doFilter is finished");
            }
        } catch (Exception ex) {
        }
//        chain.doFilter (request, response);
    }

}
