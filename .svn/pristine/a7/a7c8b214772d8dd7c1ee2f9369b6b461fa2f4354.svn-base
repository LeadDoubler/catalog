package com.asap.web.category;

import com.asap.security.Role;
import com.asap.security.Secure;
import java.util.List;
import net.sourceforge.stripes.action.RedirectResolution;
import net.sourceforge.stripes.validation.ValidationErrors;

 
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.Resolution;
import util.HibernateUtil;

import com.asap.catalog.dao.Category;
import com.asap.web.CatalogActionBean;
import java.io.StringReader;
import net.sourceforge.stripes.action.DontValidate;
import net.sourceforge.stripes.action.StreamingResolution;
import net.sourceforge.stripes.validation.SimpleError;
import net.sourceforge.stripes.validation.ValidationError;
import net.sourceforge.stripes.validation.ValidationErrorHandler;
import net.sourceforge.stripes.validation.ValidationMethod;

public class CategoryActionBean extends CatalogActionBean implements ValidationErrorHandler{
/*    @ValidateNestedProperties({
        @Validate(field = "title", required=true),
        @Validate(field = "englishTitle", required=true)
    })*/
    Category category;
    
    public Category getCategory() {
        return category;
    }
    
    public void setCategory(Category category) {
        this.category = category;
    }
    
    public List<Category> getCategories() {
        return HibernateUtil.getSessionFactory().getCurrentSession()
        .createCriteria(Category.class).list();
    }
    
    @Secure(role = Role.MODERATOR)
    public Resolution inline(){
        return new ForwardResolution("/category/inlineEdit.jsp");
    }
    
    @Secure(role = Role.MODERATOR)
    public Resolution inlineEdit(){
        return new ForwardResolution("/category/inlineEdit_1.jsp");
    }
    
    @Secure(role = Role.MODERATOR)
    public Resolution inlineNew(){
        return new ForwardResolution("/category/inlineEdit.jsp");
    }
    
    @Secure(role = Role.MODERATOR)
    public Resolution inlineAdd(){
        persist(category);
        return new RedirectResolution("/segment/Segment.action?editCategories");
    }
    
    
    @Secure(role = Role.MODERATOR)
    public Resolution inlineSave(){
        persist(category);
        return new ForwardResolution("/category/inlineView.jsp");
    }
    
    public Resolution view() {
        return new ForwardResolution("/category/view.jsp");
    }
    
    @Secure(role = Role.MODERATOR)
    public Resolution edit() {
        return new ForwardResolution("/category/edit.jsp");
    }
    
    @DefaultHandler
    @Secure(role = Role.MODERATOR)
    public Resolution manager() {
        return new RedirectResolution("/product/product.action?list");
    }
    
    @Secure(role = Role.MODERATOR)
    public Resolution delete() {
        /*deleteChild(category);*/
        category.setDeleted(true);
        persist(category);
        return new RedirectResolution ("/product/Product.action?list");
    }
    
    
    @Secure(role = Role.MODERATOR)
    public Resolution inlineRemove() {
        /*deleteChild(category);*/
        category.setDeleted(true);
        persist(category);
        return new ForwardResolution ("/category/Deleted.jsp");
    }
    
    @Secure(role = Role.MODERATOR)
    public Resolution restore() {
        category.setDeleted(false);
        persist(category);
        return new RedirectResolution ("/product/Product.action?list");
    }
    
    /*@Secure(role = Role.MODERATOR)
    public static void deleteChild(Category category) {
        Property parent = Property.forName("parent");
        
        List<Category> child = HibernateUtil.getSessionFactory()
        .getCurrentSession().createCriteria(Category.class).add(
                parent.eq(category)).list();
        if (child.size() <= 0) {
            return;
        } else {
            for (Category children : child) {
                deleteChild(children);
                children.setDeleted(true);
                persist(children);
                return;
            }
        }
    }*/
    
    @ValidationMethod(on={"saveAndRefresh","inlineSave"}) 
    public void doesNotContainAmp() { 
        if (category.getTitle() != null && category.getTitle().contains("&")){
            this.getContext().getValidationErrors().add("category.title", new SimpleError("Please remove or replace the & with something else."));
        }
        if (category.getEnglishtitle()!= null && category.getEnglishtitle().contains("&")){
            this.getContext().getValidationErrors().add("category.englishTitle", new SimpleError("Please remove or replace the & with something else"));
        }
    }
    
    @Secure(role = Role.MODERATOR)
    public Resolution save() {
        persist(
                category);
        return new ForwardResolution("/category/view.jsp");
    }
    
    @Secure(role = Role.MODERATOR)
    public Resolution saveAndRefresh(){
        System.out.println("Category - "+category.getId());
        persist(category);
        return new ForwardResolution("/category/category.jsp");
    }
    
    @Secure(role = Role.MODERATOR)
    @DontValidate
    public Resolution viewCategoryTree(){
        return new ForwardResolution("/category/category.jsp");
    }

    public Resolution handleValidationErrors(ValidationErrors errors) throws Exception {
        StringBuilder message = new StringBuilder();

        for (List<ValidationError> fieldErrors : errors.values()) {
            for (ValidationError error : fieldErrors) {
                message.append("<div class=\"error\">");
                message.append(error.getMessage(getContext().getLocale()));
                message.append("</div>");
            }
        }
        return new StreamingResolution("text/html", new StringReader(message.toString()));
    }
   
}
