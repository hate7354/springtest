package com.base.webapp.cmd;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.base.webapp.common.tray.ResultSetTray;
import com.base.webapp.common.tray.Tray;
import com.base.webapp.dao.BoardDao;



public class BoardCmd  extends BaseCommand{
	private static final Logger logger = LoggerFactory.getLogger(BoardCmd.class);
	
	
public BoardCmd() {
	logger.info("BoardCmd");

	setNextPage("/next/page/index.jsp");
}
	
	@Override
	public ResultSetTray execute(Tray reqTray, HttpServletRequest request, HttpServletResponse response) {
		final Logger logger = LoggerFactory.getLogger(BoardCmd.class);
			
		
		logger.info("do execute 실행 .");	
		
		ResultSetTray rsTray     = null;
		ResultSetTray checkoptiontray     = null;
		
		try {
			BoardDao dao = new BoardDao();
			rsTray=dao.List(getConnection(), reqTray);
		
		for (int i = 0; i < rsTray.getRowCount(); i++) {
			
			logger.info("row:"+rsTray.get("userid",i)+"\n");
			
			//logger.info("row:"+rsTray.get(0));
		}
		
		} catch (Exception ex) {
				ex.printStackTrace();
			}
		finally{
			request.setAttribute("rsTray", rsTray);			
		}
		
		// TODO Auto-generated method stub
		return rsTray;
	}

	
	
	
}
