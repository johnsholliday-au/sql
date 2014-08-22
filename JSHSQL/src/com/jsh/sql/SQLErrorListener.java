package com.jsh.sql;

import java.util.ArrayList;

import org.antlr.v4.runtime.BaseErrorListener;
import org.antlr.v4.runtime.RecognitionException;
import org.antlr.v4.runtime.Recognizer;
import org.antlr.v4.runtime.Token;

public class SQLErrorListener extends BaseErrorListener {
	ArrayList<SQLError> errList;
	
	public SQLErrorListener () {
		errList = new ArrayList<SQLError>();
	}
	
	@Override
	public void syntaxError( Recognizer<?, ?> recognizer
			               , Object offendingSymbol
			               , int line 
			               , int charPositionInLine
			               , String msg
			               , RecognitionException e)
	{
		SQLError err = new SQLError((Token)offendingSymbol,
                line, charPositionInLine, msg, e 
                );
		errList.add(err);
	}

	
	public boolean containsErrors() { return errList.size() > 0; }

	public ArrayList<SQLError> getErrList() {
		return errList;
	}

	public void setErrList(ArrayList<SQLError> errList) {
		this.errList = errList;
	}


}
