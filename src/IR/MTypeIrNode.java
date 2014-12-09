package IR;

import Nucleus.Operand;
import SymbolScope.FunctionScopeNode;

public class MTypeIrNode extends IrNode{
    // STOREI, STOREF

    Operand op1;
    Operand result;

    public MTypeIrNode(Opcode opcode, FunctionScopeNode scope, Operand op1, Operand result) {
        super(opcode, scope);
        this.op1 = op1;
        this.result = result;

        initGen(op1);
        initKill(result);
    }

    @Override
    public String toString() {
        return "; " + opcode.name() + " " + op1.reference + " " + result.reference;
    }

    @Override
    public String toTiny() {
        if (!isStarter()) {
            registers = prevs.get(0).registers;
        }
        String rop1 = ensureRegister(op1);
        dropDeadRegisters(rop1);
        String op1Ref = rop1 == null ? op1.reference : rop1;
        String rResult;
        if(result.reference.startsWith("$R")) {
            rResult = operandToTiny(result);
        }
        else {
            rResult = allocateRegister(result);
            dirtRegister(rResult);
        }
        if(op1Ref.equals(rResult)) {
            tinyCode.append("; ");
        }
        tinyCode.append("move " + op1Ref + " " + rResult + "\n");
        if (isEnder()) {
            restoreRegisteredVariables();
        }
        return tinyCode.toString();
    }
}
