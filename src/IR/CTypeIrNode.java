package IR;

import Nucleus.Operand;

public class CTypeIrNode extends IrNode{
    // Conditionals

    Operand op1;
    Operand op2;
    String label;

    public CTypeIrNode(Opcode opcode, Operand op1, Operand op2, String label) {
        super(opcode);
        this.op1 = op1;
        this.op2 = op2;
        this.label = label;
    }

    @Override
    public String toString() {
        return "; " + opcode.name() + " " + op1.reference + " " + op2.reference + " " + label;
    }

    @Override
    public String toTiny() {
        StringBuilder a = new StringBuilder();
        if(op1.dataType == Operand.DataType.INT) {
            a.append("cmpi " + operandToTiny(op1) + " " + operandToTiny(op2));
        }
        else if (op1.dataType == Operand.DataType.FLOAT) {
            a.append("cmpr " + operandToTiny(op1) + " " + operandToTiny(op2));
        }
        a.append("\n");

        switch(opcode) {
            case GT: a.append("jgt "); break;
            case LT: a.append("jlt "); break;
            case GE: a.append("jge "); break;
            case LE: a.append("jle "); break;
            case NE: a.append("jne "); break;
            case EQ: a.append("jeq "); break;
        }
        a.append(label);

        return a.toString();
    }
}
