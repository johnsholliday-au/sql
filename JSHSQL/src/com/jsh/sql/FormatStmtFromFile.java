package com.jsh.sql;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStream;
import java.util.ArrayList;

import com.ibm.jzos.FileFactory;
import com.jsh.fig4.FigReader;

public class FormatStmtFromFile {
	
	
	public static void main(String[] args) {
		String inFile;
		String outFile;
		String parmFile;
		try {
			System.out.println("SQL Formatter Starting...");
			//////////////////////////////////////////////////////////////////////////
			// 1. set up file locations
			System.out.println("1.  Set up file Locations");
			if (System.getProperty("os.name").equals("z/OS") ) {
				// ok we're on the mainframe 
				inFile    = "//DD:SQLIN";
				outFile   = "//DD:SQLOUT";
				parmFile  = "//DD:SQLPRM";
			} else {
				// We're on the Jump Box 
				inFile    = "Files\\Test Data\\_Test.txt";
				outFile   = "Files\\Output\\FormattedSQL.txt";
				parmFile  = "Files\\Parameters\\fmtParm.txt";
			}
			System.out.println("    In file     : "+inFile);
			System.out.println("    Parm file   : "+parmFile);
			System.out.println("    Output File : "+outFile);
			System.out.println("    Done!!");
			System.out.println("          ");

			/////////////////////////////////////////////////////////////////////////
			// 2. read the parameter
			System.out.println("2.  Read the parameter");
			SQLFmtOptions fmtParm;

			FigReader    fr = new FigReader();
			InputStream  is = FileFactory.newInputStream(parmFile);
			Object   inparm = fr.readFile(is);

			if (inparm instanceof SQLFmtOptions ) {
				fmtParm=(SQLFmtOptions)inparm;
			} else {
				fmtParm=new SQLFmtOptions();
			}
			System.out.println("    Done!!");
			System.out.println("          ");

			
			/////////////////////////////////////////////////////////////////////////
			// 3. create the formatter
			System.out.println("3.  Create the formatter");
			SQLFormatter fmtr = new SQLFormatter(fmtParm);
			System.out.println("    Done!!");
			System.out.println("          ");

			
			////////////////////////////////////////////////////////////////////////
			// 4. read in the SQL statement
			System.out.println("4.  Read in the SQL statement");
			BufferedReader brdr = FileFactory.newBufferedReader(inFile);
		    String         line = null;
		    StringBuilder  stringBuilder = new StringBuilder();
		    String         ls = System.getProperty("line.separator");

		    while( ( line = brdr.readLine() ) != null ) {
		        stringBuilder.append( line );
		        stringBuilder.append( ls );
		    }
		    String sqlStmt = stringBuilder.toString();
			System.out.println("    Done!!");
			System.out.println("          ");
			

			////////////////////////////////////////////////////////////////////////
			// 5. format the stmt
			System.out.println("5.  Format the statemet");
			ArrayList<String> stmt = fmtr.format(sqlStmt);
			System.out.println("    Done!!");
			System.out.println("          ");

			///////////////////////////////////////////////////////////////////////
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
	        
		} catch (Exception e) {
			e.printStackTrace();
			System.exit(12);
		}
	}	

}
