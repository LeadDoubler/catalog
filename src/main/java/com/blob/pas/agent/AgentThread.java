/*
 * AgentThread.java
 *
 * Created on 14. maj 2007, 16:48
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package com.blob.pas.agent;

/**
 *
 * @author mortenandersen
 */
public class AgentThread extends Thread{
    AgentController ac = new AgentController();
    
    
    /** Creates a new instance of AgentThread */
    public AgentThread() {
    } 
    
    public void run(){
        while (true){
            System.out.println("Run...");
            ac.execute(); 
            try {
                this.sleep(1000*60); //Run once a minute
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        }
    }
}
