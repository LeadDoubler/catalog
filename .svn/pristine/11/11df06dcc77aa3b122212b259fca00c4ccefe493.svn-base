package com.asap.web.user;

import com.asap.web.*;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.RedirectResolution;
import net.sourceforge.stripes.action.Resolution;

public class LogoutActionBean extends CatalogActionBean {
	@DefaultHandler
	public Resolution logout() throws Exception {
		getContext().logout();
                this.getContext().getRequest().getSession().setAttribute("dashboardEntered", false);
		return new RedirectResolution("/");
	}
}
