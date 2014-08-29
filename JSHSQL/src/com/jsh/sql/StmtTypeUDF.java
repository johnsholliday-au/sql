package com.jsh.sql;

import java.sql.Clob;

public class StmtTypeUDF {
	
	public static String getStmtType(Clob in)  {

		String     rawStmt = in.toString();
		SQLStmtType sqltyp = new SQLStmtType();
		String    stmtType = sqltyp.deriveType(rawStmt);
		
		int len = (stmtType.length() > 24 ? 24:stmtType.length());
		
		return stmtType.substring(0,len);
		
	}

}
