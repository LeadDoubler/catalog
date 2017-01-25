package com.asap.catalog.extensions.family.web.family;

import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;

import com.asap.security.Role;
import com.asap.security.Secure;
import com.asap.web.CatalogActionBean;

@UrlBinding("/family/list.htm")
@Secure(role = Role.USER)
public class FamilyListActionBean extends CatalogActionBean {

	@DefaultHandler
	@Secure(role = Role.USER)
	public Resolution show() {
		return new ForwardResolution("/family/list.jsp");
	}
}
