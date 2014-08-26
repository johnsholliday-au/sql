parser grammar JSHSQL;

//@header {
//package com.jsh.sql;
//}

options {
language=Java;
tokenVocab=JSHLexer;
}


@members {
}

/********************************************************************************
 *   Start Rule - a file is one or more statements 
 ********************************************************************************/
sql_file
  : sql_statement SEMI? (sql_statement SEMI)* EOF 
  ;

/********************************************************************************
 *   A Single SQL statement  
 ********************************************************************************/

sql_statement
  : supported_statement
  | unsupported_statement 
  | invalid_statement
  ;

supported_statement
  : delete_statement
  | insert_statement
  | update_statement 
  | select_statement
  | select_into_statement
  | declare_cursor_statement
  ;   
  
invalid_statement 
	: ~(ALLOCATE
	|ALTER
	|ASSOCIATE
	|BEGIN
	|CALL
	|CLOSE
	|COMMENT
	|COMMIT
	|CONNECT
	|CREATE
	|DECLARE
	|DELETE
	|DESCRIBE
	|DROP
	|END
	|EXCHANGE
	|EXECUTE
	|EXPLAIN
	|FETCH
	|FREE
	|GET
	|GRANT
	|HOLD
	|INCLUDE
	|INSERT
	|LABEL
	|LOCK
	|MERGE
	|OPEN
	|PREPARE
	|REFRESH
	|RELEASE
	|RENAME
	|REVOKE
	|ROLLBACK
	|SAVEPOINT
	|SELECT
	|SET
	|SETPATH
	|SIGNAL
	|TRUNCATE
	|UPDATE
	|VALUES
	|WHENEVER
	) token+
   ;

unsupported_statement	
: 	 allocate_statement
	| alter_statement
	| associate_statement
	| begin_statement
	| call_statement
	| close_statement
	| comment_statement
	| commit_statement
	| connect_statement
	| create_statement
	| declare_gtt_statement
	| declare_stmt_statement
	| declare_table_statement
	| declare_variable_statement
//	| delete_statement
	| describe_statement
	| drop_statement
	| end_statement
	| exchange_statement
	| execute_statement
	| explain_statement
	| fetch_statement
	| free_statement
	| get_statement
	| grant_statement
	| hold_statement
	| include_statement
//	| insert_statement
	| label_statement
	| lock_statement
	| merge_statement
	| open_statement
	| prepare_statement
	| refresh_statement
	| release_statement
	| rename_statement
	| revoke_statement
	| rollback_statement
	| savepoint_statement
//	| select_statement
	| set_statement
	| setpath_statement
	| signal_statement
	| truncate_statement
	| update_statement
	| values_statement
	| whenever_statement
  	;
  
/********************************************************************************
 *   ALLOCATE Statement
 ********************************************************************************/
allocate_statement
	: ALLOCATE token+
	;
	 
/********************************************************************************
 *   ALTER Statement
 ********************************************************************************/
alter_statement
	: ALTER token+
	;
	 
/********************************************************************************
 *   ASSOCIATE Statement
 ********************************************************************************/
associate_statement
	: ASSOCIATE token+
	;
	 
/********************************************************************************
 *   BEGIN Statement
 ********************************************************************************/
begin_statement
	: BEGIN token+
	;
	 
/********************************************************************************
 *   CALL Statement
 ********************************************************************************/
call_statement
	: CALL token+
	;
	 
/********************************************************************************
 *   CLOSE Statement
 ********************************************************************************/
close_statement
	: CLOSE token+
	;
	 
/********************************************************************************
 *   COMMENT Statement
 ********************************************************************************/
comment_statement
	: COMMENT token+
	;
	 
/********************************************************************************
 *   COMMIT Statement
 ********************************************************************************/
commit_statement
	: COMMIT token+
	;
	 
/********************************************************************************
 *   CONNECT Statement
 ********************************************************************************/
connect_statement
	: CONNECT token+
	;
	 
/********************************************************************************
 *   CREATE Statement
 ********************************************************************************/
create_statement
	: CREATE token+
	;
	 
/********************************************************************************
 *   DECLARE Statement
 ********************************************************************************/

declare_gtt_statement
	: DECLARE GLOBAL TEMPORARY TABLE token+
	;
declare_stmt_statement
	: DECLARE STATEMENT token+
	;
declare_table_statement
	: DECLARE TABLE token+
	;
declare_variable_statement
	: DECLARE VARIABLE token+
	;

	 
/********************************************************************************
 *   DESCRIBE Statement
 ********************************************************************************/
describe_statement
	: DESCRIBE token+
	;
	 
/********************************************************************************
 *   DROP Statement
 ********************************************************************************/
drop_statement
	: DROP token+
	;
	 
/********************************************************************************
 *   END Statement
 ********************************************************************************/
end_statement
	: END token+
	;
	 
/********************************************************************************
 *   EXCHANGE Statement
 ********************************************************************************/
exchange_statement
	: EXCHANGE token+
	;
	 
/********************************************************************************
 *   EXECUTE Statement
 ********************************************************************************/
execute_statement
	: EXECUTE token+
	;
	 
/********************************************************************************
 *   EXPLAIN Statement
 ********************************************************************************/
explain_statement
	: EXPLAIN token+
	;
	 
/********************************************************************************
 *   FETCH Statement
 ********************************************************************************/
fetch_statement
	: FETCH token+
	;
	 
/********************************************************************************
 *   FREE Statement
 ********************************************************************************/
free_statement
	: FREE token+
	;
	 
/********************************************************************************
 *   GET Statement
 ********************************************************************************/
get_statement
	: GET token+
	;
	 
/********************************************************************************
 *   GRANT Statement
 ********************************************************************************/
