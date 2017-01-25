/* Copyright (C) 2005-2006 Aaron Porter
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * 
 */
package util;

import java.io.IOException;
import java.io.ByteArrayInputStream;
import java.lang.reflect.Modifier;
import java.util.*;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;

import net.sourceforge.stripes.util.Log;

import org.hibernate.Interceptor;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.AnnotationConfiguration;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.ImprovedNamingStrategy;

//import com.mongus.servlet.util.ClassLocator;

/**
 * <p>HibernateFilter wraps HTTP requests to provide access to a Hibernate Session.
 * HibernateFilter.getSession() returns a thread specific instance of Session
 * associated with the class specified. If there is not already a Session, one is
 * opened and a transaction is started. HibernateFilter.commit() and
 * HibernateFilter.rollback() may be called at any time during the request to perform
 * an immediate commit or rollback and begin another transaction. Please note that
 * <b>you must call HibernateFilter.commit() in order to save any changes</b>.
 * HibernateFilter will not commit transactions by itself. This is a security measure
 * to help prevent unauthorized changes to your database.</p>
 * 
 * <p>When this filter is initialized it loads Hibernate properties from web.xml.
 * Then it scans the classes available through the ServletContext looking for classes
 * annotated with {@literal @Entity} and lets Hibernate know about them by calling
 * AnnotationConfiguration.addAnnotatedClasses(). Using this technique makes it
 * possible to use Hibernate Annotations without creating any mapping or property files.<p>
 * 
 * <p>web.xml must be modified in order to use HibernateFilter. The following example
 * sets up the Hibernate filter to use a PostgreSQL DB, scan for classes annotated 
 * with {@literal @Entity} in com.mypackage.model and com.yourpackage.model packages,
 * and automatically create or update the schema based on the matching classes.</p>
 * 
 * <pre>
     
     &lt;filter&gt;
            &lt;filter-name&gt;HibernateFilter&lt;/filter-name&gt;
            &lt;filter-class&gt;com.mongus.servlet.filter.HibernateFilter&lt;/filter-class&gt;
            &lt;init-param&gt;
                &lt;param-name&gt;packages&lt;/param-name&gt;
                &lt;param-value&gt;
                    com.mypackage.model,
                    com.yourpackage.model
                &lt;/param-value&gt;
            &lt;/init-param&gt;
            &lt;init-param&gt;
                &lt;param-name&gt;properties&lt;/param-name&gt;
                &lt;param-value&gt;
                    hibernate.hbm2ddl.auto update

                    hibernate.dialect org.hibernate.dialect.PostgreSQLDialect
                    hibernate.connection.driver_class org.postgresql.Driver
                    hibernate.connection.datasource java:/comp/env/jdbc/mydb
                &lt;/param-value&gt;
            &lt;/init-param&gt;
            &lt;init-param&gt;
                &lt;param-name&gt;hibernateInterceptor.Class&lt;/param-name&gt;
                &lt;param-value&gt;
                   com.xyz.app.util.MyHibernateInterceptor
                &lt;/param-value&gt;
            &lt;/init-param&gt;
    &lt;/filter&gt;
    
 * </pre>
 * 
 * <p>Make sure the HibernateFilter appears int the web.xml file before any other
 * filters which will use it such as the StripesFilter.</p>
 * 
 * <p>Although the filter's packages parameter is optional its use is highly encouraged to
 * decrease load time and prevent accidental inclusion of classes from other packages.</p>
 * 
 * <p>Note: The property line "hibernate.hbm2ddl.auto update" instructs hibernate to
 * automatically create or modify the database schema. Use with caution.</p> 
 *
 * <p>You can register an hibernate interceptor by specifying its class in web.xml for  
 * the optional init-param named <code>hibernateInterceptorClass</code> (must have a 
 * default constructor)</p>
 * 
 * @author Aaron Porter
 *
 */
public class HibernateFilter extends HibernateProvider implements Filter
{
	private static final Log log = Log.getInstance(HibernateFilter.class);
	
	static
	{
        Package pkg = HibernateFilter.class.getPackage();
		log.info(	"\r\n##################################################",
					"\r\n# Stripernate Version: ", pkg.getSpecificationVersion(),
					", Build: ", pkg.getImplementationVersion(),
					"\r\n##################################################");
	}
	
	protected FilterConfig config = null;
	
