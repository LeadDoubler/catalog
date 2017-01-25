package org.mortena.stripes.conf;

import net.sourceforge.stripes.localization.LocalePicker;
import net.sourceforge.stripes.localization.DefaultLocalePicker;
import net.sourceforge.stripes.config.Configuration;

import javax.servlet.http.HttpServletRequest;
import java.util.Locale;

/**
 * Created by IntelliJ IDEA.
 * User: mortenandersen
 * Date: 2007-01-12
 * Time: 07:14:20
 * To change this template use File | Settings | File Templates.
 */
public class URLLocalePicker extends  DefaultLocalePicker {
    
    public Locale pickLocale(HttpServletRequest request) {
        Locale locale = null;
        if (request.getRequestURI().startsWith("/en/")){
            return new Locale("en_US");
        }
        return new Locale("da_DK");        
    }
}
