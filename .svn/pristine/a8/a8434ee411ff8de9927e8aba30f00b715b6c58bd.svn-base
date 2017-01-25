/*
 * CompanyActionBean.java
 *
 * Created on 1. maj 2007, 08:10
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package com.blob.pas;

import com.asap.catalog.dao.Component;
import com.asap.catalog.dao.util.PaginatedListImpl;
import com.asap.web.CatalogActionBean;
import java.lang.Class;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.DontValidate;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.Resolution;
import org.displaytag.properties.SortOrderEnum;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import util.HibernateUtil;


/**
 * 
 * @author mortenandersen
 */ 
public abstract class DefaultActionBean extends CatalogActionBean{
    final protected String PREPATH = "/WEB-INF/view";

    public void ensureSearchOnPaging() {
        String pagerId = this.getContext().getRequest().getParameter("pagerid");
        if (pagerId != null) {
            // Create lookup text for "SearchText"
            StringBuilder lookupBuilder = new StringBuilder();
            lookupBuilder.append(pagerId);
            lookupBuilder.append('_');
            lookupBuilder.append("SearchText");
            String searchTextLookup = lookupBuilder.toString();
            // Create lookup text for "NumberOnPage"
            lookupBuilder = new StringBuilder();
            lookupBuilder.append(pagerId);
            lookupBuilder.append('_');
            lookupBuilder.append("NumberOnPage");
            String numberOnPageLookup = lookupBuilder.toString();
            // Handle SearchText
            String storedSearchText = (String) this.getContext().getRequest().getSession().getAttribute( searchTextLookup );
            if (searchText == null) {
                searchText = (String) this.getContext().getRequest().getSession().getAttribute( searchTextLookup );
            } else if (storedSearchText == null) {
                this.getContext().getRequest().getSession().setAttribute(storedSearchText, searchText);
            } else if (!searchText.equals(storedSearchText)) {
                this.getContext().getRequest().getSession().setAttribute(storedSearchText, searchText);
            }
            // Handle "Number on page"-selection
            Integer storedNumberOnPage = (Integer) this.getContext().getRequest().getSession().getAttribute( numberOnPageLookup );
            if (storedNumberOnPage != null) {
                if (this.getNumberOnPage() != storedNumberOnPage.intValue()) {
                    numberOnPage = storedNumberOnPage.intValue();
                } else {
                    this.getContext().getRequest().getSession().setAttribute(numberOnPageLookup, new Integer(this.getNumberOnPage()));
                }
            }
        }
    }

    public Class getClassForCriteria() {
        Class critClass = Object.class;
        if (this.getComponentClass() != Component.class) {
            critClass = this.getComponentClass();
        } else {
            for (Class sClass : HibernateUtil.getComponents()) {
                if (sClass.getSimpleName().equals(getClassname())) {
                    critClass = sClass;
                }
            }
        }
        return critClass;
    }
    public abstract String getType();
    public abstract Object getComponent();
    
    public  List<Object> getComponents(){
        Criteria crit = getSession().createCriteria(getComponentClass());
        this.addExtraCriterias(crit);
        return crit.list();
    }
    
    public String getEditorFolder(){
        return getType();
    }

    
    public String getViewFolder(){
        return getEditorFolder();
    } 
    
    public String getListFolder(){
        return getForwardFolder();
    }
            
    public String getForwardFolder(){
        return getType();
    } 
    
    public String getCapType(){
        return getType().substring(0,1).toUpperCase()+getType().substring(1);
    }
    
    public ForwardResolution getForward(){
        //System.out.println("getForward goes to: "+getPREPATH()+getViewFolder()+this.getContext().getEventName()+".jsp");
        return new ForwardResolution(getPREPATH()+"/"+getViewFolder()+"/"+this.getContext().getEventName()+".jsp");
    }