grant_statement
	: GRANT token+
	;
	 
/********************************************************************************
 *   HOLD Statement
 ********************************************************************************/
hold_statement
	: HOLD token+
	;
	 
/********************************************************************************
 *   INCLUDE Statement
 ********************************************************************************/
include_statement
	: INCLUDE token+
	;
	 
	 
/********************************************************************************
 *   LABEL Statement
 ********************************************************************************/
label_statement
	: LABEL token+
	;
	 
/********************************************************************************
 *   LOCK Statement
 ********************************************************************************/
lock_statement
	: LOCK token+
	;
	 
/********************************************************************************
 *   MERGE Statement
 ********************************************************************************/
 //??? 
merge_statement
	: MERGE STATEMENT
	;
	 
/********************************************************************************
 *   OPEN Statement
 ********************************************************************************/
open_statement
	: OPEN token+
	;
	 
/********************************************************************************
 *   PREPARE Statement
 ********************************************************************************/
prepare_statement
	: PREPARE token+
	;
	 
/********************************************************************************
 *   REFRESH Statement
 ********************************************************************************/
refresh_statement
	: REFRESH token+
	;
	 
/********************************************************************************
 *   RELEASE Statement
 ********************************************************************************/
release_statement
	: RELEASE token+
	;
	 
/********************************************************************************
 *   RENAME Statement
 ********************************************************************************/
rename_statement
	: RENAME token+
	;
	 
/********************************************************************************
 *   REVOKE Statement
 ********************************************************************************/
revoke_statement
	: REVOKE token+
	;
	 
/********************************************************************************
 *   ROLLBACK Statement
 ********************************************************************************/
rollback_statement
	: ROLLBACK token+
	;
	 
/********************************************************************************
 *   SAVEPOINT Statement
 ********************************************************************************/
savepoint_statement
	: SAVEPOINT token+
	;
	 
	 
/********************************************************************************
 *   SET Statement
 ********************************************************************************/
set_statement
	: SET token+
	;
	 
/********************************************************************************
 *   SETPATH Statement
 ********************************************************************************/
setpath_statement
	: SETPATH token+
	;
	 
/********************************************************************************
 *   SIGNAL Statement
 ********************************************************************************/
signal_statement
	: SIGNAL token+
	;
	 
/********************************************************************************
 *   TRUNCATE Statement
 ********************************************************************************/
truncate_statement
	: TRUNCATE token+
	;
	 

	 
/********************************************************************************
 *   VALUES Statement
 ********************************************************************************/
values_statement
	: VALUES token+
	;
	 
/********************************************************************************
 *   WHENEVER Statement
 ********************************************************************************/
whenever_statement
	: WHENEVER token+
	; 
  
  
  
  


	
token
	: identifier
	| symbol
	| constant 
	;
		
symbol
	: CAT
	| SLASH
	| PLUS
	| MINUS
	| ASTER
	| COMMA
	| DOT
	| COLON
	| QUESTION_MARK
	| SEMI
	| LPAREN
	| RPAREN
	| EQUALS
	| NOTEQ
	| GT
	| GTE
	| LT
	| LTE
	;



/*===============================================================================
  The SQL DELETE  statement 
  ===============================================================================*/
delete_statement
	: searched_delete_statement
	| positioned_delete_statement
	;
	 
searched_delete_statement	
	: sd_delete_clause
	  include_column_clause?
	  set_clause?
	  where_clause? 
	  isolation_clause?
	  skip_locked_data_clause?
	  queryno_clause?
	;
	
	
	
sd_delete_clause
	: DELETE FROM single_table
	;
	
include_column_clause
	: INCLUDE LPAREN icc_colspec ( COMMA icc_colspec)+ RPAREN
	;
	
icc_colspec 
	: column_name data_type?
	;
		
set_clause
	: SET assignment_clause+
	;		

assignment_clause
	: single_col_assignment
	| multi_col_assignment
	;	

single_col_assignment
	: column_name EQUALS expression
	;

multi_col_assignment
	: LPAREN column_name (COMMA column_name)* RPAREN EQUALS
	  LPAREN mca_rhs (COMMA mca_rhs)* RPAREN
	;	

mca_rhs
	: expression
	| fullselect
	;	
	
positioned_delete_statement
	: pd_delete_clause  for_row_clause?
	
	;

pd_delete_clause	
	: DELETE FROM table_name correlation_name WHERE CURRENT OF cursor_name
	;
	


/*   Data Type */

data_type
	: built_in_type
	| distinct_type
	;
	
built_in_type
	: SMALLINT
	| INTEGER
	| INT
	| BIGINT
	| DECIMAL (LPAREN integer (COMMA integer)? RPAREN)?
	| DEC     (LPAREN integer (COMMA integer)? RPAREN)?
	| NUMERIC (LPAREN integer (COMMA integer)? RPAREN)?
	| FLOAT   (LPAREN integer RPAREN)?
	| REAL
	| DOUBLE PRECISION?
	| DECFLOAT (LPAREN integer RPAREN)?
	| CHARACTER (LPAREN integer RPAREN)?
	| CHAR (LPAREN integer RPAREN)? for_bit_data?
	| CHARACTER VARYING (LPAREN integer RPAREN) for_bit_data?
	| CHAR VARYING (LPAREN integer RPAREN) for_bit_data?
	| VARCHAR 	(LPAREN integer RPAREN) for_bit_data?
	| GRAPHIC      (LPAREN integer RPAREN)
	| VARGRAPHIC   (LPAREN integer RPAREN)
	| BINARY	   (LPAREN integer RPAREN)
	| BINARY VARYING   (LPAREN integer RPAREN)
	| VARBINARY	   (LPAREN integer RPAREN)
	| DATE
	| TIME
	| TIMESTAMP (LPAREN integer RPAREN)? tz_spec
	;
	
