package com.asap.core;

import java.util.HashMap;
import java.util.Map;

import com.asap.core.number.MutableInteger;

public class ShopCartStorage {
	private Map<Class, IdStorage> map = new HashMap<Class, IdStorage>();

	public long getCount(CountedObject object) {
		IdStorage idStorage = map.get(object.getClass());
		if (idStorage == null) {
			return 0;
		}
		return idStorage.getCount(object);
	}

	public long add(CountedObject object) {
		IdStorage idStorage = map.get(object.getClass());
		if (idStorage == null) {
			idStorage = new IdStorage();
			map.put(object.getClass(), idStorage);
		}
		return idStorage.add(object);
	}

	public long add(CountedObject object, int count) {
		IdStorage idStorage = map.get(object.getClass());
		if (idStorage == null) {
			idStorage = new IdStorage();
			map.put(object.getClass(), idStorage);
		}
		return idStorage.add(object, count);
	}

	public long remove(CountedObject object, int count) {
		IdStorage idStorage = map.get(object.getClass());
		if (idStorage == null) {
			idStorage = new IdStorage();
			map.put(object.getClass(), idStorage);
		}
		long result = idStorage.remove(object, count);
		if (idStorage.getSize() == 0) {
			map.remove(object.getClass());
		}
		return result;
	}

	class IdStorage {
		private Map<Long, MutableInteger> map = new HashMap<Long, MutableInteger>();

		public long getCount(CountedObject object) {
			MutableInteger mutableInteger = map.get(object.getId());
			if (mutableInteger == null) {
				return 0;
			}
			return mutableInteger.getValue();
		}

		public int getSize() {
			return map.size();
		}

		public long add(CountedObject object) {
			MutableInteger mutableInteger = map.get(object.getId());
			if (mutableInteger == null) {
				mutableInteger = new MutableInteger();
				map.put(object.getId(), mutableInteger);
			}
			return mutableInteger.increment();
		}

		public long add(CountedObject object, int count) {
			MutableInteger mutableInteger = map.get(object.getId());
			if (mutableInteger == null) {
				mutableInteger = new MutableInteger();
				map.put(object.getId(), mutableInteger);
			}
			return mutableInteger.add(count);
		}

		public long remove(CountedObject object, int count) {
			MutableInteger countedStorage = map.get(object.getId());
			if (countedStorage == null) {
				return 0;
			}
			long result = countedStorage.subtract(count);
			if (result <= 0) {
				map.remove(object.getId());
				return 0;
			}
			return result;
		}
	}

}
