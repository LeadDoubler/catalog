package com.asap.catalog.dao.manager;

import java.util.List;
import java.util.Iterator;

import org.hibernate.Session;
import org.hibernate.criterion.Property;
import org.hibernate.criterion.Expression;
import org.hibernate.criterion.Restrictions;

import util.HibernateUtil;

import com.asap.catalog.dao.Category;
import org.hibernate.Criteria;
import org.hibernate.criterion.CriteriaSpecification;
import org.hibernate.criterion.Order;

public class CategoryManager {
    
   
    public List<Category> getAllCategory() {

        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        List<Category> category = (List<Category>) session.createCriteria(Category.class).add(Restrictions.eq("deleted",false)).list();
        return category;
    }
    
    public List<Category> getFullCategory() {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        List<Category> category = (List<Category>) session.createCriteria(Category.class).list();
        return category;
    }

    public static Category getCategory(Long id) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
//		session.beginTransaction();
        Property property = Property.forName("id");
        Category category = (Category) session.createCriteria(Category.class)
                .add(property.eq(id)).uniqueResult();
//		session.getTransaction().commit();
//		session.close();
        return category;
    }

    public List<Category> getCategoryCollection() {
        //System.out.println("getCategoryCollection - started");
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        List<Category> categoryList = (List<Category>) session.createCriteria(
                Category.class).add( Expression.not(  Expression.eq( "deleted",true)  ) ).
                addOrder(Order.asc("title")).
                setResultTransformer(CriteriaSpecification.DISTINCT_ROOT_ENTITY).list();
        //session.close();
        /*if (categoryList.size() == 0) {
            List<Category> list = new ArrayList<Category>();
            Category category = new Category();
            category.setTitle("title");
            category.setId(new Long(-1));
            list.add(category);
            return list;
        }*/
        System.out.println("#kategorier = "+categoryList.size());
        return categoryList;
    }

    public static void addCategory(Category category) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
//		session.beginTransaction();
        session.saveOrUpdate(category);
//		session.getTransaction().commit();
//		session.close();
    }

    public String getXml(){
        StringBuffer sb = new StringBuffer();
        List<Category> list = HibernateUtil.getSessionFactory().getCurrentSession().createCriteria(Category.class)
            .add(Expression.isNull("category"))
            .list();
        for (Iterator<Category> iterator = list.iterator(); iterator.hasNext();) {
            Category cat =  iterator.next();
            sb.append(cat.getXml());
        }
        return sb.toString();
    }
    
    public String getCatXml(){
        List<Category> cats = getSession().createCriteria(Category.class).add(Expression.isNull("parent")).addOrder(Order.asc("title")).list();
        
        StringBuffer sb = new StringBuffer();
        sb.append("<cats>");
        if (cats != null || cats.size() > 0){

            Iterator iter = cats.iterator();
            while (iter.hasNext()){
                Category front = (Category) iter.next();
                front.initializeChildren();
                sb.append(front.getXml());
            }
           /* Segment segment = new Segment();
            if (pages!=null && pages.size()>0){
                segment.setPage(pages.get(0));
            }*/
        }
        sb.append("</cats>");
        return sb.toString();
    }

    private Session getSession() {
        return HibernateUtil.getSessionFactory().getCurrentSession();
    }
    
     public List<Category> getCategoryList() {
        List<Category> list = 
                getSession().createCriteria(Category.class)
                    .add(Expression.isNull("parent"))
                    .add(Expression.ne("deleted", true))
                    .setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();
        return list;
    }
}
