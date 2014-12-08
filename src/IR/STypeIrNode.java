package IR;

import Nucleus.Operand;
import Nucleus.Register;
import SymbolScope.FunctionScopeNode;

import java.util.Collections;

public class STypeIrNode extends IrNode{
    // the stack stuff
    public Operand operand;

    public STypeIrNode(Opcode opcode, FunctionScopeNode scope, Operand operand) {
        super(opcode, scope);
        this.operand = operand;

        if (opcode == Opcode.PUSH) {
            initGen(operand);
        }
        else if (opcode == Opcode.POP) {
            initKill(operand);
        }
    }

    @Override
    public String toString() {
        String c = null;
        switch (opcode) {
            case LINK:
                c = "; LINK";
                break;
            case RET:
                c = "; RET";
                break;
            case PUSH:
            case POP:// PUSH POP
                c = "; " + opcode.name() + " ";
                if(operand != null) {
                    c += operand.reference;
                }
                break;
        }
        return c;
    }

    @Override
    public String toTiny() {
        if (!isStarter()) {
            registers = prevs.get(0).registers;
        }

        String c = null;
        switch (opcode) {
            case LINK:
                c = "link " + functionScope.size;
                break;
            case RET:
                c = "unlnk \n";
                c += "ret";
                break;
            case PUSH:
                if (operand != null) {
                    String rop = ensureRegister(operand);
                    dropDeadRegisters(null, rop);
                    String opRef = rop == null ? operand.reference : rop.toString();
                    c = "push " + opRef;
                } else {
                    c = "push";
                }
                break;
            case POP:// PUSH POP
                if (operand != null) {
                    String rResult = allocateRegister(operand);
                    dirtRegister(rResult);
                    c = "pop " + rResult;
                } else {
                    c = "pop";
                }
                break;
        }
        if (isEnder()) {
            restoreRegisteredVariables();
        }
        tinyCode.append(c + "\n");
        return tinyCode.toString();
    }
}
