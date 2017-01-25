package com.asap.catalog.extensions.seminar.web.location;

import com.asap.catalog.extensions.seminar.Department;
import com.asap.security.Role;
import com.asap.security.Secure;
import java.util.List;

import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.HandlesEvent;
import net.sourceforge.stripes.action.Resolution;
import util.HibernateUtil;

import com.asap.catalog.extensions.seminar.Location;
import com.asap.web.CatalogActionBean;

public class LocationActionBean extends CatalogActionBean {
	private Location location;

	public Location getLocation() {
		return location;
	}

	public void setLocation(Location location) {
		this.location = location;
	}
        
	@Secure(role = Role.MODERATOR)
        public Resolution edit() {
		return new ForwardResolution("/location/edit.jsp");
	}

	@Secure(role = Role.MODERATOR)
        public Resolution delete() {
                Department dep = location.getDepartment();
                location.setDeleted(true);
		persist(location);
		return new ForwardResolution("/department/Department.action?view&department="+dep.getId());
	}
        
        @Secure(role = Role.MODERATOR)
        public Resolution restore() {
                location.setDeleted(false);
		persist(location);
		return new ForwardResolution("/location/view.jsp");
	}

	@Secure(role = Role.MODERATOR)
        public Resolution save() {
		persist(location);
		return new ForwardResolution("/department/Department.action?view&department="+location.getDepartment().getId());
	}
}
