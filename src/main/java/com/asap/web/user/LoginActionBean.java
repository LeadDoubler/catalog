package com.asap.web.user;

import com.asap.catalog.dao.User;
import com.asap.util.PasswordHash;
import com.asap.web.CatalogActionBean;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.DontValidate;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.RedirectResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.validation.LocalizableError;
import net.sourceforge.stripes.validation.SimpleError;
import net.sourceforge.stripes.validation.Validate;
import net.sourceforge.stripes.validation.ValidationErrors;
import net.sourceforge.stripes.validation.ValidationMethod;
import org.hibernate.criterion.Restrictions;

/*
 * LoginActionBean.java
 *
 * Created on 20. marts 2007, 15:13
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */
/**
 *
 * @author Jens Rosenberg, Morten Matras
 */
public class LoginActionBean extends CatalogActionBean {

    @Validate(required = true, on = "login")
    private String username;
    @Validate(required = true, on = "login")
    private String password;
    private String targetUrl;
    private User user;
    private String message;

    @ValidationMethod(on = "login")
    public void userExists(ValidationErrors errors) {
        User foundUser = (User) getSession().createCriteria(User.class).add(Restrictions.eq("username", getUsername())).uniqueResult();
        if (foundUser == null) {
            errors.add("username", new LocalizableError("login.userDoesNotExist"));
        } else {
            try {
                if (PasswordHash.validatePassword(password, foundUser.getPassword())) {
                    setUser(foundUser);
                    return;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            if (!foundUser.getPassword().equals(getPassword())) {
                errors.add("password", new LocalizableError("login.wrong.password"));
            } else {
                setUser(foundUser);
            }
        }
    }

    @DontValidate
    @DefaultHandler
    public Resolution init() {
        System.out.println("Returning login.jsp from init.." + targetUrl);
        return new ForwardResolution("/user/Login.jsp");
    }

    public Resolution goToLogin() {
        return new ForwardResolution("/user/Login2.jsp");
    }

    public Resolution goToSignup() {
        System.out.println("Returning login.jsp from goToSignup..");
        return new ForwardResolution("/user/Login.jsp");
    }

    @DontValidate
    public Resolution initAjax() {
        return new ForwardResolution("/user/LoginAjax.jsp");
    }

    private ValidationErrors errors;

    public Resolution ajaxLogin() {
        ValidationErrors errors = this.getContext().getValidationErrors();
        if (username == null) {
            errors.add("username", new SimpleError("username.null"));
        }
        if (password == null) {
            errors.add("password", new SimpleError("password.null"));
        }
        userExists(errors);
        this.setErrors(errors);
        if (errors.isEmpty()) {
            getContext().getRequest().getSession().setAttribute("user", getUser());
        }
        return new ForwardResolution("/WEB-INF/view/login/ajaxLogin.jsp");

    }

    public Resolution login() {
        //  if(user.getPassword() == password){
        getContext().getRequest().getSession().setAttribute("user", getUser());
        //user.setLastLogin(new Date());
        persist(getUser());
        String targetU = (String) getContext().getRequest().getSession().getAttribute("targetUrl");
        if (targetU != null) {
            return new RedirectResolution(targetU);
        }
        if (this.getTargetUrl() != null) {
            String target = getTargetUrl().replaceFirst("BLOBANDBLOB", "?");
            target = target.replaceAll("BLOBANDBLOB", "&");
            return new RedirectResolution(target);
        }
        return new ForwardResolution("/user/UserLoggedIn.jsp");//"/scoreboard/stripes/"+user.getRole()+".jsp");
        //}else{
        //   return new ForwardResolution( "/index.htm" );
        //}
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getTargetUrl() {
        return targetUrl;
    }

    public void setTargetUrl(String targetUrl) {
        this.targetUrl = targetUrl;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    /**
     * @return the errors
     */
    public ValidationErrors getErrors() {
        return errors;
    }

    /**
     * @param errors the errors to set
     */
    public void setErrors(ValidationErrors errors) {
        this.errors = errors;
    }

    /**
     * @return the message
     */
    public String getMessage() {
        return message;
    }

    /**
     * @param message the message to set
     */
    public void setMessage(String message) {
        this.message = message;
    }
}
