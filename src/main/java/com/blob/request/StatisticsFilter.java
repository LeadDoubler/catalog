/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.blob.request;

import com.blob.stats.WR;
import java.io.IOException;
import java.util.Date;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import util.HibernateUtil;

/**
 *
 * @author Morten
 */
public class StatisticsFilter implements Filter{

    public static WR initWebRequest(HttpServletRequest req, String sessionId) {
        //HttpServletRequest req = this.getContext().getRequest();
        WR wr = new WR();
        wr.setRemoteAddr(req.getRemoteAddr());
        wr.setRemoteHost(req.getRemoteHost());
        wr.setRemoteUser(req.getRemoteUser());
        String pathInfo = "";
        if (req.getPathInfo() != null) {
            pathInfo = req.getServletPath() + req.getPathInfo();
        } else {
            pathInfo = req.getServletPath();
        }
        System.out.println("Creating Web Request " + pathInfo);
        wr.setPathInfo(pathInfo);
        wr.setRequestTime(new Date());
        wr.setLocale(req.getLocale());
        wr.setSessionId(sessionId);
        wr.setBrowser(req.getHeader("User-Agent"));
        wr.setReferer(req.getHeader("Referer"));
        // set affiliate
        if (pathInfo.startsWith("/port/") && pathInfo.indexOf('/', 6) > 0) {
            wr.setAffiliate(pathInfo.substring(6, pathInfo.indexOf('/', 6)));
        }
        return wr;
    }

    String[] urls;

    @Override
    public void init(FilterConfig config) throws ServletException {
        String paths = config.getInitParameter("urls");
        urls = paths.split(",");
        //throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse arg1, FilterChain chain) throws IOException, ServletException {
        //System.out.println("statisticsFilter started.");
        //throw new UnsupportedOperationException("Not supported yet.");
        if (request instanceof HttpServletRequest){
            if (request.getAttribute("statDone") == null){
                HttpServletRequest req = (HttpServletRequest) request;
                System.out.println("StatisticsFilter: "+req.getServletPath());
                for (int i = 0; i<urls.length ; i++){
                    System.out.println("urls contains: "+urls[i]);
                    if (req.getServletPath().startsWith(urls[i])){
                        createWebRequest(req);
                        request.setAttribute("statDone", "yes");
                        break;
                    }
                }
            }
        }
        chain.doFilter(request, arg1);
    }

    @Override
    public void destroy() {
        //throw new UnsupportedOperationException("Not supported yet.");
    }

    public static void createWebRequest(HttpServletRequest req) {
        String sessionId = req.getSession().getId();
        createWebRequest(req, sessionId);
    }

    public static void createWebRequest(HttpServletRequest req, String sessionId) throws HibernateException {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        WR wr = initWebRequest(req, sessionId);

        session.saveOrUpdate(wr);
        session.getTransaction().commit();
        session.disconnect();
        session.close();
        session = null;
    }

}
