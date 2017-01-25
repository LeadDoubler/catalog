/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.blob.stats;

import com.asap.catalog.dao.Component;
import java.util.Date;
import java.util.Locale;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import org.hibernate.annotations.Type;

/**
 *
 * @author Morten
 */
@Entity
@Table(name = "WebRequest")
public class WR extends Component {

    @Type(type = "text")
    private String remoteAddr;
    @Type(type = "text")
    String remoteHost;
    @Type(type = "text")
    String remoteUser;
    @Temporal(value = TemporalType.TIMESTAMP)
    private Date requestTime;
    @Type(type = "text")
    private String browser;
    private Locale locale;
    @Type(type = "text")
    private String searchFor;

    private String pathInfo;
    private Long userId;

    @Type(type = "text")
    private String referer;

    private String pageReferer;

    private Long formId;

    /*@ManyToOne
    private RequestSession requestSession;
     */
    private String sessionId;

    private String affiliate;

    /**
     * @return the requestTime
     */
    public Date getRequestTime() {
        return requestTime;
    }

    /**
     * @param requestTime the requestTime to set
     */
    public void setRequestTime(Date requestTime) {
        this.requestTime = requestTime;
    }

    /**
     * @return the browser
     */
    public String getBrowser() {
        return browser;
    }

    /**
     * @param browser the browser to set
     */
    public void setBrowser(String browser) {
        // truncate User-Agent data if it's too long
        if (browser != null && browser.length() > 255) {
            browser = browser.substring(0, 255);
        }
        this.browser = browser;
    }

    /**
     * @return the locale
     */
    public Locale getLocale() {
        return locale;
    }

    /**
     * @param locale the locale to set
     */
    public void setLocale(Locale locale) {
        this.locale = locale;
    }

    /**
     * @return the searchFor
     */
    public String getSearchFor() {
        return searchFor;
    }

    /**
     * @param searchFor the searchFor to set
     */
    public void setSearchFor(String searchFor) {
        this.searchFor = searchFor;
    }

    /**
     * @return the remoteAddr
     */
    public String getRemoteAddr() {
        return remoteAddr;
    }

    /**
     * @param remoteAddr the remoteAddr to set
     */
    public void setRemoteAddr(String remoteAddr) {
        this.remoteAddr = remoteAddr;
    }

    /**
     * @return the remoteHost
     */
    public String getRemoteHost() {
        return remoteHost;
    }

    /**
     * @param remoteHost the remoteHost to set
     */
    public void setRemoteHost(String remoteHost) {
        this.remoteHost = remoteHost;
    }

    /**
     * @return the remoteUser
     */
    public String getRemoteUser() {
        return remoteUser;
    }

    /**
     * @param remoteUser the remoteUser to set
     */
    public void setRemoteUser(String remoteUser) {
        this.remoteUser = remoteUser;
    }

    /**
     * @return the referer
     */
    public String getReferer() {
        return referer;
    }

    /**
     * @param referer the referer to set
     */
    public void setReferer(String referer) {
        this.referer = referer;
    }

    /**
     * @return the sessionId
     */
    public String getSessionId() {
        return sessionId;
    }

    /**
     * @param sessionId the sessionId to set
     */
    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    /**
     * @return the pathInfo
     */
    public String getPathInfo() {
        return pathInfo;
    }

    /**
     * @param pathInfo the pathInfo to set
     */
    public void setPathInfo(String pathInfo) {
        this.pathInfo = pathInfo;
    }

    public String getAffiliate() {
        return affiliate;
    }

    public void setAffiliate(String affiliate) {
        this.affiliate = affiliate;
    }

    /**
     * @return the userId
     */
    public Long getUserId() {
        return userId;
    }

    /**
     * @param userId the userId to set
     */
    public void setUserId(Long userId) {
        this.userId = userId;
    }

    /**
     * @return the pageReferer
     */
    public String getPageReferer() {
        return pageReferer;
    }

    /**
     * @param pageReferer the pageReferer to set
     */
    public void setPageReferer(String pageReferer) {
        this.pageReferer = pageReferer;
    }

    /**
     * @return the formId
     */
    public Long getFormId() {
        return formId;
    }

    /**
     * @param formId the formId to set
     */
    public void setFormId(Long formId) {
        this.formId = formId;
    }
}