    public void makeQuery(Class critClass, PaginatedListImpl page) throws HibernateException {
        Criteria crit = getSession().createCriteria(critClass);
        this.addExtraCriterias(crit);
        if (page.getSortCriterion() != null) {
            Order sortOrder;
            if (page.getSortDirection().equals(SortOrderEnum.ASCENDING)) {
                sortOrder = Order.asc(page.getSortCriterion());
            } else {
                sortOrder = Order.desc(page.getSortCriterion());
            }
            crit.addOrder(sortOrder);
        }
        boolean export=false;
        if (this.getContext() == null){
            export = true;
        }
        else{
            for (Object key : this.getContext().getRequest().getParameterMap().keySet() ){
                String skey = (String) key;
                if (skey.startsWith("d-") && skey.endsWith("-e")){
                    export = true;
                }
                //System.out.println("Parameter:" +key);
            }
        }
        if ( ! export ){
            String p = this.getContext().getRequest().getParameter("page");
            int pint = -1;
            if (p != null){
                pint = Integer.parseInt(p);
                page.setIndex(pint-1);
            }
            /*if (pint>0 && page.getFirstRecordIndex()==0){
                crit.setFirstResult(this.getPages() * this.getNumberOnPage());
            }
            else{*/
                crit.setFirstResult(page.getFirstRecordIndex());
            //}
                crit.setMaxResults(page.getObjectsPerPage());
        }
        page.setList(crit.list());
    }

    public void setTotalOnPage(Class critClass, PaginatedListImpl page) throws HibernateException {
        //System.out.println("Setting total on page");
        Criteria totalCrit = getSession().createCriteria(critClass);
        this.addExtraCriterias(totalCrit);
        totalCrit.setProjection(Projections.rowCount());
        try{
            Integer numberOfTracks = (Integer) totalCrit.list().get(0);
            page.setTotal(numberOfTracks);
        }catch(Exception e){
            page.setTotal(new Integer(0));
        }
        
    }
    
    public Resolution view(){
        return new ForwardResolution(PREPATH+"/"+getForwardFolder()+"/"+this.getContext().getEventName()+".jsp");
    }
    
    public Resolution inlineView(){
        return forward();
    }
    
    protected Resolution forward(){
        return new ForwardResolution(PREPATH+"/"+getListFolder()+"/"+this.getContext().getEventName()+".jsp");
    }
    
    @DefaultHandler
    @DontValidate
    public Resolution list(){ 
        String forward = PREPATH+"/"+getListFolder()+"/"+this.getContext().getEventName()+".jsp";
        //System.out.println("forwards to: "+forward);
        return new ForwardResolution(forward);
    }
    
    @DontValidate
    public Resolution edit(){
        return new ForwardResolution(PREPATH+"/"+getForwardFolder()+"/"+this.getContext().getEventName()+".jsp");
    }
    
    @DontValidate
    public Resolution inline(){
        return new ForwardResolution(PREPATH+"/"+getEditorFolder()+"/"+this.getContext().getEventName()+".jsp");
    }
    
    
    public Resolution delete(){
        getSession().delete(getComponent());
        delete(getComponent());
        return new ForwardResolution(PREPATH+"/"+getForwardFolder()+"/"+this.getContext().getEventName()+".jsp");
    }
    
    public Resolution inlineSave(){
        persist(getComponent());
        return new ForwardResolution(PREPATH+"/"+getViewFolder()+"/view.jsp");
    }
    
    public Resolution invoke(){
        persist(getComponent());
        return new ForwardResolution(PREPATH+"/"+getViewFolder()+"/inlineView.jsp");
    }

    public Resolution save(){
        persist(getComponent());
        return new ForwardResolution(PREPATH+"/"+getForwardFolder()+"/view.jsp");
    }    

    public String getPREPATH() {
        return PREPATH;
    }
    
    private int low;
    private int numberOnPage = 30;
    private Order order;
    
    /**
     * Must be overridden to create a criteria for paging etc.
     * @return
     */
    public Class getComponentClass(){
        return Component.class;
    }
    
    public List<Object> getPageComponents() {
         Criteria crit = getSession().createCriteria(getComponentClass()); //.add(Expression.ne("role",Role.GUEST))
         if (getOrder() !=null){
             crit.addOrder(getOrder());
         }
         crit.setMaxResults(numberOnPage);
         crit.setFirstResult(getLow());
         addExtraCriterias(crit);
         return crit.list();
    }
    
    public int getPages(){
        if(getPageComponents().size()>0){
          return getComponents().size() / getPageComponents().size();
        }
        else{
            return 1;
        }
     }
    
    public Resolution inlineList(){
        return new ForwardResolution(PREPATH+"/default/inlineList.jsp");
    }
    
    /**
     * Must be implemented to add filtering according to type specific fields.
     * @param crit
     * @return
     */
    protected Criteria addExtraCriterias(Criteria crit){
        //crit.add(Expression.isNotNull("title"));
        return crit;
    }
    

    public int getLow() {
        return low;
    }

    public void setLow(int low) {
        this.low = low;
    }

