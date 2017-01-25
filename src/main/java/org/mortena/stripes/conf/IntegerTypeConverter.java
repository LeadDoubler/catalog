package org.mortena.stripes.conf;

import net.sourceforge.stripes.validation.TypeConverter;

import java.util.Locale;
import java.util.Collection;

import util.HibernateUtil;

/**
 * Created by IntelliJ IDEA.
 * User: mortenandersen
 * Date: 2006-12-29
 * Time: 09:54:47
 * To change this template use File | Settings | File Templates.
 */
public class IntegerTypeConverter implements TypeConverter {

    public void setLocale(Locale locale) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    public Object convert(String s, Class aClass, Collection collection) {
        Integer key = new Integer(s);
        return HibernateUtil.getSessionFactory().getCurrentSession().load(aClass,key);
    }
}
