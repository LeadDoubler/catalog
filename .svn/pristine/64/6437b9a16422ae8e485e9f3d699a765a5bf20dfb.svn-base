package com.asap.web;

//import com.asap.catalog.dao.Page;
import net.sourceforge.stripes.action.ActionBeanContext;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.Resolution;

import com.asap.catalog.dao.User;
import java.io.Serializable;

/**
 * ActionBeanContext subclass for the Bugzooky application that manages where
 * values like the logged in user are stored.
 *
 * @author Morten Matras
 */
public class CatalogActionBeanContext extends ActionBeanContext implements Serializable {

    public void setCommit(Boolean commit) {
        this.getRequest().setAttribute("commit", commit);
    }

    public Boolean getCommit() {
        return (Boolean) this.getRequest().getAttribute("commit");
    }

    /**
     * Gets the currently logged in user, or null if no-one is logged in.
     */
    public User getUser() {
        Object object = getRequest().getSession().getAttribute("user");
        if (object != null) {
            if (object instanceof User) {
                return (User) object;
            }
        }
        return null;
    }

    /**
     * Sets the currently logged in user.
     */
    public void setUser(User currentUser) {
        getRequest().getSession().setAttribute("user", currentUser);

    }

    /**
     * Logs the user out by invalidating the session.
     */
    public void logout() {
        getRequest().getSession().setAttribute("user", null);
        getRequest().getSession().setAttribute("user_id", null);
        getRequest().getSession().removeAttribute("ld_affiliate_session_id");
        //getRequest().getSession().invalidate();
    }

    /**
     * <p>
     * Returns a resolution that can be used to return the user to the page from
     * which they submitted they current request. Most useful in situations
     * where a user-correctable error has occurred that was too difficult or
     * expensive to check at validation time. In that case an ActionBean can
     * call setValidationErrors() and then return the resolution provided by
     * this method.
     * </p>
     *
     * @return Resolution a resolution that will forward the user to the page
     * they came from
     * @throws IllegalStateException if the information required to construct a
     * source page resolution cannot be found in the request.
     *
     */
    @Override
    public Resolution getSourcePageResolution() {
        //System.out.println("GetSourcePageResolution");
        String sourcePage = this.getSourcePage();
        //getRequest().getParameter(StripesConstants.URL_KEY_SOURCE_PAGE);

        if (sourcePage == null) {
            //System.out.println("No source page");
            return new ForwardResolution("/user/UserLoggedIn.jsp");
        } else {
            //System.out.println(sourcePage);
            return new ForwardResolution(sourcePage);
        }
    }

    public void setPriceEngineAttribute() {
        if ((getServletContext().getAttribute("RealPath") == null) || (getServletContext().getAttribute("IPAddress") == null) || (getServletContext().getAttribute("Language") == null)) {
            getServletContext().setAttribute("RealPath", getRequest().getRealPath(""));
            getServletContext().setAttribute("IPAddress", getRequest().getRemoteAddr());
            getServletContext().setAttribute("Language", getRequest().getLocale().getLanguage());
        }
    }
}
