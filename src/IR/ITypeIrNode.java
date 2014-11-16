package IR;

import Nucleus.Operand;

public class ITypeIrNode extends IrNode{
    // System Interrupt
    Operand result;

    public ITypeIrNode(Opcode opcode, Operand result) {
        super(opcode);
        this.result = result;
    }

    @Override
    public String toString() {
        String a = null;
        switch(opcode) {
            case HALT: a = "; HALT"; break;
            default: a = "; " + opcode.name() + " " + result.reference;
        }
        return a;
    }

    @Override
    public String toTiny() {
        String a = null;
        switch(opcode) {
            case WRITEF: a = String.format("sys writer %s", operandToTiny(result)); break;
            case WRITEI: a = String.format("sys writei %s", operandToTiny(result)); break;
            case WRITES: a = String.format("sys writes %s", operandToTiny(result)); break;
            case READF: a = String.format("sys readr %s", operandToTiny(result)); break;
            case READI: a = String.format("sys readi %s", operandToTiny(result)); break;
            case READS: a = String.format("sys reads %s", operandToTiny(result)); break;
            case HALT: a = String.format("sys halt"); break;
        }
        return a;
    }
}
