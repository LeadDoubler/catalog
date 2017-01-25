package com.asap.security.tag;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

import com.asap.security.Role;
import com.asap.security.StripesSecurityFilter;
import com.asap.security.StripesSecurityManager;

public class SecureTag extends TagSupport {
	private Role role;

//	private static Log log = Log.getInstance(SecureTag.class);

	final StripesSecurityManager securityManager = StripesSecurityFilter
			.getSecurityManager();

	public SecureTag() {
		super();
		initValues();
	}

	public Role getRole() {
		return role;
	}

	public void setRoles(Role role) {
		this.role = role;
	}

	public int doStartTag() throws JspException {
		if (role == null)
			return SKIP_BODY;

		if (securityManager.isUserInRole(role,
				((HttpServletRequest) pageContext.getRequest()),
				((HttpServletResponse) pageContext.getResponse())))
			return EVAL_BODY_INCLUDE;

		return SKIP_BODY;
	}

	private void initValues() {
		role = Role.GUEST;
	}
}
