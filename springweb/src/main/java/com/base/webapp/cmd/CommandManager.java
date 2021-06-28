package com.base.webapp.cmd;

import java.util.HashMap;
import java.util.Map;



public class CommandManager {
	
	public static  Map getCommandMapping() {
		Map map = new HashMap();
		
		//해당하는 키가 접근못한다
		//해당하는 곳으로 이동하지 못한다
		//view js[ 접근 못한다
		map.put("test",          "com.base.webapp.cmd.BoardCmd");  
		map.put("login",          "com.base.webapp.cmd.LoginCmd");  
	
		return map;
	}
}