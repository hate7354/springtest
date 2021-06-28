package com.base.webapp.cmd;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.base.webapp.common.tray.ResultSetTray;
import com.base.webapp.common.tray.Tray;



public interface Command {
	
	
	ResultSetTray execute(Tray reqTray, HttpServletRequest request, HttpServletResponse response);
	
}
