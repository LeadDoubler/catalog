package com.asap.catalog.extensions.seminar.web.department;

import com.asap.security.Role;
import com.asap.security.Secure;
import java.util.List;

import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.HandlesEvent;
import net.sourceforge.stripes.action.Resolution;
import util.HibernateUtil;

import com.asap.catalog.extensions.seminar.Department;
import com.asap.web.CatalogActionBean;

public class DepartmentActionBean extends CatalogActionBean {
	private Department department;

	public Department getDepartment() {
		return department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}

	@DefaultHandler
	public Resolution list() {
		return new ForwardResolution("/department/list.jsp");
	}

	public Resolution view() {
		return new ForwardResolution("/department/view.jsp");
	}

	@Secure(role = Role.MODERATOR)
        public Resolution edit() {
		return new ForwardResolution("/department/edit.jsp");
	}

	@Secure(role = Role.MODERATOR)
        public Resolution delete() {
            department.setDeleted(true);
            persist(department);
            return new ForwardResolution("/department/list.jsp");
	}
        
        @Secure(role = Role.MODERATOR)
        public Resolution restore() {
            department.setDeleted(false);
            persist(department);
            return new ForwardResolution("/department/view.jsp");
	}

	@Secure(role = Role.MODERATOR)
        public Resolution save() {
		persist(department);
		return new ForwardResolution("/department/view.jsp");
	}
}