for_bit_data
	: FOR BIT DATA
	;	

tz_spec
	: WITH TIME ZONE
	| WITHOUT TIME ZONE
	;	
distinct_type
	: (identifier DOT)? identifier
	;		
/*===============================================================================
  The SQL INSERT  statement 
  ===============================================================================*/
insert_statement
    : insert_clause
      values_clause
    ;
    
insert_clause        
    : INSERT INTO table_name 
      insert_col_list?
      include_column_clause?
      overriding_clause?
    ;

insert_col_list
	: LPAREN column_name (COMMA column_name)* RPAREN
	;

overriding_clause
	: OVERRIDING USER VALUE
    ;

values_clause
	: values_fmt1
	| values_fmt2
	| values_fmt3
	| values_fmt4
	;

values_fmt1
	: VALUES value_spec
	;

values_fmt2
	: VALUES LPAREN value_spec  (COMMA value_spec)* RPAREN
	;	

values_fmt3
	: with_clause?
	  fullselect
	  isolation_clause?
	  queryno_clause?
	 ; 
	  	
	  	// multiple row insert
values_fmt4
	: VALUES
	  atomic_clause
	;	
	
mri_spec
	: expression
	| variable
	| DEFAULT 
	| NULL
	;	

value_spec
	: expression 
	| DEFAULT 
	| NULL
	;	
	
atomic_clause
	: ATOMIC
	| NOT ATOMIC CONTINUE ON SQLEXCEPTION
	;	
	
	
/*===============================================================================
  The SQL UPDATE  statement 
  ===============================================================================*/

update_statement
    : searched_update_statement
    | positioned_update_statement
	;

searched_update_statement
    : UPDATE single_table
      include_column_clause?
      set_clause
      where_clause?
      isolation_clause?
      skip_locked_data_clause?
      queryno_clause?
    ;
    
positioned_update_statement
	: UPDATE table_name identifier?
	 set_clause
	 WHERE CURRENT OF identifier 
	for_row_clause?       
    ;
        
for_row_clause
	: FOR ROW (variable| integer) OF ROWSET
	;	

/*===============================================================================
  The SQL DECLARE CURSOR statement 
  ===============================================================================*/

declare_cursor_statement
  : DECLARE cursor_name scroll_clause? CURSOR position_clauses* FOR cursor_spec 
  ;   

cursor_name
  : identifier 
  ;
  
cursor_spec
  : statement_name
  | select_statement 
  ; 
  
statement_name
  : identifier
  ; 
  
scroll_clause
  : NO SCROLL
  | ASENSITIVE SCROLL
  | INSENSITIVE SCROLL
  | SENSITIVE (DYNAMIC | STATIC)? SCROLL
  ;
  

position_clauses
  : holdability_clause
  | returnability_clause
  | rowset_positioning_clause
  ;   
  
holdability_clause
  : WITH HOLD
  | WITHOUT HOLD
  ;

returnability_clause
  : WITH RETURN (TO CALLER | TO CLIENT)?
  | WITHOUT RETURN
  ;
    
rowset_positioning_clause
  : WITH ROWSET POSITIONING
  | WITHOUT ROWSET POSITIONING
  ;   

/*===============================================================================
  The SQL SELECT INTO statement 
  ===============================================================================*/

select_into_statement
  : with_clause?
    select_clause
    into_clause
    from_clause
    where_clause?
    group_by_clause?
    having_clause?
    order_by_clause?
   ; 
   

into_clause
  : INTO variable (ic_comma variable)*
  ;
  
//host_variable
//  : variable
//  ;
  
ic_comma
  : COMMA
  ;   


/*===============================================================================
  The SQL Select statement according to IBM 
  ===============================================================================*/
select_statement 
	:  with_clause?
	   fullselect
	   ss_clause*
	   EOF
	;

// These clauses are optional, and can appear in any order.
// Furthermore the Update_clause is mutually exclusive with the read_only_clause
// and there can only be one of each clause. These rules are not enforced by this grammar  
ss_clause
	:  update_clause
	|  read_only_clause
	|  optimize_clause
	|  isolation_clause
	|  queryno_clause
	|  skip_locked_data_clause
	;
 

/*********************************************************************************************
 *   WITH CLause and Common Table Expression (cte)
 *********************************************************************************************/	   
with_clause
	: WITH cte+=common_table_expression (COMMA cte+=common_table_expression)* 
	;	   

common_table_expression
	: cte_tb_identifier 
	  ( cte_cl_lparen ctecol+=identifier ( cte_cl_comma ctecol+=identifier)* cte_cl_rparen )?
	  cte_fullselect
	;

cte_tb_identifier
	: identifier
	;	
cte_cl_lparen
	: LPAREN
	;	
	
cte_cl_rparen
	: RPAREN
	;	
	
cte_cl_comma
	: COMMA
	;	
	
cte_fullselect
	: AS cte_fs_lparen fullselect cte_fs_rparen
	;
	
cte_fs_lparen
	: LPAREN
	;	
	
cte_fs_rparen
	: RPAREN
	;		
// <<<< end of WITH clause >>>>//	

/*********************************************************************************************
 *   FULLSELECT
 *********************************************************************************************/	 	  
fullselect
	: fs_select (fs_operator fs_select)* 	
	  ob=order_by_clause?
	  ff=fetch_first_clause?
	;
	
fs_select		
	: subselect                         	#FS_SUBSELECT 
	| (LPAREN  fullselect RPAREN)			#FS_FULLSELECT	
	;
	
fs_operator
	: (UNION | EXCEPT | INTERSECT) (DISTINCT | ALL )?
	;


// <<<< end of FULLSELECT clause >>>>//	

