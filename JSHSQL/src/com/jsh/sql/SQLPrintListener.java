package com.jsh.sql;

import java.util.ArrayList;

import org.antlr.v4.runtime.tree.TerminalNode;

import com.jsh.sql.JSHSQL.Common_table_expressionContext;
import com.jsh.sql.JSHSQL.Cte_fullselectContext;
import com.jsh.sql.JSHSQL.Cursor_specContext;
import com.jsh.sql.JSHSQL.Fetch_first_clauseContext;
import com.jsh.sql.JSHSQL.From_clauseContext;
import com.jsh.sql.JSHSQL.Fs_operatorContext;
import com.jsh.sql.JSHSQL.Group_by_clauseContext;
import com.jsh.sql.JSHSQL.Having_clauseContext;
import com.jsh.sql.JSHSQL.Into_clauseContext;
import com.jsh.sql.JSHSQL.Isolation_clauseContext;
import com.jsh.sql.JSHSQL.Join_opContext;
import com.jsh.sql.JSHSQL.Join_specContext;
import com.jsh.sql.JSHSQL.Optimize_clauseContext;
import com.jsh.sql.JSHSQL.Order_by_clauseContext;
import com.jsh.sql.JSHSQL.Read_only_clauseContext;
import com.jsh.sql.JSHSQL.Sc_itemContext;
import com.jsh.sql.JSHSQL.Sc_operatorContext;
import com.jsh.sql.JSHSQL.Select_clauseContext;
import com.jsh.sql.JSHSQL.Select_statementContext;
import com.jsh.sql.JSHSQL.Skip_locked_data_clauseContext;
import com.jsh.sql.JSHSQL.Unsupported_statementContext;
import com.jsh.sql.JSHSQL.Update_clauseContext;
import com.jsh.sql.JSHSQL.Where_clauseContext;
import com.jsh.sql.JSHSQL.With_clauseContext;


