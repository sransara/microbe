package IR;

import Nucleus.Operand;
import SymbolScope.FunctionScopeNode;

public class RTypeIrNode extends IrNode{
    Operand op1;
    Operand op2;
    Operand result;

    public RTypeIrNode(Opcode opcode, FunctionScopeNode scope, Operand op1, Operand op2, Operand result) {
        super(opcode, scope);
        this.op1 = op1;
        this.op2 = op2;
        this.result = result;

        initGen(op1, op2);
        initKill(result);
    }

    @Override
    public String toString() {
        return "; " + opcode.name() + " " + op1.reference + " " + op2.reference + " " + result.reference;
    }

    @Override
    public String toTiny() {
        if (!isStarter()) {
            registers = prevs.get(0).registers;
        }

        String rop1 = ensureRegister(op1, op2);
        // dropDeadRegisters(rop1);
        String op1Ref = rop1 == null ? op1.reference : rop1.toString();
        String rResult = allocateRegister(result, op2);
        dirtRegister(rResult);

        if(!op1Ref.equals(rResult)) {
            tinyCode.append("move ");
            tinyCode.append(op1Ref + " ");
            tinyCode.append(rResult + "\n");
        }

        String rop2 = ensureRegister(op2, result);
        if(op2.equals(op1)) {
            rop2 = rop1;
        }
        dropDeadRegisters(rop1, rop2);
        String op2Ref = rop2 == null ? op2.reference : rop2.toString();

        switch (opcode) {
            case ADDF:
                tinyCode.append("addr ");
                break;
            case ADDI:
                tinyCode.append("addi ");
                break;
            case SUBF:
                tinyCode.append("subr ");
                break;
            case SUBI:
                tinyCode.append("subi ");
                break;
            case MULTF:
                tinyCode.append("mulr ");
                break;
            case MULTI:
                tinyCode.append("muli ");
                break;
            case DIVF:
                tinyCode.append("divr ");
                break;
            case DIVI:
                tinyCode.append("divi ");
                break;
        }
        tinyCode.append(op2Ref + " " + rResult + "\n");
        if (isEnder()) {
            restoreRegisteredVariables();
        }
        return tinyCode.toString();
    }
}
