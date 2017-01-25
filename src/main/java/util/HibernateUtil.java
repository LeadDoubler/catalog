package util;

import com.asap.catalog.dao.Component; 
import com.asap.configuration.Configuration;
import java.util.Collection;
import java.util.Set;
import java.util.Vector;
import net.sourceforge.stripes.util.ResolverUtil;

import org.apache.log4j.Logger;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;

public class HibernateUtil {

    private static final SessionFactory sessionFactory;
    private static Set<Class<? extends Component>> components;
    private static Logger logger = Logger.getLogger(HibernateUtil.class);
    

    static {
        try {
            long time = System.currentTimeMillis();
           // System.out.println("Creating AnnotationConfiguration...");
            // Create the SessionFactory from hibernate.cfg.xml
            AnnotationConfiguration aConf = new AnnotationConfiguration();
            aConf.configure();
            long time2 = System.currentTimeMillis();
           // System.out.println("Finished creating annotationConf - time "+ (time2-time));
            
            ResolverUtil<Component> resolver = new ResolverUtil<Component>();
            /*ResolverUtil<Object> resolver = new ResolverUtil<Object>();
            resolver.findAnnotated(Entity.class);
            Set<Class<? extends Object>> components = resolver.getClasses();
           */
            Collection<String> packages = new Vector();
            packages.add("WEB-INF/classes");
            //resolver.findAnnotated(Entity.class, "com");
            resolver.findImplementations(Component.class, "com");
            //resolver.setLocationFilters(packages);
            //resolver.loadImplementationsFromContextClassloader(Component.class);
            setComponents(resolver.getClasses());
            for(Class component : getComponents()){
                try{
                    String notImportingComponents = Configuration.getInstance().getProps().getProperty("catalog.components.exclude");
                    if (notImportingComponents != null){
                        String[] excludeComponentList = notImportingComponents.split(",");
                        if (excludeComponentList != null){
                            boolean add = true;
                            for (String s : excludeComponentList){
                                if (component.getName().equalsIgnoreCase(s)){
                                    //logger.debug("Component not added: "+component.getName());
                                    add = false;
                                }
                                else{
                                    
                                }
                            }
                            if (add){
                                aConf.addAnnotatedClass(component);
                                logger.debug("Added Component = "+component.getName());
                            }
                        }
                    }
                    else{
                        aConf.addAnnotatedClass(component);
                    }
                }
                catch(Exception e){
                    e.printStackTrace();
                }
            }
            long time3 = System.currentTimeMillis();
           // System.out.println("Finished adding components time = "+ (time3-time2) );
            sessionFactory = aConf.buildSessionFactory();
          //  System.out.println("Building Session factory takes = "+ (System.currentTimeMillis()-time3) );
        } catch (Throwable ex) {
            // Make sure you log the exception, as it might be swallowed
            ex.printStackTrace();
            logger.error("Initial SessionFactory creation failed." + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public static Set<Class<? extends Component>> getComponents() {
        return components;
    }

    public static void setComponents(Set<Class<? extends Component>> aComponents) {
        components = aComponents;
    }

}