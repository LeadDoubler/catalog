package com.asap.core.cleanurl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.Principal;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.TreeMap;
import java.util.Vector;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletInputStream;
import javax.servlet.ServletRequest;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import net.sourceforge.stripes.action.FileBean;
import net.sourceforge.stripes.controller.StripesRequestWrapper;
import net.sourceforge.stripes.exception.StripesServletException;

/**
 * An wrapper for the StripesRequestWrapper that's used 
 * for clean URLs. 
 * Allows to obtain the request parameters from the request URI, as 
 * defined in the clean URL expression for the target action.
 * @see CleanUrlInterceptor
 */
public class CleanUrlRequestWrapper extends StripesRequestWrapper {

	private StripesRequestWrapper delegate;
	
	/** 
     * The <code>CleanUrl</code> object to be used for 
     * clean URLs.<br/>
     * If null, then the "regular" request handling is done. 
     * Otherwise, the cleanUrl object is used to decode the 
     * parameters from the request URI, as specified in the 
     * action bean's <code>UrlBinding</code> annotation.
     */
    private CleanUrl cleanUrl;
    
    /**
     * A map that "caches" the clean 
     * parameters that have already been 
     * extracted from the request URI. <br/>
     * Used only if <code>cleanUrl</code> is not null.
     */
    private HashMap<String, String[]> cleanParams = new HashMap<String, String[]>();

	public CleanUrlRequestWrapper(StripesRequestWrapper delegate, CleanUrl cleanUrl) throws StripesServletException {
		super((HttpServletRequest) delegate.getRequest());
		this.delegate = delegate;
		this.cleanUrl = cleanUrl;
	}

    public CleanUrlRequestWrapper(HttpServletRequest request, CleanUrl cleanUrl) throws StripesServletException {
        super(request);
        this.delegate = new StripesRequestWrapper(request);
        this.cleanUrl = cleanUrl;
    }
    

    /**
     * Fetches just the names of regular parameters and does not include file upload parameters. If
     * the request is multipart then the information is sourced from the parsed multipart object
     * otherwise it is just pulled out of the request in the usual manner. <br/>
     * A slight exception though : when using clean URLs (this.cleanUrl!=null), 
     * this method also returns the names of the parameters as specified 
     * in the CleanUrl object. 
     */
    @SuppressWarnings("unchecked")
    @Override
    public Enumeration<String> getParameterNames() {
        if ( delegate.isMultipart() ) {
            return delegate.getParameterNames();
        }
        else {
        	Enumeration<String> regularParamsEnum = delegate.getParameterNames();

    		// clean URL handling : add eventual parameters from 
    		// the request URI !
      
        	List<String> cleanParamNames = cleanUrl.getParamDefs();
			Vector<String> allParams = new Vector<String>();
			// add clean, params only if they have a value
			for (String cleanP : cleanParamNames) {
				String pVal;
				String[] pVals = cleanParams.get(cleanP);
				if (pVals!=null)
					pVal = pVals[0];
				else 
					pVal = cleanUrl.extractParamFromRequestURI(cleanP, getRequestURI());
				if (pVal!=null) {
					allParams.add(cleanP);
					// cache this parameter
					if (!cleanParams.containsKey(cleanP))
						cleanParams.put(cleanP, new String[]{pVal});
				}
			}
			// add regular params...
			while (regularParamsEnum.hasMoreElements()) {
				allParams.add(regularParamsEnum.nextElement());
			}
			return allParams.elements();
        }
    }
    
    /**
     * Returns all values sent in the request for a given parameter name. If the request is
     * multipart then the information is sourced from the parsed multipart object otherwise it is
     * just pulled out of the request in the usual manner.  Values are consistent with
     * HttpServletRequest.getParameterValues(String).  Values for file uploads cannot be retrieved
     * in this way (though parameters sent along with file uploads can). <br/>
     * In case you're using clean URLs (this.cleanUrl!=null), this method also returns the 
     * values pulled out from the request URI, based on the binding expression defined in the action 
     * bean. "Usual" request parameters take precedency on clean URLs ones.
     */
    @Override
    public String[] getParameterValues(String name) {
        if ( delegate.isMultipart() ) {
            return delegate.getParameterValues(name);
        }
        else {
            String[] values = delegate.getParameterValues(name);
            if (values==null) {

            	// try clean url param
            	// -------------------
            	
            	// look in params cache first...
            	values = cleanParams.get(name);
            	if (values==null) {
            		// param not cached yet, extract and cache
	        		String cleanUrlVal = 
	        			cleanUrl.extractParamFromRequestURI(name, getRequestURI());
	        		if (cleanUrlVal!=null)
	        			values = new String[]{ cleanUrlVal };
	        		if (!cleanParams.containsKey(name))
	        			cleanParams.put(name, values);
            	}
        	}
        	return values;
        }
    }
    
    /**
     * Returns a map of parameter name and values. <br/>
     * Also handles clean URLs if any, by fetching the param value from the 
     * request URI.
     */
    //@SuppressWarnings("unchecked")
//    @Override
    /*@Override
    public Map<String,String[]> getParameterMap() {
        if (delegate.isMultipart()) {
            return delegate.getParameterMap();
        }
        else {
    		// add clean url params to the map
        	Map<String, String[]> res = new TreeMap<String, String[]>();
    		List<String> cleanNames = cleanUrl.getParamDefs();
    		for (String curName : cleanNames) {
				String[] cleanVals = getParameterValues(curName);
				res.put(curName, cleanVals);
			}
    		// add regular stuff...
    		Map<String, String[]> regular = delegate.getParameterMap();
    		for (String regName : regular.keySet()) {
				String[] regVals = regular.get(regName);
				res.put(regName, regVals);
			}
    		return res;
        }
    }
     * */

