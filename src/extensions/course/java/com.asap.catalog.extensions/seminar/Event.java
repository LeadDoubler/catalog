package com.asap.catalog.extensions.seminar;

import com.asap.catalog.dao.*;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Map;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.OneToMany;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

@Entity
@Table(name = "event")
public class Event {
    @Transient
    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM-yyyy"); 
    
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    @Temporal(TemporalType.DATE)
    private Date date;
    
    @ManyToOne(cascade = { CascadeType.ALL })
    @JoinColumn(name = "term")
    private Term term;
    
    @ManyToOne//(cascade = { CascadeType.ALL })
    @JoinColumn(name = "location")
    private Location location;
   
    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    public Location getLocation() {
        return location;
    }
    
    public void setLocation(Location location) {
        this.location = location;
    }
    
    public String getStringId() {
        return id.toString();
    }
    
    public void setStringId(String id) {
        this.id = new Long(id);
    }
    
    public Term getTerm() {
        return term;
    }
    
    public void setTerm(Term term) {
        this.term = term;
    }
    
    public String toString() {
        if(getId() != null){
            return getId()+"";
        }
        return null;
    }

    public String getStringDate() {
        if(date == null) return "";
        else return sdf.format(date);
    }

    public void setStringDate(String date) throws ParseException {
        if(date == "" || date == null) this.date = null;
        else this.date = sdf.parse(date);
    }
    
    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
    
    public String getFormattedDate(){
        Calendar cal = new GregorianCalendar();
        cal.setTime(date);
        return cal.DAY_OF_MONTH+"/"+cal.MONTH+" "+cal.YEAR;
    }
}
