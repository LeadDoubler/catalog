package org.mortena.netvaerk;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.StaleObjectStateException;

import org.hibernate.Transaction;
import util.HibernateUtil;

/**
 * Created by IntelliJ IDEA.
 * User: mortenandersen
 * Date: 2006-09-12
 * Time: 09:39:04
 * To change this template use File | Settings | File Templates.
 */
public class HibernateRequestFilter implements Filter{

    private static Log log = LogFactory.getLog(HibernateRequestFilter.class);

    private SessionFactory sf;

    
    public void doFilter(ServletRequest request,
                         ServletResponse response,
                         FilterChain chain)
            throws IOException, ServletException {
        
        
        try {
            long time = System.currentTimeMillis();
            log.debug("Starting a database transaction ");
            
              
            // connection object
            //Session  l_Session = 0;
            //= sf.getCurrentSession();
          //  1
           /// Connection l_connection = sf.getCurrentSession().connection();
            
            
            sf.getCurrentSession().beginTransaction();

            
             long time2 = System.currentTimeMillis();
            
             
             // Call the next filter (continue request processing)
            chain.doFilter(request, response);
            long time3 = System.currentTimeMillis();
            
            // Commit and cleanup
            org.mortena.netvaerk.HibernateRequestFilter.log.debug("Committing the database transaction");
            //sf.getCurrentSession().getTransaction().commit();
             long time4 = System.currentTimeMillis();
             //System.out.println("Start of hibernateFilter: "+(time2-time));
             //System.out.println("The rest: "+(time3-time2));
             //System.out.println("Commit of hibernateFilter: "+(time4-time3));
        } catch (StaleObjectStateException staleEx) {
            org.mortena.netvaerk.HibernateRequestFilter.log.error("This interceptor does not implement optimistic concurrency control!");
            org.mortena.netvaerk.HibernateRequestFilter.log.error("Your application will not work until you add compensation actions!");
            // Rollback, close everything, possibly compensate for any permanent changes
            // during the conversation, and finally restart business conversation. Maybe
            // give the user of the application a chance to merge some of his work with
            // fresh data... what you do here depends on your applications design.
            throw staleEx;
        } catch (Throwable ex) {
            // Rollback only
            ex.printStackTrace();
            try {
                if (sf.getCurrentSession().getTransaction().isActive()) {
                    log.debug("Trying to rollback database transaction after exception");
                    //sf.getCurrentSession().getTransaction().rollback();
                }
            } catch (Throwable rbEx) {
                log.error("Could not rollback transaction after exception!", rbEx);
            }

            // Let others handle it... maybe another interceptor for exceptions?
            throw new ServletException(ex);
        }
        finally{
            Session session = sf.getCurrentSession();
            Transaction transaction = session.getTransaction();
            Boolean doCommit = (Boolean) request.getAttribute("commit");
            //System.out.println("doCommit = "+doCommit);
            if (doCommit != null && doCommit.booleanValue()){
                //System.out.println("Committing");
                //session.flush();
                try{
                    transaction.commit();
                }
                catch(Exception e){
                    e.printStackTrace();
                }
            }
            if (transaction.isActive()){
                //System.out.println("Rolling back");
                transaction.rollback();
                //session.close();
            }
            
            
            
            //System.out.println("Closing hibernate session");
            if (session.isOpen()){
                session.disconnect();
               // session.close();
              //  session.close();
              //  session.disconnect();
                
            }
            session = null;
            transaction = null;
            //System.out.println("hibernate session closed");
            
            /*
            if (sf.getCurrentSession().getTransaction().wasCommitted()){
                System.out.println("transaction was committed");
            }
            else{
                System.out.println("Transaction was not committed");
                sf.getCurrentSession().getTransaction().rollback();
            }*/
            //sf.getCurrentSession().close();
            
            //HibernateUtil.getSessionFactory().close();
            //sf.getCurrentSession().disconnect();//.close();
        }
    }

    public void init(FilterConfig filterConfig) throws ServletException {
        log.info("Initializing filter...");
        log.info("Obtaining SessionFactory from static HibernateUtil singleton");
        //System.out.println("Starting HibernateRequestUtil filter");
        
        try{
        sf = HibernateUtil.getSessionFactory();
        }catch (Exception e){
            e.printStackTrace();
            if (e instanceof ServletException){
                throw (ServletException) e;
            }
        }
        //System.out.println("Ending HibernateRequestFilter.init(...)");
    }

    public void destroy() {
        sf.close();
    }

}
