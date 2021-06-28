package com.base.webapp.util;

import java.io.IOException;
import java.io.Reader;
import java.io.StringWriter;

import com.base.webapp.common.tray.ResultSetTray;



public class CommonUtil {


	private static final int DEFAULT_READ_BUFFER_SIZE = 4000;

    public static boolean containsHangul(String str) {
    	for (int i = 0; i < str.length(); i++) {
    		Character.UnicodeBlock unicode_block = Character.UnicodeBlock.of(str.charAt(i));  
    		if (unicode_block == Character.UnicodeBlock.HANGUL_COMPATIBILITY_JAMO ||
    			unicode_block == Character.UnicodeBlock.HANGUL_JAMO ||
    			unicode_block == Character.UnicodeBlock.HANGUL_SYLLABLES) {
    			return true;
    		}
    	}
    	return false;
    }
    
    public static boolean containsWhiteSpace(String str) {
    	for (int i = 0; i < str.length(); i++) {
    		if (Character.isWhitespace(str.charAt(i))) {
    			return true;
    		}
    	}
    	return false;
    }

    public static String readToEnd(Reader in) throws IOException {
    	return readToEnd(in, DEFAULT_READ_BUFFER_SIZE);
    }
    
    public static String readToEnd(Reader in, int bufferSize) throws IOException {
    	StringWriter out        = null;
    	char[]       cbuf       = null;
    	int          chars_read = 0;
    	
    	out  = new StringWriter(bufferSize);
    	cbuf = new char[bufferSize];
    	chars_read = in.read(cbuf, 0, bufferSize);
    	
    	while (chars_read >= 0) {
    		out.write(cbuf, 0, chars_read);
    		chars_read = in.read(cbuf, 0, bufferSize);
    	}
    	return out.toString();
    }
    
    public String makeOption(ResultSetTray optionTray, String code, String value, String selCode, String defaultValue){
    	StringBuffer buffer = null;
    	String codeValue = null;
    	int rowCount = 0;
    	
    	buffer = new StringBuffer();
    	
    	if (defaultValue != null && defaultValue.length() > 0){
        	buffer.append("<option value=''>");
        	buffer.append(defaultValue);
        	buffer.append("</option>");
    	}
    	
    	if (optionTray != null && code != null && value != null){
        	rowCount = optionTray.getRowCount();
        	for (int i = 0; i < rowCount; i++) {
        		codeValue = optionTray.getString(code, i);
            	buffer.append("<option value='");
            	buffer.append(codeValue);
            	if (selCode.equals(codeValue)){
                	buffer.append("' selected>");
            	}else {
                	buffer.append("'>");
            	}
            	buffer.append(optionTray.getString(value, i));
            	buffer.append("</option>");
        	}
    	}
    	return buffer.toString();
    }
    
    public String getImageInfo(String goodsCode){
    	StringBuffer buffer = null;

    	buffer = new StringBuffer();
    	if (goodsCode != null && goodsCode.length() > 5) {
    		buffer.append("<a href='javascript:openGoods();'>");
    		buffer.append("<img border='0' src='http://image.lgeshop.com/image/");
    		buffer.append(goodsCode.substring(0, 2));
    		buffer.append("/");
    		buffer.append(goodsCode.substring(2, 4));
    		buffer.append("/");
    		buffer.append(goodsCode);
    		buffer.append("_N1.jpg'></a>");
    	}
    	return buffer.toString();	
    }
}

