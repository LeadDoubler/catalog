package org.mortena.stripes.conf;

import java.util.Locale;
import java.util.Collection;

import net.sourceforge.stripes.validation.TypeConverter;
import org.hibernate.criterion.Restrictions;
import util.HibernateUtil;


/**
 * Created by IntelliJ IDEA.
 * User: mortenandersen
 * Date: 2006-12-29
 * Time: 09:12:30
 * To change this template use File | Settings | File Templates.
 */
public class LongTypeConverter implements TypeConverter { 
  
    public void setLocale(Locale locale) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    public Object convert(String s, Class aClass, Collection collection) {
        Long key = new Long(s);
       // System.out.println("Converting - "+key+" to Class: "+aClass.getName());
        //Object obj = HibernateUtil.getSessionFactory().getCurrentSession().createCriteria(aClass).add(Restrictions.eq("id", key));
        Object obj = HibernateUtil.getSessionFactory().getCurrentSession().load(aClass,key);
        //HibernateUtil.getSessionFactory().getCurrentSession().setReadOnly(obj,true);
        return obj;
    }
}
