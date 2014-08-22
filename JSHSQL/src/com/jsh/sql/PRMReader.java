package com.jsh.sql;


public class PRMReader {

	
	
	
//	public ArrayList<String> format (String inString) {
//		ArrayList<String> rv = new ArrayList<String>(); 
//		
//		////////////////////////////////////////////////////////////////
//		// Use an ANTLR generated parser to process a string
//		// By the numbers, go!
//
//		////////////////////////////////////////////////////////////////
//		// 1. Set Up the Lexer 
//		if (opt.debug()) System.out.println("1. Lex the code");
//		ANTLRInputStream    input = new ANTLRInputStream(inString); 
//		JSHLexer            lexer = new JSHLexer(input);
//		CommonTokenStream  tokens = new CommonTokenStream(lexer);
//		if (opt.debug()) System.out.println("   Done..");
//
//
//		////////////////////////////////////////////////////////////////
//		// 2. Create the Parser
//		if (opt.debug()) System.out.println("2. Parse the tokens");
//		JSHSQL             parser = new JSHSQL(tokens); 
//		SQLErrorListener      err =  new SQLErrorListener();
//		parser.removeErrorListeners(); 
//		parser.addErrorListener(err);
//		if (opt.debug()) System.out.println("   Done..");
//
//		///////////////////////////////////////////////////////////////
//		// 3. Build the parse tree;
//		if (opt.debug()) System.out.println("3. Build the parse tree");
//		ParseTree            tree = parser.sql_statement();
//		if (opt.debug()) System.out.println("   Done..");
//
//		////////////////////////////////////////////////////////////// 
//		// 4. Walk the parse tree using our custom listener 
//		if (opt.debug()) System.out.println("4. Walk the tree");
//		ParseTreeWalker      walker = new ParseTreeWalker();
//		SQLPrintListener   listener = new SQLPrintListener();
//		walker.walk(listener,  tree);
//		if (opt.debug()) System.out.println("   Done..");
//
//		////////////////////////////////////////////////////////////// 
//		// 5. get the result 
//		if (opt.debug()) System.out.println("5. get the result");
//		if(err.containsErrors()) 
//			if(opt.fmtError())
//				rv = errorFormat(tokens, err); 	
//			else 
//				rv = basicFormat(tokens);
//		else
//			rv = listener.getStmt();
//		if (opt.debug()) System.out.println("   Done..");
//
//
//		return rv;
//	}
//	
//	
//	
//	public static void main(String[] args) {
//		String parmFile;
//		try {
//			System.out.println("Parm Parser SQL Formatter Starting...");
//			//////////////////////////////////////////////////////////////////////////
//			// 1. Set up file locations
//			System.out.println("1.  Set up file Locations");
//			if (System.getProperty("os.name").equals("z/OS") ) {
//				// ok we're on the mainframe 
//				parmFile  = "//DD:SQLPRM";
//			} else {
//				// We're on the Jump Box 
//				parmFile  = "Files\\Parameters\\BtchDrvrParm.txt";
//			}
//			System.out.println("    Done!!");
//			System.out.println("          ");
//
//			
//			/////////////////////////////////////////////////////////////////////////
//			// 2. Read the parameter
//			System.out.println("2.  Read the parameter");
//			BufferedReader brdr = FileFactory.newBufferedReader(parmFile);
//		    String         line = null;
//		    StringBuilder  stringBuilder = new StringBuilder();
//		    String         ls = System.getProperty("line.separator");
//
//		    while( ( line = brdr.readLine() ) != null ) {
//		        stringBuilder.append( line );
//		        stringBuilder.append( ls );
//		    }
//		    String parmString = stringBuilder.toString();
//			System.out.println("    Done!!");
//			System.out.println("          ");
//
//
//			
//			/////////////////////////////////////////////////////////////////////////
//			// 3. Create the Parm Parser 
//			System.out.println("3.  Create the Parm Parser");
//			PRMReader prdr = new PRMReader(parmString);
//			System.out.println("    Done!!");
//			System.out.println("          ");
//
//			
//			////////////////////////////////////////////////////////////////////////
//			// 4. Process the Parameter
//			System.out.println("4.  Process the Parameter");
//			
//
//			////////////////////////////////////////////////////////////////////////
//			// 5. format the stmt
//			System.out.println("5.  Format the statemet");
//			ArrayList<String> stmt = fmtr.format(sqlStmt);
//			System.out.println("    Done!!");
//			System.out.println("          ");
//
//			///////////////////////////////////////////////////////////////////////
//			// 6. print out the result
//			System.out.println("6.  Print the result");
//			BufferedWriter bwtr = FileFactory.newBufferedWriter(outFile);
//	        for(String s : stmt){
//	        	System.out.println(s);
//	        	bwtr.write(s);
//	        	bwtr.newLine();
//	        }
//	        bwtr.close();
//			System.out.println("    Done!!");
//			System.out.println("          ");
//	        
//		} catch (Exception e) {
//			e.printStackTrace();
//			System.exit(12);
//		}
//	}	
}
