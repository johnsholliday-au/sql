package com.jsh.sql;

public class SQLFmtOptions {
	
	
	boolean debug    = false;
	boolean fmtError = false;
	int     ll       = 72;

	public boolean fmtError() {
		return fmtError;
	}

	public boolean debug() {
		return debug;
	}

	public void setDebug(boolean debug) {
		this.debug = debug;
	}

	public int getLl() {
		return ll;
	}

	public void setLl(int ll) {
		this.ll = ll;
	}

	public boolean isFmtError() {
		return fmtError;
	}

	public void setFmtError(boolean fmtError) {
		this.fmtError = fmtError;
	}

	public boolean isDebug() {
		return debug;
	}
	 
	
	

	
}
