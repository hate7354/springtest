package com.base.webapp.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.base.webapp.common.tray.ResultSetTray;
import com.base.webapp.common.tray.Tray;
import com.base.webapp.sql.QueryRunner;

public class BaseDao {

	public int flagupdate = 0;

	/**
	 * 
	 * 
	 * �ۼ���: ����ȣ ����:���� ������Ʈ�� insert ,delete ������ flag ó
	 * 
	 * 
	 * 
	 * @param flagupdate
	 * @return
	 */

	public void setFlagUpdate(int flagupdate) {

		this.flagupdate = flagupdate;

	}

	public int getFlagUpdate() {
		return this.flagupdate;

	}

	public ResultSetTray CheckBoxList(Connection conn, Tray reqTray) {

		QueryRunner queryRunner = null;
		ResultSetTray rsTray = null;
		StringBuffer query = null;

		query = new StringBuffer();
		query.append(" select               \n");
		query.append(" 	(                	\n");
		query.append(" 	select           	\n");
		query.append(" 		count(*) 	\n");
		query.append(" 	from tbl_checkbox	\n");
		query.append(" 	)                	\n");
		query.append(" 	option_count     	\n");
		query.append(" 	,option_userid  	\n");
		query.append(" 	,option_name     	\n");
		query.append(" from tbl_checkbox           \n");
		query.append(" group by option_userid      \n");

		queryRunner = new QueryRunner("LoginDao CheckLogin(Connection conn,Tray reqTray)", query.toString(), reqTray);
		try {
			rsTray = (ResultSetTray) queryRunner.query(conn);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return rsTray;
	}

//=======================================================================================
//	��   Ī 	 :   �۾� ���� 
//	�ۼ���        :   �ֽ��� 		
//��    ¥        :   09-02-06 		
//���� ��¥   :
//=======================================================================================

	public ResultSetTray WorkCheckBoxList(Connection conn, Tray reqTray) {

		QueryRunner queryRunner = null;
		ResultSetTray rsTray = null;
		StringBuffer query = null;

		query = new StringBuffer();
		query.append(" select                      		\n");
		query.append(" 	optuser_key  					\n");
		query.append(" 	,optsystem_key     			  	\n");
		query.append(" from tbl_checkbox          	 	\n");

		queryRunner = new QueryRunner("BaseDao WorkCheckBoxList(Connection conn,Tray reqTray)", query.toString(),
				reqTray);
		try {
			rsTray = (ResultSetTray) queryRunner.query(conn);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return rsTray;
	}

//=======================================================================================
//	��   Ī 	 :   �ý���  ��� �˻� 
//	�ۼ���        :   ����� 		
//��    ¥        :   09-01-21 		
//���� ��¥   :
//=======================================================================================
	public ResultSetTray ServerCheckBoxList(Connection conn, Tray reqTray) {

		QueryRunner queryRunner = null;
		ResultSetTray rsTray = null;
		StringBuffer query = null;

		query = new StringBuffer();
		query.append(" 	select           	\n");
		query.append(" 	optuser_key,  		\n");
		query.append(" 	optsystem_key     	\n");
		query.append(" 	from tbl_checkbox	\n");

		queryRunner = new QueryRunner("LoginDao CheckLogin(Connection conn,Tray reqTray)", query.toString(), reqTray);
		try {
			rsTray = (ResultSetTray) queryRunner.query(conn);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return rsTray;
	}

	public String Qutter(String token) {
		token = "'" + token + "'";
		return token.trim();
	}

	protected void close(ResultSet resultSet) {
		if (resultSet != null) {
			try {
				resultSet.close();
			} catch (SQLException ex) {
			}
		}
	}

	protected void close(Statement statement) {
		if (statement != null) {
			try {
				statement.close();
			} catch (SQLException ex) {
			}
		}
	}

	protected void close(Statement statement, ResultSet resultSet) {
		close(resultSet);
		close(statement);
	}

	protected void close(ResultSet resultSet, Statement statement) {
		close(statement, resultSet);
	}
}
