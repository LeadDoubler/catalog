package com.asap.security;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Annotation used to secure ActionBean classes and methods with roles within the web appliction. If
 * the
 * 
 * @Secure annotation is used, the default behavior is to deny access even if no roles are
 *         specified. If roles are specified, they are processed in the order of notAllowed,
 *         requiresAll, and then requiresAny. Any combination of these may be set. You must create a
 *         class that implements StripesSecurityManager and set this as your security manager in the
 *         web.xml config as shown below.
 * 
 * <servlet> <servlet-name>StripesDispatcher</servlet-name>
 * <servlet-class>net.sourceforge.stripes.security.controller.DispatcherServlet</servlet-class> <init-param>
 * <param-name>SecurityManager</param-name>
 * <param-value>net.sourceforge.stripes.security.MyContainerSecurityManager</param-value>
 * </init-param> <load-on-startup>1</load-on-startup> </servlet>
 * 
 * You can then add the
 * @Secure annotation to any of your action class or method declarations like so.
 * 
 * @Secure( roles="MANAGE_USERS, MANAGE_CUSTOMERS" )
 * 
 * @author Nic Holbrook
 */
@Retention(RetentionPolicy.RUNTIME)
@Target( {ElementType.METHOD, ElementType.TYPE})
@Documented
public @interface Secure {
	Role role();
	
	boolean currentUser() default false;
}
