package SymbolScope;

import IR.*;
import Nucleus.Operand;
import Nucleus.Temporary;
import java.util.List;
import java.util.Set;

public class GlobalScopeNode extends ScopeNode {
    GlobalScopeNode(SymbolScopeTree.ScopeType scopeType) {
        this.scopeType = scopeType;
        this.parent = null;
        this.name = "GLOBAL_SYMBOL";
    }

    @Override
    public Temporary getCurrentTemp() { return null; }

    @Override
    public Temporary createTemp(Operand.DataType t) { return null; }

    @Override
    public void addVariables(String type, List<String> ids) {
        Operand.DataType t = Operand.DataType.valueOf(type);
        for (String id : ids) {
            // reference and reference are the same for global variables
            addSymbol(t, id, id, null, Operand.OperandType.GLOBAL_SYMBOL);
        }
    }

    @Override
    public String addString(String id, String value) {
        Operand.DataType t = Operand.DataType.STRING;
        // id and reference are the same for global variables
        addSymbol(t, id, id, value, Operand.OperandType.GLOBAL_SYMBOL);
        return id;
    }

    @Override
    public FunctionScopeNode getParentFunction() {
        return null;
    }

    @Override
    public void generateIrCode() {
        irCode = new IrCode();
        irCode.irNodeList.add(new STypeIrNode(IrNode.Opcode.PUSH, null, null));
        irCode.irNodeList.add(new JTypeIrNode(IrNode.Opcode.JSR, null, "main"));
        irCode.irNodeList.add(new ITypeIrNode(IrNode.Opcode.HALT, null, null));
        buildControlFlowGraph();
        // rest of them
        Set<String> keys = children.keySet();
        for(String key: keys) {
            children.get(key).generateIrCode();
        }
    }

    @Override
    public void printIrCode() {
        irCode.printIrCode();
        // rest of them
        Set<String> keys = children.keySet();
        for(String key: keys) {
            children.get(key).printIrCode();
        }
    }

    @Override
    public void printTinyCode() {
        Set<String> symbolNames = symbolTable.keySet();
        for (String symbolName : symbolNames) {
            System.out.println(symbolTable.get(symbolName));
        }
        irCode.printTinyCode();
        Set<String> keys = children.keySet();
        for(String key: keys) {
            children.get(key).printTinyCode();
        }
        System.out.println("end");
    }
}