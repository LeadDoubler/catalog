package com.asap.catalog.dao.manager;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import net.sourceforge.stripes.action.ActionBean;
import net.sourceforge.stripes.action.UrlBinding;

import com.asap.security.Role;
import com.asap.security.Secure;
import com.asap.security.resourse.ResourceRepository;
import com.asap.security.resourse.SecurityResource;
import com.asap.security.resourse.XStreamResourceRepository;
import net.sourceforge.stripes.controller.ActionResolver;
import net.sourceforge.stripes.controller.NameBasedActionResolver;
//import com.sun.corba.se.spi.activation.Repository;

public class ResourceManager { 
	private static ResourceRepository repository = new XStreamResourceRepository();

	private static Map<String, SecurityResource> resources;

	public static Map<String, SecurityResource> getResourceConfiguration() {
		
		// Execute only once, no need to go through actions upon each request.
		// Besides the code below seems to eat too much CPU on prod after a while.
		if (resources != null)
			return resources;
		
		resources = ResourceManager.repository.getResources();
		if (resources == null)
			resources = new HashMap<String, SecurityResource>();
        ActionResolver resolver = new NameBasedActionResolver();
		Set<Class<? extends ActionBean>> set = (Set<Class<? extends ActionBean>>) resolver.getActionBeanClasses(); //ActionClassCache.getInstance().getActionBeanClasses();
                /*for (Class c : set){
                    System.out.println("ActionBean = "+c.getCanonicalName());
                }*/
		Iterator<Class<? extends ActionBean>> iterator = set.iterator();
		while (iterator.hasNext()) {
			Class con = (Class) iterator.next();
			UrlBinding urlBinding = (UrlBinding) con
					.getAnnotation(UrlBinding.class);
			if (urlBinding == null) {
				Secure secure = (Secure) con.getAnnotation(Secure.class);
				if (secure != null) {
					SecurityResource sr = resources.get(con.getCanonicalName());
					if (sr == null) {
						sr = new SecurityResource("", Role.GUEST, secure.role());
						resources.put(con.getCanonicalName(), sr);
					} else {
						sr.setUrl("");
						sr.setDefaultRole(secure.role());
					}
				} else {
					SecurityResource sr = resources.get(con.getCanonicalName());
					if (sr == null) {
						sr = new SecurityResource("", Role.GUEST, Role.GUEST);
						resources.put(con.getCanonicalName(), sr);
					} else {
						sr.setUrl("");
						sr.setDefaultRole(Role.GUEST);
					}
				}
			} else {
				Secure secure = (Secure) con.getAnnotation(Secure.class);
				if (secure != null) {
					SecurityResource sr = resources.get(con.getCanonicalName());
					if (sr == null) {
						sr = new SecurityResource(urlBinding.value(),
								Role.GUEST, secure.role());
						resources.put(con.getCanonicalName(), sr);
					} else {
						sr.setUrl(urlBinding.value());
						sr.setDefaultRole(secure.role());
					}
				} else {
					SecurityResource sr = resources.get(con.getCanonicalName());
					if (sr == null) {
						sr = new SecurityResource(urlBinding.value(),
								Role.GUEST, Role.GUEST);
						resources.put(con.getCanonicalName(), sr);
					} else {
						sr.setUrl(urlBinding.value());
						sr.setDefaultRole(Role.GUEST);
					}
				}
			}
		}
		return resources;
	}

	public static void saveResourceConfiguration(
			Map<String, SecurityResource> resources) {
		ResourceManager.repository.saveResources(resources);
		ResourceManager.resources = resources;

	}

}
