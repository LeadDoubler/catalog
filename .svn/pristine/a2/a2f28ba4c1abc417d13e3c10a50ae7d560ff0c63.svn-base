/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.asap.catalog.extensions;

import com.asap.catalog.extensions.seminar.Department;
import com.asap.catalog.extensions.seminar.Event;
import com.asap.catalog.extensions.seminar.Location;
import com.asap.catalog.extensions.seminar.Term;
import com.asap.catalog.extensions.shop.course.dao.Course;
import com.asap.catalog.extensions.shop.dao.Request;
import com.asap.catalog.extensions.shop.dao.ShopCart;
import com.asap.catalog.extensions.shop.dao.ShopCartItem;
import com.asap.catalog.extensions.shop.dao.ShopCartOrder;
import com.asap.catalog.extensions.seminar.TermShopCartItem;
import com.asap.catalog.extensions.shop.market.dao.ProductShopCartItem;
import com.asap.catalog.extensions.venture.dao.ProjectDescription;
import net.sourceforge.stripes.config.Configuration;
import net.sourceforge.stripes.validation.LongTypeConverter;
import org.mortena.stripes.conf.CatalogTypeConverterFactory;

/**
 *
 * @author Morten
 */
public class CompleteTypeConverterFactory extends CatalogTypeConverterFactory{

     @Override
    public void init (Configuration configuration) {
        super.init (configuration);
        this.add (Course.class, LongTypeConverter.class);
        this.add (Location.class, LongTypeConverter.class);
        this.add (Department.class, LongTypeConverter.class);
        this.add (Event.class, LongTypeConverter.class);
        this.add (Term.class, LongTypeConverter.class);
        this.add (ShopCart.class, LongTypeConverter.class);
        this.add (Request.class, LongTypeConverter.class);
        this.add (ShopCartItem.class, LongTypeConverter.class);
        this.add (ShopCartOrder.class, LongTypeConverter.class);
        this.add (ProductShopCartItem.class, LongTypeConverter.class);
        this.add (TermShopCartItem.class, LongTypeConverter.class);
        this.add (ProjectDescription.class,LongTypeConverter.class);
     }
}
