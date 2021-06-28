package com.base.webapp.common.tray;


import java.io.IOException;
import java.io.Reader;
import java.sql.Blob;
import java.sql.Clob;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.base.webapp.util.CommonUtil;



public class ResultSetTrayFactory extends TrayFactory {
	
	
	private static final Logger logger = LoggerFactory.getLogger(RequestTrayFactory.class);
    private String[] columnNames     = null;
    private String[] columnTypeNames = null;

    protected Tray createTray(ResultSet rs) {
    	int               columnCount = 0;
        ResultSetTray     tray        = null;
        ResultSetMetaData rsmd        = null;

        tray = new ResultSetTray();
        try {
            rsmd = rs.getMetaData();
            columnCount = rsmd.getColumnCount();
            setColumnNames(rsmd, tray, columnCount);
            setColumnTypeNames(rsmd, tray, columnCount);
            setColumnValues(rs, tray, columnCount);
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
        
		

        return tray;
    }

    private void setColumnNames(ResultSetMetaData rsmd, ResultSetTray tray, int columnCount) throws SQLException {
		columnNames = new String[columnCount];
		for (int i = 0; i < columnCount; i++) {
		    columnNames[i] = rsmd.getColumnName(i + 1).toLowerCase();
		}
		tray.setColumnNames(columnNames);
    }

    private void setColumnTypeNames(ResultSetMetaData rsmd, ResultSetTray tray, int columnCount) throws java.sql.SQLException {
    	columnTypeNames = new String[columnCount];
    	for (int i = 0; i < columnCount; i++) {
    	    columnTypeNames[i] = rsmd.getColumnTypeName(i + 1);
    	}
    	tray.setColumnTypeNames(columnTypeNames);
   	}
    
    private void setColumnValues(java.sql.ResultSet rs, ResultSetTray tray, int columnCount) throws java.sql.SQLException {
        List[] columnValues = null;
        Object col_obj      = null;
        String col_value    = null;
        int    row_cnt      = 0;
        
        columnValues = getEmptyList(columnCount);

        while (rs.next()) {        	
            for (int i = 0; i < columnCount; i++) {
                col_obj = rs.getObject(i + 1);
                
                if (col_obj == null) {
                	col_value = "";
                } else if (col_obj instanceof Clob) {
            		try {
                		Reader in = ((Clob) col_obj).getCharacterStream();
                		col_value = CommonUtil.readToEnd(in);
                		in.close();
            		} catch (IOException ex) {
            			//Log.debug("Clob 읽는과정에서 에러 발생");
            		}
                } else if (col_obj instanceof Blob) {
                	 new Exception("BLOB 읽기는 아직 구현되어있지 않습니다.");
                } else {
                        col_value = col_obj.toString();
                }
                columnValues[i].add(col_value);
            }
            row_cnt++;
        }

        if (columnValues != null) {
            for (int i = 0; i < columnCount; i++) {
                tray.set(columnNames[i], columnValues[i]);
            }
        }

        tray.setRowCount(row_cnt);
    }
    
    private List[] getEmptyList(int columnCount) {
        List[] columnValues = null;
        
        columnValues = new List[columnCount];
        for (int i = 0; i < columnCount; i++) {
            columnValues[i] = new ArrayList();
        }
        return columnValues;
    }

    protected Tray createTray(HttpServletRequest request) {
		return null;
	}
}