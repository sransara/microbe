package SymbolScope;

import AST.AstNode;
import IR.IrCode;
import IR.IrNode;
import IR.LTypeIrNode;
import IR.STypeIrNode;
import Nucleus.Operand;
import Nucleus.Temporary;

import java.util.List;

public class FunctionScopeNode extends ScopeNode {
    public Operand.DataType returnType = null;
    private Temporary currentTemp = null;
    public int local_x = 1;
    public int parameter_x = 1;
    public int temp_x = 1;

    FunctionScopeNode(ScopeNode parent, Operand.DataType rt, String name) {
        super(SymbolScopeTree.ScopeType.FUNCTION);
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
        addSymbol(t, id, ref, value);
        local_x++;
        return ref;
    }

    public String addParameter(String type, String id) {
        String ref = String.format("$P%d", parameter_x);
        Operand.DataType t = Operand.DataType.valueOf(type);
        addSymbol(t, id, ref, null);
        parameter_x += 1;
        return ref;
    }

    @Override
    public String addString(String id, String value) {
        String ref = String.format("%s.%s", this.name, id);
        Operand.DataType t = Operand.DataType.STRING;
        // reference and reference are the same for global variables
        addSymbol(t, id, ref, value);
        super.addString(ref, value);
        return ref;
    }

    public void generateIrCode() {
        irCode = new IrCode();
        irCode.irNodeList.add(new LTypeIrNode(IrNode.Opcode.LABEL, name));
        irCode.irNodeList.add(new STypeIrNode(IrNode.Opcode.LINK, null, local_x));
        for(AstNode n : statements) {
            irCode.irNodeList.addAll(n.generateIrCode(this).irNodeList);
        }
        if(returnType == Operand.DataType.VOID){
            irCode.irNodeList.add(new STypeIrNode(IrNode.Opcode.RET, null, 0));
        }
    }
}