/*
 * Register.java
 *
 * Created on 5. marts 2007, 09:41
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package com.asap.web.user;

import com.asap.catalog.dao.User;
import com.asap.security.Role;
import com.asap.security.Secure;
import com.asap.web.CatalogActionBean;
import java.util.List;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.DontValidate;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.RedirectResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.validation.EmailTypeConverter;
import net.sourceforge.stripes.validation.LocalizableError;
import net.sourceforge.stripes.validation.SimpleError;
import net.sourceforge.stripes.validation.Validate;
import net.sourceforge.stripes.validation.ValidateNestedProperties;
import net.sourceforge.stripes.validation.ValidationErrors;
import net.sourceforge.stripes.validation.ValidationMethod;
import org.hibernate.Criteria;
import org.hibernate.criterion.Expression;
import org.hibernate.criterion.Order;
import util.HibernateUtil;

/**
 *
 * @author mortenandersen
 */
public class UserActionBean extends CatalogActionBean{
    
  
    private User user;
    
    private int low = 0,high = 30;

    private String password1;
    private String password2;
    private String sortBy;
    private Order order;
    
     public List<User> getUsers() {
         Criteria crit = HibernateUtil.getSessionFactory().getCurrentSession().createCriteria(User.class); //.add(Expression.ne("role",Role.GUEST))
         if (getOrder() !=null){
             crit.addOrder(getOrder());
         }
         crit.setMaxResults(30);
         crit.setFirstResult(low);
         return crit.list();
    }
     
     public int getPages(){
         return getAllUsers().size() / getUsers().size();
                 
     }
     
      public List<User> getAllUsers() {
         Criteria crit = HibernateUtil.getSessionFactory().getCurrentSession().createCriteria(User.class); //.add(Expression.ne("role",Role.GUEST))
         if (getOrder() !=null){
             crit.addOrder(getOrder());
         }
         return crit.list();
    }
  
        
    public User getUser() {
        return user;
    }
    
    public void setUser(User user) {
        this.user = user;
    }
    
    
    public Resolution save() {
        System.out.println("Adding user: "+user.getUsername());
        getUser().setPassword(getUser().encryptPassword(password1));
        persist(getUser());
        return new RedirectResolution("/user/registered.jsp");
    }

    @DefaultHandler
    @DontValidate
    public Resolution init() {
        if(getUser() != null && getContext().getUser() != null){
            if(getUser().getId().intValue() != getContext().getUser().getId().intValue()
                && getUser().getRole().getValue() > getContext().getUser().getRole().getValue()){
                return new RedirectResolution("/user/Login.action");
            }
        }
        return new ForwardResolution("/user/register.jsp");
    }

    
    @DontValidate
    @Secure(role=Role.ADMINISTRATOR,currentUser=true)
    public Resolution edit() {
        if(getUser() != null && getContext().getUser() != null){
            if(getUser().getId().intValue() != getContext().getUser().getId().intValue()
                && getUser().getRole().getValue() > getContext().getUser().getRole().getValue()){
                return new RedirectResolution("/user/Login.action");
            }
        }
        return new ForwardResolution("/user/edit.jsp");
    }
    
    
    @Secure(role = Role.ADMINISTRATOR)
    @DontValidate
    public Resolution list() {
        return new ForwardResolution("/user/list.jsp");
    }
 
    @Secure(role = Role.ADMINISTRATOR,currentUser=true)
    @DontValidate
    public Resolution view() {
        return new ForwardResolution("/user/view.jsp");
    }
    
    @Secure(role = Role.USER,currentUser=true)
    @DontValidate
    public Resolution deactivate() {
         if(!getUser().getId().equals(getContext().getUser().getId()) && ! getContext().getUser().getRole().equals(Role.ADMINISTRATOR)){
            return new RedirectResolution("/user/Login.action");
        }
        getUser().setDeactivated("yes");
        if (this.getUser().getId().equals(this.getContext().getUser().getId())){
            this.getContext().setUser(user);
        }
        persist(user); 
        /*
        getUser().setRole(Role.GUEST);
        persist(user);
        //getSession().delete(getUser());*/
        return new ForwardResolution("/startup/Startup.action?home");
    }
    
    @Secure(role = Role.ADMINISTRATOR)
    @DontValidate
    public Resolution delete() {
         if(getUser().getRole().getValue() > getContext().getUser().getRole().getValue()){
            return new RedirectResolution("/user/Login.action");
        }
        getUser().setDeactivated("yes");
        persist(user);
         /*
        getUser().setRole(Role.GUEST);
        persist(user);
        //getSession().delete(getUser());*/
        return new ForwardResolution("/user/list.jsp");
    }
    
    @Secure(role = Role.USER,currentUser=true)
    @DontValidate
    public Resolution reactivate() {
         if(getUser().getRole().getValue() > getContext().getUser().getRole().getValue()){
            return new RedirectResolution("/user/Login.action");
        }
         getUser().setDeactivated("no");
        persist(user);
        this.getContext().setUser(user);
        this.getContext().getRequest().setAttribute("message", "Konto er genaktiveret");
        //getSession().delete(getUser());
        return new ForwardResolution("/startup/Startup.action?home");
    }
    
    @DontValidate
    public String getPassword1() {
        return password1;
    }

    @DontValidate
    public void setPassword1(String password1) {
        this.password1 = password1;
    }
    
    @DontValidate
    public String getPassword2() {
        return password2;
    }

    @DontValidate
    public void setPassword2(String password2) {
        this.password2 = password2;
    }
    
    @DontValidate
    public Resolution forgotPassword(){
        return new ForwardResolution("/user/forgotpass.jsp" );
    }

    public int getHigh() {
        return high;
    }

    public void setHigh(int high) {
        this.high = high;
    }

    public int getLow() {
        return low;
    }

    public void setLow(int low) {
        this.low = low;
    }

    public String getSortBy() {
        return sortBy;
    }

    public void setSortBy(String sortBy) {
        this.sortBy = sortBy;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

}
