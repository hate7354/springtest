package com.base.webapp.sql;


import java.io.Reader;
import java.io.StringReader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.base.webapp.common.tray.ResultSetTrayFactory;
import com.base.webapp.common.tray.Tray;




public class QueryRunner {
	private static final Logger logger = LoggerFactory.getLogger(QueryRunner.class);
	
	
	private   static final int MAX_VARCHAR_LENGTH = 1333;
	protected static final int DEFAULT_FETCH_SIZE = 10;

	private String   sql                 = null;
	private int      paramCount          = 0;
	private boolean  storedProcedureCall = false;
	private List     paramNames          = null;
	private Object[] paramValues         = null;
	
	public QueryRunner(String sqlObj) {
		StringBuffer buf = new StringBuffer(sqlObj.length());
		StringBuffer paramName = new StringBuffer();
		
		int length = sqlObj.length();
		boolean partOfParamName = false;
		boolean partOfString = false;
		boolean nonSpaceAppeared = false;
		
        //Logger.logger("Query-->\n" + sqlObj);

		for (int i = 0; i < length; i++) {
			char c = sqlObj.charAt(i);
			
			if (!nonSpaceAppeared && !Character.isSpaceChar(c)) {
				if (c == '{') {
					storedProcedureCall = true;
				}
				nonSpaceAppeared = true;
			}
			
			if (partOfParamName) {
				if ((c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z') ||
					(c >= '0' && c <= '9') || c == '_') {
					paramName.append(c);
				} else {
					paramNames.add(paramName.toString());
					paramName.delete(0, paramName.length());
					buf.append(c);
					partOfParamName = false;
				}
			} else {
				switch (c) {
					case ':':
						if (!partOfString) {
							if (paramNames == null) {
								paramNames = new ArrayList();
							}
							paramCount++;
							partOfParamName = true;
							c = '?';
						}
						break;
					
					case '\'':
						partOfString = !partOfString;
						break;

					case '?':
						if (!partOfString) {
							paramCount++;
						}
						break;
				}
				buf.append(c);	
			   // System.out.println("param :"+c);
			}
		}
		
		if (partOfParamName) {
			paramNames.add(paramName.toString());
		}

		sql = buf.toString();
		paramValues = new Object[paramCount];
	}
	
	
	public int update(Connection conn) {
		PreparedStatement pstmt = null;
		int rows = 0;
		
		try {
			pstmt = storedProcedureCall ? conn.prepareStatement(sql) : conn.prepareCall(sql);
			pstmt.setFetchSize(DEFAULT_FETCH_SIZE);
			System.out.println("setupStatement start");
			setupStatement(pstmt, conn);
			
			rows = pstmt.executeUpdate();
		
			if(rows==1){
				conn.commit();
			}else{
				
				conn.rollback();
				
			}
		
		
		} catch (SQLException ex) {
			ex.printStackTrace();
		} finally {
			closeStatement(pstmt);
		}
		return rows;
	}

	public QueryRunner(String queryinfo,String sql, Tray paramTray) {
		this(sql);
		logger.info("[queryinfo ]  ", queryinfo);
		logger.info("[query ]  ", sql);

		setParams(paramTray);
	}

	public void setParams(String[] paramValuesObj) {
		System.arraycopy(paramValuesObj, 0, paramValues, 0, paramValuesObj.length);
	}
	
	public void setParams(Tray paramTray) {
		for (int i = 0; i < paramCount; i++) {
			paramValues[i] = paramTray.getString((String) paramNames.get(i));
		}
	}
	
	public void setParam(String paramName, Object paramValue) {
		for (int i = 0; i < paramValues.length; i++) {
			if (paramNames.get(i).equals(paramName)) {
				paramValues[i] = paramValue;
			}	
		}
	}

	public void clearParams() {
		for (int i = 0; i < paramValues.length; i++) {
			paramValues[i] = null;
		}
	}
	
	public int getParamCount() {
		return paramCount;
	}
	
	public String[] getParamNames() {
		return (String[]) paramNames.toArray(new String[0]);	
	}
	
	public Object[] getParamValues() {
		return paramValues;
	}
	
	public String getQuery() {
		return sql; 
	}

	public boolean isStoredProcedureCall() {
		return storedProcedureCall;
	}

	public Object query(Connection conn) throws Exception {
		PreparedStatement    statement  = null;
		ResultSet            resultSet  = null;
		Object               result     = null;
		ResultSetTrayFactory factory    = null;
		
		try {
			System.out.println("sql ->  "+sql);
			statement = conn.prepareStatement(sql);
			statement.setFetchSize(DEFAULT_FETCH_SIZE);
			setupStatement(statement, conn);
			resultSet = statement.executeQuery();
			
			factory = new ResultSetTrayFactory();
			result = factory.getTray(resultSet);

		} catch (SQLException ex) {
			//Logger.trace("QueryRunner class query function.", ex);
		} finally {
			closeResultSet(resultSet);
			closeStatement(statement);
		}
		return result;
	}
	
	private void setupStatement(PreparedStatement pstmt, Connection conn) throws SQLException {
		String str_value = null;
		Reader in        = null;

		try {
			for (int i = 0; i < paramCount; i++) {
				if (paramValues[i] instanceof String &&
						((String) paramValues[i]).length() >= MAX_VARCHAR_LENGTH) {
					str_value = (String) paramValues[i];
					System.out.println("str_value :"+str_value);
					in = new StringReader(str_value);
					pstmt.setCharacterStream(i + 1, in, str_value.length()); 
				} else {
					pstmt.setObject(i + 1, paramValues[i]);
				}
			}
		} catch (Exception ex) { }
	}
	
	private static void closeStatement(Statement statement) {
		try {
			if (statement != null) {
				statement.close();
			}
		} catch (SQLException ex) {	}
	}

	private static void closeResultSet(ResultSet resultSet) {
		try {
			if (resultSet != null) {
				resultSet.close();
			}
		} catch (SQLException ex) {	}
	}
}