package IR;

import Nucleus.Operand;

public class MTypeIrNode extends IrNode{
    // STOREI, STOREF

    Operand op1;
    Operand result;

    public MTypeIrNode(Opcode opcode, Operand op1, Operand result) {
        super(opcode);
        this.op1 = op1;
        this.result = result;
    }

    @Override
    public String toString() {
        return "; " + opcode.name() + " " + op1.reference + " " + result.reference;
    }

    @Override
    public String toTiny() {
        StringBuilder a = new StringBuilder("move ");
        a.append(operandToTiny(op1) + " ");
        a.append(operandToTiny(result) + " ");
        return a.toString();
    }
}
