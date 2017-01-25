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
import com.asap.configuration.Configuration;
import com.asap.security.Role;
import com.asap.security.Secure;
import com.asap.web.CatalogActionBean;
import java.util.List;
import java.util.Locale;
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
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Expression;
import org.hibernate.criterion.Order;
import org.mortena.mail.Email;
import util.HibernateUtil;

/**
 *
 * @author mortenandersen
 */
public class RegisterActionBean extends CatalogActionBean{
    
    @ValidateNestedProperties({            
        //@Validate(field = "username", required = true),
        @Validate(field = "firstName", required = true),
        @Validate(field = "lastName", required = true),
        @Validate(field = "email", required = true, converter = EmailTypeConverter.class)
    })
    private User user;
    
    private int low = 0,high = 30;

    private String password1;
    private String password2;
    private String sortBy;
    private Order order;
    
    private String filterFirstName;
    
    private String filterLastName;
    
    private String filterUsername;
    
    private boolean showDeactivated;

    public void changeCurrentUser() {
        if (this.getContext().getUser()!=null && this.getContext().getUser().getId() != null && this.getContext().getUser().getId().equals(getUser().getId() ) ) {
            getContext().setUser(user);
        }
    }

    public Criteria getSearchCriteria() throws HibernateException {
        Criteria crit = HibernateUtil.getSessionFactory().getCurrentSession().createCriteria(User.class); //.add(Expression.ne("role",Role.GUEST))
        if (filterFirstName != null) {
            crit.add(Expression.like("firstName", "%" + filterFirstName + "%"));
        }
        if (filterLastName != null) {
            crit.add(Expression.like("lastName", "%" + filterLastName + "%"));
        }
        if (filterUsername != null) {
            crit.add(Expression.like("username", "%" + filterUsername + "%"));
        }
        if (isShowDeactivated()) {
            crit.add(Expression.eq("deactivated", "yes"));
        } else {
            crit.add(Expression.or(Expression.eq("deactivated", "no"), Expression.isNull("deactivated")));
        }
        if (getOrder() != null) {
            crit.addOrder(getOrder());
        }
        return crit;
    }
    
     public List<User> getUsers() {
        Criteria crit = getSearchCriteria();
         crit.setMaxResults(30);
         crit.setFirstResult(low);
         return crit.list();
    }
     
     public int getPages(){
         if (getUsers().size()>0){
            return getAllUsers().size() / getUsers().size();
         }
         else return 0;
                 
     }
     
      public List<User> getAllUsers() {
         Criteria crit = this.getSearchCriteria();
         if (getOrder() !=null){
             crit.addOrder(getOrder());
         }
         return crit.list();
    }
    
    @ValidationMethod
    public void canUserBeAdded(ValidationErrors errors){
        System.out.println("canUserBeAdded - started");
        int uid1=-1;
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
        
        
        doesUserExist(errors, user);
        
        checkPasswords(errors,password1,password2);
       
    }
        
    public User getUser() {
        return user;
    }

    public static void sendWelcomeMail(User user) {
       sendWelcomeMail(user,null);
    }

    public static void sendWelcomeMail(User user , Locale locale) {
        //New user added. Send email with credentials:
        Email email = new Email();
        email.setTemplateName("Welcome");
        if (locale != null){
            email.setLocale(locale);
        }
        email.setProperty("user", user);
        email.setProperty("website", Configuration.getInstance().getProps().getProperty("url"));
        email.setTo(user.getEmail());
        if (locale != null && "da".equals(locale.getLanguage()) ){
            email.setSubject(Configuration.getInstance().getProps().getProperty("mail.welcome.subject_da"));
        }
        email.send();
    }
    
    public void setUser(User user) {
        this.user = user;
    }

    @DontValidate
    @Secure(role = Role.USER)
    public Resolution saveExisting() {
        User sessionUser = this.getContext().getUser();
        if (!sessionUser.getId().equals(this.getUser().getId()) && sessionUser.getRole() != Role.ADMINISTRATOR){
            //Illegal. Go to home.
           return new RedirectResolution("/startup/Startup.action?home");
        }
        System.out.println("Editing user: "+user.getUsername());
        getUser().setPassword(password1);
        persist(getUser());
        changeCurrentUser();
        return new RedirectResolution("/startup/Startup.action?home");
    }
    
    public Resolution save() {
        if (user.getUsername()==null ){
            user.setUsername(user.getEmail());
        }
        System.out.println("Adding user: "+user.getUsername());
        getUser().setPassword(password1);
        if (getUser().getUsername() == null ){
            getUser().setUsername(getUser().getEmail());
        }
        if (getUser().getId()==null){
            sendWelcomeMail(user);
        }

       
        persist(getUser());
        changeCurrentUser();
        if (targetUrl == null){
            System.out.println("redirecting to: "+"/startup/Startup.action?home");
            return new RedirectResolution("/startup/Startup.action?home");
        }
        else{
            System.out.println("redirecting to: "+targetUrl);
            return new RedirectResolution(targetUrl);
        }
        
    }

