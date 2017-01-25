package com.asap.catalog.extensions.family.web.family;

import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.DontValidate;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.RedirectResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;

import com.asap.catalog.dao.Category;
import com.asap.catalog.extensions.family.Family;
import com.asap.catalog.dao.manager.CategoryManager;
import com.asap.catalog.extensions.family.manager.FamilyManager;
import com.asap.security.Role;
import com.asap.security.Secure;
import com.asap.web.CatalogActionBean;

@UrlBinding("/family/edit.htm")
@Secure(role = Role.USER)
public class FamilyEditActionBean extends CatalogActionBean {
	private String id;

	private Family family = new Family();

	private String title;

	private String intro;

	private String description;

	private String familyString;

	private Category parent;

	private String parentId;

	@DefaultHandler
	@Secure(role = Role.USER)
	public Resolution show() {
		return new ForwardResolution("/family/edit.jsp");

	}

	@DontValidate
	public Resolution preEdit() {
		if (family == null) {
			System.out.println("1category == null");
		} else {
			System.out.println(family.getId());
		}
		this.family = FamilyManager.getFamily(this.family.getId());
		if (family == null) {
			System.out.println("2family == null");
		} else {
			System.out.println(family.getId());
		}
		this.id = family.getId().toString();
		this.title = family.getTitle();
		this.intro = family.getIntro();
		this.description = family.getDescription();
		this.familyString = family.getFamily();
		this.parent = family.getParent();
		this.parentId = parent.getId().toString();
		return new RedirectResolution("/family/edit.jsp").flash(this);
	}

	@Secure(role = Role.AUTHORIZED)
	public Resolution edit() {
		family.setId(new Long(id));
		family.setTitle(title);
		family.setDescription(description);
		family.setFamily(this.familyString);
		family.setIntro(intro);
		family.setParent(CategoryManager.getCategory(new Long(parentId)));
		FamilyManager.addFamily(family);
		return new ForwardResolution("/family/edit.jsp");

	}

	public Family getFamily() {
		return family;
	}

	public void setFamily(Family family) {
		this.family = family;
	}

	public String getFamilyString() {
		return familyString;
	}

	public void setFamilyString(String familyString) {
		this.familyString = familyString;
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

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Category getParent() {
		return parent;
	}

	public void setParent(Category parent) {
		this.parent = parent;
	}

	public String getParentId() {
		return parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}
}
