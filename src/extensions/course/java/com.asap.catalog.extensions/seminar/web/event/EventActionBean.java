package com.asap.catalog.extensions.seminar.web.event;

import com.asap.security.Role;
import com.asap.security.Secure;
import java.util.List;

import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.RedirectResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.validation.Validate;
import net.sourceforge.stripes.validation.ValidateNestedProperties;
import org.hibernate.criterion.Restrictions;
import util.HibernateUtil;

import com.asap.catalog.extensions.seminar.Event;
import com.asap.catalog.dao.Category;
import com.asap.catalog.extensions.seminar.Location;
import com.asap.catalog.extensions.seminar.Term;
import com.asap.web.CatalogActionBean;
import java.text.SimpleDateFormat;

public class EventActionBean extends CatalogActionBean {
    
    @ValidateNestedProperties({            
        @Validate(field = "date", required = true)
    })
    private Event event;

   public List<Category> getEvents() {
            return HibernateUtil.getSessionFactory().getCurrentSession()
                            .createCriteria(Event.class).list();
    }

    public List<Category> getLocations() {
            return HibernateUtil.getSessionFactory().getCurrentSession()
                            .createCriteria(Location.class).add(Restrictions.eq("deleted",false)).list();
    }

    @DefaultHandler
    public Resolution list() {
            return new ForwardResolution("/event/list.jsp");
    }

    public Resolution view() {
            return new ForwardResolution("/event/view.jsp");
    }
    
    @Secure(role = Role.MODERATOR)
    public Resolution edit() {
        return new ForwardResolution("/event/edit.jsp");
    }
    
    @Secure(role = Role.MODERATOR)
    public Resolution delete() {
        Term t = getEvent().getTerm();
        getSession().delete(getEvent());
        persist(event);
        return new RedirectResolution("/term/Term.action?view&term="+t.getId());
    }

    @Secure(role = Role.MODERATOR)
    public Resolution save(){

        persist(event);
        return new ForwardResolution("/term/Term.action?edit&term="+event.getTerm());
    }

    public Event getEvent() {
        return event;
    }

    public void setEvent(Event event1) {
        this.event = event;
    }
}
