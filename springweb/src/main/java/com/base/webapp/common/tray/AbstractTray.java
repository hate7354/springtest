package com.base.webapp.common.tray;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class AbstractTray implements Tray {
	private static final Logger logger = LoggerFactory.getLogger(AbstractTray.class);

	private static String EMPTY_STRING = "";
	private static String[] EMPTY_STRING_ARRAY = new String[0];

	protected Map map = new TreeMap();
	public String test = "테스트 입니다 ";
	public String test2 = "테스트 입니다 ===========";
	public String test3 = "테스트 입니다333333333333333333333333 ===========";

	public String getTest3() {
		return test3;
	}

	public void setTest3(String test3) {
		this.test3 = test3;
	}

	public String getTest2() {
		return test2;
	}

	public void setTest2(String test2) {
		this.test2 = test2;
	}

	public Map getMap() {
		return map;
	}

	public void setMap(Map map) {
		this.map = map;
	}

	public String getTest() {
		return test;
	}

	public void setTest(String test) {
		this.test = test;
	}

	public String[] getKeys() {
		int iSize = map.size();
		Iterator it = map.keySet().iterator();
		String[] keys = new String[iSize];

		for (int i = 0; i < iSize && it.hasNext(); i++) {
			keys[i] = (String) it.next();
			// System.out.println("["+keys[i]+"]");

		}
		return keys;
	}

	public String[] getAllString(String key) {
		List list = (List) map.get(key);
		if (list == null) {
			return EMPTY_STRING_ARRAY;
		}

		String[] values = (String[]) list.toArray(EMPTY_STRING_ARRAY);
		return values;
	}

	public String get(String key) {
		return get(key, 0);
	}

	public String getString(String key) {
		String value = getString(key, 0);
		if (value == null)
			value = EMPTY_STRING;
		return value;
	}

	public int getInt(String key) {
		return getInt(key, 0);
	}

	public int getInt(String key, int index) {
		return Integer.parseInt(getString(key, index));
	}

	public String get(String key, int index) {
		String value = null;
		List list = null;

		list = (List) map.get(key);
		if (list != null) {
			try {
				value = (String) list.get(index);
			} catch (IndexOutOfBoundsException ex) {
				logger.trace("key = " + key + ", index = " + index, ex);
			}
		}
		return IsNull(value);
	}

	public String IsNull(String isnull) {

		if (isnull == null)
			isnull = "";
		return isnull;
	}

	public String getString(String key, int index) {
		String value = get(key, index);
		return (value == null) ? "" : value;
	}

	public void set(String key, String value) {
		set(key, new String[] { value });
	}

	public void set(String key, String[] values) {
		List list = null;
		int size = 0;

		if (key == null || values == null) {
			throw new java.lang.IllegalArgumentException("key�� value�� ������ �ֽ��ϴ�.");
		}

		list = this.newList();
		size = values.length;
		for (int i = 0; i < size; i++) {
			list.add(values[i]);
		}

		map.put(key, list);
	}

	public void set(String key, Object object) {
		if (key == null || object == null) {
			throw new java.lang.IllegalArgumentException("key�� value�� ������ �ֽ��ϴ�.");
		}
		map.put(key, object);
	}

	public void add(String key, String value) {
		List list = null;
		if (map.get(key) == null) {
			set(key, value);
		} else {
			list = (List) map.get(key);
			list.add(value);
		}
	}

	private List newList() {
		return new java.util.ArrayList();
	}

	public int size() {
		return map.size();
	}

	public int size(String key) {
		int iSize = 0;
		Object object = null;

		object = map.get(key);
		if (object != null) {
			iSize = ((List) object).size();
			// System.out.println("iSize->"+iSize);
		}

		return iSize;
	}

	protected String fillString(String value, int max) {
		StringBuffer buffer = null;
		if (max > value.length()) {
			buffer = new StringBuffer();
			buffer.append(value);

			for (int i = value.length(); i < max; i++) {
				buffer.append(' ');
			}
			value = buffer.toString();
		}
		return value;
	}

	public String toString() {
		String keys[] = null;
		String values[] = null;
		StringBuffer debugBuffer = null;

		debugBuffer = new StringBuffer();
		keys = getKeys();
		for (int i = 0; i < keys.length; i++) {
			values = getAllString(keys[i]);
			debugBuffer.append('[' + fillString(keys[i], 20) + "]\t");
			for (int j = 0; j < values.length; j++) {
				debugBuffer.append(values[j] + '\t');
			}
			debugBuffer.append('\n');
		}
		return debugBuffer.toString();
	}
}