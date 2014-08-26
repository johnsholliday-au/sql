lexer grammar JSHLexer;

//@header {
//package com.jsh.sql;
//}

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

fragment
Underscore	: '_';

/***************************************************************************************************
 *   Reserved Words, as listed 
 ****************************************************************************************************/
ADD                : A D D;
AFTER              : A F T E R;
ALL                : A L L;
ALLOCATE           : A L L O C A T E;
ALLOW              : A L L O W;
ALTER              : A L T E R;
AND                : A N D;
ANY                : A N Y;
AS                 : A S;
ASENSITIVE         : A S E N S I T I V E;
ASSOCIATE          : A S S O C I A T E;
ASUTIME            : A S U T I M E;
AT                 : A T;
AUDIT              : A U D I T;
AUX                : A U X;
AUXILIARY          : A U X I L I A R Y;
BEFORE             : B E F O R E;
BEGIN              : B E G I N;
BETWEEN            : B E T W E E N;
BUFFERPOOL         : B U F F E R P O O L;
BY                 : B Y;
CALL               : C A L L;
CAPTURE            : C A P T U R E;
CASCADED           : C A S C A D E D;
CASE               : C A S E;
CAST               : C A S T;
CCSID              : C C S I D;
CHAR               : C H A R;
CHARACTER          : C H A R A C T E R;
CHECK              : C H E C K;
CLONE              : C L O N E;
CLOSE              : C L O S E;
CLUSTER            : C L U S T E R;
COLLECTION         : C O L L E C T I O N;
COLLID             : C O L L I D;
COLUMN             : C O L U M N;
COMMENT            : C O M M E N T;
COMMIT             : C O M M I T;
CONCAT             : C O N C A T;
CONDITION          : C O N D I T I O N;
CONNECT            : C O N N E C T;
CONNECTION         : C O N N E C T I O N;
CONSTRAINT         : C O N S T R A I N T;
CONTAINS           : C O N T A I N S;
CONTENT            : C O N T E N T;
CONTINUE           : C O N T I N U E;
CREATE             : C R E A T E;
CURRENT            : C U R R E N T;
CURRENT_DATE       : C U R R E N T Underscore D A T E;
CURRENT_LC_CTYPE   : C U R R E N T Underscore L C Underscore C T Y P E;
CURRENT_PATH       : C U R R E N T Underscore P A T H;
CURRENT_SCHEMA     : C U R R E N T Underscore S C H E M A;
CURRENT_TIME       : C U R R E N T Underscore T I M E;
CURRENT_TIMESTAMP  : C U R R E N T Underscore T I M E S T A M P;
CURSOR             : C U R S O R;
DATA               : D A T A;
DATABASE           : D A T A B A S E;
DAY                : D A Y;
DAYS               : D A Y S;
DBINFO             : D B I N F O;
DECLARE            : D E C L A R E;
DEFAULT            : D E F A U L T;
DELETE             : D E L E T E;
DESCRIPTOR         : D E S C R I P T O R;
DETERMINISTIC      : D E T E R M I N I S T I C;
DISABLE            : D I S A B L E;
DISALLOW           : D I S A L L O W;
DISTINCT           : D I S T I N C T;
DO                 : D O;
DOCUMENT           : D O C U M E N T;
DOUBLE             : D O U B L E;
DROP               : D R O P;
DSSIZE             : D S S I Z E;
DYNAMIC            : D Y N A M I C;
EDITPROC           : E D I T P R O C;
ELSE               : E L S E;
ELSEIF             : E L S E I F;
ENCODING           : E N C O D I N G;
ENCRYPTION         : E N C R Y P T I O N;
END                : E N D;
ENDEXEC            : E N D '-' E X E C;
ENDING             : E N D I N G;
ERASE              : E R A S E;
ESCAPE             : E S C A P E;
EXCEPT             : E X C E P T;
EXCEPTION          : E X C E P T I O N;
EXECUTE            : E X E C U T E;
EXISTS             : E X I S T S;
EXIT               : E X I T;
EXPLAIN            : E X P L A I N;
EXTERNAL           : E X T E R N A L;
FENCED             : F E N C E D;
FETCH              : F E T C H;
FIELDPROC          : F I E L D P R O C;
FINAL              : F I N A L;
FOR                : F O R;
FREE               : F R E E;
FROM               : F R O M;
FULL               : F U L L;
FUNCTION           : F U N C T I O N;
GENERATED          : G E N E R A T E D;
GET                : G E T;
GLOBAL             : G L O B A L;
GO                 : G O;
GOTO               : G O T O;
GRANT              : G R A N T;
GROUP              : G R O U P;
HANDLER            : H A N D L E R;
HAVING             : H A V I N G;
HOLD               : H O L D;
HOUR               : H O U R;
HOURS              : H O U R S;
IF                 : I F;
IMMEDIATE          : I M M E D I A T E;
IN                 : I N;
INCLUSIVE          : I N C L U S I V E;
INDEX              : I N D E X;
INHERIT            : I N H E R I T;
INNER              : I N N E R;
INOUT              : I N O U T;
INSENSITIVE        : I N S E N S I T I V E;
INSERT             : I N S E R T;
INTERSECT          : I N T E R S E C T;
INTO               : I N T O;
IS                 : I S;
ISOBID             : I S O B I D;
ITERATE            : I T E R A T E;
JAR                : J A R;
JOIN               : J O I N;
KEEP               : K E E P;
KEY                : K E Y;
LABEL              : L A B E L;
LANGUAGE           : L A N G U A G E;
LC_CTYPE           : L C Underscore C T Y P E;
LEAVE              : L E A V E;
LEFT               : L E F T;
LIKE               : L I K E;
LOCAL              : L O C A L;
LOCALE             : L O C A L E;
LOCATOR            : L O C A T O R;
LOCATORS           : L O C A T O R S;
LOCK               : L O C K;
LOCKMAX            : L O C K M A X;
LOCKSIZE           : L O C K S I Z E;
LONG               : L O N G;
LOOP               : L O O P;
MAINTAINED         : M A I N T A I N E D;
MATERIALIZED       : M A T E R I A L I Z E D;
MICROSECOND        : M I C R O S E C O N D;
MICROSECONDS       : M I C R O S E C O N D S;
MINUTE             : M I N U T E;
MINUTES            : M I N U T E S;
MODIFIES           : M O D I F I E S;
MONTH              : M O N T H;
MONTHS             : M O N T H S;
NEXTVAL            : N E X T V A L;
NO                 : N O;
NONE               : N O N E;
NOT                : N O T;
NULL               : N U L L;
NULLS              : N U L L S;
NUMPARTS           : N U M P A R T S;
OBID               : O B I D;
OF                 : O F;
OLD                : O L D;
ON                 : O N;
OPEN               : O P E N;
OPTIMIZATION       : O P T I M I Z A T I O N;
OPTIMIZE           : O P T I M I Z E;
OR                 : O R;
ORDER              : O R D E R;
ORGANIZATION       : O R G A N I Z A T I O N;
OUT                : O U T;
OUTER              : O U T E R;
PACKAGE            : P A C K A G E;
PADDED             : P A D D E D;
PARAMETER          : P A R A M E T E R;
PART               : P A R T;
PARTITION          : P A R T I T I O N;
PARTITIONED        : P A R T I T I O N E D;
PARTITIONING       : P A R T I T I O N I N G;
PATH               : P A T H;
PERIOD             : P E R I O D;
PIECESIZE          : P I E C E S I Z E;
PLAN               : P L A N;
PRECISION          : P R E C I S I O N;
PREPARE            : P R E P A R E;
PREVVAL            : P R E V V A L;
PRIQTY             : P R I Q T Y;
PRIVILEGES         : P R I V I L E G E S;
PROCEDURE          : P R O C E D U R E;
PROGRAM            : P R O G R A M;
PSID               : P S I D;
PUBLIC             : P U B L I C;
QUERY              : Q U E R Y;
QUERYNO            : Q U E R Y N O;
READS              : R E A D S;
REFERENCES         : R E F E R E N C E S;
REFRESH            : R E F R E S H;
RELEASE            : R E L E A S E;
RENAME             : R E N A M E;
REPEAT             : R E P E A T;
RESIGNAL           : R E S I G N A L;
RESTRICT           : R E S T R I C T;
RESULT             : R E S U L T;
RESULT_SET_LOCATOR : R E S U L T Underscore S E T Underscore L O C A T O R;
RETURN             : R E T U R N;
RETURNS            : R E T U R N S;
REVOKE             : R E V O K E;
RIGHT              : R I G H T;
ROLE               : R O L E;
ROLLBACK           : R O L L B A C K;
ROUND_CEILING      : R O U N D Underscore C E I L I N G;
ROUND_DOWN         : R O U N D Underscore D O W N;
ROUND_FLOOR        : R O U N D Underscore F L O O R;
ROUND_HALF_DOWN    : R O U N D Underscore H A L F Underscore D O W N;
ROUND_HALF_EVEN    : R O U N D Underscore H A L F Underscore E V E N;
ROUND_HALF_UP      : R O U N D Underscore H A L F Underscore U P;
ROUND_UP           : R O U N D Underscore U P;
ROW                : R O W;
ROWSET             : R O W S E T;
RUN                : R U N;
SAVEPOINT          : S A V E P O I N T;
SCHEMA             : S C H E M A;
SCRATCHPAD         : S C R A T C H P A D;
SECOND             : S E C O N D;
SECONDS            : S E C O N D S;
SECQTY             : S E C Q T Y;
SECURITY           : S E C U R I T Y;
SELECT             : S E L E C T;
SENSITIVE          : S E N S I T I V E;
SEQUENCE           : S E Q U E N C E;
SESSION_USER       : S E S S I O N Underscore U S E R;
SET                : S E T;
SIGNAL             : S I G N A L;
SIMPLE             : S I M P L E;
SOME               : S O M E;
SOURCE             : S O U R C E;
SPECIFIC           : S P E C I F I C;
STANDARD           : S T A N D A R D;
STATEMENT          : S T A T E M E N T;
STATIC             : S T A T I C;
STAY               : S T A Y;
STOGROUP           : S T O G R O U P;
STORES             : S T O R E S;
STYLE              : S T Y L E;
SUMMARY            : S U M M A R Y;
SYNONYM            : S Y N O N Y M;
SYSFUN             : S Y S F U N;
SYSIBM             : S Y S I B M;
SYSPROC            : S Y S P R O C;
SYSTEM             : S Y S T E M;
TABLE              : T A B L E;
TABLESPACE         : T A B L E S P A C E;
THEN               : T H E N;
TO                 : T O;
TRIGGER            : T R I G G E R;
TRUNCATE           : T R U N C A T E;
TYPE               : T Y P E;
UNDO               : U N D O;
UNION              : U N I O N;
UNIQUE             : U N I Q U E;
UNTIL              : U N T I L;
UPDATE             : U P D A T E;
USER               : U S E R;
USING              : U S I N G;
VALIDPROC          : V A L I D P R O C;
VALUE              : V A L U E;
VALUES             : V A L U E S;
VARIABLE           : V A R I A B L E;
VARIANT            : V A R I A N T;
VCAT               : V C A T;
VIEW               : V I E W;
VOLATILE           : V O L A T I L E;
VOLUMES            : V O L U M E S;
WHEN               : W H E N;
WHENEVER           : W H E N E V E R;
WHERE              : W H E R E;
WHILE              : W H I L E;
WITH               : W I T H;
WLM                : W L M;
XMLCAST            : X M L C A S T;
XMLEXISTS          : X M L E X I S T S;
XMLNAMESPACES      : X M L N A M E S P A C E S;
YEAR               : Y E A R;
YEARS              : Y E A R S;
ZONE               : Z O N E;

