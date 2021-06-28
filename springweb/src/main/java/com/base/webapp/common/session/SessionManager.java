package com.base.webapp.common.session;


import javax.servlet.http.HttpServletRequest;

import com.base.webapp.common.exception.AppException;
import com.base.webapp.common.tray.Tray;


public abstract class SessionManager {
	private static final String DEFAULT_SESSION_MANAGER = 
		"qadb.x.session.QADBSessionManager";
	
	public static SessionManager getInstance() {
		return getInstance(DEFAULT_SESSION_MANAGER);
	}
	
	public static SessionManager getInstance(String managerName) {
		try {
			return (SessionManager) Class.forName(managerName).newInstance();
		} catch (Exception ex) {
			throw new AppException("SessionManager.getInstance()���� ���� �߻�", ex);
		}
	}
	
	abstract public Tray getUserInfo(HttpServletRequest request);
}
