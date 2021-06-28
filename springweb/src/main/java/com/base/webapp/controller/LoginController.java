package com.base.webapp.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.base.webapp.common.tray.ResultSetTray;
import com.base.webapp.common.tray.Tray;

@Controller
public class LoginController {
	//
	private static final Logger logger = LoggerFactory.getLogger(BaseController.class);
	
	//login : url path
	@RequestMapping(value = "/login")
	public String DoLogin() {
		logger.debug("login debug======");
		//spring_1/login.jsp
		return "login";
	}
}
