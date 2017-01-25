package com.asap.core.cleanurl;

import net.sourceforge.stripes.action.ActionBean;
import net.sourceforge.stripes.controller.NameBasedActionResolver;
import net.sourceforge.stripes.util.Log;

/**
 * Action resolver that handles clean URL style bindings
 */
public class CleanUrlActionResolver extends NameBasedActionResolver {

	/** Log instance used to log information from this class. */
	private static final Log log = Log
			.getInstance(NameBasedActionResolver.class);

	public CleanUrlActionResolver() {
		log.info("Creating CleanUrlActionResolver");
	}
	
	/**
	 * Return the URL binding for passed bean class. Converts 
	 * binding expressions into a regular bean url
	 * @see CleanUrl.getEquivalentBinding
	 */
	@Override
	public String getUrlBinding(Class<? extends ActionBean> clazz) {
		CleanUrl cu = CleanUrl.getCleanUrl(clazz);
        if (cu!=null) {
        	return cu.getEquivalentBinding();
        } else {
            return super.getUrlBinding(clazz);
        }
    }

}
