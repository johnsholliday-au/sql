package com.jsh.sql;

import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeWalker;

public class SQLStmtType {

	boolean debug;
	
	public SQLStmtType() {
		debug = false;
	}
	
	public void setDebug(boolean parm) {
		debug=parm;
	}
	
	public String deriveType(String inString) {
		////////////////////////////////////////////////////////////////
		// Use an ANTLR generated parser to process a string
		// By the numbers, go!

		////////////////////////////////////////////////////////////////
		// 1. Set Up the Lexer 
		if (debug) System.out.println("1. Lex the code");
		ANTLRInputStream    input = new ANTLRInputStream(inString); 
		JSHLexer            lexer = new JSHLexer(input);
		CommonTokenStream  tokens = new CommonTokenStream(lexer);
		if (debug) System.out.println("   Done..");


		////////////////////////////////////////////////////////////////
		// 2. Create the Parser
		if (debug) System.out.println("2. Parse the tokens");
		JSHSQL             parser = new JSHSQL(tokens); 
		SQLErrorListener      err =  new SQLErrorListener();
		parser.removeErrorListeners(); 
		parser.addErrorListener(err);
		if (debug) System.out.println("   Done..");

		///////////////////////////////////////////////////////////////
		// 3. Build the parse tree;
		if (debug) System.out.println("3. Build the parse tree");
		ParseTree            tree = parser.sql_statement();
		if (debug) System.out.println("   Done..");

		////////////////////////////////////////////////////////////// 
		// 4. Walk the parse tree using our custom listener 
		if (debug) System.out.println("4. Walk the tree");
		ParseTreeWalker      walker = new ParseTreeWalker();
		SQLTypeListener    listener = new SQLTypeListener();
		walker.walk(listener,  tree);
		if (debug) System.out.println("   Done..");

		////////////////////////////////////////////////////////////// 
		// 5. get the result 
		if (debug) System.out.println("5. get the result");
		String rv = listener.getStmtType();

		if(err.containsErrors()) 
			rv += " (error)";
		if (debug) System.out.println("   Done..");


		return rv;
	}
	
	
	///////////////////////////////////////////////////////////////
	// API Example
	public static void main(String[] args) {
	
		SQLStmtType  sqltyp = new SQLStmtType();
		
		//String rawStmt = "SELECT * from SYSIBM.SYSTABLES";		
		//String rawStmt = "SELECT T1.T2.T3, col2 as col \n from XXXX SYSIBM.SYSTABLES";		
		//String rawStmt = "DECLARE C1 Cursor for SELECT * from SYSIBM.SYSTABLES";
		String rawStmt = "ROLLBACK";
		//String rawStmt = "COMMIT";
		//String rawStmt = "DECLARE xyz TABLE some other stuff";
		
		String stmtType = sqltyp.deriveType(rawStmt);
		
		System.out.println("Type is : "+stmtType);
	
		
	}

}
