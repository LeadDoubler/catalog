/*
 * CourseActionBean.java
 *
 * Created on 14. februar 2007, 15:04
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package com.asap.catalog.extensions.shop.web.course;

import com.asap.catalog.dao.Category;
import com.asap.catalog.extensions.seminar.Term;
import com.asap.security.Role;
import com.asap.security.Secure;
import java.util.List;

import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.RedirectResolution;
import net.sourceforge.stripes.action.Resolution;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import util.HibernateUtil;

import com.asap.catalog.extensions.shop.course.dao.Course;
import com.asap.catalog.extensions.seminar.Event;
import com.asap.web.CatalogActionBean;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.LinkedList;
import org.hibernate.criterion.Property;

/**
 *
 * @author mortenandersen
 */
public class CourseActionBean extends CatalogActionBean {
    
    private Course course;
    
    public Course getCourse() {
        return course;
    }
    
    public void setCourse(Course course) {
        this.course = course;
    }
    
    @Secure(role = Role.MODERATOR)
    public Resolution save() { 
        persist(course);
        return new RedirectResolution("/course/Course.action?view&course="+course);
    }
    
    @Secure(role = Role.MODERATOR)
    public Resolution edit() {
        return new ForwardResolution("/course/edit.jsp");
    }
    
    @Secure(role = Role.MODERATOR)
    public Resolution delete() {
        for(Term term:getTerms()){
            for(Event event:term.getEvents()){
                getSession().delete(event);
            }
            getSession().delete(term);
        }
        getSession ().delete (getCourse ());
        persist();
        return new ForwardResolution ("/product/list.jsp");
    }
    
    @DefaultHandler
    public Resolution list() {
        return new ForwardResolution("/course/list.jsp");
    }
    
    public Resolution view() {
        return new ForwardResolution("/course/view.jsp");
    }
    
    public Resolution inlineList() {
        return new ForwardResolution("/course/inlineList.jsp");
    }
    
    public Resolution inlineView() {
        return new ForwardResolution("/course/inlineView.jsp");
    }
    
    @Secure(role = Role.MODERATOR)
    public Resolution manager() {
        return new ForwardResolution("/course/CourseManager.jsp");
    }
    
    public List<Course> getCourseList() {
        List<Course> list = getSession().createCriteria(Course.class).list();
        return list;
    }
    
    public List<Category> getCategoryList() {
        List<Category> list = getSession().createCriteria(Category.class).list();
        return list;
    }
    
    public List<Term> getTerms() {
        List<Term> list = getSession().createCriteria(Term.class).add(Restrictions.eq("course",course)).list();
        return list;
    }
    
    static final String[] months = {"Januar","Februar","Marts","April","Maj","Juni","Juli","August","September","Oktober","November","December"};
    
    public List<List<Term>> getTermsByMonth() {
        
           /* List<List<Term>> list = new LinkedList();
            Calendar cal = new GregorianCalendar();
            cal.setTimeInMillis(System.currentTimeMillis());
            
            Date date1;
            Date date2;

            for(int i=0;i<12;i++){
                //String month = months[cal.get(cal.MONTH)]+" "+(cal.get(cal.YEAR));
                date1 = cal.getTime();
                
                if(i==0){
                    cal.set(cal.DAY_OF_MONTH,1);
                    cal.set(cal.HOUR_OF_DAY,0);
                    cal.set(cal.MINUTE,0);
                    cal.set(cal.MILLISECOND,0);
                }
                
                if(cal.get(cal.MONTH) == cal.DECEMBER) cal.roll(cal.YEAR,true);
                cal.roll(cal.MONTH,true);
                date2 = cal.getTime();
                
                List<Term> month = new LinkedList();
                List<Event> tmp = HibernateUtil.getSessionFactory().getCurrentSession()
                            .createCriteria(Event.class)
                            .add(Restrictions.isNotNull("term"))
                            .add(Restrictions.between("date",date1,date2))
                            .addOrder(Order.asc("date"))
                            .createAlias("term","term").add(Restrictions.eq("term.deleted",false)).list();
                
                Iterator<Event> it=tmp.iterator();
                while(it.hasNext()){
                    Event event = it.next();
                    if(!month.contains(event.getTerm()) && event.getTerm().getCourse().getId() == course.getId())
                        month.add(event.getTerm());
                }
                list.add(month);
            }
        
        return list;*/
        List<List<Term>> list = new LinkedList();
            Calendar cal = new GregorianCalendar();
            cal.setTimeInMillis(System.currentTimeMillis());
            
            Date date1;
            Date date2;

            for(int i=0;i<12;i++){
                //String month = months[cal.get(cal.MONTH)]+" "+(cal.get(cal.YEAR));
                date1 = cal.getTime();
                
                if(i==0){
                    cal.set(cal.DAY_OF_MONTH,1);
                    cal.set(cal.HOUR_OF_DAY,0);
                    cal.set(cal.MINUTE,0);
                    cal.set(cal.MILLISECOND,0);
                }
                
                if(cal.get(cal.MONTH) == cal.DECEMBER) cal.roll(cal.YEAR,true);
                cal.roll(cal.MONTH,true);
                date2 = cal.getTime();
                
                List<Term> month = new LinkedList();
                List<Event> tmp = HibernateUtil.getSessionFactory().getCurrentSession()
                            .createCriteria(Event.class)
                            .add(Restrictions.isNotNull("term"))
                            .add(Restrictions.isNotNull("date"))
                            .add(Restrictions.between("date",date1,date2))
                            .addOrder(Order.asc("date"))
                            .createAlias("term","term").add(Restrictions.eq("term.deleted",false)).list();
                
                Iterator<Event> it=tmp.iterator();
                while(it.hasNext()){
                    Event event = it.next();
                    if(!month.contains(event.getTerm()) && event.getTerm().getCourse().getId() == course.getId())
                        month.add(event.getTerm());
                }
                list.add(month);
            }
        
        return list;
     }
}
