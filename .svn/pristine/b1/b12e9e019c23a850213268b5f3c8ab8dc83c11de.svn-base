package com.asap.catalog.extensions.seminar.manager;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Property;
import org.hibernate.criterion.Restrictions;

import util.HibernateUtil;

import com.asap.catalog.extensions.seminar.Location;

public class LocationManager {

	public List<Location> getAllLocation() {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
//		session.beginTransaction();
		List<Location> location = (List<Location>) session.createCriteria(Location.class)
                    .add(Restrictions.eq("deleted",false)).addOrder(Order.asc("department"))
                    .createAlias("department","department").add(Restrictions.eq("department.deleted",false)).list();
//		session.getTransaction().commit();
//		session.close();
		return location;
	}

	public static void addLocation(Location location) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
//		session.beginTransaction();
		session.saveOrUpdate(location);
//		session.getTransaction().commit();
//		session.close();
	}

	public static Location getLocation(Long id) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
//		session.beginTransaction();
		Property property = Property.forName("id");
		Location location = (Location) session.createCriteria(Location.class)
				.add(property.eq(id)).uniqueResult();
//		session.getTransaction().commit();
//		session.close();
		return location;
	}
}
