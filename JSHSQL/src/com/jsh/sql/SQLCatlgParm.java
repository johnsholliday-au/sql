package com.jsh.sql;

public class SQLCatlgParm {
	String  ssid;
	String  collid; 
	String  pkgName;
	int     stmtNo;
	
	SQLFmtOptions opt;

	
	public String getCollid() {
		return collid;
	}

	public void setCollid(String collid) {
		this.collid = collid;
	}

	public String getSsid() {
		return ssid;
	}

	public void setSsid(String ssid) {
		this.ssid = ssid;
	}

	public String getPkgName() {
		return pkgName;
	}

	public void setPkgName(String pkgName) {
		this.pkgName = pkgName;
	}

	public int getStmtNo() {
		return stmtNo;
	}

	public void setStmtNo(int stmtNo) {
		this.stmtNo = stmtNo;
	}

	public SQLFmtOptions getOpt() {
		return opt;
	}

	public void setOpt(SQLFmtOptions opt) {
		this.opt = opt;
	}
	
}
