package com.asap.catalog.extensions.family.web.family;

import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;

import com.asap.catalog.extensions.family.Family;
import com.asap.catalog.dao.manager.CategoryManager;
import com.asap.catalog.extensions.family.manager.FamilyManager;
import com.asap.security.Role;
import com.asap.security.Secure;
import com.asap.web.CatalogActionBean;

@UrlBinding("/family/add.htm")
@Secure(role = Role.USER)
public class FamilyAddActionBean extends CatalogActionBean {
	private String title;

	private String intro;

	private String description;

	private String family;

	private String result;

	private String parent;

	@DefaultHandler
	@Secure(role = Role.USER)
	public Resolution show() {
		return new ForwardResolution("/family/add.jsp");

	}

	@Secure(role = Role.AUTHORIZED)
	public Resolution add() {
		try {
			Family family = new Family();
			family.setTitle(title);
			family.setDescription(description);
			family.setFamily(this.family);
			family.setIntro(intro);
			System.out.println(parent);

			family.setParent(CategoryManager.getCategory(new Long(parent)));

			FamilyManager.addFamily(family);
			System.out.println("OK");
			result = "OK";
		} catch (NumberFormatException exc) {
			// Error output
		}
		return new ForwardResolution("/family/add.jsp");
	}

	public String getFamily() {
		return family;
	}

	public void setFamily(String family) {
		this.family = family;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getIntro() {
		return intro;
	}

	public void setIntro(String intro) {
		this.intro = intro;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getParent() {
		return parent;
	}

	public void setParent(String parent) {
		this.parent = parent;
	}

}
