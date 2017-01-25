package org.mortena.stripes.conf;

import net.sourceforge.stripes.localization.DefaultLocalePicker;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import java.util.Locale;


public class StripesLocalePicker extends  DefaultLocalePicker {
	private static Logger logger = Logger.getLogger(StripesLocalePicker.class);
	public static final String LOCALE = "org.apache.struts.action.LOCALE";
    
    @Override
    public Locale pickLocale(HttpServletRequest request) {
        
    	Locale locale = (Locale) request.getSession().getAttribute(LOCALE);
    	
        if (locale==null)
        	locale = super.pickLocale(request); 
     
        if (logger.isDebugEnabled()) {
			logger.debug("Locale [" + request.getRequestURI() +"]: " + locale);
		}
        
        return locale;
        
    }
}