	public boolean equals(Object obj) {
		return delegate.equals(obj);
	}

	public Object getAttribute(String arg0) {
		return delegate.getAttribute(arg0);
	}

	public Enumeration getAttributeNames() {
		return delegate.getAttributeNames();
	}

	public String getAuthType() {
		return delegate.getAuthType();
	}

	public String getCharacterEncoding() {
		return delegate.getCharacterEncoding();
	}

	public int getContentLength() {
		return delegate.getContentLength();
	}

	public String getContentType() {
		return delegate.getContentType();
	}

	public String getContextPath() {
		return delegate.getContextPath();
	}

	public Cookie[] getCookies() {
		return delegate.getCookies();
	}

	public long getDateHeader(String arg0) {
		return delegate.getDateHeader(arg0);
	}

	public Enumeration<String> getFileParameterNames() {
		return delegate.getFileParameterNames();
	}

	public FileBean getFileParameterValue(String name) {
		return delegate.getFileParameterValue(name);
	}

	public String getHeader(String arg0) {
		return delegate.getHeader(arg0);
	}

	public Enumeration getHeaderNames() {
		return delegate.getHeaderNames();
	}

	public Enumeration getHeaders(String arg0) {
		return delegate.getHeaders(arg0);
	}

	public ServletInputStream getInputStream() throws IOException {
		return delegate.getInputStream();
	}

	public int getIntHeader(String arg0) {
		return delegate.getIntHeader(arg0);
	}

//	public String getLocalAddr() {
//		return delegate.getLocalAddr();
//	}

	public Locale getLocale() {
		return delegate.getLocale();
	}

	public Enumeration getLocales() {
		return delegate.getLocales();
	}

//	public String getLocalName() {
//		return delegate.getLocalName();
//	}

//	public int getLocalPort() {
//		return delegate.getLocalPort();
//	}

	public String getMethod() {
		return delegate.getMethod();
	}

	public String getParameter(String name) {
        String param = delegate.getParameter(name);
        if (param==null) {
            String[] pVals = getParameterValues(name);
            if (pVals!=null && pVals.length>0)
                param = pVals[0];
        }
        return param;
	}

	public String getPathInfo() {
		return delegate.getPathInfo();
	}

	public String getPathTranslated() {
		return delegate.getPathTranslated();
	}

	public String getProtocol() {
		return delegate.getProtocol();
	}

	public String getQueryString() {
		return delegate.getQueryString();
	}

	public BufferedReader getReader() throws IOException {
		return delegate.getReader();
	}

	public String getRealPath(String arg0) {
		return delegate.getRealPath(arg0);
	}

	public String getRemoteAddr() {
		return delegate.getRemoteAddr();
	}

	public String getRemoteHost() {
		return delegate.getRemoteHost();
	}

//	public int getRemotePort() {
//		return delegate.getRemotePort();
//	}

	public String getRemoteUser() {
		return delegate.getRemoteUser();
	}

	public ServletRequest getRequest() {
		return delegate.getRequest();
	}

	public RequestDispatcher getRequestDispatcher(String arg0) {
		return delegate.getRequestDispatcher(arg0);
	}

	public String getRequestedSessionId() {
		return delegate.getRequestedSessionId();
	}

	public String getRequestURI() {
		return delegate.getRequestURI();
	}

	public StringBuffer getRequestURL() {
		return delegate.getRequestURL();
	}

	public String getScheme() {
		return delegate.getScheme();
	}

	public String getServerName() {
		return delegate.getServerName();
	}

	public int getServerPort() {
		return delegate.getServerPort();
	}

	public String getServletPath() {
		return delegate.getServletPath();
	}

	public HttpSession getSession() {
		return delegate.getSession();
	}

	public HttpSession getSession(boolean arg0) {
		return delegate.getSession(arg0);
	}

	public Principal getUserPrincipal() {
		return delegate.getUserPrincipal();
	}

	public int hashCode() {
		return delegate.hashCode();
	}

	public boolean isMultipart() {
		return delegate.isMultipart();
	}

	public boolean isRequestedSessionIdFromCookie() {
		return delegate.isRequestedSessionIdFromCookie();
	}

	public boolean isRequestedSessionIdFromUrl() {
		return delegate.isRequestedSessionIdFromUrl();
	}

	public boolean isRequestedSessionIdFromURL() {
		return delegate.isRequestedSessionIdFromURL();
	}

	public boolean isRequestedSessionIdValid() {
		return delegate.isRequestedSessionIdValid();
	}

	public boolean isSecure() {
		return delegate.isSecure();
	}

	public boolean isUserInRole(String arg0) {
		return delegate.isUserInRole(arg0);
	}

	public void removeAttribute(String arg0) {
		delegate.removeAttribute(arg0);
	}

	public void setAttribute(String arg0, Object arg1) {
		delegate.setAttribute(arg0, arg1);
	}

	public void setCharacterEncoding(String arg0) throws UnsupportedEncodingException {
		delegate.setCharacterEncoding(arg0);
	}

	public void setRequest(ServletRequest arg0) {
		delegate.setRequest(arg0);
	}

	public String toString() {
		return delegate.toString();
	}
    
    

    

}
