package com.asap.catalog.extensions.family.web.family;

import net.sourceforge.stripes.action.DontValidate;
import net.sourceforge.stripes.action.RedirectResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;

import com.asap.catalog.extensions.family.Family;
import com.asap.catalog.extensions.family.manager.FamilyManager;
import com.asap.security.Role;
import com.asap.security.Secure;
import com.asap.web.CatalogActionBean;

@UrlBinding("/family/show.htm")
@Secure(role = Role.USER)
public class FamilyShowActionBean extends CatalogActionBean {
	private Family family;

	public Family getFamily() {
		return family;
	}

	public void setFamily(Family family) {
		this.family = family;
	}

	@DontValidate
	public Resolution preEdit() {
		if (family == null) {
			this.family = new Family();
		} else {
			this.family = FamilyManager.getFamily(this.family.getId());
		}
		return new RedirectResolution("/family/show.jsp").flash(this);
	}
}
