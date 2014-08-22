package com.jsh.sql;

import java.io.BufferedWriter;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import com.citec.util.db2.UniversalDriver;
import com.ibm.jzos.FileFactory;
import com.jsh.fig4.FigReader;

public class FormatStmtFromCatlg {

	public static void main(String[] args) {
		
		String outFile;
		String parmFile;
		
		try {
			
			System.out.println("SQL Catalog Formatter Starting...");
			//////////////////////////////////////////////////////////////////////////
			// 1. set up file locations
			System.out.println("1.  Set up file Locations");
			if (System.getProperty("os.name").equals("z/OS") ) {
				System.out.println("    We're running on z/OS");
				// ok we're on the mainframe 
				outFile   = "//DD:SQLOUT";
				parmFile  = "//DD:SQLPRM";
			} else {
				// We're on the Jump Box
				System.out.println("    We're running on Windows");
				outFile   = "Files\\Output\\FormattedSQL.txt";
				parmFile  = "Files\\Parameters\\CATPARM.txt";
			}
			System.out.println("    Parm file   : "+parmFile);
			System.out.println("    Output File : "+outFile);
			System.out.println("    Done!!");
			System.out.println("          ");

			/////////////////////////////////////////////////////////////////////////
			// 2. read the parameter
			System.out.println("2.  Read the parameter");

			SQLCatlgParm catParm;
			FigReader    fr = new FigReader();
			InputStream  is = FileFactory.newInputStream(parmFile);
			Object   inparm = fr.readFile(is);

			if (inparm instanceof SQLCatlgParm ) {
				catParm=(SQLCatlgParm)inparm;
			} else {
				catParm=new SQLCatlgParm();
			}
			System.out.println("    Done!!");
			System.out.println("          ");

			
			/////////////////////////////////////////////////////////////////////////
			// 3. Read the SQL STMT
			System.out.println("3.  Read the SQL Statement");
			
			// 3.a Connect to DB2 
			Connection con;
			if (System.getProperty("os.name").equals("z/OS")  ) {
				// ok we're on the mainframe - do a type 2 connect
				Class.forName("com.ibm.db2.jcc.DB2Driver");
				con = DriverManager.getConnection ("jdbc:db2:"+catParm.getSsid());
			} else {
				// do the universal connect 
				UniversalDriver driver = new UniversalDriver();
				con = driver.getConnectionFor(catParm.getSsid());
			}

			// 3.b Execute the SQL stmt 
			PreparedStatement  selStmt=null;
			String             sqlStmt="";
		 	try {
	    		if (selStmt==null) {
	    			selStmt = con.prepareStatement(
	    					"SELECT STATEMENT          "+          
	    					"  FROM SYSIBM.SYSPACKSTMT "+
	    					" WHERE COLLID = ?         "+
	    					"   AND NAME   = ?         "+
	    					"   AND STMTNO = ?         " );
	    		}

	    		selStmt.setString(1, catParm.getCollid());
	    		selStmt.setString(2, catParm.getPkgName());
	    		selStmt.setInt   (3, catParm.getStmtNo());

	    		ResultSet rs = selStmt.executeQuery();

	    		if (rs.next() ) {
	    			sqlStmt = ( rs.getString(1));
	    		}

	    	} catch (Exception e) {
	    		e.printStackTrace();
	    	}
			System.out.println("    Done!!");
			System.out.println("          ");

			////////////////////////////////////////////////////////////////////////
			// 4. create the formatter
			System.out.println("4.  Create the formatter");
			SQLFmtOptions fmtParm = catParm.getOpt();
			SQLFormatter     fmtr = new SQLFormatter(fmtParm);
			System.out.println("    Done!!");
			System.out.println("          ");

			///////////////////////////////////////////////////////////////////////
			// 5. format the stmt
			System.out.println("5.  Format the stmt");
			ArrayList<String> stmt = fmtr.format(sqlStmt);
			System.out.println("    Done!!");
			System.out.println("          ");

			// 6. print out the result
			System.out.println("6.  Print the result");
			BufferedWriter bwtr = FileFactory.newBufferedWriter(outFile);
	        for(String s : stmt){
	        	System.out.println(s);
	        	bwtr.write(s);
	        	bwtr.newLine();
	        }
	        bwtr.close();
			System.out.println("    Done!!");
			System.out.println("          ");
	        
			System.out.println("SQL Catalog Formatter Complete");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}	

}
