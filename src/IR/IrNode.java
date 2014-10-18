package IR;

public abstract class IrNode {
    public static enum Opcode {
        ADDI, SUBI, MULTI, DIVI,
        ADDF, SUBF, MULTF, DIVF,
        STOREI, STOREF,
        GT, GE, LT, LE, NE, EQ,
        JUMP,
        LABEL,
        READI, READF, READS, WRITEI, WRITEF, WRITES,
        RET,
        UNKNWN
    }

    public Opcode opcode;

    public IrNode(Opcode opcode) {
        this.opcode = opcode;
    }

    public abstract String toString();

    public abstract String toAssembly();

    protected String ttoreg(String temp) {
        return temp.replace("$T", "r");
    }
}
