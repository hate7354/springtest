package com.base.webapp.common.tray;

import java.io.Serializable;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.Set;

public class RequestTray extends AbstractTray implements Serializable {

	public Enumeration getEnumeration() {
        return new Enumeration() {
            Set set = map.keySet();
            Iterator it = set.iterator();

            public boolean hasMoreElements() {
                return size() != 0;
            }

            public Object nextElement() {
                String key = (String)it.next();
                return map.get(key);
            }
        };
    }

}
