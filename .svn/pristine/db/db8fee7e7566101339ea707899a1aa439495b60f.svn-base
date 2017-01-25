package org.mortena.stripes.conf;

import com.asap.catalog.dao.Category;
import com.asap.catalog.dao.Component;
import com.asap.catalog.dao.User;
import com.asap.util.OrderTypeConverter;
import java.util.ArrayList;
import java.util.Date;
import java.util.Set;
import net.sourceforge.stripes.validation.DateTypeConverter;
import net.sourceforge.stripes.validation.DefaultTypeConverterFactory;
import net.sourceforge.stripes.config.Configuration;
import net.sourceforge.stripes.util.ResolverUtil;
import net.sourceforge.stripes.validation.TypeConverter;
import org.hibernate.criterion.Order;

/**
 * Created by IntelliJ IDEA.
 * User: mortenandersen
 * Date: 2007-02-09
 * Time: 17:21:34
 * To change this template use File | Settings | File Templates.
 */
public class CatalogTypeConverterFactory extends DefaultTypeConverterFactory {
    
    @Override
    public void init (Configuration configuration) {


        super.init (configuration);
        //this.add (Part.class, LongTypeConverter.class);
        this.add (Date.class, DateTypeConverter.class);
        //this.add (Page.class, PageTypeConverter.class);
        
        this.add (User.class, LongTypeConverter.class);
        this.add (Category.class, LongTypeConverter.class);
        this.add ( Order.class , OrderTypeConverter.class);
        /*this.add (Course.class, LongTypeConverter.class);
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
         * 
        this.add (ProjectDescription.class,LongTypeConverter.class);
        */
        
     
        
        ResolverUtil<Component> resolver = new ResolverUtil<Component>();
        ArrayList locations = new ArrayList();
        locations.add("WEB-INF/classes");
        resolver.findImplementations(Component.class, "com");
        //resolver.setLocationFilters(locations);
        //resolver.loadImplementationsFromContextClassloader(Component.class);
        Set<Class<? extends Component>> components = resolver.getClasses();
        for(Class component : components){
            try{
                this.add(component, LongTypeConverter.class);
                //System.out.println"Added Component to typeconverter = "+component.getName());
            }
            catch(Exception e){
                e.printStackTrace();
            }
        }
    }

    @Override
    public void add(Class aClass, Class converter) {
        super.add(aClass,(Class<? extends TypeConverter<?>>) converter);
    }
}
