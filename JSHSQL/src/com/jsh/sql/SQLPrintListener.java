package com.jsh.sql;

import java.util.ArrayList;
import java.util.Stack;

import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeProperty;
import org.antlr.v4.runtime.tree.TerminalNode;

import com.jsh.sql.JSHSQL.Common_table_expressionContext;
import com.jsh.sql.JSHSQL.Correlation_clauseContext;
import com.jsh.sql.JSHSQL.Cte_fullselectContext;
import com.jsh.sql.JSHSQL.Cursor_specContext;
import com.jsh.sql.JSHSQL.Fc_commaContext;
import com.jsh.sql.JSHSQL.Fetch_first_clauseContext;
import com.jsh.sql.JSHSQL.From_clauseContext;
import com.jsh.sql.JSHSQL.Fs_operatorContext;
import com.jsh.sql.JSHSQL.Group_by_clauseContext;
import com.jsh.sql.JSHSQL.Having_clauseContext;
import com.jsh.sql.JSHSQL.Insert_col_listContext;
import com.jsh.sql.JSHSQL.Into_clauseContext;
import com.jsh.sql.JSHSQL.Invalid_statementContext;
import com.jsh.sql.JSHSQL.Isolation_clauseContext;
import com.jsh.sql.JSHSQL.Join_opContext;
import com.jsh.sql.JSHSQL.Join_specContext;
import com.jsh.sql.JSHSQL.Optimize_clauseContext;
import com.jsh.sql.JSHSQL.Order_by_clauseContext;
import com.jsh.sql.JSHSQL.Pred_operatorContext;
import com.jsh.sql.JSHSQL.Read_only_clauseContext;
import com.jsh.sql.JSHSQL.Sc_itemContext;
import com.jsh.sql.JSHSQL.Sc_operatorContext;
import com.jsh.sql.JSHSQL.Search_conditionContext;
import com.jsh.sql.JSHSQL.Select_clauseContext;
import com.jsh.sql.JSHSQL.Select_statementContext;
import com.jsh.sql.JSHSQL.Skip_locked_data_clauseContext;
import com.jsh.sql.JSHSQL.Unsupported_statementContext;
import com.jsh.sql.JSHSQL.Update_clauseContext;
import com.jsh.sql.JSHSQL.Values_clauseContext;
import com.jsh.sql.JSHSQL.Where_clauseContext;
import com.jsh.sql.JSHSQL.With_clauseContext;


