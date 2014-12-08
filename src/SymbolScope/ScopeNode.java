package SymbolScope;

import AST.AstNode;
import IR.IrCode;
import IR.IrNode;
import Nucleus.Operand;
import Nucleus.Symbol;
import Nucleus.Temporary;

import java.util.*;

public abstract class ScopeNode {
    SymbolScopeTree.ScopeType scopeType;
    ScopeNode parent;
    public String name = null;
    public Map<String, ScopeNode> children = new LinkedHashMap<String, ScopeNode>();
    public Map<String, Symbol> symbolTable =  new LinkedHashMap<String, Symbol>();
    public List<AstNode> statements = null;
    public IrCode irCode;

    public abstract Temporary getCurrentTemp();
    public abstract Temporary createTemp(Operand.DataType t);

    void addSymbol(Operand.DataType type, String id, String reference, String value, Operand.OperandType operandType) {
        if(symbolTable.containsKey(id)) {
            System.out.println("DECLARATION ERROR " + id);
            System.exit(0);
        }
        switch (type) {
            case INT: {
                Symbol s = new Symbol(type, reference, 0, operandType);
                symbolTable.put(id, s);
                break;
            }
            case FLOAT: {
                Symbol s = new Symbol(type, reference, 0.0f, operandType);
                symbolTable.put(id, s);
                break;
            }
            case STRING: {
                Symbol s = new Symbol(type, reference, value, operandType);
                symbolTable.put(id, s);
            }
        }
    }

    public abstract void addVariables(String type, List<String> ids);

    public abstract String addString(String id, String value);

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

    public abstract FunctionScopeNode getParentFunction();

    public abstract void generateIrCode();

    public abstract void printIrCode();

    public abstract void printTinyCode();

    protected void buildControlFlowGraph() {
        // build control flow graph
        ListIterator<IrNode> irNodeListIterator = irCode.irNodeList.listIterator();
        Map<String, IrNode> labelMap = new HashMap<String, IrNode>();
        IrNode p = null;
        if (irNodeListIterator.hasNext()) {
            p = irNodeListIterator.next();
            p.starter = true;
            if(p.isLabel()) {
                labelMap.put(p.label, p);
            }
        }
        while(irNodeListIterator.hasNext()) {
            IrNode c = irNodeListIterator.next();
            if(p.isReturn() || p.isJmp()) {
                // RET has no NEXT IrNode
                // JMP has a label jump
                c.starter = true;
                p.ender = true;
            }
            else {
                c.prevs.add(p);
                p.nexts.add(c);
            }
            if (c.isLabel()) {
                labelMap.put(c.label, c);
            }
            p = c;
        }
        for(IrNode n : irCode.irNodeList) {
            if(n.usesLabel()) {
                IrNode l = labelMap.get(n.label);
                if(n.nexts.size() == 1) {
                    n.nexts.get(0).starter = true;
                }
                if(l.prevs.size() == 1) {
                    l.prevs.get(0).ender = true;
                }
                l.starter = true;
                l.prevs.add(n);
                n.nexts.add(l);
                n.ender = true;
            }
        }
        // end building the control flow graph
    }
}