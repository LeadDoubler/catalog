package com.asap.catalog.enums;

public enum BannerType {NONE(0,"none"),IMAGE(1,"image/jpeg"),FLASH(2,"application/x-shockwave-flash");

BannerType(int value, String name) {
		this.value = value;
		this.name = name;
	}

	private final int value;

	private final String name;

	public int getValue() {
		return value;
	}

	public String getName() {
		return name;
	}
        
        public static BannerType getBannerType(String name){
            if(name.startsWith(BannerType.IMAGE.getName ()) || name.startsWith("image/pjpeg")){
                return BannerType.IMAGE;
            }
            
            if(name.startsWith (BannerType.FLASH.getName ())){
                return BannerType.FLASH;
            }
            
            return BannerType.NONE;
        }
}