package com.asap.core.cleanurl;

import net.sourceforge.stripes.action.ActionBean;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.controller.ExecutionContext;
import net.sourceforge.stripes.controller.Interceptor;
import net.sourceforge.stripes.controller.Intercepts;
import net.sourceforge.stripes.controller.LifecycleStage;
import net.sourceforge.stripes.controller.StripesRequestWrapper;

import javax.servlet.http.HttpServletRequest;

/**
 * Interceptor that turns on clean URL handling in Stripes when 
 * the target action has a clean URL binding expression.  
 * Intercepts before HandlerResolution, and wraps the request into 
 * a specific request wrapper for transparent clean URL handling.
 * @see CleanUrlRequestWrapper
 */
@Intercepts(LifecycleStage.HandlerResolution)
public class CleanUrlInterceptor implements Interceptor {
		
	public Resolution intercept(ExecutionContext context) throws Exception {
		// get the action bean...
		ActionBean bean = context.getActionBean();
		// is that bean clean URL-ized ?
		CleanUrl cu = CleanUrl.getCleanUrl(bean.getClass());
		if (cu!=null) {
            HttpServletRequest request = context.getActionBeanContext().getRequest();
            StripesRequestWrapper wrapper;
            if (request instanceof StripesRequestWrapper)
                wrapper = new CleanUrlRequestWrapper((StripesRequestWrapper)request, cu);
            else
                wrapper = new CleanUrlRequestWrapper(request, cu);
			context.getActionBeanContext().setRequest(wrapper);
		}
				
		// proceed...
		Resolution res = context.proceed();
		return res;
	}

}
