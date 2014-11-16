package SymbolScope;

import AST.AstNode;
import IR.*;
import Nucleus.Operand;
import Nucleus.Symbol;
import Nucleus.Temporary;

import java.util.*;

public class ScopeNode {
    SymbolScopeTree.ScopeType scopeType;
    ScopeNode parent;
    public String name = null;
    public Map<String, ScopeNode> children = new LinkedHashMap<String, ScopeNode>();
    public Map<String, Symbol> symbolTable =  new LinkedHashMap<String, Symbol>();
    public List<AstNode> statements = null;
    public IrCode irCode;

    ScopeNode(SymbolScopeTree.ScopeType scopeType) {
        this.scopeType = scopeType;
        this.parent = null;
        this.name = "GLOBAL";
    }

    public Temporary getCurrentTemp() { return null; }
    public Temporary createTemp(Operand.DataType t) { return null; }

    void addSymbol(Operand.DataType type, String id, String reference, String value) {
        if(symbolTable.containsKey(id)) {
            System.out.println("DECLARATION ERROR " + id);
            System.exit(0);
        }
        switch (type) {
            case INT: {
                Symbol s = new Symbol(type, reference, 0);
                symbolTable.put(id, s);
                break;
            }
            case FLOAT: {
                Symbol s = new Symbol(type, reference, 0.0f);
                symbolTable.put(id, s);
                break;
            }
            case STRING: {
                Symbol s = new Symbol(type, reference, value);
                symbolTable.put(id, s);
            }
        }
    }

    public void addVariables(String type, List<String> ids) {
        Operand.DataType t = Operand.DataType.valueOf(type);
        for (String id : ids) {
            // reference and reference are the same for global variables
            addSymbol(t, id, id, null);
        }
    }

    public String addString(String id, String value) {
        Operand.DataType t = Operand.DataType.STRING;
        ScopeNode g = this;
        while(g.parent != null) {
            g = g.parent;
        }

        // reference and reference are the same for global variables
        g.addSymbol(t, id, id, value);
        return id;
    }

    public Symbol findSymbol(String id) {
        Symbol s = null;
        if(symbolTable.containsKey(id)) {
            s = symbolTable.get(id);
        }
        else if(parent != null) {
            s = parent.findSymbol(id);
        }
        return s;
    }

    public FunctionScopeNode getParentFunction() {
        ScopeNode f = this;
        if(f.parent == null) {
            return null;
        }
        while(f.scopeType != SymbolScopeTree.ScopeType.FUNCTION) {
            f = f.parent;
        }
        return (FunctionScopeNode)f;
    }

    public void generateIrCode() {
        irCode = new IrCode();
        irCode.irNodeList.add(new STypeIrNode(IrNode.Opcode.PUSH, null, 0));
        irCode.irNodeList.add(new JTypeIrNode(IrNode.Opcode.JSR, "main"));
        irCode.irNodeList.add(new ITypeIrNode(IrNode.Opcode.HALT, null));
    }
}