grammar Microbe;

@header {
    import java.util.LinkedList;
    import SymbolScope.*;
    import AST.*;
}

@members {
    public SymbolScopeTree sst = new SymbolScopeTree();
}

// implicitly instantiates SymbolScope.SymbolScopeTree: sst
program: PROGRAM id BEGIN pgm_body END ;

id: IDENTIFIER ;

pgm_body: decl func_declarations ;

decl: string_decl decl | var_decl decl | ;

/* Global String Declaration */
string_decl: STRING id ASSIGN str SEMICOLON
    {
        sst.currentScope.addVariable($STRING.text, $id.text, $str.text);
    };

str: STRINGLITERAL ;

/* Variable Declaration */
var_decl: var_type id_list SEMICOLON
    {
        sst.currentScope.addVariables($var_type.text, $id_list.ids);
    };

var_type: FLOAT | INT ;

any_type: var_type | VOID ;

id_list returns [List<String> ids]: id id_tail
    {
        $ids = $id_tail.ids;
        $ids.add(0, $id.text);
    };

id_tail returns [List<String> ids]:
    COMMA id
    t = id_tail
        {
            $ids = $t.ids;
            $ids.add(0, $id.text);
        }
    |
        {
            // base case
            $ids = new LinkedList<String>();
        }
    ;

/* Function Paramater List */
param_decl_list: param_decl param_decl_tail | ;

param_decl: var_type id
    {
        sst.currentScope.addVariable($var_type.text, $id.text);
    };

param_decl_tail: COMMA param_decl param_decl_tail | ;

/* Function Declarations */
func_declarations: func_decl func_declarations | ;
func_decl:
    FUNCTION any_type id
    {
        sst.enterScope($any_type.text, $id.text);
    }
    OPAREN param_decl_list CPAREN
    BEGIN
    func_body
    END
    {
        sst.exitScope();
    }
    ;
func_body: decl stmt_list
    {
        sst.currentScope.statements = $stmt_list.stmts;
    };

/* Complex Statements and Condition */
if_stmt returns [AstNode node]:
    IF OPAREN cond CPAREN
    {
        sst.enterScope();
        $node = new IfAstNode(sst.currentScope.name, $cond.node, null, null);
    }
    decl
    stmt_list
    {
        ((IfAstNode)$node).then = $stmt_list.stmts;
        sst.exitScope();
    }
    else_part
    ENDIF
    {
        ((IfAstNode)$node).otherwise = $else_part.node;
    }
    ;

else_part returns [IfAstNode node]:
    ELSE
    {
        sst.enterScope();
        $node = new IfAstNode(sst.currentScope.name, null, null, null);
    }
    decl
    stmt_list
    {
        ((IfAstNode)$node).then = $stmt_list.stmts;
        sst.exitScope();
    }
    | ;

while_stmt returns [AstNode node]:
    WHILE OPAREN cond CPAREN
    {
        sst.enterScope();
        $node = new WhileAstNode(sst.currentScope.name, $cond.node, null);
    }
    decl
    stmt_list
    {
        ((WhileAstNode)$node).then = $stmt_list.stmts;
    }
    ENDWHILE
    {
         sst.exitScope();
    }
    ;

cond  returns [AstNode node]: t1 = expr compop t2 = expr
    {
        AstNode left = $t1.node;
        AstNode right = $t2.node;
        $node = new ConditionOpAstNode($compop.optype, left, right);
    };

compop  returns [ConditionOpAstNode.OpType optype]:
    GT
        {
            $optype = ConditionOpAstNode.OpType.GT;
        }
    |
    LT
        {
            $optype = ConditionOpAstNode.OpType.LT;
        }
    |
    EQUALS
        {
            $optype = ConditionOpAstNode.OpType.EQ;
        }
    |
    NEQUALS
        {
            $optype = ConditionOpAstNode.OpType.NE;
        }
    |
    GTE
        {
            $optype = ConditionOpAstNode.OpType.GE;
        }
    |
    LTE
        {
            $optype = ConditionOpAstNode.OpType.LE;
        }
    ;


/* Statement List */
stmt_list returns [List<AstNode> stmts]:
    stmt t = stmt_list
        {
            $stmts = $t.stmts;
            $stmts.add(0, $stmt.ast_root_node);
        }
    |
        {
            // base case
            $stmts = new LinkedList<AstNode>();
        }
    ;

stmt returns [AstNode ast_root_node]:
    base_stmt
        {
            $ast_root_node = $base_stmt.node;
        }
    |
    if_stmt
        {
            $ast_root_node = $if_stmt.node;
        }
    |
    while_stmt
        {
            $ast_root_node = $while_stmt.node;
        }
    ;

