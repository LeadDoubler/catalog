/*
 * DateManager.java
 *
 * Created on 19. marts 2007, 19:43
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package com.asap.catalog.dao.manager;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author Jens Rosenberg
 */
public class DateManager {
    
    private static final String[] months = {"Januar","Februar","Marts","April","Maj","Juni","Juli","August","September","Oktober","November","December"};
    
   public List<String> getMonthList(){
        List<String> result = new LinkedList();
        Calendar cal = new GregorianCalendar();
    
        for(int i=0;i<12;i++){
        
            String outp = months[cal.get(cal.MONTH)]+" "+(cal.get(cal.YEAR));
            if(cal.get(cal.MONTH) == cal.DECEMBER) cal.roll(cal.YEAR,true);
            cal.roll(cal.MONTH,true);
            result.add(outp);
        }
        return result;
    }
}
