package com.asap.core.number;

import com.asap.core.Mutable;

public abstract class MutableNumber extends Number implements Comparable,
		Cloneable, Mutable {

	public byte byteValue() {
		return (byte) longValue();
	}

	public short shortValue() {
		return (short) longValue();
	}

	public int intValue() {
		return (int) longValue();
	}

}
