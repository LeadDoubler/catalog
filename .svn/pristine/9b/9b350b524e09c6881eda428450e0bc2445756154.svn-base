package com.asap.security;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sourceforge.stripes.action.ActionBeanContext;

import com.asap.catalog.dao.User;
import com.asap.web.CatalogActionBeanContext;

public class CatalogSecurityFilter implements StripesSecurityManager {

	public boolean isUserInRole(Role role, ActionBeanContext context) {
		if (role.equals(Role.GUEST))
			return true;
		if (context instanceof CatalogActionBeanContext) {
			User user = ((CatalogActionBeanContext) context).getUser();
			if (user != null) {
				return isUserInRole(role, user);
			} else {
				return false;
			}
		} else {
			Object object = context.getRequest().getSession().getAttribute(
					"user");
			if (object != null) {
				if (object instanceof User) {
					User user = (User) object;
					return isUserInRole(role, user);
				} else {
					return false;
				}
			} else {
				return false;
			}
		}
	}

	public boolean isUserInRole(Role role, HttpServletRequest request,
			HttpServletResponse response) {
		Object object = request.getSession().getAttribute("user");
		if (object != null) {
			if (object instanceof User) {
				User user = (User) object;
				return isUserInRole(role, user);
			} else {
				return false;
			}
		} else {
			return false;
		}
	}

	private boolean isUserInRole(Role role, User user) {
		//System.out.println("Compare ");
		if (user.getRole().compareTo(role) >= 0) {
			//System.out.println("true");
			return true;
		} else {
			//System.out.println("false");
			return false;
		}
	}
}