/*********************************************************************************************
 *   SUBSELECT
 *********************************************************************************************/	 	  
subselect
	: select_clause
	  from_clause
	  where_clause?
	  group_by_clause?
	  having_clause?
	  order_by_clause?
	  fetch_first_clause?
	 ; 
	 


select_clause
	: SELECT (ALL | DISTINCT)? (ASTER | (sc_item (sc_comma sc_item)* ) )
	;
	
sc_comma
	: COMMA
	;
		
	
sc_item
	:	expression ( (AS)? new_col_name)?
	|   sc_qual DOT ASTER 
	;

sc_qual
	: identifier
	;	

////////////////////////////////////////////////////////////////////////////////
//    FROM CLAUSE
////////////////////////////////////////////////////////////////////////////////
from_clause
	: FROM trl+=table_reference (fc_comma trl+=table_reference)*
	;

fc_comma
    : COMMA
    ;
    
table_reference
	: single_table
	| nested_table_expression
	| table_function_reference
//      implement when INSERT, UPDATE, DELETE and MERGE statements completed      
	| data_change_table_reference
//    joined table is left recursive as !!
//	| joined_table
//    refactored as:  
    | table_reference join_op table_reference join_spec
    | table_reference CROSS JOIN table_reference
    | jt_lparen table_reference jt_rparen
//  this rule is not as restrictive as the original: ( joined-table )       
	| table_locator_reference
	| xml_table_expression
	;	

single_table
	: table_name (period_specification)? (correlation_clause)?
	;
	
table_name
	: ((identifier DOT)? identifier DOT)? identifier
	;
	
period_specification
	: PERIOD SPECIFICATION
	;

correlation_clause
	: (AS)? correlation_name 
	 ( cc_lparen new_col_name (cc_comma new_col_name)* cc_rparen ) ?
	;	

cc_lparen
    : LPAREN
    ;    
cc_rparen
    : RPAREN
    ;    
cc_comma
    : COMMA
    ;    

correlation_name
	: identifier
	;

new_col_name
	: identifier
	;		

// nested table Expression
nested_table_expression
	: TABLE nte_lparen fullselect nte_rparen correlation_clause
	;	
nte_lparen
    : LPAREN
    ;    
nte_rparen
    : RPAREN
    ;    
// table_function_reference
table_function_reference
	: TABLE tfr_lparen1 
                identifier tfr_lparen2 tfr_operand tfr_rparen2 
                tfr_cardinality_clause
                tfr_rparen1
	;		
tfr_lparen1
    : LPAREN
    ;    
tfr_rparen1
    : RPAREN
    ;    
tfr_lparen2
    : LPAREN
    ;    
tfr_rparen2
    : RPAREN
    ;    
tfr_operand
    : (tfrel+=expression (tfr_comma tfrel+=expression)*) #TFR_EXPR
    | TABLE identifier                                   #TFR_TTN
    ;
tfr_comma
    : COMMA
    ;
tfr_cardinality_clause
    : (CARDINALITY  INT_CONSTANT )
    | (CARDINALITY MULTIPLIER num_constant )
    ;

num_constant
    : INT_CONSTANT
    | DECIMAL_CONSTANT
    ;

//data_change_table_reference
data_change_table_reference
	: dctr_clause correlation_clause 
	;		
dctr_clause
    : dctr_fmt1
    | dctr_fmt2
    | dctr_fmt3
    | dctr_fmt4
    ;

dctr_lparen
: LPAREN
;
dctr_rparen
: RPAREN
;
    
dctr_fmt1
    : FINAL TABLE dctr_lparen insert_statement dctr_rparen
    ;

dctr_fmt2
    : (FINAL | OLD ) TABLE dctr_lparen update_statement dctr_rparen
    ;
dctr_fmt3
    : OLD TABLE dctr_lparen delete_statement dctr_rparen
    ;

dctr_fmt4
    : FINAL TABLE dctr_lparen merge_statement dctr_rparen
    ;



//joined_table
//joined_table
//	: (table_reference join_op table_reference ON join_condition)
//    | (table_reference CROSS JOIN table_reference)
//    | (jt_lparen joined_table jt_rparen)
//	;

//jt_fmt1
//    : table_reference join_op table_reference ON join_condition
//    ;
//
//jt_fmt2
//    : table_reference CROSS JOIN table_reference
//    ;

//jt_fmt3
//    : jt_lparen joined_table jt_rparen
//    ;

jt_lparen
: LPAREN
;
jt_rparen
: RPAREN
;

join_op
    : ( INNER  | (LEFT | RIGHT | FULL) OUTER?) JOIN
    ;

join_spec
	: ON join_condition
	;
join_condition
    : search_condition
    | (foj_condition (AND foj_condition)* )
    ;

foj_condition
    : full_join_expression EQUALS full_join_expression
    ;
full_join_expression
    : fje_fmt1
    | fje_fmt2
    ;
fje_fmt1
    : column_name
    | cast_function
    ;
fje_fmt2
    : COALESCE fje2_lparen fje_fmt1 (fje2_comma fje_fmt1)+ fje2_rparen
    ;

cast_function
    : CAST LPAREN expression AS identifier RPAREN
    ;
fje2_lparen
: LPAREN
;
fje2_rparen
: RPAREN
;
fje2_comma
    : COMMA
    ;


//table_locator_reference
table_locator_reference
	: TABLE tlr_lparen identifier LIKE identifier tlr_rparen identifier
	;	
tlr_lparen
: LPAREN
;
tlr_rparen
: RPAREN
;


//xml_table_expression
xml_table_expression
	: identifier correlation_clause
	;		

//<<< End Of FROM CLAUSE rules >>>> 
	
