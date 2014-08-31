lexer grammar MicroScanner;
tokens { KEYWORD, OPERATOR }

ASSIGN: ':=' -> type(OPERATOR);
SEMICOLON: ';' -> type(OPERATOR);
ADD: '+' -> type(OPERATOR);
MINUS: '-' -> type(OPERATOR);
MULTIPLY: '*' -> type(OPERATOR);
DIVIDE: '/' -> type(OPERATOR);
GT: '<' -> type(OPERATOR);
LT: '>' -> type(OPERATOR);
GTE: '<=' -> type(OPERATOR);
LTE: '>=' -> type(OPERATOR);
EQUALS: '=' -> type(OPERATOR);
NEQUALS: '!=' -> type(OPERATOR);
LPAREN: '(' -> type(OPERATOR);
RPAREN: ')' -> type(OPERATOR);
COMMA: ',' -> type(OPERATOR);

PROGRAM: 'PROGRAM' -> type(KEYWORD);
BEGIN: 'BEGIN' -> type(KEYWORD);
END: 'END' -> type(KEYWORD);
FUNCTION: 'FUNCTION' -> type(KEYWORD);
RETURN: 'RETURN' -> type(KEYWORD);
IF: 'IF' -> type(KEYWORD);
ELSE: 'ELSE' -> type(KEYWORD);
ENDIF: 'ENDIF' -> type(KEYWORD);
WHILE: 'WHILE' -> type(KEYWORD);
CONTINUE: 'CONTINUE' -> type(KEYWORD);
BREAK: 'BREAK' -> type(KEYWORD);
ENDWHILE: 'ENDWHILE' -> type(KEYWORD);
READ: 'READ' -> type(KEYWORD);
WRITE: 'WRITE' -> type(KEYWORD);
VOID: 'VOID' -> type(KEYWORD);
INT: 'INT' -> type(KEYWORD);
FLOAT: 'FLOAT' -> type(KEYWORD);
STRING: 'STRING' -> type(KEYWORD);

IDENTIFIER:     CHAR (DIGIT | CHAR)*;
FLOATLITERAL:   DIGIT* '.' DIGIT+;
INTLITERAL:     DIGIT+;
STRINGLITERAL:  '"' (~'"')*? '"';
COMMENT:        '--' ~[\r\n]*? '\r'? '\n' -> channel(HIDDEN) ;
WS:             [ \r\n\t]+ -> channel(HIDDEN) ;

fragment CHAR:  [a-zA-Z];
fragment DIGIT: [0-9];

