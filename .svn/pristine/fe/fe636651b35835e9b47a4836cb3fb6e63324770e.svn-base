package com.asap.catalog.extensions.seminar.web.term;

import com.asap.security.Role;
import com.asap.security.Secure;
import java.util.LinkedList;
import java.util.List;
import net.sourceforge.stripes.action.DefaultHandler;

import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.Resolution;
import org.hibernate.criterion.Restrictions;
import util.HibernateUtil;

import com.asap.catalog.extensions.seminar.Event;
import com.asap.catalog.extensions.shop.course.dao.Course;
import com.asap.catalog.extensions.shop.dao.ShopCart;
import com.asap.catalog.extensions.seminar.Term;
import com.asap.catalog.extensions.seminar.TermShopCartItem;
import com.asap.catalog.extensions.shop.manager.ShopCatalogActionBean;
import net.sourceforge.stripes.action.RedirectResolution;

public class TermActionBean extends ShopCatalogActionBean {
    private Term term;
   
    @Secure(role = Role.MODERATOR)
    public Resolution save() {
        persist(term);
        
        for(Event event:term.getEvents()){
            event.setTerm(term);
            persist(event);
        }
        
        return new RedirectResolution("/course/Course.action?course="+term.getCourse());
    }
    
    @Secure(role = Role.MODERATOR)
    public Resolution delete() {
        Course c = term.getCourse();
        /*List<Event> events = term.getEvents();
        
        HibernateUtil.getSessionFactory().getCurrentSession().delete(term);
        
        for(Event event:events){
            HibernateUtil.getSessionFactory().getCurrentSession().delete(event);
        }*/
        term.setDeleted(true);
        persist(term);
        return new RedirectResolution("/course/Course.action?view&course="+c);
    }
    
    @Secure(role = Role.MODERATOR)
    public Resolution restore() {
        term.setDeleted(false);
        persist(term);
        return new RedirectResolution("/term/view.jsp");
    }
    
    @DefaultHandler
    public Resolution view() {
        return new ForwardResolution("/term/view.jsp");
    }
    
    @Secure(role = Role.MODERATOR)
    public Resolution edit() {
        if (getTerm().getId()==null){
        //if(getTerm().getEvents() == null){
            List<Event> events = new LinkedList();
            for(int i=0;i<3;i++){
                Event ne = new Event();
                ne.setTerm(getTerm());
                events.add(ne);
            }
            getTerm().setEvents(events);
        }        
        return new ForwardResolution("/term/edit.jsp");
    }
    
    public Resolution addToShopCart() {
	getSession ().getSessionFactory ().getCurrentSession ().saveOrUpdate (term);
        ShopCart shopCart = getContext ().getShopCart ();
        if(shopCart.getId() == null) getSession ().getSessionFactory ().getCurrentSession ().saveOrUpdate (shopCart);
        List<TermShopCartItem> list = getSession().createCriteria(TermShopCartItem.class).add(Restrictions.eq("shopCart",shopCart)).add(Restrictions.eq("term",term)).list();
        TermShopCartItem tsci;
        if(!list.isEmpty()){
            tsci = list.get(0);
            tsci.increaseOccurences();
        }else{
            tsci = new TermShopCartItem ();
            tsci.setTerm(term);
            tsci.setShopCart(shopCart);
        }
        getSession ().getSessionFactory ().getCurrentSession ().saveOrUpdate (tsci);
        return new ForwardResolution ("/shopcart/view.jsp");
    }
    
    public List<Course> getCourses() {
        return HibernateUtil.getSessionFactory().getCurrentSession()
        .createCriteria(Course.class).add(Restrictions.eq("deleted",false)).list();
    }
    
    public Term getTerm() {
        return term;
    }
    
    public void setTerm(Term term) {
        this.term = term;
    }
}
