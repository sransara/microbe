package SymbolScope;

import AST.AstNode;
import IR.IrCode;
import IR.IrNode;
import IR.LTypeIrNode;
import Nucleus.Operand;
import Nucleus.Temporary;

import java.util.List;

public class BlockScopeNode extends ScopeNode {
    BlockScopeNode(ScopeNode parent, String name) {
        this.scopeType = SymbolScopeTree.ScopeType.BLOCK;
        this.parent = parent;
        this.name = name;
    }

    @Override
    public Temporary getCurrentTemp() {
        ScopeNode f = parent;
        while(f.scopeType != SymbolScopeTree.ScopeType.FUNCTION) {
            f = f.parent;
        }
        return f.getCurrentTemp();
    }

    @Override
    public Temporary createTemp(Operand.DataType t) {
        ScopeNode f = parent;
        while(f.scopeType != SymbolScopeTree.ScopeType.FUNCTION) {
            f = f.parent;
        }
        return f.createTemp(t);
    }

    @Override
    public void addVariables(String type, List<String> ids) {
        Operand.DataType t = Operand.DataType.valueOf(type);

        ScopeNode f = parent;
        while(f.scopeType != SymbolScopeTree.ScopeType.FUNCTION) {
            f = f.parent;
        }

        for (String id : ids) {
            String idf = String.format("%s.%s", this.name, id);
            String ref = ((FunctionScopeNode)f).addVariable(t, idf, null);
            addSymbol(t, id, ref, null, Operand.OperandType.SYMBOL);
        }
    }

    @Override
    public String addString(String id, String value) {
        String ref = String.format("%s.%s", this.name, id);
        Operand.DataType t = Operand.DataType.STRING;
        // reference and reference are the same for global variables
        addSymbol(t, id, ref, value, Operand.OperandType.SYMBOL);

        // add it to the global scope to put it in data segment
        ScopeNode g = this;
        while(g.parent != null) {
            g = g.parent;
        }
        // reference and reference are the same for global variables
        ((GlobalScopeNode)g).addString(ref, value);
        return ref;
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
        irCode.irNodeList.add(new LTypeIrNode(IrNode.Opcode.LABEL, getParentFunction(),name));
        for(AstNode n : statements) {
            irCode.irNodeList.addAll(n.generateIrCode(this).irNodeList);
        }
    }

    @Override
    public void printIrCode() {
        irCode.printIrCode();
    }

    @Override
    public void printTinyCode() {
        irCode.printTinyCode();
    }
}