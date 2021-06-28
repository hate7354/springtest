package com.base.webapp.dao;

import java.sql.Connection;

import com.base.webapp.common.tray.ResultSetTray;
import com.base.webapp.common.tray.Tray;
import com.base.webapp.sql.QueryRunner;

public class LoginDao extends BaseDao{
	public String nextpage="";

	

		public ResultSetTray SelectLogin(Connection conn,Tray reqTray){
			 
			QueryRunner    queryRunner = null;
			ResultSetTray  rsTray      = null;
			StringBuffer   query       = null;
			
			query=new StringBuffer();
			// : -> view에서 넘어온 파라미터 이름에 식별자 flag: 붙이면 자동으로 매치되어서 값이 들어갑니다
			query.append("select * from login");
			queryRunner = new QueryRunner("SelectLogin (Connection conn,Tray reqTray)",query.toString(), reqTray); 
			try {
				rsTray = (ResultSetTray)queryRunner.query(conn);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		return rsTray;
		}
	
	
}
