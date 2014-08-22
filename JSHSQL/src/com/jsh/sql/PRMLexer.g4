lexer grammar PRMLexer;

@header {
package com.jsh.sql;
}

fragment A
: 'A' | 'a';
fragment B
: 'B' | 'b';
fragment C
: 'C' | 'c';
fragment D
: 'D' | 'd';
fragment E
: 'E' | 'e';
fragment F
: 'F' | 'f';
fragment G
: 'G' | 'g';
fragment H
: 'H' | 'h';
fragment I
: 'I' | 'i';
fragment J
: 'J' | 'j';
fragment K
: 'K' | 'k';
fragment L
: 'L' | 'l';
fragment M
: 'M' | 'm';
fragment N
: 'N' | 'n';
fragment O
: 'O' | 'o';
fragment P
: 'P' | 'p';
fragment Q
: 'Q' | 'q';
fragment R
: 'R' | 'r';
fragment S
: 'S' | 's';
fragment T
: 'T' | 't';
fragment U
: 'U' | 'u';
fragment V
: 'V' | 'v';
fragment W
: 'W' | 'w';
fragment X
: 'X' | 'x';
fragment Y
: 'Y' | 'y';
fragment Z
: 'Z' | 'z';

LPAREN    : '(';
RPAREN    : ')';

EQUALS    : '=';


// keywords

TRUE  : T R U E;
FALSE : F A L S E;
INPUT : I N P U T;
TEXT  : T E X T;
FILE  : F I L E;
CATALOG : C A T A L O G;
FORMAT  : F O R M A T;
OPTIONS : O P T I O N S;
OUTPUT  : O U T P U T;

fragment
Digit : '0'..'9';


fragment
DBLAPOST    : '\'\''; 
QUOTE       : '"';
APOST       : '\'';
COLON       : ':';

STRING      : APOST ( DBLAPOST | ~'\'' )* APOST ;

SQLTXT      : '<<' .*? '>>';

IDENTIFIER
  : ('a'..'z'|'A'..'Z'|'_') ('a'..'z'|'A'..'Z'|Digit|'_'|'-')*
  ;
  
  
  // Floating Point Constant
FP_CONSTANT
  :  ('+' | '-')? 
  ( ('0'..'9')+ '.' ('0'..'9')* ('e'|'E') ('+'|'-')? ('0'..'9')+
  | '.' ('0'..'9')+ ('e'|'E') ('+'|'-')? ('0'..'9')+
  | ('0'..'9')+ ('e'|'E') ('+'|'-')? ('0'..'9')+
  )
  ;

// Decimal Constant
DECIMAL_CONSTANT
  : ('+' | '-') ?
  ( ('0'..'9')+ '.' ('0'..'9')*
  | '.' ('0'..'9')+
  )
  ;

// Integer Constant
INT_CONSTANT  
  : ('+' | '-')? ('0'..'9')+
  ;   
  
  
/*===============================================================================
Whitespace Tokens
  ===============================================================================*/

WS : [ \r\t\n]+ -> skip ; 
SLC :   '--' .*? '\r'? '\n'    -> skip ;
MLC :   '/*' .*? '*/' -> skip ; 

