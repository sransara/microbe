package IR;

import Nucleus.Operand;
import Nucleus.Register;
import SymbolScope.FunctionScopeNode;

import java.util.Collections;
import java.util.LinkedList;

import static Nucleus.Operand.DataType;
import static Nucleus.Operand.DataType.FLOAT;
import static Nucleus.Operand.DataType.INT;

public class CTypeIrNode extends IrNode{
    // Conditionals

    Operand op1;
    Operand op2;

    public CTypeIrNode(Opcode opcode, FunctionScopeNode scope, Operand op1, Operand op2, String label) {
        super(opcode, scope);
        this.op1 = op1;
        this.op2 = op2;
        this.label = label;

        initGen(op1, op2);
    }

    @Override
    public String toString() {
        return "; " + opcode.name() + " " + op1.reference + " " + op2.reference + " " + label;
    }

    @Override
    public String toTiny() {
        if (!isStarter()) {
            registers = prevs.get(0).registers;
        }
        String rop1 = ensureRegister(op1, op2);
        String rop2 = ensureRegister(op2, op1);
        dropDeadRegisters(null, rop1, rop2);
        String op1Ref = rop1 == null ? op1.reference : rop1;
        String op2Ref = rop1 == null ? op2.reference : rop2;
        if (isEnder()) {
            restoreRegisteredVariables();
        }
        if (op1.dataType == INT) {
            tinyCode.append("cmpi " + op1Ref + " " + op2Ref);
        } else if (op1.dataType == FLOAT) {
            tinyCode.append("cmpr " + op1Ref + " " + op2Ref);
        }
        tinyCode.append("\n");
        switch (opcode) {
            case GT:
                tinyCode.append("jgt ");
                break;
            case LT:
                tinyCode.append("jlt ");
                break;
            case GE:
                tinyCode.append("jge ");
                break;
            case LE:
                tinyCode.append("jle ");
                break;
            case NE:
                tinyCode.append("jne ");
                break;
            case EQ:
                tinyCode.append("jeq ");
                break;
        }
        tinyCode.append(label + "\n");
        return tinyCode.toString();
    }
}
