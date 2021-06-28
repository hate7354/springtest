package com.base.webapp.cmd;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.base.webapp.common.tray.ResultSetTray;
import com.base.webapp.common.tray.Tray;
import com.base.webapp.dao.LoginDao;

public class LoginCmd extends BaseCommand {
	private static final Logger logger = LoggerFactory.getLogger(BoardCmd.class);
	
	
public LoginCmd() {
	logger.info("LoginCmd");

	setNextPage("/spring_1/login.jsp");
}
	
	@Override
	public ResultSetTray execute(Tray reqTray, HttpServletRequest request, HttpServletResponse response) {
		final Logger logger = LoggerFactory.getLogger(LoginCmd.class);
			
		
		logger.info("do execute login실행 .");	
		
		ResultSetTray rsTray     = null;
		ResultSetTray checkoptiontray     = null;
		
		try {
			LoginDao dao = new LoginDao();
			/* reqTray:view 에서 넘어오는 모든 파라미터 이름,값을 가지고 있다 */
			rsTray=dao.SelectLogin(getConnection(), reqTray);
		
		for (int i = 0; i < rsTray.getRowCount(); i++) {
			
			logger.info("row : "+rsTray.get("userid",i)+"\n");
			logger.info("row : "+rsTray.get("userpw",i)+"\n");
			
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

	
