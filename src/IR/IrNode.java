package IR;

import Nucleus.Operand;

public abstract class IrNode {
    private static final int NUM_OF_REGS = 4;

    public static enum Opcode {
        ADDI, SUBI, MULTI, DIVI,
        ADDF, SUBF, MULTF, DIVF,
        STOREI, STOREF,
        GT, GE, LT, LE, NE, EQ,
        JUMP,
        LABEL,
        READI, READF, READS, WRITEI, WRITEF, WRITES,
        JSR, PUSH, POP, RET, LINK,
        HALT, UNKNWN
    }

    public Opcode opcode;

    public IrNode(Opcode opcode) {
        this.opcode = opcode;
    }

    public abstract String toString();

    public abstract String toTiny();

    protected String operandToTiny(Operand operand) {
        String c = operand.reference;
        if(c.startsWith("$P")) {
            int p_offset = Integer.parseInt(c.replace("$P", "")) + 1; // offset by 1 for return address
            c = "$" + p_offset;
        }
        else if(c.startsWith("$R")) {
            int r_offset = Integer.parseInt(c.replace("$R", "")) + 1; // offset by 1 for return address
            c = "$" + r_offset;
        }
        if(c.startsWith("$L")) {
            int l_offset = Integer.parseInt(c.replace("$L", ""));
            c = "$-" + l_offset;
        }
        else {
            c = c.replace("$T", "r");
        }
        return c;
    }
}
