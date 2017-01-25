package com.asap.catalog.dao.manager;

import com.asap.catalog.dao.Profile;
import java.util.List;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import util.HibernateUtil;

public class ProfileManager {

   public List<Profile> getProfiles () {
        return HibernateUtil.getSessionFactory().getCurrentSession()
            .createCriteria (Profile.class)
            .createAlias("user","user")
            .add(Restrictions.eq("deleted",false))
            .addOrder(Order.asc("user.lastName")).list ();
    }
    
    public int getNrOfProfiles() {
        List<Profile> profiles = getProfiles();
        if(profiles == null) return 0;
        else return profiles.size();
    }
}