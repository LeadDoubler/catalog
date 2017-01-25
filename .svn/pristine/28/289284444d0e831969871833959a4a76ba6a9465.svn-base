package com.asap.catalog.extensions.seminar.manager;

import com.asap.web.CatalogActionBean;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Property;
import org.hibernate.criterion.Restrictions;

import util.HibernateUtil;

import com.asap.catalog.extensions.seminar.Department;

public class DepartmentManager extends CatalogActionBean{

	public List<Department> getAllDepartment() {
		return HibernateUtil.getSessionFactory().getCurrentSession()
                    .createCriteria(Department.class).add(Restrictions.eq("deleted",false)).addOrder(Order.asc("name")).list();
	}

}