/**********************************************************************************
 * Keywords - used in Special Register
 **********************************************************************************/
ACCELERATION       : A C C E L E R A T I O N;
AGE                : A G E;
APPLICATION        : A P P L I C A T I O N;
CLIENT_ACCTNG      : C L I E N T Underscore A C C T N G;
CLIENT_APPLNAME    : C L I E N T Underscore A P P L N A M E;
CLIENT_USERID      : C L I E N T Underscore U S E R I D;
CLIENT_WRKSTNNAME  : C L I E N T Underscore W R K S T N N A M E;
DATE               : D A T E;
DEBUG              : D E B U G;
DECFLOAT           : D E C F L O A T;
DEGREE             : D E G R E E;
HINT               : H I N T;
INT				         : I N T;	
MEMBER             : M E M B E R;
MODE               : M O D E;
PACKAGESET         : P A C K A G E S E T;
PASSWORD           : P A S S W O R D;
ROUNDING           : R O U N D I N G;
ROUTINE            : R O U T I N E;
RULES              : R U L E S;
SCHEME             : S C H E M E;
SERVER             : S E R V E R;
SESSION            : S E S S I O N;
SQLID              : S Q L I D;
TIME               : T I M E;
TIMESTAMP          : T I M E S T A M P;
TYPES              : T Y P E S;
VERSION            : V E R S I O N;
WITHOUT            : W I T H O U T;
NEXT			         : N E X T;
PREVIOUS		       : P R E V I O U S;	

