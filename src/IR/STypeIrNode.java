package IR;

import Nucleus.Operand;
import SymbolScope.FunctionScopeNode;

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

        switch (opcode) {
            case LINK:
                tinyCode.append("link " + functionScope.local_x + "\n");
                break;
            case RET:
                restoreRegisteredGlobalVariables();
                tinyCode.append("unlnk\n");
                tinyCode.append("ret\n");
                break;
            case PUSH:
                if (operand != null) {
                    String rop = ensureRegister(operand);
                    dropDeadRegisters(rop);
                    String opRef = rop == null ? operand.reference : rop;
                    tinyCode.append("push " + opRef + "\n");
                } else {
                    tinyCode.append("push\n");
                }
                if (isEnder()) {
                    restoreRegisteredVariables();
                }
                break;
            case POP:// PUSH POP
                if (operand != null) {
                    String rResult = allocateRegister(operand);
                    dirtRegister(rResult);
                    tinyCode.append("pop " + rResult + "\n");
                } else {
                    tinyCode.append("pop\n");
                }
                if (isEnder()) {
                    restoreRegisteredVariables();
                }
                break;
        }
        return tinyCode.toString();
    }
}
