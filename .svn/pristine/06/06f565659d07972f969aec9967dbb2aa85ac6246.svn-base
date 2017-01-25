/*
 * ShopCartOrderStatus.java
 *
 * Created on 18. juli 2007, 15:25
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package com.asap.catalog.enums;

public enum ShopCartOrderStatus {NOTORDERED(-1,"Not finished"),NEW(0,"Ny"),SEEN(1,"Set"),TREATED(2,"Behandlet"),SENT(3,"Afsendt");

ShopCartOrderStatus(int value, String title) {
		this.value = value;
		this.title = title;
	}

	private final int value;

	private final String title;
        
	public int getValue() {
		return value;
	}

	public String toString() {
		return title;
	}
}