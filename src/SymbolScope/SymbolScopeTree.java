package SymbolScope;

import java.util.Set;

public class SymbolScopeTree {
    static enum ScopeType {
        GLOBAL, BLOCK, FUNCTION
    }

    public SymbolScopeNode GlobalScope = new SymbolScopeNode(ScopeType.GLOBAL);
    public SymbolScopeNode currentScope;
    private int blockx = 1;

    public SymbolScopeTree() {
        currentScope = GlobalScope;
    }

    public void exitScope() {
        currentScope = currentScope.parent;
    }

    public void enterScope(String rt, String name) {
        VariableType returnType = VariableType.valueOf(rt);
        SymbolScopeNode ns = new SymbolScopeNode(ScopeType.FUNCTION, currentScope, returnType, name);
        currentScope.children.put(name, ns);
        currentScope = ns;
    }

    public void enterScope() {
        String name = "BLOCK_" + blockx; blockx++;
        SymbolScopeNode ns = new SymbolScopeNode(ScopeType.BLOCK, currentScope, name);
        currentScope.children.put(name, ns);
        currentScope = ns;
    }

    public void printTree(String scopeName, SymbolScopeNode cs) {
        System.out.println("Symbol table " + scopeName);
        cs.printSymbols();
        Set<String> scopeNames = cs.children.keySet();
        for(String sn : scopeNames) {
            printTree(sn, cs.children.get(sn));
        }
    }
}
