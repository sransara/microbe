package SymbolScope;

import AST.AstNode;
import IR.IrCode;
import IR.IrNode;
import IR.LTypeIrNode;
import IR.STypeIrNode;
import Nucleus.Operand;
import Nucleus.Register;
import Nucleus.Symbol;
import Nucleus.Temporary;

import java.util.*;

public class FunctionScopeNode extends ScopeNode {
    public Operand.DataType returnType = null;
    private Temporary currentTemp = null;
    public int size;
    public int local_x = 1;
    public int parameter_x = 1;
    public int temp_x = 1;
    private IrCode functionHeader;

    FunctionScopeNode(ScopeNode parent, Operand.DataType rt, String name) {
        this.scopeType = SymbolScopeTree.ScopeType.FUNCTION;
        this.parent = parent;
        this.returnType = rt;
        this.name = name;
    }

    public Temporary getCurrentTemp() {
        return currentTemp;
    }

    public Temporary createTemp(Operand.DataType t) {
        currentTemp = new Temporary(t, temp_x);
        temp_x++;
        return currentTemp;
    }

    @Override
    public void addVariables(String type, List<String> ids) {
        Operand.DataType t = Operand.DataType.valueOf(type);
        for (String id : ids) {
            addVariable(t, id, null);
        }
    }

    public String addVariable(Operand.DataType t, String id, String value) {
        String ref =  String.format("$L%d", local_x);
        addSymbol(t, id, ref, value, Operand.OperandType.SYMBOL);
        local_x++;
        return ref;
    }

    public String addParameter(String type, String id) {
        String ref = String.format("$P%d", parameter_x);
        Operand.DataType t = Operand.DataType.valueOf(type);
        addSymbol(t, id, ref, null, Operand.OperandType.SYMBOL);
        parameter_x += 1;
        return ref;
    }

    @Override
    public String addString(String id, String value) {
        String ref = String.format("%s.%s", this.name, id);
        Operand.DataType t = Operand.DataType.STRING;
        addSymbol(t, id, ref, value, Operand.OperandType.SYMBOL);
        // add it to the global scope to put it in data segment
        ScopeNode g = this;
        while(g.parent != null) {
            g = g.parent;
        }
        // reference and reference are the same for global variables
        g.addString(ref, value);
        return ref;
    }

    @Override
    public FunctionScopeNode getParentFunction() {
        return this;
    }

    private void generateFunctionHeader() {
        functionHeader = new IrCode();
        IrNode label = new LTypeIrNode(IrNode.Opcode.LABEL, this, name);
        IrNode link = new STypeIrNode(IrNode.Opcode.LINK, this, null);
        label.starter = true;
        label.ender = true;
        label.nexts.add(link);
        link.prevs.add(label);
        functionHeader.irNodeList.add(label);
        functionHeader.irNodeList.add(link);
    }
    @Override
    public void generateIrCode() {
        generateFunctionHeader();
        irCode = new IrCode();
        for(AstNode n : statements) {
            irCode.irNodeList.addAll(n.generateIrCode(this).irNodeList);
        }
        if(returnType == Operand.DataType.VOID){
            irCode.irNodeList.add(new STypeIrNode(IrNode.Opcode.RET, this, null));
        }
        buildControlFlowGraph();

        // do liveness analysis
        Queue<IrNode> workingList = new ArrayDeque<IrNode>();
        for(IrNode n : irCode.irNodeList) {
            if(n.isJsr()) {
                for(Symbol s : parent.symbolTable.values()) {
                    if(s.dataType != Operand.DataType.STRING) {
                        n.gen.add(s);
                    }
                }
            }
            else if(n.isReturn()) {
                for(Symbol s : parent.symbolTable.values()) {
                    if(s.dataType != Operand.DataType.STRING) {
                        n.liveOut.add(s);
                    }
                }
            }
            workingList.add(n);
        }
        while(!workingList.isEmpty()) {
            IrNode e = workingList.remove();
            for (IrNode n : e.nexts) {
                e.liveOut.addAll(n.liveIn);
            }
            Set<Operand> tLiveIn = new HashSet<Operand>();
            tLiveIn.addAll(e.liveOut);
            tLiveIn.removeAll(e.kill);
            tLiveIn.addAll(e.gen);
            if (!tLiveIn.equals(e.liveIn)) {
                e.liveIn = tLiveIn;
                for (IrNode prev : e.prevs) {
                    workingList.add(prev);
                }
            }
        }
        // end liveness analysis
    }

    @Override
    public void printIrCode() {
        functionHeader.printIrCode();
        irCode.printIrCode();
    }

    @Override
    public void printTinyCode() {
        irCode.printTinyCode(functionHeader);
    }
}