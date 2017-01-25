package com.asap.core.number;

import java.sql.Types;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.type.CustomType;

@Entity
@Table(name = "counter")
public class MutableInteger extends Number {

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	private Long value = 0L;

	// By default value is 0
	public MutableInteger() {
	}

	public MutableInteger(String value) {
		this.value = Long.parseLong(value);
	}

	public MutableInteger(long integer) {
		value = integer;
	}

	public long increment() {
		return value = value + 1;
	}

	public long decrement() {
		return value = value - 1;
	}

	public long add(long amount) {
		return value = value + amount;
	}

	public long subtract(long amount) {
		return value = value - amount;
	}

	public long multiply(long factor) {
		return value = value * factor;
	}

	public long divide(long factor) {
		return value = value / factor;
	}

	public long compareTo(long other) {
		return (value < other) ? -1 : (value == other) ? 0 : 1;
	}

	public String toString() {
		return String.valueOf(value);
	}

	public int hashCode() {
		return value.intValue();
	}

	public boolean equals(Object obj) {
		if (obj == this)
			return true;

		if (obj != null && obj.getClass() == getClass()) {
			return value == ((MutableInteger) obj).getValue();
		}

		return false;
	}

	public long getValue() {
		return value;
	}

	public Object clone() {
		try {
			return super.clone();
		} catch (CloneNotSupportedException e) {
			throw new InternalError();
		}
	}

	@Override
	public double doubleValue() {
		return (double) getValue();
	}

	@Override
	public float floatValue() {
		return (float) getValue();
	}

	@Override
	public int intValue() {
		return value.intValue();
	}

	@Override
	public long longValue() {
		return (long) getValue();
	}

	public void setValue(int value) {
		this.value = ((Integer) value).longValue();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setValue(Long value) {
		this.value = value;
	}

}
