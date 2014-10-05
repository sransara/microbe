grammar Microbe;

@member: {
  public SymbolTableTree symbols;
}

program: PROGRAM id BEGIN pgm_body END { symbols = new SymbolTableTree(); };
id: IDENTIFIER ;
pgm_body: decl func_declarations ;
decl: string_decl decl
    | var_decl decl
    |
    ;

/* Global String Declaration */
string_decl: STRING id ASSIGN str SEMICOLON ;
str: STRINGLITERAL ;

/* Variable Declaration */
var_decl: var_type id_list SEMICOLON ;
var_type: FLOAT | INT ;
any_type: var_type | VOID ;
id_list: id id_tail ;
id_tail: COMMA id id_tail | ;

/* Function Paramater List */
param_decl_list: param_decl param_decl_tail | ;
param_decl: var_type id ;
param_decl_tail: COMMA param_decl param_decl_tail | ;

/* Function Declarations */
func_declarations: func_decl func_declarations | ;
func_decl: FUNCTION any_type id OPAREN param_decl_list CPAREN BEGIN func_body END ;
func_body: decl stmt_list ;

/* Statement List */
stmt_list: stmt stmt_list | ;
stmt: base_stmt | if_stmt | while_stmt ;
base_stmt: assign_stmt | read_stmt | write_stmt | return_stmt ;

/* Basic Statements */
assign_stmt: assign_expr SEMICOLON ;
assign_expr: id ASSIGN expr ;
read_stmt: READ OPAREN id_list CPAREN SEMICOLON ;
write_stmt: WRITE OPAREN id_list CPAREN SEMICOLON ;
return_stmt: RETURN expr SEMICOLON ;

/* Expressions */
expr: expr_prefix factor ;
expr_prefix: expr_prefix factor addop | ;
factor: factor_prefix postfix_expr ;
factor_prefix:  factor_prefix postfix_expr mulop | ;
postfix_expr:  primary | call_expr ;
call_expr:  id OPAREN expr_list CPAREN ;
expr_list: expr expr_list_tail | ;
expr_list_tail: COMMA expr expr_list_tail | ;
primary: OPAREN expr CPAREN | id | INTLITERAL | FLOATLITERAL ;
addop: ADD | MINUS ;
mulop: MULTIPLY | DIVIDE;

/* Complex Statements and Condition */
if_stmt: IF OPAREN cond CPAREN decl stmt_list else_part ENDIF ;
else_part: ELSE decl stmt_list | ;
cond: expr compop expr ;
compop: GT | LT | EQUALS | NEQUALS | GTE | LTE ;
while_stmt: WHILE OPAREN cond CPAREN decl stmt_list ENDWHILE ;

ASSIGN: ':=' ;
SEMICOLON: ';' ;
ADD: '+' ;
MINUS: '-' ;
MULTIPLY: '*' ;
DIVIDE: '/' ;
GT: '<' ;
LT: '>' ;
GTE: '<=' ;
LTE: '>=' ;
EQUALS: '=' ;
NEQUALS: '!=' ;
OPAREN: '(' ;
CPAREN: ')' ;
COMMA: ',' ;

PROGRAM: 'PROGRAM' ;
BEGIN: 'BEGIN' ;
END: 'END' ;
FUNCTION: 'FUNCTION' ;
RETURN: 'RETURN' ;
IF: 'IF' ;
ELSE: 'ELSE' ;
ENDIF: 'ENDIF' ;
WHILE: 'WHILE' ;
CONTINUE: 'CONTINUE' ;
BREAK: 'BREAK' ;
ENDWHILE: 'ENDWHILE' ;
READ: 'READ' ;
WRITE: 'WRITE' ;
VOID: 'VOID' ;
INT: 'INT' ;
FLOAT: 'FLOAT' ;
STRING: 'STRING' ;

FLOATLITERAL:   DIGIT* '.' DIGIT+;
INTLITERAL:     DIGIT+;
STRINGLITERAL:  '"' (~["\r\n])*? '"';
IDENTIFIER:     CHAR (DIGIT | CHAR)*;
COMMENT:        '--' ~[\r\n]*? '\r'? '\n' -> channel(HIDDEN) ;
WHITES:         [ \r\n\t]+ -> channel(HIDDEN) ;

fragment CHAR:  [a-zA-Z];
fragment DIGIT: [0-9];