//////////////////////////////////////////////////////////////////////
// WHERE CLAUSE
where_clause
	: WHERE search_condition 
	;

group_by_clause
	: GROUP BY gb_expression ( gb_comma gb_expression)*
	;

gb_comma
    :  COMMA
    ;

gb_expression
    : column_name
    | NUMBER  
    ;

//////////////////////////////////////////////////////////////////////
// HAVING CLAUSE
having_clause
	: HAVING search_condition
	;
/////////////////////////////////////////////////////////////////////
// ORDER BY Clause
order_by_clause
	: ORDER BY ob_specification 
	;
ob_specification
    : ob_spec1
    | ob_spec2
    | ob_spec3  
    ;

ob_spec1
    : key_spec (ob_comma key_spec)*
    ;    

key_spec
    : sort_key (ASC | DESC )?
    ;

sort_key
    : column_name
    | integer
//  | sk_expr
    ;
//sk_expr
//    : 
//   ;    
ob_comma
    : COMMA;
ob_spec2
    : INPUT SEQUENCE
    ;    
ob_spec3
    : ORDER OF table_designator
    ;    
        
///////////////////////////////////
    

update_clause
	: FOR UPDATE ( OF identifier (COMMA identifier)*)?
	;
read_only_clause
	: FOR READ ONLY
	;
optimize_clause
	: OPTIMIZE FOR NUMBER (ROW | ROWS)
	;
fetch_first_clause
	: FETCH FIRST NUMBER (ROW | ROWS) ONLY
	; 
	
isolation_clause
	: WITH 
	  ( RR lock_clause
	  | RS lock_clause
	  | CS
	  | UR
	  )	
	;
lock_clause
	: USE AND KEEP ( EXCLUSIVE | UPDATE | SHARE ) LOCKS
	;	
queryno_clause
	: QUERYNO NUMBER
	;
	
skip_locked_data_clause		
	: SKIP LOCKED DATA
	;	

	
	
/************************************************************************************
 *   Expression
 ************************************************************************************/
expression
	: unary_operator? 
	  ( function_invocation
	  |	LPAREN expression RPAREN
	  | constant
	  | column_name
	  | variable
	  | special_register
//	  | scalar_fullselect
	  | tz_expression
	  | labeled_duration
//	  | case_expression // needs predicate
	  | xmlcast_specification
//	  | olap_specification   // not widely used implement later
	  | row_change_expression
	  | sequence_reference
	  )
	  (operator expression)* 
	;
	
operator
	: CAT | SLASH | PLUS | MINUS | ASTER	
	;

sequence_reference
	: ((NEXT VALUE) | (PREVIOUS VALUE)) FOR sequence_name
	;

sequence_name
	: (identifier DOT)? identifier
	;	

row_change_expression
	: ROW CHANGE (TIMESTAMP | TOKEN) FOR table_designator 
	;

table_designator
	: IDENTIFIER
	;
	
//olap_specification
//	: 
//	;

xmlcast_specification
	: XMLCAST LPAREN  ( expression | NULL | parameter_marker  ) AS datatype
	;

parameter_marker
	: QUESTION_MARK
	;

datatype
	:  DATE
	| TIME
	| TIMESTAMP
	| CHAR
	| VARCHAR
	| CLOB
	| GRAPHIC
	| VARGRAPHIC
	| DBCLOB
	| BINARY
	| VARBINARY
	| BLOB
	| SMALLINT
	| INTEGER
	| BIGINT
	| DECIMAL
	| DECFLOAT
	| REAL
	| DOUBLE
	| ROWID
	| XML
	// ?? user Defined Data Type ??
	;	
	
//case_expression
//	: CASE (searched_when_call | simple_when_clause) ELSE (NULL | expression) END  
//	;
//
//searched_when_call
//	: ( WHEN search_condition THEN (NULL | expression) )+
//	;
//	
//search_condition
//	: (NOT)? (predicate | LPAREN search_condition RPAREN) 
//	  ( (AND | OR) (NOT)? (predicate | LPAREN search_condition RPAREN) )*
//	;
//	
//predicate
//	: comparison_predicate
////	| between_predicate
////	| in_predicate
////	| pattern_matching_predicate // like predicate and other similar predicates
////	| null_predicate
////	| exists_predicate
//	;
//
//comparison_predicate
//	: left=row_value_predicand c=comp_op right=row_value_predicand
//	;
//	
//comp_op
//	: EQUAL
//	| NOT_EQUAL
//	| LTH
//	| LEQ
//	| GTH
//	| GEQ
//	;
//
//			
//simple_when_clause
//	: expression (WHEN expression THEN (NULL | expression) )+
//	;
		
labeled_duration
	: ( function_invocation
	  |	LPAREN expression RPAREN
	  | constant
	  | column_name
//	  | variable
	  ) duration
	;

duration
	: ( YEAR
	  | YEARS
	  | MONTH
	  | MONTHS
	  | DAY
	  | DAYS
	  | HOUR
	  | HOURS
	  | MINUTE
	  | MINUTES
	  | SECOND
	  | SECONDS
	  | MICROSECOND
	  | MICROSECONDS 	
	  )
	; 	

tz_expression
	: ( function_invocation
	  |	LPAREN expression RPAREN
	  | constant
	  | column_name
	  | variable
	  | special_register
//	  | scalar_fullselect
//	  | case_expression
//	  | cast_specification
	  ) AT ( LOCAL | TIME ZONE (PLUS | MINUS) NUMBER COLON NUMBER | expression)
	  ;

//scalar_fullselect
//	: fullselect
//	;

// Host Variables, SQL variables, parameter Marker, Parameter
variable
	: ( COLON (identifier DOT)? identifier var_indicator?) 
    | QUESTION_MARK
	;

var_indicator
	: INDICATOR? COLON identifier
	;