base_stmt returns [AstNode node]:
    assign_stmt
        {
            $node = $assign_stmt.node;
        }
    |
    read_stmt
        {
            $node = $read_stmt.node;
        }
    |
    write_stmt
        {
            $node = $write_stmt.node;
        }
    |
    return_stmt
        {
            $node = $return_stmt.node;
        }
    ;


/* Basic Statements */
assign_stmt returns [AstNode node]:
    assign_expr
    {
        $node = $assign_expr.node;
    }
    SEMICOLON ;
assign_expr returns [AstNode node]: id ASSIGN expr
    {
        AstNode left = new IdentifierAstNode($id.text);
        AstNode right = $expr.node;
        $node = new BinaryOpAstNode(BinaryOpAstNode.OpType.ASSIGN, left, right);
    };
read_stmt returns [AstNode node]: READ OPAREN id_list CPAREN SEMICOLON
    {
        $node = new SystemOpAstNode(SystemOpAstNode.OpType.READ, $id_list.ids);
    };
write_stmt returns [AstNode node]: WRITE OPAREN id_list CPAREN SEMICOLON
    {
        $node = new SystemOpAstNode(SystemOpAstNode.OpType.WRITE, $id_list.ids);
    };
return_stmt returns [AstNode node]: RETURN expr SEMICOLON
    {
        $node = $expr.node;
    };

/* Expressions */
expr  returns [AstNode node]: expr_prefix factor
    {
        if($expr_prefix.node != null) {
            ((BinaryOpAstNode)($expr_prefix.node)).right = $factor.node;
            $node = $expr_prefix.node;
        }
        else {
            $node = $factor.node;
        }
    };

expr_prefix returns [AstNode node]: t = expr_prefix factor addop
    {
        if($t.node != null) {
            ((BinaryOpAstNode)($t.node)).right = $factor.node;
            $node = new BinaryOpAstNode($addop.optype, $t.node, null);
        }
        else {
            $node = new BinaryOpAstNode($addop.optype, $factor.node, null);
        }
    }
    | ;

factor returns [AstNode node]: factor_prefix postfix_expr
    {
        if($factor_prefix.node != null) {
            ((BinaryOpAstNode)($factor_prefix.node)).right = $postfix_expr.node;
            $node = $factor_prefix.node;
        }
        else {
            $node = $postfix_expr.node;
        }
    };

factor_prefix returns [AstNode node]: t = factor_prefix postfix_expr mulop
    {
        if($t.node != null) {
            ((BinaryOpAstNode)($t.node)).right = $postfix_expr.node;
            $node = new BinaryOpAstNode($mulop.optype, $t.node, null);
        }
        else {
            $node = new BinaryOpAstNode($mulop.optype, $postfix_expr.node, null);
        }
    }
    | ;

postfix_expr returns [AstNode node]:
    primary
        {
            $node = $primary.node;
        }
    |
    call_expr
        {
            $node = $call_expr.node;
        }
    ;

call_expr returns [AstNode node]:  id OPAREN expr_list CPAREN;

expr_list: expr expr_list_tail | ;

expr_list_tail: COMMA expr expr_list_tail | ;

primary returns [AstNode node]:
    OPAREN expr CPAREN
        {
            $node = $expr.node;
        }
    |
    id
        {
            $node = new IdentifierAstNode($id.text);
        }
    |
    INTLITERAL
        {
            $node = new LiteralAstNode($INTLITERAL.text, VariableType.FLOAT);
        }
    |
    FLOATLITERAL
        {
            $node = new LiteralAstNode($FLOATLITERAL.text, VariableType.FLOAT);
        }
    ;

addop returns [BinaryOpAstNode.OpType optype]:
    ADD
        {
            $optype = BinaryOpAstNode.OpType.ADD;
        }
    |
    MINUS
        {
            $optype = BinaryOpAstNode.OpType.MINUS;
        }
    ;
mulop returns [BinaryOpAstNode.OpType optype]:
    MULTIPLY
        {
            $optype = BinaryOpAstNode.OpType.MULTIPLY;
        }
    |
    DIVIDE
        {
            $optype = BinaryOpAstNode.OpType.DIVIDE;
        }
    ;

ASSIGN: ':=' ;
SEMICOLON: ';' ;
ADD: '+' ;
MINUS: '-' ;
MULTIPLY: '*' ;
DIVIDE: '/' ;
LT: '<' ;
GT: '>' ;
LTE: '<=' ;
GTE: '>=' ;
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
