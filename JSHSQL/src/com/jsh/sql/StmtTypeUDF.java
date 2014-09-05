// N.B.!! N.B.!! N.B.!! N.B.!! N.B.!! N.B.!! N.B.!! N.B.!! N.B.!! N.B.!! N.B.!! N.B.!! N.B.!! 
//
//  !! This code moved to separate class, so that it can be deployed independently!!
//
//
// N.B.!! N.B.!! N.B.!! N.B.!! N.B.!! N.B.!! N.B.!! N.B.!! N.B.!! N.B.!! N.B.!! N.B.!! N.B.!! 
//package com.citec.udf;
//
//import java.sql.Clob;
//import java.sql.SQLException;
//
//import com.jsh.sql.SQLStmtType;
//
///***********************************************************************
// * 
// * This is the DB2 UDF facade to the SQLStmtType class 
// * (contained in com.jsh.sql.jar, which must be in the classpath of the WLM A/S )
// *
// ***********************************************************************/
//
//public class StmtTypeUDF {
//	
//	
///* **********************************************************************
//
//--drop function SGCCDB2.STMTTYPE;                                 
//                                                                  
//CREATE FUNCTION SGCCDB2.STMTTYPE(clob)                            
//RETURNS VARCHAR(24)                                               
//EXTERNAL NAME                                                     
//        'SGCCDB2.JSHSQL:com.citec.udf.StmtTypeUDF.getStmtType'
//FENCED                                                            
//CALLED ON NULL INPUT                                              
//NOT VARIANT                                                       
//PARAMETER STYLE JAVA                                              
//LANGUAGE JAVA                                                     
//NO EXTERNAL ACTION                                                
//WLM ENVIRONMENT DTATWLM_JAVA                                      
//	 
//	 -- USAGE EXAMPLE --
//	 -- the UDF can then be used anywhere a column can be --
//	 
//SELECT   SUBSTR(collid,1,8)          AS collid  
//       , SUBSTR(NAME,1,8)            AS NAME    
//       , STMTNO                                 
//       , SUBSTR(STATEMENT,1,26)      AS STMT    
//FROM SYSIBM.SYSPACKSTMT                         
//WHERE LOCATION = ' '                            
//  AND COLLID   = 'SGSETEST'                     
//  AND STMTNO > 0                                
//  AND SGCCDB2.STMTTYPE(STATEMENT) LIKE '%error%'
//ORDER BY NAME, STMTNO, SECTNO                   
//
//*****************************************************************	 */
//	
//	/* --------------------------------------------------------------*/
//	/* Notes:                                                        */
//	/* 1. For UDF, method needs to be static!                        */
//	/* 2. Parameter Style java = normal java call conventions        */ 
//	/*    ie parameters passed to method, result is java return      */ 
//	/* --------------------------------------------------------------*/
//	
//	public static String getStmtType(Clob in)  {
//
//		String 	rawStmt;
//		String  stmtType;
//		int 	len=0;
//		
//		try {
//			rawStmt = in.getSubString(1, (int) in.length());
//			SQLStmtType sqltyp = new SQLStmtType();
//			stmtType = sqltyp.deriveType(rawStmt);
//			
//		} catch (SQLException e) {
//			e.printStackTrace();
//			stmtType = "Error!";
//		}
//		len = (stmtType.length() > 24 ? 24:stmtType.length());
//		return stmtType.substring(0,len);
//		
//	}
//
//}