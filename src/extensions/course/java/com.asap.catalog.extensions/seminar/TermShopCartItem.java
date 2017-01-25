package com.asap.catalog.extensions.seminar;

import com.asap.catalog.extensions.shop.dao.*;
import com.asap.catalog.extensions.seminar.Term;
import com.asap.catalog.extensions.seminar.Event;
import com.asap.catalog.dao.*;
import com.asap.catalog.extensions.shop.dao.ShopCartItem;
import java.text.SimpleDateFormat;
import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity 
@DiscriminatorValue ("term")
public class TermShopCartItem extends ShopCartItem {
    
    @ManyToOne(optional=true)
    private Term term;
    
    public String getDescription () {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM-yyyy"); 
        String res = term.getCourse().getTitle();
        for(Event event:term.getEvents()){
            if(event.getDate() != null) res += "<br>"+sdf.format(event.getDate())+" "+event.getLocation().getFullname();
        }
        return res;
    }
    
    public String getName () {
        return term.getCourse().getTitle();
    }
    
    public Term getTerm () {
        return term;
    }
    
    public void setTerm (Term term) {
        this.term = term;
    }
    
    public Double getPrice () {
        return term.getCourse ().getPrice ();
    }
    
    public boolean equalsItem (Object item) {
        if (item instanceof Term) {
            return term.getId ().equals (((Term) item).getId ());
        }
        return false;
    }
}
