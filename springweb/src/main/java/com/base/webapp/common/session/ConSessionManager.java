package com.base.webapp.common.session;


import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.base.webapp.common.tray.ResultSetTray;
import com.base.webapp.common.tray.Tray;



public class ConSessionManager {
	
	
	
	/*
	 * ��ü ������ �������� �� ����
	 */ 
	
	
	
	
	
	
	
	
	public static void InitSession(HttpServletRequest req){
        HttpSession hSession = getSession(req);
        hSession.invalidate();
		
		
	}
	
	
	
	
	//TBL_USER -> user_id 
    public static String getUserId(HttpServletRequest req) {
        String userId;

        try {
            HttpSession hSession = getSession(req);
            if (hSession.isNew()) {
                return null;
            }
            userId =  (String)hSession.getAttribute("CONSOLE_USERID");
        } catch(Exception e) {
            return null;
        }

        return userId;
    }
    
//  TBL_USER -> name
    public static String getName(HttpServletRequest req){
    	String name;
    	
        try {
            HttpSession hSession = getSession(req);
            if (hSession.isNew()) {
                return null;
            }
            name =  (String)hSession.getAttribute("CONSOLE_NAME");
        } catch(Exception e) {
            return null;
        }
        
        return name;
    	
    }
    
//    TBL_USER -> kind
    public static String getKind(HttpServletRequest req){
    	String kind;
    	
        try {
            HttpSession hSession = getSession(req);
            if (hSession.isNew()) {
                return null;
            }
            kind =  (String)hSession.getAttribute("CONSOLE_KIND");
        } catch(Exception e) {
            return null;
        }
        
        return kind;
    	
    }
    
    public static HttpSession getSession(HttpServletRequest req) {
        return req.getSession();
    }

    public static void setConsoleInfo(HttpServletRequest req,ResultSetTray userTray) {
        HttpSession hSession = getSession(req);

        hSession.setAttribute("CONSOLE_USERID",userTray.getString("user_id"));
        hSession.setAttribute("CONSOLE_NAME",userTray.getString("name"));
        hSession.setAttribute("CONSOLE_KIND",userTray.getString("kind"));
    }

	
	public static void GetSession(HttpServletRequest request, Tray userTray){
		HttpSession session=getSession(request);
		
		//sessionTray.add("CONSOLE_USERID",userTray.getString("user_id"));
    	
    	Enumeration en=	session.getAttributeNames();
		while(en.hasMoreElements()){
			String sessionid=(String)en.nextElement();
				String values=(String)session.getAttribute(sessionid);
				System.out.println("[ session "+sessionid+" ]    "+values);

				//sessionTray.set(sessionid,values);
		}
		
		
		//return sessionTray;
	}
	
	
	
	
	
	
	
	
	
	
}	