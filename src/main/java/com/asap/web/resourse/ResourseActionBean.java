package com.asap.web.resourse;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import javax.servlet.ServletContext;

import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;

import com.asap.catalog.dao.manager.ResourceManager;
import com.asap.security.Role;
import com.asap.security.Secure;
import com.asap.security.resourse.SecurityResource;
import com.asap.web.CatalogActionBean;

@UrlBinding("/resource/mainview.htm")
//@Secure(role = Role.GUEST)
public class ResourseActionBean extends CatalogActionBean {

	private Map<String, SecurityResource> resources;

	private Map<String, Role> resourcesMap = new HashMap<String, Role>();

	@DefaultHandler
	// @Secure(role = Role.GUEST)
	public Resolution show() {
		resources = ResourceManager.getResourceConfiguration();
		for (String resourcesMapKey : resources.keySet()) {
			Role role = resources.get(resourcesMapKey).getRole();
			resourcesMap.put(resourcesMapKey, role);
		}
		ResourceManager.saveResourceConfiguration(resources);
		return new ForwardResolution("/resource/mainview.jsp");
	}

	// @Secure(role = Role.AUTHORIZED)
	public Resolution save() {
		resources = ResourceManager.getResourceConfiguration();
		ResourceManager.saveResourceConfiguration(resources);
		for (String resourcesMapKey : resourcesMap.keySet()) {
			Role role = resourcesMap.get(resourcesMapKey);
			resources.get(resourcesMapKey).setRole(role);
		}
		ResourceManager.saveResourceConfiguration(resources);
		return new ForwardResolution("/resource/mainview.jsp");
	}

	public Map<String, SecurityResource> getResources() {
		return resources;
	}

	public void setResources(Map<String, SecurityResource> resources) {
		this.resources = resources;
	}

	public Map<String, Role> getResourcesMap() {
		return resourcesMap;
	}

	public void setResourcesMap(Map<String, Role> resourcesMap) {
		this.resourcesMap = resourcesMap;
	}

}