column_name
	: (identifier DOT)? identifier 
	;

//qualifier
//	:  IDENTIFIER (identifier DOT)? identifier
//	;

sign	
	: (PLUS | MINUS)
	;

integer
	: NUMBER
	;
	
/*********************************************************************************
 * CONSTANT
 *********************************************************************************/
constant
	: integer_constant
	| floating_point_constant
	| decimal_constant
//	| decimal_fp_constant
	| character_string_constant
	| binary_string_constant
	| datetime_constant
	| graphic_String_constant
	;
	
integer_constant
	: INT_CONSTANT | NUMBER
	;

floating_point_constant
	:FP_CONSTANT
	| sign? ( INF | INFINITY | NAN | SNAN )
	;

decimal_constant
	: DECIMAL_CONSTANT
	;

//decimal_fp_constant
//	: floating_point_constant 
//	| sign?
//	( INF
//	| INFINITY
//	| NAN
//	| SNAN )
//	;

character_string_constant
	: string_constant
	| hex_string_constant;

string_constant	
	: STRING
	;	
	
binary_string_constant	
	: BINSTRING  
	;
	
hex_string_constant
	: HEXSTRING
	;

graphic_String_constant
	: GSTRING
	;

datetime_constant
	: DATE string_constant
	| TIME string_constant
	| TIMESTAMP string_constant
	;
	


/********************************************************************************
 * Function
 ********************************************************************************/
function_invocation
	: function_name LPAREN (ALL | DISTINCT)? ((TABLE transition_table_name) | (expression (COMMA expression)*) ) 
	;
	
	
transition_table_name
	:IDENTIFIER
	;

function_name
	: IDENTIFIER
	;


unary_operator
	: (PLUS | MINUS) 
	;

/**********************************************************************************
 *  SPECIAL REGISTER
 **********************************************************************************/ 
special_register
:	CURRENT APPLICATION ENCODING SCHEME
|	CURRENT CLIENT_ACCTNG
|	CURRENT CLIENT_APPLNAME
| 	CURRENT CLIENT_USERID
|	CURRENT CLIENT_WRKSTNNAME
|	( CURRENT DATE | CURRENT_DATE )
|	CURRENT DEBUG MODE
|	CURRENT DECFLOAT ROUNDING MODE
|	CURRENT DEGREE
|	CURRENT EXPLAIN MODE
|	(CURRENT LOCALE? LC_CTYPE | CURRENT_LC_CTYPE)
|	CURRENT MAINTAINED TABLE? TYPES (FOR OPTIMIZATION)?
|	CURRENT MEMBER
|	CURRENT OPTIMIZATION HINT
|	CURRENT PACKAGE PATH
|	CURRENT PACKAGESET
|	(CURRENT PATH | CURRENT_PATH)
|	CURRENT PRECISION
|	CURRENT QUERY ACCELERATION
|	CURRENT REFRESH AGE
|	CURRENT ROUTINE VERSION
|	CURRENT RULES
|	(CURRENT SCHEMA | CURRENT_SCHEMA)
|	CURRENT SERVER
|	CURRENT SQLID
|	(CURRENT TIME | CURRENT_TIME)
|	(CURRENT TIMESTAMP | CURRENT_TIMESTAMP) (LPAREN INT RPAREN)? ( (WITHOUT TIME ZONE) | (WITH TIME ZONE) )?
|	CURRENT TIME ZONE
|	SESSION TIME ZONE
|	ENCRYPTION PASSWORD
|	(SESSION_USER | USER)
;


/************************************************************************************
 *  SEARCH CONDITION snd PREDICATE 
 ************************************************************************************/
search_condition 
  : sc_not? sc_predfmt1 (sc_operator sc_not? sc_predfmt2)* 
  ;
  
sc_not
  : NOT
  ;

sc_operator
  : AND | OR
  ;
  
sc_predfmt1
  : (predicate selectivity_clause?)
  | (sc_lparen search_condition sc_rparen)
  ;  
  
selectivity_clause
  : SELECTIVITY (DECIMAL_CONSTANT | INT_CONSTANT)
  ;
     
sc_predfmt2
  : predicate  
  | (sc_lparen search_condition sc_rparen)
  ;  


sc_lparen
  : LPAREN
  ;

sc_rparen
  : RPAREN
  ;
      
// <<<<<<  End of Search Condition >>>>>>>>>>>>>>>

/************************************************************************************
 * PREDICATE 
 ************************************************************************************/

predicate
  : basic_predicate
  | quantified_predicate
  | between_predicate
  | distinct_predicate
  | exists_predicate
  | ipicate
  | like_predicate
  | null_predicate
  | xmlexists_predicate
  ;

// BASIC PREDICATE
basic_predicate
  : expression pred_operator expression
  | row_value_expression rve_operator row_value_expression  
  ;

// QUANTIFIED PREDICATE
quantified_predicate
  : qp_fmt1
  | qp_fmt2
  | qp_fmt3
  ;

qp_fmt1 
  : expression pred_operator qp_fmt1_qual qp_lparen fullselect qp_rparen
  ;
  
qp_fmt2
  : row_value_expression qp_fmt2_op qp_fmt2_qual qp_lparen fullselect qp_rparen
  ;

qp_fmt3
  : row_value_expression qp_fmt3_op qp_fmt3_qual qp_lparen fullselect qp_rparen
  ;    

qp_fmt1_qual 
  : SOME | ANY | ALL
  ;
qp_lparen
  : LPAREN
  ;
qp_rparen
  : RPAREN
  ;
qp_fmt2_op 
  : EQUALS
  ;
qp_fmt2_qual
  : SOME | ANY
  ;
qp_fmt3_op 
  : NOTEQ
  ;
