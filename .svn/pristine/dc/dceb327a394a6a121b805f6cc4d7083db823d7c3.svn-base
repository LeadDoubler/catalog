/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.asap.web.user;

import com.asap.catalog.dao.User;
import com.asap.catalog.dao.UserPassword;
import com.asap.web.CatalogActionBean;
import com.blob.mail.MailService;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.RedirectResolution;
import net.sourceforge.stripes.action.Resolution;
import org.apache.commons.lang.RandomStringUtils;
import org.hibernate.criterion.Restrictions;
import org.mortena.mail.Email;

/**
 *
 * @author mortenmatras
 */
public class ResetPasswordActionBean extends CatalogActionBean {

    private String username;
    private String oldPassword;
    private String password1;
    private String password2;
    private String key;
    private String message;

    public Resolution init() {
        return new ForwardResolution("/user/ResetPassword.jsp");
    }

    public Resolution resetPassword() {
        return new ForwardResolution("/user/ResetPasswordLink.jsp");
    }

    public Resolution changePassword() {
        return new ForwardResolution("/user/changePassword.jsp");
    }

    public Resolution generateNewPasswordLink() {
        Resolution res = new ForwardResolution("/user/linkGenerated.jsp");
        User user = (User) this.getSession().createCriteria(User.class).add(Restrictions.eq("username", getUsername())).setMaxResults(1).uniqueResult();
        if (user != null) {
            //String randomString = RandomCodeGenerator.randomstring();
            String randomString = RandomStringUtils.randomAlphanumeric(16);
            TemporaryKey tk = new TemporaryKey();
            tk.setUser(user);
            tk.setGeneratedTime(new Date());
            tk.setStoredKey(randomString);
            persist(tk);
            Email mail = new Email();
            mail.setTo(user.getEmail());
            mail.setTemplateName("sendGeneratedKey");
            mail.setSubject("Recover password");
            mail.setProperty("key", tk.getStoredKey());
            HttpServletRequest request = this.getContext().getRequest();
            String url = request.getRequestURL().toString().replaceAll(request.getServletPath(), "");
            mail.setProperty("link", url);
            MailService.getInstance().sendAsync(mail);
            //user.setGeneratedKey(randomString);
        } else {
            res = new RedirectResolution("/user/ResetPassword.action?resetPassword").addParameter("message", "No user found");
        }
        return res;
    }

    public Resolution newPassword() {
        String messageText = "Please fill out all input fields";
        if (getPassword1() != null && getPassword2() != null && getOldPassword() != null && getUsername() != null) {
            if (getPassword1().equals(getPassword2()) && getPassword1().length() > 5) {
                User user = (User) this.getSession().createCriteria(User.class).add(Restrictions.eq("username", getUsername())).setMaxResults(1).uniqueResult();
                if (user != null) {
                    if (user.encryptPassword(getOldPassword()).equals(user.getPassword())) {
                        List<UserPassword> userPasswords = user.getUserPasswords();

                        int count = 0;
                        boolean valid = true;
                        for (int i = userPasswords.size() - 1; i >= 0 && count < 10 && valid; i--) {
                            if (user.encryptPassword(getPassword1()).equals(userPasswords.get(i).getPassword())) {
                                valid = false;
                            }
                            count++;
                        }
                        if (valid) {
                            user.setPassword(user.encryptPassword(getPassword1()));
                            user.setFirstLogOn(false);
                            persist(user);
                            persist(user.addToPasswordList());
                            return new ForwardResolution("/user/passwordChanged.jsp");
                        } else {
                            messageText = "For security reasons, your password cannot match the last 10 passwords you've had";
                        }
                    } else {
                        messageText = "Username and password doesn't match";
                    }
                } else {
                    messageText = "Username not found";
                }
            } else {
                messageText = "Please use a password with at least 6 characters and make sure they match";
            }
        }
        return new RedirectResolution("/user/ResetPassword.action?changePassword").addParameter("username", getUsername()).addParameter("message", messageText);
    }

    public Resolution reset() {
        String messageText = "Please fill out all input fields";
        if (getPassword1() != null && getPassword2() != null && getUsername() != null) {
            if (getPassword1().equals(getPassword2()) && getPassword1().length() > 5) {
                User user = (User) this.getSession().createCriteria(User.class).add(Restrictions.eq("username", getUsername())).setMaxResults(1).uniqueResult();
                if (user != null) {
                    List<UserPassword> userPasswords = user.getUserPasswords();

                    int count = 0;
                    boolean valid = true;
                    for (int i = userPasswords.size() - 1; i >= 0 && count < 10 && valid; i--) {
                        if (user.encryptPassword(getPassword1()).equals(userPasswords.get(i).getPassword())) {
                            valid = false;
                        }
                        count++;
                    }

                    if (valid) {
                        TemporaryKey tk = (TemporaryKey) this.getSession().createCriteria(TemporaryKey.class).add(Restrictions.eq("user", user)).add(Restrictions.eq("storedKey", getKey())).setMaxResults(1).uniqueResult();
                        if (tk != null) {
                            Date date = new Date();
                            Calendar cal = new GregorianCalendar();
                            cal.setTime(tk.getGeneratedTime());
                            cal.add(Calendar.HOUR, 3);

                            if (date.before(cal.getTime())) {
                                try {
                                    user.setPassword(user.encryptPassword(getPassword1()));
                                    user.setFirstLogOn(false);
                                    persist(user.addToPasswordList());

                                    cal.add(Calendar.DATE, -1);
                                    tk.setGeneratedTime(cal.getTime());
                                    persist(tk);

                                    persist(user);
                                    getContext().getRequest().getSession().setAttribute("user", user);
                                    return new ForwardResolution("/user/NewPasswordSet.jsp");
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            } else {
                                messageText = "Reset link closed. Please try resetting again";
                            }
                        } else {
                            messageText = "Reset link closed. Please try resetting again";
                        }
                    } else {
                        messageText = "For security reasons, your password cannot match the last 10 passwords you've had";
                    }
                } else {
                    messageText = "Username not found";
                }
            } else {
                messageText = "Please use a password with at least 6 characters and make sure they match";
            }
        }
        return new RedirectResolution("/user/ResetPassword.action?init").addParameter("key", key).addParameter("username", getUsername()).addParameter("message", messageText);
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

    /**
     * @return the password1
     */
    public String getPassword1() {
        return password1;
    }

    /**
     * @param password1 the password1 to set
     */
    public void setPassword1(String password1) {
        this.password1 = password1;
    }

    /**
     * @return the password2
     */
    public String getPassword2() {
        return password2;
    }

    /**
     * @param password2 the password2 to set
     */
    public void setPassword2(String password2) {
        this.password2 = password2;
    }

    /**
     * @return the key
     */
    public String getKey() {
        return key;
    }

    /**
     * @param key the key to set
     */
    public void setKey(String key) {
        this.key = key;
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

    /**
     * @return the oldPassword
     */
    public String getOldPassword() {
        return oldPassword;
    }

    /**
     * @param oldPassword the oldPassword to set
     */
    public void setOldPassword(String oldPassword) {
        this.oldPassword = oldPassword;
    }
}
