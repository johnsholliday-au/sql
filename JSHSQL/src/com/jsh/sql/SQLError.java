package com.jsh.sql;

import org.antlr.v4.runtime.RecognitionException;
import org.antlr.v4.runtime.Token;

public class SQLError {

	
    Token                token;
    int                  line ;
    int                  pos  ;
    String               msg  ;
    RecognitionException e;

	public SQLError() {
	}
	
	public SQLError(Token token
			       , int line
			       , int pos 
			       , String msg
			       , RecognitionException e) {
		this.token=token;
		this.line=line;
		this.pos=pos;
		this.msg=msg;
		this.e=e;
	}

	public Token getToken() {
		return token;
	}

	public void setToken(Token token) {
		this.token = token;
	}

	public int getLine() {
		return line;
	}

	public void setLine(int line) {
		this.line = line;
	}

	public int getPos() {
		return pos;
	}

	public void setPos(int pos) {
		this.pos = pos;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public RecognitionException getE() {
		return e;
	}

	public void setE(RecognitionException e) {
		this.e = e;
	}
	
}
