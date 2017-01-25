package com.asap.catalog.extensions.seminar;

import com.asap.catalog.dao.*;
import java.util.Date;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import util.HibernateUtil;

@Entity
@Table(name = "department")
public class Department {

    @OneToMany(mappedBy = "department")
    private List<Location> locations;
	
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String street;

    private String zip;

    private String city;

    private String name;
    
    private boolean deleted;
   
    public Department(){
        deleted = false;
    }

    public String getCity() {
            return city;
    }

    public void setCity(String city) {
            this.city = city;
    }

    public Long getId() {
            return id;
    }

    public void setId(Long id) {
            this.id = id;
    }

    public String getName() {
            return name;
    }

    public void setName(String name) {
            this.name = name;
    }

    public String getStreet() {
            return street;
    }

    public void setStreet(String street) {
            this.street = street;
    }

    public String getZip() {
            return zip;
    }

    public void setZip(String zip) {
            this.zip = zip;
    }
    
    public String toString(){
        if(id != null){
            return getId()+"";
        }
        return null;
    }
    
    public String getStringId() {
            return id.toString();
    }

    public List<Location> getLocations() {
        return locations;
    }

    public void setLocations(List<Location> locations) {
        this.locations = locations;
    }
    
    public List<Seminar> getNextSeminars(){
        return HibernateUtil.getSessionFactory().getCurrentSession().createCriteria(Seminar.class)
                .add(Restrictions.eq("deleted",false))
                .createAlias("event.location","location").add(Restrictions.eq("location.department",this))
                .createAlias("event","event").addOrder(Order.asc("event.date")).add(Restrictions.ge("event.date",new Date()))
                .createAlias("category","category").add(Restrictions.eq("category.deleted",false))
                .setMaxResults(3).list();
    }

    public boolean getDeleted() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }
    
    
}
