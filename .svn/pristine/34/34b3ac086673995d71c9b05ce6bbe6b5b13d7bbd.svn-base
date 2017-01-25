/*
 * StructureActionBean.java
 *
 * Created on 16. februar 2007, 10:45
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package com.asap.web.explorer;

import com.asap.security.Role;
import com.asap.security.Secure;
import com.asap.web.CatalogActionBean;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.Resolution;

/**
 *
 * @author mortenandersen
 */
    @Secure(role = Role.MODERATOR)
    public class StructureActionBean extends CatalogActionBean{
    
    public Resolution edit(){
        
        return new ForwardResolution("/explorer/TreeView.jsp");
    }
    
    
}
