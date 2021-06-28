package com.base.webapp.dao;

import java.sql.Connection;

import com.base.webapp.common.tray.ResultSetTray;
import com.base.webapp.common.tray.Tray;
import com.base.webapp.sql.QueryRunner;


public class BoardDao extends BaseDao{
	public String nextpage="";

	
	/*
	 * �ڵ���� ȸ���ڵ� �ֿ� select ����
	 * �ۼ���: �ֽ���
	 */
		public ResultSetTray List(Connection conn,Tray reqTray){
			 
			QueryRunner    queryRunner = null;
			ResultSetTray  rsTray      = null;
			StringBuffer   query       = null;
			
			query=new StringBuffer();

			query.append("select 			*							 \n");
//			query.append("company_id, 									 \n");		
//			query.append("date_format(REG_DT,'%Y/%m/%d %H:%i') as timeg, \n");
//			query.append("company_name 									 \n");		
//			query.append("from TBL_COMPANY 								 \n");
//			query.append("order by company_id 							 \n");		
			
			query.append(" from  login");
			queryRunner = new QueryRunner("BoardDao List(Connection conn,Tray reqTray)",query.toString(), reqTray); 
			try {
				rsTray = (ResultSetTray)queryRunner.query(conn);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		return rsTray;
		}
	}