/* Key words : datatypes */
BIGINT             : B I G I N T;
BINARY             : B I N A R Y;
BLOB               : B L O B;
//CHAR               : C H A R;
CLOB               : C L O B;
//DATE               : D A T E;
DBCLOB             : D B C L O B;
//DECFLOAT           : D E C F L O A T;
DECIMAL            : D E C I M A L;
//DOUBLE             : D O U B L E;
GRAPHIC            : G R A P H I C;
INTEGER            : I N T E G E R;
REAL               : R E A L;
ROWID              : R O W I D;
SMALLINT           : S M A L L I N T;
//TIME               : T I M E;
//TIMESTAMP          : T I M E S T A M P;
VARBINARY          : V A R B I N A R Y;
VARCHAR            : V A R C H A R;
VARGRAPHIC         : V A R G R A P H I C;
XML                : X M L;


/* other keywords */
CHANGE				: C H A N G E;
TOKEN				: T O K E N;
SKIP				: S K I P;
LOCKED				: L O C K E D;
SPECIFICATION		: S P E C I F I C A T I O N;
SELECTIVITY     : S E L E C T I V I T Y;
PASSING         : P A S S I N G;
REF             : R E F;
INPUT           : I N P U T;
INDICATOR		: I N D I C A T O R;
CARDINALITY		: C A R D I N A L I T Y; 
MULTIPLIER		: M U L T I P L I E R;
COALESCE		: C O A L E S C E;
MERGE			: M E R G E;
CROSS			: C R O S S;

