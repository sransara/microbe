lexer grammar MicroParser;

tokens { KEYWORD, OPERATOR }

KEYWORD
    :   'PROGRAM' | 'BEGIN' | 'END'
    |   'FUNCTION' | 'RETURN'
    |   'READ' | 'WRITE'
    |   'IF' | 'ELSE' | 'ENDIF'
    |   'WHILE' | 'ENDWHILE'
    |   'CONTINUE' | 'BREAK'
    |   'VOID' | 'INT' | 'FLOAT' | 'STRING'
    ;

OPERATOR
    :   ':='
    |   '+' | '-' | '*' | '/'
    |   '=' | '!=' | '<' | '>' | '<=' | '>='
    |   '(' | ')' | ','
    |   ';'
    ;

IDENTIFIER:     CHAR (DIGIT | CHAR)*;
FLOATLITERAL:   DIGIT* '.' DIGIT+;
INTLITERAL:     DIGIT+;
STRINGLITERAL:  '"' (~'"')*? '"';
COMMENT:        '--' ~[\r\n]*? '\r'? '\n' -> skip ;
WS:             [ \r\n\t]+ -> skip ;

fragment CHAR:  [a-zA-Z];
fragment DIGIT: [0-9];
