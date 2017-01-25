package com.asap.web;

import net.sourceforge.stripes.action.ActionBean;
import net.sourceforge.stripes.action.ActionBeanContext;
import net.sourceforge.stripes.action.After;
import net.sourceforge.stripes.action.Before;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.Resolution;
import org.hibernate.Session;
import org.hibernate.Transaction;
import util.HibernateUtil;


/**
 * Simple ActionBean implementation that all ActionBeans will extend.
 *
 * @author Tim Fennell
 */
public abstract class CatalogActionBean implements ActionBean {
    private CatalogActionBeanContext context;
    private Session session;
    protected String PREPATH = "/WEB-INF/view";
    private String FOLDER = "default";
    long time1,time2;

    public void commit(){
        System.out.println("Started comitting");
        Transaction transaction = this.getSession().getTransaction();
	if ( (transaction != null) && transaction.isActive() ) {
                getSession().flush();
                transaction.commit();
        }
        setSession(HibernateUtil.getSessionFactory().openSession());
        getSession().beginTransaction();
        System.out.println("Finished comitting");

    }
    
    public void persist(Object obj){
        // this.getSession().setReadOnly(obj, false);
        this.getSession().saveOrUpdate(obj);
        this.persist();
    }
    
    public void delete(Object obj){
        this.getSession().delete(obj);
        this.persist();
    }

    public void persist(boolean bool){
        this.getContext().setCommit(new Boolean(bool));
    }
    
    public void persist(){
        this.getContext().setCommit(new Boolean(true));
    }
    
    protected Resolution forward(){
        return new ForwardResolution(PREPATH+"/"+getFOLDER()+"/"+this.getContext().getEventName()+".jsp");
    }
    
    public Session getSession(){
        if (session == null){
            session = HibernateUtil.getSessionFactory().getCurrentSession();
        }
        return session;
    }

    public void setContext(ActionBeanContext context) {
        this.context = (CatalogActionBeanContext) context; 
        setSession(HibernateUtil.getSessionFactory().getCurrentSession());
    }

    /** Gets the ActionBeanContext set by Stripes during initialization. */
    public CatalogActionBeanContext getContext() {
        return this.context;
    }

    /*@Before
    public void updatePage(){
        Long id = (Long) this.getContext().getRequest().getSession().getAttribute("page_id");
        if (id != null){
            this.getContext().getRequest().getSession().setAttribute("page", getSession().load(Page.class,id));
        }
    }*/
    
    @Before
    public void updateUserAndShopCartInSession(){
        //System.out.println("Updating user and shopcart");
        //this.getContext().setUser(this.getContext().getUser());
        //this.getContext().setShopCart(this.getContext().getShopCart());
    }
    
    @After
    public void setPageOnRequest(){
        /*HttpServletRequest request = this.getContext().getRequest();
        Cms cms = new Cms();
        cms.setRequest(this.getContext().getRequest());
        cms.getPage();
         * */
    }

    public String getFOLDER() {
        return FOLDER;
    }

    public void setFOLDER(String FOLDER) {
        this.FOLDER = FOLDER;
    }

    /* public void setPageToDefault(){
        com.asap.catalog.dao.manager.PageManager pm = new com.asap.catalog.dao.manager.PageManager();
        Page p = pm.getEnglishLandingPage();
        this.getContext().getRequest().getSession().setAttribute("page",p);
        this.getContext().getRequest().setAttribute("page", p);
    }*/

    /**
     * @param session the session to set
     */
    public void setSession(Session session) {
        this.session = session;
    }
}