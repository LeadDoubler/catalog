package com.asap.catalog.extensions.seminar.manager;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.criterion.Property;

import util.HibernateUtil;

import com.asap.catalog.extensions.seminar.Event;


public class EventManager {
        
	public List<Event> getAllevent() {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
//		session.beginTransaction();
		List<Event> event = (List<Event>) session.createCriteria(
				Event.class).list();
//		session.getTransaction().commit();
//		session.close();
		return event;
	}

	public static void addEvent(Event event) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
//		session.beginTransaction();
		session.saveOrUpdate(event);
//		session.getTransaction().commit();
//		session.close();
	}

	public static Event getEvent(Long id) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
//		session.beginTransaction();
		Property property = Property.forName("id");
		Event event = (Event) session.createCriteria(Event.class)
				.add(property.eq(id)).uniqueResult();
//		session.getTransaction().commit();
//		session.close();
		return event;
	}
    
}
