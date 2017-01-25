/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.asap.web.emailLog;

import com.blob.pas.DefaultActionBean;
import com.blob.pas.systemlog.EmailLog;
import java.util.List;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.Resolution;
import org.hibernate.Criteria;
import org.hibernate.criterion.Expression;
import org.hibernate.criterion.Order;

/**
 *
 * @author mortenmatras
 */
//@UrlBinding(value="/EmailLog.action")
public class EmailLogActionBean extends DefaultActionBean {
    
    private String PREPATH = "/WEB-INF/view/emailLog/";
    
    public Resolution list(){
        return new ForwardResolution(PREPATH+this.getContext().getEventName()+".jsp");
    }
     
    public List<EmailLog> getLogs(){
        return this.getSession().createCriteria(EmailLog.class).addOrder(Order.desc("created"))
                .list();
    }

    public Class getComponentClass(){
        return EmailLog.class;
    }

    @Override
    public String getType() {
        return "emailLog";
    }

    @Override
    public Object getComponent() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Criteria addExtraCriterias(Criteria crit){
        if(this.getSearchText()!= null && this.getSearchText().length()>0){
            crit.add(Expression.disjunction().add(Expression.like("email", "%"+this.getSearchText()+"%")).add(
            Expression.like("subject", "%"+this.getSearchText()+"%"))).add(
            Expression.like("content", "%"+this.getSearchText()+"%"));
        }

        return crit;
    }

}
