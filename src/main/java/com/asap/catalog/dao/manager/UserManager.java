package com.asap.catalog.dao.manager;

import com.asap.catalog.dao.User;
import com.asap.security.Role;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.criterion.Expression;
import org.hibernate.criterion.Restrictions;

import util.HibernateUtil;

public class UserManager {

    public List<User> getUsers() {
        return HibernateUtil.getSessionFactory().getCurrentSession().createCriteria(User.class).add(Expression.ne("role",Role.GUEST)).list();
    }
    
    public static User getUser(String username){
        List<User> list = HibernateUtil.getSessionFactory().getCurrentSession().createCriteria(User.class)
            .add(Restrictions.eq("username",username)).list();
        if(!list.isEmpty()) return list.get(0);
        else return null;
    }
    
    public static Criteria removeDeactivated(Criteria crit){
        crit.add(
                Expression.or(
                    Expression.isNull("deactivated"), 
                    Expression.eq("deactivated", "no")
                )
                );
        //crit.add(Expression.not( Expression.eq("deactivated", "yes") ) );
        return crit;
    }
    
}