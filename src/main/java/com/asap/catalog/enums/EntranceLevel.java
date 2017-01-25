package com.asap.catalog.enums;

public enum EntranceLevel {ZERO(0), ONE(1), TWO(2), THREE(3), FOUR(4), FIVE(5);

	EntranceLevel(int value) {
		this.value = value;
	}

	private final int value;

	public int getValue() {
		return value;
	}
}
