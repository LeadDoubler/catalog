package com.asap.security;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.criterion.Expression;
import org.hibernate.criterion.Property;

import util.HibernateUtil;

import com.asap.catalog.dao.User;

public class UserManager {

	public User getUser(String username) {
		Session session = util.HibernateUtil.getSessionFactory().getCurrentSession();
//		session.beginTransaction();
		Property usernameProp = Property.forName("username");

		List user = session.createCriteria(User.class).add(
				usernameProp.eq(username)).list();
//		session.getTransaction().commit();
//		session.close();
		if (user == null) {
			//System.out.println("user null1");
		} else {
			if (user.size() == 0) {
				//System.out.println("user null2");
			} else if (user.get(0) instanceof User) {
				//System.out.println(user.size());
				return (User) user.get(0);
			}
			// //System.out.println(user.getUsername());
		}
		return null;
	}

	public static List<User> getUserList() {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		List<User> users = (List<User>) session.createCriteria(User.class)
				.list();
//		session.close();
		return users;
	}

	public void saveOrUpdate(User User) {
		saveOrUpdateInternal(User);
	}

	public void deleteUser(long id) {
		deleteUser(id);
	}

	public static void deleteUser(Long userLong) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
//		session.beginTransaction();
		session.delete(getUser(userLong));
		//System.out.println("delete:");
		//System.out.println(userLong);
//		session.getTransaction().commit();
//		session.close();
		return;
	}

	public static User getUser(Long userLong) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		User user = (User) session.createCriteria(User.class).add(
				Expression.like("id", userLong)).uniqueResult();
//		session.close();
		return user;
	}

	public static void saveOrUpdateUser(User user) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		session.saveOrUpdate(user);
//		session.getTransaction().commit();
//		session.close();
	}

	private static void saveOrUpdateInternal(User user) {
		saveOrUpdateUser(user);
	}
}