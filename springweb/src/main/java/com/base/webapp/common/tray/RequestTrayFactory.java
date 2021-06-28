package com.base.webapp.common.tray;

import java.sql.ResultSet;
import java.util.Enumeration;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;




public class RequestTrayFactory extends TrayFactory {
	
	private static final Logger logger = LoggerFactory.getLogger(RequestTrayFactory.class);
	
	protected Tray createTray(ResultSet resultSet) {
		return null;
	}
	
	@Override
	protected Tray createTray(HttpServletRequest request) {
        Tray          tray       = null;
        Enumeration   namesEnum  = null;
        String        name       = null;
        String[]      values     = null;

        tray = new RequestTray();
        namesEnum = request.getParameterNames();
        while (namesEnum.hasMoreElements()) {
            name = (String)namesEnum.nextElement();
            values = request.getParameterValues(name);
            tray.set(name, values);  
        }
        return addCookiesToTray(request, tray);
    }
    
    private Tray addCookiesToTray(HttpServletRequest request, Tray tray) {
    	Cookie[] cookies = null;
		String   name    = null;
		String   value   = null;

		cookies = request.getCookies();
    	if (cookies != null) {
        	for (int i = 0; i < cookies.length; i++) {
        		name = cookies[i].getName();
        		value = cookies[i].getValue();
        		
        		if (name.indexOf("sys_") >= 0) {
        			if (tray.get(name) == null || "".equals(tray.get(name))) {
        				logger.debug("Set CookiesToTray - " + name + " : "+value);
        				tray.set(name, value);
        			} else {
        				logger.debug("Add CookiesToTray - " + name + " : "+value);
        				tray.add(name, value);
        			}
        		} else  {
        			logger.debug("Cookies - " + name + " : " + value);
        		}
        	}
    	}

    	return tray;
    }
}
