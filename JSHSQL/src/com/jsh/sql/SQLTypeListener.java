package com.jsh.sql;

import org.antlr.v4.runtime.misc.NotNull;

public class SQLTypeListener
//   implements JSHSQLListener {
	 extends JSHSQLBaseListener {
	
	String stmtType;
	
	public String getStmtType() {
		return stmtType;
	}

	public SQLTypeListener() {
		stmtType = null;
	}

	@Override public void enterAllocate_statement(@NotNull JSHSQL.Allocate_statementContext ctx) {
		stmtType = "ALLOCATE";
	}
	@Override public void enterAlter_statement(@NotNull JSHSQL.Alter_statementContext ctx) {
		stmtType = "ALTER";
	}
	@Override public void enterAssociate_statement(@NotNull JSHSQL.Associate_statementContext ctx) {
		stmtType = "ASSOCIATE LOCATORS";
	}
	@Override public void enterBegin_statement(@NotNull JSHSQL.Begin_statementContext ctx) {
		stmtType = "BEGIN DECLARE SECTION";
	}
	@Override public void enterCall_statement(@NotNull JSHSQL.Call_statementContext ctx) {
		stmtType = "CALL";
	}
	@Override public void enterClose_statement(@NotNull JSHSQL.Close_statementContext ctx) {
		stmtType = "CLOSE CURSOR";
	}
	@Override public void enterComment_statement(@NotNull JSHSQL.Comment_statementContext ctx) {
		stmtType = "COMMENT";
	}
	@Override public void enterCommit_statement(@NotNull JSHSQL.Commit_statementContext ctx) {
		stmtType = "COMMIT";
	}
	@Override public void enterConnect_statement(@NotNull JSHSQL.Connect_statementContext ctx) { 
		stmtType = "CONNECT";
	}
	@Override public void enterCreate_statement(@NotNull JSHSQL.Create_statementContext ctx) {
		stmtType = "CREATE";
	}
	@Override public void enterDeclare_cursor_statement(@NotNull JSHSQL.Declare_cursor_statementContext ctx) { 
		stmtType = "DECLARE CURSOR";
	}
	@Override public void enterDeclare_gtt_statement(@NotNull JSHSQL.Declare_gtt_statementContext ctx) { 
		stmtType = "DECLARE GTT";
	}
	@Override public void enterDeclare_stmt_statement(@NotNull JSHSQL.Declare_stmt_statementContext ctx) {
		stmtType = "DECLARE STATEMENT";
	}
	@Override public void enterDeclare_table_statement(@NotNull JSHSQL.Declare_table_statementContext ctx) { 
		stmtType = "DECLARE TABLE";
	}
	@Override public void enterDeclare_variable_statement(@NotNull JSHSQL.Declare_variable_statementContext ctx) {
		stmtType = "DECLARE VARIABLE";
	}
	@Override public void enterDescribe_statement(@NotNull JSHSQL.Describe_statementContext ctx) {
		stmtType = "DESCRIBE";
	}
	@Override public void enterDrop_statement(@NotNull JSHSQL.Drop_statementContext ctx) { 
		stmtType = "DROP";
	}
	@Override public void enterEnd_statement(@NotNull JSHSQL.End_statementContext ctx) {
		stmtType = "END DECLARE SECTION";
	}
	@Override public void enterExchange_statement(@NotNull JSHSQL.Exchange_statementContext ctx) { 
		stmtType = "EXCHANGE";
	}
	@Override public void enterExecute_statement(@NotNull JSHSQL.Execute_statementContext ctx) {
		stmtType = "EXECUTE";
	}
	@Override public void enterExplain_statement(@NotNull JSHSQL.Explain_statementContext ctx) {
		stmtType = "EXPLAIN";
	}
	@Override public void enterFetch_statement(@NotNull JSHSQL.Fetch_statementContext ctx) {
		stmtType = "FETCH";
	}
	@Override public void enterFree_statement(@NotNull JSHSQL.Free_statementContext ctx) { 
		stmtType = "FREE LOCATOR";
	}
	@Override public void enterGet_statement(@NotNull JSHSQL.Get_statementContext ctx) {
		stmtType = "GET DIAGNOSTICS";
	}
	@Override public void enterGrant_statement(@NotNull JSHSQL.Grant_statementContext ctx) { 
		stmtType = "GRANT";
	}
	@Override public void enterHold_statement(@NotNull JSHSQL.Hold_statementContext ctx) {
		stmtType = "HOLD LOCATOR";
	}
	@Override public void enterInclude_statement(@NotNull JSHSQL.Include_statementContext ctx) { 
		stmtType = "INCLUDE";
	}
	@Override public void enterInsert_statement(@NotNull JSHSQL.Insert_statementContext ctx) { 
		stmtType = "INSERT";
	}
	@Override public void enterInvalid_statement(@NotNull JSHSQL.Invalid_statementContext ctx) {
		stmtType = "INVALID STATEMENT";
	}
	@Override public void enterLabel_statement(@NotNull JSHSQL.Label_statementContext ctx) { 
		stmtType = "LABEL";
	}
	@Override public void enterLock_statement(@NotNull JSHSQL.Lock_statementContext ctx) { 
		stmtType = "LOCK";
	}
	@Override public void enterMerge_statement(@NotNull JSHSQL.Merge_statementContext ctx) { 
		stmtType = "MERGE";
	}
	@Override public void enterOpen_statement(@NotNull JSHSQL.Open_statementContext ctx) { 
		stmtType = "OPEN CURSOR";
	}
	@Override public void enterPositioned_delete_statement(@NotNull JSHSQL.Positioned_delete_statementContext ctx) { 
		stmtType = "DELETE CURSOR";
	}
	@Override public void enterPositioned_update_statement(@NotNull JSHSQL.Positioned_update_statementContext ctx) { 
		stmtType = "UPDATE CURSOR";
	}
	@Override public void enterPrepare_statement(@NotNull JSHSQL.Prepare_statementContext ctx) { 
		stmtType = "PREPARE";
	}
	@Override public void enterRefresh_statement(@NotNull JSHSQL.Refresh_statementContext ctx) { 
		stmtType = "REFRESH TABLE";
	}
	@Override public void enterRelease_statement(@NotNull JSHSQL.Release_statementContext ctx) { 
		stmtType = "RELEASE";
	}
	@Override public void enterRename_statement(@NotNull JSHSQL.Rename_statementContext ctx) { 
		stmtType = "RENAME";
	}
	@Override public void enterRevoke_statement(@NotNull JSHSQL.Revoke_statementContext ctx) { 
		stmtType = "REVOKE";
	}
	@Override public void enterRollback_statement(@NotNull JSHSQL.Rollback_statementContext ctx) { 
		stmtType = "ROLLBACK";
	}
	@Override public void enterSavepoint_statement(@NotNull JSHSQL.Savepoint_statementContext ctx) { 
		stmtType = "SAVEPOINT";
	}
	@Override public void enterSearched_delete_statement(@NotNull JSHSQL.Searched_delete_statementContext ctx) { 
		stmtType = "DELETE";
	}
	@Override public void enterSearched_update_statement(@NotNull JSHSQL.Searched_update_statementContext ctx) { 
		stmtType = "UPDATE";
	}
	@Override public void enterSelect_into_statement(@NotNull JSHSQL.Select_into_statementContext ctx) {
		stmtType = "SELECT INTO";
	}
	@Override public void enterSelect_statement(@NotNull JSHSQL.Select_statementContext ctx) {
		if (stmtType == null)
		stmtType = "SELECT";
	}
	@Override public void enterSet_statement(@NotNull JSHSQL.Set_statementContext ctx) { 
		stmtType = "SET";
	}
	@Override public void enterSetpath_statement(@NotNull JSHSQL.Setpath_statementContext ctx) { 
		stmtType = "DESCRIBE";
	}
	@Override public void enterSignal_statement(@NotNull JSHSQL.Signal_statementContext ctx) { 
		stmtType = "SIGNAL";
	}
	@Override public void enterTruncate_statement(@NotNull JSHSQL.Truncate_statementContext ctx) { 
		stmtType = "TRUNCATE";
	}
	@Override public void enterUpdate_statement(@NotNull JSHSQL.Update_statementContext ctx) { 
		stmtType = "UPDATE";
	}
	@Override public void enterValues_statement(@NotNull JSHSQL.Values_statementContext ctx) { 
		stmtType = "VALUES";
	}
	@Override public void enterWhenever_statement(@NotNull JSHSQL.Whenever_statementContext ctx) { 
		stmtType = "WHENEVER";
	}

	
}
