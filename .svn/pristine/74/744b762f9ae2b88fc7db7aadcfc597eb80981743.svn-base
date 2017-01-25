package com.asap.catalog.extensions.seminar;

import com.asap.catalog.extensions.shop.dao.*;
import com.asap.catalog.extensions.seminar.Event;
import com.asap.catalog.dao.*;
import com.asap.catalog.extensions.shop.dao.Product;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import com.asap.catalog.enums.CourseComplexity;
import com.asap.catalog.enums.EntranceLevel;
import javax.persistence.OneToOne;

@Entity
@DiscriminatorValue("SEMINAR")
public class Seminar extends Product {

    @OneToOne
    private Event event;

    public Event getEvent() {
        return event;
    }

    public void setEvent(Event event) {
        this.event = event;
    }
    
    public String getType(){
        return "Seminar";
    }
}  
