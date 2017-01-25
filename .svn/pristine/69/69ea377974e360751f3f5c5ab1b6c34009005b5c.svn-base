package com.asap.security;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.HashSet;
import java.util.Set;

import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.controller.ExecutionContext;
import net.sourceforge.stripes.controller.StripesFilter;

/**
 * A simplistic security filter for Bugzooky that ensures that the user is
 * logged in before allowing access to any secured pages.
 * 
 * @author Tim Fennell
 */
public class SecurityFilter extends StripesFilter {
	private static Set<String> publicUrls = new HashSet<String>();

	static {
		publicUrls.add("/Login.jsp");
		publicUrls.add("/Register.jsp");
		publicUrls.add("/Register2.jsp");
		publicUrls.add("/Login.action");
		publicUrls.add("/Register.action");
		publicUrls.add("/ViewResource.action");
	}

	public Resolution intercept(ExecutionContext ctx) throws Exception {
		//System.out.println("Before " + ctx.getLifecycleStage());
		Resolution resolution = ctx.proceed();
		//System.out.println("After " + ctx.getLifecycleStage());
		return resolution;
	}

	/** Does nothing. */
	public void init(FilterConfig filterConfig) throws ServletException {
	}

	public void doFilter(ServletRequest servletRequest,
			ServletResponse servletResponse, FilterChain filterChain)
			throws IOException, ServletException {
		//System.out.println("SecurityFilter");

		HttpServletRequest request = (HttpServletRequest) servletRequest;
		HttpServletResponse response = (HttpServletResponse) servletResponse;
		request.getSession().getServletContext();
		if (request.getSession().getAttribute("user") != null) {
			filterChain.doFilter(request, response);
		} else if (isPublicResource(request)) {
			filterChain.doFilter(request, response);
		} else {
			// Redirect the user to the login page, noting where they were
			// coming from
                    String params = "BLOBANDBLOB";
                    for( Object key : request.getParameterMap().keySet() ){
                        Object value = request.getParameterMap().get(key);
                        params = params+key+"="+value+"BLOBANDBLOB";
                    }
                    String targetUrl = URLEncoder.encode(request.getServletPath()+params,
					"UTF-8");
                    response.sendRedirect(request.getContextPath()
					+ "/Login.jsp?targetUrl=" + targetUrl);
		}
	}

	/**
	 * Method that checks the request to see if it is for a publicly accessible
	 * resource
	 */
	protected boolean isPublicResource(HttpServletRequest request) {
		String resource = request.getServletPath();

		return publicUrls.contains(request.getServletPath())
				|| (!resource.endsWith(".jsp") && !resource.endsWith(".action"));
	}

	/** Does nothing. */
	public void destroy() {
	}
}