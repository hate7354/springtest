package com.base.webapp.common.session;

public class QADBAuthority {
	private final int AUTH_OWNER    = 0;
	private final int AUTH_READ     = 1;
	private final int AUTH_DOWNLOAD = 2;
	private final int AUTH_PRINT    = 3;
	private final int AUTH_QnA_MGR  = 4;

	private String  QI_Class[] = null;
	private boolean authority[]     = null;
	
	public QADBAuthority(String c1, String c2, String c3) {
		QI_Class = new String[]{c1, c2, c3}; 
		authority= new boolean[10];
	}

	public boolean isAuthOwner() {
		return authority[AUTH_OWNER];
	}

	public boolean isAuthRead() {
		return authority[AUTH_READ];
	}
	
	public boolean isAuthDownload() {
		return authority[AUTH_DOWNLOAD];
	}

	public boolean isAuthPrint() {
		return authority[AUTH_PRINT];
	}

	public boolean isAuthQnAMGR() {
		return authority[AUTH_QnA_MGR];
	}

	public void setAuthority(boolean auth[]){
		System.arraycopy(auth, 0, authority, 0, authority.length);
	}

	public String getClass_1() {
		return QI_Class[0];
	}

	public String getClass_2() {
		return QI_Class[1];
	}

	public String getClass_3() {
		return QI_Class[2];
	}
}
