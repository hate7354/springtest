package com.base.webapp.cmd;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.base.webapp.common.tray.ResultSetTray;
import com.base.webapp.common.tray.Tray;



public class BaseCommand  implements Command {
	private Connection connection = null;
	private String     nextPage   = null;
	private DataSource ds=null;

	
	
	protected Connection getConnection() throws Exception {
		try{
			   Class.forName("com.mysql.jdbc.Driver");

			   
			   String url="jdbc:mysql://127.0.0.1:3306/spring";
			   String id="spring";
			   String pass="spring";
			   
			   
			   connection=DriverManager.getConnection(url,id,pass);
		
			   System.out.println("connection sucess..");
		}catch(Exception e){e.printStackTrace();}	   	
	return connection;
	}
	
	
	/*
	protected Connection getConnection() throws Exception {
		
		
		   Class.forName("oracle.jdbc.driver.OracleDriver");
		   String    url    =    "jdbc:oracle:thin:@ 172.16.20.120:1521:orcl"; // DB ȯ�濡 �µ��� ��ġ�� �˴ϴ�.
		   String userid="kim";
		   String passwd="kim2154";
		   Statement stmt;
		   connection = DriverManager.getConnection(url,userid,passwd);
	return connection;
}
	*/
	
	
	
	
	public  Connection getConnection2() throws Exception {
		
		
		
    	System.out.println("Connection start...");
    	connection = ds.getConnection();
		
		return connection;
	}
	
	
	public void commit() throws Exception
    {
		if ( connection != null ) {
			connection.commit();
		}
    }

	public void endCommand() throws SQLException {

		if ( connection != null ) {
		System.out.println("---------Connection close-----------------");
			connection.close();
		}

		
	}

	


	@Override
	public ResultSetTray execute(Tray reqTray, HttpServletRequest request, HttpServletResponse response) {
		final Logger logger = LoggerFactory.getLogger(BaseCommand.class);
		logger.info("do execute");	
		
		
		
		// TODO Auto-generated method stub
		return null;
	}
	
	
	
	 protected void setNextPage(String nextPageObj) {
	        nextPage = nextPageObj;
	    }

	    public String getNextPage()  {
	       System.out.println("[get next page   ]  "+nextPage);
	    	if (nextPage == null) {
	    		System.out.println("get next page null");
	    		// throw new AppException("���� Command���� next page�� �������� �ʾҽ��ϴ�.");
	        }
	        return "/" + nextPage;
	    }

	
}
