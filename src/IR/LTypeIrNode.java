package IR;

import SymbolScope.FunctionScopeNode;

import java.util.Collections;

public class LTypeIrNode extends IrNode{
    // It's just a label

    public LTypeIrNode(Opcode opcode, FunctionScopeNode scope, String label) {
        super(opcode, scope);
        this.label = label;
    }

    @Override
    public String toString() {
        return "; " + opcode.name() + " " + label;
    }

    @Override
    public String toTiny() {
        if (!isStarter()) {
            registers = prevs.get(0).registers;
        }
        if (isEnder()) {
            restoreRegisteredVariables();
        }
        tinyCode.append("label " + label + "\n");
        return tinyCode.toString();
    }
}