FIRST         : F I R S T;            
ROWS          : R O W S  ;            
ONLY          : O N L Y  ;            
READ          : R E A D  ;            
RR            : R R      ;            
RS            : R S      ;            
CS            : C S      ;            
UR            : U R      ;            
USE           : U S E    ;            
EXCLUSIVE     : E X C L U S I V E ;   
SHARE         : S H A R E;            
LOCKS         : L O C K S;      
ASC           : A S C;
DESC          : D E S C;
CALLER        : C A L L E R;  
CLIENT        : C L I E N T;  
POSITIONING   : P O S I T I O N I N G;
SCROLL        : S C R O L L;
INCLUDE		  : I N C L U D E;	
DEC			  : D E C;
NUMERIC		  : N U M E R I C;
FLOAT		  : F L O A T;
VARYING		  : V A R Y I N G;
BIT			  : B I T;
OVERRIDING    : O V E R R I D I N G;
ATOMIC		  : A T O M I C;
SQLEXCEPTION  : S Q L E X C E P T I O N;
TEMPORARY	  : T E M P O R A R Y;
DESCRIBE	  : D E S C R I B E;
EXCHANGE	  : E X C H A N G E;
SETPATH		  : S E T P A T H;

/* testing  */
CLAUSE				: C L A U S E;
ISOLATION			: I S O L A T I O N;

/***************************************************************************************
 *  Symbols
 ***************************************************************************************/

CAT 	:	(C O N C A T) |( '|' '|');
SLASH	: '/';
PLUS	: '+';
MINUS	: '-';
ASTER	: '*';

COMMA	: ',';
DOT		: '.';
COLON	: ':';
QUESTION_MARK	: '?';
SEMI	: ';';

 

LPAREN		: '(';
RPAREN		: ')';

EQUALS    : '=';
NOTEQ     : '<>'; 
GT        : '>';
GTE       : '>=';
LT        : '<';
LTE       : '<=';


IDENTIFIER
	: ('a'..'z'|'A'..'Z'|'_') ('a'..'z'|'A'..'Z'|Digit|'_'|'-')*
	;
 
fragment
Digit : '0'..'9';

fragment
EXPONENT : ('e'|'E') ('+'|'-')? ('0'..'9')+ ;

NUMBER : Digit+;


fragment
HEX_DIGIT 	: ('0'..'9'|'a'..'f'|'A'..'F') ;

NAN			: N A N; 
SNAN		: S N A N; 
INF			: I N F; 
INFINITY	: I N F I N I T Y; 

fragment
DBLAPOST    : '\'\''; 
QUOTE		: '"';
APOST		: '\'';


// String Constants
STRING		: APOST ( DBLAPOST | ~'\'' )* APOST ;
HEXSTRING	: ('X' | 'x') APOST HEX_DIGIT*? APOST;	
BINSTRING	: ('B'| 'b') ('X' | 'x') APOST HEX_DIGIT*? APOST;
GSTRING		: ('G'| 'g')  APOST .*? APOST;


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



/*
===============================================================================
Whitespace Tokens
===============================================================================
*/

WS : [ \r\t\n]+ -> skip ;	
SLC :   '--' .*? '\r'? '\n'    -> skip ;
MLC : 	'/*' .*? '*/' -> skip ; 