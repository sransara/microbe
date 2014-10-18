package SymbolScope;

import AST.AstNode;
import IR.IrCode;
import IR.IrNode;
import IR.LTypeIrNode;

import java.util.*;

public class SymbolScopeNode {
    SymbolScopeTree.ScopeType scopeType;
    SymbolScopeNode parent;
    VariableType returnType = null;
    public String name = null;
    public Map<String, SymbolScopeNode> children = new LinkedHashMap<String, SymbolScopeNode>();
    public Map<String, Symbol> symbolTable =  new LinkedHashMap<String, Symbol>();
    public List<AstNode> statements = null;
    public List<IrCode> irCodeList = new LinkedList<IrCode>();

    SymbolScopeNode(SymbolScopeTree.ScopeType scopeType) {
        this.scopeType = scopeType;
        this.parent = null;
    }

    SymbolScopeNode(SymbolScopeTree.ScopeType scopeType, SymbolScopeNode parent, String name) {
        this.scopeType = scopeType;
        this.parent = parent;
        this.name = name;
    }

    SymbolScopeNode(SymbolScopeTree.ScopeType scopeType, SymbolScopeNode parent, VariableType rt, String name) {
        this.scopeType = scopeType;
        this.parent = parent;
        this.returnType = rt;
        this.name = name;
    }

    private void addSymbol(VariableType type, String name, String value) {
        switch (type) {
            case INT: {
                Symbol<Integer> s = new Symbol<Integer>(type, name, 0);
                symbolTable.put(name, s);
                break;
            }
            case FLOAT: {
                Symbol<Float> s = new Symbol<Float>(type, name, 0.0f);
                symbolTable.put(name, s);
                break;
            }
            case STRING: {
                Symbol<String> s = new Symbol<String>(type, name, value);
                symbolTable.put(name, s);
            }
        }
    }

    public void addVariables(String type, List<String> names) {
        VariableType t = VariableType.valueOf(type);
        for (String name : names) {
            addVariable(t, name);
        }
    }

    public void addVariable(VariableType t, String name) {
        if(symbolTable.containsKey(name)) {
            System.out.println("DECLARATION ERROR " + name);
            System.exit(0);
        }
        addSymbol(t, name, null);
    }

    public void addVariable(String type, String name) {
        if(symbolTable.containsKey(name)) {
            System.out.println("DECLARATION ERROR " + name);
            System.exit(0);
        }
        VariableType t = VariableType.valueOf(type);
        addSymbol(t, name, null);
    }

    public void addVariable(String type, String name, String value) {
        if(symbolTable.containsKey(name)) {
            System.out.println("DECLARATION ERROR " + name);
            System.exit(0);
        }
        VariableType t = VariableType.valueOf(type);
        addSymbol(t, name, value);
    }

    public Symbol findSymbol(String id) {
        Symbol<?> s = null;
        if(symbolTable.containsKey(id)) {
            s = symbolTable.get(id);
        }
        else if(parent != null) {
            s = parent.findSymbol(id);
        }
        return s;
    }

    public void generateIrCode() {
        IrCode tempc = new IrCode();
        tempc.irNodeList.add(new LTypeIrNode(IrNode.Opcode.LABEL, name));
        irCodeList.add(tempc);

        for(AstNode n : statements) {
            irCodeList.add(n.generateIrCode());
        }
    }

    public void printSymbols() {
        Set<String> symbolNames = symbolTable.keySet();
        for (String symbolName : symbolNames) {
            System.out.println(symbolTable.get(symbolName));
        }
        System.out.println("");
    }

}