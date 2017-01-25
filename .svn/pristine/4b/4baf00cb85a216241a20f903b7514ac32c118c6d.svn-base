/*
 * ProfileActionBean.java
 *
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package com.asap.web.profile;

import com.asap.catalog.dao.Profile;
import com.asap.catalog.dao.User;
import com.asap.security.Role;
import com.asap.security.Secure;
import com.asap.web.CatalogActionBean;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.FileBean;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.StreamingResolution;

/**
 *
 * @author asapunov
 */
public class ProfileActionBean extends CatalogActionBean  {
    
    private User user;
    private Profile profile;
    private FileBean photo;
    
    public Resolution image () {
        InputStream reader = new ByteArrayInputStream (getProfile().getPicture_binary ());
        return new StreamingResolution ( "image/jpg", reader);
    }
    
    public Resolution edit () {
       return new ForwardResolution ("/profile/edit.jsp");
    }
    
    @DefaultHandler
    public Resolution list () {
        return new ForwardResolution ("/profile/list.jsp");
    }
    
    public Resolution inlineList() {
        return new ForwardResolution ("/profile/inlineList.jsp");
    }
    
    @Secure(role=Role.MODERATOR, currentUser=true)
    public Resolution save () {
        if (photo!=null) {
            if (photo.getContentType ().startsWith ("image")) {
                byte[] bytes = new byte[(int)photo.getSize ()];
                try {
                    InputStream istream = photo.getInputStream ();
                    istream.read (bytes);
                    getProfile().setPicture_binary (bytes);
                } catch (IOException ex) {
                    ex.printStackTrace ();
                }
            }
        }
        persist(profile);
        return new ForwardResolution("/profile/list.jsp");
    }
    
    @Secure(role=Role.MODERATOR, currentUser=true)
    public Resolution delete() {
        profile.setDeleted(true);
        persist(profile);
        return new ForwardResolution("/profile/list.jsp");
    }
    
    @Secure(role=Role.MODERATOR, currentUser=true)
    public Resolution restore() {
        profile.setDeleted(false);
        persist(profile);
        return new ForwardResolution("/profile/view.jsp?profile="+profile);
    }
  
    public Profile getProfile () {
        return profile;
    }
    
    public void setProfile (Profile profile) {
        this.profile = profile;
        this.user = profile.getUser();
    }
    
    public FileBean getPhoto () {
        return photo;
    }
    
    public void setPhoto (FileBean photo) {
        this.photo = photo;
    }    

    public User getUser() {
        return user;
    }
}