	private final Map<Class<? extends Object>, SessionFactory> sessionFactories = new HashMap<Class<? extends Object>, SessionFactory>();
	private final Map<Class<? extends Object>, Configuration> configurations = new HashMap<Class<? extends Object>, Configuration>();
	
	private static final ThreadLocal<Map<SessionFactory, Session>> sessions = new ThreadLocal<Map<SessionFactory, Session>>();
	
	private static final ThreadLocal<HibernateFilter> currentHibernateFilter = new ThreadLocal<HibernateFilter>();
	
	private static final ThreadLocal<HttpServletRequest> currentServletRequest = new ThreadLocal<HttpServletRequest>();
	
	private boolean initializationComplete = false;
	
	/**
	 * <p>Initializes Hibernate with properties from web.xml and classes annotated
	 *  with {@literal @Entity}.</p>  
	 */
	public void init(final FilterConfig config) throws ServletException
	{
		this.config = config;
		
		String threadedInitialization = getInitParameter("ThreadedInitialization");
		
		if (threadedInitialization != null && threadedInitialization.matches("(:i)1|yes|true"))
		{
			log.debug("Running initialization in a separate thread to speed up servlet engine startup.");
			new Thread(new Runnable()
			{
				public void run() { initialize(); }
			}).start();
		}
		else
		{
			log.debug("Running initialization in the same thread.");
			initialize();
		}

	}

	private synchronized void initialize()
	{
		try
		{
			long start = System.currentTimeMillis();

			AnnotationConfiguration configuration = new AnnotationConfiguration();

			loadHibernateProperties(configuration);

			try
			{
				loadHibernateInterceptors(configuration);
			} catch (ServletException e)
			{
				log.error(e);
			}

			
			beforeBuildSessionFactory(configuration);

			SessionFactory sessionFactory = configuration.buildSessionFactory();

			

			initializationComplete = true;

			notifyAll();

			log.info("Initialization completed in ", System.currentTimeMillis() - start, "ms.");
		}
		catch (Throwable t)
		{
			log.error(t);
		}
	}
	
	private synchronized void waitForInitialization()
	{
		log.debug("Waiting for initialization to complete...");
		while (!initializationComplete)
		{
			try
			{
				wait(100);
			} catch (InterruptedException e) { }
		}
	}
	
	/**
	 * <p>Called immediately before configuration.buildSessionFactory().
	 * You can extend HibernateFilter and override this method in case 
	 * you want fine-grained control over the configuration,
	 * before SessionFactories are created.</p>
	 * 
	 * @param configuration
	 */
	protected void beforeBuildSessionFactory(Configuration configuration)
	{
	}
	
	protected String getInitParameter(String... names)
	{
		for (String name : names)
		{
			String value = config.getInitParameter(name);
			
			if (value != null)
			{
				if (!name.equals(names[0]))
					log.warn("The HibernateFilter parameter ", name, " has been changed to ", names[0], " to conform to Stripes naming conventions.");
				
				return value;
			}
		}
		
		return null;
	}

	// Hibernate Interceptor code provided by Remi Vankeisbelck
	// Thanks Remi! :)
	private void loadHibernateInterceptors(Configuration configuration) throws ServletException
	{
		// do we register an interceptor?
		String hibernateInterceptorClass = getInitParameter("HibernateInterceptor.Class", "hibernateInterceptor.Class", "hibernateInterceptorClass");
		
		if (hibernateInterceptorClass == null)
			return;

		// yep, we try...
		log.debug("Trying to register hibernate interceptor : ", hibernateInterceptorClass);

		Class<? extends Object> clazz;
		
		try
		{
			clazz = Class.forName(hibernateInterceptorClass);
			Interceptor interceptor = (Interceptor)clazz.newInstance();
			configuration.setInterceptor(interceptor);
			log.debug("Hibernate interceptor '", interceptor, "' created and registered OK");
		}
		catch (ClassNotFoundException e) { throw new ServletException("Unable to load hibernate interceptor class", e); }
		catch (InstantiationException e) { throw new ServletException("Unable to create hibernate interceptor : default constructor throws error", e); }
		catch (IllegalAccessException e) { throw new ServletException("Unable to create hibernate interceptor : default constructor is not accessible", e); }
		catch(ClassCastException e) { throw new ServletException(hibernateInterceptorClass + " does not implement Interceptor !", e); }
	}


