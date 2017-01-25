/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.asap.util;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

/**
 *
 * @author Morten
 */
public class TimeFilter implements Filter{

	private static Logger logger = Logger.getLogger(TimeFilter.class);
	private static long requestIdx = 1;
	
    public void init(FilterConfig filterConfig) throws ServletException {
        
    }

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        long reqId = requestIdx++;
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        String requestInfo = httpRequest.getRequestURI() +
        	((httpRequest.getQueryString() == null) ? "" : ("?" + httpRequest.getQueryString()));
        if (logger.isTraceEnabled()) {
        	logger.trace("#" + reqId +"# "+requestInfo);
        }
    	long t1 = System.currentTimeMillis();
        chain.doFilter(request, response);
        long t2 = System.currentTimeMillis();
        double sec = (((double)(t2-t1))/1000.0);
        if (logger.isTraceEnabled()) {
        	logger.trace("#" + reqId +"# "+requestInfo +" " + sec + " sec");
        }
        
//        if (logger.isDebugEnabled()) {
//			logger.debug("Time to complete request [" + ((HttpServletRequest)request).getRequestURI() + 
//					"]  and send back result = "+ (sec) + " sec");
//		}
    }

    public void destroy() {

    }

}
