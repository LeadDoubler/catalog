package com.asap.core.number;

import java.io.Serializable;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

import org.hibernate.HibernateException;
import org.hibernate.engine.SessionImplementor;
import org.hibernate.usertype.UserType;

public class MutableIntegerType implements UserType {

	private static final int[] TYPES = { Types.BIGINT };

	public int[] sqlTypes() {
		return TYPES;
	}

	public Class returnedClass() {
		return MutableInteger.class;
	}

	public boolean equals(Object x, Object y) {
		if (x == y)
			return true;
		if (x == null || y == null)
			return false;
		return ((MutableInteger) x).equals((MutableInteger) y);
	}

	public Object deepCopy(Object x) {
//		String result = x.toString();
		return x;
	}

	public boolean isMutable() {
		return true;
	}

	/*public Object nullSafeGet(ResultSet rs, String[] names, Object owner)
			throws HibernateException, SQLException {
		MutableInteger first = new MutableInteger((String) StandardBasicTypes.STRING
				.nullSafeGet(rs, names[0]));
		return first;
	}

	public void nullSafeSet(PreparedStatement st, Object value, int index)
			throws HibernateException, SQLException {
		StandardBasicTypes.STRING.nullSafeSet(st, value.toString(), index);
	}*/

	public Object assemble(Serializable cached, Object owner)
			throws HibernateException {
		// TODO Auto-generated method stub
		return null;
	}

	public Serializable disassemble(Object value) throws HibernateException {
		// TODO Auto-generated method stub
		return null;
	}

	public int hashCode(Object x) throws HibernateException {
		// TODO Auto-generated method stub
		return 0;
	}

	public Object replace(Object original, Object target, Object owner)
			throws HibernateException {
		// TODO Auto-generated method stub
		return null;
	}

    public Object nullSafeGet(ResultSet rs, String[] strings, SessionImplementor si, Object o) throws HibernateException, SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void nullSafeSet(PreparedStatement ps, Object o, int i, SessionImplementor si) throws HibernateException, SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public Object nullSafeGet(ResultSet rs, String[] strings, Object o) throws HibernateException, SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void nullSafeSet(PreparedStatement ps, Object o, int i) throws HibernateException, SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

}
