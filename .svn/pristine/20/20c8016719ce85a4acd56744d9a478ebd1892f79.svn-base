/*
 * OrderTypeConverter.java
 *
 * Created on 21. november 2007, 17:55
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package com.asap.util;

import java.util.Collection;
import java.util.Locale;
import net.sourceforge.stripes.validation.TypeConverter;
import net.sourceforge.stripes.validation.ValidationError;
import org.hibernate.criterion.Order;

/**
 *
 * @author mortenmatras
 */
public class OrderTypeConverter implements TypeConverter{
    
    /** Creates a new instance of OrderTypeConverter */
    public OrderTypeConverter() {
    }

    public void setLocale(Locale locale) {
    }

    public Object convert(String s, Class aClass , Collection coll ) {
        if (s == null){
            return null;
        }
        if (s.split(":").length==1){
            return null;
        }
        String sortBy = s.split(":")[0];
        String order = s.split(":")[1];
        System.out.println("SortBy = "+sortBy);
        System.out.println("order"+order);
        if (sortBy != null){
             if (order != null && order.equals("asc")){
                 return Order.asc(sortBy);
             }
             else if (order != null && order.equals("desc")){
                 return Order.desc(sortBy);
             }
         }
        return null;
    }
    
}
