package com.jsh.sql;

import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeProperty;

import com.jsh.sql.JSHSQL.Basic_predicateContext;
import com.jsh.sql.JSHSQL.From_clauseContext;
import com.jsh.sql.JSHSQL.PredicateContext;
import com.jsh.sql.JSHSQL.Sc_predfmt1Context;
import com.jsh.sql.JSHSQL.Sc_predfmt2Context;
import com.jsh.sql.JSHSQL.Search_conditionContext;
import com.jsh.sql.JSHSQL.Single_tableContext;
import com.jsh.sql.JSHSQL.Sv_basic_predicateContext;
import com.jsh.sql.JSHSQL.Table_referenceContext;

public class SQLAnnotateListener extends JSHSQLBaseListener {
    /** maps nodes to integers with Map<ParseTree,Integer> */
    ParseTreeProperty<Integer> values = new ParseTreeProperty<Integer>();
    
    public void setValue(ParseTree node, int value) { values.put(node, value); }
    public int getValue(ParseTree node) { return values.get(node); }
 
    public ParseTreeProperty<Integer> getAnnotations() {return values;}

    
    //---------------------------------------------------------------
    // line up Predicate operators
	@Override
	public void exitSearch_condition(Search_conditionContext ctx) {
		// loop through preds
		//ParserRuleContext prc = ctx.sc_predfmt1();
		int len = getValue(ctx.sc_predfmt1());
		
		int max = ctx.sc_predfmt2().size();
		for (int i = 0; i<max ; i++ ){
			//prc = ctx.sc_predfmt2(i);
			//System.out.println(prc.getText());
			len = (len>getValue(ctx.sc_predfmt2(i))?len:getValue(ctx.sc_predfmt2(i)));
		}
		
		setValue(ctx, len);
	}

	@Override
	public void exitSc_predfmt1(Sc_predfmt1Context ctx) {
		setValue(ctx, getValue(ctx.predicate()));
	}

	@Override
	public void exitSc_predfmt2(Sc_predfmt2Context ctx) {
		setValue(ctx, getValue(ctx.predicate()));
	}

    
	@Override
	public void exitPredicate(PredicateContext ctx) {
		int len = 0;
		if (ctx.basic_predicate() != null){
			len = getValue(ctx.basic_predicate());
		} 
		setValue(ctx, len);
	}
    
	@Override
	public void exitBasic_predicate(Basic_predicateContext ctx) {
		
		int len = 0;
		if (ctx.sv_basic_predicate() != null){
			len = getValue(ctx.sv_basic_predicate());
		} 
		setValue(ctx, len);
	}

	
	@Override
	public void exitSv_basic_predicate(Sv_basic_predicateContext ctx) {
		int len = ctx.expression(0).getRuleContext().getText().length();
		setValue(ctx,len);
	}

	//-------------------------------
	//  FROM CLAUSE Correation
	
	@Override
	public void exitFrom_clause(From_clauseContext ctx) {
		// loop through table references 
		int len = 0;
		ParserRuleContext prc ;
		int max = ctx.table_reference().size();
		for (int i = 0; i<max ; i++ ){
			prc = ctx.table_reference(i);
			len = (len>getValue(prc)?len:getValue(prc));
		}
		
		setValue(ctx, len);
		
	}
    
	@Override
	public void exitTable_reference(Table_referenceContext ctx) {
		int len = 0;
		if (ctx.single_table() != null){
			len = getValue(ctx.single_table());
		} 
		setValue(ctx,len);
	}

	@Override
	public void exitSingle_table(Single_tableContext ctx) {
		int len = ctx.table_name().getRuleContext().getText().length();
		setValue(ctx,len);
	}

}
