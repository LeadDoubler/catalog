package com.asap.security;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sourceforge.stripes.action.ActionBeanContext;

/**
 * Interface proveded to allow for role based security using the
 * 
 * @Secure annotation which is specified at a class or method level.
 * 
 * @author Nic Holbrook
 */
public interface StripesSecurityManager {
	public boolean isUserInRole(Role role, ActionBeanContext context);

	public boolean isUserInRole(Role role, HttpServletRequest request,
			HttpServletResponse response);
}