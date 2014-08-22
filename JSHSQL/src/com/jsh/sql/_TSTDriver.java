package com.jsh.sql;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import org.antlr.v4.runtime.ANTLRErrorStrategy;
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
	    inputFile = "Files\\Test Data\\TSTINTO.txt";
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
        
//		// 3. Parse the tokens
        System.out.println("3. Parse the tokens");
        JSHSQL           parser = new JSHSQL(tokens); 

        
        //////////////////////////////////////////////////////////////
        // Display the tree (in GUI) 
        // N.B. this consumes the tokens
//        parser.sql_statement().inspect(parser);
//        if(true) return;
        // N.B don't have to start at the start rule!! 
        //parser.select_statement().inspect(parser);
        
        // 4. Build the parse tree;
        System.out.println("4. Build the parse tree");
        ParseTree          tree = parser.sql_statement();
        ANTLRErrorStrategy err = parser.getErrorHandler();
        System.out.println(err.toString());
        ////////////////////////////////////////////////////////////// 
        // 5. walk the parse tree (Build SQLStatement Object)
        System.out.println("5. Walk the tree");

        ParseTreeWalker        walker = new ParseTreeWalker();
        SQLPrintListener     listener = new SQLPrintListener();
        walker.walk(listener,  tree);
        
        //////////////////////////////////////////////////////////////
        // 5. Format the SQL Statement
//      SelectStatement sel = listener.getSelectStatement();
//      System.out.println(sel.toString());
//      
        System.out.println("5. Print the formatted statement");
        ArrayList<String> stmt = listener.getStmt();
        for(String s : stmt){
        	System.out.println(s);
        }
        System.out.println("   Done..");
		
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();

   }
}
}
