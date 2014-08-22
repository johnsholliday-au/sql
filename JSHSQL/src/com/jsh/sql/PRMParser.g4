parser grammar PRMParser;

@header {
package com.jsh.sql;
}

options {
language=Java;
tokenVocab=PRMLexer;
}


@members {
}

parameter_file 
  : input_cmd fmt_opts output_cmd  
  ;
  
input_cmd  
  :INPUT COLON 
  ( text_input
  | file_input
  | catlg_input
  )
  ;

text_input
  :TEXT SQLTXT
  ;
     
file_input
  : FILE file_spec
  ;

file_spec
  : STRING
  ;
  
catlg_input
  : CATALOG LPAREN assignment_stmt+ RPAREN
  
  ;  
   

fmt_opts
  : FORMAT OPTIONS COLON assignment_stmt+
  ;


output_cmd
  :OUTPUT COLON file_spec 
  ;
  
  
assignment_stmt
  : identifier EQUALS value  
  ;  

value
  :
  ;  
  
identifier
  :TRUE
  |FALSE
  |IDENTIFIER
  ;  
   
    
  