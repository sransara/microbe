package IR;

import Nucleus.Operand;

public class RTypeIrNode extends IrNode{
    Operand op1;
    Operand op2;
    Operand result;

    public RTypeIrNode(Opcode opcode, Operand op1, Operand op2, Operand result) {
        super(opcode);
        this.op1 = op1;
        this.op2 = op2;
        this.result = result;
    }

    @Override
    public String toString() {
        return "; " + opcode.name() + " " + op1.reference + " " + op2.reference + " " + result.reference;
    }

    @Override
    public String toTiny() {
        StringBuilder a = new StringBuilder("move ");
        a.append(operandToTiny(op1) + " ");
        a.append(operandToTiny(result) + "\n");

        switch(opcode) {
            case ADDF: a.append("addr "); break;
            case ADDI: a.append("addi "); break;
            case SUBF: a.append("subr "); break;
            case SUBI: a.append("subi "); break;
            case MULTF: a.append("mulr "); break;
            case MULTI: a.append("muli "); break;
            case DIVF: a.append("divr "); break;
            case DIVI: a.append("divi "); break;
        }
        a.append(operandToTiny(op2) + " ");
        a.append(operandToTiny(result));
        return a.toString();
    }
}
