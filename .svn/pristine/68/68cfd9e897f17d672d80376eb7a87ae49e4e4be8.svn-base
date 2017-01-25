package com.asap.catalog.extensions.seminar.manager;

import com.asap.catalog.extensions.seminar.Department;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Property;
import org.hibernate.criterion.Restrictions;

import util.HibernateUtil;

import com.asap.catalog.extensions.seminar.Seminar;
import com.asap.web.CatalogActionBean;

public class SeminarManager extends CatalogActionBean{

    public List<Seminar> getAllSeminar() {
        return HibernateUtil.getSessionFactory().getCurrentSession().createCriteria(Seminar.class).createAlias("event","event").addOrder(Order.asc("event.date")).list ();
    }
}
