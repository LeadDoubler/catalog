/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.asap.web.log;

import com.asap.web.CatalogActionBean;
import com.blob.pas.systemlog.AgentLog;
import java.util.List;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.Resolution;
import org.hibernate.criterion.Order;

/**
 *
 * @author mortenmatras
 */
//@UrlBinding(value="/EmailLog.action")
public class AgentLogActionBean extends CatalogActionBean {
    
    private String PREPATH = "/WEB-INF/view/agentLog/";
    
    public Resolution list(){
        return new ForwardResolution(PREPATH+this.getContext().getEventName()+".jsp");
    }
     
    public List<AgentLog> getLogs(){
        return this.getSession().createCriteria(AgentLog.class).addOrder(Order.desc("created"))
                .list();
    }

}
