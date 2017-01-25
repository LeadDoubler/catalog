package com.asap.web.user;


import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.DontValidate;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.StreamingResolution;
import net.sourceforge.stripes.validation.EmailTypeConverter;
import net.sourceforge.stripes.validation.LocalizableError;
import net.sourceforge.stripes.validation.Validate;
import net.sourceforge.stripes.validation.ValidationErrors;
import net.sourceforge.stripes.validation.ValidationMethod;

import org.hibernate.criterion.Restrictions;

import com.asap.catalog.dao.User;
import com.asap.configuration.Configuration;
import com.asap.web.CatalogActionBean;

import org.mortena.mail.Email;

/**
 * 
 * @author Jens Rosenberg
 */
public class ForgotPassActionBean extends CatalogActionBean /*
												 * ValidationErrorHandler
														 */{
	@Validate(required = true, converter = EmailTypeConverter.class)
	private String email;

	private User user;

	public Resolution send(){
        try {
            Email email = new Email();
            email.setTemplateName("sendpass");
            email.setProperty("user",getUser());
            email.setProperty("website", Configuration.getInstance().getProps().getProperty("url"));
            email.setTo(getUser().getEmail());
            email.setSubject(Configuration.getInstance().getProps().getProperty("mail.forgotpassword.subject"));
            email.send();
            
            /*Configuration cfg = new Configuration();
            cfg.setServletContextForTemplateLoading(
                    getContext().getServletContext(), "template");
            Map root = new HashMap();
            
            root.put("user", user);
            
            Template t = cfg.getTemplate("sendpass.ftl");
            
            HTMLMailSender htmlMailSender = new HTMLMailSender();
            htmlMailSender.setTemplate(t);
            htmlMailSender.setDataModel(root);
            htmlMailSender.setToAddress(user.getEmail());
            htmlMailSender.setFromAddress("do-not-reply@kodeord");
            htmlMailSender.setSubject("Kodeordservice");
            
            htmlMailSender.sendMail();
             * */
        
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        
        return new ForwardResolution("/user/forgotpassreply.jsp");//new StreamingResolution("text/html", "<br><center><strong>Der er sendt en email til: "+user.getEmail()+" med brugeroplysninger.</strong></center><br>");
    }

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@DefaultHandler
	@DontValidate
	public Resolution forgotEmail() {
		return new ForwardResolution("/user/forgotpass.jsp");
	}

	@ValidationMethod
	public void handleValidationErrors(ValidationErrors errors)
			throws Exception {
		User foundUser = (User) getSession().createCriteria(User.class).add(
				Restrictions.eq("email", email)).uniqueResult();
		if (foundUser == null) {
			errors.add("email", new LocalizableError("emailDoesNotExist"));
		} else {
			setUser(foundUser);
		}
		/*
		 * StringBuilder message = new StringBuilder();
		 * 
		 * for (List<ValidationError> fieldErrors : errors.values()) { for
		 * (ValidationError error : fieldErrors) { message.append("<div
		 * class=\"error\">");
		 * message.append(error.getMessage(getContext().getLocale()));
		 * message.append("</div>"); } }
		 */
		// return new ForwardResolution("/user/forgotpass.jsp");
		// return new StreamingResolution("text/html", new
		// StringReader(message.toString()));
	}

    /**
     * @return the user
     */
    public User getUser() {
        return user;
    }

    /**
     * @param user the user to set
     */
    public void setUser(User user) {
        this.user = user;
    }
}
