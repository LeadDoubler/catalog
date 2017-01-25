/*
 * FCKeditor - The text editor for Internet - http://www.fckeditor.net
 * Copyright (C) 2003-2007 Frederico Caldeira Knabben
 *
 * == BEGIN LICENSE ==
 *
 * Licensed under the terms of any of the following licenses at your
 * choice: 
 *
 *  - GNU General Public License Version 2 or later (the "GPL")
 *    http://www.gnu.org/licenses/gpl.html
 *
 *  - GNU Lesser General Public License Version 2.1 or later (the "LGPL")
 *    http://www.gnu.org/licenses/lgpl.html
 *
 *  - Mozilla Public License Version 1.1 or later (the "MPL")
 *    http://www.mozilla.org/MPL/MPL-1.1.html
 *
 * == END LICENSE ==
 *
 * FCKeditor control class.
 */


package com.fredck.FCKeditor;

import javax.servlet.http.HttpServletRequest;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * The main class of the class lib.<br>
 * It's the container for all properties and the class that generate the output based on browser capabilities and configurations passed by the developer.
 */
public class FCKeditor {

	private FCKeditorConfigurations oConfig;
	private String instanceName;
	private String value = "";
	private String basePath;
	private String toolbarSet = "Default";
	private String width = "100%";
	private String height = "200";

	HttpServletRequest request;

    /**
     * Get the unique name of the editor
     *
     * @return name
     */
	public String getInstanceName() {
		return instanceName;
	}

    /**
     * Set the unique name of the editor
     *
     * @param value name
     */
	public void setInstanceName(String value) {
		instanceName=value;
	}

    /**
     * Get the initial value to be edited.<br>
     * In HTML code
     *
     * @return value
     */
	public String getValue() {
		return value;
	}

    /**
     * Set the initial value to be edited.<br>
     * In HTML code
     *
     * @param value value
     */
	public void setValue(String value) {
		this.value=value;
	}

    /**
     * Get the dir where the FCKeditor files reside on the server
     *
     * @return path
     */
	public String getBasePath() {
		return basePath;
	}

    /**
     * Set the dir where the FCKeditor files reside on the server.<br>
	 *<b>Remarks</b>:<br>
	 *Avoid using relative paths. It is preferable to set the base path starting from the root (/).<br>
	 *Always finish the path with a slash (/).
	 *
     * @param value path
     */
	public void setBasePath(String value) {
		basePath=value;
	}

    /**
     * Get the name of the toolbar to display
     *
     * @return toolbar name
     */
	public String getToolbarSet() {
		return toolbarSet;
	}

    /**
     * Set the name of the toolbar to display
     *
     * @param value toolbar name
     */
	public void setToolbarSet(String value) {
		toolbarSet=value;
	}

    /**
     * Get the width of the textarea
     *
     * @return width
     */
	public String getWidth() {
		return width;
	}

    /**
     * Set the width of the textarea
     *
     * @param value width
     */
	public void setWidth(String value) {
		width=value;
	}

    /**
     * Get the height of the textarea
     *
     * @return height
     */
	public String getHeight() {
		return height;
	}

    /**
     * Set the height of the textarea
     *
     * @param value height
     */
	public void setHeight(String value) {
		height=value;
	}


    /**
     * Get the advanced configuation set.<br>
     * Adding element to this collection you can override the settings specified in the config.js file.
     *
     * @return configuration collection
     */
	public FCKeditorConfigurations getConfig() {
		return oConfig;
	}

    /**
     * Set the advanced configuation set.
     *
     * @param value configuration collection
     */
	public void setConfig(FCKeditorConfigurations value) {
		oConfig=value;
	}

    /**
     * Initialize the object setting all value to the default ones.
     * <p>
     * <ul>
     * <li>width: 100%</li>
     * <li>height: 200</li>
     * <li>toolbar name: Default</li>
     * <li>basePath: context root + "/FCKeditor/"</li>
     * </ul>
     * </p>
     *
     * @param req request object
     */
	public FCKeditor(HttpServletRequest req){
		request=req;
		basePath = request.getContextPath() + "/FCKeditor/";
		oConfig = new FCKeditorConfigurations() ;
	}

    /**
     * Initialize the object setting the unique name and then all value to the default ones.
     * <p>
     * <ul>
     * <li>width: 100%</li>
     * <li>height: 200</li>
     * <li>toolbar name: Default</li>
     * <li>basePath: context root + "/FCKeditor/"</li>
     * </ul>
     * </p>
     *
     * @param req request object
     * @param parInstanceName unique name
     */
	public FCKeditor(HttpServletRequest req, String parInstanceName){
		request=req;
		basePath = request.getContextPath() + "/FCKeditor/";
		instanceName=parInstanceName;
		oConfig = new FCKeditorConfigurations() ;
	}

