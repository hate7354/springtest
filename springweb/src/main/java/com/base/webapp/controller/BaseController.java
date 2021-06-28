package com.base.webapp.controller;

import java.io.IOException;
import java.text.DateFormat;
import java.util.Date;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.ServletContextAware;

import com.base.webapp.cmd.BaseCommand;
import com.base.webapp.cmd.CommandManager;
import com.base.webapp.cmd.ErrorCommand;
import com.base.webapp.common.tray.RequestTrayFactory;
import com.base.webapp.common.tray.ResultSetTray;
import com.base.webapp.common.tray.Tray;
import com.base.webapp.common.tray.TrayFactory;


public class BaseController implements ServletContextAware  {
	String nextpage;
	
	/* @Autowired  : 자동주입
	 * 
	 * spring 은 view에서 처라된 파라미터 이름을 controller에서 정의하지 않으면(Autowired) 에러가 난다
	 * 
	 * */
	private static final Logger logger = LoggerFactory.getLogger(BaseController.class);

	public static String command_key = "init_key";
	@Autowired
	ServletContext context;
	
	
	
	public void setServletContext(ServletContext context) {
		this.context = context;
	}

	String message;

	
	public String getMessage() {
		return message;
	}

	
	
	public void setMessage(String message) {
		this.message = message;
	}

	public void init() throws Exception {

		logger.info("init 시작 .");
		// CommandManager
		
		//application
		context.setAttribute(BaseController.command_key, CommandManager.getCommandMapping());

	}

	protected Tray getRequestTray(HttpServletRequest request) {
		TrayFactory requestFactory = null;
		Tray reqTray = null;

		requestFactory = new RequestTrayFactory();
		reqTray = requestFactory.getTray(request);

		// System.out.println("reqTray:"+reqTray.);
		request.setAttribute("reqTray", reqTray);

		logger.debug("\n======================================\n");
		logger.debug("\nRemoteAddr:" + request.getRemoteAddr() + '\n');
		logger.debug(reqTray.toString());

		return reqTray;
	}

	protected BaseCommand createCommand(String commandName)  {
		BaseCommand command = null;
		ErrorCommand errorCommand = null;
		String exceptionType = null;
		String classFullPath = null;
		System.out.println("getCommand start");
		classFullPath = getCommand(commandName);
		System.out.println("classFullPath instance name:  " + classFullPath);
		if (classFullPath != null && classFullPath.length() > 0) {
			System.out.println("classFullPath " + classFullPath);
			try {
				command = (BaseCommand) Class.forName(classFullPath).newInstance();
				System.out.println("command " + command.toString());
			}catch(Exception e) {
				
					e.printStackTrace();
				
			}
		}

		if (command == null) {
			errorCommand = new ErrorCommand();
			errorCommand.setException(commandName, exceptionType, classFullPath);
			command = errorCommand;
		}

		return command;
	}

	private String getCommand(String commandName) {
		Map map = null;
		String command = null;

		map = (Map) context.getAttribute(command_key);
		command = (String) map.get(commandName);

		return command;
	}

	protected void forward(HttpServletRequest request, HttpServletResponse response, String nextPage)
			throws ServletException, IOException {
//		RequestDispatcher dispatcher = null;
//		dispatcher = getServletContext().getRequestDispatcher(nextPage);
//		dispatcher.forward(request, response);
//	
	
	}

	protected void setNoCache(HttpServletRequest request, HttpServletResponse response) {
		if (request.getProtocol().compareTo("HTTP/1.0") == 0) {
			response.setHeader("Pragma", "no-cache");
		} else if (request.getProtocol().compareTo("HTTP/1.1") == 0) {
			response.setHeader("Cache-Control", "no-cache");
		}
		response.setDateHeader("Expires", 0);
	}

	public void cleanUp() throws Exception {
		System.out.println("Spring Container is destroy! Customer clean up");
	}

	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String DoGet(HttpServletRequest request, HttpServletResponse response) {
		logger.info("Welcome home! The client locale is {}. get");

		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG);

		String formattedDate = dateFormat.format(date);
		
		ResultSetTray tray=DoService(request, response);
		
		return tray.NextPage("login");
	}

	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/", method = RequestMethod.POST)
	public ResultSetTray DoPost(HttpServletRequest request, HttpServletResponse response) {
		logger.info("Welcome home! The client locale is {}. post");

		ResultSetTray tray=DoService(request, response);
		return tray;
	}

	public ResultSetTray DoService(HttpServletRequest request, HttpServletResponse response) {
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG);

		String formattedDate = dateFormat.format(date);

		request.setAttribute("serverTime", formattedDate);
		return null;
	}

	
		
	public void DoDestory() {
		logger.info("destory==================================");
		
	}
	
	
}