public class SQLPrintListener
//   implements JSHSQLListener {
     extends JSHSQLBaseListener {

	  ParseTreeProperty<Integer> values = new ParseTreeProperty<Integer>();
	    
	    public void setValue(ParseTree node, int value) { values.put(node, value); }
	    public int getValue(ParseTree node) { return values.get(node); }
	 
	    public void setAnnotations(ParseTreeProperty<Integer> values) {this.values=values ;}
	
	ArrayList<String> stmt = new ArrayList<String>();
	
	StringBuilder line = new StringBuilder();
	
	int ll = 72;
	
	int indent = 0;
	//boolean ignore = false;
	boolean noSpace = true;
	
	public ArrayList<String> getStmt() {
		stmt.add(line.toString());
		return stmt; 
	}
	
	String space(int len) {
		String s = "                                                            ";
		return s.substring(0,len);
	}
	
	void newline() {
		stmt.add(line.toString());
		line = new StringBuilder();
		line.append(space(indent));
		noSpace=true;
	}
	
	void write(String s) {
		// 1. leading space required?
		if(spaceRqd(s)) line.append(" ");
		
		// 2. write the string
		if (s.length() + line.length() > ll) {
			newline();
		}
		line.append(s);
		
		// 3. set the space flag
		setSpace(s);
		
		//
	}
	
	boolean spaceRqd(String s) {
		boolean rv = true;
		if (noSpace)		rv = false;
		if (s.equals(","))  rv = false;
		if (s.equals("."))  rv = false;
//	  //if (s.equals("'"))  rv = false;
//		if (s.equals(")"))  rv = false;
		
		return rv;
		
	}
	
	void setSpace( String s) {
		noSpace = false;
		if (s.equals("."))  noSpace = true;
		//if (s.equals(","))  noSpace = true;
		if (s.equals("("))  noSpace = true;
	}
	
	//-----------------------------------------------------------------
	
	
	public void visitTerminal(TerminalNode arg0) {
		//System.out.println(arg0.getText());
//		if (ignore)
//			ignore = false;
//		else {
//			if(arg0.getSymbol().getType() >= 0)
//			write(arg0.getText());
//		}
		
		if(arg0.getSymbol().getType() >= 0)
		write(arg0.getText());

	}

	//-----------------------------------------------------------------

	@Override
	public void enterUnsupported_statement(Unsupported_statementContext ctx) {
		String stmt = ctx.getChild(0).getChild(0).getText().toUpperCase();
		write ("-- "+stmt+" Statement not currently supported by the formatter");
		newline();
	}
	
	@Override
	public void enterInvalid_statement(Invalid_statementContext ctx) {
		String stmt = ctx.getChild(0).getText().toUpperCase();
		write ("-- "+stmt+" Is not a valid SQL Statement!!");
		newline();
	}
	
	@Override
	public void enterSc_item(Sc_itemContext ctx) {
		int len = 0;
		int i;
		for(i=0;i<ctx.getChildCount();i++){
			len+=ctx.getChild(i).getText().length();
		}
		if (len + line.length() + i >= ll) {
			newline();
		}
	}

	
	@Override
	public void enterInto_clause(Into_clauseContext ctx) {
		newline();
		write(" ");
		indent += 7;
	}
	
	@Override
	public void exitInto_clause(Into_clauseContext ctx) {
		indent -= 7;
	}

	@Override
	public void enterWith_clause(With_clauseContext ctx) {
		indent += 4;
		// 
	}

	@Override
	public void exitWith_clause(With_clauseContext ctx) {
		indent -= 4;
	}
	
	@Override
	public void enterCursor_spec(Cursor_specContext ctx) {
		
		indent += 3;
		newline();
	}
	
	int tbLength;
	@Override
	public void enterCommon_table_expression(Common_table_expressionContext ctx) {
		String tbIdentifier1 = ctx.cte_tb_identifier().identifier().getText();
		tbLength = tbIdentifier1.length();
		
		indent += tbLength + 3;
	}

	@Override
	public void exitCommon_table_expression(Common_table_expressionContext ctx) {
	//	indent -= ctx.cte_tb_identifier().identifier().getText().length() + 2;
	}	

	@Override
	public void enterFc_comma(Fc_commaContext ctx) {
		newline();
	}	
	
//	@Override
//	public void enterSearch_condition(Search_conditionContext ctx) {
//		
//		// loop through the children, and compute the Pred_op pos
//		int predPos = 0;
//		for(ParserRuleContext prc : ctx.pl ) {
//			if (prc instanceof Sc_predfmt1Context) {
//				predPos = process()
//			}
//			if (prc instanceof Sc_predfmt2Context) {
//				
//			}
//		}
//		
//		
//		
//	}	
//
//	int process()
	
//	@Override
//	public void enterCte_tb_identifier(Cte_tb_identifierContext ctx) {
//		String tbIdentifier1 = ctx.identifier().getText();
//		indent += ctx.identifier().getText().length() + 2;
//	}
//
//	@Override
//	public void exitCte_tb_identifier(Cte_tb_identifierContext ctx) {
//		//indent -= ctx.identifier().getText().length() + 2;
//	}

	@Override
	public void enterCte_fullselect(Cte_fullselectContext ctx) {
		indent-=tbLength + 2;
		// AS ( Select ... 
		newline();
		indent += 3;
	}

	@Override
	public void exitCte_fullselect(Cte_fullselectContext ctx) {
		indent -= 3;
		newline();
		newline();

	}
	
	//------------------------------------------------------------
	
	@Override
	public void enterFs_operator(Fs_operatorContext ctx) {
		newline();
		newline();
	}

	@Override
	public void exitFs_operator(Fs_operatorContext ctx) {
		newline();
		newline();

	}
	
	// -------------------------------------------------------
	@Override
	public void enterSelect_statement(Select_statementContext ctx) {
		//System.out.println("->Enter Select statement");
	}

	@Override
	public void enterSelect_clause(Select_clauseContext ctx) {
		indent+=7;
	}

	@Override
	public void exitSelect_clause(Select_clauseContext ctx) {
		indent-=7;
	}

	
	//////////////////////////////////////////////////////////////
	// FROM CLAUSE
	int corrPos;

	@Override
	public void enterFrom_clause(From_clauseContext ctx) {
		newline();
		write("  ");
		
		noSpace=true;
		indent+=5;
		corrPos = getValue(ctx);
	}
	@Override
	public void exitFrom_clause(From_clauseContext ctx) {
		indent-=5;
	}
	
//	@Override
//	public void enterSingle_table(Single_tableContext ctx) {
//		
//	}

	@Override
	public void enterCorrelation_clause(Correlation_clauseContext ctx) {

		if (line.length() < indent + corrPos + 7)
		  line.append(space((indent + corrPos + 7) - line.length() ));
	}

	@Override
	public void enterWhere_clause(Where_clauseContext ctx) {
		newline();
		//indent+=7;
		write(" ");
		noSpace=true;
	}
	
	@Override
	public void exitWhere_clause(Where_clauseContext ctx) {
		//indent-=7;
	}

	@Override
	public void enterGroup_by_clause(Group_by_clauseContext ctx) {
		newline();
	}
	
	@Override
	public void enterHaving_clause(Having_clauseContext ctx) {
		newline();
	}
	
	@Override
	public void enterFetch_first_clause(Fetch_first_clauseContext ctx) {
		newline();
	}
	@Override
	public void enterUpdate_clause(Update_clauseContext ctx) {
		newline();
	}
	
	@Override
	public void enterIsolation_clause(Isolation_clauseContext ctx) {
		newline();

	}
	
	@Override
	public void enterRead_only_clause(Read_only_clauseContext ctx) {
		newline();
		
	}
	
	@Override
	public void enterOptimize_clause(Optimize_clauseContext ctx) {
		newline();
		
	}
	
	@Override
	public void enterSkip_locked_data_clause(Skip_locked_data_clauseContext ctx) {
		newline();
	}
	
	@Override
	public void enterOrder_by_clause(Order_by_clauseContext ctx) {
		newline();
	}
	
	int insertColList;
	@Override
	public void enterInsert_col_list(Insert_col_listContext ctx) {
		insertColList = line.length();
		indent += (insertColList + 2);
	}
	
	@Override
	public void exitInsert_col_list(Insert_col_listContext ctx) {
		indent -= (insertColList + 2);
	}

	@Override
	public void enterValues_clause(Values_clauseContext ctx) {
		newline();
	}

	
//--------------------------------------------------------------
	
	int ji;
	@Override
	public void enterJoin_op(Join_opContext ctx) {
		newline();
				
		// calc length of join 
		ji = ctx.getChildCount()-1;
		for (int i =0;i<ctx.getChildCount();i++) {
			String ts = ctx.getChild(i).getText();
			ji+=ts.length();
		}
		ji-=3;
		ji-=3;
		indent+=ji;
	}

	@Override
	public void exitJoin_op(Join_opContext ctx) {
		
	}

	@Override
	public void enterJoin_spec(Join_specContext ctx) {
		newline();
		write("    ");
		noSpace=true;
	}
	@Override
	public void exitJoin_spec(Join_specContext ctx) {
		indent-=ji;
		
	}
	
	@Override
	public void enterSc_operator(Sc_operatorContext ctx) {
		newline();
		if (ctx.getText().equals("OR"))
		write("   ");
		if (ctx.getText().equals("AND"))
		write("  ");
	}
	
//	-----------------------
//  Predicates and Search Conditions
	Stack<Integer> scIndent = new Stack<Integer>();
	int cscindent;
	@Override
	public void enterSearch_condition(Search_conditionContext ctx) {
		int scindent = getValue(ctx);
		//push
		cscindent = scindent; 
	}

	@Override
	public void exitSearch_condition(Search_conditionContext ctx) {
		// pop
	}

	@Override 
	public void enterPred_operator(Pred_operatorContext ctx) {
		if (line.length() < indent + cscindent + 7)
		  line.append(space((indent + cscindent + 7) - line.length() ));
	}
	
			
}
