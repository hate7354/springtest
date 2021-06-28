package com.base.webapp.common.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.base.webapp.controller.HomeController;

public class AppException extends RuntimeException {
	private String    message   = null;
    private Throwable throwable = null;
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
    
    public AppException(String msgObj) {
    	this(msgObj, (Throwable) null);
    }

    public AppException(String msgObj, Throwable throwableObj) {
        message = msgObj;
    	throwable = throwableObj;
       	writeLog();
    }

    private void writeLog() {
    	logger.error("[x.exception.AppException]");
    	logger.trace("AppException.message = " + message, throwable);
    }
}
