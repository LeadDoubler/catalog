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
import net.sourceforge.stripes.validation.SimpleError;
import net.sourceforge.stripes.validation.Validate;
import net.sourceforge.stripes.validation.ValidateNestedProperties;
import net.sourceforge.stripes.validation.ValidationErrors;
import net.sourceforge.stripes.validation.ValidationMethod;
import org.hibernate.criterion.Expression;
import util.HibernateUtil;

/**
 *
 * @author mortenandersen
 */
public class AddActionBean extends CatalogActionBean{
    
    @ValidateNestedProperties({            
        @Validate(field = "username", required = true),
        @Validate(field = "firstName", required = true),
        @Validate(field = "lastName", required = true),
        @Validate(field = "email", required = true, converter = EmailTypeConverter.class)
    })
    private User user;

    private String password1;
    private String password2;
    
    @ValidationMethod
    public void canUserBeAdded(ValidationErrors errors){
/*        int uid1=-1;
        int uid2=-2;
        
        if(getUser() != null){
            if(getUser().getId() != null){
                uid1 = getUser().getId().intValue();
            }
        }
        
        if(getContext().getUser() != null){
            if(getContext().getUser().getId() != null){
                uid2 = getContext().getUser().getId().intValue();
            }
        }
            
        if(uid1 != -1 && (uid1 != uid2 || (password1 == null && password2 == null))){
            password1 = getUser().getPassword();
            password2 = password1;
        }
  */      
        long id = -1;
        if(getUser().getId() != null) id = getUser().getId();
        
        List<User> list = getSession().createCriteria(User.class)
            .add(Expression.eq("email",getUser().getEmail()))
            .add(Expression.ne("id",id)).list();
        if (!list.isEmpty()){
            errors.add("user.email",new SimpleError("Bruger med angivet email findes i forvejen."));
        }
        
        list = getSession().createCriteria(User.class)
            .add(Expression.eq("username",getUser().getUsername()))
            .add(Expression.ne("id",id)).list();
        if (!list.isEmpty()){
             errors.add("user.username",new SimpleError("Bruger med angivet brugernavn findes i forvejen."));
        }
        
        if(password1 == null || password1 == ""){
            errors.add("password1",new SimpleError("Udfyld venligst dette felt"));
        }
        if(password2 == null || password2 == ""){
            errors.add("password2",new SimpleError("Udfyld venligst dette felt"));
        }
        else if ( !password1.equals( password2 ) ){
            errors.add("password2",new SimpleError("De to passwords skal matche"));
        }
    }
        
    @DontValidate
    public User getUser() {
        return user;
    }
    
    @DontValidate
    public void setUser(User user) {
        this.user = user;
    }
    
    public Resolution save() {
        getUser().setPassword(password1);
        if (getUser().getRole() == null){
            getUser().setRole(Role.USER);
        }
        persist(getUser());
        return new RedirectResolution("/startup/Startup.action?home");
    }

    @DefaultHandler
    @DontValidate
    public Resolution edit() {
        prefill();
        return new ForwardResolution("/user/register.jsp");
    }
    
    @DontValidate
    public Resolution list() {
        return new ForwardResolution("/user/list.jsp");
    }
 
    @DontValidate
    public Resolution view() {
        return new ForwardResolution("/user/view.jsp");
    }
    
    @DontValidate
    public Resolution delete() {
        getSession().delete(getUser());
        persist(user);
        return new ForwardResolution("/user/list.jsp");
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

    protected void prefill() {
       
    }

}
