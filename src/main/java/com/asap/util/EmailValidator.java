/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.asap.util;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;

/**
 *
 * @author mortenmatras
 */
public class EmailValidator {

    /**
     * Validate the form of an email address.
     *
     * <P>
     * Return <tt>true</tt> only if
     * <ul>
     * <li> <tt>aEmailAddress</tt> can successfully construct an
     * {@link javax.mail.internet.InternetAddress}
     * <li> when parsed with "@" as delimiter, <tt>aEmailAddress</tt> contains
     * two tokens which satisfy
     * {@link hirondelle.web4j.util.Util#textHasContent}.
     * </ul>
     *
     * <P>
     * The second condition arises since local email addresses, simply of the
     * form "<tt>albert</tt>", for example, are valid for
     * {@link javax.mail.internet.InternetAddress}, but almost always undesired.
     *
     * @param mail
     * @return
     */
    public static boolean isValidEmailAddress(String mail) {
        boolean valid = false;
        try {
            if (mail != null && mail.split("@")[1].contains(".")) {
                InternetAddress ad = new InternetAddress();
                ad.setAddress(mail);
                ad.validate();
                valid = true;
            }
        } catch (Exception ex) {
        }
        return valid;
    }
    /*
  public static boolean isValidEmailAddress(String aEmailAddress){
    if (aEmailAddress == null) return false;
    boolean result = true;
    try {
      InternetAddress emailAddr = new InternetAddress(aEmailAddress);
      if ( ! hasNameAndDomain(aEmailAddress) ) {
        result = false;
      }
    }
    catch (AddressException ex){
      result = false;
    }
    return result;
  }

  private static boolean hasNameAndDomain(String aEmailAddress){
    String[] tokens = aEmailAddress.split("@");
    return
     tokens.length == 2 && tokens[0]!= null && tokens[0].length()>0 && tokens[1]!= null && tokens[1].length()>0 ;
  }
     */
}
