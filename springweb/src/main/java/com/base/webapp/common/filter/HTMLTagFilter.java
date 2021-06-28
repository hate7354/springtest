package com.base.webapp.common.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.base.webapp.controller.HomeController;



public class HTMLTagFilter implements Filter{
	private static final Logger logger = LoggerFactory.getLogger(HTMLTagFilter.class);
		
	
	private FilterConfig config;	
	
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		
			
		 logger.info("do filter ======================");
		 
		chain.doFilter(new HTMLTagFilterRequestWrapper((HttpServletRequest)request), response);		
	}

	public void init(FilterConfig config) throws ServletException {
		this.config = config;		
	}
	
	public void destroy() {
		
	}
}
