package com.asap.catalog.enums;

public enum Language {
    NONE(0,"None","NN","Ikke angivet"),
DANISH(1,"Dansk","da","Dansk")
        ,ENGLISH(2,"English","en","Engelsk"),
        FRENCH(2,"Francois","fr","Fransk"),
        GERMAN(2,"Deutch","de","Tysk");
    private String dkName;

Language(int value, String name, String code,String dkName) {
		this.value = value;
		this.name = name;
                this.code = code;
                this.dkName = dkName;               
	}

	private final int value;

	private final String name;
        
        private final String code;

	public int getValue() {
		return value;
	}

	public String getName() {
		return name;
	}
        
        public String getCode() {
            return code;
        }

    public String getDkName() {
        return dkName;
    }

    public void setDkName(String dkName) {
        this.dkName = dkName;
    }
}