qp_fmt3_qual
  : ALL
  ;

   
// BETWEEN PREDICATE
between_predicate
  : expression NOT? BETWEEN expression AND expression
  ;

// BETWEEN PREDICATE
distinct_predicate
  : dp_fmt1
  | dp_fmt2
  ;

dp_fmt1
  : expression dp_op expression
  ;
  
dp_fmt2
  : row_value_expression dp_op row_value_expression
  ;    

dp_op
  : NOT? DISTINCT FROM 
  ;
  
// EXISTS PREDICATE  
exists_predicate
  : EXISTS ep_lparen fullselect ep_rparen
  ;
ep_lparen
  : LPAREN
  ; 

ep_rparen
  : RPAREN
  ;    

// IN PREDICATE  
ipicate
  : ip_fmt1
  | ip_fmt2
  ;

ip_fmt1  
  : expression ip_op ip_rhs 
  ;

ip_rhs
  : ip_lparen fullselect ip_rparen
  | ip_lparen expression (ip_comma expression)ip_rparen
  ;
  
ip_fmt2  
  : row_value_expression ip_op ip_lparen fullselect ip_rparen
  ;

ip_lparen
  : LPAREN
  ;
ip_rparen
  : RPAREN
  ;
ip_comma
  : COMMA
  ;  
ip_op
  : NOT? IN
  ;

// LIKE PREDICATE   
like_predicate
  : expression lp_op expression (ESCAPE expression)?
  ;
 lp_op
  : NOT? LIKE
  ;
  
// NULL PREDICATE  
null_predicate
  : expression np_op NULL 
  ;

np_op
  : IS NOT?
  ;

// XML EXISTS    
xmlexists_predicate
  : XMLEXISTS xp_lparen xquery xp_passing? xp_rparen
  ;

xp_lparen
  : LPAREN
  ;
xp_rparen
  : RPAREN
  ;

xp_passing
  : PASSING (BY REF)? xq_arg (xpp_comma xq_arg)*
  ;  
xpp_comma
  : COMMA
  ;      
    
xquery
  : STRING
  ;

xq_arg  
  : identifier (AS identifier)
  ;    
// other pred related rules
pred_operator
  :  EQUALS | NOTEQ | GT | GTE | LT | LTE 
  ;

rve_operator
  :  EQUALS | NOTEQ  
  ;

// ROW VALUE EXPRESSION
row_value_expression
  : rv_lparen expression (rve_comma expression)* rv_rparen
  ;

rve_comma
  : COMMA
  ;    
rv_lparen
  : LPAREN
  ;

rv_rparen
  : RPAREN
  ;
//<<<<<<<< END OF SEARCH CONDITION >>>>>>

////////////////////////////////////////////////////////////////////////////////
// Language Elements
   
	
identifier
	: ordinary_identifier
	| delimited_identifier
	;
	
ordinary_identifier	
	:id
	;

delimited_identifier	
	: QUOTE id QUOTE
	;		
	
id
	: IDENTIFIER 
	| keyword
	;	
	