    /**
     * Initialize the object setting all basic configurations.<br>
     *
     * The basePath is context root + "/FCKeditor/"
     *
     * @param req request object
     * @param parInstanceName unique name
     * @param parWidth width
     * @param parHeight height
     * @param parToolbarSet toolbarSet name
     * @param parValue initial value
     */
	public FCKeditor(HttpServletRequest req, String parInstanceName, String parWidth, String parHeight, String parToolbarSet, String parValue){
		request=req;
		basePath = request.getContextPath() + "/FCKeditor/";
		instanceName=parInstanceName;
		width=parWidth;
		height=parHeight;
		toolbarSet=parToolbarSet;
		value=parValue;
		oConfig = new FCKeditorConfigurations() ;
	}


	private boolean isCompatible() {
		String userAgent=request.getHeader("user-agent");
		if(userAgent==null)
			return false;
		userAgent=userAgent.toLowerCase();
		if ((userAgent.indexOf("msie") !=-1) && (userAgent.indexOf("mac") == -1) && (userAgent.indexOf("opera") == -1)) {
			if(retrieveBrowserVersion(userAgent)>=5.5)
				return true;
		}
		else if (userAgent.indexOf("applewebkit") != -1) {
			// Safari (applewebkit) has "gecko" in its agent string so this test should appear before the gecko test
            return (retrieveBrowserVersion(userAgent) >= 522);
        } 
		else if (userAgent.indexOf("gecko") !=-1){
			if(retrieveBrowserVersion(userAgent)>=20030210)
				return true;
		}
		else if (userAgent.indexOf("opera") != -1) {
            return (retrieveBrowserVersion(userAgent) >= 9.5);
        }
		return false;
	}

	private double retrieveBrowserVersion(String userAgent) {
		try {
			if(userAgent.indexOf("msie")>-1) {
				String str = userAgent.substring(userAgent.indexOf("msie") + 5);
				return Double.parseDouble(str.substring(0, str.indexOf(";")));
			}
			else if (userAgent.indexOf("applewebkit") != -1) {
				// Safari (applewebkit) has "gecko" in its agent string so this test should appear before the gecko test
	            Matcher safari = Pattern.compile("applewebkit/" + "(( [\\d]* )" + "(?:" + "\\." + " [\\d]* )?)", Pattern.COMMENTS).matcher(userAgent);
	            safari.find();
	            int majorVersion = 0;
	            return Integer.parseInt(safari.group(2));
			}
			else if (userAgent.indexOf("gecko") != -1)  {
				String str = userAgent.substring(userAgent.indexOf("gecko") + 6);
				return Double.parseDouble(str.substring(0, 8));
	        } else if (userAgent.indexOf("opera") != -1) {
	            String str = userAgent.substring(userAgent.indexOf("opera") + 6);
	            return  Double.parseDouble(str.substring(0, 4));
	        }
        } catch (NumberFormatException e) {
        	// do not do anything - want to return 0
        }
        
        return 0;


		
	}

	private String HTMLEncode(String txt) {
		txt=txt.replaceAll("&","&amp;");
		txt=txt.replaceAll("<","&lt;");
		txt=txt.replaceAll(">","&gt;");
		txt=txt.replaceAll("\"","&quot;");
		txt=txt.replaceAll("'","&#146;");
		return txt;
	}


    /**
     * Generate the HTML Code for the editor.
     * <br>
     * Evalute the browser capabilities and generate the editor if IE 5.5 or Gecko 20030210 or greater,
     * or a simple textarea otherwise.
     *
     * @return html code
     */
	public String create() {
		StringBuffer strEditor=new StringBuffer();

		strEditor.append("<div>");
		String encodedValue=HTMLEncode(value);

		if(isCompatible()) {

			strEditor.append("<input type=\"hidden\" id=\"" + instanceName + "\" name=\"" + instanceName + "\" value=\"" + encodedValue + "\">");

			strEditor.append(createConfigHTML());
			strEditor.append(createIFrameHTML());

		}
		else{
			strEditor.append("<TEXTAREA name=\"" + instanceName + "\" rows=\"4\" cols=\"40\" style=\"WIDTH: " + width + "; HEIGHT: " + height + "\" wrap=\"virtual\">"+encodedValue+"</TEXTAREA>");
		}
		strEditor.append("</div>");
		return strEditor.toString();
	}

	private String createConfigHTML() {
		String configStr=oConfig.getUrlParams();


		if(!configStr.equals(""))
			configStr=configStr.substring(1);

		return "<input type=\"hidden\" id=\"" + instanceName + "___Config\" value=\"" + configStr + "\">" ;
	}

	private String createIFrameHTML() {

		String sLink=basePath + "editor/fckeditor.html?InstanceName=" + instanceName;

		if (!toolbarSet.equals(""))
			sLink+="&Toolbar=" + toolbarSet;

		 return "<iframe id=\"" + instanceName + "___Frame\" src=\"" + sLink + "\" width=\"" + width + "\" height=\"" + height + "\" frameborder=\"no\" scrolling=\"no\"></iframe>";

	}


}
