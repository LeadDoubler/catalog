package com.asap.util;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

/**
 * @author Aleksei Sapunov
 * 
 * 
 * This class used for sending messages to https site for add, update, publish,
 * unpublish patents TODO: Add logging and exceptions handling to save method
 */
public class PatentBorsSender {
    
    private static final String BASEURL = "https://www.flintbox.com";

	private String uid;

	private String pwd;

	private String email;

	private String title;

	private String shortDescription;

	private String patent;

	private String patentnumber;

	private String filenumber;

	private String website;

	private String description;

	private String potentialapplications;

	private String additionalinfo;

	private String techguid;

	public static void main(String[] args) {
            System.out.println("Testing patentborssender");
		PatentBorsSender pbs = new PatentBorsSender();
		pbs.setUid("patentborsadmin");
		pbs.setPwd("Techtrans");
		pbs.setEmail("ton@adm.dtu.dk");
		pbs.setTitle("patentbors project");
		pbs.setShortDescription("project for testing");
		pbs.setPatent("yes");
		pbs.setPatentnumber("patent 123");
		pbs.setFilenumber("file 456");
		pbs.setWebsite("www.test.com");
		pbs.setDescription("project description");
		pbs.setPotentialapplications("this project has potential");
		pbs.setAdditionalinfo("additional information");
		//pbs.setTechguid("{31C78D1F-E3E0-4D0A-81C6-9B6DF253305B}");

		System.out.println(pbs.add());
		System.out.println(pbs.update());
                System.out.println(pbs.publish());
                System.out.println("Finished adding patent to flintbox");
		
		//System.out.println(pbs.unpublish());
	}

	public String add() {
		StringBuilder url = new StringBuilder();
		url.append(BASEURL+"/technology_inteum.asp?");
		appendParameter(url, "uid", uid);
		appendParameterDelimeter(url);
		appendParameter(url, "pwd", pwd);
		appendParameterDelimeter(url);
		appendParameter(url, "action", "add");
		appendParameterDelimeter(url);
		appendParameter(url, "contactemail", email);
		appendParameterDelimeter(url);
		appendParameter(url, "title", title);
		appendParameterDelimeter(url);
		appendParameter(url, "shortdescription", shortDescription);
		appendParameterDelimeter(url);
		appendParameter(url, "patent", "yes");
		appendParameterDelimeter(url);
		appendParameter(url, "patentnumber", patentnumber);
		appendParameterDelimeter(url);
		appendParameter(url, "filenumber", filenumber);
		appendParameterDelimeter(url);
		appendParameter(url, "website", website);
		appendParameterDelimeter(url);
		appendParameter(url, "description", description);
		appendParameterDelimeter(url);
		appendParameter(url, "potentialapplications", potentialapplications);
		appendParameterDelimeter(url);
		appendParameter(url, "additionalinfo", additionalinfo);
                System.out.println(url);
		return send(url.toString());
	}

	public String update() {
		StringBuilder url = new StringBuilder();
		url.append(BASEURL+"/technology_inteum.asp?");
		appendParameter(url, "uid", uid);
		appendParameterDelimeter(url);
		appendParameter(url, "pwd", pwd);
		appendParameterDelimeter(url);
		appendParameter(url, "action", "update");
		appendParameterDelimeter(url);
		appendParameter(url, "contactemail", email);
		appendParameterDelimeter(url);
		appendParameter(url, "title", title);
		appendParameterDelimeter(url);
		appendParameter(url, "shortdescription", shortDescription);
		appendParameterDelimeter(url);
		appendParameter(url, "patent", "pending");
		appendParameterDelimeter(url);
		appendParameter(url, "patentnumber", patentnumber);
		appendParameterDelimeter(url);
		appendParameter(url, "filenumber", filenumber);
		appendParameterDelimeter(url);
		appendParameter(url, "website", website);
		appendParameterDelimeter(url);
		appendParameter(url, "description", description);
		appendParameterDelimeter(url);
		appendParameter(url, "potentialapplications", potentialapplications);
		appendParameterDelimeter(url);
		appendParameter(url, "additionalinfo", additionalinfo);
		appendParameterDelimeter(url);
		if (techguid!=null)
                    appendParameter(url, "techguid", techguid);
		return send(url.toString());
	}

