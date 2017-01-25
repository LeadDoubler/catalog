package com.asap.catalog.extensions.family.manager;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.criterion.Property;

import util.HibernateUtil;

import com.asap.catalog.extensions.family.Family;

public class FamilyManager {

	public List<Family> getAllFamily() {

		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
//		session.beginTransaction();
		List<Family> family = (List<Family>) session.createCriteria(
				Family.class).list();
//		session.getTransaction().commit();
//		session.close();
		return family;
	}

	public static Family getFamily(Long id) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
//		session.beginTransaction();
		Property property = Property.forName("id");
		Family family = (Family) session.createCriteria(Family.class).add(
				property.eq(id)).uniqueResult();
//		session.getTransaction().commit();
//		session.close();
		return family;
	}

	public static Collection<Family> getFamilyCollection() {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		List<Family> familyList = (List<Family>) session.createCriteria(
				Family.class).list();
//		session.close();
		if (familyList.size() == 0) {
			List<Family> list = new ArrayList<Family>();
			Family family = new Family();
			family.setTitle("title");
			family.setId(new Long(-1));
			list.add(family);
			return list;
		}

		return familyList;
	}

	public static void addFamily(Family family) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
//		session.beginTransaction();
		session.saveOrUpdate(family);
//		session.getTransaction().commit();
//		session.close();
	}
}
