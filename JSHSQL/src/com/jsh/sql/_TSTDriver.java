package com.jsh.sql;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeWalker;


public class _TSTDriver {

	public static void main(String[] args) {

		try {

			InputStream is;
			String inputFile;

			/////////////////////////////////////////////////////////////////
			// 1. Set up input 
			//inputFile = "Files\\Test Data\\KAM0031M.txt";
			//inputFile = "Files\\Test Data\\_Test.txt";
			inputFile = "Files\\Test Data\\TSTERR.txt";
			is = new FileInputStream(inputFile);
			ANTLRInputStream input = new ANTLRInputStream(is);

			////////////////////////////////////////////////////////////////
			// 2. Lex the code
			System.out.println("2. Lex the code");
			JSHLexer            lexer = new JSHLexer(input);
			CommonTokenStream  tokens = new CommonTokenStream(lexer);

			//      // Display the tokens
			//        tokens.fill();
			//        for (Object tok : tokens.getTokens()) {
			//			System.out.println(tok);
			//		}

			//////////////////////////////////////////////////////////////////
			// 3. Create the parser 
			System.out.println("3. Create the parser");
			JSHSQL           parser = new JSHSQL(tokens); 
			SQLErrorListener    err = new SQLErrorListener();
			parser.removeErrorListeners(); 
			parser.addErrorListener(err);


			//////////////////////////////////////////////////////////////
			// Display the tree (in GUI) 
			// N.B. this consumes the tokens
			//        parser.sql_statement().inspect(parser);
			//        if(true) return;
			// N.B don't have to start at the start rule!! 
			//parser.select_statement().inspect(parser);

			//////////////////////////////////////////////////////////////
			// 4. Build the parse tree;
			System.out.println("4. Build the parse tree");
			ParseTree          tree = parser.sql_statement();


			////////////////////////////////////////////////////////////// 
			// 5. walk the parse tree (Build SQLStatement Object)
			System.out.println("5. Walk the tree");

			ParseTreeWalker        walker = new ParseTreeWalker();
			SQLPrintListener     listener = new SQLPrintListener();
			walker.walk(listener,  tree);



			////////////////////////////////////////////////////////////// 
			// 6. get the result 
			System.out.println("6. get the result");
			ArrayList<String> stmt;
			if(err.containsErrors()) 
				stmt = errorFormat(tokens, err); 	
			else
				stmt = listener.getStmt();
			System.out.println("   Done..");

			//////////////////////////////////////////////////////////////
			// 5. Format the SQL Statement
			//      SelectStatement sel = listener.getSelectStatement();
			//      System.out.println(sel.toString());
			//      
			System.out.println("7. Print the formatted statement");
			for(String s : stmt){
				System.out.println(s);
			}
			System.out.println("   Done..");

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

		}
	}
	
	
	static ArrayList<String> errorFormat (CommonTokenStream  tokens, SQLErrorListener err) {
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

}