    private String targetUrl;

    @DefaultHandler
    @DontValidate
    public Resolution init() {
        if(getUser() != null && getContext().getUser() != null){
            if(getUser().getId().intValue() != getContext().getUser().getId().intValue()
                && getUser().getRole().getValue() > getContext().getUser().getRole().getValue()){
                return new RedirectResolution("/user/Login.action");
            }
        }
        prefill();
        return new ForwardResolution("/user/register.jsp");
    }

    @DontValidate
    public Resolution ajax() {
        if(getUser() != null && getContext().getUser() != null){
            if(getUser().getId().intValue() != getContext().getUser().getId().intValue()
                && getUser().getRole().getValue() > getContext().getUser().getRole().getValue()){
                return new RedirectResolution("/user/Login.action");
            }
        }
        this.prefill();
        return new ForwardResolution("/user/registerAjax.jsp");
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
 
    @Secure(role = Role.ADMINISTRATOR)//,currentUser=true)
    @DontValidate
    public Resolution view() {
        return new ForwardResolution("/user/view.jsp");
    }
    
    @Secure(role = Role.ADMINISTRATOR)
    @DontValidate
    public Resolution delete() {
         if(getUser().getRole().getValue() > getContext().getUser().getRole().getValue()){
            return new RedirectResolution("/user/Login.action");
        }
        getUser().setDeactivated("yes");
        persist(getUser());
        /*
        getUser().setRole(Role.GUEST);
        persist(user);
        //getSession().delete(getUser());*/
        return new ForwardResolution("/user/list.jsp");
    }
    
    @Secure(role = Role.ADMINISTRATOR)
    @DontValidate
    public Resolution reactivate() {
         if(getUser().getRole().getValue() > getContext().getUser().getRole().getValue()){
            return new RedirectResolution("/user/Login.action");
        }
        getUser().setDeactivated("no");
        persist(user);
        //getSession().delete(getUser());
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

    public String getFilterFirstName() {
        return filterFirstName;
    }

    public void setFilterFirstName(String filterFirstName) {
        this.filterFirstName = filterFirstName;
    }

    public String getFilterLastName() {
        return filterLastName;
    }

    public void setFilterLastName(String filterLastName) {
        this.filterLastName = filterLastName;
    }

    public String getFilterUsername() {
        return filterUsername;
    }

    public void setFilterUsername(String filterUsername) {
        this.filterUsername = filterUsername;
    }

    public boolean isShowDeactivated() {
        return showDeactivated;
    }

    public void setShowDeactivated(boolean showDeactivated) {
        this.showDeactivated = showDeactivated;
    }

    public static void checkPasswords(ValidationErrors errors, String password1, String password2) {
         if(password1 == null || password1.equals("") ){
            errors.add("password1",new SimpleError("fillout"));
        }
        if(password2 == null || password2.equals("") ){
            errors.add("password2",new LocalizableError("fillout"));
        }
        else if ( !password1.equals( password2 ) ){
            errors.add("password2",new LocalizableError("Passwordfieldmatch"));
        }
         //password for redantenna must be between 6 and 12 characters
        if(password1.length() < 6 || password1.length() > 12){
            errors.add("password1",new LocalizableError("Passwordlength"));
        }
    }

    public static void doesUserExist(ValidationErrors errors, User user) throws HibernateException {
        long id = -1;
        if(user != null && user.getId() != null){
            id = user.getId();
        }
        Session session =HibernateUtil.getSessionFactory().getCurrentSession(); 
        List<User> list = session.createCriteria(User.class).add(Expression.eq("email", user.getEmail())).add(Expression.not(Expression.eq("id", id))).list();
        if (!list.isEmpty()) {
            errors.add("user.email", new LocalizableError("emailIsRegistered"));
        }
//        removed because redAntenna does not require username to be unique
//        list = session.createCriteria(User.class).add(Expression.eq("username", user.getUsername())).add(Expression.not(Expression.eq("id", id))).list();
//        if (!list.isEmpty()) {
//            errors.add("user.username", new LocalizableError("username.exist"));
//        }
    }

    /**
     * @return the targetUrl
     */
    public String getTargetUrl() {
        return targetUrl;
    }

    /**
     * @param targetUrl the targetUrl to set
     */
    public void setTargetUrl(String targetUrl) {
        this.targetUrl = targetUrl;
    }

    protected void prefill() {
        if(this.user!= null){

        }
    }

}
