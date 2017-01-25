package com.asap.core.cleanurl;

import java.util.ArrayList;
import java.util.List;

import net.sourceforge.stripes.action.ActionBean;
import net.sourceforge.stripes.action.UrlBinding;
import net.sourceforge.stripes.util.Log;

/**
 * Represents a clean URL definition, and allows parsing and
 * management of the clean URL binding expressions. <br/>
 * A clean URL includes parameters in the request URI instead of appending
 * them to it. Here is an example :
 * <ul>
 * 	<li>http://.../myApp/SomeAction.action?id=123&type=Book&_eventName=edit</li>
 *  <li>http://.../myApp/SomeAction.action/123/Book/edit</li>
 * </ul>
 * Clean url binding expressions can be defined in the <code>UrlBinding</code>
 * annotation of the action beans, like this :
 * <ul>
 * 	<li>/@MyAction.action/:type/:id</li>
 * 	<li>/some/path/@do-stuff/:param1/some/other/path/:param2</li>
 * 	<li>/xxx/@DoStuff.ext/foo/:param/bar/:_eventName</li>
 * </ul>
 * <pre>
 */
public class CleanUrl {
    
    /** Log instance used to log information from this class. */
    private static final Log log = Log
            .getInstance (CleanUrl.class);
    
    /** action delimiter */
    public static final char DELIM_ACTION = '@';
    /** param delimiter */
    public static final char DELIM_PARAM = ':';
    /** path delimiter */
    public static final char DELIM_PATH = '/';
    
    /** the binding expression */
    private String expression;
    /** the expression's tokens */
    private String[] expTokens;
    /** the equivalent action binding */
    private String equivalentBinding;
    /** the list of parm definitions */
    private List<String> paramDefs;
    
    /**
     * Create and initialize CleanUrl with passed binding expression
     */
    public CleanUrl (String expression) {
        this.expression = expression;
        initialize ();
    }
    
    /**
     * Initialize this clean URL object by extracting parameter
     * definitions from (and tokenizing) the expression.
     * Invoked at construction.
     */
    private void initialize () {
        
        log.debug ("Initializing with expression ", expression);
        
        // compute equivalent binding
        // --------------------------
        int i = expression.indexOf (DELIM_ACTION);
        if (i!=-1){
            equivalentBinding = expression.substring (0, i);
            StringBuffer actionName = new StringBuffer ();
            i++;
            char c = expression.charAt (i);
            while (c!=DELIM_PATH) {
                actionName.append (c);
                c = expression.charAt (++i);
            }
            equivalentBinding += actionName;
            
            log.debug ("equivalentBinding=", equivalentBinding);
        }
        // compute param names & event if any
        // ----------------------------------
        paramDefs = new ArrayList<String>();
        int expLen = expression.length ();
        for (int j = 0; j < expLen; j++) {
            char c = expression.charAt (j);
            if (c==DELIM_PARAM) {
                // param start, get the name...
                StringBuffer pName = new StringBuffer ();
                j++;
                while (j<expLen) {
                    c = expression.charAt (j);
                    if (c==DELIM_PARAM || c==DELIM_PATH)
                        break;
                    else
                        pName.append (c);
                    j++;
                }
                String pNameStr = pName.toString ();
                paramDefs.add (pNameStr);
                log.debug ("Param def '", pNameStr, "' added");
            }
        }
        
        // split expression into tokens
        expTokens = expression.split ("" + DELIM_PATH);
    }
    
    /**
     * Return the clean URL expression
     */
    public String getExpression () {
        return expression;
    }
    
    
    /**
     * Return the URL binding computed from passed clean URL expression
     */
    public String getEquivalentBinding () {
        return equivalentBinding;
    }
    
    /**
     * Return the list of param names defined in the clean URL expression
     */
    public List<String> getParamDefs () {
        return paramDefs;
    }
    
    /**
     * Return the index of passed token in the
     * binding expression.
     */
    private int getTokenIndex (String expressionToken) {
        int i = -1;
        for (int j = 0; j < expTokens.length; j++) {
            if (expTokens[j].equals (expressionToken)) {
                i = j;
                break;
            }
        }
        return i;
    }
    
    /**
     * Extract a parameter value from the request URI
     * @param paramName The name of the param to get the value of
     * @param requestURI The request URI
     * @return The value of the param if found, null if not found
     */
    public String extractParamFromRequestURI (String paramName, String requestURI) {
        log.debug ("Trying to extract param ", paramName, " from ", requestURI, "...");
        
        // trim beginning of the URI by removing context etc.
        int i = requestURI.indexOf (equivalentBinding);
        if (i==-1)
            return null;
        else {
            requestURI = requestURI.substring (i);
            log.debug ("Trimmed requestURI=", requestURI);
            
            String[] uriTokens = requestURI.split ("" + DELIM_PATH);
            // which indice ?
            int paramIndex = getTokenIndex (DELIM_PARAM + paramName);
            if (paramIndex==-1 || paramIndex>=uriTokens.length) {
                log.debug ("Returning null");
                return null;
            } else {
                String pVal = uriTokens[paramIndex];
                log.debug ("Retuning value=", pVal);
                return pVal;
            }
        }
    }
    
    /**
     * Create and return an initialized CleanUrl object for passed
     * action bean class if possible (by inspecting the contents of its
     * UrlBinding annotation), null if no clean URL available for passed
     * bean class
     */
    public static CleanUrl getCleanUrl (Class<? extends ActionBean> clazz) {
        log.debug ("Trying to create CleanUrl for bean class ", clazz);
        UrlBinding binding = (UrlBinding)clazz.getAnnotation (UrlBinding.class);
        if (binding==null) {
            log.debug ("No associated @UrlBinding, returning null");
            return null;
        } else {
            String exp = binding.value ();
            if (exp.indexOf ('@')!=-1) {
                log.debug ("Found clean url expression...");
                CleanUrl cu = new CleanUrl (exp);
                log.debug ("OK returning ", cu);
                return cu;
            } else {
                log.debug ("Exp '", exp, "' does not look like a clean url expr, returning null");
                return null;
            }
        }
    }
}
