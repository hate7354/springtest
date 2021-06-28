package com.base.webapp.cmd;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.base.webapp.common.tray.Tray;




public class ErrorCommand extends BaseCommand {
	private String	exceptionType = null;
	private String	classFullPath = null;
	private String	commandName   = null;
	
	public ErrorCommand() {
		setNextPage("qa_error.jsp");
	}
	
	public void setException(String command, String type, String classPath) {
		commandName = command;
		exceptionType = type;
		classFullPath = classPath;
	}

	protected void doExecute(Tray reqTray, HttpServletRequest request, HttpServletResponse response) {
		
		if (exceptionType == null) {
			request.setAttribute("type", "BadCmd");
			request.setAttribute("message", commandName + "�� ���� Command�� ���ǰ� �����ϴ�.");
		} else {
			if (classFullPath == null) {
				request.setAttribute("type", "BadJsp");
				request.setAttribute("message", exceptionType);
			} else {
				request.setAttribute("type", "BadCls");
				request.setAttribute("message", classFullPath + " ������ " + exceptionType + "�� �߻��߽��ϴ�.");
			}
		}
		
	}
}
