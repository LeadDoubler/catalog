package com.asap.security;

public enum RoleModerator {

	GUEST(0, "Ingen"), USER(1, "Bruger"), AUTHORIZED(2, "Super-bruger"), CORRECTOR(
			3, "CMS korrektor"), MODERATOR(4, "Moderator");

	RoleModerator(int value, String name) {
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
}
