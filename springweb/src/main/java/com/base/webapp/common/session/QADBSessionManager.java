package com.base.webapp.common.session;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.base.webapp.common.tray.NomalTray;
import com.base.webapp.common.tray.ResultSetTray;
import com.base.webapp.common.tray.Tray;
import com.base.webapp.controller.HomeController;





public class QADBSessionManager extends SessionManager {
		
	private static final Logger logger = LoggerFactory.getLogger( QADBSessionManager.class);	
	
	public QADBAuthority getAuthority(HttpServletRequest request, Tray userTray) {
		QADBAuthority  authority  = null;
		ResultSetTray  rsTray    = null;
		int            rowCount   = 0;
		
		String         qi_class_1 = null;
		String         qi_class_2 = null;
		String         qi_class_3 = null;
		boolean        authList[] = null;
		
		
		
		qi_class_1 = userTray.getString("qi_class_1");
		qi_class_2 = userTray.getString("qi_class_2");
		qi_class_3 = userTray.getString("qi_class_3");
		authority = new QADBAuthority(qi_class_1, qi_class_2, qi_class_3);
		
		rsTray = (ResultSetTray)getDeptAuth(request);
		if(rsTray != null) {
			rowCount = rsTray.getRowCount();
			for (int i = 0; i < rowCount; i++) {
				if (rsTray.getString("qi_class_1", i).equals(qi_class_1) &&
					rsTray.getString("qi_class_2", i).equals(qi_class_1) &&
					rsTray.getString("qi_class_3", i).equals(qi_class_1)){
					
					authList = new boolean[10];

					for (int j = 1; j <= 10; j++) {
						authList[j - 1] = rsTray.getString("qi_auth_" + j, i).equals("N");
					}
					authority.setAuthority(authList);
					break;
				}
			}
		}
		return authority;
	}
	
	
	public boolean isAdmin(HttpServletRequest request) {
		String isAdmin = null;
		HttpSession session = request.getSession(false);

		if ( session != null ) {
			isAdmin = (String)session.getValue("sys_admin_auth");
		}
		return (isAdmin != null && isAdmin.equals("TRUE"));	
	}
	
	public boolean isApprove(HttpServletRequest request) {
		String isApprove = null;
		HttpSession session = request.getSession(false);

		if ( session != null ) {
			isApprove = (String)session.getValue("sys_appr_auth");
		}
		return (isApprove != null && isApprove.equals("TRUE"));	
	}
	
	public Tray getUserInfo(HttpServletRequest request) {
		HttpSession session = request.getSession(false);
		Tray        rtnTray = null;

		if ( session != null ) {
			rtnTray = (Tray)session.getValue("sys_user_info");
		}
		return rtnTray;
	}

	public Tray getDeptAuth(HttpServletRequest request) {
		HttpSession session = request.getSession(false);
		Tray        rtnTray = null;

		if ( session != null ) {
			rtnTray = (Tray)session.getValue("sys_dept_auth");
		}
		return rtnTray;
	}

	public void initSession(HttpServletRequest request, Tray userTray) {
    	NomalTray    sessionTray = null;
        HttpSession  session     = null;
        String       isAdmin     = null;
        String       isApprove   = null;
        
        session = request.getSession(true);
    	if (session.getValue("isLogin") != null && session.getValue("isLogin").equals("Y")) {
        } else {
        	logger.debug("UserTray\n" + ((ResultSetTray)userTray).toString());
        	
        	sessionTray = new NomalTray();
        	sessionTray.add("common_user_id",        userTray.getString("emp_no"));
        	sessionTray.add("kmc",    userTray.getString("emp_nm"));
        	sessionTray.add("hkmc",    userTray.getString("emp_nm"));

        	isAdmin = checkAdmin((ResultSetTray)request.getAttribute("dept_auth"));
           	isApprove = checkApprove((ResultSetTray)request.getAttribute("dept_auth"));
           	logger.debug("-->Is Admin : " + isAdmin +  "  IS Approvae : " + isApprove);
           	
			session.putValue("sys_user_info", sessionTray); 
        	session.putValue("sys_dept_auth", request.getAttribute("dept_auth"));
        	session.putValue("sys_admin_auth", isAdmin);
        	session.putValue("sys_appr_auth", isApprove);
			session.putValue("isLogin","Y");
        }
	}

   	private String checkApprove(ResultSetTray rsTray) {
    	boolean       isApprove = false;
    	
		if(rsTray != null) {
		}
    	return (isApprove)?"TRUE":"FALSE";
   	}
   	
	private String checkAdmin(ResultSetTray rsTray) {
		boolean       isAdmin = false;
		int           rowCount= 0;
		
		if(rsTray != null) {
			rowCount = rsTray.getRowCount();
			for (int i = 0; i < rowCount; i++) {
				if (rsTray.getString("qi_class_1", i).equals("000") &&
					rsTray.getString("qi_class_2", i).equals("000") &&
					rsTray.getString("qi_class_3", i).equals("000")){
					isAdmin = true;
					for (int j = 1; j <= 10; j++) {
						if (rsTray.getString("qi_auth_" + j, i).equals("N")) {
							isAdmin = false;
							break;
						}
					}
					break;
				}
			}
		}
		return (isAdmin)?"TRUE":"FALSE";
	}

	public void clearSession(HttpServletRequest request) {
		HttpSession session = request.getSession(false);

		if ( (session != null) && (session.getValue("sys_user_info") != null) ) {
			session.putValue("isLogin","N");
			session.removeValue("sys_user_info");
			session.invalidate();
			session = null;
			request.setAttribute("LOGOUT.RESULT", "LOGOUT.SUCCESS");
		}
		else {
			request.setAttribute("LOGOUT.RESULT", "LOGOUT.FAILURE");
		}
	}
}
