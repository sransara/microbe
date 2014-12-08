package IR;

import Nucleus.Register;
import SymbolScope.FunctionScopeNode;

import java.util.Collections;

public class JTypeIrNode extends IrNode{
    public JTypeIrNode(Opcode opcode, FunctionScopeNode scope, String label) {
        // Kind of dump to do it this way because there's only one IR Opcode for JUMP
        // but let's stick with it for easy extension and consistency
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
        switch (opcode) {
            case JUMP:
                tinyCode.append("jmp " + label + "\n");
                break;
            case JSR:
                restoreRegisteredGlobalVariables();
                saveRegisters();
                tinyCode.append("jsr " + label + "\n");
                restoreRegisters();
                break;
        }
        return tinyCode.toString();
    }

    private void saveRegisters() {
        for(int i = 0; i < Register.REG_N; i++) {
            tinyCode.append("push " + Register.getReference(i) + "\n");
        }
    }

    private void restoreRegisters() {
        for(int i = Register.REG_N - 1; i >= 0; i--) {
            tinyCode.append("pop " + Register.getReference(i) + "\n");
        }
    }
}
