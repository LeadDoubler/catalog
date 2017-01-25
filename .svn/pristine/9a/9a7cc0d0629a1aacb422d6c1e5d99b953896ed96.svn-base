/*
 * SoftwareConfiguration.java
 *
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package com.asap.configuration;

import java.util.Collection;
import javax.servlet.ServletContext;
import net.sourceforge.stripes.config.BootstrapPropertyResolver;
import net.sourceforge.stripes.config.Configuration;
import net.sourceforge.stripes.config.RuntimeConfiguration;
import net.sourceforge.stripes.controller.ActionBeanContextFactory;
import net.sourceforge.stripes.controller.ActionBeanPropertyBinder;
import net.sourceforge.stripes.controller.ActionResolver;
import net.sourceforge.stripes.controller.Interceptor;
import net.sourceforge.stripes.controller.LifecycleStage;
import net.sourceforge.stripes.controller.multipart.MultipartWrapperFactory;
import net.sourceforge.stripes.exception.ExceptionHandler;
import net.sourceforge.stripes.format.FormatterFactory;
import net.sourceforge.stripes.localization.LocalePicker;
import net.sourceforge.stripes.localization.LocalizationBundleFactory;
import net.sourceforge.stripes.tag.PopulationStrategy;
import net.sourceforge.stripes.tag.TagErrorRendererFactory;
import net.sourceforge.stripes.validation.TypeConverterFactory;

/**
 *
 * @author asapunov
 */
public class SoftwareConfiguration extends RuntimeConfiguration {
    
    private static final String SITE_PARAMETER      =   "site";
    private static final String CUSTOMER_PARAMETER  =   "customer";
    
    private static String prefix = null;
    
    
    public void init() {        
        super.init();
        StringBuilder sb = new StringBuilder();
        sb.append(getBootstrapPropertyResolver().getProperty(CUSTOMER_PARAMETER));
        sb.append("_");
        sb.append(getBootstrapPropertyResolver().getProperty(SITE_PARAMETER));
        sb.append("_");
        prefix = sb.toString();
    }
    
    
    public static String getDatabaseConfigurationParameterPrefix() {
        return prefix;
    }
}