keyword
	:ACCELERATION
	|ADD
	|AFTER
	|AGE
	|ALL
	|ALLOCATE
	|ALLOW
	|ALTER
	|AND
	|ANY
	|APOST
	|APPLICATION
	|AS
	|ASC
	|ASENSITIVE
	|ASSOCIATE
	|ASTER
	|ASUTIME
	|AT
	|ATOMIC
	|AUDIT
	|AUX
	|AUXILIARY
	|BEFORE
	|BEGIN
	|BETWEEN
	|BIGINT
	|BINARY
	|BINSTRING
	|BIT
	|BLOB
	|BUFFERPOOL
	|BY
	|CALL
	|CALLER
	|CAPTURE
	|CARDINALITY
	|CASCADED
	|CASE
	|CAST
	|CAT
	|CCSID
	|CHANGE
	|CHAR
	|CHARACTER
	|CHECK
	|CLAUSE
	|CLIENT
	|CLIENT_ACCTNG
	|CLIENT_APPLNAME
	|CLIENT_USERID
	|CLIENT_WRKSTNNAME
	|CLOB
	|CLONE
	|CLOSE
	|CLUSTER
	|COALESCE
	|COLLECTION
	|COLLID
	|COLON
	|COLUMN
	|COMMA
	|COMMENT
	|COMMIT
	|CONCAT
	|CONDITION
	|CONNECT
	|CONNECTION
	|CONSTRAINT
	|CONTAINS
	|CONTENT
	|CONTINUE
	|CREATE
	|CROSS
	|CS
	|CURRENT
	|CURRENT_DATE
	|CURRENT_LC_CTYPE
	|CURRENT_PATH
	|CURRENT_SCHEMA
	|CURRENT_TIME
	|CURRENT_TIMESTAMP
	|CURSOR
	|DATA
	|DATABASE
	|DATE
	|DAY
	|DAYS
	|DBCLOB
	|DBINFO
	|DEBUG
	|DEC
	|DECFLOAT
	|DECIMAL
	|DECIMAL_CONSTANT
	|DECLARE
	|DEFAULT
	|DEGREE
	|DELETE
	|DESC
	|DESCRIPTOR
	|DETERMINISTIC
	|DISABLE
	|DISALLOW
	|DISTINCT
	|DO
	|DOCUMENT
	|DOT
	|DOUBLE
	|DROP
	|DSSIZE
	|DYNAMIC
	|EDITPROC
	|ELSE
	|ELSEIF
	|ENCODING
	|ENCRYPTION
	|END
	|ENDEXEC
	|ENDING
	|EQUALS
	|ERASE
	|ESCAPE
	|EXCEPT
	|EXCEPTION
	|EXCLUSIVE
	|EXECUTE
	|EXISTS
	|EXIT
	|EXPLAIN
	|EXTERNAL
	|FENCED
	|FETCH
	|FIELDPROC
	|FINAL
	|FIRST
	|FLOAT
	|FOR
	|FP_CONSTANT
	|FREE
	|FROM
	|FULL
	|FUNCTION
	|GENERATED
	|GET
	|GLOBAL
	|GO
	|GOTO
	|GRANT
	|GRAPHIC
	|GROUP
	|GSTRING
	|GT
	|GTE
	|HANDLER
	|HAVING
	|HEXSTRING
	|HINT
	|HOLD
	|HOUR
	|HOURS
	|IDENTIFIER
	|IF
	|IMMEDIATE
	|IN
	|INCLUDE
	|INCLUSIVE
	|INDEX
	|INDICATOR
	|INF
	|INFINITY
	|INHERIT
	|INNER
	|INOUT
	|INPUT
	|INSENSITIVE
	|INSERT
	|INT
	|INTEGER
	|INTERSECT
	|INTO
	|INT_CONSTANT
	|IS
	|ISOBID
	|ISOLATION
	|ITERATE
	|JAR
	|JOIN
	|KEEP
	|KEY
	|LABEL
	|LANGUAGE
	|LC_CTYPE
	|LEAVE
	|LEFT
	|LIKE
	|LOCAL
	|LOCALE
	|LOCATOR
	|LOCATORS
	|LOCK
	|LOCKED
	|LOCKMAX
	|LOCKS
	|LOCKSIZE
	|LONG
	|LOOP
	|LPAREN
	|LT
	|LTE
	|MAINTAINED
	|MATERIALIZED
	|MEMBER
	|MERGE
	|MICROSECOND
	|MICROSECONDS
	|MINUS
	|MINUTE
	|MINUTES
	|MLC
	|MODE
	|MODIFIES
	|MONTH
	|MONTHS
	|MULTIPLIER
	|NAN
	|NEXT
	|NEXTVAL
	|NO
	|NONE
	|NOT
	|NOTEQ
	|NULL
	|NULLS
	|NUMBER
	|NUMERIC
	|NUMPARTS
	|OBID
	|OF
	|OLD
	|ON
	|ONLY
	|OPEN
	|OPTIMIZATION
	|OPTIMIZE
	|OR
	|ORDER
	|ORGANIZATION
	|OUT
	|OUTER
	|OVERRIDING
	|PACKAGE
	|PACKAGESET
	|PADDED
	|PARAMETER
	|PART
	|PARTITION
	|PARTITIONED
	|PARTITIONING
	|PASSING
	|PASSWORD
	|PATH
	|PERIOD
	|PIECESIZE
	|PLAN
	|PLUS
	|POSITIONING
	|PRECISION
	|PREPARE
	|PREVIOUS
	|PREVVAL
	|PRIQTY
	|PRIVILEGES
	|PROCEDURE
	|PROGRAM
	|PSID
	|PUBLIC
	|QUERY
	|QUERYNO
	|QUESTION_MARK
	|QUOTE
	|READ
	|READS
	|REAL
	|REF
	|REFERENCES
	|REFRESH
	|RELEASE
	|RENAME
	|REPEAT
	|RESIGNAL
	|RESTRICT
	|RESULT
	|RESULT_SET_LOCATOR
	|RETURN
	|RETURNS
	|REVOKE
	|RIGHT
	|ROLE
	|ROLLBACK
	|ROUNDING
	|ROUND_CEILING
	|ROUND_DOWN
	|ROUND_FLOOR
	|ROUND_HALF_DOWN
	|ROUND_HALF_EVEN
	|ROUND_HALF_UP
	|ROUND_UP
	|ROUTINE
	|ROW
	|ROWID
	|ROWS
	|ROWSET
	|RPAREN
	|RR
	|RS
	|RULES
	|RUN
	|SAVEPOINT
	|SCHEMA
	|SCHEME
	|SCRATCHPAD
	|SCROLL
	|SECOND
	|SECONDS
	|SECQTY
	|SECURITY
	|SELECT
	|SELECTIVITY
	|SEMI
	|SENSITIVE
	|SEQUENCE
	|SERVER
	|SESSION
	|SESSION_USER
	|SET
	|SHARE
	|SIGNAL
	|SIMPLE
	|SKIP
	|SLASH
	|SLC
	|SMALLINT
	|SNAN
	|SOME
	|SOURCE
	|SPECIFIC
	|SPECIFICATION
	|SQLEXCEPTION
	|SQLID
	|STANDARD
	|STATEMENT
	|STATIC
	|STAY
	|STOGROUP
	|STORES
	|STRING
	|STYLE
	|SUMMARY
	|SYNONYM
	|SYSFUN
	|SYSIBM
	|SYSPROC
	|SYSTEM
	|TABLE
	|TABLESPACE
	|THEN
	|TIME
	|TIMESTAMP
	|TO
	|TOKEN
	|TRIGGER
	|TRUNCATE
	|TYPE
	|TYPES
	|UNDO
	|UNION
	|UNIQUE
	|UNTIL
	|UPDATE
	|UR
	|USE
	|USER
	|USING
	|VALIDPROC
	|VALUE
	|VALUES
	|VARBINARY
	|VARCHAR
	|VARGRAPHIC
	|VARIABLE
	|VARIANT
	|VARYING
	|VCAT
	|VERSION
	|VIEW
	|VOLATILE
	|VOLUMES
	|WHEN
	|WHENEVER
	|WHERE
	|WHILE
	|WITH
	|WITHOUT
	|WLM
	|WS
	|XML
	|XMLCAST
	|XMLEXISTS
	|XMLNAMESPACES
	|YEAR
	|YEARS
	|ZONE
	;
