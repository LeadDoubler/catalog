/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.blob.utilities.web.agent;

import com.asap.web.CatalogActionBean;
import com.blob.pas.agent.DefaultAgent;

import java.util.logging.Level;
import java.util.logging.Logger;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.StreamingResolution;

/**
 *
 * @author Morten
 */
public class TestReporting extends CatalogActionBean{

    private String clazz;
    private boolean commit;

    public Resolution test() {
        try {
            DefaultAgent agent;
            agent = (DefaultAgent) Class.forName(clazz).newInstance();
            agent.setSession(this.getSession());
            agent.execute();
            if (commit){
                //System.out.println("Committing");
                //session.flush();
                this.getSession().getTransaction().commit();
            }
            else if (this.getSession().getTransaction().isActive()){
                //System.out.println("Rolling back");
                this.getSession().getTransaction().rollback();
                //session.close();
            }
            //System.out.println("Closing hibernate session");
            if (this.getSession().isOpen()){
                this.getSession().disconnect();
            }
            return new StreamingResolution("text/html","Agent tested - success");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(TestReporting.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(TestReporting.class.getName()).log(Level.SEVERE, null, ex);
        }catch (InstantiationException ex) {
            Logger.getLogger(TestReporting.class.getName()).log(Level.SEVERE, null, ex);
        }
        return new StreamingResolution("text/html","Agent tested - failed");
        
    }

    /**
     * @return the clazz
     */
    public String getClazz() {
        return clazz;
    }

    /**
     * @param clazz the clazz to set
     */
    public void setClazz(String clazz) {
        this.clazz = clazz;
    }

    /**
     * @return the commit
     */
    public boolean isCommit() {
        return commit;
    }

    /**
     * @param commit the commit to set
     */
    public void setCommit(boolean commit) {
        this.commit = commit;
    }

}
