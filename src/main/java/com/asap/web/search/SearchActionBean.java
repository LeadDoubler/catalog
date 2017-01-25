/*
 * SearchActionBean.java
 *
 * Created on 28. marts 2007, 16:09
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package com.asap.web.search;

import com.asap.catalog.dao.manager.Manager;
import com.asap.catalog.dao.manager.ManagerConfiguration;
import com.asap.configuration.ConfigurationManager;
import util.HibernateUtil;
import com.asap.web.CatalogActionBean;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.Vector;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.util.ResolverUtil;
import org.hibernate.Criteria;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Expression;

/**
 *
 * @author mortenandersen
 */
public class SearchActionBean extends CatalogActionBean{
    
    private String text,language;
    private long executionTime;
    
       
    
            
            
    private List<Manager> results = new ArrayList();
    
    
    
    /** Creates a new instance of SearchActionBean */
    public SearchActionBean() {
    }
    
    @DefaultHandler
    public Resolution search()  {
        long before = System.currentTimeMillis();
        ManagerConfiguration managerConf = ManagerConfiguration.getInstance();        
        if (managerConf.getManagers() == null){
            ResolverUtil<Manager> resolver = new ResolverUtil<Manager>();
            resolver.findImplementations(Manager.class,"com.asap");
            resolver.findImplementations(Manager.class,"com.blob");
            //List<String> l = new Vector();
            //l.add("WEB-INF/classes");
            //resolver.setLocationFilters(l);
            //resolver.loadImplementationsFromContextClassloader(Manager.class);
            Set<Class<? extends Manager>> classes = resolver.getClasses();
            managerConf.setManagers(classes);
        }
        for ( Class theClass : managerConf.getManagers() ){
            Manager man;
            try {
                man = (Manager) theClass.newInstance();
                System.out.println("Manager added ="+man.getClass().getName());
                man.setText(getText());                
                man.setLanguage(getLanguage());
                getResults().add(man);
            } catch (IllegalAccessException ex) {
                ex.printStackTrace();
            } catch (InstantiationException ex) {
                ex.printStackTrace();
            }
            
        }
        long after = System.currentTimeMillis();
        setExecutionTime(after-before);
        /*ConfigurationManager manager = new ConfigurationManager();
        String classes = manager.getParameter("search_managers");
        if (classes != null ){
            for(String className : classes.split(",")){
                Class theClass;
                try {
                    theClass = Class.forName(className);
                    Manager man;
                    man = (Manager) theClass.newInstance();
                    man.setText(text);
                    results.add(man);
                } catch (InstantiationException ex) {
                    ex.printStackTrace();
                } catch (IllegalAccessException ex) {
                    ex.printStackTrace();            
                } catch (ClassNotFoundException ex) {
                    ex.printStackTrace();
                }

            }
        }*/
        return new ForwardResolution("/search/view.jsp");
    }

    private String firstLetterToLow(String name) {
        String nameOfClassLowStart = name.substring(0,1).toLowerCase()+name.substring(1);
        return nameOfClassLowStart;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
    
    public int getNumberOfResults(){
        int i = 0;
        for(Manager manager: getResults()){
            i = i + manager.getResults().size();
        }
        return i;
    }

    public List<Manager> getResults() {        
        return results;
    }

    public void setResults(List<Manager> results) {
        this.results = results;
    }
    
    /*for(String className : classes.split(",")){
            try {                
                Class theClass = Class.forName(className);
                String nameOfClassLowStart = firstLetterToLow(theClass.getName());
                
                Criteria crit = getSession().createCriteria(theClass);
                Criterion curExp = null;
                for(Method method : theClass.getMethods()){
                    if ( method.getReturnType().equals(String.class) && method.getName().startsWith("get") ){
                        String property = method.getName().substring("get".length());
                        try {
                            //theClass.getMethod("set"+property);
                            property = firstLetterToLow( property );
                            Criterion exp2 = Expression.like( property,"%"+text+"%") ;
                            if (curExp == null){
                                curExp = exp2;
                            }
                            else{
                                curExp = Expression.or(curExp,exp2);
                            }                           
                        } catch (SecurityException ex) {
                            ex.printStackTrace();
                        } 
                    }                    
                }
                if (curExp != null){
                    crit.add(curExp);
                    List findings = crit.list();                    
                    for ( Object obj : findings ){
                        Method m;
                        try {
                            m = theClass.getMethod("getId");
                            try {                                
                                results.add(nameOfClassLowStart+"/"+m.invoke(obj));
                            } catch (IllegalArgumentException ex) {
                                ex.printStackTrace();
                            } catch (IllegalAccessException ex) {
                                ex.printStackTrace();
                            } catch (InvocationTargetException ex) {
                                ex.printStackTrace();
                            }
                        } catch (SecurityException ex) {
                            ex.printStackTrace();
                        } catch (NoSuchMethodException ex) {
                            ex.printStackTrace();
                        }  
                        results.add(nameOfClassLowStart);
                    }
                }
                
            } catch (ClassNotFoundException ex) {
                ex.printStackTrace();
            }
        }*/

    public long getExecutionTime() {
        return executionTime;
    }

    public void setExecutionTime(long executionTime) {
        this.executionTime = executionTime;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }
    
}
