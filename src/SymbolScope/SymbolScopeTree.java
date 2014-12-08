package SymbolScope;

import Nucleus.Operand;

public class SymbolScopeTree {
    static enum ScopeType {
        GLOBAL, BLOCK, FUNCTION
    }

    public static ScopeNode GlobalScope;
    public ScopeNode currentScope;
    private int block_x = 1;

    public SymbolScopeTree() {
        currentScope = new GlobalScopeNode(ScopeType.GLOBAL);
        GlobalScope = currentScope;
    }

    public void exitScope() {
        currentScope = currentScope.parent;
    }

    public void enterScope(String rt, String name) {
        Operand.DataType returnType = Operand.DataType.valueOf(rt);
        ScopeNode ns = new FunctionScopeNode(currentScope, returnType, name);
        currentScope.children.put(name, ns);
        currentScope = ns;
    }

    public void enterScope() {
        String name = "BLOCK_" + block_x;
        block_x++;
        ScopeNode ns = new BlockScopeNode(currentScope, name);
        currentScope.children.put(name, ns);
        currentScope = ns;
    }
}