public class SQLPrintListener
//   implements JSHSQLListener {
     extends JSHSQLBaseListener {

	
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
		String stmt = ctx.getChild(0).getText().toUpperCase();
		write ("-- "+stmt+" Statement not currently supported by the formatter");
	}
	
	@Override
	public void enterSc_item(Sc_itemContext ctx) {
		int len = 0;
		for(int i=0;i<ctx.getChildCount();i++){
			len+=ctx.getChild(i).getText().length();
		}
		if (len + line.length() > ll) {
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

	@Override
	public void enterFrom_clause(From_clauseContext ctx) {
		newline();
		write("  ");
		noSpace=true;
		indent+=7;
	}
	@Override
	public void exitFrom_clause(From_clauseContext ctx) {
		indent-=7;
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
	
//	@Override
//	public void enterEveryRule(ParserRuleContext arg0) {
//		// TODO Auto-generated method stub
//		
//	}
//
//	@Override
//	public void exitEveryRule(ParserRuleContext arg0) {
//		// TODO Auto-generated method stub
//		
//	}
//
//	@Override
//	public void visitErrorNode(ErrorNode arg0) {
//		// TODO Auto-generated method stub
//		
//	}
//
//	@Override
//	public void enterTz_expression(Tz_expressionContext ctx) {
//		// TODO Auto-generated method stub
//		
//	}
//
//	@Override
//	public void exitTz_expression(Tz_expressionContext ctx) {
//		// TODO Auto-generated method stub
//		
//	}
//
//	@Override
//	public void enterPredicate(PredicateContext ctx) {
//		// TODO Auto-generated method stub
//		
//	}
//
//	@Override
//	public void exitPredicate(PredicateContext ctx) {
//		// TODO Auto-generated method stub
//		
//	}
//
//	@Override
//	public void enterTable_function_reference(
//			Table_function_referenceContext ctx) {
//		// TODO Auto-generated method stub
//		
//	}
//
//	@Override
//	public void exitTable_function_reference(Table_function_referenceContext ctx) {
//		// TODO Auto-generated method stub
//		
//	}
//
//	@Override
//	public void enterXp_passing(Xp_passingContext ctx) {
//		// TODO Auto-generated method stub
//		
//	}
//
//	@Override
//	public void exitXp_passing(Xp_passingContext ctx) {
//		// TODO Auto-generated method stub
//		
//	}
//
//	@Override
//	public void enterXp_lparen(Xp_lparenContext ctx) {
//		// TODO Auto-generated method stub
//		
//	}
//
//	@Override
//	public void exitXp_lparen(Xp_lparenContext ctx) {
//		// TODO Auto-generated method stub
//		
//	}
//
//	@Override
//	public void enterRow_change_expression(Row_change_expressionContext ctx) {
//		// TODO Auto-generated method stub
//		
//	}
//
//	@Override
//	public void exitRow_change_expression(Row_change_expressionContext ctx) {
//		// TODO Auto-generated method stub
//		
//	}
//
//	@Override
//	public void enterNp_op(Np_opContext ctx) {
//		// TODO Auto-generated method stub
//		
//	}
//
//	@Override
//	public void exitNp_op(Np_opContext ctx) {
//		// TODO Auto-generated method stub
//		
//	}
//
//	@Override
//	public void enterQp_fmt2(Qp_fmt2Context ctx) {
//		// TODO Auto-generated method stub
//		
//	}
//
//	@Override
//	public void exitQp_fmt2(Qp_fmt2Context ctx) {
//		// TODO Auto-generated method stub
//		
//	}
//
//	@Override
//	public void enterQp_fmt1(Qp_fmt1Context ctx) {
//		// TODO Auto-generated method stub
//		
//	}
//
//	@Override
//	public void exitQp_fmt1(Qp_fmt1Context ctx) {
//		// TODO Auto-generated method stub
//		
//	}
//
//	@Override
//	public void enterQp_fmt3(Qp_fmt3Context ctx) {
//		// TODO Auto-generated method stub
//		
//	}
//
//	@Override
//	public void exitQp_fmt3(Qp_fmt3Context ctx) {
//		// TODO Auto-generated method stub
//		
//	}
//
//	@Override
//	public void enterDistinct_predicate(Distinct_predicateContext ctx) {
//		// TODO Auto-generated method stub
//		
//	}
//
//	@Override
//	public void exitDistinct_predicate(Distinct_predicateContext ctx) {
//		// TODO Auto-generated method stub
//		
//	}
//
//	@Override
//	public void enterTfr_cardinality_clause(Tfr_cardinality_clauseContext ctx) {
//		// TODO Auto-generated method stub
//		
//	}
//
//	@Override
//	public void exitTfr_cardinality_clause(Tfr_cardinality_clauseContext ctx) {
//		// TODO Auto-generated method stub
//		
//	}
//
//	@Override
//	public void enterMerge_statement(Merge_statementContext ctx) {
//		// TODO Auto-generated method stub
//		
//	}
//
//	@Override
//	public void exitMerge_statement(Merge_statementContext ctx) {
//		// TODO Auto-generated method stub
//		
//	}
//
//	@Override
//	public void enterEp_lparen(Ep_lparenContext ctx) {
//		// TODO Auto-generated method stub
//		
//	}
//
//	@Override
//	public void exitEp_lparen(Ep_lparenContext ctx) {
//		// TODO Auto-generated method stub
//		
//	}
//
//	@Override
//	public void enterTlr_rparen(Tlr_rparenContext ctx) {
//		// TODO Auto-generated method stub
//		
//	}
//
//	@Override
//	public void exitTlr_rparen(Tlr_rparenContext ctx) {
//		// TODO Auto-generated method stub
//		
//	}
//
//	@Override
//	public void enterBetween_predicate(Between_predicateContext ctx) {
//		// TODO Auto-generated method stub
//		
//	}
//
//	@Override
//	public void exitBetween_predicate(Between_predicateContext ctx) {
//		// TODO Auto-generated method stub
//		
//	}
//
//	@Override
//	public void enterData_change_table_reference(
//			Data_change_table_referenceContext ctx) {
//		// TODO Auto-generated method stub
//		
//	}
//
//	@Override
//	public void exitData_change_table_reference(
//			Data_change_table_referenceContext ctx) {
//		// TODO Auto-generated method stub
//		
//	}
//
//	@Override
//	public void enterCte_fs_lparen(Cte_fs_lparenContext ctx) {
//		// TODO Auto-generated method stub
//		
//	}
//
//	@Override
//	public void exitCte_fs_lparen(Cte_fs_lparenContext ctx) {
//		// TODO Auto-generated method stub
//		
//	}
//
//	@Override
//	public void enterFje_fmt1(Fje_fmt1Context ctx) {
//		// TODO Auto-generated method stub
//		
//	}
//
//	@Override
//	public void exitFje_fmt1(Fje_fmt1Context ctx) {
//		// TODO Auto-generated method stub
//		
//	}
//
//	@Override
//	public void enterFje_fmt2(Fje_fmt2Context ctx) {
//		// TODO Auto-generated method stub
//		
//	}
//
//	@Override
//	public void exitFje_fmt2(Fje_fmt2Context ctx) {
//		// TODO Auto-generated method stub
//		
//	}
//
//	@Override
//	public void enterSequence_name(Sequence_nameContext ctx) {
//		// TODO Auto-generated method stub
//		
//	}
//
//	@Override
//	public void exitSequence_name(Sequence_nameContext ctx) {
//		// TODO Auto-generated method stub
//		
//	}
//
//	@Override
//	public void enterOb_comma(Ob_commaContext ctx) {
//		// TODO Auto-generated method stub
//		
//	}
//
//	@Override
//	public void exitOb_comma(Ob_commaContext ctx) {
//		// TODO Auto-generated method stub
//		
//	}
//
//	@Override
//	public void enterTfr_comma(Tfr_commaContext ctx) {
//		// TODO Auto-generated method stub
//		
//	}
//
//	@Override
//	public void exitTfr_comma(Tfr_commaContext ctx) {
//		// TODO Auto-generated method stub
//		
//	}
//
//	@Override
//	public void enterCte_cl_rparen(Cte_cl_rparenContext ctx) {
//		// TODO Auto-generated method stub
//		
//	}
//
//	@Override
//	public void exitCte_cl_rparen(Cte_cl_rparenContext ctx) {
//		// TODO Auto-generated method stub
//		
//	}
//
//	@Override
//	public void enterCc_comma(Cc_commaContext ctx) {
//		// TODO Auto-generated method stub
//		
//	}
//
//	@Override
//	public void exitCc_comma(Cc_commaContext ctx) {
//		// TODO Auto-generated method stub
//		
//	}
//
//	@Override
//	public void enterTable_designator(Table_designatorContext ctx) {
//		// TODO Auto-generated method stub
//		
//	}
//
//	@Override
//	public void exitTable_designator(Table_designatorContext ctx) {
//		// TODO Auto-generated method stub
//		
//	}
//
//	@Override
//	public void enterNum_constant(Num_constantContext ctx) {
//		// TODO Auto-generated method stub
//		
//	}
//
//	@Override
//	public void exitNum_constant(Num_constantContext ctx) {
//		// TODO Auto-generated method stub
//		
//	}
//
//	@Override
//	public void enterGraphic_String_constant(Graphic_String_constantContext ctx) {
//		// TODO Auto-generated method stub
//		
//	}
//
//	@Override
//	public void exitGraphic_String_constant(Graphic_String_constantContext ctx) {
//		// TODO Auto-generated method stub
//		
//	}
//
//	@Override
//	public void enterUpdate_statement(Update_statementContext ctx) {
//		// TODO Auto-generated method stub
//		
//	}
//
//	@Override
//	public void exitUpdate_statement(Update_statementContext ctx) {
//		// TODO Auto-generated method stub
//		
//	}
//
//	@Override
//	public void enterCorrelation_name(Correlation_nameContext ctx) {
//		// TODO Auto-generated method stub
//		
//	}
//
//	@Override
//	public void exitCorrelation_name(Correlation_nameContext ctx) {
//		// TODO Auto-generated method stub
//		
//	}
//
//	@Override
//	public void enterSc_qual(Sc_qualContext ctx) {
//		// TODO Auto-generated method stub
//		
//	}
//
//	@Override
//	public void exitSc_qual(Sc_qualContext ctx) {
//		// TODO Auto-generated method stub
//		
//	}
//
//	@Override
//	public void enterXpp_comma(Xpp_commaContext ctx) {
//		// TODO Auto-generated method stub
//		
//	}
//
//	@Override
//	public void exitXpp_comma(Xpp_commaContext ctx) {
//		// TODO Auto-generated method stub
//		
//	}
//
//	@Override
//	public void enterQp_fmt2_op(Qp_fmt2_opContext ctx) {
//		// TODO Auto-generated method stub
//		
//	}
//
//	@Override
//	public void exitQp_fmt2_op(Qp_fmt2_opContext ctx) {
//		// TODO Auto-generated method stub
//		
//	}
//
//	@Override
//	public void enterFullselect(FullselectContext ctx) {
//		// TODO Auto-generated method stub
//		
//	}
//
//	@Override
//	public void exitFullselect(FullselectContext ctx) {
//		// TODO Auto-generated method stub
//		
//	}
//
//	@Override
//	public void exitFrom_clause(From_clauseContext ctx) {
//		// TODO Auto-generated method stub
//		
//	}
//
//	@Override
//	public void enterExists_predicate(Exists_predicateContext ctx) {
//		// TODO Auto-generated method stub
//		
//	}
//
//	@Override
//	public void exitExists_predicate(Exists_predicateContext ctx) {
//		// TODO Auto-generated method stub
//		
//	}
//
//	@Override
//	public void enterRv_rparen(Rv_rparenContext ctx) {
//		// TODO Auto-generated method stub
//		
//	}
//
//	@Override
//	public void exitRv_rparen(Rv_rparenContext ctx) {
//		// TODO Auto-generated method stub
//		
//	}
//
//	@Override
//	public void exitSelect_statement(Select_statementContext ctx) {
//		// TODO Auto-generated method stub
//		
//	}
//
//	@Override
//	public void enterQp_lparen(Qp_lparenContext ctx) {
//		// TODO Auto-generated method stub
//		
//	}
//
//	@Override
//	public void exitQp_lparen(Qp_lparenContext ctx) {
//		// TODO Auto-generated method stub
//		
//	}
//
//	@Override
//	public void enterBasic_predicate(Basic_predicateContext ctx) {
//		// TODO Auto-generated method stub
//		
//	}
//
//	@Override
//	public void exitBasic_predicate(Basic_predicateContext ctx) {
//		// TODO Auto-generated method stub
//		
//	}
//
//	@Override
//	public void enterDctr_lparen(Dctr_lparenContext ctx) {
//		// TODO Auto-generated method stub
//		
//	}
//
//	@Override
//	public void exitDctr_lparen(Dctr_lparenContext ctx) {
//		// TODO Auto-generated method stub
//		
//	}
//
//	@Override
//	public void enterFje2_rparen(Fje2_rparenContext ctx) {
//		// TODO Auto-generated method stub
//		
//	}
//
//	@Override
//	public void exitFje2_rparen(Fje2_rparenContext ctx) {
//		// TODO Auto-generated method stub
//		
//	}
//
//	@Override
//	public void enterDatetime_constant(Datetime_constantContext ctx) {
//		// TODO Auto-generated method stub
//		
//	}
//
//	@Override
//	public void exitDatetime_constant(Datetime_constantContext ctx) {
//		// TODO Auto-generated method stub
//		
//	}
//
//	@Override
//	public void exitSkip_locked_data_clause(Skip_locked_data_clauseContext ctx) {
//		// TODO Auto-generated method stub
//		
//	}
//
//	@Override
//	public void enterPeriod_specification(Period_specificationContext ctx) {
//		// TODO Auto-generated method stub
//		
//	}
//
//	@Override
//	public void exitPeriod_specification(Period_specificationContext ctx) {
//		// TODO Auto-generated method stub
//		
//	}
//
//	@Override
//	public void enterLock_clause(Lock_clauseContext ctx) {
//		// TODO Auto-generated method stub
//		
//	}
//
//	@Override
//	public void exitLock_clause(Lock_clauseContext ctx) {
//		// TODO Auto-generated method stub
//		
//	}
//
//	@Override
//	public void enterIp_comma(Ip_commaContext ctx) {
//		// TODO Auto-generated method stub
//		
//	}
//
//	@Override
//	public void exitIp_comma(Ip_commaContext ctx) {
//		// TODO Auto-generated method stub
//		
//	}
//
//	@Override
//	public void enterFoj_condition(Foj_conditionContext ctx) {
//		// TODO Auto-generated method stub
//		
//	}
//
//	@Override
//	public void exitFoj_condition(Foj_conditionContext ctx) {
//		// TODO Auto-generated method stub
//		
//	}
//
//	@Override
//	public void enterQuantified_predicate(Quantified_predicateContext ctx) {
//		// TODO Auto-generated method stub
//		
//	}
//
//	@Override
//	public void exitQuantified_predicate(Quantified_predicateContext ctx) {
//		// TODO Auto-generated method stub
//		
//	}
//
//	@Override
//	public void enterLike_predicate(Like_predicateContext ctx) {
//		// TODO Auto-generated method stub
//		
//	}
//
//	@Override
//	public void exitLike_predicate(Like_predicateContext ctx) {
//		// TODO Auto-generated method stub
//		
//	}
//
//	@Override
//	public void enterSc_comma(Sc_commaContext ctx) {
//		// TODO Auto-generated method stub
//		
//	}
//
//	@Override
//	public void exitSc_comma(Sc_commaContext ctx) {
//		// TODO Auto-generated method stub
//		
//	}
//
//	@Override
//	public void exitHaving_clause(Having_clauseContext ctx) {
//		// TODO Auto-generated method stub
//		
//	}
//
//	@Override
//	public void enterFS_FULLSELECT(FS_FULLSELECTContext ctx) {
//		// TODO Auto-generated method stub
//		
//	}
//
//	@Override
//	public void exitFS_FULLSELECT(FS_FULLSELECTContext ctx) {
//		// TODO Auto-generated method stub
//		
//	}
//
//	@Override
//	public void enterFc_comma(Fc_commaContext ctx) {
//		// TODO Auto-generated method stub
//		
//	}
//
//	@Override
//	public void exitFc_comma(Fc_commaContext ctx) {
//		// TODO Auto-generated method stub
//		
//	}
//
//	@Override
//	public void enterSearch_condition(Search_conditionContext ctx) {
//		// TODO Auto-generated method stub
//		
//	}
//
//	@Override
//	public void exitSearch_condition(Search_conditionContext ctx) {
//		// TODO Auto-generated method stub
//		
//	}
//
//	@Override
//	public void enterIp_op(Ip_opContext ctx) {
//		// TODO Auto-generated method stub
//		
//	}
//
//	@Override
//	public void exitIp_op(Ip_opContext ctx) {
//		// TODO Auto-generated method stub
//		
//	}
//
//	@Override
//	public void enterLabeled_duration(Labeled_durationContext ctx) {
//		// TODO Auto-generated method stub
//		
//	}
//
//	@Override
//	public void exitLabeled_duration(Labeled_durationContext ctx) {
//		// TODO Auto-generated method stub
//		
//	}
//
//	@Override
//	public void enterTfr_rparen2(Tfr_rparen2Context ctx) {
//		// TODO Auto-generated method stub
//		
//	}
//
//	@Override
//	public void exitTfr_rparen2(Tfr_rparen2Context ctx) {
//		// TODO Auto-generated method stub
//		
//	}
//
//	@Override
//	public void enterTfr_rparen1(Tfr_rparen1Context ctx) {
//		// TODO Auto-generated method stub
//		
//	}
//
//	@Override
//	public void exitTfr_rparen1(Tfr_rparen1Context ctx) {
//		// TODO Auto-generated method stub
//		
//	}
//
//	@Override
//	public void enterSs_clause(Ss_clauseContext ctx) {
//		// TODO Auto-generated method stub
//		
//	}
//
//	@Override
//	public void exitSs_clause(Ss_clauseContext ctx) {
//		// TODO Auto-generated method stub
//		
//	}
//
//	@Override
//	public void enterDp_fmt2(Dp_fmt2Context ctx) {
//		// TODO Auto-generated method stub
//		
//	}
//
//	@Override
//	public void exitDp_fmt2(Dp_fmt2Context ctx) {
//		// TODO Auto-generated method stub
//		
//	}
//
//	@Override
//	public void enterOb_specification(Ob_specificationContext ctx) {
//		// TODO Auto-generated method stub
//		
//	}
//
//	@Override
//	public void exitOb_specification(Ob_specificationContext ctx) {
//		// TODO Auto-generated method stub
//		
//	}
//
//	@Override
//	public void enterXq_arg(Xq_argContext ctx) {
//		// TODO Auto-generated method stub
//		
//	}
//
//	@Override
//	public void exitXq_arg(Xq_argContext ctx) {
//		// TODO Auto-generated method stub
//		
//	}
//
//	@Override
//	public void enterCharacter_string_constant(
//			Character_string_constantContext ctx) {
//		// TODO Auto-generated method stub
//		
//	}
//
//	@Override
//	public void exitCharacter_string_constant(
//			Character_string_constantContext ctx) {
//		// TODO Auto-generated method stub
//		
//	}
//
//	@Override
//	public void enterInteger(IntegerContext ctx) {
//		// TODO Auto-generated method stub
//		
//	}
//
//	@Override
//	public void exitInteger(IntegerContext ctx) {
//		// TODO Auto-generated method stub
//		
//	}
//
//	@Override
//	public void enterSc_rparen(Sc_rparenContext ctx) {
//		// TODO Auto-generated method stub
//		
//	}
//
//	@Override
//	public void exitSc_rparen(Sc_rparenContext ctx) {
//		// TODO Auto-generated method stub
//		
//	}
//
//	@Override
//	public void enterSelectivity_clause(Selectivity_clauseContext ctx) {
//		// TODO Auto-generated method stub
//		
//	}
//
//	@Override
//	public void exitSelectivity_clause(Selectivity_clauseContext ctx) {
//		// TODO Auto-generated method stub
//		
//	}
//
//	@Override
//	public void enterDp_fmt1(Dp_fmt1Context ctx) {
//		// TODO Auto-generated method stub
//		
//	}
//
//	@Override
//	public void exitDp_fmt1(Dp_fmt1Context ctx) {
//		// TODO Auto-generated method stub
//		
//	}
//
//	@Override
//	public void enterOperator(OperatorContext ctx) {
//		// TODO Auto-generated method stub
//		
//	}
//
//	@Override
//	public void exitOperator(OperatorContext ctx) {
//		// TODO Auto-generated method stub
//		
//	}
//
//	@Override
//	public void enterXmlexists_predicate(Xmlexists_predicateContext ctx) {
//		// TODO Auto-generated method stub
//		
//	}
//
//	@Override
//	public void exitXmlexists_predicate(Xmlexists_predicateContext ctx) {
//		// TODO Auto-generated method stub
//		
//	}
//
//	@Override
//	public void enterVar_indicator(Var_indicatorContext ctx) {
//		// TODO Auto-generated method stub
//		
//	}
//
//	@Override
//	public void exitVar_indicator(Var_indicatorContext ctx) {
//		// TODO Auto-generated method stub
//		
//	}
//
//	@Override
//	public void enterOb_spec3(Ob_spec3Context ctx) {
//		// TODO Auto-generated method stub
//		
//	}
//
//	@Override
//	public void exitOb_spec3(Ob_spec3Context ctx) {
//		// TODO Auto-generated method stub
//		
//	}
//
//	@Override
//	public void enterIpicate(IpicateContext ctx) {
//		// TODO Auto-generated method stub
//		
//	}
//
//	@Override
//	public void exitIpicate(IpicateContext ctx) {
//		// TODO Auto-generated method stub
//		
//	}
//
//	@Override
//	public void enterCte_fs_rparen(Cte_fs_rparenContext ctx) {
//		// TODO Auto-generated method stub
//		
//	}
//
//	@Override
//	public void exitCte_fs_rparen(Cte_fs_rparenContext ctx) {
//		// TODO Auto-generated method stub
//		
//	}
//
//	@Override
//	public void enterNew_col_name(New_col_nameContext ctx) {
//		// TODO Auto-generated method stub
//		
//	}
//
//	@Override
//	public void exitNew_col_name(New_col_nameContext ctx) {
//		// TODO Auto-generated method stub
//		
//	}
//
//	@Override
//	public void enterOb_spec1(Ob_spec1Context ctx) {
//		// TODO Auto-generated method stub
//		
//	}
//
//	@Override
//	public void exitOb_spec1(Ob_spec1Context ctx) {
//		// TODO Auto-generated method stub
//		
//	}
//
//	@Override
//	public void enterLp_op(Lp_opContext ctx) {
//		// TODO Auto-generated method stub
//		
//	}
//
//	@Override
//	public void exitLp_op(Lp_opContext ctx) {
//		// TODO Auto-generated method stub
//		
//	}
//
//	@Override
//	public void enterOb_spec2(Ob_spec2Context ctx) {
//		// TODO Auto-generated method stub
//		
//	}
//
//	@Override
//	public void exitOb_spec2(Ob_spec2Context ctx) {
//		// TODO Auto-generated method stub
//		
//	}
//
//	@Override
//	public void enterJt_lparen(Jt_lparenContext ctx) {
//		// TODO Auto-generated method stub
//		
//	}
//
//	@Override
//	public void exitJt_lparen(Jt_lparenContext ctx) {
//		// TODO Auto-generated method stub
//		
//	}
//
//	@Override
//	public void enterDatatype(DatatypeContext ctx) {
//		// TODO Auto-generated method stub
//		
//	}
//
//	@Override
//	public void exitDatatype(DatatypeContext ctx) {
//		// TODO Auto-generated method stub
//		
//	}
//
//	@Override
//	public void enterVariable(VariableContext ctx) {
//		// TODO Auto-generated method stub
//		
//	}
//
//	@Override
//	public void exitVariable(VariableContext ctx) {
//		// TODO Auto-generated method stub
//		
//	}
//
//	@Override
//	public void enterSc_operator(Sc_operatorContext ctx) {
//		// TODO Auto-generated method stub
//		
//	}
//
//	@Override
//	public void exitSc_operator(Sc_operatorContext ctx) {
//		// TODO Auto-generated method stub
//		
//	}
//
//	@Override
//	public void enterExpression(ExpressionContext ctx) {
//		// TODO Auto-generated method stub
//		
//	}
//
//	@Override
//	public void exitExpression(ExpressionContext ctx) {
//		// TODO Auto-generated method stub
//		
//	}
//
//	@Override
//	public void enterBinary_string_constant(Binary_string_constantContext ctx) {
//		// TODO Auto-generated method stub
//		
//	}
//
//	@Override
//	public void exitBinary_string_constant(Binary_string_constantContext ctx) {
//		// TODO Auto-generated method stub
//		
//	}
//
//	@Override
//	public void enterGb_comma(Gb_commaContext ctx) {
//		// TODO Auto-generated method stub
//		
//	}
//
//	@Override
//	public void exitGb_comma(Gb_commaContext ctx) {
//		// TODO Auto-generated method stub
//		
//	}
//
//	@Override
//	public void enterFje2_lparen(Fje2_lparenContext ctx) {
//		// TODO Auto-generated method stub
//		
//	}
//
//	@Override
//	public void exitFje2_lparen(Fje2_lparenContext ctx) {
//		// TODO Auto-generated method stub
//		
//	}
//
//	@Override
//	public void enterQp_fmt3_qual(Qp_fmt3_qualContext ctx) {
//		// TODO Auto-generated method stub
//		
//	}
//
//	@Override
//	public void exitQp_fmt3_qual(Qp_fmt3_qualContext ctx) {
//		// TODO Auto-generated method stub
//		
//	}
//
//	@Override
//	public void enterQp_fmt2_qual(Qp_fmt2_qualContext ctx) {
//		// TODO Auto-generated method stub
//		
//	}
//
//	@Override
//	public void exitQp_fmt2_qual(Qp_fmt2_qualContext ctx) {
//		// TODO Auto-generated method stub
//		
//	}
//
//	@Override
//	public void enterInsert_statement(Insert_statementContext ctx) {
//		// TODO Auto-generated method stub
//		
//	}
//
//	@Override
//	public void exitInsert_statement(Insert_statementContext ctx) {
//		// TODO Auto-generated method stub
//		
//	}
//
//	@Override
//	public void enterColumn_name(Column_nameContext ctx) {
//		// TODO Auto-generated method stub
//		
//	}
//
//	@Override
//	public void exitColumn_name(Column_nameContext ctx) {
//		// TODO Auto-generated method stub
//		
//	}
//
//	@Override
//	public void enterIp_rparen(Ip_rparenContext ctx) {
//		// TODO Auto-generated method stub
//		
//	}
//
//	@Override
//	public void exitIp_rparen(Ip_rparenContext ctx) {
//		// TODO Auto-generated method stub
//		
//	}
//
//	@Override
//	public void enterXp_rparen(Xp_rparenContext ctx) {
//		// TODO Auto-generated method stub
//		
//	}
//
//	@Override
//	public void exitXp_rparen(Xp_rparenContext ctx) {
//		// TODO Auto-generated method stub
//		
//	}
//
//	@Override
//	public void enterFS_SUBSELECT(FS_SUBSELECTContext ctx) {
//		// TODO Auto-generated method stub
//		
//	}
//
//	@Override
//	public void exitFS_SUBSELECT(FS_SUBSELECTContext ctx) {
//		// TODO Auto-generated method stub
//		
//	}
//
//	@Override
//	public void enterSc_predfmt2(Sc_predfmt2Context ctx) {
//		// TODO Auto-generated method stub
//		
//	}
//
//	@Override
//	public void exitSc_predfmt2(Sc_predfmt2Context ctx) {
//		// TODO Auto-generated method stub
//		
//	}
//
//	@Override
//	public void enterSc_predfmt1(Sc_predfmt1Context ctx) {
//		// TODO Auto-generated method stub
//		
//	}
//
//	@Override
//	public void exitSc_predfmt1(Sc_predfmt1Context ctx) {
//		// TODO Auto-generated method stub
//		
//	}
//
//	@Override
//	public void enterNte_rparen(Nte_rparenContext ctx) {
//		// TODO Auto-generated method stub
//		
//	}
//
//	@Override
//	public void exitNte_rparen(Nte_rparenContext ctx) {
//		// TODO Auto-generated method stub
//		
//	}
//
//	@Override
//	public void enterFunction_name(Function_nameContext ctx) {
//		// TODO Auto-generated method stub
//		
//	}
//
//	@Override
//	public void exitFunction_name(Function_nameContext ctx) {
//		// TODO Auto-generated method stub
//		
//	}
//
//	@Override
//	public void enterSc_not(Sc_notContext ctx) {
//		// TODO Auto-generated method stub
//		
//	}
//
//	@Override
//	public void exitSc_not(Sc_notContext ctx) {
//		// TODO Auto-generated method stub
//		
//	}
//
//	@Override
//	public void enterFje2_comma(Fje2_commaContext ctx) {
//		// TODO Auto-generated method stub
//		
//	}
//
//	@Override
//	public void exitFje2_comma(Fje2_commaContext ctx) {
//		// TODO Auto-generated method stub
//		
//	}
//
//	@Override
//	public void enterRow_value_expression(Row_value_expressionContext ctx) {
//		// TODO Auto-generated method stub
//		
//	}
//
//	@Override
//	public void exitRow_value_expression(Row_value_expressionContext ctx) {
//		// TODO Auto-generated method stub
//		
//	}
//
//	@Override
//	public void enterCc_lparen(Cc_lparenContext ctx) {
//		// TODO Auto-generated method stub
//		
//	}
//
//	@Override
//	public void exitCc_lparen(Cc_lparenContext ctx) {
//		// TODO Auto-generated method stub
//		
//	}
//
//	@Override
//	public void enterDelimited_identifier(Delimited_identifierContext ctx) {
//		// TODO Auto-generated method stub
//		
//	}
//
//	@Override
//	public void exitDelimited_identifier(Delimited_identifierContext ctx) {
//		// TODO Auto-generated method stub
//		
//	}
//
//	@Override
//	public void enterIp_fmt1(Ip_fmt1Context ctx) {
//		// TODO Auto-generated method stub
//		
//	}
//
//	@Override
//	public void exitIp_fmt1(Ip_fmt1Context ctx) {
//		// TODO Auto-generated method stub
//		
//	}
//
//	@Override
//	public void enterNte_lparen(Nte_lparenContext ctx) {
//		// TODO Auto-generated method stub
//		
//	}
//
//	@Override
//	public void exitNte_lparen(Nte_lparenContext ctx) {
//		// TODO Auto-generated method stub
//		
//	}
//
//	@Override
//	public void enterIp_fmt2(Ip_fmt2Context ctx) {
//		// TODO Auto-generated method stub
//		
//	}
//
//	@Override
//	public void exitIp_fmt2(Ip_fmt2Context ctx) {
//		// TODO Auto-generated method stub
//		
//	}
//
//	@Override
//	public void enterIp_rhs(Ip_rhsContext ctx) {
//		// TODO Auto-generated method stub
//		
//	}
//
//	@Override
//	public void exitIp_rhs(Ip_rhsContext ctx) {
//		// TODO Auto-generated method stub
//		
//	}
//
//	@Override
//	public void enterDctr_rparen(Dctr_rparenContext ctx) {
//		// TODO Auto-generated method stub
//		
//	}
//
//	@Override
//	public void exitDctr_rparen(Dctr_rparenContext ctx) {
//		// TODO Auto-generated method stub
//		
//	}
//
//	@Override
//	public void enterSc_lparen(Sc_lparenContext ctx) {
//		// TODO Auto-generated method stub
//		
//	}
//
//	@Override
//	public void exitSc_lparen(Sc_lparenContext ctx) {
//		// TODO Auto-generated method stub
//		
//	}
//
//	@Override
//	public void enterFunction_invocation(Function_invocationContext ctx) {
//		// TODO Auto-generated method stub
//		
//	}
//
//	@Override
//	public void exitFunction_invocation(Function_invocationContext ctx) {
//		// TODO Auto-generated method stub
//		
//	}
//
//	@Override
//	public void enterQp_rparen(Qp_rparenContext ctx) {
//		// TODO Auto-generated method stub
//		
//	}
//
//	@Override
//	public void exitQp_rparen(Qp_rparenContext ctx) {
//		// TODO Auto-generated method stub
//		
//	}
//
//	@Override
//	public void enterSign(SignContext ctx) {
//		// TODO Auto-generated method stub
//		
//	}
//
//	@Override
//	public void exitSign(SignContext ctx) {
//		// TODO Auto-generated method stub
//		
//	}
//
//	@Override
//	public void enterTFR_EXPR(TFR_EXPRContext ctx) {
//		// TODO Auto-generated method stub
//		
//	}
//
//	@Override
//	public void exitTFR_EXPR(TFR_EXPRContext ctx) {
//		// TODO Auto-generated method stub
//		
//	}
//
//	@Override
//	public void enterUnary_operator(Unary_operatorContext ctx) {
//		// TODO Auto-generated method stub
//		
//	}
//
//	@Override
//	public void exitUnary_operator(Unary_operatorContext ctx) {
//		// TODO Auto-generated method stub
//		
//	}
//
//	@Override
//	public void enterCorrelation_clause(Correlation_clauseContext ctx) {
//		// TODO Auto-generated method stub
//		
//	}
//
//	@Override
//	public void exitCorrelation_clause(Correlation_clauseContext ctx) {
//		// TODO Auto-generated method stub
//		
//	}
//
//	@Override
//	public void enterCc_rparen(Cc_rparenContext ctx) {
//		// TODO Auto-generated method stub
//		
//	}
//
//	@Override
//	public void exitCc_rparen(Cc_rparenContext ctx) {
//		// TODO Auto-generated method stub
//		
//	}
//
//	@Override
//	public void enterIp_lparen(Ip_lparenContext ctx) {
//		// TODO Auto-generated method stub
//		
//	}
//
//	@Override
//	public void exitIp_lparen(Ip_lparenContext ctx) {
//		// TODO Auto-generated method stub
//		
//	}
//
//	@Override
//	public void enterCte_cl_lparen(Cte_cl_lparenContext ctx) {
//		// TODO Auto-generated method stub
//		
//	}
//
//	@Override
//	public void exitCte_cl_lparen(Cte_cl_lparenContext ctx) {
//		// TODO Auto-generated method stub
//		
//	}
//
//	@Override
//	public void enterIdentifier(IdentifierContext ctx) {
//		// TODO Auto-generated method stub
//		
//	}
//
//	@Override
//	public void exitIdentifier(IdentifierContext ctx) {
//		// TODO Auto-generated method stub
//		
//	}
//
//	@Override
//	public void enterGb_expression(Gb_expressionContext ctx) {
//		// TODO Auto-generated method stub
//		
//	}
//
//	@Override
//	public void exitGb_expression(Gb_expressionContext ctx) {
//		// TODO Auto-generated method stub
//		
//	}
//
//	@Override
//	public void enterNested_table_expression(Nested_table_expressionContext ctx) {
//		// TODO Auto-generated method stub
//		
//	}
//
//	@Override
//	public void exitNested_table_expression(Nested_table_expressionContext ctx) {
//		// TODO Auto-generated method stub
//		
//	}
//

//
//	@Override
//	public void enterXquery(XqueryContext ctx) {
//		// TODO Auto-generated method stub
//		
//	}
//
//	@Override
//	public void exitXquery(XqueryContext ctx) {
//		// TODO Auto-generated method stub
//		
//	}
//
//	@Override
//	public void exitOptimize_clause(Optimize_clauseContext ctx) {
//		// TODO Auto-generated method stub
//		
//	}
//
//	@Override
//	public void enterDp_op(Dp_opContext ctx) {
//		// TODO Auto-generated method stub
//		
//	}
//
//	@Override
//	public void exitDp_op(Dp_opContext ctx) {
//		// TODO Auto-generated method stub
//		
//	}
//
//	@Override
//	public void enterXml_table_expression(Xml_table_expressionContext ctx) {
//		// TODO Auto-generated method stub
//		
//	}
//
//	@Override
//	public void exitXml_table_expression(Xml_table_expressionContext ctx) {
//		// TODO Auto-generated method stub
//		
//	}
//
//	@Override
//	public void exitIsolation_clause(Isolation_clauseContext ctx) {
//		// TODO Auto-generated method stub
//		
//	}
//
//	@Override
//	public void enterQp_fmt3_op(Qp_fmt3_opContext ctx) {
//		// TODO Auto-generated method stub
//		
//	}
//
//	@Override
//	public void exitQp_fmt3_op(Qp_fmt3_opContext ctx) {
//		// TODO Auto-generated method stub
//		
//	}
//
//	@Override
//	public void enterDctr_fmt4(Dctr_fmt4Context ctx) {
//		// TODO Auto-generated method stub
//		
//	}
//
//	@Override
//	public void exitDctr_fmt4(Dctr_fmt4Context ctx) {
//		// TODO Auto-generated method stub
//		
//	}
//
//	@Override
//	public void enterDctr_fmt3(Dctr_fmt3Context ctx) {
//		// TODO Auto-generated method stub
//		
//	}
//
//	@Override
//	public void exitDctr_fmt3(Dctr_fmt3Context ctx) {
//		// TODO Auto-generated method stub
//		
//	}
//
//	@Override
//	public void enterTable_reference(Table_referenceContext ctx) {
//		// TODO Auto-generated method stub
//		
//	}
//
//	@Override
//	public void exitTable_reference(Table_referenceContext ctx) {
//		// TODO Auto-generated method stub
//		
//	}
//
//	@Override
//	public void enterXmlcast_specification(Xmlcast_specificationContext ctx) {
//		// TODO Auto-generated method stub
//		
//	}
//
//	@Override
//	public void exitXmlcast_specification(Xmlcast_specificationContext ctx) {
//		// TODO Auto-generated method stub
//		
//	}
//
//	@Override
//	public void enterDctr_fmt2(Dctr_fmt2Context ctx) {
//		// TODO Auto-generated method stub
//		
//	}
//
//	@Override
//	public void exitDctr_fmt2(Dctr_fmt2Context ctx) {
//		// TODO Auto-generated method stub
//		
//	}
//
//	@Override
//	public void enterDctr_fmt1(Dctr_fmt1Context ctx) {
//		// TODO Auto-generated method stub
//		
//	}
//
//	@Override
//	public void exitDctr_fmt1(Dctr_fmt1Context ctx) {
//		// TODO Auto-generated method stub
//		
//	}
//
//	@Override
//	public void enterNull_predicate(Null_predicateContext ctx) {
//		// TODO Auto-generated method stub
//		
//	}
//
//	@Override
//	public void exitNull_predicate(Null_predicateContext ctx) {
//		// TODO Auto-generated method stub
//		
//	}
//
//	@Override
//	public void enterId(IdContext ctx) {
//		// TODO Auto-generated method stub
//		
//	}
//
//	@Override
//	public void exitId(IdContext ctx) {
//		// TODO Auto-generated method stub
//		
//	}
//
//	@Override
//	public void enterDelete_statement(Delete_statementContext ctx) {
//		// TODO Auto-generated method stub
//		
//	}
//
//	@Override
//	public void exitDelete_statement(Delete_statementContext ctx) {
//		// TODO Auto-generated method stub
//		
//	}
//
//	@Override
//	public void enterHex_string_constant(Hex_string_constantContext ctx) {
//		// TODO Auto-generated method stub
//		
//	}
//
//	@Override
//	public void exitHex_string_constant(Hex_string_constantContext ctx) {
//		// TODO Auto-generated method stub
//		
//	}
//
//	@Override
//	public void enterSequence_reference(Sequence_referenceContext ctx) {
//		// TODO Auto-generated method stub
//		
//	}
//
//	@Override
//	public void exitSequence_reference(Sequence_referenceContext ctx) {
//		// TODO Auto-generated method stub
//		
//	}
//
//	@Override
//	public void enterRv_lparen(Rv_lparenContext ctx) {
//		// TODO Auto-generated method stub
//		
//	}
//
//	@Override
//	public void exitRv_lparen(Rv_lparenContext ctx) {
//		// TODO Auto-generated method stub
//		
//	}
//
//	@Override
//	public void enterOrdinary_identifier(Ordinary_identifierContext ctx) {
//		// TODO Auto-generated method stub
//		
//	}
//
//	@Override
//	public void exitOrdinary_identifier(Ordinary_identifierContext ctx) {
//		// TODO Auto-generated method stub
//		
//	}
//
//	@Override
//	public void enterSpecial_register(Special_registerContext ctx) {
//		// TODO Auto-generated method stub
//		
//	}
//
//	@Override
//	public void exitSpecial_register(Special_registerContext ctx) {
//		// TODO Auto-generated method stub
//		
//	}
//
//	@Override
//	public void enterQueryno_clause(Queryno_clauseContext ctx) {
//		// TODO Auto-generated method stub
//		
//	}
//
//	@Override
//	public void exitQueryno_clause(Queryno_clauseContext ctx) {
//		// TODO Auto-generated method stub
//		
//	}
//
//	@Override
//	public void enterDctr_clause(Dctr_clauseContext ctx) {
//		// TODO Auto-generated method stub
//		
//	}
//
//	@Override
//	public void exitDctr_clause(Dctr_clauseContext ctx) {
//		// TODO Auto-generated method stub
//		
//	}
//
//	@Override
//	public void enterTlr_lparen(Tlr_lparenContext ctx) {
//		// TODO Auto-generated method stub
//		
//	}
//
//	@Override
//	public void exitTlr_lparen(Tlr_lparenContext ctx) {
//		// TODO Auto-generated method stub
//		
//	}
//
//	@Override
//	public void enterTable_name(Table_nameContext ctx) {
//		// TODO Auto-generated method stub
//		
//	}
//
//	@Override
//	public void exitTable_name(Table_nameContext ctx) {
//		// TODO Auto-generated method stub
//		
//	}
//
//	@Override
//	public void enterCte_tb_identifier(Cte_tb_identifierContext ctx) {
//		// TODO Auto-generated method stub
//		
//	}
//
//	@Override
//	public void exitCte_tb_identifier(Cte_tb_identifierContext ctx) {
//		// TODO Auto-generated method stub
//		
//	}
//
//	@Override
//	public void enterSingle_table(Single_tableContext ctx) {
//		// TODO Auto-generated method stub
//		
//	}
//
//	@Override
//	public void exitSingle_table(Single_tableContext ctx) {
//		// TODO Auto-generated method stub
//		
//	}
//
//	@Override
//	public void enterTfr_lparen1(Tfr_lparen1Context ctx) {
//		// TODO Auto-generated method stub
//		
//	}
//
//	@Override
//	public void exitTfr_lparen1(Tfr_lparen1Context ctx) {
//		// TODO Auto-generated method stub
//		
//	}
//
//	@Override
//	public void enterCte_cl_comma(Cte_cl_commaContext ctx) {
//		// TODO Auto-generated method stub
//		
//	}
//
//	@Override
//	public void exitCte_cl_comma(Cte_cl_commaContext ctx) {
//		// TODO Auto-generated method stub
//		
//	}
//
//	@Override
//	public void enterTfr_lparen2(Tfr_lparen2Context ctx) {
//		// TODO Auto-generated method stub
//		
//	}
//
//	@Override
//	public void exitTfr_lparen2(Tfr_lparen2Context ctx) {
//		// TODO Auto-generated method stub
//		
//	}
//
//	@Override
//	public void enterFull_join_expression(Full_join_expressionContext ctx) {
//		// TODO Auto-generated method stub
//		
//	}
//
//	@Override
//	public void exitFull_join_expression(Full_join_expressionContext ctx) {
//		// TODO Auto-generated method stub
//		
//	}
//
//	@Override
//	public void enterTable_locator_reference(Table_locator_referenceContext ctx) {
//		// TODO Auto-generated method stub
//		
//	}
//
//	@Override
//	public void exitTable_locator_reference(Table_locator_referenceContext ctx) {
//		// TODO Auto-generated method stub
//		
//	}
//
//	@Override
//	public void enterTransition_table_name(Transition_table_nameContext ctx) {
//		// TODO Auto-generated method stub
//		
//	}
//
//	@Override
//	public void exitTransition_table_name(Transition_table_nameContext ctx) {
//		// TODO Auto-generated method stub
//		
//	}
//
//	@Override
//	public void enterConstant(ConstantContext ctx) {
//		// TODO Auto-generated method stub
//		
//	}
//
//	@Override
//	public void exitConstant(ConstantContext ctx) {
//		// TODO Auto-generated method stub
//		
//	}
//
//	@Override
//	public void exitWhere_clause(Where_clauseContext ctx) {
//		// TODO Auto-generated method stub
//		
//	}
//
//	@Override
//	public void enterKeyword(KeywordContext ctx) {
//		// TODO Auto-generated method stub
//		
//	}
//
//	@Override
//	public void exitKeyword(KeywordContext ctx) {
//		// TODO Auto-generated method stub
//		
//	}
//
//	@Override
//	public void enterTFR_TTN(TFR_TTNContext ctx) {
//		// TODO Auto-generated method stub
//		
//	}
//
//	@Override
//	public void exitTFR_TTN(TFR_TTNContext ctx) {
//		// TODO Auto-generated method stub
//		
//	}
//
//	@Override
//	public void enterParameter_marker(Parameter_markerContext ctx) {
//		// TODO Auto-generated method stub
//		
//	}
//
//	@Override
//	public void exitParameter_marker(Parameter_markerContext ctx) {
//		// TODO Auto-generated method stub
//		
//	}
//
//	@Override
//	public void enterSc_item(Sc_itemContext ctx) {
//		// TODO Auto-generated method stub
//		
//	}
//
//	@Override
//	public void exitSc_item(Sc_itemContext ctx) {
//		// TODO Auto-generated method stub
//		
//	}
//
//	@Override
//	public void enterKey_spec(Key_specContext ctx) {
//		// TODO Auto-generated method stub
//		
//	}
//
//	@Override
//	public void exitKey_spec(Key_specContext ctx) {
//		// TODO Auto-generated method stub
//		
//	}
//
//	@Override
//	public void enterQp_fmt1_qual(Qp_fmt1_qualContext ctx) {
//		// TODO Auto-generated method stub
//		
//	}
//
//	@Override
//	public void exitQp_fmt1_qual(Qp_fmt1_qualContext ctx) {
//		// TODO Auto-generated method stub
//		
//	}
//
//	@Override
//	public void enterFloating_point_constant(Floating_point_constantContext ctx) {
//		// TODO Auto-generated method stub
//		
//	}
//
//	@Override
//	public void exitFloating_point_constant(Floating_point_constantContext ctx) {
//		// TODO Auto-generated method stub
//		
//	}
//
//	@Override
//	public void enterRve_operator(Rve_operatorContext ctx) {
//		// TODO Auto-generated method stub
//		
//	}
//
//	@Override
//	public void exitRve_operator(Rve_operatorContext ctx) {
//		// TODO Auto-generated method stub
//		
//	}
//
//	@Override
//	public void enterRve_comma(Rve_commaContext ctx) {
//		// TODO Auto-generated method stub
//		
//	}
//
//	@Override
//	public void exitRve_comma(Rve_commaContext ctx) {
//		// TODO Auto-generated method stub
//		
//	}
//
//	@Override
//	public void enterDuration(DurationContext ctx) {
//		// TODO Auto-generated method stub
//		
//	}
//
//	@Override
//	public void exitDuration(DurationContext ctx) {
//		// TODO Auto-generated method stub
//		
//	}
//
//	@Override
//	public void enterOrder_by_clause(Order_by_clauseContext ctx) {
//		// TODO Auto-generated method stub
//		
//	}
//
//	@Override
//	public void exitOrder_by_clause(Order_by_clauseContext ctx) {
//		// TODO Auto-generated method stub
//		
//	}
//
//	@Override
//	public void exitUpdate_clause(Update_clauseContext ctx) {
//		// TODO Auto-generated method stub
//		
//	}
//
//	@Override
//	public void enterInteger_constant(Integer_constantContext ctx) {
//		// TODO Auto-generated method stub
//		
//	}
//
//	@Override
//	public void exitInteger_constant(Integer_constantContext ctx) {
//		// TODO Auto-generated method stub
//		
//	}
//
//	@Override
//	public void enterString_constant(String_constantContext ctx) {
//		// TODO Auto-generated method stub
//		
//	}
//
//	@Override
//	public void exitString_constant(String_constantContext ctx) {
//		// TODO Auto-generated method stub
//		
//	}
//
//	@Override
//	public void exitGroup_by_clause(Group_by_clauseContext ctx) {
//		// TODO Auto-generated method stub
//		
//	}
//
//	@Override
//	public void enterSort_key(Sort_keyContext ctx) {
//		// TODO Auto-generated method stub
//		
//	}
//
//	@Override
//	public void exitSort_key(Sort_keyContext ctx) {
//		// TODO Auto-generated method stub
//		
//	}
//
//	@Override
//	public void enterPred_operator(Pred_operatorContext ctx) {
//		// TODO Auto-generated method stub
//		
//	}
//
//	@Override
//	public void exitPred_operator(Pred_operatorContext ctx) {
//		// TODO Auto-generated method stub
//		
//	}
//
//	@Override
//	public void enterDecimal_constant(Decimal_constantContext ctx) {
//		// TODO Auto-generated method stub
//		
//	}
//
//	@Override
//	public void exitDecimal_constant(Decimal_constantContext ctx) {
//		// TODO Auto-generated method stub
//		
//	}
//
//	@Override
//	public void exitRead_only_clause(Read_only_clauseContext ctx) {
//		// TODO Auto-generated method stub
//		
//	}
//
//	@Override
//	public void enterEp_rparen(Ep_rparenContext ctx) {
//		// TODO Auto-generated method stub
//		
//	}
//
//	@Override
//	public void exitEp_rparen(Ep_rparenContext ctx) {
//		// TODO Auto-generated method stub
//		
//	}
//
//	@Override
//	public void enterJt_rparen(Jt_rparenContext ctx) {
//		// TODO Auto-generated method stub
//		
//	}
//
//	@Override
//	public void exitJt_rparen(Jt_rparenContext ctx) {
//		// TODO Auto-generated method stub
//		
//	}
//
//	@Override
//	public void enterSubselect(SubselectContext ctx) {
//		// TODO Auto-generated method stub
//		
//	}
//
//	@Override
//	public void exitSubselect(SubselectContext ctx) {
//		// TODO Auto-generated method stub
//		
//	}
//
//	@Override
//	public void enterCast_function(Cast_functionContext ctx) {
//		// TODO Auto-generated method stub
//		
//	}
//
//	@Override
//	public void exitCast_function(Cast_functionContext ctx) {
//		// TODO Auto-generated method stub
//		
//	}
//
//	@Override
//	public void enterJoin_condition(Join_conditionContext ctx) {
//		// TODO Auto-generated method stub
//		
//	}
//
//	@Override
//	public void exitJoin_condition(Join_conditionContext ctx) {
//		// TODO Auto-generated method stub
//		
//	}
//
//	@Override
//	public void exitFetch_first_clause(Fetch_first_clauseContext ctx) {
//		// TODO Auto-generated method stub
//		
//	}

	//------------------------------------------------------------------

	
			
}
