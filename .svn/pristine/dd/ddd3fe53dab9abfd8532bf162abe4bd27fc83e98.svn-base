/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.asap.util;

import com.asap.configuration.Configuration;
import javax.mail.PasswordAuthentication;

/**
 *
 * @author mortenmatras
 */
public class SMTPAuthenticator extends javax.mail.Authenticator {

    public PasswordAuthentication getPasswordAuthentication() {
        String username = Configuration.getInstance().getProps().getProperty("smtp.user");//"leaddoubler_1";
        String password = Configuration.getInstance().getProps().getProperty("smtp.psw");//"vse4ever";
        return new PasswordAuthentication(username, password);
    }
}
