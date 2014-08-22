parser grammar JSHSQL;

@header {
package com.jsh.sql;
}

options {
language=Java;
tokenVocab=JSHLexer;
}


@members {
}

/********************************************************************************
 *   Start Rule
 ********************************************************************************/
sql_file
  : select_statement SEMI? (select_statement SEMI)* EOF 
  ;

sql_statement
  : dml_statement
 // | ddl_statement 
 // | dcl_STatement
  ;
  
// ddl_statement
//  :
//  ;
//  
// dcl_statement
//  :
//  ; 
    
 dml_statement
  : select_statement
  | select_into_statement
  | declare_cursor_statement
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

insert_statement
    : INSERT STATEMENT
    ;

update_statement
    : UPDATE STATEMENT
    ;
delete_statement
    : DELETE STATEMENT
    ;

merge_statement
    : MERGE STATEMENT
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
	:ADD
	|AFTER
	|ALL
	|ALLOCATE
	|ALLOW
	|ALTER
	|AND
	|ANY
	|AS
	|ASENSITIVE
	|ASSOCIATE
	|ASUTIME
	|AT
	|AUDIT
	|AUX
	|AUXILIARY
	|BEFORE
	|BEGIN
	|BETWEEN
	|BUFFERPOOL
	|BY
	|CALL
	|CAPTURE
	|CASCADED
	|CASE
	|CAST
	|CCSID
	|CHAR
	|CHARACTER
	|CHECK
	|CLONE
	|CLOSE
	|CLUSTER
	|COLLECTION
	|COLLID
	|COLUMN
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
	|DAY
	|DAYS
	|DBINFO
	|DECLARE
	|DEFAULT
	|DELETE
	|DESCRIPTOR
	|DETERMINISTIC
	|DISABLE
	|DISALLOW
	|DISTINCT
	|DO
	|DOCUMENT
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
	|ERASE
	|ESCAPE
	|EXCEPT
	|EXCEPTION
	|EXECUTE
	|EXISTS
	|EXIT
	|EXPLAIN
	|EXTERNAL
	|FENCED
	|FETCH
	|FIELDPROC
	|FINAL
	|FOR
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
	|GROUP
	|HANDLER
	|HAVING
	|HOLD
	|HOUR
	|HOURS
	|IF
	|IMMEDIATE
	|IN
	|INCLUSIVE
	|INDEX
	|INHERIT
	|INNER
	|INOUT
	|INSENSITIVE
	|INSERT
	|INTERSECT
	|INTO
	|IS
	|ISOBID
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
	|LOCKMAX
	|LOCKSIZE
	|LONG
	|LOOP
	|MAINTAINED
	|MATERIALIZED
	|MICROSECOND
	|MICROSECONDS
	|MINUTE
	|MINUTES
	|MODIFIES
	|MONTH
	|MONTHS
	|NEXTVAL
	|NO
	|NONE
	|NOT
	|NULL
	|NULLS
	|NUMPARTS
	|OBID
	|OF
	|OLD
	|ON
	|OPEN
	|OPTIMIZATION
	|OPTIMIZE
	|OR
	|ORDER
	|ORGANIZATION
	|OUT
	|OUTER
	|PACKAGE
	|PADDED
	|PARAMETER
	|PART
	|PARTITION
	|PARTITIONED
	|PARTITIONING
	|PATH
	|PERIOD
	|PIECESIZE
	|PLAN
	|PRECISION
	|PREPARE
	|PREVVAL
	|PRIQTY
	|PRIVILEGES
	|PROCEDURE
	|PROGRAM
	|PSID
	|PUBLIC
	|QUERY
	|QUERYNO
	|READS
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
	|ROUND_CEILING
	|ROUND_DOWN
	|ROUND_FLOOR
	|ROUND_HALF_DOWN
	|ROUND_HALF_EVEN
	|ROUND_HALF_UP
	|ROUND_UP
	|ROW
	|ROWSET
	|RUN
	|SAVEPOINT
	|SCHEMA
	|SCRATCHPAD
	|SECOND
	|SECONDS
	|SECQTY
	|SECURITY
	|SELECT
	|SENSITIVE
	|SEQUENCE
	|SESSION_USER
	|SET
	|SIGNAL
	|SIMPLE
	|SOME
	|SOURCE
	|SPECIFIC
	|STANDARD
	|STATEMENT
	|STATIC
	|STAY
	|STOGROUP
	|STORES
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
	|TO
	|TRIGGER
	|TRUNCATE
	|TYPE
	|UNDO
	|UNION
	|UNIQUE
	|UNTIL
	|UPDATE
	|USER
	|USING
	|VALIDPROC
	|VALUE
	|VALUES
	|VARIABLE
	|VARIANT
	|VCAT
	|VIEW
	|VOLATILE
	|VOLUMES
	|WHEN
	|WHENEVER
	|WHERE
	|WHILE
	|WITH
	|WLM
	|XMLCAST
	|XMLEXISTS
	|XMLNAMESPACES
	|YEAR
	|YEARS
	|ZONE
	;