	private void loadHibernateProperties(AnnotationConfiguration configuration)
	{
		configuration.setNamingStrategy(ImprovedNamingStrategy.INSTANCE);

		configuration.setProperty("hibernate.current_session_context_class", "thread");
		configuration.setProperty("hibernate.cache.provider_class", "org.hibernate.cache.NoCacheProvider");

		String propertiesString = getInitParameter("HibernateProperties", "properties");

		if (propertiesString != null)
		{
			try
			{
				Properties properties = new Properties();
				properties.load(new ByteArrayInputStream(propertiesString.getBytes()));
				configuration.addProperties(properties);
			} catch (IOException e)
			{
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * Calls the next filter in the chain then clean up the session if one was opened.
	 */
	public void doFilter(final ServletRequest request, final ServletResponse response, final FilterChain chain) throws IOException, ServletException
	{
		currentHibernateFilter.set(this);
		
		if (request instanceof HttpServletRequest)
			currentServletRequest.set((HttpServletRequest) request);
		
		try
		{
			sessions.set(new HashMap<SessionFactory, Session>());
                        getSession();
			
			chain.doFilter(request, response);
                        commit();
		}
		finally
		{
			if (sessions.get() != null)
			{
				for (Session session : sessions.get().values())
				{
					Transaction transaction = session.getTransaction();
					if (transaction.isActive())
						transaction.rollback();
					session.close();
				}
				
				sessions.get().clear();

				sessions.set(null);
			}
			
			currentHibernateFilter.set(null);
			currentServletRequest.set(null);
		}
	}

	public void destroy()
	{
		config = null;
		sessionFactories.clear();
		configurations.clear();
	}
	
	
	
	private Session getSession(SessionFactory sessionFactory)
	{
		if (!initializationComplete)
			waitForInitialization();
		
		Session session = sessions.get().get(sessionFactory);
		
		if ((session == null) && (sessionFactory != null))
		{
			session = sessionFactory.openSession();
			sessions.get().put(sessionFactory, session);
			session.beginTransaction();
		}

		return session;
	}
	
	@Override
	public Session getSession()
	{
		return getSession(getSessionFactory());
	}
	
	/**
	 * Convenience method that performs an immediate commit on the
	 * current transaction and begins a new transaction.
	 */
	@Override
	public void commit(Session session)
	{
		Transaction transaction = session.getTransaction();
		
		if ((transaction != null) && transaction.isActive())
		{
			session.flush();
			transaction.commit();
		}
		
		session.beginTransaction();
	}
	
	/**
	 * Convenience method that performs an immediate rollback on the
	 * current transaction and begins a new transaction.
	 */
	@Override
	public void rollback(Session session)
	{
		Transaction transaction = session.getTransaction();
		
		if ((transaction != null) && transaction.isActive())
			transaction.rollback();
		
		session.beginTransaction();
	}

	/**
	 * @return The Configuration used to build the SessionFactory associated with the specified class. 
	 */
	
	public Configuration getConfiguration(Class forClass)
	{
		if (!initializationComplete)
			waitForInitialization();
		
		return configurations.get(forClass);
	}
	
	/**
	 * @return The SessionFactory associated with the specified class.
	 */
	public SessionFactory getSessionFactory(Class forClass)
	{
		if (!initializationComplete)
			waitForInitialization();
		
		return sessionFactories.get(forClass);
	}

	@Override
	public Set<Configuration> getConfigurations()
	{
		if (!initializationComplete)
			waitForInitialization();
		
		HashSet<Configuration> configurations = new HashSet<Configuration>();
		
		configurations.addAll(this.configurations.values());
		
		return configurations;
	}

	@Override
	public Set<SessionFactory> getSessionFactories()
	{
		if (!initializationComplete)
			waitForInitialization();
		
		HashSet<SessionFactory> sessionFactories = new HashSet<SessionFactory>();
		
		sessionFactories.addAll(this.sessionFactories.values());
		
		return sessionFactories;
	}
	
	static public HibernateFilter getCurrentInstance()
	{
		return currentHibernateFilter.get();
	}
	
	static public HttpServletRequest getRequest()
	{
		return currentServletRequest.get();
	}

    @Override
    public Session getSession(Class<? extends Object> forClass) {
        return getSession();
    }

   
}
