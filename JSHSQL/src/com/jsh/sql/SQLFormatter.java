package com.jsh.sql;

import java.util.ArrayList;

import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeWalker;


public class SQLFormatter {
	
	SQLFmtOptions opt;
	//String fmtParmFile;

	public SQLFormatter() {
		// build default options
	}
	
	public SQLFormatter(SQLFmtOptions opt) {
		// use options supplied
		this.opt = opt;
	}
	 
	public ArrayList<String> format (String inString) {
		ArrayList<String> rv = new ArrayList<String>(); 
		
		////////////////////////////////////////////////////////////////
		// Use an ANTLR generated parser to process a string
		// By the numbers, go!

		////////////////////////////////////////////////////////////////
		// 1. Set Up the Lexer 
		if (opt.debug()) System.out.println("1. Lex the code");
		ANTLRInputStream    input = new ANTLRInputStream(inString); 
		JSHLexer            lexer = new JSHLexer(input);
		CommonTokenStream  tokens = new CommonTokenStream(lexer);
		if (opt.debug()) System.out.println("   Done..");


		////////////////////////////////////////////////////////////////
		// 2. Create the Parser
		if (opt.debug()) System.out.println("2. Parse the tokens");
		JSHSQL             parser = new JSHSQL(tokens); 
		SQLErrorListener      err =  new SQLErrorListener();
		parser.removeErrorListeners(); 
		parser.addErrorListener(err);
		if (opt.debug()) System.out.println("   Done..");

		///////////////////////////////////////////////////////////////
		// 3. Build the parse tree;
		if (opt.debug()) System.out.println("3. Build the parse tree");
		ParseTree            tree = parser.sql_statement();
		if (opt.debug()) System.out.println("   Done..");

		////////////////////////////////////////////////////////////// 
		// 4. Walk the parse tree using our custom listener 
		if (opt.debug()) System.out.println("4. Walk the tree (first time to build Annotations)");
		ParseTreeWalker        walker = new ParseTreeWalker();
		SQLAnnotateListener alistener = new SQLAnnotateListener();
		walker.walk(alistener,  tree);
		if (opt.debug()) System.out.println("   Done..");

		////////////////////////////////////////////////////////////// 
		// 4. Walk the parse tree using our custom listener 
		if (opt.debug()) System.out.println("4. Walk the tree (Second time to Format the statement)");
		//ParseTreeWalker      walker = new ParseTreeWalker();
		SQLPrintListener   flistener = new SQLPrintListener();
		flistener.setAnnotations(alistener.getAnnotations());
		walker.walk(flistener,  tree);
		if (opt.debug()) System.out.println("   Done..");

		////////////////////////////////////////////////////////////// 
		// 5. get the result 
		if (opt.debug()) System.out.println("5. get the result");
		if(err.containsErrors()) 
			if(opt.fmtError())
				rv = errorFormat(tokens, err); 	
			else 
				rv = basicFormat(tokens);
		else
			rv = flistener.getStmt();
		if (opt.debug()) System.out.println("   Done..");


		return rv;
	}

	ArrayList<String> basicFormat (CommonTokenStream  tokens) {
		ArrayList<String> rv = new ArrayList<String>(); 


		StringBuilder line = new StringBuilder(); 
		tokens.fill();
		for (Token tok : tokens.getTokens()) {
			if (line.length() + tok.getText().length() > opt.getLl()) {
				rv.add(line.toString());
				line = new StringBuilder();
			}
			line.append(tok.getText());
			line.append(" ");

		}
		rv.add(line.toString());

		return rv;
	}
	
	ArrayList<String> errorFormat (CommonTokenStream  tokens, SQLErrorListener err) {
		ArrayList<String> rv = new ArrayList<String>();

		ArrayList<SQLError> errList = err.getErrList();
		int erri = 0;
		SQLError lerr = errList.get(erri);
		

		String input     = tokens.getTokenSource().getInputStream().toString();
		String[] lines   = input.split("\n");
		for(int li = 0;li<lines.length;li++){
			rv.add(lines[li]);

			while (erri < errList.size() && lerr.getLine() - 1  == li) {
				StringBuilder line = new StringBuilder();
				for (int i=0; i<lerr.getPos(); i++) line.append(" ");
				int start = lerr.getToken().getStartIndex();
				int stop  = lerr.getToken().getStopIndex();
				if ( start>=0 && stop>=0 ) {
					for (int i=start; i<=stop; i++) line.append("^");
				}
				rv.add(line.toString());
				rv.add(lerr.getMsg());
				rv.add("    ");
				
				if (++erri < errList.size()) {
					lerr = errList.get(erri);
				}
			}
		}
		

		return rv;
	}



	
	///////////////////////////////////////////////////////////////
	// API Example
	public static void main(String[] args) {
	
		SQLFmtOptions opts = new SQLFmtOptions();
		opts.setFmtError(true);
		SQLFormatter  sqlf = new SQLFormatter(opts);
		
		String rawStmt = "SELECT * from SYSIBM.SYSTABLES";		
		//String rawStmt = "SELECT T1.T2.T3, col2 as col \n from XXXX SYSIBM.SYSTABLES";		
		//String rawStmt = "DECLARE C1 Cursor for SELECT * from SYSIBM.SYSTABLES";
		
		ArrayList<String> fmtStmt = sqlf.format(rawStmt);
	
		for(String line: fmtStmt) {
			System.out.println(line);
		}
		
	}
	
	
	
	
///////////////////////////////////////////////////////////////////////	

	
//	void setDefaultFileLocations() {
//		if (System.getProperty("os.name").equals("z/OS") ) {
//			// ok we're on the mainframe 
//			fmtParmFile = "//DD:FMTOPTS";
//		} else {
//			// We're on the Jump Box 
//			fmtParmFile = "Files\\Parameters\\FMTOPTS.txt";
//		}
//	}
	
}