    public int getNumberOnPage() {
        return numberOnPage;
    }

    public void setNumberOnPage(int numberOnPage) {
        this.numberOnPage = numberOnPage;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }
    
    private Method[] columns;
    
    public Method[] getColumns(){
        List<Method> c = new ArrayList();
        Method m[] = getComponentClass().getDeclaredMethods();
        for (int i = 0; i < m.length; i++){
            Method method = m[i];
            if (method.getName().startsWith("get")){
                c.add(method);
            }
        }
        Method[] cls = new Method[c.size()];
        this.columns =  c.toArray(cls);
        return columns;
    }
    
    public String[] getColumnNames(){
        List<String> s = new ArrayList();
        for (Method m : getColumns()){
            s.add(m.getName().substring(0,1).toLowerCase()+m.getName().substring(1));
        }
        return s.toArray(new String[s.size()]);
    }
    
    public Object invokeMethod (Method m , Object obj) {
        try {
            return m.invoke(obj);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(DefaultActionBean.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalArgumentException ex) {
            Logger.getLogger(DefaultActionBean.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InvocationTargetException ex) {
            Logger.getLogger(DefaultActionBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    public Object getValue(Object component, Object column){
        Method m = (Method) column;
        try {
            return m.invoke(component);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(DefaultActionBean.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalArgumentException ex) {
            Logger.getLogger(DefaultActionBean.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InvocationTargetException ex) {
            Logger.getLogger(DefaultActionBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    public Object getValueByName(Object component, Object name){
        String methodName = "get"+name.toString().substring(0,1).toUpperCase()+name.toString().substring(1);
        Method m = null;//.getDeclaredMethod(name);
        try {
            m = getComponentClass().getMethod(methodName);
        } catch (NoSuchMethodException ex) {
            Logger.getLogger(DefaultActionBean.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SecurityException ex) {
            Logger.getLogger(DefaultActionBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        return getValue(component, m);
    }
    
    public Object getColumnDisplay(){
        try {
            Object obj = this.getContext().getServletContext().getAttribute("component");
            Method m = (Method) this.getContext().getServletContext().getAttribute("column");
            System.out.println("m = "+m+" obj = "+obj);
            return m.invoke(obj);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(DefaultActionBean.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalArgumentException ex) {
            Logger.getLogger(DefaultActionBean.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InvocationTargetException ex) {
            Logger.getLogger(DefaultActionBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    public Map<String,String> getValues(){
        if (columns == null){
            columns = getColumns();
        }
        return new MethodMap(columns, getComponent());
    }
    
    private String classname;
    
    protected PaginatedListImpl objects;
    
     public Resolution pageList() {
        setObjects(new PaginatedListImpl(this.getContext().getRequest()));
        createList(getObjects());
        return new ForwardResolution("/inlineList.jsp");
  }
     
     //@DontValidate
     public Resolution inlineTable() {
        setObjects(new PaginatedListImpl(this.getContext().getRequest()));
        createList(getObjects());
        return new ForwardResolution(PREPATH+"/"+this.getType()+"/inlineTable.jsp");
  }


    public void createList(PaginatedListImpl page) {
        Class critClass = getClassForCriteria();
        //System.out.println("start setting total");
        setTotalOnPage(critClass,page);
        page.setPageSize(this.getNumberOnPage());

        
        //ensureSearchOnPaging();
        makeQuery(critClass, page) ;
  }

    public String getClassname() {
        if (classname == null && this.getComponentClass() != null){
            this.getComponentClass().getSimpleName();
        }
        return classname;
    }

    public void setClassname(String classname) {
        this.classname = classname;
    }

    public PaginatedListImpl getObjects() {
        if (objects == null){
            if (this.getContext() == null){
                setObjects(new PaginatedListImpl());
                getObjects().setPageSize(10000);
            }
            else{
                setObjects(new PaginatedListImpl(this.getContext().getRequest()));
            }
            createList(getObjects());
        }
        return objects;
    }

    public void setObjects(PaginatedListImpl objects) {
        this.objects = objects;
    }
    
    private String searchText;

    public String getSearchText() {
        return searchText;
    }

    public void setSearchText(String searchtext)
    {
        this.searchText = searchtext;
    }


    
//    private String pagerId;
//    public String getPagerId()
//    {
//        return pagerId;
//    }
//    public void setPagerId( String pagerId )
//    {
//        this.pagerId = pagerId;
//    }
    
}
