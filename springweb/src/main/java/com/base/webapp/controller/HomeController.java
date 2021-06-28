package com.base.webapp.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;

import com.base.webapp.cmd.BaseCommand;
import com.base.webapp.common.tray.ResultSetTray;
import com.base.webapp.common.tray.Tray;

/**
 * Handles requests for the application home page.
 */



@Controller
public class HomeController extends BaseController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	Tray  reqTray;
	BaseCommand command;
	
	@Override
		public ResultSetTray DoService(HttpServletRequest request, HttpServletResponse response) {
			// TODO Auto-generated method stub
		
		
		System.out.println("BoardServlet doService() start");
		reqTray = getRequestTray(request);
		command = createCommand(reqTray.getString("cmd"));
		
		System.out.println("execute start");
		ResultSetTray tray=command.execute(reqTray, request, response);
		//command.endCommand();
		nextpage = command.getNextPage();
		
				
				
				
				
		
			logger.info("test");
			
			return tray;
		
		}
	
	
	
	
}