	public String publish() {
		StringBuilder url = new StringBuilder();
		url.append(BASEURL+"/technology_inteum.asp?");
		appendParameter(url, "uid", uid);
		appendParameterDelimeter(url);
		appendParameter(url, "pwd", pwd);
		appendParameterDelimeter(url);
		appendParameter(url, "action", "publish");
		appendParameterDelimeter(url);
		appendParameter(url, "contactemail", email);
		appendParameterDelimeter(url);
		appendParameter(url, "techguid", techguid);
		return send(url.toString());
	}

	public String unpublish() {
		StringBuilder url = new StringBuilder();
		url.append(BASEURL+"/technology_inteum.asp?");
		appendParameter(url, "uid", uid);
		appendParameterDelimeter(url);
		appendParameter(url, "pwd", pwd);
		appendParameterDelimeter(url);
		appendParameter(url, "action", "unpublish");
		appendParameterDelimeter(url);
		appendParameter(url, "contactemail", email);
		appendParameterDelimeter(url);
		appendParameter(url, "techguid", techguid);
		return send(url.toString());
	}

	private void appendParameterDelimeter(StringBuilder sb) {
		sb.append("&");
	}

	// This method is used for normalize URL
	private void appendParameter(StringBuilder sb, String parameterName,
			String parameterValue) {
		try {
			sb.append(URLEncoder.encode(parameterName, "UTF-8"));
			sb.append("=");
			sb.append(URLEncoder.encode(parameterValue, "UTF-8"));
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private String send(String stringURL) {
		try {
			// String encoded = URLEncoder.encode(stringURL, "UTF-8");
			URL url = new URL(stringURL);
			return URLSSLUtil.getContentFrom(url);
		} catch (MalformedURLException e) {
			// TODO: Add logging and exception handling
			e.printStackTrace();
		} catch (IOException e) {
			// TODO: Add logging and exception handling
			e.printStackTrace();
		}
		return null;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPwd() {
		return "Techtrans";
	}

	public void setPwd(String pwd) {
		//this.pwd = pwd;
	}

	public String getUid() {
		return uid;
	}

	public void setUid(String uid) {
		this.uid = uid;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		if (title.length() > 200) {
			this.title = title.substring(0, 199);
		} else {
			this.title = title;
		}
	}

	public String getShortDescription() {
		return shortDescription;
	}

	public void setShortDescription(String shortDescription) {
		if (shortDescription.length() > 2000) {
			this.shortDescription = shortDescription.substring(0, 1999);
		} else {
			this.shortDescription = shortDescription;
		}
		this.shortDescription = shortDescription;
	}

	public String getAdditionalinfo() {
		return additionalinfo;
	}

	public void setAdditionalinfo(String additionalinfo) {
		this.additionalinfo = additionalinfo;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getFilenumber() {
		return filenumber;
	}

	public void setFilenumber(String filenumber) {
		if (filenumber.length() > 50) {
			this.filenumber = filenumber.substring(0, 49);
		} else {
			this.filenumber = filenumber;
		}
		this.filenumber = filenumber;
	}

	public String getPatent() {
		return patent;
	}

	public void setPatent(String patent) {
		this.patent = patent;
	}

	public String getPatentnumber() {
		return patentnumber;
	}

	public void setPatentnumber(String patentnumber) {
		if (patentnumber.length() > 30) {
			this.patentnumber = patentnumber.substring(0, 29);
		} else {
			this.patentnumber = patentnumber;
		}
		this.patentnumber = patentnumber;
	}

	public String getPotentialapplications() {
		return potentialapplications;
	}

	public void setPotentialapplications(String potentialapplications) {
		this.potentialapplications = potentialapplications;
	}

	public String getTechguid() {
		return techguid;
	}

	public void setTechguid(String techguid) {
		this.techguid = techguid;
	}

	public String getWebsite() {
		return website;
	}

	public void setWebsite(String website) {
		if (website.length() > 256) {
			this.website = website.substring(0, 255);
		} else {
			this.website = website;
		}
		this.website = website;
	}
}
