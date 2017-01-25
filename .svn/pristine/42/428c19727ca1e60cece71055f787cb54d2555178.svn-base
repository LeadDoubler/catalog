/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.asap.web.user;

import com.asap.catalog.dao.User;
import com.asap.configuration.Configuration;
import com.asap.web.CatalogActionBean;
import com.blob.mail.MailService;
import java.util.Date;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.Resolution;
import org.apache.commons.lang.RandomStringUtils;
import org.hibernate.criterion.Restrictions;
import org.mortena.mail.Email;

/**
 *
 * @author mortenmatras
 */
public class PasswordServiceActionBean extends CatalogActionBean {

    private String username;
    User user;

    @DefaultHandler
    public Resolution generateNewPasswordLink() {
        user = (User) this.getSession().createCriteria(User.class).add(Restrictions.eq("username", getUsername())).setMaxResults(1).uniqueResult();
        //String randomString = RandomCodeGenerator.randomstring();
        String randomString = RandomStringUtils.randomAlphanumeric(16);
        TemporaryKey key = new TemporaryKey();
        key.setUser(user);
        key.setGeneratedTime(new Date());
        key.setStoredKey(randomString);
        persist(key);
        Email mail = new Email();
        mail.setTo(user.getEmail());
        mail.setTemplateName("sendGeneratedKey");
        mail.setSubject("Recover password");
        mail.setProperty("key", key.getStoredKey());
        mail.setProperty("link", Configuration.getInstance().getProps().getProperty("link"));
        MailService.getInstance().sendAsync(mail);
        //user.setGeneratedKey(randomString);
        return new ForwardResolution("/user/linkGenerated.jsp");
    }

    /**
     * @return the username
     */
    public String getUsername() {
        return username;
    }

    /**
     * @param username the username to set
     */
    public void setUsername(String username) {
        this.username = username;
    }
}
